package com.cesde.momento2retro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cesde.momento2retro.models.Cliente;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class NuevoClienteActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private Button btn_nuevo_cliente_registrar;
    private EditText et_nuevo_cliente_nombre,
            et_nuevo_cliente_apellido,
            et_nuevo_cliente_cedula,
            et_nuevo_cliente_clave;
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_cliente);
        init();

        btn_nuevo_cliente_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar();
            }
        });
    }

    private void init(){
        db = FirebaseFirestore.getInstance();
        btn_nuevo_cliente_registrar = findViewById(R.id.btn_nuevo_cliente_registrar);
        et_nuevo_cliente_nombre = findViewById(R.id.et_nuevo_cliente_nombre);
        et_nuevo_cliente_apellido = findViewById(R.id.et_nuevo_cliente_apellido);
        et_nuevo_cliente_cedula = findViewById(R.id.et_nuevo_cliente_cedula);
        et_nuevo_cliente_clave = findViewById(R.id.et_nuevo_cliente_clave);
        cliente = new Cliente();
    }

    private void guardar(){

        String nombre = et_nuevo_cliente_nombre.getText().toString();
        String apellido = et_nuevo_cliente_apellido.getText().toString();
        String cedula = et_nuevo_cliente_cedula.getText().toString();
        String clave = et_nuevo_cliente_clave.getText().toString();

        cliente.setApellido(apellido);
        cliente.setNombre(nombre);
        cliente.setCedula(cedula);
        cliente.setClave(clave);

        // Add a new document with a generated ID
        db.collection("Clientes")
                .add(cliente)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        irLogin();
                        //Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Log.w(TAG, "Error adding document", e);
                    }
                });
    }


    protected void irLogin(){
        Intent login = new Intent(this, MainActivity.class);
        startActivity(login);
    }



}
