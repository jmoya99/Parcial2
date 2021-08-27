package com.example.parcial.command;

import com.example.parcial.model.LocalStorage;
import com.example.parcial.model.dao.PensamientoRoomDao;
import com.example.parcial.model.pojo.Pensamiento;
import com.example.parcial.view.MainActivity;

public class ReportarComando implements Comando{

    private PensamientoRoomDao pensamientoRoomDao;
    private Pensamiento pensamientoReportado;
    private MainActivity mainActivity;

    public void ejecutar(Pensamiento pensamiento, MainActivity mainActivity){
        this.pensamientoReportado = pensamiento;
        this.mainActivity = mainActivity;
        this.pensamientoRoomDao = LocalStorage.getLocalStorage(
                mainActivity.getApplicationContext()).pensamientoRoomDao();
        this.pensamientoRoomDao.insertarPensamiento(pensamiento);

    }

    public void rehacer(){
        ejecutar(pensamientoReportado,mainActivity);
    }

    public void deshacer(){
        Pensamiento pensamientoABorrar = this.pensamientoRoomDao.obtenerPensamientoSinId(
                pensamientoReportado.getTitulo(), pensamientoReportado.getDescripcion(),
                pensamientoReportado.getFecha(), pensamientoReportado.getCategoria_color());
        this.pensamientoRoomDao.eliminarPensamiento(pensamientoABorrar);
    }
}
