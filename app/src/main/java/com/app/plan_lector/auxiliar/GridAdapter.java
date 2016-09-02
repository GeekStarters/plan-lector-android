package com.app.plan_lector.auxiliar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.plan_lector.R;
import com.app.plan_lector.model.LibroTexto;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * {@link BaseAdapter} personalizado para el gridview
 */
public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {


    private final List<LibroTexto> items;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nombre;
        public TextView descripcion;
        public ImageView imagen;

        public ViewHolder(View v) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.nombre);
            imagen = (ImageView) v.findViewById(R.id.imagen);
            descripcion = (TextView) v.findViewById(R.id.descripcion);
        }
    }


    public GridAdapter(List<LibroTexto> items, Context context) {
        this.items = items;
        this.context=context;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.grid_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final LibroTexto item = items.get(i);
        Glide.with(viewHolder.itemView.getContext())
                .load(item.getIdThumbnail())
                .centerCrop()
                .into(viewHolder.imagen);
        viewHolder.nombre.setText(item.getNombre());
        viewHolder.descripcion.setText(item.getDescripcion());
    }


}