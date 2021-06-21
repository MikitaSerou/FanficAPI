package com.example.fanficapi.exception;

public class ThemeNotFoundException extends RuntimeException {
    public ThemeNotFoundException(String message) {
        super(message);
    }
}