package com.petrov;

import java.util.List;

public interface ListForStudy<E> {

    void add(E o);

    boolean remove(E o);

    int size();

    boolean isEmpty();

    boolean contains(E o);

}
