package com.company;

public class UnderflowException extends RuntimeException {
    public UnderflowException() {
        super("Underflow!");
    }
}
