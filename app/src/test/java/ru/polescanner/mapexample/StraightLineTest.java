package ru.polescanner.mapexample;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.android.gms.maps.model.LatLng;

import org.junit.Test;

import ru.polescanner.mapexample.service.ParallelLinesProvider;
import ru.polescanner.mapexample.service.ParallelLinesProvider.GeoPoint;


/**
 * Created by RykVS on 16.06.2022.
 */
public class StraightLineTest {

    @Test
    public void getIntersect_test_ForShortSpansAndTypeXIntersection() {

        // Arrange
        // Creating 4 points. Spans r1r2 and t1t2 nearly 5-10m, spans intersect each other like X
        LatLng r1 = new LatLng(57.71592, 39.77547);
        LatLng r2 = new LatLng(57.71589, 39.77559);
        LatLng t1 = new LatLng(57.71581, 39.77546);
        LatLng t2 = new LatLng(57.71594, 39.77556);
        // I created map in google maps and i know that r1r2 and t1t2 intersect each other in [57.7159, 39.77553] coordinates. Let it be LatLng intersect
        LatLng intersect = new LatLng(57.7159, 39.77553);

        // Act
        // Receive intersect point (sut) by ParallelProvider methods
        ParallelLinesProvider parallelLinesProvider = new ParallelLinesProvider();
        double t1t2Bearing = parallelLinesProvider.bearingR(t1, t2);
        double r1r2Bearing = parallelLinesProvider.bearingR(r1, r2);
        ParallelLinesProvider.StraightLine straightLineTower = new ParallelLinesProvider.StraightLine(t1, t1t2Bearing);
        ParallelLinesProvider.StraightLine straightLineRiver = new ParallelLinesProvider.StraightLine(r1, r1r2Bearing);
        LatLng sut = straightLineTower.getIntersect(straightLineRiver);

        // Assert
        // We receive by inner class Point distance between intersect and sut . If distance less than 1m then the test should be passed.
        GeoPoint intersectGeoPoint = new GeoPoint(intersect.latitude, intersect.longitude);
        GeoPoint sutGeoPoint = new GeoPoint(sut.latitude, sut.longitude);
        assertThat(intersectGeoPoint.distanceTo(sutGeoPoint)).isBetween(0.0, 1.0);
    }

    @Test
    public void getIntersect_test_ForLongSpansAndTypeXIntersection() {

        // Arrange
        // Creating 4 points. Spans r1r2 and t1t2 nearly 1000m, spans intersect each other like X
        LatLng r1 = new LatLng(57.68827, 39.83359);
        LatLng r2 = new LatLng(57.69455, 39.82192);
        LatLng t1 = new LatLng(57.6879, 39.82294);
        LatLng t2 = new LatLng(57.69431, 39.83614);
        // I created map in google maps and i know that r1r2 and t1t2 intersect each other in [57.69079, 39.8289] coordinates. Let it be LatLng intersect
        LatLng intersect = new LatLng(57.69079, 39.8289);

        // Act
        // Receive intersect point (sut) by ParallelProvider methods
        ParallelLinesProvider parallelLinesProvider = new ParallelLinesProvider();
        double t1t2Bearing = parallelLinesProvider.bearingR(t1, t2);
        double r1r2Bearing = parallelLinesProvider.bearingR(r1, r2);
        ParallelLinesProvider.StraightLine straightLineTower = new ParallelLinesProvider.StraightLine(t1, t1t2Bearing);
        ParallelLinesProvider.StraightLine straightLineRiver = new ParallelLinesProvider.StraightLine(r1, r1r2Bearing);
        LatLng sut = straightLineTower.getIntersect(straightLineRiver);

        // Assert
        // We receive by inner class Point distance between intersect and sut. If distance less than 10m then the test should be passed.
        GeoPoint intersectGeoPoint = new GeoPoint(intersect.latitude, intersect.longitude);
        GeoPoint sutGeoPoint = new GeoPoint(sut.latitude, sut.longitude);
        assertThat(intersectGeoPoint.distanceTo(sutGeoPoint)).isBetween(0.0, 10.0);
    }

