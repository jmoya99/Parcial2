package com.example.parcial.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.parcial.R;
import com.example.parcial.view.MainActivity;

public class PensamientoFragment extends Fragment {

    private int ID;
    private String titulo;
    private String descripcion;
    private String categoria_color;
    private String fecha;
    private MainActivity mainActivity;
    private TextView tituloTextView;
    private TextView descripcionTextView;
    private TextView colorTextView;
    private TextView fechaTextView;
    private Button editarButton;
    private Button eliminarButton;
    private View rootView;

    public PensamientoFragment() {
        // Required empty public constructor
    }

    public static PensamientoFragment newInstance(int id, String titulo, String descripcion, String fecha,
                                                  String categoria_color, MainActivity mainActivity) {
        PensamientoFragment fragment = new PensamientoFragment();
        fragment.setID(id);
        fragment.setTitulo(titulo);
        fragment.setDescripcion(descripcion);
        fragment.setFecha(fecha);
        fragment.setCategoria_color(categoria_color);
        fragment.setMainActivity(mainActivity);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_pensamiento,container,false);
        tituloTextView = rootView.findViewById(R.id.tituloTextView_fragment);
        descripcionTextView = rootView.findViewById(R.id.descripcionTextView_fragment);
        colorTextView = rootView.findViewById(R.id.colorTextView_fragment);
        fechaTextView = rootView.findViewById(R.id.fechaTextView_fragment);
        editarButton = rootView.findViewById(R.id.editarButton_fragment);
        eliminarButton = rootView.findViewById(R.id.eliminarButton_fragment);

        tituloTextView.setText(titulo);
        descripcionTextView.setText(descripcion);
        colorTextView.setText(categoria_color);
        fechaTextView.setText(fecha);

        editarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMainActivity().editar(getID(),getTitulo(),getDescripcion());
            }
        });
        eliminarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMainActivity().eliminarPensamiento(getID());
            }
        });

        return rootView;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}