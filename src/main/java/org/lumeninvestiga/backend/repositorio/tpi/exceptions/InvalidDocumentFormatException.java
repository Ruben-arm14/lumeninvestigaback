package org.lumeninvestiga.backend.repositorio.tpi.exceptions;

public class InvalidDocumentFormatException extends RuntimeException{
    public InvalidDocumentFormatException() {
    }

    public InvalidDocumentFormatException(String message) {
        super(message);
    }
}
