package org.lumeninvestiga.backend.repositorio.tpi.exceptions;

public class NotFoundResourceException extends RuntimeException{
    public NotFoundResourceException() {
    }

    public NotFoundResourceException(String message) {
        super(message);
    }
}
