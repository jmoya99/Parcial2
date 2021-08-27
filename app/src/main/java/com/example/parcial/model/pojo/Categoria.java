package com.example.parcial.model.pojo;

public class Categoria {

    private String nombre;
    private String color;
    private String descripcion;

    public Categoria(String nombre, String color, String descripcion) {
        this.nombre = nombre;
        this.color = color;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
