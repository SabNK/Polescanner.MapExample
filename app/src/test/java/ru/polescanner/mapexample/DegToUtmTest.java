package ru.polescanner.mapexample;

import static org.assertj.core.api.Assertions.assertThat;

import ru.polescanner.mapexample.service.ParallelLinesProvider.DegToUtm;

import org.junit.Test;

/**
 * Created by RykVS on 27.06.2022.
 * It is testing for common UTM zones into Russia.
 * https://coordinates-converter.com
 */
public class DegToUtmTest {
    // from North to South
    @Test
    public void w32_test() {
        DegToUtm sut = new DegToUtm(65.982270, 8.876953);
        assertThat(sut.getEasting()).isBetween(494411.0, 494412.0);
        assertThat(sut.getNorthing()).isBetween(7317939.0, 7317940.0);
        assertThat(sut.getZone()).isEqualTo(32);
        assertThat(sut.getLetter()).isEqualTo('W');
    }

    @Test
    public void v32_test() {
        DegToUtm sut = new DegToUtm(61.100789, 7.998047);
        assertThat(sut.getEasting()).isBetween(445981.0, 445982.0);
        assertThat(sut.getNorthing()).isBetween(6774427.0, 6774428.0);
        assertThat(sut.getZone()).isEqualTo(32);
        assertThat(sut.getLetter()).isEqualTo('V');
    }

    @Test
    public void u32_test() {
        DegToUtm sut = new DegToUtm(51.124213, 8.876953);
        assertThat(sut.getEasting()).isBetween(491388.0, 491389.0);
        assertThat(sut.getNorthing()).isBetween(5663645.0, 5663646.0);
        assertThat(sut.getZone()).isEqualTo(32);
        assertThat(sut.getLetter()).isEqualTo('U');
    }

    @Test
    public void t32_test() {
        DegToUtm sut = new DegToUtm(40.178873, 9.052734);
        assertThat(sut.getEasting()).isBetween(504489.0, 504490.0);
        assertThat(sut.getNorthing()).isBetween(4447612.0, 4447613.0);
        assertThat(sut.getZone()).isEqualTo(32);
        assertThat(sut.getLetter()).isEqualTo('T');
    }

    @Test
    public void s32_test() {
        DegToUtm sut = new DegToUtm(33.284620, 8.261719);
        assertThat(sut.getEasting()).isBetween(431254.0, 431255.0);
        assertThat(sut.getNorthing()).isBetween(3683083.0, 3683084.0);
        assertThat(sut.getZone()).isEqualTo(32);
        assertThat(sut.getLetter()).isEqualTo('S');
    }

    @Test
    public void r32_test() {
        DegToUtm sut = new DegToUtm(28.459033, 7.734375);
        assertThat(sut.getEasting()).isBetween(376086.0, 376087.0);
        assertThat(sut.getNorthing()).isBetween(3148705.0, 3148706.0);
        assertThat(sut.getZone()).isEqualTo(32);
        assertThat(sut.getLetter()).isEqualTo('R');
    }

    @Test
    public void q32_test() {
        DegToUtm sut = new DegToUtm(17.476432, 6.943359);
        assertThat(sut.getEasting()).isBetween(281606.0, 281607.0);
        assertThat(sut.getNorthing()).isBetween(1933439.0, 1933440.0);
        assertThat(sut.getZone()).isEqualTo(32);
        assertThat(sut.getLetter()).isEqualTo('Q');
    }

    @Test
    public void p32_test() {
        DegToUtm sut = new DegToUtm(10.401378, 7.382813);
        assertThat(sut.getEasting()).isBetween(322963.0, 322964.0);
        assertThat(sut.getNorthing()).isBetween(1150241.0, 1150242.0);
        assertThat(sut.getZone()).isEqualTo(32);
        assertThat(sut.getLetter()).isEqualTo('P');
    }

    @Test
    public void n32_test() {
        DegToUtm sut = new DegToUtm(5.703448, 6.679688);
        assertThat(sut.getEasting()).isBetween(243007.0, 243008.0);
        assertThat(sut.getNorthing()).isBetween(630940.0, 630941.0);
        assertThat(sut.getZone()).isEqualTo(32);
        assertThat(sut.getLetter()).isEqualTo('N');
    }

    @Test
    public void m32_test() {
        DegToUtm sut = new DegToUtm(-7.188101, 6.416016);
        assertThat(sut.getEasting()).isBetween(214617.0, 214618.0);
        assertThat(sut.getNorthing()).isBetween(9204651.0, 9204652.0);
        assertThat(sut.getZone()).isEqualTo(32);
        assertThat(sut.getLetter()).isEqualTo('M');
    }

    @Test
    public void l32_test() {
        DegToUtm sut = new DegToUtm(-8.102739, 8.041992);
        assertThat(sut.getEasting()).isBetween(394450.0, 394451.0);
        assertThat(sut.getNorthing()).isBetween(9104219.0, 9104220.0);
        assertThat(sut.getZone()).isEqualTo(32);
        assertThat(sut.getLetter()).isEqualTo('L');
    }

    @Test
    public void k32_test() {
        DegToUtm sut = new DegToUtm(-18.646245, 6.328125);
        assertThat(sut.getEasting()).isBetween(218114.0, 218115.0);
        assertThat(sut.getNorthing()).isBetween(7936211.0, 7936212.0);
        assertThat(sut.getZone()).isEqualTo(32);
        assertThat(sut.getLetter()).isEqualTo('K');
    }

