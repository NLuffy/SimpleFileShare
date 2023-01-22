package com.example.simplefileshare.simplefileshare.services.utils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileNotFoundException;
import java.io.File;

@Service
public class MultipartFileToJavaFileUtil {
    
    public File convert(MultipartFile multipartFile) throws FileNotFoundException {
        try{
            File file = new File(multipartFile.getName());
            multipartFile.transferTo(file);
            System.out.println("File Created Successfully");
            return file;
        }
        catch(Exception e){
            throw new FileNotFoundException("Invalid File"); 
        }
    }

}
