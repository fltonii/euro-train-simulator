package com.company;

public class Train {
    private Node locationNode;
    private TrainDirection direction;
    private boolean active = true;
    private int passengers = 0;
    private int pendingWait = 0;


    public Train(TrainDirection trainDirection, Node startLocation) {
        direction = trainDirection;
        locationNode = startLocation;
        locationNode.setTrain(this);
        passengers = (int) (Math.random() * 40);
    }

    public Node getLocationNode() {
        return locationNode;
    }

    public void setLocationNode(Node locationNode) {
        this.locationNode = locationNode;
    }

    public void moveAhead() throws OutOfTrackException {
        if (pendingWait > 0) {
            pendingWait--;
        }
        if (pendingWait == 0) {
            locationNode.setTrain(null);
            locationNode = locationNode.getNext(direction);
            if (locationNode == null) {
                throw new OutOfTrackException();
            }
            locationNode.setTrain(this);
            if (locationNode instanceof StationNode) {
                ((StationNode) locationNode).cyclePassengers();
            }

        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getPassengers() {
        return passengers;
    }

    public void boardPassengers(int amount) {
        System.out.println(amount + " passengers got in  into station " + locationNode.getElement() + ' ');
        pendingWait = amount;
        passengers += amount;
    }

    public void unboardPassengers(int amount) {
        passengers -= amount;
    }

    public void chooChoo() {
        System.out.print(" CHOO CHOO");
        // kkk
    }
}
