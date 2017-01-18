package com.app.plan_lector.fragment.student;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.plan_lector.R;
import com.app.plan_lector.activity.game.QuizActivity;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Gabriela Mejia on 30/10/2016.
 */
public class Games extends Fragment implements View.OnClickListener {

    String valor;
    Activity context;
    LinearLayout memory, quiz,soup,hang;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.games, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        context = super.getActivity();
        init();
    }



    private void init() {
        memory = (LinearLayout) context.findViewById(R.id.memory);
        quiz = (LinearLayout) context.findViewById(R.id.quiz);
        soup = (LinearLayout) context.findViewById(R.id.soup);
        hang = (LinearLayout)context.findViewById(R.id.hang);
        Bundle bundle = this.getArguments();
        valor =bundle.getString("RESUL");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Ebooks");
        query.whereEqualTo("objectId",valor);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if(object.getBoolean("memory")){
                    memory.setVisibility(View.VISIBLE);
                }
                if(object.getBoolean("hangman")){
                    hang.setVisibility(View.VISIBLE);
                }
                if(object.getBoolean("quiz")){
                    quiz.setVisibility(View.VISIBLE);
                }
                if(object.getBoolean("soup")){
                    soup.setVisibility(View.VISIBLE);
                }
            }
        });
        hang.setOnClickListener(this);
        soup.setOnClickListener(this);
        memory.setOnClickListener(this);
        quiz.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.memory:
                MemoryGame memoryG = new MemoryGame();
                Bundle b = new Bundle();
                b.putString("RESUL",valor);
                memoryG.setArguments(b);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.main_content, memoryG)
                        .commit();
                break;
            case R.id.quiz:
                startActivity(new Intent(context, QuizActivity.class).putExtra("RESUL",valor));
                break;
            case R.id.soup:
                break;
            case R.id.hang:
                break;
        }

    }


}
