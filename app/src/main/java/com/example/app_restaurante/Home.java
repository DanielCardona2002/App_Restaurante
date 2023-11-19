package com.example.app_restaurante;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.ComponentActivity;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Home extends ComponentActivity {

    private String UserID = null;
    private String Nombre = null;
    private TextView Logged = null;
    private FirebaseDatabase firebaseDatabase = null;
    private DatabaseReference databaseReference = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Bundle bundle = getIntent().getExtras();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        Logged = this.findViewById(R.id.UsuarioLogged);

        if (bundle != null && bundle.getString("UserID") != null) {
            UserID = bundle.getString("UserID");
            databaseReference.child("Usuarios").child(UserID).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        String nombre = snapshot.child("Nombre").getValue().toString();
                        String apellido = snapshot.child("Apellidos").getValue().toString();

                        Logged.setText(String.format("%s %s", nombre, apellido));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

    }

    public void Gestion_Empleados(View view) {
        Intent in  = new Intent(this, Gestion_Empleados.class);
        in.putExtra("UserID", UserID);
        startActivity(in);
    }

    public void Gestion_Inventario(View view){

    }

    public void Gestion_Mesas(View view){

    }

    public void Gestion_Menu(View view){

    }

    public void Gestion_Reservacion(View view){

    }

    public void Gestion_Pedidos(View view){

    }
}
