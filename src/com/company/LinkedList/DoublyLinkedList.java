package com.company.LinkedList;

public class DoublyLinkedList<E> implements List<E> {
    private DNode<E> head;
    private DNode<E> tail;
    private int numElements;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.numElements = 0;
    }

    public int numElements() {
        return numElements;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public boolean isFull() {
        return false;
    }

    public void insertFirst(E element) throws NullPointerException {
        if (element == null)
            throw new NullPointerException();

        DNode<E> newNode = new DNode<>(element);
        if (isEmpty())
            head = tail = newNode;
        else {
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        }
        numElements++;
    }

    public void insertLast(E element) throws NullPointerException {
        if (element == null)
            throw new NullPointerException();

        DNode<E> newNode = new DNode<>(element);
        if (isEmpty())
            head = tail = newNode;
        else {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail = newNode;
        }
        numElements++;
    }

    public E removeFirst() throws UnderflowException {
        if (isEmpty())
            throw new UnderflowException();

        E element = head.getElement();

        if (head == tail)
            head = tail = null;
        else
            head = head.getNext();
        head.setPrevious(null);

        numElements--;
        return element;
    }

    public E removeLast() throws UnderflowException {
        if (isEmpty())
            throw new UnderflowException();

        E element = tail.getElement();

        if (head == tail)
            head = tail = null;
        else {
            DNode<E> current = tail.getPrevious();
            tail = current;
            current.setNext(null);
        }

        numElements--;
        return element;
    }

    public void insert(E element) throws NullPointerException, IndexOutOfBoundsException {
        if (this.tail != null) {
            DNode newItem = new DNode(element);
            newItem.setPrevious(this.tail);
            this.tail.setNext(newItem);
            this.tail = newItem;

        } else {
            this.head = this.tail = new DNode(element);
        }
        numElements++;
    }

    public void insert(E element, int pos) throws NullPointerException, IndexOutOfBoundsException {
        if (element == null)
            throw new NullPointerException();
        if (pos < 0 || pos > numElements)
            throw new IndexOutOfBoundsException();

        if (pos == 0)
            insertFirst(element);
        else if (pos == numElements)
            insertLast(element);
        else {
            DNode<E> current = head;
            for (int i = 0; i < pos - 1; i++)
                current = current.getNext();

            DNode<E> newNode = new DNode<>(element);
            newNode.setNext(current.getNext());
            newNode.setPrevious(current);
            current.setNext(newNode);
            numElements++;
        }
    }

    public E remove(int pos) throws IndexOutOfBoundsException {
        if (pos < 0 || pos >= numElements)
            throw new IndexOutOfBoundsException();

        if (pos == 0)
            return removeFirst();
        else if (pos == numElements - 1)
            return removeLast();
        else {
            DNode<E> prev = head;
            for (int i = 0; i < pos - 1; i++)
                prev = prev.getNext();

            E element = prev.getNext().getElement();

            DNode<E> nextNode = prev.getNext().getNext();
            prev.setNext(nextNode);
            nextNode.setPrevious(prev);

            numElements--;
            return element;
        }
    }

    public E get(int pos) throws IndexOutOfBoundsException {
        if (pos < 0 || pos >= numElements)
            throw new IndexOutOfBoundsException();

        DNode<E> current = head;
        for (int i = 0; i < pos; i++)
            current = current.getNext();

        return current.getElement();

    }

    public int search(E element) throws NullPointerException {
        if (element == null)
            throw new NullPointerException();

        DNode<E> current = head;
        int i = 0;
        while (current != null) {
            if (element.equals(current.getElement()))
                return i;
            i++;
            current = current.getNext();
        }

        return -1;

    }

    public String toString() {
        String s = "";

        DNode<E> current = head;
        while (current != null) {
            if (current.getPrevious() != null) {
                s += "| prev: " + current.getPrevious().getElement() + "   ";
            }
            s += "current: " + current.getElement().toString();
            if (current.getNext() != null) {
                s += "  next: " + current.getNext().getElement();
            }
            current = current.getNext();
        }
        return s;

    }
}
