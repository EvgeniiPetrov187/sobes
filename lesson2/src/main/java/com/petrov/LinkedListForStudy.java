package com.petrov;

import java.util.Iterator;

public class LinkedListForStudy<E> implements ListForStudy<E>,Iterable<E> {

    protected Node<E> lastElement;
    protected Node<E> firstElement;
    protected int size;

    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value, null, lastElement);

        if (isEmpty()) {
            insertFirst(value);
            return;
        }

        lastElement.next = newNode;
        newNode.previous = lastElement;
        lastElement = newNode;
        size++;
    }

    public void insertFirst(E value) {
        firstElement = new Node<>(value, firstElement, null);
        size++;
        if (size == 1) {
            lastElement = firstElement;
        }
    }

    @Override
    public boolean remove(E value) {
        Node<E> current = firstElement;
        Node<E> previous = null;

        while (current != null) {
            if (current.item.equals(value)) {
                break;
            }
            previous = current;
            current = current.next;
        }

        if (current == null) {
            return false;
        } else if (current == firstElement) {
            removeFirst();
            return true;
        } else if (current == lastElement) {
            lastElement = previous;
            previous.next = null;
        } else {
            previous.next = current.next;
        }

        current.next = null;
        size--;

        return true;
    }

    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }

        Node<E> removedNode = firstElement;
        firstElement = removedNode.next;
        removedNode.next = null;
        size--;

        if (isEmpty()) {
            lastElement = null;
        }

        return removedNode.item;
    }

    public E removeLast() {
        if (isEmpty()) {
            return null;
        }

        Node<E> removedNode = lastElement;
        lastElement = lastElement.previous;
        lastElement.next = null;
        size--;

        return removedNode.item;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E value) {
        Node<E> current = firstElement;
        while (current != null) {
            if (current.item.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        Node<E> current = firstElement;
        while (current != null) {
            sb.append(current.item);
            if (current.next != null) {
                sb.append(" link ");
            }

            current = current.next;
        }

        return sb.toString();
    }

    class Node<E> {
        E item;
        Node<E> next;
        Node<E> previous;

        public Node(E item, Node<E> next) {
            this(item, next, null);
        }

        public Node(E item, Node<E> next, Node<E> previous) {
            this.item = item;
            this.next = next;
            this.previous = previous;
        }

        public Node() {
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    public class LinkedListIterator<E> implements Iterator<E> {

        @Override
        public boolean hasNext() {
            return firstElement != null;
        }

        @Override
        public E next() {
            Node<E> element = (Node<E>) firstElement;
            firstElement = firstElement.next;
            return element.item;
        }

        @Override
        public void remove() {
            firstElement = null;
        }
    }
}