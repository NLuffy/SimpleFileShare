package com.example.simplefileshare.simplefileshare.error;

import com.example.simplefileshare.simplefileshare.data.models.base.BaseResponse;
import com.example.simplefileshare.simplefileshare.error.models.ApiErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<BaseResponse<?>> badRequestExceptionHandler(HttpClientErrorException.BadRequest badRequest) {
        log.error(badRequest.getMessage(), badRequest);
        return new ResponseEntity<>(new BaseResponse<>(new ApiErrorMessage(badRequest.getMessage()), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<BaseResponse<?>> httpErrorExceptionHandler(HttpClientErrorException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(new BaseResponse<>(new ApiErrorMessage(exception.getMessage()), null), exception.getStatusCode());
    }
}
