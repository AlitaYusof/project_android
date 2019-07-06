package com.example.ttx.appmessagerme;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ttx.appmessagerme.Home.HomeFragment;
import com.example.ttx.appmessagerme.User.ShowoderFragment;
import com.example.ttx.appmessagerme.databinding.ActivityMainBinding;

public class MainActivity extends  AppCompatActivity {

private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil. setContentView(this,R.layout.activity_main);

       this.getSupportFragmentManager()
               .beginTransaction()
               .replace(R.id.container,new HomeFragment()) //
               .commit();
    }

    public void actionToPost(View view ){

        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,new ShowoderFragment())
                .addToBackStack(null) //ยเก็บค่าก่อนหน้าเพื่อย้อนกลับ
                .commit();
    }

    public class RecycleViewAdapter extends RecyclerView.Adapter<ViewHoder>{
        MyRecyclerAdapter.Holder holder;
        ViewGroup parent;
        @NonNull
        @Override
        public ViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view =LayoutInflater.from(getApplicationContext()).inflate(R.layout.fragment_comfirm,parent,false);
            return new ViewHoder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHoder viewHoder, int i) {
            holder.textTitle.setText("CodeMobile");
        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
    public class ViewHoder extends RecyclerView.ViewHolder{
        TextView textTitle;
        TextView textDescription;
        TextView textView14;
        TextView textView10;
        TextView textView13;
        TextView textView11;
        TextView textView12;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            textTitle= (TextView) itemView.findViewById(R.id.item_listview_title);
            textDescription =(TextView) itemView.findViewById((R.id.item_listview_dec));
            textView14 = (TextView) itemView.findViewById((R.id.textView14));
            textView14 = (TextView) itemView.findViewById((R.id.textView13));
            textView14 = (TextView) itemView.findViewById((R.id.textView12));
            textView14 = (TextView) itemView.findViewById((R.id.textView10));
            textView14 = (TextView) itemView.findViewById((R.id.textView11));
        }
    }
    }