    @Test
    public void j32_test() {
        DegToUtm sut = new DegToUtm(-26.980829, 6.943359);
        assertThat(sut.getEasting()).isBetween(295889.0, 295890.0);
        assertThat(sut.getNorthing()).isBetween(7014025.0, 7014026.0);
        assertThat(sut.getZone()).isEqualTo(32);
        assertThat(sut.getLetter()).isEqualTo('J');
    }

    @Test
    public void h32_test() {
        DegToUtm sut = new DegToUtm(-33.358062, 7.207031);
        assertThat(sut.getEasting()).isBetween(333177.0, 333178.0);
        assertThat(sut.getNorthing()).isBetween(6307581.0, 6307582.0);
        assertThat(sut.getZone()).isEqualTo(32);
        assertThat(sut.getLetter()).isEqualTo('H');
    }

    @Test
    public void g32_test() {
        DegToUtm sut = new DegToUtm(-42.358544, 8.349609);
        assertThat(sut.getEasting()).isBetween(446439.0, 446440.0);
        assertThat(sut.getNorthing()).isBetween(5310208.0, 5310209.0);
        assertThat(sut.getZone()).isEqualTo(32);
        assertThat(sut.getLetter()).isEqualTo('G');
    }

    @Test
    public void f32_test() {
        DegToUtm sut = new DegToUtm(-49.951220, 8.349609);
        assertThat(sut.getEasting()).isBetween(453341.0, 453342.0);
        assertThat(sut.getNorthing()).isBetween(4466590.0, 4466591.0);
        assertThat(sut.getZone()).isEqualTo(32);
        assertThat(sut.getLetter()).isEqualTo('F');
    }

    @Test
    public void e32_test() {
        DegToUtm sut = new DegToUtm(-58.995311, 8.525391);
        assertThat(sut.getEasting()).isBetween(472729.0, 472730.0);
        assertThat(sut.getNorthing()).isBetween(3460373.0, 3460374.0);
        assertThat(sut.getZone()).isEqualTo(32);
        assertThat(sut.getLetter()).isEqualTo('E');
    }

    @Test
    public void d32_test() {
        DegToUtm sut = new DegToUtm(-65.366837, 9.052734);
        assertThat(sut.getEasting()).isBetween(502452.0, 502453.0);
        assertThat(sut.getNorthing()).isBetween(2750660.0, 2750661.0);
        assertThat(sut.getZone()).isEqualTo(32);
        assertThat(sut.getLetter()).isEqualTo('D');
    }

    @Test
    public void c32_test1() {
        DegToUtm sut = new DegToUtm(-75.140778, 7.910156);
        assertThat(sut.getEasting()).isBetween(468804.0, 468805.0);
        assertThat(sut.getNorthing()).isBetween(1660399.0, 1660400.0);
        assertThat(sut.getZone()).isEqualTo(32);
        assertThat(sut.getLetter()).isEqualTo('C');
    }

    @Test
    public void c32_test2() {
        DegToUtm sut = new DegToUtm(-85.051129, 7.031250);
        assertThat(sut.getEasting()).isBetween(481041.0, 481042.0);
        assertThat(sut.getNorthing()).isBetween(554234.0, 554235.0);
        assertThat(sut.getZone()).isEqualTo(32);
        assertThat(sut.getLetter()).isEqualTo('C');
    }

    @Test
    public void c32_test3() {
        DegToUtm sut = new DegToUtm(-88.206436, 11.777344);
        assertThat(sut.getEasting()).isBetween(509701.0, 509702.0);
        assertThat(sut.getNorthing()).isBetween(202049.0, 202050.0);
        assertThat(sut.getZone()).isEqualTo(32);
        assertThat(sut.getLetter()).isEqualTo('C');
    }

    // from West of Russia to East of Russia
    @Test
    public void w34_test() {
        DegToUtm sut = new DegToUtm(65.072130, 19.160156);
        assertThat(sut.getEasting()).isBetween(413482.0, 413483.0);
        assertThat(sut.getNorthing()).isBetween(7217753.0, 7217754.0);
        assertThat(sut.getZone()).isEqualTo(34);
        assertThat(sut.getLetter()).isEqualTo('W');
    }

    @Test
    public void w35_test() {
        DegToUtm sut = new DegToUtm(64.736641, 28.212891);
        assertThat(sut.getEasting()).isBetween(557755.0, 557756.0);
        assertThat(sut.getNorthing()).isBetween(7179657.0, 7179658.0);
        assertThat(sut.getZone()).isEqualTo(35);
        assertThat(sut.getLetter()).isEqualTo('W');
    }

    @Test
    public void w36_test() {
        DegToUtm sut = new DegToUtm(64.623877, 31.992188);
        assertThat(sut.getEasting()).isBetween(451809.0, 451810.0);
        assertThat(sut.getNorthing()).isBetween(7166920.0, 7166921.0);
        assertThat(sut.getZone()).isEqualTo(36);
        assertThat(sut.getLetter()).isEqualTo('W');
    }

    @Test
    public void w37_test() {
        DegToUtm sut = new DegToUtm(64.661517, 40.693359);
        assertThat(sut.getEasting()).isBetween(580854.0, 580855.0);
        assertThat(sut.getNorthing()).isBetween(7171812.0, 7171813.0);
        assertThat(sut.getZone()).isEqualTo(37);
        assertThat(sut.getLetter()).isEqualTo('W');
    }

    @Test
    public void w38_test() {
        DegToUtm sut = new DegToUtm(64.548440, 47.021484);
        assertThat(sut.getEasting()).isBetween(596919.0, 596920.0);
        assertThat(sut.getNorthing()).isBetween(7159674.0, 7159675.0);
        assertThat(sut.getZone()).isEqualTo(38);
        assertThat(sut.getLetter()).isEqualTo('W');
    }

