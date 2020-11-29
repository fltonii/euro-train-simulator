package com.company;

public class Railway {
    private StationNode startStation;
    private StationNode endStation;
    private int numElements = 0;
    private int stationCount = 0;

    public Railway(int stations) {
        for (int i = 0; i < stations; i++) {
            insertStation();
        }
    }

    public void insertStation() {
        if (stationCount == 0) {
            this.startStation = this.endStation = new StationNode(1);
        } else {
            StationNode newStation = new StationNode(stationCount + 1);
            insert20kmBetween(this.endStation, newStation);
            insertTrainStopsBetween(this.endStation, newStation);
            this.endStation.setFinalStation(false);
            this.endStation = newStation;
            this.endStation.setFinalStation(true);
        }
        stationCount++;
        numElements++;
    }

    public String toString() {
        Node head = startStation;
        String returnStatement = "";
        for (int i = 0; i < numElements; i++) {
            if (head instanceof StationNode) {
                returnStatement += formatStationString((StationNode) head);
            } else {
                returnStatement += formatMilestoneString((MilestoneNode) head);
            }

            head = head.getNext();
        }
        return returnStatement;
    }

    public boolean isStretchBusy(StationNode start, StationNode end) {
        TrainDirection direction;
        int distance;
        if (start.getElement() < end.getElement()) {
            direction = TrainDirection.AtoB;
            distance = end.getElement() - start.getElement();
        } else {
            direction = TrainDirection.BtoA;
            distance = start.getElement() - end.getElement();
        }

        Node head = start;

        for (int i = 0; i < distance; i++) {
            if (head.getBusy()) {
                return true;
            }
            head = start.getNext(direction);
        }

        return false;
    }

    public void spawnTrains() {
        new Train(TrainDirection.AtoB, this.startStation);
        new Train(TrainDirection.BtoA, this.endStation);
    }

    private String formatStationString(StationNode station) {
        String returnStatement = "";
        returnStatement += "  [ ";
        if (station.getStopLeft() != null) {
            returnStatement += station.getStopLeft().getElement() + "  ";
        }
        if(station.getBusy()) {
            returnStatement += "(  \uD83D\uDE82  )";
        } else {
            returnStatement += "( " + station.getType() + "  " + station.getElement() + " )";
        }
        if (station.getStopRight() != null) {
            returnStatement += "  " + station.getStopRight().getElement();
        }
        return returnStatement += " ]  ";
    }

    private String formatMilestoneString(MilestoneNode milestone) {
        if(milestone.getBusy()) {
            return "[  \uD83D\uDE82  ]";
        }
        return "[ " + milestone.getType() + "  " + milestone.getElement() + " ]";
    }

    private void insertTrainStopsBetween(StationNode start, StationNode end) {
        StopNode startRightStop = new StopNode(start.getElement() + " Right Stop");
        start.setStopRight(startRightStop);
        startRightStop.setStation(start);

        StopNode endLeftStop = new StopNode(end.getElement() + " leftStop");
        end.setStopLeft(endLeftStop);
        endLeftStop.setStation(end);
    }

    private void insert20kmBetween(StationNode start, StationNode end) {
        Node head = start;
        for (int i = 0; i < 20; i++) {
            Node oldHead = head;
            head = new MilestoneNode(numElements + i);
            oldHead.setNext(head);
            head.setPrevious(head);
        }

        numElements += 20;
        head.setNext(end);
        end.setPrevious(head);
    }
}
