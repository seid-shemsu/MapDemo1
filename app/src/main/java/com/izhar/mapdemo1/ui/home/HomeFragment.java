package com.izhar.mapdemo1.ui.home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.izhar.mapdemo1.R;
import com.izhar.mapdemo1.model.Places;
import com.izhar.mapdemo1.objects.Place;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends Fragment implements OnMapReadyCallback {
    private MapView mapView;
    private GoogleMap map;
    private AutoCompleteTextView auto;
    private List<Place> placeList;
    private List<String> names;
    private Places places;
    private Button search;
    private ImageView my_pos;
    private LatLngBounds ADAMA = new LatLngBounds(new LatLng(8.541389, 39.268889), new LatLng(8.541389, 39.268889));
    // Constrain the camera target to the Adelaide bounds.

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        mapView = root.findViewById(R.id.map);
        my_pos = root.findViewById(R.id.my_pos);
        my_pos.setOnClickListener(v -> {
            getCurrentLocation();
        });
        mapView.getMapAsync(this);
        mapView.onCreate(savedInstanceState);
        places = new Places(getContext());
        placeList = places.getPlaces();
        names = new ArrayList<>();
        for (Place place : placeList)
            names.add(place.getName());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, names);
        auto = root.findViewById(R.id.auto);
        auto.setAdapter(adapter);
        search = root.findViewById(R.id.search);
        search.setOnClickListener(v -> searchPlace());
        client = LocationServices.getFusedLocationProviderClient(getContext());
        return root;
    }

    private FusedLocationProviderClient client;
    private double currentLat, currentLong;
    Location loc;
    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1200);
            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(location -> {
            if (location != null) {
                loc = location;
                currentLat = loc.getLatitude();
                currentLong = loc.getLongitude();
                LatLng you = new LatLng(currentLat, currentLong);
                map.clear();
                map.addMarker(new MarkerOptions()
                        .position(you)
                        .title("you")
                        .icon(BitmapFromVector(getContext(), R.drawable.locc)));
                map.addCircle(new CircleOptions()
                        .center(you)
                        .radius(200)
                        .strokeWidth(0.1f)
                        .fillColor(0x22339708)
                        .strokeColor(getResources().getColor(R.color.purple_700)));
                CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
                map.animateCamera(zoom);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(you, 15));
            }
            else{
                getCurrentLocation();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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

    private void searchPlace() {
        LatLng here = null;
        for (Place place : placeList)
            if (place.getName().equalsIgnoreCase(auto.getText().toString()) || place.getCategory().equalsIgnoreCase(auto.getText().toString())){
                here = new LatLng(Double.parseDouble(place.getLatitude()), Double.parseDouble(place.getLongtude()));
                map.clear();
                map.addCircle(new CircleOptions()
                        .center(new LatLng(Double.parseDouble(place.getLatitude()), Double.parseDouble(place.getLongtude())))
                        .radius(75)
                        .strokeWidth(0.1f)
                        .fillColor(0x22339708)
                        .strokeColor(getResources().getColor(R.color.purple_700)));

            }
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(18);
        map.animateCamera(zoom);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(here, 18));

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng you = new LatLng(8.541389, 39.268889);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(13);
        map.animateCamera(zoom);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(you, 13));
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