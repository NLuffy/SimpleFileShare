package com.example.simplefileshare.simplefileshare.services;

import com.amazonaws.services.s3.AmazonS3;
import com.example.simplefileshare.simplefileshare.aws.utils.AWSObjectUtils;
import com.example.simplefileshare.simplefileshare.services.utils.MultipartFileToJavaFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.amazonaws.SdkClientException;
import java.io.File;
import org.springframework.web.multipart.MultipartFile;


@Service
public class AWSService implements UploadInterface {
    
    @Autowired
    private AmazonS3 awsS3Client;

    @Override
    public String uploadMultipartFile (MultipartFile multipartFile) throws Exception {
            File file = MultipartFileToJavaFileUtil.convert(multipartFile);
            String result = this.uploadFile(file); 
            file.delete();
            return result;
    }

    @Override
    public String uploadFile (File file) throws SdkClientException {
        try{
            AWSObjectUtils objectUtils = new AWSObjectUtils(awsS3Client);
            objectUtils.uploadFile("simplefilehost", "AWSBucketUtils", file, "plain/text");
            return objectUtils.getObjectUrl("simplefilehost", "AWSBucketUtils");
        } catch(Exception e) {
            file.delete();
            throw e;
        }
    }
}
