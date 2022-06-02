package ru.polescanner.mapexample.domain;

import static org.apache.commons.lang3.Validate.notNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//ToDo implement Feature to stick to Span or Pole? (or only Span)
public class Feature extends Entity implements Describable{

    private Point point;
    private Pole pole;
    private Span span;
    private Type type;
    private Description avatar;
    private List<Description> descriptions;

    protected Feature(UUID featureId,
                      int featureVersion,
                      Point featurePoint,
                      Pole featurePole,
                      Span featureSpan,
                      Type featureType,
                      Description featurePicture,
                      List<Description> featureDescriptions) {
        super(featureId, featureVersion);
        this.point = featurePoint;
        this.pole = featurePole;
        this.span = featureSpan;
        this.type = featureType;
        this.avatar = featurePicture;
        this.descriptions = featureDescriptions;
    }

    public static Feature registerInSpan(double featureLon,
                                  double featureLat,
                                  double featureAcc,
                                  boolean isFeatureEasyAccessible,
                                  Span featureSpan,
                                  Type featureType,
                                  Description featurePicture){
        Point featurePoint = new Point((float)featureLon, (float)featureLat,
                                       (float)featureAcc, isFeatureEasyAccessible);
        Feature feature = new Feature(UUID.randomUUID(),
                                      0,
                                      featurePoint,
                                      null,
                                      notNull(featureSpan),
                                      notNull(featureType),
                                      notNull(featurePicture),
                                      new ArrayList<Description>());
        return feature;
    }

    public static Feature registerAtPole(Pole featurePole,
                                  Span featureSpan,
                                  Type featureType,
                                  Description featurePicture){
        notNull(featurePole);
        notNull(featurePole.getPoint());
        Feature f = new Feature(UUID.randomUUID(),
                                0,
                                featurePole.getPoint(),
                                featurePole,
                                featureSpan,
                                notNull(featureType),
                                notNull(featurePicture),
                                new ArrayList<Description>());
        return f;
    }

    //todo
    public boolean isLocationCorrect(Point point, Pole pole, Span span){
        boolean result = false;
        switch (this.type) {
            case CONNECTOR: {
                result = pole == null;
                break;
            }
            case JOINT:
                break;
        }
        return result;
    }

    @Override
    public Description getAvatar() {
        return avatar;
    }

    @Override
    public List<Description> getDescriptions() {
        return descriptions;
    }

    @Override
    public void addDescription(Description desc) {
        descriptions.add(desc);
    }

    public Type getType() {
        return type;
    }

    public Pole getPole() {
        return pole;
    }

    public Span getSpan() {
        return span;
    }

    public Point getPoint() {
        return point;
    }

    // INNER CLASSES
    public static enum Type {
        CONNECTOR,
        INSULATOR,
        JOINT,
        VIBRATION_DAMPER;
    }
}
