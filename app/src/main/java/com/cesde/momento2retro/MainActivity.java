package com.cesde.momento2retro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cesde.momento2retro.models.Cliente;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends BaseActivity {

    private Button btn_main_registro, btn_main_ingresar;
    private EditText et_main_cedula, et_main_clave;

    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btn_main_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irRegistro();
            }
        });

        btn_main_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void init(){
        btn_main_registro = findViewById(R.id.btn_main_registro);
        btn_main_ingresar = findViewById(R.id.btn_main_ingresar);
        et_main_cedula = findViewById(R.id.et_main_cedula);
        et_main_clave = findViewById(R.id.et_main_clave);
        db = FirebaseFirestore.getInstance();
        preferences = this.getSharedPreferences("session_sp", Context.MODE_PRIVATE);
        cliente = new Cliente();
    }

    protected void irRegistro(){
        Intent registro = new Intent(this, NuevoClienteActivity.class);
        startActivity(registro);
    }

    private void login(){
        String cedula = et_main_cedula.getText().toString();
        String clave = et_main_clave.getText().toString();

        db.collection("Clientes")
                .whereEqualTo("cedula", cedula)
                .whereEqualTo("clave", clave)
                .get()
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        alert("Cancelado en el camino");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        alert("Falló " + e.getMessage());
                    }
                })
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (queryDocumentSnapshots != null) {
                            if (!queryDocumentSnapshots.getDocuments().isEmpty()) {
                                for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                                    //alert(document.getId() + " => " + document.getData());
                                    cliente = document.toObject(Cliente.class);
                                    guardarPreferencias(cliente, document.getId());
                                    //Log.d(TAG, document.getId() + " => " + document.getData());
                                }
                            }else{
                                alert("Sin datos");
                            }
                        }
                    }
                });

    }

    private void guardarPreferencias(Cliente cliente, String clienteId){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("inicio", true);
        editor.putString("cedula", cliente.getCedula());
        editor.putString("nombre", cliente.getNombre());
        editor.putString("id", clienteId);
        editor.commit();

        irListaCuenta();

    }

    protected void irListaCuenta(){
        Intent lista = new Intent(this, ListaCuentaActivity.class);
        startActivity(lista);
    }
}
