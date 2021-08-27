package com.example.parcial.view.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.parcial.R;


public class EditarProductoDialog extends AppCompatDialogFragment {
    private EditText tituloEditText;
    private EditText descripcionEditText;
    private EditarPensamientoDialogListener listener;
    private int id;
    private String titulo;
    private String descripcion;

    public EditarProductoDialog(int id, String titulo, String descripcion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.editar_producto_dialog, null);

        builder.setView(view)
                .setTitle("Editar")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String titulo = tituloEditText.getText().toString();
                        String descripcion = descripcionEditText.getText().toString();
                        listener.editarPensamiento(id, titulo, descripcion);
                    }
                });

        tituloEditText = view.findViewById(R.id.tituloEditText_dialog);
        descripcionEditText = view.findViewById(R.id.descripcionEditText_dialog);
        tituloEditText.setText(titulo);
        descripcionEditText.setText(descripcion);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (EditarPensamientoDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public interface EditarPensamientoDialogListener {
        void editarPensamiento(int id, String titulo, String descripcion);
    }
}
