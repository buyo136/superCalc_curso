package com.example.supercalc_curso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.mariuszgromada.math.mxparser.Expression;

public class MainActivity extends AppCompatActivity {

    EditText txtDisplay;
    String memoria="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtDisplay = findViewById(R.id.txtDisplay);

        final Button btnmemoria = findViewById(R.id.btnMemoria);/*Agregar final para usar variable de hilo diferente cuando esta dentro de una funcion. Para que pueda salir de la funcion al resto del programa*/
        btnmemoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(memoria.isEmpty()){
                    memoria = txtDisplay.getText().toString();
                    txtDisplay.setText("");
                    btnmemoria.setText(memoria);
                }
                else{
                    txtDisplay.append(memoria);
                    memoria = "";
                    btnmemoria.setText("MEM");
                }
            }
        });
    }

    public void agrgarValor(View vista){
        Button boton = (Button)vista;
        txtDisplay.append(boton.getText());
    }

    public void agrgarFuncion(View vista){
        Button boton = (Button)vista;
        txtDisplay.setText(boton.getText()+"("+txtDisplay.getText()+")");
    }

    public void limpiarEntrada(View vista){
        txtDisplay.setText("");
    }

    public void limpiarTodo(View vista){
        if (!memoria.isEmpty()){
            memoria = "";
            Button btnMemoria = findViewById(R.id.btnMemoria);
            btnMemoria.setText("MEM");
        }
        txtDisplay.setText("");
    }

    public void funcionCalcular(View vista){
        Double res = new Expression(txtDisplay.getText().toString()).calculate();
        txtDisplay.setText(String.valueOf(res));
    }

    public void agregarPunto(View Vista){
        String texto = txtDisplay.getText().toString();
        if(texto.isEmpty()){
            return;
        }
        if(texto.endsWith(".")){
            return;
        }

        txtDisplay.append(".");
    }
}
