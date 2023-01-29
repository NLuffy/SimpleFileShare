package com.example.simplefileshare.simplefileshare.services;

import java.io.File;
import org.springframework.web.multipart.MultipartFile;
import java.net.URL;

public interface FileHandlingInterface {
    public String uploadFile (File file) throws Exception;
    public String uploadMultipartFile (MultipartFile multipartFile) throws Exception;
    public String deleteFile (final URL url) throws Exception;
}
