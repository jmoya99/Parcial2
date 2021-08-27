
package com.example.parcial.controller;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.parcial.command.Comando;
import com.example.parcial.command.EliminarComando;
import com.example.parcial.command.ReportarComando;
import com.example.parcial.model.LocalStorage;
import com.example.parcial.model.dao.PensamientoRoomDao;
import com.example.parcial.model.pojo.Categoria;
import com.example.parcial.model.pojo.Pensamiento;
import com.example.parcial.view.MainActivity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.List;
import java.util.Stack;

public class MainController {

    public Stack<Comando> pilaDeshacer;
    public Stack<Comando> pilaRehacer;
    private PensamientoRoomDao pensamientoRoomDao;

    private static MainController mainController;

    public static MainController getMainController(){
        if(mainController == null){
            mainController = new MainController();
            mainController.pilaDeshacer = new Stack<>();
            mainController.pilaRehacer = new Stack<>();
        }
        return mainController;
    }

    private MainController(){
    }

    public void cargarPensamiento(MainActivity mainActivity){
        this.pensamientoRoomDao = LocalStorage.getLocalStorage(mainActivity.getApplicationContext())
                .pensamientoRoomDao();
        List<Pensamiento> pensamientos = this.pensamientoRoomDao.obtenerPensamientosOrdenadoPorColor();
        for (Pensamiento pensamiento: pensamientos) {
            mainActivity.agregarPensamientos(pensamiento.getId(),pensamiento.getTitulo(),pensamiento.getDescripcion(),
                    pensamiento.getCategoria_color(), pensamiento.getFecha());
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void reportar(MainActivity mainActivity, String titulo, String descripcion, String categoria){
        if(titulo == null || titulo.compareTo("")==0){
            mainActivity.campoFaltante();
            return;
        }
        if(descripcion == null || descripcion.compareTo("")==0){
            mainActivity.campoFaltante();
            return;
        }
        if(categoria == null || categoria.compareTo("")==0){
            mainActivity.campoFaltante();
            return;
        }
        if(titulo.length() > 100){
            mainActivity.tamanoExcedido();
            return;
        }
        String color="";
        for (Categoria c: LocalStorage.getLocalStorage(
                mainActivity.getApplicationContext()).getCategorias()) {
            if(c.getNombre().equals(categoria)){
                color = c.getColor();
            }

        }
        if(color.equals("")){
            mainActivity.categoriaFaltante();
        }

        Pensamiento pensamiento = new Pensamiento(titulo,descripcion, getFecha(), color);
        Comando comando = new ReportarComando();
        pilaDeshacer.push(comando);
        comando.ejecutar(pensamiento,mainActivity);
        mainActivity.recargarActivity();
    }

    public void eliminar(MainActivity mainActivity, int id){
        this.pensamientoRoomDao = LocalStorage.getLocalStorage(mainActivity.getApplicationContext())
                .pensamientoRoomDao();
        Comando comando = new EliminarComando();
        pilaDeshacer.push(comando);
        Pensamiento pensamiento = this.pensamientoRoomDao.obtenerPensamiento(id);
        comando.ejecutar(pensamiento,mainActivity);
        mainActivity.recargarActivity();
    }

    public void deshacer(MainActivity mainActivity){
        if(pilaDeshacer.empty()){
            return;
        }
        Comando comando = pilaDeshacer.pop();
        comando.deshacer();
        pilaRehacer.push(comando);
        mainActivity.recargarActivity();
    }

    public void rehacer(MainActivity mainActivity){
        if(pilaRehacer.empty()){
            return;
        }
        Comando comando = pilaRehacer.pop();
        comando.rehacer();
        pilaDeshacer.push(comando);
        mainActivity.recargarActivity();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getFecha(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return  dtf.format(LocalDateTime.now());
    }
}