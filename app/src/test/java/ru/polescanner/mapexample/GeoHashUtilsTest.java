package ru.polescanner.mapexample;

import static org.assertj.core.api.Assertions.assertThat;

import ru.polescanner.mapexample.service.ParallelLinesProvider.GeoHashUtils;
import ru.polescanner.mapexample.service.ParallelLinesProvider.GeoPoint;

import org.assertj.core.data.Percentage;
import org.junit.Test;

import java.util.List;

/**
 * Created by RykVS on 29.06.2022.
 * Based on
 * https://www.researchgate.net/figure/Precision-of-binary-geohash-codes-with-various-code-lengths_tbl1_353185831
 */
public class GeoHashUtilsTest {

    @Test
    public void encode_test_ForPositiveLatitudeAndLongitudeAndPrecision12() {
        // Arrange
        GeoPoint geoPoint = new GeoPoint(57.716078, 39.776899); // Park of 1905year in Yaroslavl
        // Act
        String sut = GeoHashUtils.encode(geoPoint.getLatitude(), geoPoint.getLongitude(), 12);
        // Assert
        assertThat(sut).isEqualTo("ufk234ussbtx");
    }

    @Test
    public void encode_test_ForPositiveLatitudeAndLongitudeAndPrecision10() {
        // Arrange
        GeoPoint geoPoint = new GeoPoint(57.716078, 39.776899); // Park of 1905year in Yaroslavl
        // Act
        String sut = GeoHashUtils.encode(geoPoint.getLatitude(), geoPoint.getLongitude(), 10);
        // Assert
        assertThat(sut).isEqualTo("ufk234ussb");
    }

    @Test
    public void encode_test_ForNegativeLatitudeAndPositiveLongitudeAndPrecision12() {
        // Arrange
        GeoPoint geoPoint = new GeoPoint(-33.859436, 151.228963); // Sydney
        // Act
        String sut = GeoHashUtils.encode(geoPoint.getLatitude(), geoPoint.getLongitude(), 12);
        // Assert
        assertThat(sut).isEqualTo("r3gx3k1d6pxu");
    }

    @Test
    public void encode_test_ForNegativeLatitudeAndPositiveLongitudeAndPrecision9() {
        // Arrange
        GeoPoint geoPoint = new GeoPoint(-33.859436, 151.228963); // Sydney
        // Act
        String sut = GeoHashUtils.encode(geoPoint.getLatitude(), geoPoint.getLongitude(), 9);
        // Assert
        assertThat(sut).isEqualTo("r3gx3k1d6");
    }

    @Test
    public void encode_test_ForPositiveLatitudeAndNegativeLongitudeAndPrecision12() {
        // Arrange
        GeoPoint geoPoint = new GeoPoint(23.129527, -82.335214); // La Habana
        // Act
        String sut = GeoHashUtils.encode(geoPoint.getLatitude(), geoPoint.getLongitude(), 12);
        // Assert
        assertThat(sut).isEqualTo("dhj7w6t762qd");
    }

    @Test
    public void encode_test_ForPositiveLatitudeAndNegativeLongitudeAndPrecision11() {
        // Arrange
        GeoPoint geoPoint = new GeoPoint(23.129527, -82.335214); // La Habana
        // Act
        String sut = GeoHashUtils.encode(geoPoint.getLatitude(), geoPoint.getLongitude(), 11);
        // Assert
        assertThat(sut).isEqualTo("dhj7w6t762q");
    }

    @Test
    public void encode_test_ForNegativeLatitudeAndLongitudeAndPrecision12() {
        // Arrange
        GeoPoint geoPoint = new GeoPoint(-34.603635, -58.351839); // Buenos Aires
        // Act
        String sut = GeoHashUtils.encode(geoPoint.getLatitude(), geoPoint.getLongitude(), 12);
        // Assert
        assertThat(sut).isEqualTo("69ye0ht7pwgh");
    }

