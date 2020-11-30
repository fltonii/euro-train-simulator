package com.company;

public class Train {
    private Node locationNode;
    private TrainDirection direction;

    public Train(TrainDirection trainDirection, Node startLocation) {
        direction = trainDirection;
        locationNode = startLocation;
        locationNode.setBusy(true);
    }

    public Node getLocationNode() {
        return locationNode;
    }

    public void setLocationNode(Node locationNode) {
        this.locationNode = locationNode;
    }

    public TrainDirection getDirection() {
        return direction;
    }

    public void setDirection(TrainDirection direction) {
        this.direction = direction;
    }

    public void moveAhead() throws OutOfTrackException {
        locationNode.setBusy(false);
        locationNode = locationNode.getNext(direction);
        if(locationNode == null) {
            throw new OutOfTrackException();
        }
        locationNode.setBusy(true);
    }

    public void chooChoo() {
        System.out.print(" CHOO CHOO");
        // kkk
    }
}
