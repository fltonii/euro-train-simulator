package com.company;

import com.company.LinkedList.DoublyLinkedList;

import java.io.*;

public class Railway {
    private StationNode startStation;
    private StationNode endStation;
    private int numElements = 0;
    private int stationCount = 0;
    private DoublyLinkedList<Train> trainList;

    public Railway(int stations) {
        trainList = new DoublyLinkedList<>();
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
            this.endStation = newStation;
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

    public void spawnTrains() {
        int  trainNumber = trainList.numElements();
        trainList.insert(new Train(TrainDirection.AtoB, this.startStation, TrainDirection.AtoB + " - " + trainNumber));
        trainList.insert(new Train(TrainDirection.BtoA, this.endStation, TrainDirection.BtoA + " - " + trainNumber));

        startStation.cyclePassengers();
        endStation.cyclePassengers();
    }

    public void moveTrains() {
        for (int i = 0; i < trainList.numElements(); i++) {
            Train train = trainList.get(i);
            try {
                if (train.isActive()) train.tick();
            } catch (OutOfTrackException e) {
                train.setActive(false);
            }
        }
    }

    public void reportBoardingLog() {
        File file = new File("./src/com/company/boardingLog.txt");
        try {
            FileWriter writer = new FileWriter(file);
            PrintWriter printer = new PrintWriter(writer);
            recursivelyFillLogFile(startStation, printer);
            printer.close();
        } catch (IOException error) {
            System.out.println(error);
            System.out.println("Erro ao salvar o arquivo de log");
        }

    }

    private void recursivelyFillLogFile(Node head, PrintWriter printer) {
        for (int i = 0; i < numElements; i++) {
            if (head instanceof StationNode) {
                printer.println("Estação de número " + head.getElement());
                printer.println("Embarques: " + ((StationNode) head).getTotalBoarded());
                printer.println("Desembarques: " + ((StationNode) head).getTotalUnboarded());
                printer.println("------------------");
                printer.println();
            }
            head = head.getNext();
        }
    }

    private String formatStationString(StationNode station) {
        String returnStatement = "";
        returnStatement += "  [ ";
        returnStatement += formatStopString(station.getBtoAStop(), TrainDirection.BtoA);
        if (station.getBusy()) {
            returnStatement += formatTrainString(station.getTrain());
        } else {
            returnStatement += "( " + station.getType() + "  " + station.getElement() + " )";
        }
        returnStatement += formatStopString(station.getAtoBStop(), TrainDirection.AtoB);
        return returnStatement += " ]  ";
    }

    private String formatMilestoneString(MilestoneNode milestone) {
        if (milestone.getBusy()) {
            return formatTrainString(milestone.getTrain());
        }
        return "[ " + milestone.getType() + "  " + milestone.getElement() + " ]";
    }

    private String formatStopString(StopNode stop, TrainDirection direction) {
        if (stop == null) return "";
        if (stop.getBusy()) {
            return formatTrainString(stop.getTrain());
        }
        return " { " + stop.getElement() + " " + direction + " }  ";
    }

    private String formatTrainString(Train train) {
        String returnStatement = "( ";
        if (train.getDirection() == TrainDirection.BtoA) {
            returnStatement += " \u2B05 ";
        }
        returnStatement += train.getPassengers() + " passageiros " + "\uD83D\uDE82";
        if (train.getPendingWait() != 0) {
            returnStatement += " esperando " + train.getPendingWait();
        }

        if (train.getDirection() == TrainDirection.AtoB) {
            returnStatement += " \u27A1 ";
        }

        return returnStatement + " )";
    }

    private void insertTrainStopsBetween(StationNode start, StationNode end) {
        StopNode startAtoBStopNode = new StopNode(start.getElement());
        startAtoBStopNode.setNext(start.getNext());
        startAtoBStopNode.setPrevious(start);
        start.setAtoBStop(startAtoBStopNode);
        startAtoBStopNode.setStation(start);

        StopNode endBtoAStopNode = new StopNode(end.getElement());
        endBtoAStopNode.setPrevious(end.getPrevious());
        endBtoAStopNode.setNext(end);
        end.setBtoAStop(endBtoAStopNode);
        endBtoAStopNode.setStation(end);
    }

    private void insert20kmBetween(StationNode start, StationNode end) {
        Node head = start;
        for (int i = 0; i < 20; i++) {
            Node oldHead = head;
            head = new MilestoneNode(numElements + i);
            oldHead.setNext(head);
            head.setPrevious(oldHead);
        }

        numElements += 20;
        head.setNext(end);
        end.setPrevious(head);
    }
}
