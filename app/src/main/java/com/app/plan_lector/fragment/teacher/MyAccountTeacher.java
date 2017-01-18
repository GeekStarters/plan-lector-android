package com.app.plan_lector.fragment.teacher;

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
public class MyAccountTeacher extends Fragment implements View.OnClickListener {
    Activity context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.account_teacher, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        context = super.getActivity();
        init();
    }



    private void init() {

        /*Declaracion de TextView a utilizar*/
        final ImageView teacher_image = (ImageView)context.findViewById(R.id.photo);
        final TextView teacher_username = (TextView) context.findViewById(R.id.teacher_username);
        final TextView teacher_first_name = (TextView) context.findViewById(R.id.teacher_first_name);
        final TextView teacher_last_name = (TextView) context.findViewById(R.id.teacher_last_name);
        final TextView teacher_birthdate = (TextView) context.findViewById(R.id.teacher_birthdate);
        final TextView teacher_country = (TextView) context.findViewById(R.id.teacher_state);
        final TextView teacher_state = (TextView) context.findViewById(R.id.teacher_country);

        /*currentUser contiene la informacion del usuario actual (logueado)*/
        ParseUser currentUser = ParseUser.getCurrentUser();
        String idUser = currentUser.getObjectId();

        /*Se obtinen los datos de la base (Parse)*/
       /* TipoDato variable = objetoParse.geString("Nombre_del_campo_en_BD")*/
        String username = currentUser.getString("username");
        teacher_username.setText(username);
        String nombres = currentUser.getString("first_name");
        teacher_first_name.setText(nombres);
        String apellidos = currentUser.getString("last_name");
        teacher_last_name.setText(apellidos);
        String fechaNacimiento = currentUser.getDate("birthdate").toLocaleString().substring(0,12);
        teacher_birthdate.setText(fechaNacimiento);
        final String pais_id = currentUser.getString("country_id");
        //teacher_country.setText(pais);
        final String ciudad_id = currentUser.getString("state_id");
        //teacher_state.setText(ciudad);
        if(currentUser.getParseFile("photo")!=null){
            if(currentUser.getParseFile("photo").getUrl()!=null){
                String url = currentUser.getParseFile("photo").getUrl();
                Picasso.with(context).load(url).fit().into(teacher_image);
            }else{
                Picasso.with(context).load(R.drawable.account_no).fit().into(teacher_image);
            }
        }else{
            Picasso.with(context).load(R.drawable.account_no).fit().into(teacher_image);
        }

        //final String level_id = currentUser.getString("level_id");
        //teacher_level.setText(level);




        final String[] pais = new String[1];
        final String[] ciudad = new String[1];

        /*Consulta a Parse para obtener datos de la Tabla Country*/
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Country");
        query.whereEqualTo("objectId",pais_id );
        query.getFirstInBackground(new GetCallback<ParseObject>() {

            public void done(ParseObject object, ParseException e) {
                if (object == null) {
                    Log.d("user_id", "The getFirst request failed.");
                } else {
                    pais[0] = object.getString("name");
                    teacher_country.setText(pais[0]);
                    Log.d("user_contry", pais[0]);
                }
            }
        });

        /*Consulta a Parse para obtener datos de la Tabla State*/
        ParseQuery<ParseObject> query_state = ParseQuery.getQuery("State");
        query_state.whereEqualTo("objectId",ciudad_id );
        query_state.getFirstInBackground(new GetCallback<ParseObject>() {

            public void done(ParseObject state_object, ParseException e) {
                if (state_object == null) {
                    Log.d("teacher_state", "The getFirst request failed.");
                } else {
                    ciudad[0] = state_object.getString("name");
                    teacher_state.setText(ciudad[0]);
                    Log.d("teacher_state",ciudad[0]);
                }
            }
        });

    }

    @Override
    public void onClick(View v) {

    }


}
