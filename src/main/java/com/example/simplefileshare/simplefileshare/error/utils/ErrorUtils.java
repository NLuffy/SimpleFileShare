package com.example.simplefileshare.simplefileshare.error.utils;

import org.springframework.http.HttpStatusCode;
import org.springframework.lang.Nullable;
import org.springframework.web.client.HttpClientErrorException;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorUtils {
    public static HttpClientErrorException createApiError(HttpStatusCode statusCode, String statusText, @Nullable String message, @Nullable StackTraceElement[] stackTraceElements) {
        HttpClientErrorException exception = HttpClientErrorException.create(message, statusCode, statusText, null, null, null);
        if (stackTraceElements != null) {
            exception.setStackTrace(stackTraceElements);
        }
        return exception;
    }

    public static String getStackTraceAsString(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}
