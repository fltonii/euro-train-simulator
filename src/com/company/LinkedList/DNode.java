package com.company.LinkedList;

public class DNode<E> {
    protected E element;
    protected DNode<E> next;
    protected DNode<E> previous;

    public DNode(E e) {
        element = e;
    }

    public E getElement() {
        return element;
    }

    public DNode<E> getNext() {
        return next;
    }

    public DNode<E> getPrevious() {
        return previous;
    }

    public void setElement(E e) {
        element = e;
    }

    public void setNext(DNode<E> node) {
        next = node;
    }

    public void setPrevious(DNode<E> node) {
        previous = node;
    }
}
