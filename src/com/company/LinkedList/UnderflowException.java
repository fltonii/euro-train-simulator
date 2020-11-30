package com.company.LinkedList;

public class UnderflowException extends RuntimeException {
    public UnderflowException() {
        super("Underflow!");
    }
}
