package ru.polescanner.mapexample.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import ru.polescanner.mapexample.domain.Pole;
import ru.polescanner.mapexample.domain.Repository;

public class RepositoryPole implements Repository<Pole> {
    private Map<UUID, Pole> collection;

    public RepositoryPole(){
        this.collection = new HashMap<>();
    }

    @Override
    public void add(Pole... p) {
        for (Pole pole : p)
            addPole(pole);
    }

    @Override
    public Pole getById(String id) {
        if (collection.containsKey(UUID.fromString(id)))
            return collection.get(UUID.fromString(id));
        return null;
    }

    @Override
    public List<Pole> getAll() {
        return new ArrayList<Pole>(collection.values());
    }

    private void addPole(Pole p){
        collection.put(p.getId(), p);
    }

}
