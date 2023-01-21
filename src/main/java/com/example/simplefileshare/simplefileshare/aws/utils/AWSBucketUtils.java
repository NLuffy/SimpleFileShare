package com.example.simplefileshare.simplefileshare.aws.utils;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AWSBucketUtils {

    private final AmazonS3 awsS3Client;

    public AWSBucketUtils(AmazonS3 awsS3client) {
        this.awsS3Client = awsS3client;
    }

    public void createS3Bucket(String bucketName) {
        if (awsS3Client.doesBucketExistV2(bucketName)) {
            Logger.getGlobal().log(Level.INFO, "Bucket name already in use. Try another name.");
            return;
        }
        awsS3Client.createBucket(bucketName);
    }

    public List<Bucket> listBuckets() {
        return awsS3Client.listBuckets();
    }

    public void deleteBucket(String bucketName) {
        try {
            awsS3Client.deleteBucket(bucketName);
        } catch (AmazonServiceException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getErrorMessage());
            return;
        }
    }
}
