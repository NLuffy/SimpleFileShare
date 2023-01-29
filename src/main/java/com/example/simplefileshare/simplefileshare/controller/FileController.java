package com.example.simplefileshare.simplefileshare.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import com.example.simplefileshare.simplefileshare.services.AWSService;
import com.example.simplefileshare.simplefileshare.error.models.BadRequestError;
import com.example.simplefileshare.simplefileshare.error.utils.ErrorUtils;
import com.example.simplefileshare.simplefileshare.data.models.base.BaseResponse;
import com.amazonaws.SdkClientException;
import java.net.URL;


@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private AWSService awsService;


    @PostMapping
    public ResponseEntity<BaseResponse<String>> upload(@RequestParam("file") MultipartFile file) throws Exception {
        try {
            checkFileSanity(file);
            String url = awsService.uploadMultipartFile(file);
            return new ResponseEntity<>(new BaseResponse<>(null, url), HttpStatus.CREATED);
        } catch (SdkClientException e) {
            throw ErrorUtils.createApiError(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), "Some error occurred", e.getStackTrace());
        } catch (BadRequestError e) {
            throw ErrorUtils.createApiError(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage(), e.getStackTrace());
        } catch (Exception e) {
            throw ErrorUtils.createApiError(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), "Some file error occurred", e.getStackTrace());
        }
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse<String>> delete (@RequestParam("url") URL url) throws Exception {
        try {
            final String response = awsService.deleteFile(url);
            return new ResponseEntity<>(new BaseResponse<>(null, response), HttpStatus.OK);
        } catch (Exception e) {
            throw ErrorUtils.createApiError(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), "Some error occueerd", e.getStackTrace());
        }
    }

    private void checkFileSanity(MultipartFile file) throws Exception {
        if (null == file || file.isEmpty()) throw new BadRequestError("File is empty");
        if (file.getSize() / (1024 * 1024) > 10.0) throw new BadRequestError("File size > 10 MB");   
    }
}
