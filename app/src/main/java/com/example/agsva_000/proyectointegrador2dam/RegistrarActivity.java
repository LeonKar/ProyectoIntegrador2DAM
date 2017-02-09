package com.example.agsva_000.proyectointegrador2dam;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrarActivity extends AppCompatActivity {
    Button botonRegistrar;
    EditText etRegNombre, etRegContraseña, etRegRepetirCon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar);
        botonRegistrar = (Button) findViewById(R.id.botonRegistrar);
        etRegNombre = (EditText) findViewById(R.id.etRegNombre);
        etRegContraseña = (EditText) findViewById(R.id.etRegContraseña);
        etRegRepetirCon = (EditText) findViewById(R.id.etRegRepetirCon);

        botonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etRegNombre.getText().toString().equals("") || etRegContraseña.getText().toString().equals("") || etRegRepetirCon.getText().toString().equals("")){
                    Toast toast = Toast.makeText(RegistrarActivity.this, "Rellene los campos", Toast.LENGTH_SHORT);
                    toast.show();
                }else if(etRegContraseña.getText().toString().equals(etRegRepetirCon.getText().toString()) == false){
                    Toast toast = Toast.makeText(RegistrarActivity.this, "Las contraseñas no son iguales", Toast.LENGTH_SHORT);
                    toast.show();
                }else if(etRegContraseña.getText().toString().trim().length() < 6){
                    Toast toast = Toast.makeText(RegistrarActivity.this, "Longitud minima de contraseña: 6 caracteres", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(etRegNombre.getText().toString(), etRegContraseña.getText().toString())
                        .addOnCompleteListener(RegistrarActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(RegistrarActivity.this, "Fallo al registrar usuario, intentelo de nuevo",
                                            Toast.LENGTH_SHORT).show();
                                }else{
                                    //Registro correcto, pasamos a login
                                    Toast.makeText(RegistrarActivity.this, "Registrado correctamente",
                                            Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegistrarActivity.this, LoginActivity.class));
                                    finish();
                                }
                            }
                        });
                }
            }
        });

    }
}
