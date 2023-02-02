package com.example.simplefileshare.simplefileshare.controller;

import com.example.simplefileshare.simplefileshare.data.models.base.BaseResponse;
import com.example.simplefileshare.simplefileshare.error.models.BadRequestError;
import com.example.simplefileshare.simplefileshare.services.AWSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;


@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private AWSService awsService;


    @PostMapping
    public ResponseEntity<BaseResponse<String>> upload(@RequestParam("file") MultipartFile file) throws Exception {
        checkFileSanity(file);
        String url = awsService.uploadMultipartFile(file);
        return new ResponseEntity<>(new BaseResponse<>(null, url), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse<String>> delete(@RequestParam("url") URL url) throws Exception {
        final String response = awsService.deleteFile(url);
        return new ResponseEntity<>(new BaseResponse<>(null, response), HttpStatus.OK);
    }

    private void checkFileSanity(MultipartFile file) throws Exception {
        if (null == file || file.isEmpty()) throw new BadRequestError("File is empty");
        if (file.getSize() / (1024 * 1024) > 10.0) throw new BadRequestError("File size > 10 MB");
    }
}
