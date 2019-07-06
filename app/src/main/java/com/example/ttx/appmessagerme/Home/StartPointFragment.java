package com.example.ttx.appmessagerme.Home;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ttx.appmessagerme.R;
import com.example.ttx.appmessagerme.databinding.FragmentStartPointBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class StartPointFragment extends Fragment {
    private FragmentStartPointBinding binding;
    private Context context;

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
                getActivity().onBackPressed();
            }
        });
        return binding.getRoot();
    }

}
