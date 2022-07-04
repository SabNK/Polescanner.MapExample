package ru.polescanner.mapexample;

import static org.assertj.core.api.Assertions.assertThat;

import ru.polescanner.mapexample.service.ParallelLinesProvider.*;

import org.assertj.core.data.Percentage;
import org.junit.Test;

/**
 * Created by RykVS on 27.06.2022.
 * It is testing for common UTM zones into Russia.
 * https://coordinates-converter.com
 */
public class UtmToDegTest {
    @Test
    public void w34_test() {
        DegToUtm utm = new DegToUtm(65.072130, 19.160156);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(65.072130, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(19.16015, Percentage.withPercentage(0.0001));
    }

    @Test
    public void w35_test() {
        DegToUtm utm = new DegToUtm(64.736641, 28.212891);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(64.736641, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(28.212891, Percentage.withPercentage(0.0001));
    }

    @Test
    public void w36_test() {
        DegToUtm utm = new DegToUtm(64.623877, 31.992188);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(64.623877, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(31.992188, Percentage.withPercentage(0.0001));
    }

    @Test
    public void w37_test() {
        DegToUtm utm = new DegToUtm(64.661517, 40.693359);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(64.661517, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(40.693359, Percentage.withPercentage(0.0001));
    }

    @Test
    public void w38_test() {
        DegToUtm utm = new DegToUtm(64.548440, 47.021484);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(64.548440, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(47.021484, Percentage.withPercentage(0.0001));
    }

    @Test
    public void w39_test() {
        DegToUtm utm = new DegToUtm(64.491725, 48.208008);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(64.491725, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(48.208008, Percentage.withPercentage(0.0001));
    }

    @Test
    public void w40_test() {
        DegToUtm utm = new DegToUtm(64.301822, 56.030273);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(64.301822, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(56.030273, Percentage.withPercentage(0.0001));
    }

    @Test
    public void w41_test() {
        DegToUtm utm = new DegToUtm(64.129784, 60.073242);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(64.129784, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(60.073242, Percentage.withPercentage(0.0001));
    }

    @Test
    public void w42_test() {
        DegToUtm utm = new DegToUtm(64.148952, 67.368164);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(64.148952, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(67.368164, Percentage.withPercentage(0.0001));
    }

    @Test
    public void w43_test() {
        DegToUtm utm = new DegToUtm(64.187249, 73.608398);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(64.187249, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(73.608398, Percentage.withPercentage(0.0001));
    }

    @Test
    public void w44_test() {
        DegToUtm utm = new DegToUtm(64.014496, 79.497070);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(64.014496, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(79.497070, Percentage.withPercentage(0.0001));
    }

    @Test
    public void w45_test() {
        DegToUtm utm = new DegToUtm(64.642704, 89.868164);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(64.642704, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(89.868164, Percentage.withPercentage(0.0001));
    }

    @Test
    public void w46_test() {
        DegToUtm utm = new DegToUtm(64.510643, 90.043945);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(64.510643, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(90.043945, Percentage.withPercentage(0.0001));
    }

    @Test
    public void w47_test() {
        DegToUtm utm = new DegToUtm(64.444372, 96.547852);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(64.444372, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(96.547852, Percentage.withPercentage(0.0001));
    }

    @Test
    public void w48_test() {
        DegToUtm utm = new DegToUtm(64.368438, 102.238770);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(64.368438, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(102.238770, Percentage.withPercentage(0.0001));
    }

    @Test
    public void w49_test() {
        DegToUtm utm = new DegToUtm(64.349421, 108.017578);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(64.349421, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(108.017578, Percentage.withPercentage(0.0001));
    }

    @Test
    public void w50_test() {
        DegToUtm utm = new DegToUtm(64.168107, 114.960938);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(64.168107, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(114.960938, Percentage.withPercentage(0.0001));
    }

    @Test
    public void w51_test() {
        DegToUtm utm = new DegToUtm(64.072200, 125.837402);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(64.072200, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(125.837402, Percentage.withPercentage(0.0001));
    }

    @Test
    public void w52_test() {
        DegToUtm utm = new DegToUtm(64.081805, 126.079102);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(64.081805, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(126.079102, Percentage.withPercentage(0.0001));
    }

    @Test
    public void w53_test() {
        DegToUtm utm = new DegToUtm(64.024122, 132.055664);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(64.024122, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(132.055664, Percentage.withPercentage(0.0001));
    }

    @Test
    public void w54_test() {
        DegToUtm utm = new DegToUtm(64.129784, 138.427734);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(64.129784, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(138.427734, Percentage.withPercentage(0.0001));
    }

    @Test
    public void w55_test() {
        DegToUtm utm = new DegToUtm(64.033744, 144.887695);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(64.033744, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(144.887695, Percentage.withPercentage(0.0001));
    }

    @Test
    public void w56_test() {
        DegToUtm utm = new DegToUtm(64.014496, 150.073242);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(64.014496, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(150.073242, Percentage.withPercentage(0.0001));
    }

    @Test
    public void w57_test() {
        DegToUtm utm = new DegToUtm(64.717880, 160.004883);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(64.717880, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(160.004883, Percentage.withPercentage(0.0001));
    }

    @Test
    public void w58_test() {
        DegToUtm utm = new DegToUtm(64.586185, 166.025391);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(64.586185, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(166.025391, Percentage.withPercentage(0.0001));
    }

    @Test
    public void w59_test() {
        DegToUtm utm = new DegToUtm(64.717880, 172.661133);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(64.717880, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(172.661133, Percentage.withPercentage(0.0001));
    }

    @Test
    public void w60_test() {
        DegToUtm utm = new DegToUtm(64.886265, 177.451172);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(64.886265, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(177.451172, Percentage.withPercentage(0.0001));
    }

    @Test
    public void w61_test() {
        DegToUtm utm = new DegToUtm(66.843807, 182.856445);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(66.843807, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(182.856445, Percentage.withPercentage(0.0001));
    }

    @Test
    public void w62_test() {
        DegToUtm utm = new DegToUtm(65.603878, 188.129883);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(65.603878, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(188.129883, Percentage.withPercentage(0.0001));
    }

    @Test
    public void v34_test() {
        DegToUtm utm = new DegToUtm(56.897004, 22.236328);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(56.897004, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(22.236328, Percentage.withPercentage(0.0001));
    }

    @Test
    public void v35_test() {
        DegToUtm utm = new DegToUtm(59.310768, 28.916016);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(59.310768, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(28.916016, Percentage.withPercentage(0.0001));
    }

    @Test
    public void v36_test() {
        DegToUtm utm = new DegToUtm(57.350237, 34.584961);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(57.350237, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(34.584961, Percentage.withPercentage(0.0001));
    }

    @Test
    public void v37_test() {
        DegToUtm utm = new DegToUtm(57.762799, 39.836426);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(57.762799, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(39.836426, Percentage.withPercentage(0.0001));
    }

    @Test
    public void v38_test() {
        DegToUtm utm = new DegToUtm(56.255557, 43.879395);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(56.255557, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(43.879395, Percentage.withPercentage(0.0001));
    }

    @Test
    public void v39_test() {
        DegToUtm utm = new DegToUtm(56.788845, 53.063965);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(56.788845, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(53.063965, Percentage.withPercentage(0.0001));
    }

    @Test
    public void v40_test() {
        DegToUtm utm = new DegToUtm(58.031372, 56.403809);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(58.031372, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(56.403809, Percentage.withPercentage(0.0001));
    }

    @Test
    public void v41_test() {
        DegToUtm utm = new DegToUtm(56.897004, 60.380859);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(56.897004, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(60.380859, Percentage.withPercentage(0.0001));
    }

    @Test
    public void v42_test() {
        DegToUtm utm = new DegToUtm(58.193871, 68.225098);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(58.193871, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(68.225098, Percentage.withPercentage(0.0001));
    }

    @Test
    public void v43_test() {
        DegToUtm utm = new DegToUtm(61.068917, 73.256836);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(61.068917, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(73.256836, Percentage.withPercentage(0.0001));
    }

    @Test
    public void v44_test() {
        DegToUtm utm = new DegToUtm(58.344101, 81.716309);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(58.344101, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(81.716309, Percentage.withPercentage(0.0001));
    }

    @Test
    public void v45_test() {
        DegToUtm utm = new DegToUtm(56.498892, 84.924316);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(56.498892, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(84.924316, Percentage.withPercentage(0.0001));
    }

    @Test
    public void v46_test() {
        DegToUtm utm = new DegToUtm(56.108810, 93.098145);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(56.108810, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(93.098145, Percentage.withPercentage(0.0001));
    }

    @Test
    public void v47_test() {
        DegToUtm utm = new DegToUtm(56.182254, 101.667480);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(56.182254, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(101.667480, Percentage.withPercentage(0.0001));
    }

    @Test
    public void v48_test() {
        DegToUtm utm = new DegToUtm(57.088515, 105.380859);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(57.088515, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(105.380859, Percentage.withPercentage(0.0001));
    }

    @Test
    public void v49_test() {
        DegToUtm utm = new DegToUtm(56.072035, 113.994141);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(56.072035, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(113.994141, Percentage.withPercentage(0.0001));
    }

    @Test
    public void v50_test() {
        DegToUtm utm = new DegToUtm(56.920997, 118.652344);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(56.920997, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(118.652344, Percentage.withPercentage(0.0001));
    }

    @Test
    public void v51_test() {
        DegToUtm utm = new DegToUtm(56.860986, 120.476074);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(56.860986, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(120.476074, Percentage.withPercentage(0.0001));
    }

    @Test
    public void v52_test() {
        DegToUtm utm = new DegToUtm(58.716894, 127.155762);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(58.716894, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(127.155762, Percentage.withPercentage(0.0001));
    }

    @Test
    public void v53_test() {
        DegToUtm utm = new DegToUtm(60.909073, 135.131836);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(60.909073, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(135.131836, Percentage.withPercentage(0.0001));
    }

    @Test
    public void v54_test() {
        DegToUtm utm = new DegToUtm(60.673179, 141.811523);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(60.673179, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(141.811523, Percentage.withPercentage(0.0001));
    }

    @Test
    public void v55_test() {
        DegToUtm utm = new DegToUtm(62.532594, 146.997070);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(62.532594, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(146.997070, Percentage.withPercentage(0.0001));
    }

    @Test
    public void v56_test() {
        DegToUtm utm = new DegToUtm(62.754726, 152.753906);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(62.754726, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(152.753906, Percentage.withPercentage(0.0001));
    }

    @Test
    public void v57_test() {
        DegToUtm utm = new DegToUtm(62.995158, 159.960938);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(62.995158, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(159.960938, Percentage.withPercentage(0.0001));
    }

    @Test
    public void v58_test() {
        DegToUtm utm = new DegToUtm(62.512318, 166.596680);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(62.512318, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(166.596680, Percentage.withPercentage(0.0001));
    }

    @Test
    public void v59_test() {
        DegToUtm utm = new DegToUtm(61.627286, 170.991211);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(61.627286, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(170.991211, Percentage.withPercentage(0.0001));
    }

    @Test
    public void v60_test() {
        DegToUtm utm = new DegToUtm(63.134503, 177.583008);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(63.134503, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(177.583008, Percentage.withPercentage(0.0001));
    }

    @Test
    public void u35_test() {
        DegToUtm utm = new DegToUtm(52.214339, 24.697266);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(52.214339, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(24.697266, Percentage.withPercentage(0.0001));
    }

    @Test
    public void u36_test() {
        DegToUtm utm = new DegToUtm(52.643063, 33.420410);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(52.643063, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(33.420410, Percentage.withPercentage(0.0001));
    }

    @Test
    public void u37_test() {
        DegToUtm utm = new DegToUtm(51.713416, 39.177246);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(51.713416, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(39.177246, Percentage.withPercentage(0.0001));
    }

    @Test
    public void u38_test() {
        DegToUtm utm = new DegToUtm(54.188155, 45.175781);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(54.188155, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(45.175781, Percentage.withPercentage(0.0001));
    }

    @Test
    public void u39_test() {
        DegToUtm utm = new DegToUtm(54.278055, 48.339844);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(54.278055, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(48.339844, Percentage.withPercentage(0.0001));
    }

    @Test
    public void u40_test() {
        DegToUtm utm = new DegToUtm(52.696361, 56.118164);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(52.696361, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(56.118164, Percentage.withPercentage(0.0001));
    }

    @Test
    public void u41_test() {
        DegToUtm utm = new DegToUtm(55.103516, 61.325684);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(55.103516, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(61.325684, Percentage.withPercentage(0.0001));
    }

    @Test
    public void u42_test() {
        DegToUtm utm = new DegToUtm(55.590763, 68.664551);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(55.590763, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(68.664551, Percentage.withPercentage(0.0001));
    }

    @Test
    public void u43_test() {
        DegToUtm utm = new DegToUtm(54.851315, 73.410645);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(54.851315, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(73.410645, Percentage.withPercentage(0.0001));
    }

    @Test
    public void u44_test() {
        DegToUtm utm = new DegToUtm(53.488046, 83.759766);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(53.488046, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(83.759766, Percentage.withPercentage(0.0001));
    }

    @Test
    public void u45_test() {
        DegToUtm utm = new DegToUtm(53.774689, 87.099609);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(53.774689, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(87.099609, Percentage.withPercentage(0.0001));
    }

    @Test
    public void u46_test() {
        DegToUtm utm = new DegToUtm(55.727110, 92.636719);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(55.727110, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(92.636719, Percentage.withPercentage(0.0001));
    }

    @Test
    public void u47_test() {
        DegToUtm utm = new DegToUtm(53.592505, 98.613281);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(53.592505, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(98.613281, Percentage.withPercentage(0.0001));
    }

    @Test
    public void u48_test() {
        DegToUtm utm = new DegToUtm(51.481383, 107.709961);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(51.481383, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(107.709961, Percentage.withPercentage(0.0001));
    }

    @Test
    public void u49_test() {
        DegToUtm utm = new DegToUtm(52.052490, 113.510742);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(52.052490, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(113.510742, Percentage.withPercentage(0.0001));
    }

    @Test
    public void u50_test() {
        DegToUtm utm = new DegToUtm(52.133488, 119.663086);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(52.133488, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(119.663086, Percentage.withPercentage(0.0001));
    }

    @Test
    public void u51_test() {
        DegToUtm utm = new DegToUtm(54.901882, 123.310547);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(54.901882, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(123.310547, Percentage.withPercentage(0.0001));
    }

    @Test
    public void u52_test() {
        DegToUtm utm = new DegToUtm(54.085173, 128.540039);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(54.085173, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(128.540039, Percentage.withPercentage(0.0001));
    }

    @Test
    public void u53_test() {
        DegToUtm utm = new DegToUtm(54.007769, 133.901367);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(54.007769, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(133.901367, Percentage.withPercentage(0.0001));
    }

    @Test
    public void u54_test() {
        DegToUtm utm = new DegToUtm(52.882391, 139.921875);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(52.882391, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(139.921875, Percentage.withPercentage(0.0001));
    }

    @Test
    public void u56_test() {
        DegToUtm utm = new DegToUtm(54.876607, 155.742188);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(54.876607, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(155.742188, Percentage.withPercentage(0.0001));
    }

    @Test
    public void u57_test() {
        DegToUtm utm = new DegToUtm(55.065787, 158.356934);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(55.065787, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(158.356934, Percentage.withPercentage(0.0001));
    }

    @Test
    public void u58_test() {
        DegToUtm utm = new DegToUtm(55.002826, 166.223145);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(55.002826, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(166.223145, Percentage.withPercentage(0.0001));
    }

    @Test
    public void t36_test() {
        DegToUtm utm = new DegToUtm(45.182037, 34.035645);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(45.182037, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(34.035645, Percentage.withPercentage(0.0001));
    }

    @Test
    public void t37_test() {
        DegToUtm utm = new DegToUtm(47.234490, 39.792480);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(47.234490, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(39.792480, Percentage.withPercentage(0.0001));
    }

    @Test
    public void t38_test() {
        DegToUtm utm = new DegToUtm(46.286224, 44.318848);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(46.286224, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(44.318848, Percentage.withPercentage(0.0001));
    }

    @Test
    public void t39_test() {
        DegToUtm utm = new DegToUtm(46.301406, 48.098145);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(46.301406, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(48.098145, Percentage.withPercentage(0.0001));
    }

    // ToDo: add test code for Kazakhstan and letter T

    @Test
    public void t52_test() {
        DegToUtm utm = new DegToUtm(43.826601, 131.550293);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(43.826601, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(131.550293, Percentage.withPercentage(0.0001));
    }

    @Test
    public void t53_test() {
        DegToUtm utm = new DegToUtm(43.659924, 133.571777);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(43.659924, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(133.571777, Percentage.withPercentage(0.0001));
    }

    @Test
    public void t54_test() {
        DegToUtm utm = new DegToUtm(46.769968, 142.558594);
        UtmToDeg sut = new UtmToDeg(utm);
        assertThat(sut.getLatitude()).isCloseTo(46.769968, Percentage.withPercentage(0.0001));
        assertThat(sut.getLongitude()).isCloseTo(142.558594, Percentage.withPercentage(0.0001));
    }
}
