package ru.polescanner.mapexample.service;

import com.google.android.gms.maps.model.LatLng;

import ru.polescanner.mapexample.domain.Pole;

public class MapUtils {

    public static LatLng getLatLng (Pole p){
        return new LatLng(p.getPoint().getCoordinates()[0], p.getPoint().getCoordinates()[1]);
    }

    public static double gapToTapM(float zoom){
        return 5.0;
    }
}
