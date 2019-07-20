package com.example.ttx.appmessagerme.Home;

import android.content.Context;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ttx.appmessagerme.Model.Endpoint;
import com.example.ttx.appmessagerme.R;
import com.example.ttx.appmessagerme.databinding.FragmentEndPointBinding;

public class EndPointFragment extends Fragment {
    private FragmentEndPointBinding binding;
    private Context context;
    private boolean valid;
    private String showToast;

    public EndPointFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_end_point, container, false);
        context = binding.getRoot().getContext();
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Valid()) {
                    getActivity().onBackPressed();
                }else {
                    Toast.makeText(context, showToast, Toast.LENGTH_SHORT).show();
                }
            }
        });
        return binding.getRoot();
    }

    private boolean Valid() {
        valid = true;

        Endpoint endpoint = new Endpoint();

        String sNamePoint = binding.editNameent.getText().toString();
        String sLat = binding.editLat.getText().toString();
        String sLong = binding.editLong.getText().toString();

        if (sNamePoint.isEmpty()) {
            valid = false;
            showToast = "sNamePoint.isEmpty";
        } else {
            endpoint.setNamepoit(sNamePoint);
        }
        if (sLat.isEmpty()) {
            valid = false;
            showToast = "sLat.isEmpty";
        } else {
            endpoint.setLat(Double.parseDouble(sLat));
        }
        if (sLong.isEmpty()) {
            valid = false;
            showToast = "sLong.isEmpty";
        } else {
            endpoint.setLog(Double.parseDouble(sLong));
        }

        return valid;
    }

}
