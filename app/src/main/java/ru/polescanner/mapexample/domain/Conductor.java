package ru.polescanner.mapexample.domain;

import static org.apache.commons.lang3.Validate.notNull;
import static java.lang.Integer.max;

import java.util.Set;
import java.util.UUID;

public class Conductor extends Entity { //Провод
    private ConductorName alias; //Рабочее (временное) наименование
    private Voltage voltage;//Напряжение
    private boolean isBare; // Голый
    private ConductorType type; // Марка провода
    private Feature end1; // начало
    private Feature end2; // конец
    private Feeder feeder; //питающий фидер
    private Set<Wire> circuitWires; //Жилы цепи
    //ToDo check if 0 < length < 10000 -> to UnitConfig
    private int lengthM; // длина в м.

    protected Conductor(UUID conductorId,
                        int conductorVersion,
                        ConductorName conductorAlias,
                        Voltage conductorVoltage,
                        boolean isConductorBare,
                        ConductorType type,
                        Feature conductorEnd1,
                        Feature conductorEnd2,
                        Feeder conductorFeeder,
                        Set<Wire> conductorCircuitWires){
        super(conductorId, conductorVersion);
        this.alias = conductorAlias;
        this.voltage = conductorVoltage;
        this.isBare = isConductorBare;
        this.type = type;
        this.end1 = conductorEnd1;
        this.end2 = conductorEnd2;
        this.feeder = conductorFeeder;
        this.circuitWires = conductorCircuitWires;
    }

    public Conductor[] cutConductor (Feature joint){
        Conductor[] conductors = new Conductor[2];
        conductors[0] = this;
        if (joint.getType().equals(Feature.Type.CONNECTOR)){
            conductors[1] = new Conductor(UUID.randomUUID(), 0, this.alias,
                                          this.voltage, this.isBare, this.type, joint,
                                          this.end2, this.feeder, this.circuitWires);
            this.end2 = joint;
        }
        return conductors;
    }

    public boolean isFeatureIn(Feature f){
        return f == end1 || f == end2;
    }

    public boolean isConnected(Conductor other){
        return !this.equals(other) && (isFeatureIn(other.getEnd1()) || isFeatureIn(other.getEnd2()));
    }

    public int lengthM(){
        Float lengthBySpans = 0f;
        if (feeder != null) {
            for (Span s : feeder.getSpans()) {
                if (end1 != null && end1.getSpan() != null && end1.getSpan().equals(s))
                    lengthBySpans += distanceEndToPole(end1);
                else if (end2 != null && end2.getSpan() != null && end2.getSpan().equals(s))
                    lengthBySpans += distanceEndToPole(end2);
                else if (s.getPole1().getLayout().containsValue(this)
                        && s.getPole2().getLayout().containsValue(this))
                    lengthBySpans += s.lengthM();
            }
        }
        return max(lengthByFlyM(), lengthBySpans.intValue());
    }

    private float distanceEndToPole(Feature end){
        if (end.getSpan() == null) return 0f;
        if (end.getPole() != null) return end.getPoint().distanceTo(end.getPole().getPoint());
        if (end.getSpan().getPole1().getLayout().containsValue(this))
            return end.getPoint().distanceTo(end.getSpan().getPole1().getPoint());
        if (end.getSpan().getPole2().getLayout().containsValue(this))
            return end.getPoint().distanceTo(end.getSpan().getPole2().getPoint());
        return 0f;
    }

    public int lengthByFlyM(){
        Float lengthByFly = 0f;
        if (end1 != null && end2 != null && end1.getPoint() != null && end2.getPoint() != null)
            lengthByFly += end1.getPoint().distanceTo(end2.getPoint());
        return lengthByFly.intValue();
    }

    public Feeder getFeeder() {
        return feeder;
    }

    public boolean setFeeder(Feeder f){
        boolean result = false;
        notNull(f);
        if (f.getConductors().contains(this)) {
            this.feeder = f;
            result = true;
        }
        return result;
    }

    public Feature getEnd1(){
        return end1;
    }
    public Feature getEnd2(){
        return end2;
    }

    public void changeType(ConductorType t){
        this.type = t;
    }

    // ToDO: insert method in UML
    public ConductorName getConductorName() {
        return this.alias;
    }



//INNER CLASSES
    //ToDo complete class
    protected static class ConductorName extends Name {
        private static final int CONDUCTORNAME_MIN_LENGTH = 2;
        private static final int CONDUCTORNAME_MAX_LENGTH = 20;
        private static final String CONDUCTORNAME_VALID_CHARS = "[A-Za-zА-Яа-я0-9_-]+";
        ConductorName(String name) {
            super(name, CONDUCTORNAME_MIN_LENGTH,
                  CONDUCTORNAME_MAX_LENGTH, CONDUCTORNAME_VALID_CHARS);
        }
    }
    public enum ConductorType {
        A35,
        ADSS,
        OPGW
    }
}
