package com.example.app_restaurante.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_restaurante.Models.Empleado;
import com.example.app_restaurante.R;

import java.util.ArrayList;

import kotlin.Unit;

public class EmpleadoAdapter extends RecyclerView.Adapter<EmpleadoAdapter.ViewHolder> {

    private int resource;
    private ArrayList<Empleado> EmpleadoList;

    public EmpleadoAdapter(ArrayList<Empleado  > EmpleadoList, int resource){
        this.EmpleadoList = EmpleadoList;
        this.resource = resource;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Empleado empleado = EmpleadoList.get(position);

        holder.textViewNombreEmpleado.setText(String.format("%s %s", empleado.getNombre(), empleado.getApellidos()));

    }

    @Override
    public int getItemCount() {
        return EmpleadoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewNombreEmpleado;
        private Button btnEditarEmpleado;
        private Button btnEliminarEmpleado;

        public View view;

        public ViewHolder(View view){
            super(view);

            this.view =  view;
            this.textViewNombreEmpleado =  view.findViewById(R.id.Nombre_Empleado_TextView);
            this.btnEditarEmpleado = view.findViewById(R.id.btnEditar_Empleado_view);
            this.btnEliminarEmpleado = view.findViewById(R.id.btnEliminar_Empleado_View);
        }
    }
}
