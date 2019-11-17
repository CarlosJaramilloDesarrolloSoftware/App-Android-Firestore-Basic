package com.cesde.momento2retro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

public class BaseActivity extends AppCompatActivity {

    protected SharedPreferences preferences;
    protected FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        init_b();
    }

    protected void init_b(){
        preferences = this.getSharedPreferences("session_sp", Context.MODE_PRIVATE);
        db = FirebaseFirestore.getInstance();
    }
    protected void alert(String texto){
        Toast.makeText(this, texto, Toast.LENGTH_LONG).show();
    }
}
