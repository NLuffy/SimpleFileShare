package com.example.simplefileshare.simplefileshare.services;

import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UploadService {

    private AWSService awsService = new AWSService();

    public String uploadMultipartFile(MultipartFile multipartFile) throws Exception {
        String result = "";
        try{
            File file = convertMultipartFileToFile(multipartFile);
            if(null!=file)
                result = awsService.uploadFile(file);
            else
                throw new FileNotFoundException("Invalid File");  
        } catch(Exception e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage());
        }
        return result;
    }

    public File convertMultipartFileToFile(MultipartFile multipartFile) {
        try{
            File file = new File("/c/Users/nishekha/Desktop/Java/SimpleFileShare/src/main/java/com/example/simplefileshare/simplefileshare/aws/utils/file.tmp");
            multipartFile.transferTo(file);
            return file;
        }
        catch(Exception e){
            Logger.getGlobal().log(Level.SEVERE, e.getMessage());
            return null;
        }
    }
    
}
