package com.example.simplefileshare.simplefileshare.aws.utils;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;

public class AWSObjectUtils {

    private final AmazonS3 awsS3Client;

    public AWSObjectUtils(AmazonS3 awsS3Client) {
        this.awsS3Client = awsS3Client;
    }

    public void uploadFile(String bucketName, String key, File file, String contentType) throws SdkClientException {
        PutObjectRequest request = new PutObjectRequest(bucketName, key, file).withCannedAcl(CannedAccessControlList.PublicRead);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);
        metadata.addUserMetadata("title", file.getName());
        request.setMetadata(metadata);
        awsS3Client.putObject(request);
    }

    public String getObjectUrl(String bucketName, String key) {
        return awsS3Client.getUrl(bucketName, key).toExternalForm();
    }
}
