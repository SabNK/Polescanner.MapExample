package ru.polescanner.mapexample.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.Pair;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.polescanner.mapexample.R;
import ru.polescanner.mapexample.domain.Pole;
import ru.polescanner.mapexample.domain.PoleType;
import ru.polescanner.mapexample.domain.Span;

public class Controller {
    private static final String TAG = "Controller";
    private RepositoryPole poleRepo;
    private RepositorySpan spanRepo;
    private Context context;


    public Controller (Context context){
        this.context = context;
        poleRepo = new RepositoryPole();
        spanRepo = new RepositorySpan();
        spanRepo.add(factorySpans());
    }

    public List<MarkerOptions> providePoleMarkers(){
        List<MarkerOptions> mos = new ArrayList<>();
        for (Pole p : poleRepo.getAll())
            mos.add(new MarkerOptions()
                    .position(getLatLng(p))
                    .title(p.getName())
                    .icon(getIcon(p))
                    .anchor((float)0.5, (float)0.5)
                    .draggable(true)
            );
        return mos;
    }

    public List<PolylineOptions> providePolyLines(){
        List<Pole> poles = poleRepo.getAll();
        for (Pole p : poles)
            if (p.getName().equals("ОП_2")) {
                Log.d(TAG, "Pole " + p.getName());
                return providePolyLines(p);
            }
        return null;
    }

    public List<PolylineOptions> providePolyLines(Pole pole){
        List<PolylineOptions> pos = new ArrayList<>();
        List<Span> spansOfPole = new ArrayList<>();
        for (Span s : spanRepo.getAll())
            if (s.isPoleIn(pole)) spansOfPole.add(s);
        Log.d(TAG, "Spans q-ty: " + spansOfPole.size());
        List<ConductorDraw> cds =
                getLinesToAttachConductors(16.0f, pole, spansOfPole.toArray(new Span[0]));
        Log.d(TAG, "ConductorDraws q-ty: " + cds.size());
        for (ConductorDraw cd : cds)
            pos.add(new PolylineOptions()
                        .add(cd.endA, cd.endB)
                        .width(cd.width)
                        .color(cd.color)
                        .pattern(cd.strikePattern)
            );
        return pos;
    }

    //TODO provide icons for a collection of Poles (maybe Map<Pole, BitmapDescriptor>)
    public BitmapDescriptor getIcon(Pole p) {
        Map<Pair<PoleType.Material, Boolean>, BitmapDescriptor> icons = new HashMap<>();
        int poleColor = Color.parseColor("#D81B60");
        icons.put(new Pair(PoleType.Material.CONCRETE, Boolean.TRUE),
                  toBitmap(context, R.drawable.ic_square, poleColor));
        icons.put(new Pair(PoleType.Material.CONCRETE, Boolean.FALSE),
                  toBitmap(context, R.drawable.ic_square_outline, poleColor));
        icons.put(new Pair(PoleType.Material.STEEL, Boolean.TRUE),
                  toBitmap(context, R.drawable.ic_hexagon, poleColor));
        icons.put(new Pair(PoleType.Material.STEEL, Boolean.FALSE),
                  toBitmap(context, R.drawable.ic_hexagon_outline, poleColor));
        icons.put(new Pair(PoleType.Material.WOOD, Boolean.TRUE),
                  toBitmap(context, R.drawable.ic_circle, poleColor));
        icons.put(new Pair(PoleType.Material.WOOD, Boolean.FALSE),
                  toBitmap(context, R.drawable.ic_circle_outline, poleColor));
        PoleType pt = p.getPoleType();
        return icons.get(new Pair(pt.material(), pt.isTension()));
    }

