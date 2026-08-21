package com.alejogiraldoo.domain.errors;

public class CustomError extends Exception {
    public CustomError(String message) {
        super(String.format("\nERROR: %s", message));
    }
}
