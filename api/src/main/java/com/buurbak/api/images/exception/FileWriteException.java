package com.buurbak.api.images.exception;

import lombok.Data;

@Data
public class FileWriteException extends RuntimeException {

    private String message;

    public FileWriteException(String message) {
        super(message);
        this.message = message;
    }
}
