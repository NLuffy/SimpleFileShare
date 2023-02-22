package com.example.simplefileshare.simplefileshare.error;

import com.amazonaws.SdkClientException;
import com.example.simplefileshare.simplefileshare.data.models.base.BaseResponse;
import com.example.simplefileshare.simplefileshare.error.models.ApiErrorMessage;
import com.example.simplefileshare.simplefileshare.error.models.BadRequestError;
import com.example.simplefileshare.simplefileshare.error.utils.ErrorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @Value("${spring.application.debug}")
    private boolean debugMode;

    @ExceptionHandler
    public ResponseEntity<BaseResponse<?>> httpErrorExceptionHandler(HttpClientErrorException exception) {
        log.error(exception.getLocalizedMessage(), exception);
        if (debugMode) {
            return new ResponseEntity<>(new BaseResponse<>(new ApiErrorMessage(exception.getLocalizedMessage(), ErrorUtils.getStackTraceAsString(exception)), null), exception.getStatusCode());
        } else {
            return new ResponseEntity<>(new BaseResponse<Object>(new ApiErrorMessage(exception.getLocalizedMessage()), null), exception.getStatusCode());
        }
    }

    @ExceptionHandler
    public ResponseEntity<BaseResponse<?>> amazonSdkClientExceptionHandler(SdkClientException exception) {
        log.error(exception.getLocalizedMessage(), exception);
        return httpErrorExceptionHandler(ErrorUtils.createApiError(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), "Some error occurred", exception.getStackTrace()));
    }

    @ExceptionHandler
    public ResponseEntity<BaseResponse<?>> customBadRequestErrorExceptionHandler(BadRequestError exception) {
        return httpErrorExceptionHandler(ErrorUtils.createApiError(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(), exception.getLocalizedMessage(), exception.getStackTrace()));
    }

    @ExceptionHandler
    public ResponseEntity<BaseResponse<?>> generalExceptionHandler(Exception e) {
        log.error(e.getLocalizedMessage(), e);
        return httpErrorExceptionHandler(ErrorUtils.createApiError(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getLocalizedMessage(), e.getStackTrace()));
    }
}
