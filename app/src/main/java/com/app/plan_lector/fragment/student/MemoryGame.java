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
import com.app.plan_lector.activity.game.MemoryActivity43;
import com.app.plan_lector.activity.game.MemoryActivity54;
import com.app.plan_lector.activity.game.MemoryActivity65;
import com.app.plan_lector.activity.game.MemoryActivity66;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Gabriela Mejia on 30/10/2016.
 */
public class MemoryGame extends Fragment implements View.OnClickListener {
    Activity context;
    String valor;
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
        Bundle bundle = this.getArguments();
        valor =bundle.getString("RESUL");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Memory");
        query.whereEqualTo("ebook",valor);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                valor = object.getObjectId();
                switch (object.getInt("levels")){
                    case 1:
                        memory43.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        memory43.setVisibility(View.VISIBLE);
                        memory54.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        memory43.setVisibility(View.VISIBLE);
                        memory54.setVisibility(View.VISIBLE);
                        memory65.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        memory43.setVisibility(View.VISIBLE);
                        memory54.setVisibility(View.VISIBLE);
                        memory65.setVisibility(View.VISIBLE);
                        memory66.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
        memory43.setOnClickListener(this);
        memory54.setOnClickListener(this);
        memory65.setOnClickListener(this);
        memory66.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.memory43:
                startActivity(new Intent(context, MemoryActivity43.class).putExtra("valor",valor));
                break;
            case R.id.memory54:
                startActivity(new Intent(context, MemoryActivity54.class).putExtra("valor",valor));
                break;
            case R.id.memory65:
                startActivity(new Intent(context, MemoryActivity65.class).putExtra("valor",valor));
                break;
            case R.id.memory66:
                startActivity(new Intent(context, MemoryActivity66.class).putExtra("valor",valor));
                break;
        }

    }


}
