package com.example.parcial.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.parcial.model.dao.PensamientoRoomDao;
import com.example.parcial.model.pojo.Categoria;
import com.example.parcial.model.pojo.Pensamiento;

@Database(entities = {Pensamiento.class}, version = 1)
public abstract class LocalStorage extends RoomDatabase {

    private Categoria[] categorias;

    public abstract PensamientoRoomDao pensamientoRoomDao();

    private static LocalStorage localStorage;

    public static LocalStorage getLocalStorage(final Context context){
        if(localStorage == null){
            localStorage = Room.databaseBuilder(context,
                    LocalStorage.class,
                    "Tagebuch")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
            localStorage.categorias = new Categoria[5];
            localStorage.categorias[0] = new Categoria("Pensamientos buenos", "Verde",
                    "Cosas que me hacen feliz");
            localStorage.categorias[1] = new Categoria("Pensamientos malo", "Rojo",
                    "Cosas que me hacen triste");
            localStorage.categorias[2] = new Categoria("Pensamientos suicidas", "Morado",
                    "formas en las que he pensado matarme");
            localStorage.categorias[3] = new Categoria("Viajes", "Blanco",
                    "Sueños de irme a Suiza y salir de Colombia");
            localStorage.categorias[4] = new Categoria("Diagramación", "Negro",
                    "Formas de suicidio gracias esquemas preconceptuales");

        }
        return localStorage;
    }

    public Categoria[] getCategorias() {
        return categorias;
    }
}
