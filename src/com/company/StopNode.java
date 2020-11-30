package com.company;

public class StopNode extends Node<Integer> {
     private StationNode station;
    public StopNode(int e) {
        this.element = e;
        this.type = "Desvio";
    }

    public StationNode getStation() {
        return station;
    }

    public void setStation(StationNode station) {
        this.station = station;
    }
}
