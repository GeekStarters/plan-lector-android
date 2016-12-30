package com.app.plan_lector.fragment.teacher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.plan_lector.R;
import com.app.plan_lector.activity.student.ReaderActivity;
import com.app.plan_lector.model.LibroTexto;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gabriela Mejia on 29/10/2016.
 */
public class Library extends Fragment implements View.OnClickListener{

    Activity context;
    private ImageView image1, image2, image3,image4,image5,image6,image7,image8,image9,image10,image11,image12,image13,image14,image15;
    private List<String> libritos;
    private List<LibroTexto> loa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.library, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        context = super.getActivity();
        init();
    }



    private void init() {
        image1 = (ImageView) context.findViewById(R.id.imageView);
        image2 = (ImageView) context.findViewById(R.id.imageView2);
        image3 = (ImageView) context.findViewById(R.id.imageView3);
        image4 = (ImageView) context.findViewById(R.id.imageView4);
        image5 = (ImageView) context.findViewById(R.id.imageView5);
        image6 = (ImageView) context.findViewById(R.id.imageView6);
        image7 = (ImageView) context.findViewById(R.id.imageView7);
        image8 = (ImageView) context.findViewById(R.id.imageView8);
        image9 = (ImageView) context.findViewById(R.id.imageView9);
        image10 = (ImageView) context.findViewById(R.id.imageView10);
        image11 = (ImageView) context.findViewById(R.id.imageView11);
        image12 = (ImageView) context.findViewById(R.id.imageView12);
        image13 = (ImageView) context.findViewById(R.id.imageView13);
        image14 = (ImageView) context.findViewById(R.id.imageView14);
        image15 = (ImageView) context.findViewById(R.id.imageView15);
        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);
        image4.setOnClickListener(this);
        image5.setOnClickListener(this);
        image6.setOnClickListener(this);
        image7.setOnClickListener(this);
        image8.setOnClickListener(this);
        image9.setOnClickListener(this);
        image10.setOnClickListener(this);
        image11.setOnClickListener(this);
        image12.setOnClickListener(this);
        image13.setOnClickListener(this);
        image14.setOnClickListener(this);
        image15.setOnClickListener(this);
        llenarLista();
    }

    private void llenarLista() {
        libritos = new ArrayList<>();
        loa = new ArrayList<>();
        ParseUser u = ParseUser.getCurrentUser();
        ParseQuery<ParseObject> q = ParseQuery.getQuery("Teachers");
        q.whereEqualTo("user_id",u.getObjectId());
        q.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                ParseQuery<ParseObject> query = ParseQuery.getQuery("TeacherEbook");
                query.whereEqualTo("teacher", object.getObjectId());
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> libros, ParseException ex) {
                        for (int j=0;j<libros.size();j++){
                            ParseQuery<ParseObject> con = ParseQuery.getQuery("Ebooks");
                            con.whereEqualTo("objectId",libros.get(j).getString("ebook"));
                            con.getFirstInBackground(new GetCallback<ParseObject>() {
                                @Override
                                public void done(ParseObject parseObject, ParseException e) {
                                    Log.e("url: ",parseObject.getParseFile("cover_book").getUrl());
                                    Log.e("id: ",parseObject.getObjectId());
                                    loa.add(new LibroTexto(parseObject.getParseFile("cover_book").getUrl(),parseObject.getObjectId()));
                                    loadImages(loa);
                                }

                            });



                        }

                    }
                });
                loadImages(loa);
            }
        });
    }

    public void loadImages(List<LibroTexto>loa){
        switch (loa.size()){
            case 1:
                Picasso.with(context).load(loa.get(0).getCover()).fit().into(image1);
                break;
            case 2:
                Picasso.with(context).load(loa.get(0).getCover()).fit().into(image1);
                Picasso.with(context).load(loa.get(1).getCover()).fit().into(image2);
                break;
            case 3:
                Picasso.with(context).load(loa.get(0).getCover()).fit().into(image1);
                Picasso.with(context).load(loa.get(1).getCover()).fit().into(image2);
                Picasso.with(context).load(loa.get(2).getCover()).fit().into(image3);
                break;
            case 4:
                Picasso.with(context).load(loa.get(0).getCover()).fit().into(image1);
                Picasso.with(context).load(loa.get(1).getCover()).fit().into(image2);
                Picasso.with(context).load(loa.get(2).getCover()).fit().into(image3);
                Picasso.with(context).load(loa.get(3).getCover()).fit().into(image4);
                break;
            case 5:
                Picasso.with(context).load(loa.get(0).getCover()).fit().into(image1);
                Picasso.with(context).load(loa.get(1).getCover()).fit().into(image2);
                Picasso.with(context).load(loa.get(2).getCover()).fit().into(image3);
                Picasso.with(context).load(loa.get(3).getCover()).fit().into(image4);
                Picasso.with(context).load(loa.get(4).getCover()).fit().into(image5);
                break;
            case 6:
                Picasso.with(context).load(loa.get(0).getCover()).fit().into(image1);
                Picasso.with(context).load(loa.get(1).getCover()).fit().into(image2);
                Picasso.with(context).load(loa.get(2).getCover()).fit().into(image3);
                Picasso.with(context).load(loa.get(3).getCover()).fit().into(image4);
                Picasso.with(context).load(loa.get(4).getCover()).fit().into(image5);
                Picasso.with(context).load(loa.get(5).getCover()).fit().into(image6);
                break;
            case 7:
                Picasso.with(context).load(loa.get(0).getCover()).fit().into(image1);
                Picasso.with(context).load(loa.get(1).getCover()).fit().into(image2);
                Picasso.with(context).load(loa.get(2).getCover()).fit().into(image3);
                Picasso.with(context).load(loa.get(3).getCover()).fit().into(image4);
                Picasso.with(context).load(loa.get(4).getCover()).fit().into(image5);
                Picasso.with(context).load(loa.get(5).getCover()).fit().into(image6);
                Picasso.with(context).load(loa.get(6).getCover()).fit().into(image7);
                break;
            case 8:
                Picasso.with(context).load(loa.get(0).getCover()).fit().into(image1);
                Picasso.with(context).load(loa.get(1).getCover()).fit().into(image2);
                Picasso.with(context).load(loa.get(2).getCover()).fit().into(image3);
                Picasso.with(context).load(loa.get(3).getCover()).fit().into(image4);
                Picasso.with(context).load(loa.get(4).getCover()).fit().into(image5);
                Picasso.with(context).load(loa.get(5).getCover()).fit().into(image6);
                Picasso.with(context).load(loa.get(6).getCover()).fit().into(image7);
                Picasso.with(context).load(loa.get(7).getCover()).fit().into(image8);
                break;
            case 9:
                Picasso.with(context).load(loa.get(0).getCover()).fit().into(image1);
                Picasso.with(context).load(loa.get(1).getCover()).fit().into(image2);
                Picasso.with(context).load(loa.get(2).getCover()).fit().into(image3);
                Picasso.with(context).load(loa.get(3).getCover()).fit().into(image4);
                Picasso.with(context).load(loa.get(4).getCover()).fit().into(image5);
                Picasso.with(context).load(loa.get(5).getCover()).fit().into(image6);
                Picasso.with(context).load(loa.get(6).getCover()).fit().into(image7);
                Picasso.with(context).load(loa.get(7).getCover()).fit().into(image8);
                Picasso.with(context).load(loa.get(8).getCover()).fit().into(image9);
                break;
            case 10:
                Picasso.with(context).load(loa.get(0).getCover()).fit().into(image1);
                Picasso.with(context).load(loa.get(1).getCover()).fit().into(image2);
                Picasso.with(context).load(loa.get(2).getCover()).fit().into(image3);
                Picasso.with(context).load(loa.get(3).getCover()).fit().into(image4);
                Picasso.with(context).load(loa.get(4).getCover()).fit().into(image5);
                Picasso.with(context).load(loa.get(5).getCover()).fit().into(image6);
                Picasso.with(context).load(loa.get(6).getCover()).fit().into(image7);
                Picasso.with(context).load(loa.get(7).getCover()).fit().into(image8);
                Picasso.with(context).load(loa.get(8).getCover()).fit().into(image9);
                Picasso.with(context).load(loa.get(9).getCover()).fit().into(image10);
                break;
            case 11:
                Picasso.with(context).load(loa.get(0).getCover()).fit().into(image1);
                Picasso.with(context).load(loa.get(1).getCover()).fit().into(image2);
                Picasso.with(context).load(loa.get(2).getCover()).fit().into(image3);
                Picasso.with(context).load(loa.get(3).getCover()).fit().into(image4);
                Picasso.with(context).load(loa.get(4).getCover()).fit().into(image5);
                Picasso.with(context).load(loa.get(5).getCover()).fit().into(image6);
                Picasso.with(context).load(loa.get(6).getCover()).fit().into(image7);
                Picasso.with(context).load(loa.get(7).getCover()).fit().into(image8);
                Picasso.with(context).load(loa.get(8).getCover()).fit().into(image9);
                Picasso.with(context).load(loa.get(9).getCover()).fit().into(image10);
                Picasso.with(context).load(loa.get(10).getCover()).fit().into(image11);
                break;
            case 12:
                Picasso.with(context).load(loa.get(0).getCover()).fit().into(image1);
                Picasso.with(context).load(loa.get(1).getCover()).fit().into(image2);
                Picasso.with(context).load(loa.get(2).getCover()).fit().into(image3);
                Picasso.with(context).load(loa.get(3).getCover()).fit().into(image4);
                Picasso.with(context).load(loa.get(4).getCover()).fit().into(image5);
                Picasso.with(context).load(loa.get(5).getCover()).fit().into(image6);
                Picasso.with(context).load(loa.get(6).getCover()).fit().into(image7);
                Picasso.with(context).load(loa.get(7).getCover()).fit().into(image8);
                Picasso.with(context).load(loa.get(8).getCover()).fit().into(image9);
                Picasso.with(context).load(loa.get(9).getCover()).fit().into(image10);
                Picasso.with(context).load(loa.get(10).getCover()).fit().into(image11);
                Picasso.with(context).load(loa.get(11).getCover()).fit().into(image12);
                break;
            case 13:
                Picasso.with(context).load(loa.get(0).getCover()).fit().into(image1);
                Picasso.with(context).load(loa.get(1).getCover()).fit().into(image2);
                Picasso.with(context).load(loa.get(2).getCover()).fit().into(image3);
                Picasso.with(context).load(loa.get(3).getCover()).fit().into(image4);
                Picasso.with(context).load(loa.get(4).getCover()).fit().into(image5);
                Picasso.with(context).load(loa.get(5).getCover()).fit().into(image6);
                Picasso.with(context).load(loa.get(6).getCover()).fit().into(image7);
                Picasso.with(context).load(loa.get(7).getCover()).fit().into(image8);
                Picasso.with(context).load(loa.get(8).getCover()).fit().into(image9);
                Picasso.with(context).load(loa.get(9).getCover()).fit().into(image10);
                Picasso.with(context).load(loa.get(10).getCover()).fit().into(image11);
                Picasso.with(context).load(loa.get(11).getCover()).fit().into(image12);
                Picasso.with(context).load(loa.get(12).getCover()).fit().into(image13);
                break;
            case 14:
                Picasso.with(context).load(loa.get(0).getCover()).fit().into(image1);
                Picasso.with(context).load(loa.get(1).getCover()).fit().into(image2);
                Picasso.with(context).load(loa.get(2).getCover()).fit().into(image3);
                Picasso.with(context).load(loa.get(3).getCover()).fit().into(image4);
                Picasso.with(context).load(loa.get(4).getCover()).fit().into(image5);
                Picasso.with(context).load(loa.get(5).getCover()).fit().into(image6);
                Picasso.with(context).load(loa.get(6).getCover()).fit().into(image7);
                Picasso.with(context).load(loa.get(7).getCover()).fit().into(image8);
                Picasso.with(context).load(loa.get(8).getCover()).fit().into(image9);
                Picasso.with(context).load(loa.get(9).getCover()).fit().into(image10);
                Picasso.with(context).load(loa.get(10).getCover()).fit().into(image11);
                Picasso.with(context).load(loa.get(11).getCover()).fit().into(image12);
                Picasso.with(context).load(loa.get(12).getCover()).fit().into(image13);
                Picasso.with(context).load(loa.get(13).getCover()).fit().into(image14);
                break;
            case 15:
                Picasso.with(context).load(loa.get(0).getCover()).fit().into(image1);
                Picasso.with(context).load(loa.get(1).getCover()).fit().into(image2);
                Picasso.with(context).load(loa.get(2).getCover()).fit().into(image3);
                Picasso.with(context).load(loa.get(3).getCover()).fit().into(image4);
                Picasso.with(context).load(loa.get(4).getCover()).fit().into(image5);
                Picasso.with(context).load(loa.get(5).getCover()).fit().into(image6);
                Picasso.with(context).load(loa.get(6).getCover()).fit().into(image7);
                Picasso.with(context).load(loa.get(7).getCover()).fit().into(image8);
                Picasso.with(context).load(loa.get(8).getCover()).fit().into(image9);
                Picasso.with(context).load(loa.get(9).getCover()).fit().into(image10);
                Picasso.with(context).load(loa.get(10).getCover()).fit().into(image11);
                Picasso.with(context).load(loa.get(11).getCover()).fit().into(image12);
                Picasso.with(context).load(loa.get(12).getCover()).fit().into(image13);
                Picasso.with(context).load(loa.get(13).getCover()).fit().into(image14);
                Picasso.with(context).load(loa.get(14).getCover()).fit().into(image15);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(context,ReaderActivity.class);
        switch (v.getId()){
            case R.id.imageView:
               i.putExtra("name",loa.get(0).getId());
                break;
            case R.id.imageView2:
                i.putExtra("name",loa.get(1).getId());
                break;
            case R.id.imageView3:
                i.putExtra("name",loa.get(2).getId());
                break;
            case R.id.imageView4:
                i.putExtra("name",loa.get(3).getId());
                break;
            case R.id.imageView5:
                i.putExtra("name",loa.get(4).getId());
                break;
            case R.id.imageView6:
                i.putExtra("name",loa.get(5).getId());
                break;
            case R.id.imageView7:
                i.putExtra("name",loa.get(6).getId());
                break;
            case R.id.imageView8:
                i.putExtra("name",loa.get(7).getId());
                break;
            case R.id.imageView9:
                i.putExtra("name",loa.get(8).getId());
                break;
            case R.id.imageView10:
                i.putExtra("name",loa.get(9).getId());
                break;
            case R.id.imageView11:
                i.putExtra("name",loa.get(10).getId());
                break;
            case R.id.imageView12:
                i.putExtra("name",loa.get(11).getId());
                break;
            case R.id.imageView13:
                i.putExtra("name",loa.get(12).getId());
                break;
            case R.id.imageView14:
                i.putExtra("name",loa.get(13).getId());
                break;
            case R.id.imageView15:
                i.putExtra("name",loa.get(14).getId());
                break;
        }
        startActivity(i);

    }


}
