package com.example.app_restaurante;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.activity.ComponentActivity;

public class Login extends ComponentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

    }

    public void Loggeo(View view) {
        startActivity(new Intent(this, Home.class));
    }

    public void Registro1(View view) {
        startActivity(new Intent(this, Register.class));
    }

    public void LoginUser(){

    }


}