    @Test
    public void encode_test_ForNegativeLatitudeAndLongitudeAndPrecision10() {
        // Arrange
        GeoPoint geoPoint = new GeoPoint(-34.603635, -58.351839); // Buenos Aires
        // Act
        String sut = GeoHashUtils.encode(geoPoint.getLatitude(), geoPoint.getLongitude(), 10);
        // Assert
        assertThat(sut).isEqualTo("69ye0ht7pw");
    }

    @Test
    public void decode_test_ForPositiveLatitudeAndLongitudeAndPrecision12() {
        // Arrange
        String geoHash = "ufk234ussbtx";  // Park of 1905year in Yaroslavl
        // Act
        GeoPoint sut = GeoHashUtils.decode(geoHash);
        // Assert
        assertThat(sut.getLatitude()).isCloseTo(57.71607793, Percentage.withPercentage(0.0000001));
        assertThat(sut.getLongitude()).isCloseTo(39.77689901, Percentage.withPercentage(0.0000001));
    }

    @Test
    public void decode_test_ForPositiveLatitudeAndLongitudeAndPrecision10() {
        // Arrange
        String geoHash = "ufk234ussb";  // Park of 1905year in Yaroslavl
        // Act
        GeoPoint sut = GeoHashUtils.decode(geoHash);
        // Assert
        assertThat(sut.getLatitude()).isCloseTo(57.71607667, Percentage.withPercentage(0.0000001));
        assertThat(sut.getLongitude()).isCloseTo(39.77689683, Percentage.withPercentage(0.0000001));
    }

    @Test
    public void decode_test_ForNegativeLatitudeAndPositiveLongitudeAndPrecision12() {
        // Arrange
        String geoHash = "r3gx3k1d6pxu";  // Sydney
        // Act
        GeoPoint sut = GeoHashUtils.decode(geoHash);
        // Assert
        assertThat(sut.getLatitude()).isCloseTo(-33.85943606, Percentage.withPercentage(0.0000001));
        assertThat(sut.getLongitude()).isCloseTo(151.22896297, Percentage.withPercentage(0.0000001));
    }

    @Test
    public void decode_test_ForNegativeLatitudeAndPositiveLongitudeAndPrecision9() {
        // Arrange
        String geoHash = "r3gx3k1d6";  // Sydney
        // Act
        GeoPoint sut = GeoHashUtils.decode(geoHash);
        // Assert
        assertThat(sut.getLatitude()).isCloseTo(-33.85945559, Percentage.withPercentage(0.0000001));
        assertThat(sut.getLongitude()).isCloseTo(151.22897387, Percentage.withPercentage(0.0000001));
    }

    @Test
    public void decode_test_ForPositiveLatitudeAndNegativeLongitudeAndPrecision12() {
        // Arrange
        String geoHash = "dhj7w6t762qd";  // La Habana
        // Act
        GeoPoint sut = GeoHashUtils.decode(geoHash);
        // Assert
        assertThat(sut.getLatitude()).isCloseTo(23.12952694, Percentage.withPercentage(0.0000001));
        assertThat(sut.getLongitude()).isCloseTo(-82.33521407, Percentage.withPercentage(0.0000001));
    }

    @Test
    public void decode_test_ForPositiveLatitudeAndNegativeLongitudeAndPrecision11() {
        // Arrange
        String geoHash = "dhj7w6t762q";  // La Habana
        // Act
        GeoPoint sut = GeoHashUtils.decode(geoHash);
        // Assert
        assertThat(sut.getLatitude()).isCloseTo(23.12952720, Percentage.withPercentage(0.0000001));
        assertThat(sut.getLongitude()).isCloseTo(-82.33521424, Percentage.withPercentage(0.0000001));
    }