    @Test
    public void w39_test() {
        DegToUtm sut = new DegToUtm(64.491725, 48.208008);
        assertThat(sut.getEasting()).isBetween(365875.0, 365876.0);
        assertThat(sut.getNorthing()).isBetween(7154760.0, 7154761.0);
        assertThat(sut.getZone()).isEqualTo(39);
        assertThat(sut.getLetter()).isEqualTo('W');
    }

    @Test
        public void w40_test() {
            DegToUtm sut = new DegToUtm(64.301822, 56.030273);
            assertThat(sut.getEasting()).isBetween(453082.0, 453083.0);
            assertThat(sut.getNorthing()).isBetween(7131005.0, 7131006.0);
        assertThat(sut.getZone()).isEqualTo(40);
        assertThat(sut.getLetter()).isEqualTo('W');
    }

    @Test
    public void w41_test() {
        DegToUtm sut = new DegToUtm(64.129784, 60.073242);
        assertThat(sut.getEasting()).isBetween(357548.0, 357549.0);
        assertThat(sut.getNorthing()).isBetween(7114751.0, 7114752.0);
        assertThat(sut.getZone()).isEqualTo(41);
        assertThat(sut.getLetter()).isEqualTo('W');
    }

    @Test
    public void w42_test() {
        DegToUtm sut = new DegToUtm(64.148952, 67.368164);
        assertThat(sut.getEasting()).isBetween(420615.0, 420616.0);
        assertThat(sut.getNorthing()).isBetween(7114629.0, 7114630.0);
        assertThat(sut.getZone()).isEqualTo(42);
        assertThat(sut.getLetter()).isEqualTo('W');
    }

    @Test
    public void w43_test() {
        DegToUtm sut = new DegToUtm(64.187249, 73.608398);
        assertThat(sut.getEasting()).isBetween(432393.0, 432394.0);
        assertThat(sut.getNorthing()).isBetween(7118619.0, 7118620.0);
        assertThat(sut.getZone()).isEqualTo(43);
        assertThat(sut.getLetter()).isEqualTo('W');
    }

    @Test
    public void w44_test() {
        DegToUtm sut = new DegToUtm(64.014496, 79.497070);
        assertThat(sut.getEasting()).isBetween(426531.0, 426532.0);
        assertThat(sut.getNorthing()).isBetween(7099495.0, 7099496.0);
        assertThat(sut.getZone()).isEqualTo(44);
        assertThat(sut.getLetter()).isEqualTo('W');
    }

    @Test
    public void w45_test() {
        DegToUtm sut = new DegToUtm(64.642704, 89.868164);
        assertThat(sut.getEasting()).isBetween(637020.0, 637021.0);
        assertThat(sut.getNorthing()).isBetween(7171735.0, 7171736.0);
        assertThat(sut.getZone()).isEqualTo(45);
        assertThat(sut.getLetter()).isEqualTo('W');
    }

    @Test
    public void w46_test() {
        DegToUtm sut = new DegToUtm(64.510643, 90.043945);
        assertThat(sut.getEasting()).isBetween(358097.0, 358098.0);
        assertThat(sut.getNorthing()).isBetween(7157223.0, 7157224.0);
        assertThat(sut.getZone()).isEqualTo(46);
        assertThat(sut.getLetter()).isEqualTo('W');
    }

    @Test
    public void w47_test() {
        DegToUtm sut = new DegToUtm(64.444372, 96.547852);
        assertThat(sut.getEasting()).isBetween(381991.0, 381992.0);
        assertThat(sut.getNorthing()).isBetween(7148811.0, 7148812.0);
        assertThat(sut.getZone()).isEqualTo(47);
        assertThat(sut.getLetter()).isEqualTo('W');
    }

    @Test
    public void w48_test() {
        DegToUtm sut = new DegToUtm(64.368438, 102.238770);
        assertThat(sut.getEasting()).isBetween(366755.0, 366756.0);
        assertThat(sut.getNorthing()).isBetween(7140966.0, 7140967.0);
        assertThat(sut.getZone()).isEqualTo(48);
        assertThat(sut.getLetter()).isEqualTo('W');
    }

    @Test
    public void w49_test() {
        DegToUtm sut = new DegToUtm(64.349421, 108.017578);
        assertThat(sut.getEasting()).isBetween(355988.0, 355989.0);
        assertThat(sut.getNorthing()).isBetween(7139331.0, 7139332.0);
        assertThat(sut.getZone()).isEqualTo(49);
        assertThat(sut.getLetter()).isEqualTo('W');
    }

    @Test
    public void w50_test() {
        DegToUtm sut = new DegToUtm(64.168107, 114.960938);
        assertThat(sut.getEasting()).isBetween(400877.0, 400878.0);
        assertThat(sut.getNorthing()).isBetween(7117334.0, 7117335.0);
        assertThat(sut.getZone()).isEqualTo(50);
        assertThat(sut.getLetter()).isEqualTo('W');
    }

    @Test
    public void w51_test() {
        DegToUtm sut = new DegToUtm(64.072200, 125.837402);
        assertThat(sut.getEasting()).isBetween(638390.0, 638391.0);
        assertThat(sut.getNorthing()).isBetween(7108142.0, 7108143.0);
        assertThat(sut.getZone()).isEqualTo(51);
        assertThat(sut.getLetter()).isEqualTo('W');
    }

