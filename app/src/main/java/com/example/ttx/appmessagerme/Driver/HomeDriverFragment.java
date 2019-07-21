package com.example.ttx.appmessagerme.Driver;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ttx.appmessagerme.R;
import com.example.ttx.appmessagerme.databinding.FragmentHomeDriverBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeDriverFragment extends Fragment {
    private FragmentHomeDriverBinding binding;
    private Context context;

    public HomeDriverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_driver, container, false);
        context = binding.getRoot().getContext();

        binding.btnReceivedWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppCompatActivity) context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new HistoryDriverFragment()) //
                        .addToBackStack(null)
                        .commit();
            }
        });

        binding.btnGetjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppCompatActivity) context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new ConfirmFragment()) //
                        .addToBackStack(null)
                        .commit();
            }
        });

        return binding.getRoot();
    }

}
