package com.example.ttx.appmessagerme.User;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ttx.appmessagerme.Driver.ComfirmFragment;
import com.example.ttx.appmessagerme.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowoderFragment extends Fragment {


    public ShowoderFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_showoder, container, false);
        Button textTitle =view.findViewById(R.id.btn_cf_all);
        textTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppCompatActivity)getContext()).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container,new ComfirmFragment())
                        .addToBackStack(null) //เก็บค่าก่อนหน้าเพื่อย้อนกลับ
                        .commit();
            }
        });
        Button button =view.findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            getFragmentManager().popBackStack();
            }
        });
        return view;
    }





}
