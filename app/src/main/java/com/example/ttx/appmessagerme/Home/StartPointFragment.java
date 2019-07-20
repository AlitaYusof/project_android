package com.example.ttx.appmessagerme.Home;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.databinding.DataBindingUtil;

import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ttx.appmessagerme.Model.Startpoint;
import com.example.ttx.appmessagerme.R;
import com.example.ttx.appmessagerme.Utility;
import com.example.ttx.appmessagerme.databinding.FragmentStartPointBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class StartPointFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener {
    private FragmentStartPointBinding binding;
    private Context context;
    private boolean valid;
    String showToast;
    MapView mMapView;
    private GoogleMap googleMap;
    public static final String LOG_TAG = "PlacePicker";
    private static final int LOC_REQ_CODE = 1;
    private static final int PLACE_PICKER_REQ_CODE = 2;
    private LocationListener locationListener;
    private double lat;
    private double lng;
    private LatLng coordinate;
    private FusedLocationProviderClient fusedLocationClient;


    public StartPointFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_start_point, container, false);
        context = binding.getRoot().getContext();

        mMapView = (MapView) binding.mapView;
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        mMapView.getMapAsync(this);

        fusedLocationClient = (FusedLocationProviderClient) LocationServices.getFusedLocationProviderClient(context);


        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valid()) {
                    getActivity().onBackPressed();
                } else {
                    Toast.makeText(context, showToast, Toast.LENGTH_SHORT).show();
                }
            }
        });

        getCurrentPlaceItems();

        return binding.getRoot();
    }

    private void getCurrentPlaceItems() {
        if (isLocationAccessPermitted()) {
            showPlacePicker();
        } else {
            requestLocationAccessPermission();
        }
    }

    private void requestLocationAccessPermission() {
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                LOC_REQ_CODE);
    }

    private void showPlacePicker() {
    }

    private boolean isLocationAccessPermitted() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }

    private boolean valid() {
        valid = true;


        return valid;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LOC_REQ_CODE) {
            if (resultCode == RESULT_OK) {
                showPlacePicker();
            }
        } else if (requestCode == PLACE_PICKER_REQ_CODE) {
            if (resultCode == RESULT_OK) {

                Toast.makeText(context, "PlacePicker Success", Toast.LENGTH_SHORT).show();
                Log.d(LOG_TAG, " => ");

            }
        }
    }


    @Override
    public void onMapReady(GoogleMap Map) {
        this.googleMap = Map;
        googleMap.setOnMapClickListener(this);
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        googleMap.setMyLocationEnabled(true);

        fusedLocationClient.getLastLocation().addOnSuccessListener((Activity) context, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());
//                    googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 12.0f));

                }
            }
        });
    }

    @Override
    public void onMapClick(LatLng latLng) {
        googleMap.clear();
        LatLng latLng1 = new LatLng(latLng.latitude, latLng.longitude);
        String nameTitle = Utility.getAddress(context, latLng.latitude, latLng.longitude);
        Startpoint startpoint = new Startpoint();
        startpoint.setNamepoit(nameTitle);
        startpoint.setLat(latLng1.latitude);
        startpoint.setLog(latLng1.longitude);
        googleMap.addMarker(new MarkerOptions().position(latLng1).title(nameTitle));

    }
}
