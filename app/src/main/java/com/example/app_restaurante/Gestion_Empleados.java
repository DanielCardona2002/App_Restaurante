package com.example.app_restaurante;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.ComponentActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_restaurante.Adapters.EmpleadoAdapter;
import com.example.app_restaurante.Models.Empleado;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Gestion_Empleados extends ComponentActivity {


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private EmpleadoAdapter empleadoAdapter;
    private RecyclerView mRecyclerView;
    private ArrayList<Empleado> empleadosList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion_empleados);

        firebaseDatabase =FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        mRecyclerView = findViewById(R.id.reciclerViewEmpleados);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        getEmpleadosfromFirebase();
    }

    private void getEmpleadosfromFirebase(){
        databaseReference.child("Usuarios").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){

                    empleadosList.clear();
                    for (DataSnapshot ds: snapshot.getChildren()){
                        String nombre = ds.child("Nombre").getValue().toString();
                        String apellido  = ds.child("Apellidos").getValue().toString();
                        empleadosList.add(new Empleado(ds.getKey().toString(), nombre, apellido));
                    }
                    empleadoAdapter = new EmpleadoAdapter(empleadosList, R.layout.empleado_view);
                    mRecyclerView.setAdapter(empleadoAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void EliminarEpleado(String UID){

    }
}
