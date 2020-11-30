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
        this.chooChoo();
    }

    public int getPendingWait() {
        return pendingWait;
    }

    public void tick() throws OutOfTrackException {
        if (pendingWait > 0) {
            pendingWait--;
        }
        if (pendingWait == 0) {
            if (shouldGetIntoStopNode()) {
                getIntoNextStopNode();
                return;
            }
            if (locationNode instanceof StopNode) {
                if (shouldGetOutOfStopNode()) {
                    moveAhead();
                }
            } else {
                moveAhead();
            }
        }
    }

    private boolean shouldGetIntoStopNode() {
        return !(locationNode instanceof StopNode) &&
                ((locationNode instanceof StationNode && ((StationNode) locationNode).isStretchBusyUntilNextStation(direction))
                || (locationNode.getNext(direction) instanceof StationNode && locationNode.getNext(direction).getBusy()));
    }

    private boolean shouldGetOutOfStopNode() {
        return !((StopNode) locationNode).getStation().isStretchBusyUntilNextStation(direction) && !locationNode.getNext(direction).getBusy();
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
        System.out.print(amount + " passengers got in in station " + locationNode.getElement() + ", ");
        pendingWait += Math.floor(amount / 2);
        passengers += amount;
    }

    public void unboardPassengers(int amount) {
        System.out.println(amount + " passengers got out in station " + locationNode.getElement());
        pendingWait += Math.floor(amount / 2);
        passengers -= amount;
    }

    public void chooChoo() {
        System.out.println("CHOO CHOO");
        // kkk
    }

    private void moveAhead() throws OutOfTrackException {
        locationNode.setTrain(null);
        Node newLocationNode = locationNode.getNext(direction);
        if (newLocationNode == null) {
            throw new OutOfTrackException();
        }
        newLocationNode.setTrain(this);
        if (newLocationNode instanceof StationNode) {
            ((StationNode) newLocationNode).cyclePassengers();
        }
        locationNode = newLocationNode;
    }

    private void getIntoNextStopNode() {
        locationNode.setTrain(null);
        Node newLocationNode = findClosestStation(locationNode).getNextStopNode(locationNode);
        newLocationNode.setTrain(this);
        locationNode = newLocationNode;
    }

    private StationNode findClosestStation(Node currentNode) {
        if (currentNode instanceof StationNode) {
            return (StationNode) currentNode;
        }
        return findClosestStation(locationNode.getNext(direction));
    }
}