    @Test
    public void getIntersect_test_ForShortSpansAndTypeVIntersection() {

        // Arrange
        // Creating 4 points. Spans r8r9 and r10r11 nearly 50m, spans intersect each other like V
        LatLng r8 = new LatLng(57.71564, 39.77666);
        LatLng r9 = new LatLng(57.7159, 39.77741);
        LatLng r10 = new LatLng(57.71542, 39.77734);
        LatLng r11 = new LatLng(57.71582, 39.77757);
        // I created map in google maps and i know that r8r9 and r10r11 intersect each other in [57.71599, 39.77767] coordinates. Let it be LatLng intersect
        LatLng r12 = new LatLng(57.71599, 39.77767);

        // Act
        // Receive intersect point (sut) by ParallelProvider methods
        ParallelLinesProvider parallelLinesProvider = new ParallelLinesProvider();
        double r8r9Bearing = parallelLinesProvider.bearingR(r8, r9);
        double r10r11Bearing = parallelLinesProvider.bearingR(r10, r11);
        ParallelLinesProvider.StraightLine straightLineR8R9 = new ParallelLinesProvider.StraightLine(r8, r8r9Bearing);
        ParallelLinesProvider.StraightLine straightLineR10R11 = new ParallelLinesProvider.StraightLine(r10, r10r11Bearing);
        LatLng sut = straightLineR8R9.getIntersect(straightLineR10R11);

        // Assert
        // We receive by inner class Point distance between intersect and sut. If distance less than 1m then the test should be passed.
        GeoPoint intersectGeoPoint = new GeoPoint(r12.latitude, r12.longitude);
        GeoPoint sutGeoPoint = new GeoPoint(sut.latitude, sut.longitude);
        assertThat(intersectGeoPoint.distanceTo(sutGeoPoint)).isBetween(0.0, 1.0);
    }

    @Test
    public void getIntersect_test_36MZone_730kmSouthOfTheEquator_TypeXIntersection() {
        // Arrange
        // Creating 4 points. Spans e1e2 and e3e4 intersect each other like X
        LatLng e1 = new LatLng(-6.22603, 32.67078);
        LatLng e2 = new LatLng(-6.21782, 32.68437);
        LatLng e3 = new LatLng(-6.21591, 32.67088);
        LatLng e4 = new LatLng(-6.22649, 32.68455);
        // I created map in google maps and i know that e1e2 and e3e4 intersect each other in [-6.22157, 32.67813] coordinates. Let it be LatLng intersect
        LatLng intersect = new LatLng(-6.22157, 32.67813);

        // Act
        // Receive intersect point (sut) by ParallelProvider methods
        ParallelLinesProvider parallelLinesProvider = new ParallelLinesProvider();
        double e1e2Bearing = parallelLinesProvider.bearingR(e1, e2);
        double e3e4Bearing = parallelLinesProvider.bearingR(e3, e4);
        ParallelLinesProvider.StraightLine straightLine1 = new ParallelLinesProvider.StraightLine(e1, e1e2Bearing);
        ParallelLinesProvider.StraightLine straightLine2 = new ParallelLinesProvider.StraightLine(e3, e3e4Bearing);
        LatLng sut = straightLine1.getIntersect(straightLine2);

        // Assert
        // We receive by inner class Point distance between intersect and sut . If distance less than 1m then the test should be passed.
        GeoPoint intersectGeoPoint = new GeoPoint(intersect.latitude, intersect.longitude);
        GeoPoint sutGeoPoint = new GeoPoint(sut.latitude, sut.longitude);
        assertThat(intersectGeoPoint.distanceTo(sutGeoPoint)).isBetween(0.0, 1.0);
    }