    @Test
    public void w52_test() {
        DegToUtm sut = new DegToUtm(64.081805, 126.079102);
        assertThat(sut.getEasting()).isBetween(357588.0, 357589.0);
        assertThat(sut.getNorthing()).isBetween(7109395.0, 7109396.0);
        assertThat(sut.getZone()).isEqualTo(52);
        assertThat(sut.getLetter()).isEqualTo('W');
    }

    @Test
    public void w53_test() {
        DegToUtm sut = new DegToUtm(64.024122, 132.055664);
        assertThat(sut.getEasting()).isBetween(356149.0, 356150.0);
        assertThat(sut.getNorthing()).isBetween(7103025.0, 7103026.0);
        assertThat(sut.getZone()).isEqualTo(53);
        assertThat(sut.getLetter()).isEqualTo('W');
    }

    @Test
    public void w54_test() {
        DegToUtm sut = new DegToUtm(64.129784, 138.427734);
        assertThat(sut.getEasting()).isBetween(374794.0, 374795.0);
        assertThat(sut.getNorthing()).isBetween(7114005.0, 7114006.0);
        assertThat(sut.getZone()).isEqualTo(54);
        assertThat(sut.getLetter()).isEqualTo('W');
    }

    @Test
    public void w55_test() {
        DegToUtm sut = new DegToUtm(64.033744, 144.887695);
        assertThat(sut.getEasting()).isBetween(396821.0, 396822.0);
        assertThat(sut.getNorthing()).isBetween(7102484.0, 7102485.0);
        assertThat(sut.getZone()).isEqualTo(55);
        assertThat(sut.getLetter()).isEqualTo('W');
    }

    @Test
    public void w56_test() {
        DegToUtm sut = new DegToUtm(64.014496, 150.073242);
        assertThat(sut.getEasting()).isBetween(356958.0, 356959.0);
        assertThat(sut.getNorthing()).isBetween(7101914.0, 7101915.0);
        assertThat(sut.getZone()).isEqualTo(56);
        assertThat(sut.getLetter()).isEqualTo('W');
    }

    @Test
    public void w57_test() {
        DegToUtm sut = new DegToUtm(64.717880, 160.004883);
        assertThat(sut.getEasting()).isBetween(547884.0, 547885.0);
        assertThat(sut.getNorthing()).isBetween(7177393.0, 7177394.0);
        assertThat(sut.getZone()).isEqualTo(57);
        assertThat(sut.getLetter()).isEqualTo('W');
    }

    @Test
    public void w58_test() {
        DegToUtm sut = new DegToUtm(64.586185, 166.025391);
        assertThat(sut.getEasting()).isBetween(549099.0, 549100.0);
        assertThat(sut.getNorthing()).isBetween(7162733.0, 7162734.0);
        assertThat(sut.getZone()).isEqualTo(58);
        assertThat(sut.getLetter()).isEqualTo('W');
    }

    @Test
    public void w59_test() {
        DegToUtm sut = new DegToUtm(64.717880, 172.661133);
        assertThat(sut.getEasting()).isBetween(579151.0, 579152.0);
        assertThat(sut.getNorthing()).isBetween(7178051.0, 7178052.0);
        assertThat(sut.getZone()).isEqualTo(59);
        assertThat(sut.getLetter()).isEqualTo('W');
    }

    @Test
    public void w60_test() {
        DegToUtm sut = new DegToUtm(64.886265, 177.451172);
        assertThat(sut.getEasting()).isBetween(521366.0, 521367.0);
        assertThat(sut.getNorthing()).isBetween(7195855.0, 7195856.0);
        assertThat(sut.getZone()).isEqualTo(60);
        assertThat(sut.getLetter()).isEqualTo('W');
    }

    @Test
    public void w61_test() {
        DegToUtm sut = new DegToUtm(66.843807, 182.856445);
        assertThat(sut.getEasting()).isBetween(493700.0, 493701.0);
        assertThat(sut.getNorthing()).isBetween(7413975.0, 7413976.0);
        assertThat(sut.getZone()).isEqualTo(61);
        assertThat(sut.getLetter()).isEqualTo('W');
    }

    @Test
    public void w62_test() {
        DegToUtm sut = new DegToUtm(65.603878, 188.129883);
        assertThat(sut.getEasting()).isBetween(459897.0, 459898.0);
        assertThat(sut.getNorthing()).isBetween(7276035.0, 7276036.0);
        assertThat(sut.getZone()).isEqualTo(62);
        assertThat(sut.getLetter()).isEqualTo('W');
    }

    @Test
    public void v34_test() {
        DegToUtm sut = new DegToUtm(56.897004, 22.236328);
        assertThat(sut.getEasting()).isBetween(575309.0, 575310.0);
        assertThat(sut.getNorthing()).isBetween(6306601.0, 6306602.0);
        assertThat(sut.getZone()).isEqualTo(34);
        assertThat(sut.getLetter()).isEqualTo('V');
    }

    @Test
    public void v35_test() {
        DegToUtm sut = new DegToUtm(59.310768, 28.916016);
        assertThat(sut.getEasting()).isBetween(609076.0, 609077.0);
        assertThat(sut.getNorthing()).isBetween(6576225.0, 6576226.0);
        assertThat(sut.getZone()).isEqualTo(35);
        assertThat(sut.getLetter()).isEqualTo('V');
    }

    @Test
    public void v36_test() {
        DegToUtm sut = new DegToUtm(57.350237, 34.584961);
        assertThat(sut.getEasting()).isBetween(595371.0, 595372.0);
        assertThat(sut.getNorthing()).isBetween(6357484.0, 6357485.0);
        assertThat(sut.getZone()).isEqualTo(36);
        assertThat(sut.getLetter()).isEqualTo('V');
    }

