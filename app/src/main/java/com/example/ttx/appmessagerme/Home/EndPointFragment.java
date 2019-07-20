package com.example.ttx.appmessagerme.Home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.Location;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.ttx.appmessagerme.Model.Endpoint;
import com.example.ttx.appmessagerme.R;
import com.example.ttx.appmessagerme.Utility;
import com.example.ttx.appmessagerme.databinding.FragmentEndPointBinding;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.example.ttx.appmessagerme.Home.StartPointFragment.LOG_TAG;

public class EndPointFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleApiClient.OnConnectionFailedListener {
    private FragmentEndPointBinding binding;
    private Context context;
    private boolean valid;

    private Location mLastKnownLocation;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private GoogleMap mMap;
    private LocationCallback locationCallback;
    private float DEFAULT_ZOOM = 15;
    private MapView mMapView;
    private int container_height;
    private final static int PLACE_PICKER_REQUEST = 1;

    private GoogleApiClient mGoogleApiClient;

    public EndPointFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_end_point, container, false);
        context = binding.getRoot().getContext();

        mMapView = (MapView) binding.mapView;
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        mGoogleApiClient = new GoogleApiClient
                .Builder(context)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(getActivity(), this)
                .build();
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent intent = builder.build(getActivity());
                    startActivityForResult(intent, PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

//                getActivity().onBackPressed();

            }
        });
        return binding.getRoot();
    }
    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }
    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Place place = PlacePicker.getPlace(context, data);
            Toast.makeText(context, place.getName(), Toast.LENGTH_LONG).show();
        }  else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(context, "No place selected", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mMap.setMyLocationEnabled(true);
        mMap.setOnMapClickListener(this);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        mMap.clear();
        LatLng latLng1 = new LatLng(latLng.latitude, latLng.longitude);
        String nameTitle = Utility.getAddress(context, latLng.latitude, latLng.longitude);
        Endpoint startpoint = new Endpoint();
        startpoint.setNamepoit(nameTitle);
        startpoint.setLat(latLng1.latitude);
        startpoint.setLog(latLng1.longitude);
        mMap.addMarker(new MarkerOptions().position(latLng1).title(nameTitle));
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Snackbar.make(binding.getRoot(), connectionResult.getErrorMessage() + "", Snackbar.LENGTH_LONG).show();
    }
}
