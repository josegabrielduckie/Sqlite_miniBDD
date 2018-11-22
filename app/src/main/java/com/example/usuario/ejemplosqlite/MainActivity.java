package com.example.usuario.ejemplosqlite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
EditText nombre, apellidos,nota,idee;
Button insertar ,boton,modificar,borrar;
MiBaseDeDatos baseDeDatos;
ListView lista_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre = findViewById(R.id.editTextNombre);
        apellidos = findViewById(R.id.editTextApellidos);
        nota = findViewById(R.id.editTextNota);
        insertar = findViewById(R.id.button);
        lista_view=findViewById(R.id.lista);
        boton=findViewById(R.id.listar);
        modificar=findViewById(R.id.mod);
        borrar=findViewById(R.id.borr);
        idee=findViewById(R.id.id);
        baseDeDatos = new MiBaseDeDatos(this);

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean resultado = false;

                resultado = baseDeDatos.insertar(nombre.getText().toString(),
                        apellidos.getText().toString(),
                        nota.getText().toString());

                if (resultado){
                    Toast.makeText(MainActivity.this, "Insertado correctamente", Toast.LENGTH_SHORT).show();
                    boton.callOnClick();
                }else{
                    Toast.makeText(MainActivity.this, "Error en la insercción.", Toast.LENGTH_SHORT).show();

                }

            }
        });


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor =baseDeDatos.listar();
                ArrayList<String> lista =  new ArrayList<String>();
                ArrayAdapter <String>adapter;
                if(cursor!=null && cursor.getCount()>0){

                    while(cursor.moveToNext()){
                        String fila="";
                        fila+="Id "+cursor.getString(0);
                        fila+=" Nombre "+cursor.getString(1);
                        fila+=" Apellidos "+cursor.getString(2);
                        fila+=" Nota "+cursor.getString(3);

                        lista.add(fila);
                    }

                    adapter= new ArrayAdapter<>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,lista);
                    lista_view.setAdapter(adapter);
                }else{
                    Toast.makeText(MainActivity.this, "Base de datos vacia", Toast.LENGTH_SHORT).show();
                }
            }
        });
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean resultado = false;

                resultado = baseDeDatos.borrar(idee.getText().toString());

                if (resultado){
                    Toast.makeText(MainActivity.this, "Borrado correctamente", Toast.LENGTH_SHORT).show();
                    boton.callOnClick();
                }else{
                    Toast.makeText(MainActivity.this, "Error en el Borrado.", Toast.LENGTH_SHORT).show();

                }
            }
        });


        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean resultado = false;

                resultado = baseDeDatos.modificar(idee.getText().toString(),nombre.getText().toString(),
                        apellidos.getText().toString(),
                        nota.getText().toString());

                if (resultado){
                    Toast.makeText(MainActivity.this, "Modificar correctamente", Toast.LENGTH_SHORT).show();
                    boton.callOnClick();
                }else{
                    Toast.makeText(MainActivity.this, "Error en la Modificacion.", Toast.LENGTH_SHORT).show();

                }
            }
        });



    }
}
