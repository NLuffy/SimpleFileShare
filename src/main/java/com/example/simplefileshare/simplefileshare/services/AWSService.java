package com.example.simplefileshare.simplefileshare.services;

import com.amazonaws.services.s3.AmazonS3;
import com.example.simplefileshare.simplefileshare.aws.utils.AWSObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AWSService {
    
    @Autowired
    private AmazonS3 awsS3Client;

    public String uploadFile(File file) {
        try {
            AWSObjectUtils objectUtils = new AWSObjectUtils(awsS3Client);
            objectUtils.uploadFile("simplefilehost", "AWSBucketUtils", file, true, "plain/text");
            return "<link to file>";
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage());
        }
        return "";
    }
}
