package com.example.ttx.appmessagerme.Driver;


import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ttx.appmessagerme.MyRecyclerAdapter;
import com.example.ttx.appmessagerme.R;
import com.example.ttx.appmessagerme.databinding.FragmentComfirmBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmFragment extends Fragment {
    private FragmentComfirmBinding binding;
    private RecyclerView recyclerView;
    private Context context;
    String[] strings = {"A", "B", "C"}; //ข้อความอะไรก็ได้
    public ConfirmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_comfirm, container, false);
        context = binding.getRoot().getContext();
        recyclerView = binding.recyclerView;

        MyRecyclerAdapter adapter = new MyRecyclerAdapter();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        return binding.getRoot();


    }

}
