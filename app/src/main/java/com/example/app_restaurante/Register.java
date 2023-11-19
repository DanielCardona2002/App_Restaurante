package com.example.app_restaurante;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.ComponentActivity;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class Register extends ComponentActivity {

    private EditText txtNombres= null;
    private EditText txtApellidos= null;
    private EditText txtUsuario= null;
    private EditText txtClave= null;
    private EditText txtClave2= null;

    private FirebaseDatabase database =  null;
    private FirebaseAuth auth = null;
    private DatabaseReference databaseReference =null;

    private FirebaseUser user =null;

    private String tNombres = null;
    private String tApellidos= null;
    private String tUsuario =null;
    private String tClave =null;
    private String tClave2 =null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        txtNombres = this.findViewById(R.id.rNombres);
        txtApellidos =this.findViewById(R.id.rApellidos);
        txtUsuario =  this.findViewById(R.id.rUsuario);
        txtClave = this.findViewById(R.id.rContrasena);
        txtClave2 =this.findViewById(R.id.rContrasena2);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        databaseReference = database.getReference().child("Usuarios");

    }

    public void Registro2(View view) {
        createNewAccount();
    }

    private void createNewAccount(){
        try{
            tNombres =  txtNombres.getText().toString();
            tApellidos = txtApellidos.getText().toString();
            tUsuario = txtUsuario.getText().toString();
            tClave = txtClave.getText().toString();
            tClave2 = txtClave2.getText().toString();

            if(!TextUtils.isEmpty(tNombres)){
                if (!TextUtils.isEmpty(tApellidos)){
                    if(!TextUtils.isEmpty(tUsuario)){
                        if(!TextUtils.isEmpty(tClave)){
                            if(!TextUtils.isEmpty(tClave2)){
                                if(tClave.equals(tClave2)){
                                    if(tClave.length() >= 6) {
                                        auth.createUserWithEmailAndPassword(tUsuario, tClave).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {
                                                    user = auth.getCurrentUser();
                                                    if (user != null) user.sendEmailVerification();
                                                    else
                                                        throw new RuntimeException("Error usuario en blanco");
                                                    DatabaseReference currentuserDB = databaseReference.child(user.getUid());
                                                    currentuserDB.child("Nombre").setValue(tNombres);
                                                    currentuserDB.child("Apellidos").setValue(tApellidos);
                                                    Toast.makeText(Register.this, "Registro Completo", Toast.LENGTH_LONG).show();
                                                    startActivity(new Intent(Register.this, Home.class).putExtra("UserID", user.getUid()));
                                                    finish();
                                                }
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(Register.this, "Error en el registro", Toast.LENGTH_LONG).show();
                                            }
                                        });
                                    }else Toast.makeText(Register.this, "la  clave debe tener minimo  6 caracteres", Toast.LENGTH_LONG).show();
                                }else Toast.makeText(Register.this, "Las Contraseñas brindadas no coinciden", Toast.LENGTH_LONG).show();
                            }else Toast.makeText(Register.this, "Debes confirmar la contraseña", Toast.LENGTH_LONG).show();
                        }else Toast.makeText(Register.this, "Debes ingresar una contraseña", Toast.LENGTH_LONG).show();
                    }else Toast.makeText(Register.this, "Debes ingresar un correo", Toast.LENGTH_LONG).show();
                }else Toast.makeText(Register.this, "Debes ingrersar tus apellidos", Toast.LENGTH_LONG).show();
            } else Toast.makeText(Register.this, "Debes ingresar tus nombres", Toast.LENGTH_LONG).show();

        }catch(Exception e){
            Log.e("Error", "createNewAccount: Error "+e);
        }
    }
}
