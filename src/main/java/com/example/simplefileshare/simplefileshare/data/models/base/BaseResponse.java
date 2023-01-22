package com.example.simplefileshare.simplefileshare.data.models.base;

import com.example.simplefileshare.simplefileshare.error.models.ApiErrorMessage;

import java.util.Objects;

public record BaseResponse<T>(
        ApiErrorMessage error,
        T data
) {
    public BaseResponse {
        if (error == null) {
            Objects.requireNonNull(data);
        } else if (data == null) {
            Objects.requireNonNull(error);
        }
    }
}