    @Test
    public void getIntersect_test_36NZone_20kmNorthOfTheEquator_TypeXIntersection() {
        // Arrange
        // Creating 4 points. Spans e5e6 and e7e8 intersect each other like X
        LatLng e5 = new LatLng(0.18729, 32.47606);
        LatLng e6 = new LatLng(0.18712, 32.47628);
        LatLng e7 = new LatLng(0.18714, 32.47606);
        LatLng e8 = new LatLng(0.18724, 32.47642);
        // I created map in google maps and i know that e5e6 and e7e8 intersect each other in [0.18718, 32.4762] coordinates. Let it be LatLng intersect
        LatLng intersect = new LatLng(0.18718, 32.4762);

        // Act
        // Receive intersect point (sut) by ParallelProvider methods
        ParallelLinesProvider parallelLinesProvider = new ParallelLinesProvider();
        double e5e6Bearing = parallelLinesProvider.bearingR(e5, e6);
        double e7e8Bearing = parallelLinesProvider.bearingR(e7, e8);
        ParallelLinesProvider.StraightLine straightLine1 = new ParallelLinesProvider.StraightLine(e5, e5e6Bearing);
        ParallelLinesProvider.StraightLine straightLine2 = new ParallelLinesProvider.StraightLine(e7, e7e8Bearing);
        LatLng sut = straightLine1.getIntersect(straightLine2);

        // Assert
        // We receive by inner class Point distance between intersect and sut . If distance less than 1m then the test should be passed.
        GeoPoint intersectGeoPoint = new GeoPoint(intersect.latitude, intersect.longitude);
        GeoPoint sutGeoPoint = new GeoPoint(sut.latitude, sut.longitude);
        assertThat(intersectGeoPoint.distanceTo(sutGeoPoint)).isBetween(0.0, 1.0);
    }

    @Test
    public void getIntersect_test_OnEquator_With2DifferentZoneAndLetter_TypeXIntersection() {
        // Arrange
        // Creating 4 points. Spans e1e2 and e3e4 intersect each other like X
        LatLng e1 = new LatLng(-0.00015, 24.99977);
        LatLng e2 = new LatLng(0.00014, 24.99998);
        LatLng e3 = new LatLng(0.00007, 24.99964);
        LatLng e4 = new LatLng(-0.00012, 25);
        // I created map in google maps and i know that e5e6 and e7e8 intersect each other in [-0.00004, 24.99985] coordinates. Let it be LatLng intersect
        LatLng intersect = new LatLng(-0.00004, 24.99985);

        // Act
        // Receive intersect point (sut) by ParallelProvider methods
        ParallelLinesProvider parallelLinesProvider = new ParallelLinesProvider();
        double e1e2Bearing = parallelLinesProvider.bearingR(e1, e2);
        double e3e4Bearing = parallelLinesProvider.bearingR(e3, e4);
        ParallelLinesProvider.StraightLine straightLine1 = new ParallelLinesProvider.StraightLine(e1, e1e2Bearing);
        ParallelLinesProvider.StraightLine straightLine2 = new ParallelLinesProvider.StraightLine(e3, e3e4Bearing);
        LatLng sut = straightLine1.getIntersect(straightLine2);

        // Assert
        // We receive by inner class Point distance between intersect and sut . If distance less than 1m then the test should be passed.
        GeoPoint intersectGeoPoint = new GeoPoint(intersect.latitude, intersect.longitude);
        GeoPoint sutGeoPoint = new GeoPoint(sut.latitude, sut.longitude);
        assertThat(intersectGeoPoint.distanceTo(sutGeoPoint)).isBetween(0.0, 1.0);
    }

