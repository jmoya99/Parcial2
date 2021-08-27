package com.example.parcial.command;

import com.example.parcial.model.LocalStorage;
import com.example.parcial.model.dao.PensamientoRoomDao;
import com.example.parcial.model.pojo.Pensamiento;
import com.example.parcial.view.MainActivity;

public class EditarComando implements Comando{

    Pensamiento pensamientoAnterior;
    Pensamiento pensamientoNuevo;
    private PensamientoRoomDao pensamientoRoomDao;
    private MainActivity mainActivity;

    public void ejecutar(Pensamiento pensamiento, MainActivity mainActivity){
        this.mainActivity = mainActivity;
        this.pensamientoRoomDao = LocalStorage.getLocalStorage(
                mainActivity.getApplicationContext()).pensamientoRoomDao();
        this.pensamientoAnterior = this.pensamientoRoomDao.obtenerPensamiento(pensamiento.getId());
        this.pensamientoNuevo = pensamiento;
        this.pensamientoRoomDao.modificarPensamiento(pensamientoNuevo);
    }

    public void rehacer(){
        ejecutar(pensamientoNuevo,mainActivity);
    }

    public void deshacer(){
        this.pensamientoRoomDao.modificarPensamiento(pensamientoAnterior);
    }
}
