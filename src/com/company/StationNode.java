package com.company;

public class StationNode extends Node<Integer> {
    private StopNode BtoAStop;
    private StopNode AtoBStop;
    private int totalBoarded = 0;
    private int totalUnboarded = 0;

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

        if(passengersOut > train.getPassengers()) {
            passengersOut = train.getPassengers();
        }

        this.totalBoarded += passengersIn;
        this.totalUnboarded += passengersOut;

        train.boardPassengers(passengersIn);
        train.unboardPassengers(passengersOut);
    }

    public boolean isStretchBusyUntilNextStation(TrainDirection direction) {
        Node head = this.getNext(direction);
        if (head == null) {
            return false;
        }
        boolean found = false;
        while (!found) {
            if (head instanceof StationNode) {
                found = true;
                continue;
            }
            if (head.getBusy()) {
                return true;
            }
            head = head.getNext(direction);
        }

        return false;
    }

    public StopNode getNextStopNode(Node<Integer> mile) {
        if (mile.getElement() < getNext().getElement()) {
            return getBtoAStop();
        }
        return getAtoBStop();
    }

    public void setAtoBStop(StopNode atoBStop) {
        AtoBStop = atoBStop;
    }

    public void setBtoAStop(StopNode btoAStop) {
        BtoAStop = btoAStop;
    }

    public StopNode getAtoBStop() {
        return AtoBStop;
    }

    public StopNode getBtoAStop() {
        return BtoAStop;
    }

    public int getTotalBoarded() {
        return totalBoarded;
    }

    public int getTotalUnboarded() {
        return totalUnboarded;
    }
}
