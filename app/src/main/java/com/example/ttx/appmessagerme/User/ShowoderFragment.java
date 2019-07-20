package com.example.ttx.appmessagerme.User;


import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ttx.appmessagerme.Driver.ComfirmFragment;
import com.example.ttx.appmessagerme.R;
import com.example.ttx.appmessagerme.databinding.FragmentShowoderBinding;

import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowoderFragment extends Fragment {
    private FragmentShowoderBinding binding;

    public ShowoderFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_showoder, container, false);
        binding.btnCfAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppCompatActivity) getContext()).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new ComfirmFragment())
                        .addToBackStack(null) //เก็บค่าก่อนหน้าเพื่อย้อนกลับ
                        .commit();
            }
        });
        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        Date currentTime = Calendar.getInstance().getTime();

        binding.textDate.setText(""+currentTime.getHours()+":"+currentTime.getMinutes()+":"+currentTime.getSeconds());


        return binding.getRoot();
    }


}
