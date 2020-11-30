package com.company;

public class OutOfTrackException extends RuntimeException {
    public OutOfTrackException() {
        super("Out of track!");
    }
}
