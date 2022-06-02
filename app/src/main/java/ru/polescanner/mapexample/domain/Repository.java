package ru.polescanner.mapexample.domain;

import java.util.List;

public interface Repository<E> {

    void add(E... e);

    E getById(String id);

    List<E> getAll();
}
