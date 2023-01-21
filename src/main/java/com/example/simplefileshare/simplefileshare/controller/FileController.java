package com.example.simplefileshare.simplefileshare.controller;
import org.springframework.web.bind.annotation.*;
import java.io.File;

@RestController
@RequestMapping("/api/upload")
public class FileController {

    @GetMapping("/api/get")
    public String get(File uploadFile){
        //not needed
        return "File Get";
    }
    
    @PostMapping
    public String upload(File uploadFile){
        //Upload File to S3 Logic
        return "File Uploaded";
    }
}
