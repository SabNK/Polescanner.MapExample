package ru.polescanner.mapexample.service;

import android.graphics.Color;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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

    public ParallelLinesProvider() {}

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
//            get p1LL, p2LL
            LatLng p1LL = new LatLng(this.point.latitude, this.point.longitude);
            LatLng p2LL = new LatLng(other.point.latitude, other.point.longitude);
//            get p1Utm, p2Utm
            DegToUtm p1Utm = new DegToUtm(p1LL.latitude, p1LL.longitude);
            DegToUtm p2Utm = new DegToUtm(p2LL.latitude, p2LL.longitude);
//            int counter
            int counter = 0;

//            while(p1Utm and p2Utm haven't the same zone and letter) {
            while (p1Utm.getZone() != p2Utm.getZone() || p1Utm.getLetter() != p2Utm.getLetter()) {
//                change (update) coordinates of p1LL, p2LL
                p1LL = new LatLng(p1LL.latitude + 0.01, p1LL.longitude + 0.01);
                p2LL = new LatLng(p2LL.latitude + 0.01, p2LL.longitude + 0.01);
//                update p1Utm, p2Utm from updated p1LL, p2LL
                p1Utm = new DegToUtm(p1LL.latitude, p1LL.longitude);
                p2Utm = new DegToUtm(p2LL.latitude, p2LL.longitude);
//                counter++
                counter ++;
            }
//            calculate x1, y1, x2, y2 of p1Utm, p2Utm
            double x1 = p1Utm.getEasting();
            double y1 = p1Utm.getNorthing();
            double x2 = p2Utm.getEasting();
            double y2 = p2Utm.getNorthing();
//            calculate x, y of intersect point
            double alpha = this.bearingR;
            double beta = other.bearingR;
            double y = (x2 - x1 + y1 * Math.tan(alpha) - y2 * Math.tan(beta)) / (Math.tan(alpha) - Math.tan(beta));
            double x = x1 + (y - y1) * Math.tan(alpha);
//            get ipLL
            DegToUtm ipUtm = new DegToUtm(x, y, p1Utm.getZone(), p1Utm.getLetter());
            UtmToDeg ipDeg = new UtmToDeg(ipUtm);
            LatLng ipLL = new LatLng(ipDeg.latitude, ipDeg.longitude);
//            get p1GP, p2GP, ipGP to calculate distance between p1, p2 and intersect point
            GeoPoint p1GP = new GeoPoint(p1LL.latitude, p1LL.longitude);
            GeoPoint p2GP = new GeoPoint(p2LL.latitude, p2LL.longitude);
            GeoPoint ipGP = new GeoPoint(ipLL.latitude, ipLL.longitude);
            double delta = p1GP.distanceTo(p2GP) * 5;
//            while intersect point doesn't close to p1 and p2 (more 5 * distance between p1 and p2) {
            while (ipGP.distanceTo(p1GP) > delta || ipGP.distanceTo(p2GP) > delta) {
//              change (update) coordinates of p1LL, p2LL
                p1LL = new LatLng(p1LL.latitude + 0.01, p1LL.longitude + 0.01);
                p2LL = new LatLng(p2LL.latitude + 0.01, p2LL.longitude + 0.01);
//              update p1Utm, p2Utm from updated p1LL, p2LL
                p1Utm = new DegToUtm(p1LL.latitude, p1LL.longitude);
                p2Utm = new DegToUtm(p2LL.latitude, p2LL.longitude);
//              counter++
                counter ++;
//              calculate x1, y1, x2, y2 of p1Utm, p2Utm
                x1 = p1Utm.getEasting();
                y1 = p1Utm.getNorthing();
                x2 = p2Utm.getEasting();
                y2 = p2Utm.getNorthing();
//              calculate x, y of intersect point
                y = (x2 - x1 + y1 * Math.tan(alpha) - y2 * Math.tan(beta)) / (Math.tan(alpha) - Math.tan(beta));
                x = x1 + (y - y1) * Math.tan(alpha);
//              get ipLL and ipGP
                ipUtm = new DegToUtm(x, y, p1Utm.getZone(), p1Utm.getLetter());
                ipDeg = new UtmToDeg(ipUtm);
                ipLL = new LatLng(ipDeg.latitude, ipDeg.longitude);
                ipGP = new GeoPoint(ipLL.latitude, ipLL.longitude);
            }
//          update back ipLL
            ipLL = new LatLng(ipLL.latitude - counter * 0.01, ipLL.longitude - counter * 0.01);
