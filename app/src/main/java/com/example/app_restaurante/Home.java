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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

        if(bundle.getString("UserID") != null){
            UserID = bundle.getString("UserID");
            databaseReference.child("Usuarios").child(UserID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                   if (task.isSuccessful()){
                       Object registro =  null;
                       registro = task.getResult().getValue();
                       String Name =null;

                     /*   for(DataSnapshot child: task.getResult().getChildren()){
                            Name = Name + " " + child.get;
                        } */

                       Logged.setText(Name);

                   }
                }
            });

        }

    }

    public void Gestion_Empleados(View view) {
        startActivity(new Intent(this, Gestion_Empleados.class));
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
