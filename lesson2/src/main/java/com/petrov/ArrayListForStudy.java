package com.petrov;

import java.util.NoSuchElementException;

public class ArrayListForStudy<E> implements ListForStudy<E> {

    private int size = 0;
    protected E[] array = (E[]) new Object[5];

    public ArrayListForStudy(int size) {
        this.array = (E[]) new Object[size];
    }

    public ArrayListForStudy() {
    }

    @Override
    public void add(E value) {
        E[] tempArray;
        size++;
        if (size > array.length) {
            tempArray = this.array.clone();
            this.array = (E[]) new Object[tempArray.length + 5];
            for (int i = 0; i < tempArray.length; i++) {
                this.array[i] = tempArray[i];
            }
        }
        array[size-1] = value;
    }

    @Override
    public boolean remove(E value) {
        E[] tempArray;
        int count = 0;
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            if (this.array.length - size <= 0) {
                tempArray = this.array.clone();
                for (int i = 0; i < tempArray.length; i++) {
                    this.array[i] = tempArray[i];
                }
            }
            for (int i = 0; i < size; i++) {
                if (this.array[i] == value) {
                    this.array[i] = null;
                } else {
                    count++;
                }
            }
            tempArray = (E[]) new Object[count];
            for (int i = 0; i < size; i++) {
                for (int j  = 0; j < tempArray.length; j++){
                    if (array[i] != null){
                        tempArray[j] = array[i];
                    }
                }
            }
            array = tempArray;
            size--;

        }
        return true;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++){
            sb.append(this.array[i].toString()).append(" WTF))) ");
        }
        return sb.toString();
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
        for (E e : array) {
            if (e.equals(value)) {
                return true;
            }
        }
        return false;
    }

}