    @Test
    public void v37_test() {
        DegToUtm sut = new DegToUtm(57.762799, 39.836426);
        assertThat(sut.getEasting()).isBetween(549766.0, 549767.0);
        assertThat(sut.getNorthing()).isBetween(6402609.0, 6402610.0);
        assertThat(sut.getZone()).isEqualTo(37);
        assertThat(sut.getLetter()).isEqualTo('V');
    }

    @Test
    public void v38_test() {
        DegToUtm sut = new DegToUtm(56.255557, 43.879395);
        assertThat(sut.getEasting()).isBetween(430573.0, 430574.0);
        assertThat(sut.getNorthing()).isBetween(6235087.0, 6235088.0);
        assertThat(sut.getZone()).isEqualTo(38);
        assertThat(sut.getLetter()).isEqualTo('V');
    }

    @Test
    public void v39_test() {
        DegToUtm sut = new DegToUtm(56.788845, 53.063965);
        assertThat(sut.getEasting()).isBetween(626079.0, 626080.0);
        assertThat(sut.getNorthing()).isBetween(6295781.0, 6295782.0);
        assertThat(sut.getZone()).isEqualTo(39);
        assertThat(sut.getLetter()).isEqualTo('V');
    }

    @Test
    public void v40_test() {
        DegToUtm sut = new DegToUtm(58.031372, 56.403809);
        assertThat(sut.getEasting()).isBetween(464790.0, 464791.0);
        assertThat(sut.getNorthing()).isBetween(6432358.0, 6432359.0);
        assertThat(sut.getZone()).isEqualTo(40);
        assertThat(sut.getLetter()).isEqualTo('V');
    }

    @Test
    public void v41_test() {
        DegToUtm sut = new DegToUtm(56.897004, 60.380859);
        assertThat(sut.getEasting()).isBetween(340475.0, 340476.0);
        assertThat(sut.getNorthing()).isBetween(6308976.0, 6308977.0);
        assertThat(sut.getZone()).isEqualTo(41);
        assertThat(sut.getLetter()).isEqualTo('V');
    }

    @Test
    public void v42_test() {
        DegToUtm sut = new DegToUtm(58.193871, 68.225098);
        assertThat(sut.getEasting()).isBetween(454444.0, 454445.0);
        assertThat(sut.getNorthing()).isBetween(6450556.0, 6450557.0);
        assertThat(sut.getZone()).isEqualTo(42);
        assertThat(sut.getLetter()).isEqualTo('V');
    }

    @Test
    public void v43_test() {
        DegToUtm sut = new DegToUtm(61.068917, 73.256836);
        assertThat(sut.getEasting()).isBetween(405931.0, 405932.0);
        assertThat(sut.getNorthing()).isBetween(6771716.0, 6771717.0);
        assertThat(sut.getZone()).isEqualTo(43);
        assertThat(sut.getLetter()).isEqualTo('V');
    }

    @Test
    public void v44_test() {
        DegToUtm sut = new DegToUtm(58.344101, 81.716309);
        assertThat(sut.getEasting()).isBetween(541933.0, 541934.0);
        assertThat(sut.getNorthing()).isBetween(6467244.0, 6467245.0);
        assertThat(sut.getZone()).isEqualTo(44);
        assertThat(sut.getLetter()).isEqualTo('V');
    }

    @Test
    public void v45_test() {
        DegToUtm sut = new DegToUtm(56.498892, 84.924316);
        assertThat(sut.getEasting()).isBetween(372227.0, 372228.0);
        assertThat(sut.getNorthing()).isBetween(6263537.0, 6263538.0);
        assertThat(sut.getZone()).isEqualTo(45);
        assertThat(sut.getLetter()).isEqualTo('V');
    }

    @Test
    public void v46_test() {
        DegToUtm sut = new DegToUtm(56.108810, 93.098145);
        assertThat(sut.getEasting()).isBetween(506103.0, 506104.0);
        assertThat(sut.getNorthing()).isBetween(6218194.0, 6218195.0);
        assertThat(sut.getZone()).isEqualTo(46);
        assertThat(sut.getLetter()).isEqualTo('V');
    }

    @Test
    public void v47_test() {
        DegToUtm sut = new DegToUtm(56.182254, 101.667480);
        assertThat(sut.getEasting()).isBetween(665558.0, 665559.0);
        assertThat(sut.getNorthing()).isBetween(6229567.0, 6229568.0);
        assertThat(sut.getZone()).isEqualTo(47);
        assertThat(sut.getLetter()).isEqualTo('V');
    }

    @Test
    public void v48_test() {
        DegToUtm sut = new DegToUtm(57.088515, 105.380859);
        assertThat(sut.getEasting()).isBetween(523081.0, 523082.0);
        assertThat(sut.getNorthing()).isBetween(6327303.0, 6327304.0);
        assertThat(sut.getZone()).isEqualTo(48);
        assertThat(sut.getLetter()).isEqualTo('V');
    }

    @Test
    public void v49_test() {
        DegToUtm sut = new DegToUtm(56.072035, 113.994141);
        assertThat(sut.getEasting()).isBetween(686358.0, 686359.0);
        assertThat(sut.getNorthing()).isBetween(6218138.0, 6218139.0);
        assertThat(sut.getZone()).isEqualTo(49);
        assertThat(sut.getLetter()).isEqualTo('V');
    }

