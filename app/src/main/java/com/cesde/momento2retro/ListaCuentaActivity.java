package com.cesde.momento2retro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class ListaCuentaActivity extends AppCompatActivity {


    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cuenta);
        init();
        verificarSesion();

        String nombre = preferences.getString("nombre", null);
        alert("Hola " + nombre);

    }

    private void verificarSesion(){
        boolean inicio = preferences.getBoolean("inicio", false);
        //alert("Ya inicio?  " + inicio);
        if(!inicio){
            irLogin();
        }
    }

    private void init(){
        preferences = this.getSharedPreferences("session_sp", Context.MODE_PRIVATE);
    }

    private void irLogin(){
        Intent login = new Intent(this, MainActivity.class);
        startActivity(login);
    }

    public void alert(String texto){
        Toast.makeText(this, texto, Toast.LENGTH_LONG).show();
    }
}
