package com.example.app_restaurante;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.ComponentActivity;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends ComponentActivity {

    private String tUsuario = null;
    private String tClave = null;
    private EditText  txtUsuario = null;
    private EditText txtClave = null;

    private FirebaseAuth auth = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        txtUsuario = this.findViewById(R.id.lUsuario);
        txtClave = this.findViewById(R.id.lContrasena);
        auth = FirebaseAuth.getInstance();

    }

    public void Loggeo(View view) {
        LoginUser();
    }
    public void Registro1(View view) {
        startActivity(new Intent(this, Register.class));
        finish();
    }

    public void Olvido(View view){
        startActivity(new Intent(Login.this, Forgot.class));
        finish();
    }

    private void LoginUser(){
        tUsuario =txtUsuario.getText().toString();
        tClave = txtClave.getText().toString();

        if (!TextUtils.isEmpty(tUsuario)){
            if (!TextUtils.isEmpty(tClave)){
                auth.signInWithEmailAndPassword(tUsuario, tClave).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(Login.this, Home.class));
                            finish();
                        }else Toast.makeText(Login.this, "Hubo unn  problema", Toast.LENGTH_LONG).show();
                    }
                });
            }else Toast.makeText(Login.this, "Debes ingresar una contraseña", Toast.LENGTH_SHORT).show();
        }else Toast.makeText(Login.this, "Debes ingresar un correo", Toast.LENGTH_SHORT).show();
    }


}
