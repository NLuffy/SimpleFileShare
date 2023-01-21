package com.example.simplefileshare.simplefileshare.controller;
import org.springframework.web.bind.annotation.*;
import java.io.File;

@RestController
@RequestMapping("/api/files")
public class FileController {
    @PostMapping
    public String upload(File uploadFile){
        //Upload File to S3 Logic
        return "File Uploaded";
    }
}
