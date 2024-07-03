package org.lumeninvestiga.backend.repositorio.tpi.exceptions;

public class InvalidResourceException extends RuntimeException{
    public InvalidResourceException() {
    }

    public InvalidResourceException(String message) {
        super(message);
    }
}
