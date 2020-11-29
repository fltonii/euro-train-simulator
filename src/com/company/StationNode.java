package com.company;

public class StationNode extends Node<Integer> {
    private boolean isStartingStation = false;
    private boolean isFinalStation = false;
    private StopNode StopRight;
    private StopNode StopLeft;

    public StationNode(int e) {
        this.element = e;
        this.type = "Station";
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
