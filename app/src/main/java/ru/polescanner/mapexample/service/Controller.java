package ru.polescanner.mapexample.service;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Pair;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import java.util.Map;

import ru.polescanner.mapexample.R;
import ru.polescanner.mapexample.domain.Pole;
import ru.polescanner.mapexample.domain.PoleType;

public class Controller {
    private RepositoryPole poleRepo;
    private RepositorySpan spanRepo;

    public BitmapDescriptor getIcon(Pole p){
        Map<Pair<PoleType.Material, Boolean>, BitmapDescriptor> icons;
        BitmapDescriptor concrete_tension_below1kv_Icon;
        BitmapDescriptor concrete_suspension_below1kv_Icon;
        BitmapDescriptor steel_tension_below1kv_Icon;
        BitmapDescriptor steel_suspension_below1kv_Icon;
        BitmapDescriptor wood_tension_below1kv_Icon;
        BitmapDescriptor wood_suspension_below1kv_Icon;
        BitmapDescriptor markerIcon;

        //задаю иконки для линий - цвет - напряжение,
        // квадрат - бетон, круг - дерево, шестигранник - сталь
        //  пустой - промка, закрашенный - анкер
        int poleColor = Color.parseColor("#D81B60");
        concrete_tension_below1kv_Icon = vectorToBitmap(R.drawable.ic_square, poleColor);
        concrete_suspension_below1kv_Icon = vectorToBitmap(R.drawable.ic_square_outline,poleColor);
        //ContextCompat.getColor(getApplicationContext(),R.color.colorBelow1kv));
        steel_tension_below1kv_Icon = vectorToBitmap(R.drawable.ic_hexagon,poleColor);
        steel_suspension_below1kv_Icon = vectorToBitmap(R.drawable.ic_hexagon_outline,poleColor);
        wood_tension_below1kv_Icon = vectorToBitmap(R.drawable.ic_circle,poleColor);
        wood_suspension_below1kv_Icon = vectorToBitmap(R.drawable.ic_circle_outline,poleColor);
        PoleType pt = p.getPoleType();
        if (pt == null) return null;

        final String steelTension = PoleType.Material.STEEL.name() + true;

        switch (pt.material().name() + pt.isTension()) {

            case steelTension:
                markerIcon = steel_tension_below1kv_Icon;
                break;
            case "steel / suspension":
                markerIcon = steel_suspension_below1kv_Icon;
                break;
            case "concrete / tension":
                markerIcon = concrete_tension_below1kv_Icon;
                break;
            case "concrete / suspension":
                markerIcon = concrete_suspension_below1kv_Icon;
                break;
            case "wood / tension":
                markerIcon = wood_tension_below1kv_Icon;
                break;
            case "wood / suspension":
                markerIcon = wood_suspension_below1kv_Icon;
                break;
            default:
                markerIcon = wood_suspension_below1kv_Icon;
                break;

        }
        return markerIcon;
    }

    // этот метод я украл тут https://gist.github.com/nanopc/7b693607e673c4bcafed701b5d3f54ab
    private BitmapDescriptor vectorToBitmap(@DrawableRes int id, @ColorInt int color) {
        Drawable vectorDrawable = ResourcesCompat.getDrawable(getResources(), id, null);
        assert vectorDrawable != null;
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                                            vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        DrawableCompat.setTint(vectorDrawable, color);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}
