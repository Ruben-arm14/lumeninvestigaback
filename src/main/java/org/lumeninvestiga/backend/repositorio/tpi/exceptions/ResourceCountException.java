package org.lumeninvestiga.backend.repositorio.tpi.exceptions;

public class ResourceCountException extends RuntimeException{
    public ResourceCountException() {
    }

    public ResourceCountException(String message) {
        super(message);
    }
}
