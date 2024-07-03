package org.lumeninvestiga.backend.repositorio.tpi.exceptions;

public class ReferenceNotFoundException extends RuntimeException{
    public ReferenceNotFoundException() {
    }

    public ReferenceNotFoundException(String message) {
        super(message);
    }
}
