package com.app.plan_lector.fragment.student;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.app.plan_lector.R;
import com.app.plan_lector.activity.MemoryActivity43;
import com.app.plan_lector.activity.MemoryActivity54;
import com.app.plan_lector.activity.MemoryActivity65;
import com.app.plan_lector.activity.MemoryActivity66;

/**
 * Created by Gabriela Mejia on 30/10/2016.
 */
public class MemoryGame extends Fragment implements View.OnClickListener {
    Activity context;
    LinearLayout memory43, memory54,memory65,memory66;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.memory_game, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        context = super.getActivity();
        init();
    }



    private void init() {
        memory43 = (LinearLayout)context.findViewById(R.id.memory43);
        memory54 = (LinearLayout)context.findViewById(R.id.memory54);
        memory65 = (LinearLayout)context.findViewById(R.id.memory65);
        memory66 = (LinearLayout)context.findViewById(R.id.memory66);
        memory43.setOnClickListener(this);
        memory54.setOnClickListener(this);
        memory65.setOnClickListener(this);
        memory66.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.memory43:
                startActivity(new Intent(context, MemoryActivity43.class));
                break;
            case R.id.memory54:
                startActivity(new Intent(context, MemoryActivity54.class));
                break;
            case R.id.memory65:
                startActivity(new Intent(context, MemoryActivity65.class));
                break;
            case R.id.memory66:
                startActivity(new Intent(context, MemoryActivity66.class));
                break;
        }

    }


}
