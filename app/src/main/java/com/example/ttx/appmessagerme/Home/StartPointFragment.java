package com.example.ttx.appmessagerme.Home;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ttx.appmessagerme.Model.Startpoint;
import com.example.ttx.appmessagerme.R;
import com.example.ttx.appmessagerme.databinding.FragmentStartPointBinding;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class StartPointFragment extends Fragment {
    private FragmentStartPointBinding binding;
    private Context context;
    private boolean valid;
    String showToast;

    public static final String LOG_TAG = "PlacePicker";
    private static final int LOC_REQ_CODE = 1;
    private static final int PLACE_PICKER_REQ_CODE = 2;
    private Place place;

    public StartPointFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_start_point, container, false);
        context = binding.getRoot().getContext();

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
//        if (isLocationAccessPermitted()) {
        showPlacePicker();
//        } else {
//            requestLocationAccessPermission();
//        }
    }

    private void requestLocationAccessPermission() {
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                LOC_REQ_CODE);
    }

    private void showPlacePicker() {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(context), PLACE_PICKER_REQ_CODE);

        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    private boolean isLocationAccessPermitted() {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }

    private boolean valid() {
        valid = true;

        Startpoint startpoint = new Startpoint();

        String sNamePoint = binding.editNamestart.getText().toString();
        String sLat = binding.editLat.getText().toString();
        String sLong = binding.editLong.getText().toString();

        if (sNamePoint.isEmpty()) {
            valid = false;
            showToast = "sNamePoint.isEmpty";
        } else {
            startpoint.setNamepoit(sNamePoint);
        }
        if (sLat.isEmpty()) {
            valid = false;
            showToast = "sLat.isEmpty";
        } else {
            startpoint.setLat(Double.parseDouble(sLat));
        }
        if (sLong.isEmpty()) {
            valid = false;
            showToast = "sLong.isEmpty";
        } else {
            startpoint.setLog(Double.parseDouble(sLong));
        }


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
//                Place place = PlacePicker.getPlace(data, context);

                Toast.makeText(context, "PlacePicker Success", Toast.LENGTH_SHORT).show();
                Log.d(LOG_TAG, " => ");

            }
        }
    }
}
