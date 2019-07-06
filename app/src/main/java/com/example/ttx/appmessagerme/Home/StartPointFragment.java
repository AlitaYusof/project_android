package com.example.ttx.appmessagerme.Home;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ttx.appmessagerme.Model.Startpoint;
import com.example.ttx.appmessagerme.R;
import com.example.ttx.appmessagerme.databinding.FragmentStartPointBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class StartPointFragment extends Fragment {
    private FragmentStartPointBinding binding;
    private Context context;
    private boolean valid;
    String showToast;

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
        return binding.getRoot();
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

}
