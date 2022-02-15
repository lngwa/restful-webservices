package com.lewis.in28minsrestfulwebservices.exception;

public class PostAlreadyExistException extends RuntimeException {
    public PostAlreadyExistException(String message) {
        super(message);
    }
}
