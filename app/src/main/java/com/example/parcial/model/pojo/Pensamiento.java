package com.example.parcial.model.pojo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "pensamiento")
public class Pensamiento {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "titulo")
    private String titulo;

    @NonNull
    @ColumnInfo(name = "descripcion")
    private String descripcion;

    @NonNull
    @ColumnInfo
    private String fecha;

    @NonNull
    @ColumnInfo(name = "categoria_color")
    private String categoria_color;

    public Pensamiento(String titulo, String descripcion, String fecha, String categoria_color) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.categoria_color = categoria_color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCategoria_color() {
        return categoria_color;
    }

    public void setCategoria_color(String categoria_color) {
        this.categoria_color = categoria_color;
    }
}