package com.app.plan_lector.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.plan_lector.R;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Gabriela Mejia on 30/10/2016.
 */
public class ResumenBook extends Fragment implements View.OnClickListener {
    Activity context;
    TextView title, resumen;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.resume_book, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        context = super.getActivity();
        init();
    }



    private void init() {
        Bundle bundle = this.getArguments();
        String valor =bundle.getString("RESUL");
        title = (TextView)context.findViewById(R.id.title);
        resumen = (TextView)context.findViewById(R.id.resumen);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Ebooks");
        query.whereEqualTo("objectId",valor);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                title.setText(object.getString("name"));
                resumen.setText(object.getString("description"));
            }
        });


    }

    @Override
    public void onClick(View v) {

    }


}
