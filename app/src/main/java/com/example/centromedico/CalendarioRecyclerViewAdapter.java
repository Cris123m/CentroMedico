package com.example.centromedico;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.centromedico.model.CalendarioModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class CalendarioRecyclerViewAdapter extends RecyclerView.Adapter<CalendarioRecyclerViewAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView fecha,nombreMedico,direccionMedico,nombrePaciente,direccionPaciente;
        private ImageView logoMedico,logoPaciente;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fecha = (TextView) itemView.findViewById(R.id.txtFecha);
            nombreMedico = (TextView) itemView.findViewById(R.id.txtNMedico);
            nombrePaciente = (TextView) itemView.findViewById(R.id.txtNPaciente);
            direccionMedico = (TextView) itemView.findViewById(R.id.txtDMedico);
            direccionPaciente = (TextView) itemView.findViewById(R.id.txtDPaciente);
            logoMedico = (ImageView) itemView.findViewById(R.id.imgMedico);
            logoPaciente = (ImageView) itemView.findViewById(R.id.imgPaciente);
        }
    }

    public List<CalendarioModel> calendarioLista;

    public CalendarioRecyclerViewAdapter(List<CalendarioModel> calendarioLista) {
        this.calendarioLista = calendarioLista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calendario,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        holder.fecha.setText(sdf.format(calendarioLista.get(position).getFecha()));
        holder.nombreMedico.setText("Dr(a)."+calendarioLista.get(position).getMedico().getNombre()+" "+calendarioLista.get(position).getMedico().getApellido());
        holder.nombrePaciente.setText(calendarioLista.get(position).getPaciente().getPrimerNombre()+" "+calendarioLista.get(position).getPaciente().getPrimerApellido());
        holder.direccionMedico.setText(calendarioLista.get(position).getMedico().getEspecialidad());
        holder.direccionPaciente.setText(calendarioLista.get(position).getPaciente().getCedula());
        //holder.logoMedico.setImageResource();
    }

    @Override
    public int getItemCount() {
        return calendarioLista.size();
    }
}