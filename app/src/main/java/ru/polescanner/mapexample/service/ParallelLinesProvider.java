package ru.polescanner.mapexample.service;

import android.graphics.Color;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParallelLinesProvider {
    private static final String TAG = "PerallelLinesProvider";
    private double gapM;
    private int[] colorSet;
    private int width;
    private List<PatternItem> strikePattern;
    StraightLine[] courses;
    StraightLine[] crosses;



    public ParallelLinesProvider(double gapM, double offsetM, int[] colorSet, int width,
                                 List<PatternItem> strikePattern, LatLng... points) {
        //ToDo Check points.length > 2
        this.gapM = gapM;
        this.colorSet = colorSet;
        this.width = width;
        this.strikePattern = strikePattern;

        //init courses and crosses
        int pQty = points.length;
        courses = new StraightLine[pQty-1];
        crosses = new StraightLine[pQty];
        double bearingR = bearingR(points[0], points[1]);
        crosses[0] = new StraightLine(points[0], bearingR + Math.PI /2);
        courses[0] = new StraightLine(crosses[0].nextPoint(offsetM), bearingR);
        bearingR = bearingR(points[pQty-1], points[pQty]);
        crosses[pQty-1] = new StraightLine(points[pQty-1], bearingR + Math.PI /2);
        for (int i = 1; i < pQty - 1; i++) {
            bearingR = bearingR(points[i], points[i+1]);
            double bisectorR = bisectorBearingR(points[i-1], points[i], points[i+1]);
            crosses[i] = new StraightLine(points[i], bisectorR);
            courses[i] = new StraightLine(crosses[i].nextPoint(inclineGapM(offsetM,
                                                                           courses[i-1],
                                                                           crosses[i])),
                                          bearingR);
        }
    }

    public ParallelLinesProvider(double gapM, double rectSpace, LatLng point1, LatLng point2) {
        this(gapM, 0.0,
             new int[]{Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.RED,
                     Color.YELLOW, Color.GRAY, Color.WHITE},
             6, null, point1, point2);
        this.crosses[1] = crosses[1].getParallelLine(-rectSpace);
    }

    public ParallelLinesProvider(double gapM, LatLng... points) {
        this(gapM, 0.0,
             new int[]{Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.RED,
                     Color.YELLOW, Color.GRAY, Color.WHITE},
             6, null, points);
    }
    //ToDo Реализовать через параллельные прямые и пересечение с cross
    public List<Controller.ConductorDraw> provide(int... count) {
        List<Controller.ConductorDraw> result = new ArrayList<>();
        Controller.ConductorDraw[] intermediate = new Controller.ConductorDraw[0];
        int cnt = count[0];
        for (int i = 0; i < this.crosses.length; i++) {
            if (i == 0 || i != this.crosses.length - 1 || count[i] != count[i - 1]) {
                result.addAll(Arrays.asList(intermediate));
                intermediate = new Controller.ConductorDraw[count[i]];
                for (int j = 0; j < count[i]; j++) {
                    intermediate[j] = new Controller.ConductorDraw(new ArrayList<>(), colorSet[j],
                                                                   width, strikePattern);
                }
            }
            double gap;
            if (i == 0 || i == this.crosses.length - 1)
                gap = gapM;
            else {
                gap = inclineGapM(gapM / 2, courses[i], crosses[i]);
                cnt = count[i];
            }
            double offsetRight = gap * (cnt - 1) / 2;
            for (int j = 0; j < cnt; j++) {
                intermediate[j].points.add(crosses[i].nextPoint(offsetRight - gap * j));
            }
        }
        result.addAll(Arrays.asList(intermediate));
        return result;
    }


    public double bearingR(LatLng start, LatLng end) {
        double sLatR = Math.toRadians(start.latitude);
        double eLatR = Math.toRadians(end.latitude);
        double lngDiff = Math.toRadians(end.longitude - start.longitude);
        double y = Math.sin(lngDiff) * Math.cos(eLatR);
        double x = Math.cos(sLatR) * Math.sin(eLatR)
                - Math.sin(sLatR) * Math.cos(eLatR) * Math.cos(lngDiff);
        return Math.atan2(y, x);
    }

    public double bisectorBearingR(LatLng start, LatLng middle, LatLng end) {
        double alpha = bearingR(start, middle);
        double beta = bearingR(middle, end);
        return (Math.PI - beta + alpha) / 2 + beta;
    }

    public double inclineGapM(double gapM, StraightLine course, StraightLine cross){
        return gapM / Math.sin(cross.bearingR - course.bearingR);
    }

    public static class StraightLine {
        LatLng point;
        double bearingR;

        public StraightLine(LatLng point, double bearingR) {
            this.point = point;
            this.bearingR = bearingR;
        }

        public LatLng getIntersect(StraightLine other){
            //ToDo Check if lines are parallel
            LatLng c; // intersect this line and other,
            LatLng a = this.point;
            LatLng b = other.point;
            double alpha = this.bearingR;
            double beta = other.bearingR;
            double abLngDelta = Math.toRadians(b.longitude - a.longitude);
            double abLatDelta = Math.toRadians(b.latitude - a.latitude);
            double bcLatDelta =    (Math.tan(alpha)*abLatDelta - abLngDelta) /
                                (Math.tan(beta) + Math.tan(alpha));
            double bcLngDelta = Math.tan(beta) * bcLatDelta;
            c = new LatLng(b.latitude + Math.toDegrees(bcLatDelta),
                           b.longitude + Math.toDegrees(bcLngDelta));
            return c;
        }

        public LatLng nextPoint (double stepM){
            //https://v-ipc.ru/guides/coord
            double degreeLngM = 111134.861111;
            double degreeLatM = Math.cos(Math.toRadians(point.latitude)) * 111321.377778;
            double latDeltaD = stepM * Math.sin(bearingR) / degreeLatM;
            double lngDeltaD = stepM * Math.cos(bearingR) / degreeLngM;
            return new LatLng(point.latitude - latDeltaD, point.longitude + lngDeltaD);
        }
        //ToDo Implement method for method Equals
        public boolean isOnStraightLine(LatLng point, double errorM){
            return false;
        }

        public StraightLine getParallelLine(double stepM){
            StraightLine perpendicular = new StraightLine(point, bearingR + Math.PI/2);
            LatLng newPoint = perpendicular.nextPoint(stepM);
            return new StraightLine(newPoint, bearingR);
        }
    }
}
