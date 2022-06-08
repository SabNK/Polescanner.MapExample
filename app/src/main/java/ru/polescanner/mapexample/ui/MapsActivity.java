package ru.polescanner.mapexample.ui;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.polescanner.mapexample.R;
import ru.polescanner.mapexample.databinding.ActivityMapsBinding;
import ru.polescanner.mapexample.service.Controller;
import ru.polescanner.mapexample.service.RepositoryPole;
import ru.polescanner.mapexample.service.RepositorySpan;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final LatLng CENTER = new LatLng(-20, 130);
    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private Controller controller;
    private Polygon mutablePolygon;
    private Context context;

    private ArrayList<Marker> pole_markers = new ArrayList<>();
    private ArrayList<Polyline> conductor_lines = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        controller = new Controller(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Selino and move the camera
        LatLng selino = new LatLng(56.479277, 36.027601);
        LatLng pole1 = new LatLng(56.47792, 36.022);
        LatLng pisarevsky = new LatLng(56.008401, 37.843595);
        mMap.addMarker(new MarkerOptions().position(selino).title("Welcome to Selino!"));
        //mMap.addMarker(new MarkerOptions().position(selino).title("Welcome to Office!"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pole1, 21.0F));

        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pisarevsky, 19.0F));
        //mMap.setMapType(mMap.MAP_TYPE_SATELLITE);
        addMarkers(mMap, controller.providePoleMarkers());
        addLines(mMap, controller.providePolyLines());

    }

    private void addMarkers(GoogleMap map, List<MarkerOptions> markerOptions){
        for (MarkerOptions mo : markerOptions)
            pole_markers.add(map.addMarker(mo));
    }

    private void addLines(GoogleMap map, List<PolylineOptions> lineOptions){
        for (PolylineOptions po : lineOptions)
            conductor_lines.add(map.addPolyline(po));
    }

    private List<LatLng> createRectangle(LatLng center, double halfWidth, double halfHeight) {
        return Arrays.asList(new LatLng(center.latitude - halfHeight, center.longitude - halfWidth),
                             new LatLng(center.latitude - halfHeight, center.longitude + halfWidth),
                             new LatLng(center.latitude + halfHeight, center.longitude + halfWidth),
                             new LatLng(center.latitude + halfHeight, center.longitude - halfWidth),
                             new LatLng(center.latitude - halfHeight, center.longitude - halfWidth));
    }
}