    @Test
    public void getIntersect_test_For4DifferentZoneAndLetter_TypeXIntersection() {
        // Arrange
        /**
         * we consider point with coordinates latitude = 48 and longitude = 12
         * this point is the boundary of 4 UTM zones:
         *      32U | 33U
         *      ---------
         *      32T | 33T
         * point z1 has 32U, point z2 has 33T, z3 has 32T, z4 has 33U zones
         * intersect point will have 32u zone
         */
        // Creating 4 points. Spans z1z2 and z3z4 intersect each other like X
        LatLng z1 = new LatLng(48.0003, 11.99946);
        LatLng z2 = new LatLng(47.99961, 12.00043);
        LatLng z3 = new LatLng(47.99981, 11.99909);
        LatLng z4 = new LatLng(48.00027, 12.00056);
        // I created map in google maps and i know that z1z2 and z3z4 intersect each other in [48.00006, 11.99981] coordinates. Let it be LatLng intersect
        LatLng intersect = new LatLng(48.00006, 11.99981);

        // Act
        // Receive intersect point (sut) by ParallelProvider methods
        ParallelLinesProvider parallelLinesProvider = new ParallelLinesProvider();
        double z1z2Bearing = parallelLinesProvider.bearingR(z1, z2);
        double z3z4Bearing = parallelLinesProvider.bearingR(z3, z4);
        ParallelLinesProvider.StraightLine straightLine1 = new ParallelLinesProvider.StraightLine(z1, z1z2Bearing);
        ParallelLinesProvider.StraightLine straightLine2 = new ParallelLinesProvider.StraightLine(z3, z3z4Bearing);
        LatLng sut = straightLine1.getIntersect(straightLine2);

        // Assert
        // We receive by inner class Point distance between intersect and sut . If distance less than 1m then the test should be passed.
        GeoPoint intersectGeoPoint = new GeoPoint(intersect.latitude, intersect.longitude);
        GeoPoint sutGeoPoint = new GeoPoint(sut.latitude, sut.longitude);
        assertThat(intersectGeoPoint.distanceTo(sutGeoPoint)).isBetween(0.0, 1.0);
    }

    @Test
    public void getIntersect_test_For4DifferentZoneAndLetter_TypeVIntersection() {
        // Arrange
        /**
         * we consider point with coordinates latitude = 48 and longitude = 12
         * this point is the boundary of 4 UTM zones:
         *      32U | 33U
         *      ---------
         *      32T | 33T
         * points z1 and z6 have 32U, point z3 and z5 have 32T zones
         * intersect point will have 32u zone
         */
        // Creating 4 points. Spans z1z6 and z3z5 intersect each other like X
        LatLng z1 = new LatLng(48.0003, 11.99946);
        LatLng z6 = new LatLng(48.0002, 11.99985);
        LatLng z3 = new LatLng(47.99981, 11.99909);
        LatLng z5 = new LatLng(47.99985, 11.99967);
        // I created map in google maps and i know that z1z6 and z3z5 intersect each other in [47.99997, 12.000866] coordinates. Let it be LatLng intersect
        LatLng intersect = new LatLng(47.999979, 12.00086);

        // Act
        // Receive intersect point (sut) by ParallelProvider methods
        ParallelLinesProvider parallelLinesProvider = new ParallelLinesProvider();
        double z1z6Bearing = parallelLinesProvider.bearingR(z1, z6);
        double z3z5Bearing = parallelLinesProvider.bearingR(z3, z5);
        ParallelLinesProvider.StraightLine straightLine1 = new ParallelLinesProvider.StraightLine(z1, z1z6Bearing);
        ParallelLinesProvider.StraightLine straightLine2 = new ParallelLinesProvider.StraightLine(z3, z3z5Bearing);
        LatLng sut = straightLine1.getIntersect(straightLine2);

        // Assert
        // We receive by inner class Point distance between intersect and sut . If distance less than 1m then the test should be passed.
        GeoPoint intersectGeoPoint = new GeoPoint(intersect.latitude, intersect.longitude);
        GeoPoint sutGeoPoint = new GeoPoint(sut.latitude, sut.longitude);
        assertThat(intersectGeoPoint.distanceTo(sutGeoPoint)).isBetween(0.0, 1.0);
    }

