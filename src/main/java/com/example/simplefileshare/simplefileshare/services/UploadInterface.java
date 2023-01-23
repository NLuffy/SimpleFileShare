package com.example.simplefileshare.simplefileshare.services;
import java.io.File;
import org.springframework.web.multipart.MultipartFile;

public interface UploadInterface {
    public String uploadFile (File file);
    public String uploadMultipartFile (MultipartFile multipartFile) throws Exception;
}
