package com.example.simplefileshare.simplefileshare.error.utils;

import org.springframework.http.HttpStatusCode;
import org.springframework.lang.Nullable;
import org.springframework.web.client.HttpClientErrorException;

public class ErrorUtils {
    public static HttpClientErrorException createApiError(HttpStatusCode statusCode, String statusText, @Nullable String message) {
        return HttpClientErrorException.create(message, statusCode, statusText, null, null, null);
    }
}
