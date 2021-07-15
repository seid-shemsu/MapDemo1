package com.izhar.mapdemo1.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.izhar.mapdemo1.Category;
import com.izhar.mapdemo1.R;

public class GalleryFragment extends Fragment implements OnMapReadyCallback {
    private MapView mapView;
    private GoogleMap map;
    private TextView bank, hall, hosp, hot, uni, gas, stad, shop;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        mapView = root.findViewById(R.id.map);
        mapView.getMapAsync(this);
        mapView.onCreate(savedInstanceState);
        bank = root.findViewById(R.id.bank);
        hall = root.findViewById(R.id.hall);
        hosp = root.findViewById(R.id.hosp);
        hot = root.findViewById(R.id.hot);
        uni = root.findViewById(R.id.uni);
        gas = root.findViewById(R.id.gas);
        stad = root.findViewById(R.id.stad);
        shop = root.findViewById(R.id.shop);
        bank.setOnClickListener(v -> {
            getActivity().startActivity(new Intent(getContext(), Category.class).putExtra("category", "Bank"));
        });
        hall.setOnClickListener(v -> {
            getActivity().startActivity(new Intent(getContext(), Category.class).putExtra("category", "Hall"));
        });
        hosp.setOnClickListener(v -> {
            getActivity().startActivity(new Intent(getContext(), Category.class).putExtra("category", "Hospital"));
        });
        hot.setOnClickListener(v -> {
            getActivity().startActivity(new Intent(getContext(), Category.class).putExtra("category", "Hotel"));
        });
        uni.setOnClickListener(v -> {
            getActivity().startActivity(new Intent(getContext(), Category.class).putExtra("category", "University"));
        });
        gas.setOnClickListener(v -> {
            getActivity().startActivity(new Intent(getContext(), Category.class).putExtra("category", "Gas"));
        });
        stad.setOnClickListener(v -> {
            getActivity().startActivity(new Intent(getContext(), Category.class).putExtra("category", "Stadium"));
        });
        shop.setOnClickListener(v -> {
            getActivity().startActivity(new Intent(getContext(), Category.class).putExtra("category", "Market"));
        });
        return root;
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng you = new LatLng(8.541389, 39.268889);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
        map.animateCamera(zoom);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(you, 15));
        map.getUiSettings().setScrollGesturesEnabled(false);
        map.getUiSettings().setZoomGesturesEnabled(false);
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