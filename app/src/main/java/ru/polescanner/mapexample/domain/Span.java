package ru.polescanner.mapexample.domain;

import static org.apache.commons.lang3.Validate.exclusiveBetween;
import static org.apache.commons.lang3.Validate.notNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Span extends Entity{
    private Pole pole1;
    private Pole pole2;
    private ConductorCount count;
    //ToDo: add Set of Conductors
    private Set<Conductor> conductors;

    protected Span(UUID spanId,
                   int spanVersion,
                   Pole pole1,
                   Pole pole2,
                   ConductorCount count,
                   Set<Conductor> conductors) {
        super(spanId, spanVersion);
        this.pole1 = pole1;
        this.pole2 = pole2;
        this.count = count;
        this.conductors = conductors;
    }
    public static Span registerSpan(Pole pole1, Pole pole2, int conductorCount){
        //ToDo Error messages
        notNull(pole1);
        notNull(pole2);
        notNull(pole1.getPoint());
        notNull(pole2.getPoint());
        //TODO analyze 100m as max span
        exclusiveBetween(0, 100, pole1.getPoint().distanceTo(pole2.getPoint()), "span length is incorrect");
        Span span = new Span(UUID.randomUUID(),
                             0,
                             pole1,
                             pole2,
                             new ConductorCount(conductorCount),
                             new HashSet<Conductor>());
        return span;
    }

    public boolean isPoleIn(Pole p){
        return p == pole1 || p == pole2;
    }

    public boolean isConnected(Span other){
        return !this.equals(other) && (isPoleIn(other.getPole1()) || isPoleIn(other.getPole2()));
    }

    public int lengthM(){
        Float length = pole1.getPoint().distanceTo(pole2.getPoint());
        return length.intValue();
    }

    public Pole getPole1(){
        return pole1;
    }

    public Pole getPole2(){
        return pole2;
    }

    public Pole getOtherPole(Pole p){
        if (isPoleIn(p))
            return p == pole1 ? pole2 : pole1;
        return null;
    }

    public int conductorCount() {
        return count.getValue();
    }
}