    @Test
    public void decode_test_ForNegativeLatitudeAndNegativeLongitudeAndPrecision12() {
        // Arrange
        String geoHash = "69ye0ht7pwgh";  // Buenos Aires
        // Act
        GeoPoint sut = GeoHashUtils.decode(geoHash);
        // Assert
        assertThat(sut.getLatitude()).isCloseTo(-34.60363506, Percentage.withPercentage(0.0000001));
        assertThat(sut.getLongitude()).isCloseTo(-58.35183917, Percentage.withPercentage(0.0000001));
    }

    @Test
    public void decode_test_ForNegativeLatitudeAndNegativeLongitudeAndPrecision10() {
        // Arrange
        String geoHash = "69ye0ht7pw";  // Buenos Aires
        // Act
        GeoPoint sut = GeoHashUtils.decode(geoHash);
        // Assert
        assertThat(sut.getLatitude()).isCloseTo(-34.60363716, Percentage.withPercentage(0.0000001));
        assertThat(sut.getLongitude()).isCloseTo(-58.35183799, Percentage.withPercentage(0.0000001));
    }

    @Test
    public void neighbors_test_ForPositiveLatitudeAndLongitudeAndPrecision12() {
        // Arrange
        String geohash = "ufk234ussbtx";
        // Act
        List<String> neighbors = (List<String>) GeoHashUtils.neighbors(geohash);
        // Assert
        assertThat(neighbors.size()).isEqualTo(8);
        assertThat(neighbors).contains("ufk234ussbv2");
        assertThat(neighbors).contains("ufk234ussbv8");
        assertThat(neighbors).contains("ufk234ussbvb");
        assertThat(neighbors).contains("ufk234ussbtr");
        assertThat(neighbors).contains("ufk234ussbtz");
        assertThat(neighbors).contains("ufk234ussbtq");
        assertThat(neighbors).contains("ufk234ussbtw");
        assertThat(neighbors).contains("ufk234ussbty");
    }

    @Test
    public void neighbors_test_ForPositiveLatitudeAndLongitudeAndPrecision10() {
        // Arrange
        String geohash = "ufk234ussb";
        // Act
        List<String> neighbors = (List<String>) GeoHashUtils.neighbors(geohash);
        // Assert
        assertThat(neighbors.size()).isEqualTo(8);
        assertThat(neighbors).contains("ufk234uss9");
        assertThat(neighbors).contains("ufk234ussc");
        assertThat(neighbors).contains("ufk234ust1");
        assertThat(neighbors).contains("ufk234uss8");
        assertThat(neighbors).contains("ufk234ust0");
        assertThat(neighbors).contains("ufk234uskx");
        assertThat(neighbors).contains("ufk234uskz");
        assertThat(neighbors).contains("ufk234usmp");
    }

    @Test
    public void neighbors_test_ForNegativeLatitudeAndPositiveLongitudeAndPrecision12() {
        // Arrange
        String geohash = "r3gx3k1d6pxu";
        // Act
        List<String> neighbors = (List<String>) GeoHashUtils.neighbors(geohash);
        // Assert
        assertThat(neighbors.size()).isEqualTo(8);
        assertThat(neighbors).contains("r3gx3k1d6pxt");
        assertThat(neighbors).contains("r3gx3k1d6pxv");
        assertThat(neighbors).contains("r3gx3k1d6r8j");
        assertThat(neighbors).contains("r3gx3k1d6pxs");
        assertThat(neighbors).contains("r3gx3k1d6r8h");
        assertThat(neighbors).contains("r3gx3k1d6pxe");
        assertThat(neighbors).contains("r3gx3k1d6pxg");
        assertThat(neighbors).contains("r3gx3k1d6r85");
    }

    @Test
    public void neighbors_test_ForNegativeLatitudeAndPositiveLongitudeAndPrecision9() {
        // Arrange
        String geohash = "r3gx3k1d6";
        // Act
        List<String> neighbors = (List<String>) GeoHashUtils.neighbors(geohash);
        // Assert
        assertThat(neighbors.size()).isEqualTo(8);
        assertThat(neighbors).contains("r3gx3k1d9");
        assertThat(neighbors).contains("r3gx3k1dd");
        assertThat(neighbors).contains("r3gx3k1de");
        assertThat(neighbors).contains("r3gx3k1d3");
        assertThat(neighbors).contains("r3gx3k1d7");
        assertThat(neighbors).contains("r3gx3k1d1");
        assertThat(neighbors).contains("r3gx3k1d4");
        assertThat(neighbors).contains("r3gx3k1d5");
    }

