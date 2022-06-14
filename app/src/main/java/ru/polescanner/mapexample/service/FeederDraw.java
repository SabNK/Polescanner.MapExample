package ru.polescanner.mapexample.service;

import com.google.android.gms.maps.model.LatLng;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Set;

import ru.polescanner.mapexample.domain.Feeder;
import ru.polescanner.mapexample.domain.Pole;
import ru.polescanner.mapexample.domain.Span;

public class FeederDraw {
    int color;
    Feeder feeder;
    Span[] spans;
    double offsetM; //в плюс - значит рисунок правее, в минус, значит левее. Оффсет - центр для рисунка.
    double gapM;
    int maxCount;

    public FeederDraw(int color, Feeder feeder, Span[] spans, double offsetM, double gapM) {
        this.color = color;
        this.feeder = feeder;
        this.spans = getOrderedSpans(spans);
        this.offsetM = offsetM;
        this.gapM = gapM;
        Optional<Span> span = Arrays.stream(spans).max(Comparator.comparing(Span::conductorCount));
        this.maxCount = span.get().conductorCount();
    }

    private Span[] getOrderedSpans(Span[] s) {
        LinkedList<Span> spanLL = new LinkedList<>();
        Set<Span> spanSet = new HashSet<Span>(Arrays.asList(s));
        /*while (spanSet.size() < spans.length)
            for (Span span : s) {
                if (span.isConnected(ss)) {
                    p1 = s.getOtherPole(p1);
                    poles.addFirst(p1);
                }
                if (s.isPoleIn(p2)) {
                    p1 = s.getOtherPole(p2);
                    poles.addLast(p2);
                }
            }
        return poles.toArray(new Pole[0]);

         */
        return null;
    }

    public Controller.ConductorDraw[] prepare(){
        Pole[] poles = getOrderedPolesFromSpans(spans);
        int[] count = Arrays.stream(getOrderedSpans(spans)).map(Span::conductorCount)
                .mapToInt(i->i).toArray();
        LatLng[] points = Arrays.stream(poles).map(MapUtils::getLatLng).toArray(LatLng[]::new);
        ParallelLinesProvider plp = new ParallelLinesProvider(gapM, points);
        return plp.provide(count).toArray(new Controller.ConductorDraw[0]);
    }

    private Pole[] getOrderedPolesFromSpans(Span ... spans) {
        //ToDo if Spans contains twice the same span this method crashes
        LinkedList<Pole> poles = new LinkedList<>();
        Pole p1 = spans[0].getPole1();
        Pole p2 = spans[0].getPole2();
        poles.addFirst(p1);
        poles.addLast(p2);
        Set<Span> spanSeen = new HashSet<>();
        while (spanSeen.size() < spans.length)
            for (Span s : spans) {
                if (s.isPoleIn(p1)) {
                    p1 = s.getOtherPole(p1);
                    poles.addFirst(p1);
                }
                if (s.isPoleIn(p2)) {
                    p1 = s.getOtherPole(p2);
                    poles.addLast(p2);
                }
            }
        return poles.toArray(new Pole[0]);
    }



}
