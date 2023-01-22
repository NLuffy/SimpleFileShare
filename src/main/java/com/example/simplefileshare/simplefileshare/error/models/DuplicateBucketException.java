package com.example.simplefileshare.simplefileshare.error.models;

public class DuplicateBucketException extends RuntimeException {
    public DuplicateBucketException(String message) {
        super(message);
    }
}
