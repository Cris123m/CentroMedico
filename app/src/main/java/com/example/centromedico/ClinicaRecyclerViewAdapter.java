package com.example.centromedico;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.centromedico.model.CalendarioModel;
import com.example.centromedico.model.ClinicaModel;

import java.text.SimpleDateFormat;
import java.util.List;

public class ClinicaRecyclerViewAdapter extends RecyclerView.Adapter<ClinicaRecyclerViewAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nombreClinica,direccionClinica;
        private ImageView logoClinica;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreClinica = (TextView) itemView.findViewById(R.id.txtNClinica);
            direccionClinica = (TextView) itemView.findViewById(R.id.txtDClinica);
            logoClinica = (ImageView) itemView.findViewById(R.id.imgClinica);
        }
    }

    public List<ClinicaModel> clinicasLista;

    public ClinicaRecyclerViewAdapter(List<ClinicaModel> clinicasLista) {
        this.clinicasLista = clinicasLista;
    }

    @Override
    public ClinicaRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_clinica,parent,false);
        ClinicaRecyclerViewAdapter.ViewHolder viewHolder = new ClinicaRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClinicaRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.nombreClinica.setText(clinicasLista.get(position).getNombre());
        holder.direccionClinica.setText(clinicasLista.get(position).getDireccion());
        //holder.logoClinica.setImageResource();
    }

    @Override
    public int getItemCount() {
        return clinicasLista.size();
    }
}
