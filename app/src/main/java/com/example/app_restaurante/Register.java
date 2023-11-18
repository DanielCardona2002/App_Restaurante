package com.example.app_restaurante;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.ComponentActivity;

public class Register extends ComponentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

    }

    public void Registro2(View view) {
        startActivity(new Intent(this, Home.class));
    }
}
