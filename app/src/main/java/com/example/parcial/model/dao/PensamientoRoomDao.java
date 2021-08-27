package com.example.parcial.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.parcial.model.pojo.Pensamiento;

import java.util.List;

@Dao
public interface PensamientoRoomDao {

    @Query("SELECT * FROM pensamiento")
    List<Pensamiento> obtenerPensamientos();

    @Query("SELECT * FROM pensamiento ORDER BY categoria_color")
    List<Pensamiento> obtenerPensamientosOrdenadoPorColor();

    @Query("SELECT * FROM pensamiento WHERE id = :id")
    Pensamiento obtenerPensamiento(int id);

    @Query("SELECT * FROM pensamiento WHERE titulo = :titulo AND descripcion = :descripcion AND" +
            " fecha = :fecha AND categoria_color = :categoria_color")
    Pensamiento obtenerPensamientoSinId(String titulo, String descripcion, String fecha,
                                        String categoria_color);
    @Insert
    void insertarPensamiento(Pensamiento pensamiento);

    @Update
    void modificarPensamiento(Pensamiento pensamiento);

    @Delete
    void eliminarPensamiento(Pensamiento pensamiento);

}