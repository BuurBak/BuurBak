package com.buurbak.api.images.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GCPFileUploadException extends RuntimeException {
    private String message;

    public GCPFileUploadException(String message) {
        super(message);
        this.message = message;
    }
}
