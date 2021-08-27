package com.example.parcial.command;

import com.example.parcial.model.pojo.Pensamiento;
import com.example.parcial.view.MainActivity;

public interface Comando {

    void ejecutar(Pensamiento pensamiento, MainActivity mainActivity);

    void deshacer();

    void rehacer();
}