    @Test
    public void v50_test() {
        DegToUtm sut = new DegToUtm(56.920997, 118.652344);
        assertThat(sut.getEasting()).isBetween(600583.0, 600584.0);
        assertThat(sut.getNorthing()).isBetween(6309807.0, 6309808.0);
        assertThat(sut.getZone()).isEqualTo(50);
        assertThat(sut.getLetter()).isEqualTo('V');
    }

    @Test
    public void v51_test() {
        DegToUtm sut = new DegToUtm(56.860986, 120.476074);
        assertThat(sut.getEasting()).isBetween(346125.0, 346126.0);
        assertThat(sut.getNorthing()).isBetween(6304750.0, 6304751.0);
        assertThat(sut.getZone()).isEqualTo(51);
        assertThat(sut.getLetter()).isEqualTo('V');
    }

    @Test
    public void v52_test() {
        DegToUtm sut = new DegToUtm(58.716894, 127.155762);
        assertThat(sut.getEasting()).isBetween(393184.0, 393185.0);
        assertThat(sut.getNorthing()).isBetween(6509998.0, 6509999.0);
        assertThat(sut.getZone()).isEqualTo(52);
        assertThat(sut.getLetter()).isEqualTo('V');
    }

    @Test
    public void v53_test() {
        DegToUtm sut = new DegToUtm(60.909073, 135.131836);
        assertThat(sut.getEasting()).isBetween(507150.0, 507151.0);
        assertThat(sut.getNorthing()).isBetween(6752666.0, 6752667.0);
        assertThat(sut.getZone()).isEqualTo(53);
        assertThat(sut.getLetter()).isEqualTo('V');
    }

    @Test
    public void v54_test() {
        DegToUtm sut = new DegToUtm(60.673179, 141.811523);
        assertThat(sut.getEasting()).isBetween(544341.0, 544342.0);
        assertThat(sut.getNorthing()).isBetween(6726659.0, 6726660.0);
        assertThat(sut.getZone()).isEqualTo(54);
        assertThat(sut.getLetter()).isEqualTo('V');
    }

    @Test
    public void v55_test() {
        DegToUtm sut = new DegToUtm(62.532594, 146.997070);
        assertThat(sut.getEasting()).isBetween(499849.0, 499850.0);
        assertThat(sut.getNorthing()).isBetween(6933513.0, 6933514.0);
        assertThat(sut.getZone()).isEqualTo(55);
        assertThat(sut.getLetter()).isEqualTo('V');
    }

    @Test
    public void v56_test() {
        DegToUtm sut = new DegToUtm(62.754726, 152.753906);
        assertThat(sut.getEasting()).isBetween(487430.0, 487431.0);
        assertThat(sut.getNorthing()).isBetween(6958285.0, 6958286.0);
        assertThat(sut.getZone()).isEqualTo(56);
        assertThat(sut.getLetter()).isEqualTo('V');
    }

    @Test
    public void v57_test() {
        DegToUtm sut = new DegToUtm(62.995158, 159.960938);
        assertThat(sut.getEasting()).isBetween(548680.0, 548681.0);
        assertThat(sut.getNorthing()).isBetween(6985413.0, 6985414.0);
        assertThat(sut.getZone()).isEqualTo(57);
        assertThat(sut.getLetter()).isEqualTo('V');
    }

    @Test
    public void v58_test() {
        DegToUtm sut = new DegToUtm(62.512318, 166.596680);
        assertThat(sut.getEasting()).isBetween(582215.0, 582216.0);
        assertThat(sut.getNorthing()).isBetween(6932271.0, 6932272.0);
        assertThat(sut.getZone()).isEqualTo(58);
        assertThat(sut.getLetter()).isEqualTo('V');
    }

    @Test
    public void v59_test() {
        DegToUtm sut = new DegToUtm(61.627286, 170.991211);
        assertThat(sut.getEasting()).isBetween(499534.0, 499535.0);
        assertThat(sut.getNorthing()).isBetween(6832660.0, 6832661.0);
        assertThat(sut.getZone()).isEqualTo(59);
        assertThat(sut.getLetter()).isEqualTo('V');
    }

    @Test
    public void v60_test() {
        DegToUtm sut = new DegToUtm(63.134503, 177.583008);
        assertThat(sut.getEasting()).isBetween(529394.0, 529395.0);
        assertThat(sut.getNorthing()).isBetween(7000708.0, 7000709.0);
        assertThat(sut.getZone()).isEqualTo(60);
        assertThat(sut.getLetter()).isEqualTo('V');
    }

    @Test
    public void u35_test() {
        DegToUtm sut = new DegToUtm(52.214339, 24.697266);
        assertThat(sut.getEasting()).isBetween(342682.0, 342683.0);
        assertThat(sut.getNorthing()).isBetween(5787377.0, 5787378.0);
        assertThat(sut.getZone()).isEqualTo(35);
        assertThat(sut.getLetter()).isEqualTo('U');
    }

    @Test
    public void u36_test() {
        DegToUtm sut = new DegToUtm(52.643063, 33.420410);
        assertThat(sut.getEasting()).isBetween(528445.0, 528446.0);
        assertThat(sut.getNorthing()).isBetween(5832648.0, 5832649.0);
        assertThat(sut.getZone()).isEqualTo(36);
        assertThat(sut.getLetter()).isEqualTo('U');
    }

    @Test
    public void u37_test() {
        DegToUtm sut = new DegToUtm(51.713416, 39.177246);
        assertThat(sut.getEasting()).isBetween(512245.0, 512246.0);
        assertThat(sut.getNorthing()).isBetween(5729179.0, 5729180.0);
        assertThat(sut.getZone()).isEqualTo(37);
        assertThat(sut.getLetter()).isEqualTo('U');
    }

