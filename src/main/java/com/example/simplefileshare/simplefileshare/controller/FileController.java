package com.example.simplefileshare.simplefileshare.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.simplefileshare.simplefileshare.services.AWSService;
import com.example.simplefileshare.simplefileshare.error.utils.ErrorUtils;
import com.example.simplefileshare.simplefileshare.data.models.base.BaseResponse;
import com.amazonaws.SdkClientException;
import java.io.FileNotFoundException;


@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private AWSService uploadService;

    @PostMapping
    public ResponseEntity<BaseResponse<String>> upload(@RequestParam("file") MultipartFile file) throws Exception{
        System.out.println(file);
        try{
            String url = uploadService.uploadMultipartFile(file);
            return new ResponseEntity<>(new BaseResponse<>(null, url), HttpStatus.CREATED);
        } catch (SdkClientException e){
            throw ErrorUtils.createApiError(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), "Some error occurred");
        } catch (Exception e){
            throw ErrorUtils.createApiError(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), "Some file error occurred");
        }
    }
}
