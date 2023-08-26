package com.example.survey.exception;

import java.io.Serial;

public class UserNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -7630066735832452688L;

    public UserNotFoundException(final String message) {
        super(message);
    }
}
