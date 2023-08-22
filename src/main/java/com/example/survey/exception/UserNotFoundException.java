package com.example.survey.exception;

public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -7630066735832452688L;

    public UserNotFoundException(final String message) {
        super(message);
    }
}