    // that method is from https://gist.github.com/nanopc/7b693607e673c4bcafed701b5d3f54ab
    private BitmapDescriptor toBitmap(Context context,
                                      @DrawableRes int id,
                                      @ColorInt int color) {
        Drawable drawable = ResourcesCompat.getDrawable(context.getResources(), id, null);
        assert drawable != null;
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                                            drawable.getIntrinsicHeight(),
                                            Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        DrawableCompat.setTint(drawable, color);
        drawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    public List<LatLng> getCanvasToAttachConductors(float zoom, Pole p, Span... s){
        LatLng center = new LatLng(p.getPoint().getCoordinates()[0],
                                   p.getPoint().getCoordinates()[1]);
        return null;
    }

    //ToDo This is a mock ) Complete the
    private double gapToTapM(float zoom){
        return 5.0;
    }

    public List<ConductorDraw> getLinesToAttachConductors(float zoom, Pole p, Span... s) {
        for (Span span : s) {
            if (!span.isPoleIn(p)) {
                Log.d(TAG, "Span without Pole getLinesToAttachConductor");
                return null;
            }
        }
        List<ConductorDraw> lines = new ArrayList<>();
        LatLng pole = getLatLng(p);
        double gapM = gapToTapM(zoom);
        for (Span span: s) {
            int count = span.conductorCount();
            Log.d(TAG, "Conductor Count: " + count);
            LatLng otherPole = getLatLng(span.getOtherPole(p));
            ParallelLinesProvider parLines = new ParallelLinesProvider(pole, otherPole, gapM);
            lines.addAll(parLines.provide(count));
        }
        return lines;
    }

    private LatLng getLatLng (Pole p){
        return new LatLng(p.getPoint().getCoordinates()[0], p.getPoint().getCoordinates()[1]);
    }

    public static class ConductorDraw {
        public final LatLng endA;
        public final LatLng endB;
        public final int color;
        public final int width;
        public final List<PatternItem> strikePattern;

        public ConductorDraw(LatLng endA, LatLng endB, int color,
                             int width, List<PatternItem> strikePattern) {
            this.endA = endA;
            this.endB = endB;
            this.color = color;
            this.width = width;
            this.strikePattern = strikePattern;
        }
    }

    public static class ParallelLinesProvider{
        private LatLng toPoint;
        private LatLng fromPoint;
        private double gapM;
        private int[] colorSet;
        private int width;
        private List<PatternItem> strikePattern;
        public double bearingR;

        public ParallelLinesProvider(LatLng toPoint, LatLng fromPoint,
                                     double gapM, int[] colorSet,
                                     int width, List<PatternItem> strikePattern){
            this.fromPoint = fromPoint;
            this.toPoint = toPoint;
            this.gapM = gapM;
            this.colorSet = colorSet;
            this.width = width;
            this.strikePattern = strikePattern;
            this.bearingR = bearingRadians(fromPoint, toPoint);
        }

        public ParallelLinesProvider(LatLng toPoint, LatLng fromPoint, double gapM){
            this(toPoint, fromPoint, gapM,
                 new int[]{Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.RED,
                          Color.YELLOW, Color.GRAY, Color.WHITE},
                 6, null);
        }

        public  List<ConductorDraw> provide(int count){
            List<ConductorDraw> lines = new ArrayList<>();
            LatLng[] sideLineEnds, leftSideLineEnds, rightSideLineEnds;
            Log.d(TAG, "count on the provide input: " + count);
            if (count%2 == 0) {
                Log.d(TAG, "count is really 2");
                leftSideLineEnds = new LatLng[]{stepAway(toPoint, -gapM/2),
                                                stepAway(fromPoint, -gapM/2)};
                rightSideLineEnds = new LatLng[]{stepAway(toPoint, gapM/2),
                                                 stepAway(fromPoint, gapM/2)};
                lines.add(line(leftSideLineEnds[0], leftSideLineEnds[1], 0));
                lines.add(line(rightSideLineEnds[0], rightSideLineEnds[1], 1));
            }
            else {
                sideLineEnds = new LatLng[]{toPoint, fromPoint};
                lines.add(line(sideLineEnds[0], sideLineEnds[1], 0));
                leftSideLineEnds = new LatLng[]{stepAwayLeft(sideLineEnds[0]),
                                                stepAwayLeft(sideLineEnds[1])};
                rightSideLineEnds = new LatLng[]{stepAwayRight(sideLineEnds[0]),
                                                 stepAwayRight(sideLineEnds[1])};
                lines.add(line(leftSideLineEnds[0], leftSideLineEnds[1], 1));
                lines.add(line(rightSideLineEnds[0], rightSideLineEnds[1], 2));
            }
            for (int i = 1; i < count/2; i++){
                leftSideLineEnds = new LatLng[]{stepAwayLeft(leftSideLineEnds[0]),
                                                stepAwayLeft(leftSideLineEnds[1])};
                rightSideLineEnds = new LatLng[]{stepAwayRight(rightSideLineEnds[0]),
                                                stepAwayRight(rightSideLineEnds[1])};
                lines.add(line(leftSideLineEnds[0], leftSideLineEnds[1], 2*i + count%2));
                lines.add(line(rightSideLineEnds[0], rightSideLineEnds[1], 2*i + 1 + count%2));
            }
            Log.d(TAG, "provider gives lines: " + lines.size());
            return lines;
        }

        private ConductorDraw line(LatLng endA, LatLng endB, int index){
            return new ConductorDraw(endA, endB, colorSet[index], width, strikePattern);
        }
        //ToDo Refactor to rename stepAwaySouth
        private LatLng stepAwayRight(LatLng point){
            return stepAway(point, gapM);
        }
        //ToDo Refactor to rename stepAwayNorth
        private LatLng stepAwayLeft(LatLng point){
            return stepAway(point, -gapM);
        }

        public LatLng stepAway(LatLng point, double gapM){
            //https://v-ipc.ru/guides/coord
            double degreeLngM = 111134.861111;
            double degreeLatM = Math.cos(Math.toRadians(point.latitude))*111321.377778;
            double latDeltaD = gapM*Math.sin(bearingR)/degreeLatM;
            double lngDeltaD = gapM*Math.cos(bearingR)/degreeLngM;
            return new LatLng(point.latitude - latDeltaD, point.longitude + lngDeltaD);
        }

        public static double bearingRadians(LatLng start, LatLng end){
            double sLatR = Math.toRadians(start.latitude);
            double eLatR = Math.toRadians(end.latitude);
            double lngDiff= Math.toRadians(end.longitude - start.longitude);
            double y = Math.sin(lngDiff)*Math.cos(eLatR);
            double x = Math.cos(sLatR)*Math.sin(eLatR)
                    - Math.sin(sLatR)*Math.cos(eLatR)*Math.cos(lngDiff);
            return Math.atan2(y, x);
        }
    }

    public Pole[] factoryPoles(){
        Pole[] poles = new Pole[4];
        PoleType typeTension = PoleType.registerWOLayout("CON", PoleType.Material.CONCRETE,
                                                         true, 12);
        PoleType typeSuspension = PoleType.registerWOLayout("CON", PoleType.Material.CONCRETE,
                                                            false, 8);
        poles[0] = Pole.register("ОП_1", 8, typeTension,
                                 56.47767, 36.02205,3, true);
        poles[1] = Pole.register("ОП_2", 8, typeTension,
                                 56.47792, 36.022,2, true);
        poles[2] = Pole.register("ОП_3", 8, typeSuspension,
                                 56.47814, 36.02208,2, true);
        poles[3] = Pole.register("ОП_4", 8, typeSuspension,
                                 56.4784, 36.02217,1, true);
        return poles;
    }
    public Span[] factorySpans(){
        Pole[] poles = factoryPoles();
        poleRepo.add(poles);
        Span[] spans = new Span[3];
        spans[0] = Span.registerSpan(poles[0], poles[1], 8);
        spans[1] = Span.registerSpan(poles[1], poles[2], 5);
        spans[2] = Span.registerSpan(poles[2], poles[3], 2);
        return spans;
    }

}
