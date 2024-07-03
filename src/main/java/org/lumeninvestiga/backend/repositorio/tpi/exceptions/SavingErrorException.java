package org.lumeninvestiga.backend.repositorio.tpi.exceptions;

public class SavingErrorException extends RuntimeException{
    public SavingErrorException() {
    }

    public SavingErrorException(String message) {
        super(message);
    }
}