    @Test
    public void neighbors_test_ForPositiveLatitudeAndNegativeLongitudeAndPrecision12() {
        // Arrange
        String geohash = "dhj7w6t762qd";
        // Act
        List<String> neighbors = (List<String>) GeoHashUtils.neighbors(geohash);
        // Assert
        assertThat(neighbors.size()).isEqualTo(8);
        assertThat(neighbors).contains("dhj7w6t762q7");
        assertThat(neighbors).contains("dhj7w6t762qe");
        assertThat(neighbors).contains("dhj7w6t762qg");
        assertThat(neighbors).contains("dhj7w6t762q6");
        assertThat(neighbors).contains("dhj7w6t762qf");
        assertThat(neighbors).contains("dhj7w6t762q3");
        assertThat(neighbors).contains("dhj7w6t762q9");
        assertThat(neighbors).contains("dhj7w6t762qc");
    }

    @Test
    public void neighbors_test_ForPositiveLatitudeAndNegativeLongitudeAndPrecision11() {
        // Arrange
        String geohash = "dhj7w6t762q";
        // Act
        List<String> neighbors = (List<String>) GeoHashUtils.neighbors(geohash);
        // Assert
        assertThat(neighbors.size()).isEqualTo(8);
        assertThat(neighbors).contains("dhj7w6t762t");
        assertThat(neighbors).contains("dhj7w6t762w");
        assertThat(neighbors).contains("dhj7w6t762x");
        assertThat(neighbors).contains("dhj7w6t762m");
        assertThat(neighbors).contains("dhj7w6t762r");
        assertThat(neighbors).contains("dhj7w6t762j");
        assertThat(neighbors).contains("dhj7w6t762n");
        assertThat(neighbors).contains("dhj7w6t762p");
    }

    @Test
    public void neighbors_test_ForNegativeLatitudeAndLongitudeAndPrecision12() {
        // Arrange
        String geohash = "69ye0ht7pwgh";
        // Act
        List<String> neighbors = (List<String>) GeoHashUtils.neighbors(geohash);
        // Assert
        assertThat(neighbors.size()).isEqualTo(8);
        assertThat(neighbors).contains("69ye0ht7pwfv");
        assertThat(neighbors).contains("69ye0ht7pwgj");
        assertThat(neighbors).contains("69ye0ht7pwgm");
        assertThat(neighbors).contains("69ye0ht7pwfu");
        assertThat(neighbors).contains("69ye0ht7pwgk");
        assertThat(neighbors).contains("69ye0ht7pwfg");
        assertThat(neighbors).contains("69ye0ht7pwg5");
        assertThat(neighbors).contains("69ye0ht7pwg7");
    }

    @Test
    public void neighbors_test_ForNegativeLatitudeAndLongitudeAndPrecision10() {
        // Arrange
        String geohash = "69ye0ht7pw";
        // Act
        List<String> neighbors = (List<String>) GeoHashUtils.neighbors(geohash);
        // Assert
        assertThat(neighbors.size()).isEqualTo(8);
        assertThat(neighbors).contains("69ye0ht7pr");
        assertThat(neighbors).contains("69ye0ht7px");
        assertThat(neighbors).contains("69ye0ht7pz");
        assertThat(neighbors).contains("69ye0ht7pq");
        assertThat(neighbors).contains("69ye0ht7py");
        assertThat(neighbors).contains("69ye0ht7pm");
        assertThat(neighbors).contains("69ye0ht7pt");
        assertThat(neighbors).contains("69ye0ht7pv");
    }

}
