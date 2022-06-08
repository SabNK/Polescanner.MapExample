package ru.polescanner.mapexample.domain;

import static org.apache.commons.lang3.Validate.notNull;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

//ToDo Check if Pole has to be serializable
public class Pole extends Entity{ //Опора
    private Polename name;
    private ConductorCount conductorCount; // количество проводов выходит из опоры (лучей, не транзитных!!!)
    private Point point;
    private PoleType type;
    private Map<PoleType.PolePosition, Conductor> layout;

    protected Pole(UUID poleId,
                   int poleVersion,
                   Polename name,
                   ConductorCount conductorsOnPole,
                   Point polePoint,
                   PoleType type,
                   Map<PoleType.PolePosition, Conductor> poleLayout) {
        super(poleId, poleVersion);
        this.name = name;
        this.conductorCount = conductorsOnPole;
        this.point = polePoint;
        this.type = type;
        this.layout = poleLayout;

    }

    public static Pole register(String poleName,
                                int conductorCount,
                                PoleType type,
                                double poleLat,
                                double poleLng,
                                double poleAcc,
                                boolean isPoleEasyAccessible
                                ){
        Point polePoint = new Point((float)poleLat, (float)poleLng,
                                    (float)poleAcc, isPoleEasyAccessible);

        Pole p = new Pole(UUID.randomUUID(),
                          0,
                          new Polename(poleName),
                          new ConductorCount(conductorCount),
                          polePoint,
                          notNull(type, "Pole type has to be at least undetailed"),
                          new HashMap<PoleType.PolePosition, Conductor>()
                          );
        return p;
    }

    public void attachConductor(PoleType.PolePosition position, Conductor conductor) {
        if (this.isPositionFreedom(position)) {
            this.getLayout().put(position, conductor);
            this.conductorCount = new ConductorCount(this.conductorCount.getValue() + 1);
        }
    }

    public void detachConductor(PoleType.PolePosition position, Conductor conductor) {
        if (!this.getLayout().keySet().contains(position))
            throw new IllegalArgumentException("Pole doesn't have " + position + " as position");
        this.getLayout().remove(position, conductor);
        this.conductorCount = new ConductorCount(this.conductorCount.getValue() - 1);
    }

    public boolean isPositionFreedom(PoleType.PolePosition position) {
        for (PoleType.PolePosition p: this.getLayout().keySet()) {
            if (p.equals(position))
                return false;
        }
        return true;
    }

    public boolean generateConductorAtPole(Conductor.ConductorName cName,
                                           Voltage v,
                                           boolean isConductorBare,
                                           PoleType.PolePosition pp) throws Exception {
        boolean result = false;
        if (!isLayoutFull(pp)){
            Conductor c = new Conductor(UUID.randomUUID(),
                                        0,
                                        cName,
                                        v,
                                        isConductorBare,
                                        null);
            layout.put(pp, c);
            result = true;
        };
        return result;
    }

    public boolean addConductorAtPole(Conductor c, PoleType.PolePosition pp) throws Exception {
        boolean result = false;
        if (!isLayoutFull(pp)){
            layout.put(pp, c);
            result = true;
        }
        return result;
    }

    //ToDo make PoleLayoutOverflowException
    private boolean isLayoutFull(PoleType.PolePosition pp) throws Exception {
        boolean result = false;
        if (layout.size() > conductorCount.getValue())
            throw new Exception("PoleLayout contains more conductors than counted at Pole");
        else if (layout.size() == conductorCount.getValue() || layout.keySet().contains(pp))
            result = true;
        return result;
    }

    public Span generateSpan(Pole other, int conductorCount) {
        return Span.registerSpan(this, other, conductorCount);
    }



    //ToDo may be to MediaPoviderImpl
    public static String convertImageToString(Bitmap image){
        String image_base64 = null;
        if (image != null) {
            ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayBitmapStream);
            byte[] b = byteArrayBitmapStream.toByteArray();
            image_base64 = Base64.encodeToString(b, Base64.DEFAULT);
        }
        return image_base64;
    }

    public Point getPoint() {
        return point;
    }

    public String getName() {
        return name.toString();
    }

    public Map<PoleType.PolePosition, Conductor> getLayout() {
        return layout;
    }

    public PoleType getPoleType() {
        return this.type;
    }

    public ConductorCount getConductorCount() {
        return this.conductorCount;
    }

    //INNER CLASSES
    protected static class Polename extends Name{
        private static final int POLENAME_MIN_LENGTH = 2;
        private static final int POLENAME_MAX_LENGTH = 20;
        private static final String POLENAME_VALID_CHARS = "[A-Za-zА-Яа-я0-9_-]+";

        public Polename (final String polename) {
            super(polename, POLENAME_MIN_LENGTH, POLENAME_MAX_LENGTH, POLENAME_VALID_CHARS);
        }
    }
}
