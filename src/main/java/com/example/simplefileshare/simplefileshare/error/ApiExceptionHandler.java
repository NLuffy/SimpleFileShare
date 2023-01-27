package com.example.simplefileshare.simplefileshare.error;

import com.example.simplefileshare.simplefileshare.data.models.base.BaseResponse;
import com.example.simplefileshare.simplefileshare.error.models.ApiErrorMessage;
import com.example.simplefileshare.simplefileshare.error.utils.ErrorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @Value("${spring.application.debug}")
    private boolean debugMode;

    @ExceptionHandler
    public ResponseEntity<BaseResponse<?>> badRequestExceptionHandler(HttpClientErrorException.BadRequest badRequest) {
        log.error(badRequest.getMessage(), badRequest);
        if (debugMode) {
            return new ResponseEntity<>(new BaseResponse<>(new ApiErrorMessage(badRequest.getMessage(), ErrorUtils.getStackTraceAsString(badRequest)), null), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(new BaseResponse<>(new ApiErrorMessage(badRequest.getMessage()), null), HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler
    public ResponseEntity<BaseResponse<?>> httpErrorExceptionHandler(HttpClientErrorException exception) {
        log.error(exception.getMessage(), exception);
        if (debugMode) {
            return new ResponseEntity<>(new BaseResponse<>(new ApiErrorMessage(exception.getMessage(), ErrorUtils.getStackTraceAsString(exception)), null), exception.getStatusCode());
        } else {
            return new ResponseEntity<>(new BaseResponse<Object>(new ApiErrorMessage(exception.getMessage()), null), exception.getStatusCode());
        }
    }
}