    @Test
    public void getIntersect_test_ForTheSamePointsZoneButDifferentIntersectPointZone_TypeVIntersection() {
        // Arrange
        /**
         * points z7, z8, z9 and z10 have 32T zone, intersect point will have 33T zone
         */
        // Creating 4 points. Spans z7z8 and z9z10 intersect each other like X
        LatLng z7 = new LatLng(47.99941, 11.99891);
        LatLng z8 = new LatLng(47.99922, 11.99971);
        LatLng z9 = new LatLng(47.99907, 11.99871);
        LatLng z10 = new LatLng(47.99908, 11.99974);
        // I created map in google maps and i know that z7z8 and z9z10 intersect each other in [47.99908, 12.00027] coordinates. Let it be LatLng intersect
        LatLng intersect = new LatLng(47.999044, 12.000274);

        // Act
        // Receive intersect point (sut) by ParallelProvider methods
        ParallelLinesProvider parallelLinesProvider = new ParallelLinesProvider();
        double z7z8Bearing = parallelLinesProvider.bearingR(z7, z8);
        double z9z10Bearing = parallelLinesProvider.bearingR(z9, z10);
        ParallelLinesProvider.StraightLine straightLine1 = new ParallelLinesProvider.StraightLine(z7, z7z8Bearing);
        ParallelLinesProvider.StraightLine straightLine2 = new ParallelLinesProvider.StraightLine(z9, z9z10Bearing);
        LatLng sut = straightLine1.getIntersect(straightLine2);

        // Assert
        // We receive by inner class Point distance between intersect and sut . If distance less than 1m then the test should be passed.
        GeoPoint intersectGeoPoint = new GeoPoint(intersect.latitude, intersect.longitude);
        GeoPoint sutGeoPoint = new GeoPoint(sut.latitude, sut.longitude);
        assertThat(intersectGeoPoint.distanceTo(sutGeoPoint)).isBetween(0.0, 1.0);

    }

    @Test
    public void nextPoint_test_forEastSouthBearingCaseAndPositiveStep() {

        // Arrange
        // Creating 2 points and bearing between them
        LatLng r1 = new LatLng(57.71592, 39.77547);
        LatLng r3 = new LatLng(57.71567, 39.77578);
        ParallelLinesProvider parallelLinesProvider = new ParallelLinesProvider();
        double bearingR1R3R = parallelLinesProvider.bearingR(r1, r3);
        // I created map in google maps and i know that next point from r1 have [57.71585, 39.77555] coordinates. Let it be LatLng nextPoint
        LatLng nextPoint = new LatLng(57.71585, 39.77555);

        // Act
        // Receive nextPoint (sut) by ParallelProvider method nextPoint()
        ParallelLinesProvider.StraightLine straightLine = new ParallelLinesProvider.StraightLine(r1, bearingR1R3R);
        double stepM = 9;
        LatLng sut = straightLine.nextPoint(stepM);

        // Assert
        // We receive by inner class Point distance between nextPoint and sut. If distance less than 1m then the test should be passed.
        GeoPoint nextGeoPointP = new GeoPoint(nextPoint.latitude, nextPoint.longitude);
        GeoPoint sutP = new GeoPoint(sut.latitude, sut.longitude);
        assertThat(nextGeoPointP.distanceTo(sutP)).isBetween(0.0, 1.0);
    }

    @Test
    public void nextPoint_test_forEastSouthBearingCaseAndNegativeStep() {

        // Arrange
        // Creating 2 points and bearing between them
        LatLng r1 = new LatLng(57.71592, 39.77547);
        LatLng r3 = new LatLng(57.71567, 39.77578);
        ParallelLinesProvider parallelLinesProvider = new ParallelLinesProvider();
        double bearingR1R3R = parallelLinesProvider.bearingR(r1, r3);
        // I created map in google maps and i know that next point from r1 have [57.71598, 39.77539] coordinates. Let it be LatLng nextPoint
        LatLng nextPoint = new LatLng(57.71598, 39.77539);

        // Act
        // Receive nextPoint (sut) by ParallelProvider method nextPoint()
        ParallelLinesProvider.StraightLine straightLine = new ParallelLinesProvider.StraightLine(r1, bearingR1R3R);
        double stepM = -9;
        LatLng sut = straightLine.nextPoint(stepM);

        // Assert
        // We receive by inner class Point distance between nextPoint and sut. If distance less than 1m then the test should be passed.
        GeoPoint nextGeoPointP = new GeoPoint(nextPoint.latitude, nextPoint.longitude);
        GeoPoint sutP = new GeoPoint(sut.latitude, sut.longitude);
        assertThat(nextGeoPointP.distanceTo(sutP)).isBetween(0.0, 1.0);
    }