//          return ipLL
            return ipLL;
        }

        public LatLng nextPoint (double stepM) {
            // convert this.point from Degree to UTM coordinates, using DegToUtm class
            DegToUtm thisPointU = new DegToUtm(point.latitude, point.longitude);
            // nextPoint coordinates
            double nextPointXM = thisPointU.getEasting() + stepM * Math.sin(bearingR);
            double nextPointYM = thisPointU.getNorthing() + stepM * Math.cos(bearingR);
            // receive nextPoint by its coordinates
            DegToUtm nextPointU = new DegToUtm(nextPointXM, nextPointYM, thisPointU.getZone(), thisPointU.getLetter());
            UtmToDeg nextPointD = new UtmToDeg(nextPointU);

            return new LatLng(nextPointD.getLatitude(), nextPointD.getLongitude());
        }

        public boolean isOnStraightLine(LatLng p, double errorM){
            StraightLine perpendicular = new StraightLine(p, bearingR + Math.PI/2);
            LatLng intersectPointD = this.getIntersect(perpendicular);
            DegToUtm pU = new DegToUtm(p.latitude, p.longitude);
            DegToUtm intersectPointU = new DegToUtm(intersectPointD.latitude, intersectPointD.longitude);
            double distance = Math.sqrt(Math.pow((pU.getEasting() - intersectPointU.getEasting()), 2) + Math.pow(pU.getNorthing() - intersectPointU.getNorthing(), 2));
            if (distance < errorM)
                return true;
            return  false;
        }

        public StraightLine getParallelLine(double stepM){
            StraightLine perpendicular = new StraightLine(point, bearingR + Math.PI/2);
            LatLng newPoint = perpendicular.nextPoint(stepM);
            return new StraightLine(newPoint, bearingR);
        }

        public LatLng getPoint() {
            return this.point;
        }

        public double getBearingR() {
            return this.bearingR;
        }
    }

    /**
     * Inner class
     * Created by RykVS on 15.06.2022.
     * Based on:
     * https://stackoverflow.com/questions/176137/java-convert-lat-lon-to-utm
     */
    public static class DegToUtm {

        private double easting;
        private double northing;
        private int zone;
        private char letter;

        public DegToUtm(double easting, double northing, int zone, char letter) {
            this.easting = easting;
            this.northing = northing;
            this.zone = zone;
            this.letter = letter;
        }

        public DegToUtm(double Lat, double Lon) {

            zone = (int) Math.floor(Lon/6+31);
            if (Lat<-72)
                letter ='C';
            else if (Lat<-64)
                letter ='D';
            else if (Lat<-56)
                letter ='E';
            else if (Lat<-48)
                letter ='F';
            else if (Lat<-40)
                letter ='G';
            else if (Lat<-32)
                letter ='H';
            else if (Lat<-24)
                letter ='J';
            else if (Lat<-16)
                letter ='K';
            else if (Lat<-8)
                letter ='L';
            else if (Lat<0)
                letter ='M';
            else if (Lat<8)
                letter ='N';
            else if (Lat<16)
                letter ='P';
            else if (Lat<24)
                letter ='Q';
            else if (Lat<32)
                letter ='R';
            else if (Lat<40)
                letter ='S';
            else if (Lat<48)
                letter ='T';
            else if (Lat<56)
                letter ='U';
            else if (Lat<64)
                letter ='V';
            else if (Lat<72)
                letter ='W';
            else
                letter ='X';

            easting =0.5*Math.log((1+Math.cos(Lat*Math.PI/180)*Math.sin(Lon*Math.PI/180-(6* zone -183)*Math.PI/180))/(1-Math.cos(Lat*Math.PI/180)*Math.sin(Lon*Math.PI/180-(6* zone -183)*Math.PI/180)))*0.9996*6399593.62/Math.pow((1+Math.pow(0.0820944379, 2)*Math.pow(Math.cos(Lat*Math.PI/180), 2)), 0.5)*(1+ Math.pow(0.0820944379,2)/2*Math.pow((0.5*Math.log((1+Math.cos(Lat*Math.PI/180)*Math.sin(Lon*Math.PI/180-(6* zone -183)*Math.PI/180))/(1-Math.cos(Lat*Math.PI/180)*Math.sin(Lon*Math.PI/180-(6* zone -183)*Math.PI/180)))),2)*Math.pow(Math.cos(Lat*Math.PI/180),2)/3)+500000;
            easting =Math.round(easting *100)*0.01;
            northing = (Math.atan(Math.tan(Lat*Math.PI/180)/Math.cos((Lon*Math.PI/180-(6* zone -183)*Math.PI/180)))-Lat*Math.PI/180)*0.9996*6399593.625/Math.sqrt(1+0.006739496742*Math.pow(Math.cos(Lat*Math.PI/180),2))*(1+0.006739496742/2*Math.pow(0.5*Math.log((1+Math.cos(Lat*Math.PI/180)*Math.sin((Lon*Math.PI/180-(6* zone -183)*Math.PI/180)))/(1-Math.cos(Lat*Math.PI/180)*Math.sin((Lon*Math.PI/180-(6* zone -183)*Math.PI/180)))),2)*Math.pow(Math.cos(Lat*Math.PI/180),2))+0.9996*6399593.625*(Lat*Math.PI/180-0.005054622556*(Lat*Math.PI/180+Math.sin(2*Lat*Math.PI/180)/2)+4.258201531e-05*(3*(Lat*Math.PI/180+Math.sin(2*Lat*Math.PI/180)/2)+Math.sin(2*Lat*Math.PI/180)*Math.pow(Math.cos(Lat*Math.PI/180),2))/4-1.674057895e-07*(5*(3*(Lat*Math.PI/180+Math.sin(2*Lat*Math.PI/180)/2)+Math.sin(2*Lat*Math.PI/180)*Math.pow(Math.cos(Lat*Math.PI/180),2))/4+Math.sin(2*Lat*Math.PI/180)*Math.pow(Math.cos(Lat*Math.PI/180),2)*Math.pow(Math.cos(Lat*Math.PI/180),2))/3);

            if (letter <='M')
                northing = northing + 10000000;
            northing =Math.round(northing *100)*0.01;

        }

        public double getEasting() {
            return easting;
        }

        public double getNorthing() {
            return northing;
        }

        public int getZone() {
            return zone;
        }

        public char getLetter() {
            return letter;
        }

        public String toString() {
            return "UTM coordinates: " + String.valueOf(this.getZone()) + Character.toString(this.getLetter()) +
                    ", " + Double. toString(this.getEasting()) + ", " + Double. toString(this.getNorthing());
        }
    }

    /**
     * Inner class
     * Created by RykVS on 15.06.2022.
     * Based on:
     * https://stackoverflow.com/questions/176137/java-convert-lat-lon-to-utm
     */
    public static class UtmToDeg {

        private double latitude;
        private double longitude;

        public UtmToDeg(DegToUtm utm) {

            int zone= utm.getZone();
            char letter = utm.getLetter();
            double easting = utm.getEasting();
            double northing = utm.getNorthing();
            double hem;

            if (letter>'M')
                hem='N';
            else
                hem='S';
            double north;
            if (hem == 'S')
                north = northing - 10000000;
            else
                north = northing;

            latitude = (north/6366197.724/0.9996+(1+0.006739496742*Math.pow(Math.cos(north/6366197.724/0.9996),2)-0.006739496742*Math.sin(north/6366197.724/0.9996)*Math.cos(north/6366197.724/0.9996)*(Math.atan(Math.cos(Math.atan(( Math.exp((easting - 500000) / (0.9996*6399593.625/Math.sqrt((1+0.006739496742*Math.pow(Math.cos(north/6366197.724/0.9996),2))))*(1-0.006739496742*Math.pow((easting - 500000) / (0.9996*6399593.625/Math.sqrt((1+0.006739496742*Math.pow(Math.cos(north/6366197.724/0.9996),2)))),2)/2*Math.pow(Math.cos(north/6366197.724/0.9996),2)/3))-Math.exp(-(easting-500000)/(0.9996*6399593.625/Math.sqrt((1+0.006739496742*Math.pow(Math.cos(north/6366197.724/0.9996),2))))*( 1 -  0.006739496742*Math.pow((easting - 500000) / (0.9996*6399593.625/Math.sqrt((1+0.006739496742*Math.pow(Math.cos(north/6366197.724/0.9996),2)))),2)/2*Math.pow(Math.cos(north/6366197.724/0.9996),2)/3)))/2/Math.cos((north-0.9996*6399593.625*(north/6366197.724/0.9996-0.006739496742*3/4*(north/6366197.724/0.9996+Math.sin(2*north/6366197.724/0.9996)/2)+Math.pow(0.006739496742*3/4,2)*5/3*(3*(north/6366197.724/0.9996+Math.sin(2*north/6366197.724/0.9996 )/2)+Math.sin(2*north/6366197.724/0.9996)*Math.pow(Math.cos(north/6366197.724/0.9996),2))/4-Math.pow(0.006739496742*3/4,3)*35/27*(5*(3*(north/6366197.724/0.9996+Math.sin(2*north/6366197.724/0.9996)/2)+Math.sin(2*north/6366197.724/0.9996)*Math.pow(Math.cos(north/6366197.724/0.9996),2))/4+Math.sin(2*north/6366197.724/0.9996)*Math.pow(Math.cos(north/6366197.724/0.9996),2)*Math.pow(Math.cos(north/6366197.724/0.9996),2))/3))/(0.9996*6399593.625/Math.sqrt((1+0.006739496742*Math.pow(Math.cos(north/6366197.724/0.9996),2))))*(1-0.006739496742*Math.pow((easting-500000)/(0.9996*6399593.625/Math.sqrt((1+0.006739496742*Math.pow(Math.cos(north/6366197.724/0.9996),2)))),2)/2*Math.pow(Math.cos(north/6366197.724/0.9996),2))+north/6366197.724/0.9996)))*Math.tan((north-0.9996*6399593.625*(north/6366197.724/0.9996 - 0.006739496742*3/4*(north/6366197.724/0.9996+Math.sin(2*north/6366197.724/0.9996)/2)+Math.pow(0.006739496742*3/4,2)*5/3*(3*(north/6366197.724/0.9996+Math.sin(2*north/6366197.724/0.9996)/2)+Math.sin(2*north/6366197.724/0.9996 )*Math.pow(Math.cos(north/6366197.724/0.9996),2))/4-Math.pow(0.006739496742*3/4,3)*35/27*(5*(3*(north/6366197.724/0.9996+Math.sin(2*north/6366197.724/0.9996)/2)+Math.sin(2*north/6366197.724/0.9996)*Math.pow(Math.cos(north/6366197.724/0.9996),2))/4+Math.sin(2*north/6366197.724/0.9996)*Math.pow(Math.cos(north/6366197.724/0.9996),2)*Math.pow(Math.cos(north/6366197.724/0.9996),2))/3))/(0.9996*6399593.625/Math.sqrt((1+0.006739496742*Math.pow(Math.cos(north/6366197.724/0.9996),2))))*(1-0.006739496742*Math.pow((easting-500000)/(0.9996*6399593.625/Math.sqrt((1+0.006739496742*Math.pow(Math.cos(north/6366197.724/0.9996),2)))),2)/2*Math.pow(Math.cos(north/6366197.724/0.9996),2))+north/6366197.724/0.9996))-north/6366197.724/0.9996)*3/2)*(Math.atan(Math.cos(Math.atan((Math.exp((easting-500000)/(0.9996*6399593.625/Math.sqrt((1+0.006739496742*Math.pow(Math.cos(north/6366197.724/0.9996),2))))*(1-0.006739496742*Math.pow((easting-500000)/(0.9996*6399593.625/Math.sqrt((1+0.006739496742*Math.pow(Math.cos(north/6366197.724/0.9996),2)))),2)/2*Math.pow(Math.cos(north/6366197.724/0.9996),2)/3))-Math.exp(-(easting-500000)/(0.9996*6399593.625/Math.sqrt((1+0.006739496742*Math.pow(Math.cos(north/6366197.724/0.9996),2))))*(1-0.006739496742*Math.pow((easting-500000)/(0.9996*6399593.625/Math.sqrt((1+0.006739496742*Math.pow(Math.cos(north/6366197.724/0.9996),2)))),2)/2*Math.pow(Math.cos(north/6366197.724/0.9996),2)/3)))/2/Math.cos((north-0.9996*6399593.625*(north/6366197.724/0.9996-0.006739496742*3/4*(north/6366197.724/0.9996+Math.sin(2*north/6366197.724/0.9996)/2)+Math.pow(0.006739496742*3/4,2)*5/3*(3*(north/6366197.724/0.9996+Math.sin(2*north/6366197.724/0.9996)/2)+Math.sin(2*north/6366197.724/0.9996)*Math.pow(Math.cos(north/6366197.724/0.9996),2))/4-Math.pow(0.006739496742*3/4,3)*35/27*(5*(3*(north/6366197.724/0.9996+Math.sin(2*north/6366197.724/0.9996)/2)+Math.sin(2*north/6366197.724/0.9996)*Math.pow(Math.cos(north/6366197.724/0.9996),2))/4+Math.sin(2*north/6366197.724/0.9996)*Math.pow(Math.cos(north/6366197.724/0.9996),2)*Math.pow(Math.cos(north/6366197.724/0.9996),2))/3))/(0.9996*6399593.625/Math.sqrt((1+0.006739496742*Math.pow(Math.cos(north/6366197.724/0.9996),2))))*(1-0.006739496742*Math.pow((easting-500000)/(0.9996*6399593.625/Math.sqrt((1+0.006739496742*Math.pow(Math.cos(north/6366197.724/0.9996),2)))),2)/2*Math.pow(Math.cos(north/6366197.724/0.9996),2))+north/6366197.724/0.9996)))*Math.tan((north-0.9996*6399593.625*(north/6366197.724/0.9996-0.006739496742*3/4*(north/6366197.724/0.9996+Math.sin(2*north/6366197.724/0.9996)/2)+Math.pow(0.006739496742*3/4,2)*5/3*(3*(north/6366197.724/0.9996+Math.sin(2*north/6366197.724/0.9996)/2)+Math.sin(2*north/6366197.724/0.9996)*Math.pow(Math.cos(north/6366197.724/0.9996),2))/4-Math.pow(0.006739496742*3/4,3)*35/27*(5*(3*(north/6366197.724/0.9996+Math.sin(2*north/6366197.724/0.9996)/2)+Math.sin(2*north/6366197.724/0.9996)*Math.pow(Math.cos(north/6366197.724/0.9996),2))/4+Math.sin(2*north/6366197.724/0.9996)*Math.pow(Math.cos(north/6366197.724/0.9996),2)*Math.pow(Math.cos(north/6366197.724/0.9996),2))/3))/(0.9996*6399593.625/Math.sqrt((1+0.006739496742*Math.pow(Math.cos(north/6366197.724/0.9996),2))))*(1-0.006739496742*Math.pow((easting-500000)/(0.9996*6399593.625/Math.sqrt((1+0.006739496742*Math.pow(Math.cos(north/6366197.724/0.9996),2)))),2)/2*Math.pow(Math.cos(north/6366197.724/0.9996),2))+north/6366197.724/0.9996))-north/6366197.724/0.9996))*180/Math.PI;
            latitude=Math.round(latitude*10000000);
            latitude=latitude/10000000;
            longitude =Math.atan((Math.exp((easting-500000)/(0.9996*6399593.625/Math.sqrt((1+0.006739496742*Math.pow(Math.cos(north/6366197.724/0.9996),2))))*(1-0.006739496742*Math.pow((easting-500000)/(0.9996*6399593.625/Math.sqrt((1+0.006739496742*Math.pow(Math.cos(north/6366197.724/0.9996),2)))),2)/2*Math.pow(Math.cos(north/6366197.724/0.9996),2)/3))-Math.exp(-(easting-500000)/(0.9996*6399593.625/Math.sqrt((1+0.006739496742*Math.pow(Math.cos(north/6366197.724/0.9996),2))))*(1-0.006739496742*Math.pow((easting-500000)/(0.9996*6399593.625/Math.sqrt((1+0.006739496742*Math.pow(Math.cos(north/6366197.724/0.9996),2)))),2)/2*Math.pow(Math.cos(north/6366197.724/0.9996),2)/3)))/2/Math.cos((north-0.9996*6399593.625*( north/6366197.724/0.9996-0.006739496742*3/4*(north/6366197.724/0.9996+Math.sin(2*north/6366197.724/0.9996)/2)+Math.pow(0.006739496742*3/4,2)*5/3*(3*(north/6366197.724/0.9996+Math.sin(2*north/6366197.724/0.9996)/2)+Math.sin(2* north/6366197.724/0.9996)*Math.pow(Math.cos(north/6366197.724/0.9996),2))/4-Math.pow(0.006739496742*3/4,3)*35/27*(5*(3*(north/6366197.724/0.9996+Math.sin(2*north/6366197.724/0.9996)/2)+Math.sin(2*north/6366197.724/0.9996)*Math.pow(Math.cos(north/6366197.724/0.9996),2))/4+Math.sin(2*north/6366197.724/0.9996)*Math.pow(Math.cos(north/6366197.724/0.9996),2)*Math.pow(Math.cos(north/6366197.724/0.9996),2))/3)) / (0.9996*6399593.625/Math.sqrt((1+0.006739496742*Math.pow(Math.cos(north/6366197.724/0.9996),2))))*(1-0.006739496742*Math.pow((easting-500000)/(0.9996*6399593.625/Math.sqrt((1+0.006739496742*Math.pow(Math.cos(north/6366197.724/0.9996),2)))),2)/2*Math.pow(Math.cos(north/6366197.724/0.9996),2))+north/6366197.724/0.9996))*180/Math.PI+zone*6-183;
            longitude=Math.round(longitude*10000000);
            longitude=longitude/10000000;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public String toString() {
            return "WGS84: " + Double.toString(this.getLatitude()) + ", " + Double.toString(this.getLongitude());
        }
    }

    /**
     * Created by RykVS on 27.06.2022.
     * Based on:
     * http://people.apache.org/~rmuir/es-coverage/combined/org.elasticsearch.common.geo/GeoPoint.java.html
     */
    public static class GeoPoint {

        private double latitude;
        private double longitude;

        public GeoPoint(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public GeoPoint() {}

        /**
         * Create a new Geopointform a string. This String must either be a geohash
         * or a lat-lon tuple.
         *
         * @param value String to create the point from
         */
        public GeoPoint(String value) {
            this.setCoordinatesFromString(value);
        }

        public GeoPoint setCoordinates(double lat, double lon) {
            this.latitude = lat;
            this.longitude = lon;
            return this;
        }

        public GeoPoint setLatitude(double lat) {
            this.latitude = lat;
            return this;
        }

        public GeoPoint setLongitude(double lon) {
            this.longitude = lon;
            return this;
        }

        public GeoPoint setCoordinatesFromString(String value) {
            int comma = value.indexOf(',');
            if (comma != -1) {
                latitude = Double.parseDouble(value.substring(0, comma).trim());
                longitude = Double.parseDouble(value.substring(comma + 1).trim());
            } else {
                setCoordinatesFromGeoHash(value);
            }
            return this;
        }

        public GeoPoint setCoordinatesFromGeoHash(String hash) {
            GeoHashUtils.decode(hash, this);
            return this;
        }

        public final double getLatitude() {
            return this.latitude;
        }

        public final double getLongitude() {
            return this.longitude;
        }

        public final String getGeohash() {
            return GeoHashUtils.encode(latitude, longitude);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            GeoPoint geoPoint = (GeoPoint) o;

            if (Double.compare(geoPoint.latitude, latitude) != 0) return false;
            if (Double.compare(geoPoint.longitude, longitude) != 0) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result;
            long temp;
            temp = latitude != +0.0d ? Double.doubleToLongBits(latitude) : 0L;
            result = (int) (temp ^ (temp >>> 32));
            temp = longitude != +0.0d ? Double.doubleToLongBits(longitude) : 0L;
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            return result;
        }

        @Override
        public String toString() {
            return "[" + latitude + ", " + longitude + "]";
        }

        public static GeoPoint parseFromLatLon(String latLon) {
            GeoPoint point = new GeoPoint();
            point.setCoordinatesFromString(latLon);
            return point;
        }

        public double distanceTo(GeoPoint p){
            final int R = 6371; // Radius of the earth
            double lat1 = this.latitude;
            double lon1 = this.longitude;
            double lat2 = p.latitude;
            double lon2 = p.longitude;

            double latDistance = Math.toRadians(lat2 - lat1);
            double lonDistance = Math.toRadians(lon2 - lon1);
            double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                    + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                    * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            double dis = (double)(R * c * 1000);
            return dis > 0 ? dis : 0.0;
        }
    }

    /**
     * Created by RykVS on 29.06.2022.
     * Based on
     * http://people.apache.org/~rmuir/es-coverage/combined/org.elasticsearch.common.geo/GeoHashUtils.java.html
     * GeoHash precisions have next values:
     * 1) 5,009.4km x 4,992.6km 2) 1,252.3km x 624.1km 3) 156.5km x 156km 4) 39.1km x 19.5km 5) 4.9km x 4.9km 6) 1.2km x 609.4m 7) 152.9m x 152.4m 8) 38.2m x 19m 9) 4.8m x 4.8m 10) 1.2m x 59.5cm 11) 14.9cm x 14.9cm 12) 3.7cm x 1.9cm
     */

    /**
     * Utilities for encoding and decoding geohashes. Based on
     * http://en.wikipedia.org/wiki/Geohash.
     */

    public static class GeoHashUtils {

        private static final char[] BASE_32 = {'0', '1', '2', '3', '4', '5', '6',
                '7', '8', '9', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n',
                'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        public static final int PRECISION = 12;
        private static final int[] BITS = {16, 8, 4, 2, 1};

        private GeoHashUtils() {
        }

        public static String encode(double latitude, double longitude) {
            return encode(latitude, longitude, PRECISION);
        }

        /**
         * Encodes the given latitude and longitude into a geohash
         *
         * @param latitude  Latitude to encode
         * @param longitude Longitude to encode
         * @return Geohash encoding of the longitude and latitude
         */
        public static String encode(double latitude, double longitude, int precision) {
//        double[] latInterval = {-90.0, 90.0};
//        double[] lngInterval = {-180.0, 180.0};
            double latInterval0 = -90.0;
            double latInterval1 = 90.0;
            double lngInterval0 = -180.0;
            double lngInterval1 = 180.0;

            final StringBuilder geohash = new StringBuilder();
            boolean isEven = true;

            int bit = 0;
            int ch = 0;

            while (geohash.length() < precision) {
                double mid = 0.0;
                if (isEven) {
//                mid = (lngInterval[0] + lngInterval[1]) / 2D;
                    mid = (lngInterval0 + lngInterval1) / 2D;
                    if (longitude > mid) {
                        ch |= BITS[bit];
//                    lngInterval[0] = mid;
                        lngInterval0 = mid;
                    } else {
//                    lngInterval[1] = mid;
                        lngInterval1 = mid;
                    }
                } else {
//                mid = (latInterval[0] + latInterval[1]) / 2D;
                    mid = (latInterval0 + latInterval1) / 2D;
                    if (latitude > mid) {
                        ch |= BITS[bit];
//                    latInterval[0] = mid;
                        latInterval0 = mid;
                    } else {
//                    latInterval[1] = mid;
                        latInterval1 = mid;
                    }
                }

                isEven = !isEven;

                if (bit < 4) {
                    bit++;
                } else {
                    geohash.append(BASE_32[ch]);
                    bit = 0;
                    ch = 0;
                }
            }

            return geohash.toString();
        }

        private static final char encode(int x, int y) {
            return BASE_32[((x & 1) + ((y & 1) * 2) + ((x & 2) * 2) + ((y & 2) * 4) + ((x & 4) * 4)) % 32];
        }

        /**
         * Calculate all neighbors of a given geohash cell.
         *
         * @param geohash Geohash of the defined cell
         * @return geohashes of all neighbor cells
         */
        public static Collection<? extends CharSequence> neighbors(String geohash) {
            return addNeighbors(geohash, geohash.length(), new ArrayList<CharSequence>(8));
        }

        /**
         * Calculate the geohash of a neighbor of a geohash
         *
         * @param geohash the geohash of a cell
         * @param level   level of the geohash
         * @param dx      delta of the first grid coordinate (must be -1, 0 or +1)
         * @param dy      delta of the second grid coordinate (must be -1, 0 or +1)
         * @return geohash of the defined cell
         */
        private final static String neighbor(String geohash, int level, int dx, int dy) {
            int cell = decode(geohash.charAt(level - 1));

            // Decoding the Geohash bit pattern to determine grid coordinates
            int x0 = cell & 1;  // first bit of x
            int y0 = cell & 2;  // first bit of y
            int x1 = cell & 4;  // second bit of x
            int y1 = cell & 8;  // second bit of y
            int x2 = cell & 16; // third bit of x

            // combine the bitpattern to grid coordinates.
            // note that the semantics of x and y are swapping
            // on each level
            int x = x0 + (x1 / 2) + (x2 / 4);
            int y = (y0 / 2) + (y1 / 4);

            if (level == 1) {
                // Root cells at north (namely "bcfguvyz") or at
                // south (namely "0145hjnp") do not have neighbors
                // in north/south direction
                if ((dy < 0 && y == 0) || (dy > 0 && y == 3)) {
                    return null;
                } else {
                    return Character.toString(encode(x + dx, y + dy));
                }
            } else {
                // define grid coordinates for next level
                final int nx = ((level % 2) == 1) ? (x + dx) : (x + dy);
                final int ny = ((level % 2) == 1) ? (y + dy) : (y + dx);

                // if the defined neighbor has the same parent a the current cell
                // encode the cell directly. Otherwise find the cell next to this
                // cell recursively. Since encoding wraps around within a cell
                // it can be encoded here.
                // xLimit and YLimit must always be respectively 7 and 3
                // since x and y semantics are swapping on each level.
                if (nx >= 0 && nx <= 7 && ny >= 0 && ny <= 3) {
                    return geohash.substring(0, level - 1) + encode(nx, ny);
                } else {
                    String neighbor = neighbor(geohash, level - 1, dx, dy);
                    if(neighbor != null) {
                        return neighbor + encode(nx, ny);
                    } else {
                        return null;
                    }
                }
            }
        }

        /**
         * Add all geohashes of the cells next to a given geohash to a list.
         *
         * @param geohash   Geohash of a specified cell
         * @param neighbors list to add the neighbors to
         * @return the given list
         */
        public static final <E extends Collection<? super String>> E addNeighbors(String geohash, E neighbors) {
            return addNeighbors(geohash, geohash.length(), neighbors);
        }

        /**
         * Add all geohashes of the cells next to a given geohash to a list.
         *
         * @param geohash   Geohash of a specified cell
         * @param length    level of the given geohash
         * @param neighbors list to add the neighbors to
         * @return the given list
         */
        public static final <E extends Collection<? super String>> E addNeighbors(String geohash, int length, E neighbors) {
            String south = neighbor(geohash, length, 0, -1);
            String north = neighbor(geohash, length, 0, +1);
            if (north != null) {
                neighbors.add(neighbor(north, length, -1, 0));
                neighbors.add(north);
                neighbors.add(neighbor(north, length, +1, 0));
            }

            neighbors.add(neighbor(geohash, length, -1, 0));
            neighbors.add(neighbor(geohash, length, +1, 0));

            if (south != null) {
                neighbors.add(neighbor(south, length, -1, 0));
                neighbors.add(south);
                neighbors.add(neighbor(south, length, +1, 0));
            }

            return neighbors;
        }

        private static final int decode(char geo) {
            switch (geo) {
                case '0':
                    return 0;
                case '1':
                    return 1;
                case '2':
                    return 2;
                case '3':
                    return 3;
                case '4':
                    return 4;
                case '5':
                    return 5;
                case '6':
                    return 6;
                case '7':
                    return 7;
                case '8':
                    return 8;
                case '9':
                    return 9;
                case 'b':
                    return 10;
                case 'c':
                    return 11;
                case 'd':
                    return 12;
                case 'e':
                    return 13;
                case 'f':
                    return 14;
                case 'g':
                    return 15;
                case 'h':
                    return 16;
                case 'j':
                    return 17;
                case 'k':
                    return 18;
                case 'm':
                    return 19;
                case 'n':
                    return 20;
                case 'p':
                    return 21;
                case 'q':
                    return 22;
                case 'r':
                    return 23;
                case 's':
                    return 24;
                case 't':
                    return 25;
                case 'u':
                    return 26;
                case 'v':
                    return 27;
                case 'w':
                    return 28;
                case 'x':
                    return 29;
                case 'y':
                    return 30;
                case 'z':
                    return 31;
                default:
                    throw new IllegalArgumentException("the character '" + geo + "' is not a valid geohash character");
            }
        }

        /**
         * Decodes the given geohash
         *
         * @param geohash Geohash to decocde
         * @return {@link GeoPoint} at the center of cell, given by the geohash
         */
        public static GeoPoint decode(String geohash) {
            return decode(geohash, new GeoPoint());
        }

        /**
         * Decodes the given geohash into a latitude and longitude
         *
         * @param geohash Geohash to decocde
         * @return the given {@link GeoPoint} reseted to the center of
         *         cell, given by the geohash
         */
        public static GeoPoint decode(String geohash, GeoPoint ret) {
            double[] interval = decodeCell(geohash);
            return ret.setCoordinates((interval[0] + interval[1]) / 2D, (interval[2] + interval[3]) / 2D);
        }

        private static double[] decodeCell(String geohash) {
            double[] interval = {-90.0, 90.0, -180.0, 180.0};
            boolean isEven = true;

            for (int i = 0; i < geohash.length(); i++) {
                final int cd = decode(geohash.charAt(i));

                for (int mask : BITS) {
                    if (isEven) {
                        if ((cd & mask) != 0) {
                            interval[2] = (interval[2] + interval[3]) / 2D;
                        } else {
                            interval[3] = (interval[2] + interval[3]) / 2D;
                        }
                    } else {
                        if ((cd & mask) != 0) {
                            interval[0] = (interval[0] + interval[1]) / 2D;
                        } else {
                            interval[1] = (interval[0] + interval[1]) / 2D;
                        }
                    }
                    isEven = !isEven;
                }
            }
            return interval;
        }

        //========== long-based encodings for geohashes ========================================


        /**
         * Encodes latitude and longitude information into a single long with variable precision.
         * Up to 12 levels of precision are supported which should offer sub-metre resolution.
         *
         * @param latitude
         * @param longitude
         * @param precision The required precision between 1 and 12
         * @return A single long where 4 bits are used for holding the precision and the remaining
         * 60 bits are reserved for 5 bit cell identifiers giving up to 12 layers.
         */
        public static long encodeAsLong(double latitude, double longitude, int precision) {
            if((precision>12)||(precision<1))
            {
                throw new IllegalArgumentException("Illegal precision length of "+precision+
                        ". Long-based geohashes only support precisions between 1 and 12");
            }
            double latInterval0 = -90.0;
            double latInterval1 = 90.0;
            double lngInterval0 = -180.0;
            double lngInterval1 = 180.0;

            long geohash = 0l;
            boolean isEven = true;

            int bit = 0;
            int ch = 0;

            int geohashLength=0;
            while (geohashLength < precision) {
                double mid = 0.0;
                if (isEven) {
                    mid = (lngInterval0 + lngInterval1) / 2D;
                    if (longitude > mid) {
                        ch |= BITS[bit];
                        lngInterval0 = mid;
                    } else {
                        lngInterval1 = mid;
                    }
                } else {
                    mid = (latInterval0 + latInterval1) / 2D;
                    if (latitude > mid) {
                        ch |= BITS[bit];
                        latInterval0 = mid;
                    } else {
                        latInterval1 = mid;
                    }
                }

                isEven = !isEven;

                if (bit < 4) {
                    bit++;
                } else {
                    geohashLength++;
                    geohash|=ch;
                    if(geohashLength<precision){
                        geohash<<=5;
                    }
                    bit = 0;
                    ch = 0;
                }
            }
            geohash<<=4;
            geohash|=precision;
            return geohash;
        }

        /**
         * Formats a geohash held as a long as a more conventional
         * String-based geohash
         * @param geohashAsLong a geohash encoded as a long
         * @return A traditional base32-based String representation of a geohash
         */
        public static String toString(long geohashAsLong)
        {
            int precision = (int) (geohashAsLong&15);
            char[] chars = new char[precision];
            geohashAsLong >>= 4;
            for (int i = precision - 1; i >= 0 ; i--) {
                chars[i] =  BASE_32[(int) (geohashAsLong & 31)];
                geohashAsLong >>= 5;
            }
            return new String(chars);
        }



        public static GeoPoint decode(long geohash) {
            GeoPoint point = new GeoPoint();
            decode(geohash, point);
            return point;
        }

        /**
         * Decodes the given long-format geohash into a latitude and longitude
         *
         * @param geohash long format Geohash to decode
         * @param ret The Geopoint into which the latitude and longitude will be stored
         */
        public static void decode(long geohash, GeoPoint ret) {
            double[] interval = decodeCell(geohash);
            ret.setCoordinates((interval[0] + interval[1]) / 2D, (interval[2] + interval[3]) / 2D);

        }

        private static double[] decodeCell(long geohash) {
            double[] interval = {-90.0, 90.0, -180.0, 180.0};
            boolean isEven = true;

            int precision= (int) (geohash&15);
            geohash>>=4;
            int[]cds=new int[precision];
            for (int i = precision-1; i >=0 ; i--) {
                cds[i] = (int) (geohash&31);
                geohash>>=5;
            }

            for (int i = 0; i <cds.length ; i++) {
                final int cd = cds[i];
                for (int mask : BITS) {
                    if (isEven) {
                        if ((cd & mask) != 0) {
                            interval[2] = (interval[2] + interval[3]) / 2D;
                        } else {
                            interval[3] = (interval[2] + interval[3]) / 2D;
                        }
                    } else {
                        if ((cd & mask) != 0) {
                            interval[0] = (interval[0] + interval[1]) / 2D;
                        } else {
                            interval[1] = (interval[0] + interval[1]) / 2D;
                        }
                    }
                    isEven = !isEven;
                }
            }
            return interval;
        }
    }
}
