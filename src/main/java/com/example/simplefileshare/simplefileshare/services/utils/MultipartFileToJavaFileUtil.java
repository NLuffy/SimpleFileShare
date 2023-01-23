package com.example.simplefileshare.simplefileshare.services.utils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.File;

@Service
public class MultipartFileToJavaFileUtil {
    
    public File convert(MultipartFile multipartFile) throws FileNotFoundException {
        try{
            File file = new File("tmp/"+multipartFile.getOriginalFilename());
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(multipartFile.getBytes());
            fos.close();
            System.out.println("File Created Successfully");
            return file;
        }
        catch(Exception e){
            throw new FileNotFoundException("Invalid File"); 
        }
    }

}
