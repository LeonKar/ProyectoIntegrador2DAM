package com.example.agsva_000.proyectointegrador2dam;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth.AuthStateListener authListener =  new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null && user.getEmail() != null) {
                FirebaseAuth.getInstance().removeAuthStateListener(authListener);
                Log.d("Login", "Sesion iniciada");
                Toast toast = Toast.makeText(LoginActivity.this, "Sesion iniciada", Toast.LENGTH_LONG);
                toast.show();
                MainActivity.loggedUser = user;
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }else{
                Log.d("Login", "Sesion cerrada");
            }
        }
    };

    Button botonEntrar, botonRegistrar;
    EditText etEntrar, etContraseña;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        botonEntrar = (Button) findViewById(R.id.botonEntrar);
        botonRegistrar = (Button) findViewById(R.id.botonRegistrar);
        etEntrar = (EditText) findViewById(R.id.etEntrar);
        etContraseña = (EditText) findViewById(R.id.etContraseña);

        FirebaseAuth.getInstance().addAuthStateListener(authListener);

        botonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etEntrar.getText().toString().equals("") || etContraseña.getText().toString().equals("")){
                    Toast toast = Toast.makeText(LoginActivity.this, "Rellene los campos", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(etEntrar.getText().toString(), etContraseña.getText().toString())
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, "Usuario y/o contraseña incorrectos",
                                            Toast.LENGTH_SHORT).show();
                                }else{
                                    //Correcto, entrara en el listener de usuario autenticado
                                }
                            }
                        });
                }
            }
        });

        botonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrarActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
