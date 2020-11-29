package com.company;

public class Node<E> {
    protected E element;
    protected Node<E> next;
    protected Node<E> previous;
    protected String type;
    private boolean isBusy = false;

    public Node() {}

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public boolean getBusy() {
        return isBusy;
    }
    public E getElement() {
        return this.element;
    }

    public String getType() {
        return type;
    }

    public Node<E> getNext(TrainDirection direction) {
        if(direction == TrainDirection.AtoB) {
            return this.getNext();
        } else {
            return this.getPrevious();
        }
    }

    public Node<E> getNext() {
        return this.next;
    }

    public Node<E> getPrevious() {
        return this.previous;
    }

    public void setElement(E e) {
        this.element = e;
    }

    public void setNext(Node<E> node) {
        this.next = node;
    }

    public void setPrevious(Node<E> node) {
        this.previous = node;
    }
}
