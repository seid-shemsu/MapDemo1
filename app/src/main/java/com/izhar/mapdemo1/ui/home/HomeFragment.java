package com.izhar.mapdemo1.ui.home;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.izhar.mapdemo1.R;
import com.izhar.mapdemo1.model.Places;
import com.izhar.mapdemo1.objects.Place;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements OnMapReadyCallback {
    MapView mapView;
    GoogleMap map;
    AutoCompleteTextView auto;
    List<Place> placeList;
    List<String> names;
    Places places;
    private Button search;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        mapView = root.findViewById(R.id.map);
        mapView.getMapAsync(this);
        mapView.onCreate(savedInstanceState);
        String a = "5.8";
        places = new Places(getContext());
        placeList = places.getPlaces();
        names = new ArrayList<>();
        for (Place place : placeList)
            names.add(place.getName());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, names);
        auto = root.findViewById(R.id.auto);
        auto.setAdapter(adapter);
        search = root.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchPlace();
            }
        });
        return root;
    }

    private void searchPlace() {
        LatLng here = null;
        for (Place place : placeList)
            if (place.getName().equalsIgnoreCase(auto.getText().toString()))
                here = new LatLng(Double.parseDouble(place.getLatitude()), Double.parseDouble(place.getLongtude()));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(18);
        map.animateCamera(zoom);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(here, 18));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    getActivity(),
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    }, 1001);

        }
        //googleMap.setMyLocationEnabled(true);
        LatLng adama = new LatLng(8.541389, 39.268889);
        map.addMarker(new MarkerOptions()
                .position(adama)
                .title("adama"));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(13);
        map.animateCamera(zoom);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(adama, 13));
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