package ru.polescanner.mapexample.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Pair;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.polescanner.mapexample.R;
import ru.polescanner.mapexample.domain.Pole;
import ru.polescanner.mapexample.domain.PoleType;
import ru.polescanner.mapexample.domain.Span;

public class Controller {
    private RepositoryPole poleRepo;
    private RepositorySpan spanRepo;
    private Context context;

    Controller (Context context){
        this.context = context;
        poleRepo = new RepositoryPole();
        spanRepo = new RepositorySpan();
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


    }
}
