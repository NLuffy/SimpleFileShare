package com.example.simplefileshare.simplefileshare.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.example.simplefileshare.simplefileshare.services.UploadService;
import org.springframework.web.multipart.MultipartFile;
import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
@RequestMapping("/api/files")
public class FileController {

    private UploadService uploadService = new UploadService();

    @PostMapping
    public String upload(@RequestBody MultipartFile file) throws Exception{
        //Upload File to S3 Logic
        try{
            String link = uploadService.uploadMultipartFile(file);
            if(link.isBlank()) throw new Error("File upload failed");
            return "File Uploaded. File link: "+link;
        } catch (Exception e){
            Logger.getGlobal().log(Level.SEVERE, e.getMessage());
            return "Upload failed";
        }
    }

    @ExceptionHandler()
    public ResponseEntity<?> FileNotUploaded(Exception exc){
        return ResponseEntity.notFound().build();
    }
}
