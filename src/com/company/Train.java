package com.company;

public class Train {
    private Node locationNode;
    private TrainDirection direction;

    public Train(TrainDirection trainDirection, Node startLocation) {
        direction = trainDirection;
        locationNode = startLocation;
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

    public void moveAhead() {
        if(direction == TrainDirection.AtoB) {
            locationNode = locationNode.getNext();
        } else {
            locationNode = locationNode.getPrevious();
        }
    }

    public void chooChoo() {
        System.out.print(" CHOO CHOO");
        // kkk
    }
}
