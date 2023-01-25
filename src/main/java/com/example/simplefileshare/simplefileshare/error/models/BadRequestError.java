package com.example.simplefileshare.simplefileshare.error.models;

public class BadRequestError extends RuntimeException {
        public BadRequestError(String message) {
            super(message);
        }
}
