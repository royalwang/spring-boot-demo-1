package com.bambrow.s3.dto;

public class FileObject {
    private String path;
    private boolean isDir;

    public FileObject() {}

    public FileObject(String path, boolean isDir) {
        this.path = path;
        this.isDir = isDir;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isDir() {
        return isDir;
    }

    public void setDir(boolean dir) {
        isDir = dir;
    }
}
