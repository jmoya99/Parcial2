package com.example.parcial.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.parcial.R;
import com.example.parcial.controller.MainController;
import com.example.parcial.view.fragment.EditarProductoDialog;
import com.example.parcial.view.fragment.PensamientoFragment;

public class MainActivity extends AppCompatActivity implements EditarProductoDialog.EditarPensamientoDialogListener {

    public EditText tituloEditText;
    public EditText descripcionEditText;
    public Spinner categoriaSpinner;
    public MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tituloEditText = findViewById(R.id.tituloTextView_main);
        descripcionEditText = findViewById(R.id.descripcionTextView_main);
        categoriaSpinner = (Spinner) findViewById(R.id.categoriaSpinner_main);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.nombres_categoria, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        categoriaSpinner.setAdapter(adapter);
        controller = MainController.getMainController();
        controller.cargarPensamiento(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void reportar(View view){
        String titulo = tituloEditText.getText().toString();
        String descripcion = descripcionEditText.getText().toString();
        String categoria = categoriaSpinner.getSelectedItem().toString();
        controller.reportar(this,titulo,descripcion,categoria);
    }

    public void agregarPensamientos(int id, String titulo, String descripcion, String color, String fecha){
        getSupportFragmentManager().beginTransaction().add(R.id.pensamientoLinearLayout_main,
                PensamientoFragment.newInstance(id,titulo,descripcion,fecha,color,this))
                .commit();
    }

    public void campoFaltante(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("por favor llenar todos los campos\n")
                .setTitle("Algo fue Mal")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void categoriaFaltante(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Por favor seleccione una categoría\n")
                .setTitle("Algo fue Mal")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void tamanoExcedido(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("El titulo solo puede tener hasta 100 caracteres\n")
                .setTitle("Algo fue Mal")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void recargarActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void eliminarPensamiento(int id){
        controller.eliminar(this,id);
    }

    public void deshacer(View view){
        controller.deshacer(this);
    }

    public void rehacer(View view){
        controller.rehacer(this);
    }

    public void editar(int id,String titulo,String descripcion){
        EditarProductoDialog editarProductoDialog = new EditarProductoDialog(id,titulo,descripcion);
        editarProductoDialog.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void editarPensamiento(int id, String titulo, String descripcion) {
        controller.editar(this,id,titulo,descripcion);
    }
}