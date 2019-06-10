package com.example.vegan.actividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.vegan.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegistrarActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
    }
}
