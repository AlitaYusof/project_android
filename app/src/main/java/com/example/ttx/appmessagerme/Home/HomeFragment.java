package com.example.ttx.appmessagerme.Home;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ttx.appmessagerme.Model.Endpoint;
import com.example.ttx.appmessagerme.Model.Startpoint;
import com.example.ttx.appmessagerme.R;
import com.example.ttx.appmessagerme.databinding.FragmentHomeBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private Context context;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        context = binding.getRoot().getContext();

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

        if (Startpoint.getNamepoit() != null) {
            binding.btnStartpoint.setText(Startpoint.getNamepoit());
        }

        if (Endpoint.getNamepoit() != null) {
            binding.btnEntpoin.setText(Endpoint.getNamepoit());
        }

        return binding.getRoot();
    }

}
