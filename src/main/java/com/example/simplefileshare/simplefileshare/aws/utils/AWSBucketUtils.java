package com.example.simplefileshare.simplefileshare.aws.utils;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.example.simplefileshare.simplefileshare.error.models.DuplicateBucketException;

import java.util.List;


public class AWSBucketUtils {

    private final AmazonS3 awsS3Client;

    public AWSBucketUtils(AmazonS3 awsS3client) {
        this.awsS3Client = awsS3client;
    }

    public void createS3Bucket(String bucketName) throws SdkClientException, DuplicateBucketException {
        if (awsS3Client.doesBucketExistV2(bucketName)) {
            throw new DuplicateBucketException("Bucket name already in use");
        }
        awsS3Client.createBucket(bucketName);
    }

    public List<Bucket> listBuckets() throws SdkClientException {
        return awsS3Client.listBuckets();
    }

    public void deleteBucket(String bucketName) throws SdkClientException {
        awsS3Client.deleteBucket(bucketName);
    }
}