    @Test
    public void nextPoint_test_forEastNorthBearingCaseAndPositiveStep() {

        // Arrange
        // Creating 2 points and bearing between them
        LatLng r1 = new LatLng(57.71592, 39.77547);
        LatLng r4 = new LatLng(57.71616, 39.77572);
        ParallelLinesProvider parallelLinesProvider = new ParallelLinesProvider();
        double bearingR1R4R = parallelLinesProvider.bearingR(r1, r4);
        // I created map in google maps and i know that next point from r1 have [57.71598, 39.77553] coordinates. Let it be LatLng nextPoint
        LatLng nextPoint = new LatLng(57.71598, 39.77553);

        // Act
        // Receive nextPoint (sut) by ParallelProvider method nextPoint()
        ParallelLinesProvider.StraightLine straightLine = new ParallelLinesProvider.StraightLine(r1, bearingR1R4R);
        double stepM = 8;
        LatLng sut = straightLine.nextPoint(stepM);

        // Assert
        // We receive by inner class Point distance between nextPoint and sut. If distance less than 1m then the test should be passed.
        GeoPoint nextGeoPointP = new GeoPoint(nextPoint.latitude, nextPoint.longitude);
        GeoPoint sutP = new GeoPoint(sut.latitude, sut.longitude);
        assertThat(nextGeoPointP.distanceTo(sutP)).isBetween(0.0, 1.0);
    }

    @Test
    public void nextPoint_test_forEastNorthBearingCaseAndNegativeStep() {

        // Arrange
        // Creating 2 points and bearing between them
        LatLng r1 = new LatLng(57.71592, 39.77547);
        LatLng r4 = new LatLng(57.71616, 39.77572);
        ParallelLinesProvider parallelLinesProvider = new ParallelLinesProvider();
        double bearingR1R4R = parallelLinesProvider.bearingR(r1, r4);
        // I created map in google maps and i know that next point from r1 have [57.71586, 39.77541] coordinates. Let it be LatLng nextPoint
        LatLng nextPoint = new LatLng(57.71586, 39.77541);

        // Act
        // Receive nextPoint (sut) by ParallelProvider method nextPoint()
        ParallelLinesProvider.StraightLine straightLine = new ParallelLinesProvider.StraightLine(r1, bearingR1R4R);
        double stepM = -8;
        LatLng sut = straightLine.nextPoint(stepM);

        // Assert
        // We receive by inner class Point distance between nextPoint and sut. If distance less than 1m then the test should be passed.
        GeoPoint nextGeoPointP = new GeoPoint(nextPoint.latitude, nextPoint.longitude);
        GeoPoint sutP = new GeoPoint(sut.latitude, sut.longitude);
        assertThat(nextGeoPointP.distanceTo(sutP)).isBetween(0.0, 1.0);
    }

    @Test
    public void nextPoint_test_forWestNorthBearingCaseAndPositiveStep() {

        // Arrange
        // Creating 2 points and bearing between them
        LatLng r1 = new LatLng(57.71592, 39.77547);
        LatLng r5 = new LatLng(57.71598, 39.77496);
        ParallelLinesProvider parallelLinesProvider = new ParallelLinesProvider();
        double bearingR1R5R = parallelLinesProvider.bearingR(r1, r5);
        // I created map in google maps and i know that next point from r1 have [57.71592, 39.77543] coordinates. Let it be LatLng nextPoint
        LatLng nextPoint = new LatLng(57.71592, 39.77543);

        // Act
        // Receive nextPoint (sut) by ParallelProvider method nextPoint()
        ParallelLinesProvider.StraightLine straightLine = new ParallelLinesProvider.StraightLine(r1, bearingR1R5R);
        double stepM = 3;
        LatLng sut = straightLine.nextPoint(stepM);

        // Assert
        // We receive by inner class Point distance between nextPoint and sut. If distance less than 1m then the test should be passed.
        GeoPoint nextGeoPointP = new GeoPoint(nextPoint.latitude, nextPoint.longitude);
        GeoPoint sutP = new GeoPoint(sut.latitude, sut.longitude);
        assertThat(nextGeoPointP.distanceTo(sutP)).isBetween(0.0, 1.0);
    }

