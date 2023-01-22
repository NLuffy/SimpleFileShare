package com.example.simplefileshare.simplefileshare.controllers;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.example.simplefileshare.simplefileshare.aws.utils.AWSObjectUtils;
import com.example.simplefileshare.simplefileshare.data.models.base.BaseResponse;
import com.example.simplefileshare.simplefileshare.error.utils.ErrorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class RootController {

    @Autowired
    private AmazonS3 awsS3Client;

    @GetMapping("/")
    public ResponseEntity<BaseResponse<String>> getRoot() {
        try {
            uploadFile();
            return new ResponseEntity<>(new BaseResponse<>(null, "File uploaded"), HttpStatus.OK);
        } catch (SdkClientException e) {
            throw ErrorUtils.createApiError(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), "Some error occurred");
        }
    }

    private void uploadFile() throws SdkClientException {
        File file = new File("/Users/tnluser/personal/Code/PersonalProjects/SimpleFileShare/src/main/java/com/example/simplefileshare/simplefileshare/aws/utils/AWSBucketUtils.java");
        AWSObjectUtils objectUtils = new AWSObjectUtils(awsS3Client);
        objectUtils.uploadFile("simplefilehost", "AWSBucketUtils", file, "plain/text");
    }

}
