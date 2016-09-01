package com.app.plan_lector.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.plan_lector.R;
import com.avast.android.dialogs.fragment.ListDialogFragment;
import com.avast.android.dialogs.iface.IListDialogListener;

/**
 * Created by Gabriela Mejia on 29/10/2016.
 */
public class Biblioteca extends Fragment implements View.OnClickListener, IListDialogListener {
    Activity context;
    private ImageView image1, image2, image3, image4, image5;
    private static final int BOOK = 74295;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.biblioteca, container, false);
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
        image1.setOnClickListener(this);
        image4.setOnClickListener(this);
        image3.setOnClickListener(this);
        image2.setOnClickListener(this);
        image5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageView:
                ListDialogFragment
                        .createBuilder(context, getFragmentManager())
                        .setTitle("Seleccione una opcion:")
                        .setItems(new String[]{"Resumen", "Personajes", "Comprension Lectora",
                                "Actividades"})
                        .setRequestCode(BOOK)
                        .show();
                break;
            case R.id.imageView2:
                ListDialogFragment
                        .createBuilder(context, getFragmentManager())
                        .setTitle("Seleccione una opcion:")
                        .setItems(new String[]{"Resumen", "Personajes", "Comprension Lectora",
                                "Actividades"})
                        .setRequestCode(BOOK)
                        .show();
                break;
            case R.id.imageView3:
                ListDialogFragment
                        .createBuilder(context, getFragmentManager())
                        .setTitle("Seleccione una opcion:")
                        .setItems(new String[]{"Resumen", "Personajes", "Comprension Lectora",
                                "Actividades"})
                        .setRequestCode(BOOK)
                        .show();
                break;
            case R.id.imageView4:
                ListDialogFragment
                        .createBuilder(context, getFragmentManager())
                        .setTitle("Seleccione una opcion:")
                        .setItems(new String[]{"Resumen", "Personajes", "Comprension Lectora",
                                "Actividades"})
                        .setRequestCode(BOOK)
                        .show();
                break;
            case R.id.imageView5:
                ListDialogFragment
                        .createBuilder(context, getFragmentManager())
                        .setTitle("Seleccione una opcion:")
                        .setItems(new String[]{"Resumen", "Personajes", "Comprension Lectora",
                                "Actividades"})
                        .setRequestCode(BOOK)
                        .show();
                break;
        }
    }

    @Override
    public void onListItemSelected(CharSequence value, int number, int requestCode) {
        Log.i("Respuesta",value+" "+number+" "+requestCode);
        if (requestCode == BOOK) {
            Log.i("Seleccionado:",""+value);
        }
    }
}
