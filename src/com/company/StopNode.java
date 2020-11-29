package com.company;

public class StopNode<E> extends Node<E> {
     private StationNode station;
    public StopNode(E e) {
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
