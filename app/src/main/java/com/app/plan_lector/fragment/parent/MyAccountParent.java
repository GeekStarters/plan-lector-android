package com.app.plan_lector.fragment.parent;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.plan_lector.R;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.squareup.picasso.Picasso;

/**
 * Created by Gabriela Mejia on 30/10/2016.
 */
public class MyAccountParent extends Fragment implements View.OnClickListener {
    Activity context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.account_parent, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        context = super.getActivity();
        init();
    }



    private void init() {
        final ImageView parent_photo = (ImageView)context.findViewById(R.id.photo);
        final TextView parent_username = (TextView) context.findViewById(R.id.parent_username);
        final TextView parent_first_name = (TextView) context.findViewById(R.id.parent_first_name);
        final TextView parent_last_name = (TextView) context.findViewById(R.id.parent_last_name);
        final TextView parent_birthdate = (TextView) context.findViewById(R.id.parent_birthdate);
        final TextView parent_country = (TextView) context.findViewById(R.id.parent_state);
        final TextView parent_state = (TextView) context.findViewById(R.id.parent_country);

        ParseUser currentUser = ParseUser.getCurrentUser();
        String idUser = currentUser.getObjectId();

        String username = currentUser.getString("username");
        parent_username.setText(username);
        String nombres = currentUser.getString("first_name");
        parent_first_name.setText(nombres);
        String apellidos = currentUser.getString("last_name");
        parent_last_name.setText(apellidos);
        String fechaNacimiento = currentUser.getDate("birthdate").toString();
        parent_birthdate.setText(fechaNacimiento);
        if(currentUser.getParseFile("photo")!=null){
            if(currentUser.getParseFile("photo").getUrl()!=null){
                String url = currentUser.getParseFile("photo").getUrl();
                Picasso.with(context).load(url).fit().into(parent_photo);
            }else{
                Picasso.with(context).load(R.drawable.account_no).fit().into(parent_photo);
            }
        }else{
            Picasso.with(context).load(R.drawable.account_no).fit().into(parent_photo);
        }
        final String pais_id = currentUser.getString("country_id");
        //parent_country.setText(pais);
        final String ciudad_id = currentUser.getString("state_id");
        //parent_state.setText(ciudad);



        final String[] pais = new String[1];
        final String[] ciudad = new String[1];


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Country");
        query.whereEqualTo("objectId",pais_id );
        query.getFirstInBackground(new GetCallback<ParseObject>() {

            public void done(ParseObject object, ParseException e) {
                if (object == null) {
                    Log.d("user_id", "The getFirst request failed.");
                } else {
                    pais[0] = object.getString("name");
                    parent_country.setText(pais[0]);
                    Log.d("user_contry", pais[0]);
                }
            }
        });

        ParseQuery<ParseObject> query_state = ParseQuery.getQuery("State");
        query_state.whereEqualTo("objectId",ciudad_id );
        query_state.getFirstInBackground(new GetCallback<ParseObject>() {

            public void done(ParseObject state_object, ParseException e) {
                if (state_object == null) {
                    Log.d("parent_state", "The getFirst request failed.");
                } else {
                    ciudad[0] = state_object.getString("name");
                    parent_state.setText(ciudad[0]);
                    Log.d("parent_state",ciudad[0]);
                }
            }
        });

    }

    @Override
    public void onClick(View v) {

    }


}
