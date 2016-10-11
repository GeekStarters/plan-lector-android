package com.app.plan_lector.fragment.teacher;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.plan_lector.R;
import com.app.plan_lector.auxiliar.GridAdapter;
import com.app.plan_lector.model.LibroTexto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gabriela Mejia on 30/10/2016.
 */



public class BookListStudent extends Fragment {


    private RecyclerView reciclador;
    private GridLayoutManager layoutManager;
    private List<LibroTexto> resultado;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_list, container, false);
        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        layoutManager = new GridLayoutManager(getActivity(), 3);
        reciclador.setLayoutManager(layoutManager);
        llenarListaS();
        return view;
    }

    private void llenarListaS() {
        resultado=new ArrayList<>();
        resultado.add(new LibroTexto(R.drawable.elprincipito,"Matematicas I","Libro de Matematicas (Estudiante)"));
        resultado.add(new LibroTexto(R.drawable.elprincipito,"Matematicas I","Libro de Matematicas (Estudiante)"));
        resultado.add(new LibroTexto(R.drawable.elprincipito,"Matematicas I","Libro de Matematicas (Estudiante)"));
        resultado.add(new LibroTexto(R.drawable.elprincipito,"Matematicas I","Libro de Matematicas (Estudiante)"));
        resultado.add(new LibroTexto(R.drawable.elprincipito,"Matematicas I","Libro de Matematicas (Estudiante)"));
        resultado.add(new LibroTexto(R.drawable.elprincipito,"Matematicas I","Libro de Matematicas (Estudiante)"));
        resultado.add(new LibroTexto(R.drawable.elprincipito,"Matematicas I","Libro de Matematicas (Estudiante)"));
        resultado.add(new LibroTexto(R.drawable.elprincipito,"Matematicas I","Libro de Matematicas (Estudiante)"));
        resultado.add(new LibroTexto(R.drawable.elprincipito,"Matematicas I","Libro de Matematicas (Estudiante)"));
        resultado.add(new LibroTexto(R.drawable.elprincipito,"Matematicas I","Libro de Matematicas (Estudiante)"));
        resultado.add(new LibroTexto(R.drawable.elprincipito,"Matematicas I","Libro de Matematicas (Estudiante)"));
        resultado.add(new LibroTexto(R.drawable.elprincipito,"Matematicas I","Libro de Matematicas (Estudiante)"));
        resultado.add(new LibroTexto(R.drawable.elprincipito,"Matematicas I","Libro de Matematicas (Estudiante)"));
        resultado.add(new LibroTexto(R.drawable.elprincipito,"Matematicas I","Libro de Matematicas (Estudiante)"));
        resultado.add(new LibroTexto(R.drawable.elprincipito,"Matematicas I","Libro de Matematicas (Estudiante)"));
        resultado.add(new LibroTexto(R.drawable.elprincipito,"Matematicas I","Libro de Matematicas (Estudiante)"));
        reciclador.setAdapter(new GridAdapter(resultado,getActivity()));
    }


    private void llenarLista() {
        resultado=new ArrayList<>();
        resultado.add(new LibroTexto(R.drawable.elprincipito,"Matematicas I","Libro de Matematicas"));
        resultado.add(new LibroTexto(R.drawable.elprincipito,"Matematicas I","Libro de Matematicas"));
        resultado.add(new LibroTexto(R.drawable.elprincipito,"Matematicas I","Libro de Matematicas"));
        resultado.add(new LibroTexto(R.drawable.elprincipito,"Matematicas I","Libro de Matematicas"));
        resultado.add(new LibroTexto(R.drawable.elprincipito,"Matematicas I","Libro de Matematicas"));
        resultado.add(new LibroTexto(R.drawable.elprincipito,"Matematicas I","Libro de Matematicas"));
        resultado.add(new LibroTexto(R.drawable.elprincipito,"Matematicas I","Libro de Matematicas"));
        resultado.add(new LibroTexto(R.drawable.elprincipito,"Matematicas I","Libro de Matematicas"));
        resultado.add(new LibroTexto(R.drawable.elprincipito,"Matematicas I","Libro de Matematicas"));
        resultado.add(new LibroTexto(R.drawable.elprincipito,"Matematicas I","Libro de Matematicas"));
        resultado.add(new LibroTexto(R.drawable.elprincipito,"Matematicas I","Libro de Matematicas"));
        resultado.add(new LibroTexto(R.drawable.elprincipito,"Matematicas I","Libro de Matematicas"));
        resultado.add(new LibroTexto(R.drawable.elprincipito,"Matematicas I","Libro de Matematicas"));
        resultado.add(new LibroTexto(R.drawable.elprincipito,"Matematicas I","Libro de Matematicas"));
        resultado.add(new LibroTexto(R.drawable.elprincipito,"Matematicas I","Libro de Matematicas"));
        resultado.add(new LibroTexto(R.drawable.elprincipito,"Matematicas I","Libro de Matematicas"));
        reciclador.setAdapter(new GridAdapter(resultado,getActivity()));
    }

}

