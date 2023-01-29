package com.example.simplefileshare.simplefileshare.services;

import com.amazonaws.services.s3.AmazonS3;
import com.example.simplefileshare.simplefileshare.aws.utils.AWSObjectUtils;
import com.example.simplefileshare.simplefileshare.services.utils.MultipartFileToJavaFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.nio.file.Files;
import java.net.URL;
import org.springframework.web.multipart.MultipartFile;


@Service
public class AWSService implements UploadInterface, DeleteInterface {
    
    @Autowired
    private AmazonS3 awsS3Client;

    private final String bucketName = "simplefilehost";

    @Override
    public String uploadMultipartFile (MultipartFile multipartFile) throws Exception {
            File file = MultipartFileToJavaFileUtil.convert(multipartFile);
            String result = this.uploadFile(file); 
            file.delete();
            return result;
    }

    @Override
    public String uploadFile (File file) throws Exception {
        try{
            AWSObjectUtils objectUtils = new AWSObjectUtils(awsS3Client);
            objectUtils.uploadFile(bucketName, file.getName(), file, Files.probeContentType(file.toPath()));
            return objectUtils.getObjectUrl(bucketName, file.getName());
        } catch(Exception e) {
            file.delete();
            throw e;
        }
    }

    public String deleteFile (final URL url) throws Exception {
        AWSObjectUtils objectUtils = new AWSObjectUtils(awsS3Client);
        String key = url.getPath();
        key = key.substring(1, key.length());
        objectUtils.deleteFile(bucketName, key);
        return ("File " + key + " deleted successfully!");
    }
}
