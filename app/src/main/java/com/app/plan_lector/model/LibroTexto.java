package com.app.plan_lector.model;

/**
 * Created by Gabriela Mejia on 2/9/2016.
 */
public class LibroTexto {

    private int idThumbnail;
    private String nombre;
    private String descripcion;

    public LibroTexto(int idThumbnail, String nombre, String descripcion) {
        this.idThumbnail = idThumbnail;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdThumbnail() {
        return idThumbnail;
    }

    public void setIdThumbnail(int idThumbnail) {
        this.idThumbnail = idThumbnail;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
