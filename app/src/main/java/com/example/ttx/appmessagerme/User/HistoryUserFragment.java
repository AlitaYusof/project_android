package com.example.ttx.appmessagerme.User;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ttx.appmessagerme.R;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryUserFragment extends Fragment {


    public HistoryUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_user, container, false);
    }

}
