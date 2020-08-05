package com.junior.technical.exception;

public class SimulationException extends RuntimeException {
    public SimulationException() {
        super();
    }

    public SimulationException(String message) {
        super(message);
    }

    public SimulationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