    @Test
    public void nextPoint_test_forWestNorthBearingCaseAndNegativeStep() {

        // Arrange
        // Creating 2 points and bearing between them
        LatLng r1 = new LatLng(57.71592, 39.77547);
        LatLng r5 = new LatLng(57.71598, 39.77496);
        ParallelLinesProvider parallelLinesProvider = new ParallelLinesProvider();
        double bearingR1R5R = parallelLinesProvider.bearingR(r1, r5);
        // I created map in google maps and i know that next point from r1 have [57.7159, 39.77564] coordinates. Let it be LatLng nextPoint
        LatLng nextPoint = new LatLng(57.7159, 39.77564);

        // Act
        // Receive nextPoint (sut) by ParallelProvider method nextPoint()
        ParallelLinesProvider.StraightLine straightLine = new ParallelLinesProvider.StraightLine(r1, bearingR1R5R);
        double stepM = -11;
        LatLng sut = straightLine.nextPoint(stepM);

        // Assert
        // We receive by inner class Point distance between nextPoint and sut. If distance less than 1m then the test should be passed.
        GeoPoint nextGeoPointP = new GeoPoint(nextPoint.latitude, nextPoint.longitude);
        GeoPoint sutP = new GeoPoint(sut.latitude, sut.longitude);
        assertThat(nextGeoPointP.distanceTo(sutP)).isBetween(0.0, 1.0);
    }

    @Test
    public void nextPoint_test_forWestSouthBearingCaseAndPositiveStep() {

        // Arrange
        // Creating 2 points and bearing between them
        LatLng r1 = new LatLng(57.71592, 39.77547);
        LatLng r6 = new LatLng(57.71575, 39.77492);
        ParallelLinesProvider parallelLinesProvider = new ParallelLinesProvider();
        double bearingR1R6R = parallelLinesProvider.bearingR(r1, r6);
        // I created map in google maps and i know that next point from r1 have [57.7159, 39.77541] coordinates. Let it be LatLng nextPoint
        LatLng nextPoint = new LatLng(57.7159, 39.77541);

        // Act
        // Receive nextPoint (sut) by ParallelProvider method nextPoint()
        ParallelLinesProvider.StraightLine straightLine = new ParallelLinesProvider.StraightLine(r1, bearingR1R6R);
        double stepM = 5;
        LatLng sut = straightLine.nextPoint(stepM);

        // Assert
        // We receive by inner class Point distance between nextPoint and sut. If distance less than 1m then the test should be passed.
        GeoPoint nextGeoPointP = new GeoPoint(nextPoint.latitude, nextPoint.longitude);
        GeoPoint sutP = new GeoPoint(sut.latitude, sut.longitude);
        assertThat(nextGeoPointP.distanceTo(sutP)).isBetween(0.0, 1.0);
    }

    @Test
    public void nextPoint_test_forWestSouthBearingCaseAndNegativeStep() {

        // Arrange
        // Creating 2 points and bearing between them
        LatLng r1 = new LatLng(57.71592, 39.77547);
        LatLng r6 = new LatLng(57.71575, 39.77492);
        ParallelLinesProvider parallelLinesProvider = new ParallelLinesProvider();
        double bearingR1R6R = parallelLinesProvider.bearingR(r1, r6);
        // I created map in google maps and i know that next point from r1 have [57.71596, 39.77561] coordinates. Let it be LatLng nextPoint
        LatLng nextPoint = new LatLng(57.71596, 39.77561);

        // Act
        // Receive nextPoint (sut) by ParallelProvider method nextPoint()
        ParallelLinesProvider.StraightLine straightLine = new ParallelLinesProvider.StraightLine(r1, bearingR1R6R);
        double stepM = -10;
        LatLng sut = straightLine.nextPoint(stepM);

        // Assert
        // We receive by inner class Point distance between nextPoint and sut. If distance less than 1m then the test should be passed.
        GeoPoint nextGeoPointP = new GeoPoint(nextPoint.latitude, nextPoint.longitude);
        GeoPoint sutP = new GeoPoint(sut.latitude, sut.longitude);
        assertThat(nextGeoPointP.distanceTo(sutP)).isBetween(0.0, 1.0);
    }

