package com.example.simplefileshare.simplefileshare.controllers;

import com.amazonaws.services.s3.AmazonS3;
import com.example.simplefileshare.simplefileshare.aws.utils.AWSObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class RootController {

    @Autowired
    private AmazonS3 awsS3Client;

    @GetMapping("/")
    public String getRoot() {
        uploadFile();
        return "Hello";
    }

    private void uploadFile() {
        try {
            File file = new File("/Users/tnluser/personal/Code/PersonalProjects/SimpleFileShare/src/main/java/com/example/simplefileshare/simplefileshare/aws/utils/AWSBucketUtils.java");
            AWSObjectUtils objectUtils = new AWSObjectUtils(awsS3Client);
            objectUtils.uploadFile("simplefilehost", "AWSBucketUtils", file, true, "plain/text");
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage());
        }
    }

}
