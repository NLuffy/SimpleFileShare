package com.example.simplefileshare.simplefileshare.error.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

import java.util.Objects;


public record ApiErrorMessage(
        @JsonProperty("error_message") String errorMessage,
        @JsonProperty("error_debug_message") String errorDebugMessage
) {
    public ApiErrorMessage {
        Objects.requireNonNull(errorMessage);
    }

    public ApiErrorMessage(String errorMessage) {
        this(errorMessage, null);
    }
}
