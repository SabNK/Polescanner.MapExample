package ru.polescanner.mapexample.domain;

import static org.apache.commons.lang3.Validate.notNull;

import java.util.List;
import java.util.Objects;

//ToDo check implements Describable
public class PoleType { //Тип опоры

    private final PoleTypeCode code; //Марка опоры
    private final Material material; //Материал стойки
    private final boolean isTension; //Анкер
    private final ConductorCount conductorCount; //Количество проводов
    private final List<PolePosition> defaultLayout; //


    protected PoleType(PoleTypeCode code, Material material, boolean isTension,
                       ConductorCount count, List<PolePosition> defaultLayout
                       ) {
        this.code = code;
        this.material = material;
        this.isTension = isTension;
        this.conductorCount = count;
        this.defaultLayout = defaultLayout;

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoleType poleType = (PoleType) o;
        return isTension == poleType.isTension && code == poleType.code
                && material == poleType.material
                && Objects.equals(conductorCount, poleType.conductorCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, material, isTension);
    }

    public String code() {
        return code.toString();
    }

    public Material material() {
        return material;
    }

    public boolean isTension() {
        return isTension;
    }

    public int conductorCount() {
        return conductorCount.getValue();
    }

    public List<PolePosition> getLayout() {
        return defaultLayout;
    }



    //INNER CLASSES
    //ToDo make a subclass of name s a reference info.
    public static class PoleTypeCode extends Name{

        private static final int POLETYPECODE_MIN_LENGTH = 2;
        private static final int POLETYPECODE_MAX_LENGTH = 20;
        private static final String POLETYPECODE_VALID_CHARS = "[A-Za-zА-Яа-я0-9_-]+";

        protected PoleTypeCode(final String poleTypeCode) {
            super(poleTypeCode,
                  POLETYPECODE_MIN_LENGTH,
                  POLETYPECODE_MAX_LENGTH,
                  POLETYPECODE_VALID_CHARS);
        }
    }

    public enum Material {
        WOOD,
        STEEL,
        CONCRETE;

    }

    public enum PolePosition {
        A, B, C, D, E, F;
    }
}
