package com.example.vegan.formularios;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vegan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FormAgricultoresActivity extends AppCompatActivity {

    EditText etId, etNombre, etApellido, etCorreo, etContrasena, etNit, etCiudad;
    FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_agricultores);

        etId = findViewById(R.id.etId);
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etCorreo = findViewById(R.id.etCorreo);
        etContrasena = findViewById(R.id.etContrasena);
        etNit = findViewById(R.id.etNit);
        etCiudad = findViewById(R.id.etCiudad);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    //Registro
    public void registrarAgricultor(View view) {
        final String cedula = etId.getText().toString();
        final String correo = etCorreo.getText().toString();
        final String nombre = etNombre.getText().toString();
        final String apellidos = etApellido.getText().toString();
        final String clave = etContrasena.getText().toString();
        final String nit = etNit.getText().toString();
        final String ciudad = etCiudad.getText().toString();

        //Validar campos para el registro
        if (cedula.isEmpty()) {
            Toast.makeText(FormAgricultoresActivity.this, "Digita tu identificación", Toast.LENGTH_SHORT).show();
            return;
        }
        if (nombre.isEmpty()) {
            Toast.makeText(FormAgricultoresActivity.this, "Digita tu nombre", Toast.LENGTH_SHORT).show();
            return;
        }

        if (apellidos.isEmpty()) {
            Toast.makeText(FormAgricultoresActivity.this, "Digita tus apellidos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (ciudad.isEmpty()) {
            Toast.makeText(FormAgricultoresActivity.this, "Digita tu ciudad", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            etCorreo.setError("Debe ingresar un email valido");
            return;
        }
        if (clave.length() < 6) {
            etContrasena.setError("La contraseña debe tener minimo 6 caracteres");
            return;
        }

        //Crear usuario nuevo autenticacion firebase - correo, clave
        mAuth.createUserWithEmailAndPassword(correo, clave)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Registro de usuarios en FIREBASE.
                            registrarUsuarios(cedula, nombre, apellidos, nit, ciudad, correo, clave);
                        } else {
                            Toast.makeText(FormAgricultoresActivity.this, "No registrado, tu correo ya existe", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


    //Registrar en la BD firebase
    public void registrarUsuarios(final String cedula, String nombre, String apellidos,
                                  String ciudad, String nit, final String correo, String clave) {
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();

        //Registrar en la BD firebase
        user.put("id", cedula);
        user.put("Nombre", nombre);
        user.put("Apellido", apellidos);
        user.put("Correo", correo);
        user.put("Clave", clave);
        user.put("Nit", nit);

        // Add a new document with a generated ID
        db.collection("FormAgricultores")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("er", "DocumentSnapshot added with ID: " + documentReference.getId());

                        Toast.makeText(FormAgricultoresActivity.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
                        //Intent lo_intent = new Intent(getApplicationContext(),AsignacionCitaActivity.class);
                        //Enviar variables para mostrar en la asignación de citas.
                        //lo_intent.putExtra("cedula", cedula);
                        //lo_intent.putExtra("correo", correo);
                        //startActivity(lo_intent);
                        //finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("err", "Error adding document", e);
                        Toast.makeText(FormAgricultoresActivity.this, "Error al registrar", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}