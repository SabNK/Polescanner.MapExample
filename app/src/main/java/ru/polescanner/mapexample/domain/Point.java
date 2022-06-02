package ru.polescanner.mapexample.domain;

import static org.apache.commons.lang3.Validate.inclusiveBetween;


import java.util.Objects;

//TODO serializable
public class Point {
    //TODO float or double
    private final float latitude;
    private final float longitude;
    private final float accuracy;
    private boolean easyAccess;

    protected Point(float latitude, float longitude, float accuracy, boolean isPointEasyAccessible) {
        inclusiveBetween(-90., 90., latitude,
                         "Latitude must be between -90 : 90");
        inclusiveBetween(-180., 180., longitude,
                         "Longitude must be between -180 : 180");
        //ToDo include max acc to Constants Setup
        inclusiveBetween(0., 60., accuracy,
                         "Accuracy greater than 60m is not applicable");
        this.latitude = latitude;
        this.longitude = longitude;
        this.accuracy = accuracy;
        this.easyAccess = isPointEasyAccessible;
    }

    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null) return false;
        if(this.getClass() != o.getClass()) return false;
        Point p = (Point) o;
        return this.distanceTo(p) == 0 && (easyAccess == p.isEasyAccess());
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude, accuracy, easyAccess);
    }

    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     * @returns Distance in Meters
     */
    private float distance(Point p) {

        final int R = 6371; // Radius of the earth
        double lat1 = this.latitude;
        double lon1 = this.longitude;
        double lat2 = p.getCoordinates()[0];
        double lon2 = p.getCoordinates()[1];

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (float)(R * c * 1000);
    }
    public float distanceTo(Point p){
        float dis = this.distance(p) - this.accuracy - p.getCoordinates()[2];
        return dis > 0 ? dis : 0.0f;
    }

    public float[] getCoordinates(){
        return new float[]{this.latitude, this.longitude, this.accuracy};
    }

    public boolean isEasyAccess(){
        return this.easyAccess;
    }

    // ToDO: insert into UML diagram
    public void setEasyAccess(boolean isEasyAccess) {
        this.easyAccess = isEasyAccess;
    }

}
