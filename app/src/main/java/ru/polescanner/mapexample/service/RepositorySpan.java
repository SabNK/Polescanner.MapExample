package ru.polescanner.mapexample.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


import ru.polescanner.mapexample.domain.Repository;
import ru.polescanner.mapexample.domain.Span;

public class RepositorySpan implements Repository<Span> {
    private Map<UUID, Span> collection;

    public RepositorySpan(){
        this.collection = new HashMap<>();
    }

    @Override
    public void add(Span... s) {
        for (Span span : s)
            addSpan(span);
    }

    @Override
    public Span getById(String id) {
        if (collection.containsKey(UUID.fromString(id)))
            return collection.get(UUID.fromString(id));
        return null;
    }

    @Override
    public List<Span> getAll() {
        return new ArrayList<Span>(collection.values());
    }

    private void addSpan(Span s){
        collection.put(s.getId(), s);
    }
}
