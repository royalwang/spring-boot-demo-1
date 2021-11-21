package com.bambrow.s3.service;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.bambrow.s3.config.S3Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class S3Service {

    @Autowired
    private S3Config s3Config;

    private AmazonS3 conn;

    @PostConstruct
    private void init() {
        AWSCredentials credentials = new BasicAWSCredentials(s3Config.getAk(), s3Config.getSk());
        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setProtocol(Protocol.HTTP);
        conn = new AmazonS3Client(credentials, clientConfig);
        conn.setEndpoint(s3Config.getEndpoint());
        log.info("S3Service init: initialization complete");
    }

    public List<Bucket> listBuckets() {
        return conn.listBuckets();
    }

    public List<String> listPrefixesAndObjects(String bucket, String prefix) {
        log.info("S3Service listPrefixesAndObjects: bucket = [" + bucket + "], prefix = [" + prefix + "]");
        boolean bucketLevel = false;
        String delimiter = "/";
        if (prefix == null) {
            prefix = "";
        }
        if (Objects.equals(prefix, "") || Objects.equals(prefix, delimiter)) {
            bucketLevel = true;
        }
        if (!prefix.isEmpty() && !prefix.endsWith(delimiter)) {
            prefix += delimiter;
        }
        log.info("S3Service listPrefixesAndObjects: prefix = " + prefix);
        ListObjectsRequest listObjectsRequest;
        if (bucketLevel) {
            listObjectsRequest = new ListObjectsRequest().withBucketName(bucket).withDelimiter(delimiter);
        } else {
            listObjectsRequest = new ListObjectsRequest().withBucketName(bucket).withPrefix(prefix).withDelimiter(delimiter);
        }
        ObjectListing objects = conn.listObjects(listObjectsRequest);
        List<String> prefixes = new ArrayList<>();
        List<S3ObjectSummary> files = new ArrayList<>();
        do {
            prefixes.addAll(objects.getCommonPrefixes());
            files.addAll(objects.getObjectSummaries());
            objects = conn.listNextBatchOfObjects(objects);
        } while (objects.isTruncated());
        prefixes.addAll(files.stream().map(S3ObjectSummary::getKey).collect(Collectors.toList()));
        return prefixes.stream().map(x -> bucket + delimiter + x).collect(Collectors.toList());
    }

    public String openString(String bucket, String key) {
        log.info("S3Service openString: bucket = [" + bucket + "], key = [" + key + "]");
        return conn.getObjectAsString(bucket, key);
    }

}
