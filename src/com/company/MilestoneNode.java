package com.company;

public class MilestoneNode<E> extends Node<E> {
    public MilestoneNode(E e) {
        this.element = e;
        this.type = "Km";
        this.setBusy(true);
    }
}
