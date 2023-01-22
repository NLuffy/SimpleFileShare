package com.example.simplefileshare.simplefileshare.services;

import com.amazonaws.services.s3.AmazonS3;
import com.example.simplefileshare.simplefileshare.aws.utils.AWSObjectUtils;
import com.example.simplefileshare.simplefileshare.services.utils.MultipartFileToJavaFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.amazonaws.SdkClientException;
import java.io.File;
import java.io.FileNotFoundException;
import org.springframework.web.multipart.MultipartFile;


@Service
public class AWSService implements UploadInterface {
    
    @Autowired
    private AmazonS3 awsS3Client;

    @Autowired
    private MultipartFileToJavaFileUtil multipartFileToJavaFileConverter;

    @Override
    public String uploadMultipartFile (MultipartFile multipartFile) throws FileNotFoundException, SdkClientException {
            File file = multipartFileToJavaFileConverter.convert(multipartFile);
            return this.uploadFile(file); 
    }

    @Override
    public String uploadFile (File file) throws SdkClientException {
        AWSObjectUtils objectUtils = new AWSObjectUtils(awsS3Client);
        objectUtils.uploadFile("simplefilehost", "AWSBucketUtils", file, "plain/text");
        return objectUtils.getObjectUrl("simplefilehost", "AWSBucketUtils");
    }
}
