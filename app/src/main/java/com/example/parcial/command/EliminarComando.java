package com.example.parcial.command;

import com.example.parcial.model.LocalStorage;
import com.example.parcial.model.dao.PensamientoRoomDao;
import com.example.parcial.model.pojo.Pensamiento;
import com.example.parcial.view.MainActivity;

public class EliminarComando implements Comando{

    private PensamientoRoomDao pensamientoRoomDao;
    private Pensamiento pensamientoEliminado;
    private MainActivity mainActivity;

    public void ejecutar(Pensamiento pensamiento, MainActivity mainActivity){
        this.pensamientoEliminado = pensamiento;
        this.mainActivity = mainActivity;
        this.pensamientoRoomDao = LocalStorage.getLocalStorage(
                mainActivity.getApplicationContext()).pensamientoRoomDao();
        this.pensamientoRoomDao.eliminarPensamiento(pensamiento);
    }

    public void rehacer(){
        this.pensamientoRoomDao.eliminarPensamiento(pensamientoEliminado);
    }

    public void deshacer(){
        this.pensamientoRoomDao.insertarPensamiento(pensamientoEliminado);
    }
}