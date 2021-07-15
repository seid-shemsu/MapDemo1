package com.izhar.mapdemo1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;
import com.izhar.mapdemo1.model.Places;
import com.izhar.mapdemo1.objects.Place;

import java.util.ArrayList;
import java.util.List;

public class Category extends AppCompatActivity implements OnMapReadyCallback {
    private MapView mapView;
    private GoogleMap map;
    private List<Place> placeList;
    private List<String> names;
    private Places places;
    private String category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        category = getIntent().getExtras().getString("category");
        setTitle(category + "s");
        mapView = findViewById(R.id.map);
        mapView.getMapAsync(this);
        mapView.onCreate(savedInstanceState);
        places = new Places(this);
        placeList = places.getPlaces();
        client = LocationServices.getFusedLocationProviderClient(this);
    }


    private FusedLocationProviderClient client;
    private double currentLat, currentLong;
    Location loc;
    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1200);
            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(location -> {
            if (location != null) {
                loc = location;
                currentLat = loc.getLatitude();
                currentLong = loc.getLongitude();
                LatLng you = new LatLng(currentLat, currentLong);
                map.addMarker(new MarkerOptions()
                        .position(you)
                        .title("you")
                        .icon(BitmapFromVector(this, R.drawable.you)));
                map.addCircle(new CircleOptions()
                        .center(you)
                        .radius(1000)
                        .strokeWidth(0.1f)
                        .fillColor(0x22339708)
                        .strokeColor(getResources().getColor(R.color.purple_700)));
                CameraUpdate zoom = CameraUpdateFactory.zoomTo(13);
                map.animateCamera(zoom);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(you, 13));
            }
            else{
                getCurrentLocation();
            }
        });
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setTrafficEnabled(true);
        LatLng you = new LatLng(8.541389, 39.268889);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(13);
        map.animateCamera(zoom);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(you, 13));
        map.clear();
        for (Place place : placeList){
            if (place.getCategory().equalsIgnoreCase(category))
            map.addMarker(
                    new MarkerOptions()
                            .title(place.getName())
                            .position(new LatLng(Double.parseDouble(place.getLatitude()), Double.parseDouble(place.getLongtude())))
                            .icon(BitmapFromVector(this, R.drawable.location))

            );
        }
        getCurrentLocation();
    }


    private BitmapDescriptor BitmapFromVector(Context context, int vectorResId) {
        // below line is use to generate a drawable.
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);

        // below line is use to set bounds to our vector drawable.
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());

        // below line is use to create a bitmap for our
        // drawable which we have added.
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        // below line is use to add bitmap in our canvas.
        Canvas canvas = new Canvas(bitmap);

        // below line is use to draw our
        // vector drawable in canvas.
        vectorDrawable.draw(canvas);

        // after generating our bitmap we are returning our bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }


    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

}