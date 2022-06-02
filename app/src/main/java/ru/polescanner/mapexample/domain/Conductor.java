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

    //ToDo check if 0 < length < 10000 -> to UnitConfig
    private int lengthM; // длина в м.

    protected Conductor(UUID conductorId,
                        int conductorVersion,
                        ConductorName conductorAlias,
                        Voltage conductorVoltage,
                        boolean isConductorBare,
                        ConductorType type
                        ){
        super(conductorId, conductorVersion);
        this.alias = conductorAlias;
        this.voltage = conductorVoltage;
        this.isBare = isConductorBare;
        this.type = type;

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
