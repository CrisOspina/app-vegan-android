package com.example.vegan;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.vegan.actividades.LoginActivity;
import com.example.vegan.actividades.RegistrarActivity;
import com.example.vegan.formularios.FormCompradorActivity;

public class MainActivity extends AppCompatActivity {

    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegistrar = findViewById(R.id.btnregistrar);
    }

    //Ir a formulario de registro
    public void registro(View view) {
        dialogoTipoDeUsuario().show();
    }

    //Alerta para elegir el formulario según tipo de usuario
    public AlertDialog dialogoTipoDeUsuario(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.saludo)
                .setMessage(R.string.tipoUsuarios)
                .setPositiveButton("Agricultor", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Agricultor", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                })
                .setNeutralButton("Comerciante", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Comerciante", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Comprador", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getApplicationContext(), "Comprador", Toast.LENGTH_SHORT).show();
                        Intent lo_intent = new Intent(getApplicationContext(), FormCompradorActivity.class);
                        startActivity(lo_intent);
                        finish();
                        dialog.cancel();
                    }
                });
        return builder.create();
    }

    //Ir a iniciar sesión
    public void login(View view) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
