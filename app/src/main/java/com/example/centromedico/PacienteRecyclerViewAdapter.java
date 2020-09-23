package com.example.centromedico;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.centromedico.model.PacienteModel;

import java.util.List;

public class PacienteRecyclerViewAdapter extends RecyclerView.Adapter<PacienteRecyclerViewAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNPaciente,txtNomClinica,txtNumPaciente,txtIdentificacion;
        private ImageView imgPaciente;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNPaciente = (TextView) itemView.findViewById(R.id.txtNPaciente);
            txtNomClinica = (TextView) itemView.findViewById(R.id.txtNomClinica);
            txtNumPaciente = (TextView) itemView.findViewById(R.id.txtNumPaciente);
            txtIdentificacion = (TextView) itemView.findViewById(R.id.txtIdentificacion);
            imgPaciente = (ImageView) itemView.findViewById(R.id.imgPaciente);
        }
    }

    public List<PacienteModel> pacientesLista;

    public PacienteRecyclerViewAdapter(List<PacienteModel> pacientesLista) {
        this.pacientesLista = pacientesLista;
    }

    @Override
    public PacienteRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_paciente,parent,false);
        PacienteRecyclerViewAdapter.ViewHolder viewHolder = new PacienteRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PacienteRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.txtNPaciente.setText(pacientesLista.get(position).getPrimerNombre() + " "
                + pacientesLista.get(position).getSegundoNombre() + " "
                + pacientesLista.get(position).getPrimerApellido() + " "
                + pacientesLista.get(position).getSegundoApellido());
        holder.txtNomClinica.setText(pacientesLista.get(position).getClinica().getNombre());
        holder.txtNumPaciente.setText("Historia: " + Integer.toString(pacientesLista.get(position).getHistoriaClinica()));
        holder.txtIdentificacion.setText("Identificación: " + pacientesLista.get(position).getCedula());
        //holder.logoClinica.setImageResource();
    }

    @Override
    public int getItemCount() {
        return pacientesLista.size();
    }
}
