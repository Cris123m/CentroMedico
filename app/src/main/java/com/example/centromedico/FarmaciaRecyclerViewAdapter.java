package com.example.centromedico;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.centromedico.fragments.dummy.DummyContent.DummyItem;
import com.example.centromedico.model.FarmaciaModel;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class FarmaciaRecyclerViewAdapter extends RecyclerView.Adapter<FarmaciaRecyclerViewAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNPaciente,txtCedula,txtDescripcion;
        private ImageView imgMedicina;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNPaciente = (TextView) itemView.findViewById(R.id.txtNPaciente);
            txtCedula = (TextView) itemView.findViewById(R.id.txtCedula);
            txtDescripcion = (TextView) itemView.findViewById(R.id.txtDescripcion);
        }
    }

    public List<FarmaciaModel> farmaciaLista;

    /*public FarmaciaRecyclerViewAdapter(List<FarmaciaModel> historiaClinicaLista) {
        this.historiaClinicaLista = historiaClinicaLista;
    }*/

    public FarmaciaRecyclerViewAdapter(List<FarmaciaModel> farmaciaLista) {
        this.farmaciaLista = farmaciaLista;
    }

    @Override
    public FarmaciaRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_farmacia,parent,false);
        FarmaciaRecyclerViewAdapter.ViewHolder viewHolder = new FarmaciaRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FarmaciaRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.txtNPaciente.setText(farmaciaLista.get(position).getPaciente().getPrimerNombre() + " "
                + farmaciaLista.get(position).getPaciente().getSegundoNombre() + " "
                + farmaciaLista.get(position).getPaciente().getPrimerApellido() + " "
                + farmaciaLista.get(position).getPaciente().getSegundoApellido());
        holder.txtCedula.setText("Cédula: " +farmaciaLista.get(position).getPaciente().getCedula());
        holder.txtDescripcion.setText("Descripción: " +farmaciaLista.get(position).getDescripcion());
        //holder.logoClinica.setImageResource();
    }

    @Override
    public int getItemCount() {
        return farmaciaLista.size();
    }
}