    @Test
    public void isOnStraightLine_test() {
        // Arrange
        LatLng r1 = new LatLng(57.71592, 39.77547);
        LatLng r7 = new LatLng(57.71624, 39.77712);
        ParallelLinesProvider parallelLinesProvider = new ParallelLinesProvider();
        double bearingR1R7 = parallelLinesProvider.bearingR(r1, r7);
        ParallelLinesProvider.StraightLine straightLine = new ParallelLinesProvider.StraightLine(r1, bearingR1R7);
        // p1 is located on straightLine
        LatLng p1 = new LatLng(57.71616, 39.77675);
        // p2 is located at a distance of 3 meters from straightLine
        LatLng p2 = new LatLng(57.71613, 39.77674);
        // p3 is located at a distance of 10 meters from straightLine
        LatLng p3 = new LatLng(57.71606, 39.77673);
        // p4 is located on straightLine on another side from point and bearing straightLine
        LatLng p4 = new LatLng(57.71584, 39.77507);
        // p5 is located at a distance of 2 meters from straightLine on another side from point and bearing straightLine
        LatLng p5 = new LatLng(57.71583, 39.77489);

        // Act
        boolean result1 = straightLine.isOnStraightLine(p1, 0.1);
        boolean result2 = straightLine.isOnStraightLine(p2, 0.1);
        boolean result3 = straightLine.isOnStraightLine(p3, 0.1);
        boolean result4 = straightLine.isOnStraightLine(p4, 0.6);
        boolean result5 = straightLine.isOnStraightLine(p5, 0.6);

        // Assert
        assertThat(result1).isTrue();
        assertThat(result2).isFalse();
        assertThat(result3).isFalse();
        assertThat(result4).isTrue();
        assertThat(result5).isFalse();
    }

    @Test
    public void getParallelLine_ShouldReturnLineWithTheSameBearing() {
        // Arrange
        LatLng t1 = new LatLng(57.6879, 39.82294);
        LatLng t2 = new LatLng(57.69431, 39.83614);
        ParallelLinesProvider parallelLinesProvider = new ParallelLinesProvider();
        double bearingT1T2 = parallelLinesProvider.bearingR(t1, t2);
        ParallelLinesProvider.StraightLine straightLine = new ParallelLinesProvider.StraightLine(t1, bearingT1T2);
        //Act
        ParallelLinesProvider.StraightLine parallelLine = straightLine.getParallelLine(10);
        double bearingPL = parallelLine.getBearingR();
        //Assert
        assertThat(bearingT1T2).isEqualTo(bearingPL);
    }

    @Test
    public void getParallelLine_ShouldReturnLinesWithPointsOnTheSameLines() {
        // Arrange
        LatLng t1 = new LatLng(57.6879, 39.82294);
        LatLng t2 = new LatLng(57.69431, 39.83614);
        ParallelLinesProvider parallelLinesProvider = new ParallelLinesProvider();
        double bearingT1T2 = parallelLinesProvider.bearingR(t1, t2);
        ParallelLinesProvider.StraightLine straightLine = new ParallelLinesProvider.StraightLine(t1, bearingT1T2);
        // Act
        ParallelLinesProvider.StraightLine parallelLine1 = straightLine.getParallelLine(-10);
        ParallelLinesProvider.StraightLine parallelLine2 = straightLine.getParallelLine(10);
        ParallelLinesProvider.StraightLine perpendicularLine = new ParallelLinesProvider.StraightLine(t1, bearingT1T2 + Math.PI/2);
        boolean pointOfParallelLine1IsOnPerpendicularLine = perpendicularLine.isOnStraightLine(parallelLine1.getPoint(), 0.1);
        boolean pointOfParallelLine2IsOnPerpendicularLine = perpendicularLine.isOnStraightLine(parallelLine2.getPoint(), 0.1);
        // Assert
        assertThat(pointOfParallelLine1IsOnPerpendicularLine).isTrue();
        assertThat(pointOfParallelLine2IsOnPerpendicularLine).isTrue();
    }

}