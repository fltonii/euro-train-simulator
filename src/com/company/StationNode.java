package com.company;

public class StationNode extends Node<Integer> {
    private boolean isStartingStation = false;
    private boolean isFinalStation = false;
    private StopNode StopRight;
    private StopNode StopLeft;
    private int totalBoarded = 0;

    public StationNode(int e) {
        this.element = e;
        this.type = "Station";
    }


    public void cyclePassengers() {
        int passengersIn = (int) (Math.random() * 10);
        int passengersOut = (int) (Math.random() * 10);
        Train train = getTrain();

        if ((train.getPassengers() + passengersIn - passengersOut) > 50) {
            int removed = train.getPassengers() + passengersIn - passengersOut - 50;
            passengersIn = passengersIn - removed;
        }

        train.boardPassengers(passengersIn);
        train.unboardPassengers(passengersOut);
    }

    public void setStartingStation(boolean startingStation) {
        isStartingStation = startingStation;
    }

    public boolean isStartingStation() {
        return isStartingStation;
    }

    public void setFinalStation(boolean startingStation) {
        isFinalStation = startingStation;
    }

    public boolean isFinalStation() {
        return isFinalStation;
    }

    public void setStopLeft(StopNode stopLeft) {
        StopLeft = stopLeft;
    }

    public void setStopRight(StopNode stopRight) {
        StopRight = stopRight;
    }

    public StopNode getStopLeft() {
        return StopLeft;
    }

    public StopNode getStopRight() {
        return StopRight;
    }
}