    @Test
    public void u38_test() {
        DegToUtm sut = new DegToUtm(54.188155, 45.175781);
        assertThat(sut.getEasting()).isBetween(511470.0, 511471.0);
        assertThat(sut.getNorthing()).isBetween(6004470.0, 6004471.0);
        assertThat(sut.getZone()).isEqualTo(38);
        assertThat(sut.getLetter()).isEqualTo('U');
    }

    @Test
    public void u39_test() {
        DegToUtm sut = new DegToUtm(54.278055, 48.339844);
        assertThat(sut.getEasting()).isBetween(326811.0, 326812.0);
        assertThat(sut.getNorthing()).isBetween(6017723.0, 6017724.0);
        assertThat(sut.getZone()).isEqualTo(39);
        assertThat(sut.getLetter()).isEqualTo('U');
    }

    @Test
    public void u40_test() {
        DegToUtm sut = new DegToUtm(52.696361, 56.118164);
        assertThat(sut.getEasting()).isBetween(440405.0, 440406.0);
        assertThat(sut.getNorthing()).isBetween(5838858.0, 5838859.0);
        assertThat(sut.getZone()).isEqualTo(40);
        assertThat(sut.getLetter()).isEqualTo('U');
    }

    @Test
    public void u41_test() {
        DegToUtm sut = new DegToUtm(55.103516, 61.325684);
        assertThat(sut.getEasting()).isBetween(393177.0, 393178.0);
        assertThat(sut.getNorthing()).isBetween(6107590.0, 6107591.0);
        assertThat(sut.getZone()).isEqualTo(41);
        assertThat(sut.getLetter()).isEqualTo('U');
    }

    @Test
    public void u42_test() {
        DegToUtm sut = new DegToUtm(55.590763, 68.664551);
        assertThat(sut.getEasting()).isBetween(478858.0, 478859.0);
        assertThat(sut.getNorthing()).isBetween(6160585.0, 6160586.0);
        assertThat(sut.getZone()).isEqualTo(42);
        assertThat(sut.getLetter()).isEqualTo('U');
    }

    @Test
    public void u43_test() {
        DegToUtm sut = new DegToUtm(54.851315, 73.410645);
        assertThat(sut.getEasting()).isBetween(397960.0, 397961.0);
        assertThat(sut.getNorthing()).isBetween(6079403.0, 6079404.0);
        assertThat(sut.getZone()).isEqualTo(43);
        assertThat(sut.getLetter()).isEqualTo('U');
    }

    @Test
    public void u44_test() {
        DegToUtm sut = new DegToUtm(53.488046, 83.759766);
        assertThat(sut.getEasting()).isBetween(683093.0, 683094.0);
        assertThat(sut.getNorthing()).isBetween(5930109.0, 5930110.0);
        assertThat(sut.getZone()).isEqualTo(44);
        assertThat(sut.getLetter()).isEqualTo('U');
    }

    @Test
    public void u45_test() {
        DegToUtm sut = new DegToUtm(53.774689, 87.099609);
        assertThat(sut.getEasting()).isBetween(506564.0, 506565.0);
        assertThat(sut.getNorthing()).isBetween(5958458.0, 5958459.0);
        assertThat(sut.getZone()).isEqualTo(45);
        assertThat(sut.getLetter()).isEqualTo('U');
    }

    @Test
    public void u46_test() {
        DegToUtm sut = new DegToUtm(55.727110, 92.636719);
        assertThat(sut.getEasting()).isBetween(477183.0, 477184.0);
        assertThat(sut.getNorthing()).isBetween(6175768.0, 6175769.0);
        assertThat(sut.getZone()).isEqualTo(46);
        assertThat(sut.getLetter()).isEqualTo('U');
    }

    @Test
    public void u47_test() {
        DegToUtm sut = new DegToUtm(53.592505, 98.613281);
        assertThat(sut.getEasting()).isBetween(474403.0, 474404.0);
        assertThat(sut.getNorthing()).isBetween(5938254.0, 5938255.0);
        assertThat(sut.getZone()).isEqualTo(47);
        assertThat(sut.getLetter()).isEqualTo('U');
    }

    @Test
    public void u48_test() {
        DegToUtm sut = new DegToUtm(51.481383, 107.709961);
        assertThat(sut.getEasting()).isBetween(688166.0, 688167.0);
        assertThat(sut.getNorthing()).isBetween(5706841.0, 5706842.0);
        assertThat(sut.getZone()).isEqualTo(48);
        assertThat(sut.getLetter()).isEqualTo('U');
    }

    @Test
    public void u49_test() {
        DegToUtm sut = new DegToUtm(52.052490, 113.510742);
        assertThat(sut.getEasting()).isBetween(672148.0, 672149.0);
        assertThat(sut.getNorthing()).isBetween(5769851.0, 5769852.0);
        assertThat(sut.getZone()).isEqualTo(49);
        assertThat(sut.getLetter()).isEqualTo('U');
    }

    @Test
    public void u50_test() {
        DegToUtm sut = new DegToUtm(52.133488, 119.663086);
        assertThat(sut.getEasting()).isBetween(682261.0, 682262.0);
        assertThat(sut.getNorthing()).isBetween(5779230.0, 5779231.0);
        assertThat(sut.getZone()).isEqualTo(50);
        assertThat(sut.getLetter()).isEqualTo('U');
    }

    @Test
    public void u51_test() {
        DegToUtm sut = new DegToUtm(54.901882, 123.310547);
        assertThat(sut.getEasting()).isBetween(519913.0, 519914.0);
        assertThat(sut.getNorthing()).isBetween(6083917.0, 6083918.0);
        assertThat(sut.getZone()).isEqualTo(51);
        assertThat(sut.getLetter()).isEqualTo('U');
    }

