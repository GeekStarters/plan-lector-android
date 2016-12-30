package com.app.plan_lector.model;

/**
 * Created by Gabriela Mejia on 2/9/2016.
 */
public class LibroTexto {

    private int idThumbnail;
    private String cover;
    private String nombre;
    private String descripcion;
    private String id;

    public LibroTexto(int idThumbnail, String nombre, String descripcion) {
        this.idThumbnail = idThumbnail;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public LibroTexto(String cover, String id) {
        this.cover=cover;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "LibroTexto{" +
                "id='" + id + '\'' +
                ", cover='" + cover + '\'' +
                '}';
    }
}
