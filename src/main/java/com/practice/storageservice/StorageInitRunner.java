package com.practice.storageservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;

import java.net.URI;

@Component
@Slf4j
public class StorageInitRunner implements ApplicationRunner {


    private static final String STAGING_BUCKET = "staging-bucket";
    private static final String PERMANENT_BUCKET = "permanent-bucket";
    private URI s3Uri;
    private static final StaticCredentialsProvider CREDENTIALS_PROVIDER = StaticCredentialsProvider.create(
            AwsBasicCredentials.create("test", "test"));
    private S3Client s3Client;

    @Value("${s3.host}")
    private String s3_host;
    @Value("${s3.port}")
    private String s3_port;
    @Autowired
    private StorageService storageService;

    @Override
    public void run(ApplicationArguments args) {
        log.info("Starting to init S3 storages");
        s3Uri = URI.create(s3_host + ":" + s3_port);
        s3Client = S3Client.builder()
                .endpointOverride(s3Uri)
                .credentialsProvider(CREDENTIALS_PROVIDER)
                .region(Region.US_EAST_1)
                .build();
        storageService.cleanUpAllStorages();
        s3Client.createBucket(CreateBucketRequest.builder()
                .bucket(STAGING_BUCKET)
                .build());
        storageService.createStorage(StorageType.STAGING, STAGING_BUCKET);
        s3Client.createBucket(CreateBucketRequest.builder()
                .bucket(PERMANENT_BUCKET)
                .build());
        storageService.createStorage(StorageType.PERMANENT, PERMANENT_BUCKET);
        log.info("Successfully init default S3 storages");
    }
}
