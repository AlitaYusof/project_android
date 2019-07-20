package com.example.ttx.appmessagerme.Home;


import android.content.Context;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ttx.appmessagerme.APIClient;
import com.example.ttx.appmessagerme.Model.Endpoint;
import com.example.ttx.appmessagerme.Model.Example;
import com.example.ttx.appmessagerme.Model.Startpoint;
import com.example.ttx.appmessagerme.R;
import com.example.ttx.appmessagerme.RetrofitMap;
import com.example.ttx.appmessagerme.databinding.FragmentHomeBinding;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.security.cert.CertificateEncodingException;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private Context context;
    private MarkerOptions origin;
    private MarkerOptions destination;
    private RetrofitMap service;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        context = binding.getRoot().getContext();
        try {
            service = APIClient.getClient(getResources()).create(RetrofitMap.class);
        } catch (CertificateEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        binding.btnStartpoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppCompatActivity) context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new StartPointFragment()) //
                        .addToBackStack(null)
                        .commit();
            }
        });

        binding.btnEntpoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppCompatActivity) context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new EndPointFragment()) //
                        .addToBackStack(null)
                        .commit();
            }
        });

        boolean valid = true;

        if (Startpoint.getNamepoit() != null) {
            valid = true;
            origin = new MarkerOptions().position(new LatLng(Startpoint.getLat(), Startpoint.getLog())).title(Startpoint.getNamepoit());
            binding.btnStartpoint.setText(Startpoint.getNamepoit());
        } else {
            valid = false;
        }

        if (Endpoint.getNamepoit() != null) {
            valid = true;
            destination = new MarkerOptions().position(new LatLng(Endpoint.getLat(), Endpoint.getLog())).title(Endpoint.getNamepoit());
            binding.btnEntpoin.setText(Endpoint.getNamepoit());
        } else {
            valid = false;
        }

        if (valid) {
            Call<Example> call = service.getDistance("metric",
                    origin.getPosition().latitude + "," + origin.getPosition().longitude,
                    destination.getPosition().latitude + "," + destination.getPosition().longitude,
                    "driving");

            call.enqueue(new Callback<Example>() {
                @Override
                public void onResponse(Call<Example> call, Response<Example> response) {
                    binding.textPrice.setText("ราคา " + response.body().routes.get(0).legs.get(0).distance.text + " บาท");
                }

                @Override
                public void onFailure(Call<Example> call, Throwable t) {

                }
            });
        }
        return binding.getRoot();
    }

}
