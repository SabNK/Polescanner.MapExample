package ru.polescanner.mapexample.domain;

import static org.apache.commons.lang3.Validate.inclusiveBetween;

import java.util.Objects;

public class ConductorCount { //Количество проводов
    private final int value; // значение

    protected ConductorCount(int number) {
        /*
         i (RykVS) changed range from [1; 20] to [0, 20], because pole register should create pole without conductor,
         and later after attach conductor conductorCount will be changed
         */
        inclusiveBetween(0, 20, number,
                "There should be at least one conductor, but no more than 20");
        value = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConductorCount that = (ConductorCount) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    //ToDo think another name for method
    public int getValue() {
        return value;
    }
}
