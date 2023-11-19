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
import com.google.firebase.auth.FirebaseAuth;

public class Forgot extends ComponentActivity {

    private EditText fUsuario = null;
    private FirebaseAuth Auth = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot);

        fUsuario = this.findViewById(R.id.fUsuario);
        Auth = FirebaseAuth.getInstance();

    }

    public void bForgot (View view){
        sendPassReset();
    }

    private void sendPassReset(){
        String  tUsuario = fUsuario.getText().toString();
        if(!TextUtils.isEmpty(tUsuario)){
            Auth.sendPasswordResetEmail(tUsuario).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Forgot.this, "Se ha enviado correo para recuperar tu cuenta", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Forgot.this, Login.class));
                        finish();
                    }else Toast.makeText(Forgot.this, "No se ha encotrado cuenta con dicho correo", Toast.LENGTH_SHORT).show();
                }
            });
        }else Toast.makeText(Forgot.this, "Debe ingresar un correo", Toast.LENGTH_SHORT).show();
    }
}
