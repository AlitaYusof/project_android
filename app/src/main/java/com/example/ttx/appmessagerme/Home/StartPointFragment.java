package com.example.ttx.appmessagerme.Home;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.databinding.DataBindingUtil;
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
import com.example.ttx.appmessagerme.databinding.FragmentStartPointBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class StartPointFragment extends Fragment {
    private FragmentStartPointBinding binding;
    private Context context;
    private boolean valid;
    String showToast;
    MapView mMapView;
    private GoogleMap googleMap;
    public static final String LOG_TAG = "PlacePicker";
    private static final int LOC_REQ_CODE = 1;
    private static final int PLACE_PICKER_REQ_CODE = 2;

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
        mMapView.onResume(); // needed to get the map to display immediately
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                googleMap.setMyLocationEnabled(true);

                // For dropping a marker at a point on the Map
                LatLng sydney = new LatLng(-34, 151);
                googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            }
        });
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

        Startpoint startpoint = new Startpoint();

     

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
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
