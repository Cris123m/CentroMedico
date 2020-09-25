package com.example.centromedico;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.centromedico.model.DetalleHistoriaClinicaModel;
import com.example.centromedico.model.HistoriaClinicaModel;

import java.util.List;

public class HistoriaClinicaRecyclerViewAdapter extends RecyclerView.Adapter<HistoriaClinicaRecyclerViewAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNPaciente,txtNumHistoria,txtEspecialidad,txtProcedimiento,txtDetalle;
        private ImageView imgPaciente;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNPaciente = (TextView) itemView.findViewById(R.id.txtNPaciente);
            txtNumHistoria = (TextView) itemView.findViewById(R.id.txtNumHistoria);
            txtEspecialidad = (TextView) itemView.findViewById(R.id.txtEspecialidad);
            txtProcedimiento = (TextView) itemView.findViewById(R.id.txtProcedimiento);
            txtDetalle = (TextView) itemView.findViewById(R.id.txtDetalle);
            imgPaciente = (ImageView) itemView.findViewById(R.id.imgPaciente);
        }
    }

    public List<DetalleHistoriaClinicaModel> detalleHistoriaClinicaLista;

    /*public HistoriaClinicaRecyclerViewAdapter(List<HistoriaClinicaModel> historiaClinicaLista) {
        this.historiaClinicaLista = historiaClinicaLista;
    }*/

    public HistoriaClinicaRecyclerViewAdapter(List<DetalleHistoriaClinicaModel> detalleHistoriaClinicaLista) {
        this.detalleHistoriaClinicaLista = detalleHistoriaClinicaLista;
    }

    @Override
    public HistoriaClinicaRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detalle_historia,parent,false);
        HistoriaClinicaRecyclerViewAdapter.ViewHolder viewHolder = new HistoriaClinicaRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoriaClinicaRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.txtNPaciente.setText(detalleHistoriaClinicaLista.get(position).getHistoriaClinica().getPaciente().getPrimerNombre() + " "
                + detalleHistoriaClinicaLista.get(position).getHistoriaClinica().getPaciente().getSegundoNombre() + " "
                + detalleHistoriaClinicaLista.get(position).getHistoriaClinica().getPaciente().getPrimerApellido() + " "
                + detalleHistoriaClinicaLista.get(position).getHistoriaClinica().getPaciente().getSegundoApellido());
        holder.txtNumHistoria.setText("Historia: " +detalleHistoriaClinicaLista.get(position).getHistoriaClinica().getIdHistoriaClinica());
        holder.txtEspecialidad.setText("Especialidad: " +detalleHistoriaClinicaLista.get(position).getMedico().getEspecialidad());
        holder.txtProcedimiento.setText("Procedimiento: " + detalleHistoriaClinicaLista.get(position).getProcedimiento());
        holder.txtDetalle.setText("Detalle: " + detalleHistoriaClinicaLista.get(position).getDetalle());
        //holder.logoClinica.setImageResource();
    }

    @Override
    public int getItemCount() {
        return detalleHistoriaClinicaLista.size();
    }
}
