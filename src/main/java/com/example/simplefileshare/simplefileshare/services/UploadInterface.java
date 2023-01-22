package com.example.simplefileshare.simplefileshare.services;
import java.io.File;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.SdkClientException;
import java.io.FileNotFoundException;

public interface UploadInterface {
    public String uploadFile (File file) throws SdkClientException;
    public String uploadMultipartFile (MultipartFile multipartFile) throws SdkClientException,FileNotFoundException;
}
