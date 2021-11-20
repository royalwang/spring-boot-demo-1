package com.bambrow.hdfs.service;

import com.bambrow.hdfs.config.HdfsConfig;
import com.bambrow.hdfs.dto.FileObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class HdfsService {

    @Autowired
    private HdfsConfig hdfsConfig;

    private Configuration configuration;

    @PostConstruct
    private void init() {
        configuration = new Configuration();
        configuration.set("fs.defaultFS", hdfsConfig.getRoot().endsWith("/") ? hdfsConfig.getRoot() : hdfsConfig.getRoot() + "/");
        log.info("HdfsService init: initialization complete");
    }

    private void close(FileSystem fs) {
        if (fs != null) {
            try {
                fs.close();
            } catch (Exception e) {
                log.error("HdfsService close: " + e.getMessage());
            }
        }
    }

    private FileSystem getFileSystem() {
        try {
            return FileSystem.get(new URI(hdfsConfig.getRoot().endsWith("/") ? hdfsConfig.getRoot() : hdfsConfig.getRoot() + "/"), configuration, hdfsConfig.getUser());
        } catch (Exception e) {
            log.error("HdfsService getFileSystem: " + e.getMessage());
            return null;
        }
    }

    private String getHdfsPath(String path) {
        String rootPath = hdfsConfig.getRoot().endsWith("/") ? hdfsConfig.getRoot() : hdfsConfig.getRoot() + "/";
        path = path.trim();
        String relativePath = path.startsWith("/") ? path.substring(1) : path;
        return rootPath + relativePath;
    }

    public boolean exists(String path) {
        if (StringUtils.isEmpty(path)) {
            return false;
        }
        FileSystem fs = getFileSystem();
        Path hdfsPath = new Path(getHdfsPath(path));
        boolean exists = false;
        try {
            exists = fs.exists(hdfsPath);
        } catch (Exception e) {
            log.error("HdfsService exists: " + e.getMessage());
        } finally {
            close(fs);
        }
        return exists;
    }

    public boolean isFile(String path) {
        if (StringUtils.isEmpty(path)) {
            return false;
        }
        FileSystem fs = getFileSystem();
        Path hdfsPath = new Path(getHdfsPath(path));
        boolean isFile = false;
        try {
            isFile = fs.getFileStatus(hdfsPath).isFile();
        } catch (Exception e) {
            log.error("HdfsService isFile: " + e.getMessage());
        } finally {
            close(fs);
        }
        return isFile;
    }

    public boolean isDirectory(String path) {
        if (StringUtils.isEmpty(path)) {
            return true;
        }
        FileSystem fs = getFileSystem();
        Path hdfsPath = new Path(getHdfsPath(path));
        boolean isDirectory = false;
        try {
            isDirectory = fs.getFileStatus(hdfsPath).isDirectory();
        } catch (Exception e) {
            log.error("HdfsService isFile: " + e.getMessage());
        } finally {
            close(fs);
        }
        return isDirectory;
    }

    public boolean mkdir(String path) {
        if (StringUtils.isEmpty(path)) {
            return false;
        }
        if (exists(path)) {
            return false;
        }
        FileSystem fs = getFileSystem();
        Path hdfsPath = new Path(getHdfsPath(path));
        boolean mkdir = false;
        try {
            mkdir = fs.mkdirs(hdfsPath);
        } catch (Exception e) {
            log.error("HdfsService mkdir: " + e.getMessage());
        } finally {
            close(fs);
        }
        return mkdir;
    }

    public List<FileObject> ls(String path, PathFilter filter) {
        List<FileObject> list = new ArrayList<>();
        FileSystem fs = getFileSystem();
        Path hdfsPath = new Path(getHdfsPath(path));
        try {
            FileStatus[] statusList;
            if (filter != null) {
                statusList = fs.listStatus(hdfsPath, filter);
            } else {
                statusList = fs.listStatus(hdfsPath);
            }
            if (statusList != null) {
                String rootPath = hdfsConfig.getRoot().endsWith("/") ? hdfsConfig.getRoot().substring(0, hdfsConfig.getRoot().length() - 1) : hdfsConfig.getRoot();
                for (FileStatus status : statusList) {
                    String absolutePath = status.getPath().toString();
                    String relativePath = absolutePath.replaceFirst("^" + rootPath.replace("/", "\\/"), "");
                    FileObject fileObject = new FileObject();
                    fileObject.setPath(relativePath);
                    fileObject.setDir(status.isDirectory());
                    list.add(fileObject);
                }
            }
        } catch (Exception e) {
            log.error("HdfsService ls: " + e.getMessage());
        } finally {
            close(fs);
        }
        return list;
    }

    public void put(String file, String path) {
        Path localPath = new Path(file);
        Path hdfsPath = new Path(getHdfsPath(path));
        FileSystem fs = getFileSystem();
        try {
            fs.copyFromLocalFile(localPath, hdfsPath);
        } catch (Exception e) {
            log.error("HdfsService put: " + e.getMessage());
        } finally {
            close(fs);
        }
    }

    public void get(String file, String path) {
        Path hdfsPath = new Path(getHdfsPath(file));
        Path localPath = new Path(path);
        FileSystem fs = getFileSystem();
        try {
            fs.copyToLocalFile(hdfsPath, localPath);
        } catch (Exception e) {
            log.error("HdfsService get: " + e.getMessage());
        } finally {
            close(fs);
        }
    }

    public byte[] openBytes(String path) {
        Path hdfsPath = new Path(getHdfsPath(path));
        FileSystem fs = getFileSystem();
        FSDataInputStream is;
        byte[] bytes = null;
        try {
            is = fs.open(hdfsPath);
            bytes = IOUtils.toByteArray(is);
        } catch (Exception e) {
            log.error("HdfsService openBytes: " + e.getMessage());
        } finally {
            close(fs);
        }
        return bytes;
    }

    public String openString(String path) {
        Path hdfsPath = new Path(getHdfsPath(path));
        FileSystem fs = getFileSystem();
        FSDataInputStream is;
        String content = null;
        try {
            is = fs.open(hdfsPath);
            content =  IOUtils.toString(is, Charset.forName("UTF-8"));
        } catch (Exception e) {
            log.error("HdfsService openString: " + e.getMessage());
        } finally {
            close(fs);
        }
        return content;
    }

    public boolean rm(String path) {
        Path hdfsPath = new Path(getHdfsPath(path));
        FileSystem fs = getFileSystem();
        boolean rm = false;
        try {
            rm = fs.delete(hdfsPath, true);
        } catch (Exception e) {
            log.error("HdfsService rm: " + e.getMessage());
        } finally {
            close(fs);
        }
        return rm;
    }

}
