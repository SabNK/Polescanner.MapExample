package ru.polescanner.mapexample;

import org.junit.Test;

import static org.junit.Assert.*;

import com.google.android.gms.maps.model.LatLng;

import ru.polescanner.mapexample.domain.Pole;
import ru.polescanner.mapexample.domain.PoleType;
import ru.polescanner.mapexample.domain.Span;
import ru.polescanner.mapexample.service.ParallelLinesProvider;

import static org.assertj.core.api.Assertions.*;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
/*
    @Test
    public void ParallelLinesProviderTest() {
        Pole[] poles = factoryPoles();
        Span[] spans = factorySpans(poles);
        LatLng p1, p2;
        p1 = getLatLng(poles[0]);
        p2 = getLatLng(poles[1]);
        //double b1 = ParallelLinesProvider.bearingRadians(p1, p2);
        //double b2 = ParallelLinesProvider.bearingRadians(p2, p1);
        //ToDo Check - bearing is wrong smth
        //assertThat(b1).isCloseTo(0.3491, within(0.1));
        assertThat(b2).isCloseTo(0.3491 - Math.PI, within(0.1));
        ParallelLinesProvider plp = new ParallelLinesProvider(p2, p1, 10.0);
        assertThat(plp.bearingR).isCloseTo(0.3491, within(0.1));
        LatLng stepPlus = plp.stepAway(p2,50.0);
        assertThat(stepPlus.latitude).isCloseTo(56.47968, within(0.0005));
        assertThat(stepPlus.longitude).isCloseTo(36.01693, within(0.0002));
        LatLng stepMinus = plp.stepAway(p2,-50.0);
        assertThat(stepPlus.latitude).isCloseTo(56.47938, within(0.0002));
        assertThat(stepPlus.longitude).isCloseTo(36.01845, within(0.0002));

    }
    private LatLng getLatLng (Pole p){
        return new LatLng(p.getPoint().getCoordinates()[0], p.getPoint().getCoordinates()[1]);
    }

    private Pole[] factoryPoles(){
        Pole[] poles = new Pole[4];
        PoleType typeTension = PoleType.registerWOLayout("CON", PoleType.Material.CONCRETE,
                                                         true, 12);
        PoleType typeSuspension = PoleType.registerWOLayout("CON", PoleType.Material.CONCRETE,
                                                            false, 8);
        poles[0] = Pole.register("ОП_1", 8, typeTension,
                                 56.4768, 36.01585,3, true);
        poles[1] = Pole.register("ОП_2", 8, typeTension,
                                 56.47953, 36.01768,2, true);
        /*poles[2] = Pole.register("ОП_3", 8, typeSuspension,
                                 56.47814, 36.02208,2, true);
        poles[3] = Pole.register("ОП_4", 8, typeSuspension,
                                 56.4784, 36.02217,1, true);
        return poles;
    }
    private Span[] factorySpans(Pole[] poles){

        Span[] spans = new Span[3];
        spans[0] = Span.registerSpan(poles[0], poles[1], 2);
        /*spans[1] = Span.registerSpan(poles[1], poles[2], 2);
        spans[2] = Span.registerSpan(poles[2], poles[3], 2);
        return spans;
    }
*/
}