    @Test
    public void u52_test() {
        DegToUtm sut = new DegToUtm(54.085173, 128.540039);
        assertThat(sut.getEasting()).isBetween(469911.0, 469912.0);
        assertThat(sut.getNorthing()).isBetween(5993095.0, 5993096.0);
        assertThat(sut.getZone()).isEqualTo(52);
        assertThat(sut.getLetter()).isEqualTo('U');
    }

    @Test
    public void u53_test() {
        DegToUtm sut = new DegToUtm(54.007769, 133.901367);
        assertThat(sut.getEasting()).isBetween(427999.0, 428000.0);
        assertThat(sut.getNorthing()).isBetween(5984944.0, 5984945.0);
        assertThat(sut.getZone()).isEqualTo(53);
        assertThat(sut.getLetter()).isEqualTo('U');
    }

    @Test
    public void u54_test() {
        DegToUtm sut = new DegToUtm(52.882391, 139.921875);
        assertThat(sut.getEasting()).isBetween(427451.0, 427452.0);
        assertThat(sut.getNorthing()).isBetween(5859731.0, 5859732.0);
        assertThat(sut.getZone()).isEqualTo(54);
        assertThat(sut.getLetter()).isEqualTo('U');
    }

    @Test
    public void u56_test() {
        DegToUtm sut = new DegToUtm(54.876607, 155.742188);
        assertThat(sut.getEasting()).isBetween(675928.0, 675929.0);
        assertThat(sut.getNorthing()).isBetween(6084505.0, 6084506.0);
        assertThat(sut.getZone()).isEqualTo(56);
        assertThat(sut.getLetter()).isEqualTo('U');
    }

    @Test
    public void u57_test() {
        DegToUtm sut = new DegToUtm(55.065787, 158.356934);
        assertThat(sut.getEasting()).isBetween(458931.0, 458932.0);
        assertThat(sut.getNorthing()).isBetween(6102301.0, 6102302.0);
        assertThat(sut.getZone()).isEqualTo(57);
        assertThat(sut.getLetter()).isEqualTo('U');
    }

    @Test
    public void u58_test() {
        DegToUtm sut = new DegToUtm(55.002826, 166.223145);
        assertThat(sut.getEasting()).isBetween(578235.0, 578236.0);
        assertThat(sut.getNorthing()).isBetween(6095790.0, 6095791.0);
        assertThat(sut.getZone()).isEqualTo(58);
        assertThat(sut.getLetter()).isEqualTo('U');
    }

    @Test
    public void t36_test() {
        DegToUtm sut = new DegToUtm(45.182037, 34.035645);
        assertThat(sut.getEasting()).isBetween(581365.0, 581366.0);
        assertThat(sut.getNorthing()).isBetween(5003694.0, 5003695.0);
        assertThat(sut.getZone()).isEqualTo(36);
        assertThat(sut.getLetter()).isEqualTo('T');
    }

    @Test
    public void t37_test() {
        DegToUtm sut = new DegToUtm(47.234490, 39.792480);
        assertThat(sut.getEasting()).isBetween(559984.0, 559985.0);
        assertThat(sut.getNorthing()).isBetween(5231527.0, 5231528.0);
        assertThat(sut.getZone()).isEqualTo(37);
        assertThat(sut.getLetter()).isEqualTo('T');
    }

    @Test
    public void t38_test() {
        DegToUtm sut = new DegToUtm(46.286224, 44.318848);
        assertThat(sut.getEasting()).isBetween(447529.0, 447530.0);
        assertThat(sut.getNorthing()).isBetween(5126075.0, 5126076.0);
        assertThat(sut.getZone()).isEqualTo(38);
        assertThat(sut.getLetter()).isEqualTo('T');
    }

    @Test
    public void t39_test() {
        DegToUtm sut = new DegToUtm(46.301406, 48.098145);
        assertThat(sut.getEasting()).isBetween(276530.0, 276531.0);
        assertThat(sut.getNorthing()).isBetween(5131629.0, 5131630.0);
        assertThat(sut.getZone()).isEqualTo(39);
        assertThat(sut.getLetter()).isEqualTo('T');
    }

    // ToDo: add test code for Kazakhstan and letter T

    @Test
    public void t52_test() {
        DegToUtm sut = new DegToUtm(43.826601, 131.550293);
        assertThat(sut.getEasting()).isBetween(705064.0, 705065.0);
        assertThat(sut.getNorthing()).isBetween(4855775.0, 4855776.0);
        assertThat(sut.getZone()).isEqualTo(52);
        assertThat(sut.getLetter()).isEqualTo('T');
    }

    @Test
    public void t53_test() {
        DegToUtm sut = new DegToUtm(43.659924, 133.571777);
        assertThat(sut.getEasting()).isBetween(384840.0, 384841.0);
        assertThat(sut.getNorthing()).isBetween(4835093.0, 4835094.0);
        assertThat(sut.getZone()).isEqualTo(53);
        assertThat(sut.getLetter()).isEqualTo('T');
    }

    @Test
    public void t54_test() {
        DegToUtm sut = new DegToUtm(46.769968, 142.558594);
        assertThat(sut.getEasting()).isBetween(618999.0, 619000.0);
        assertThat(sut.getNorthing()).isBetween(5180781.0, 5180782.0);
        assertThat(sut.getZone()).isEqualTo(54);
        assertThat(sut.getLetter()).isEqualTo('T');
    }
}
