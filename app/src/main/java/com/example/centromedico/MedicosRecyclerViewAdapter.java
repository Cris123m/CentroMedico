package com.example.centromedico;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.centromedico.fragments.dummy.DummyContent.DummyItem;
import com.example.centromedico.model.MedicoModel;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MedicosRecyclerViewAdapter extends RecyclerView.Adapter<MedicosRecyclerViewAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNMedico,txtEspecialidad,txtCedula;
        private ImageView imgMedico;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNMedico = (TextView) itemView.findViewById(R.id.txtNMedico);
            txtEspecialidad = (TextView) itemView.findViewById(R.id.txtEspecialidad);
            txtCedula = (TextView) itemView.findViewById(R.id.txtCedula);
            imgMedico = (ImageView) itemView.findViewById(R.id.imgMedico);
        }
    }

    public List<MedicoModel> medicosLista;

    public MedicosRecyclerViewAdapter(List<MedicoModel> medicosLista) {
        this.medicosLista = medicosLista;
    }

    @Override
    public MedicosRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medico,parent,false);
        MedicosRecyclerViewAdapter.ViewHolder viewHolder = new MedicosRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MedicosRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.txtNMedico.setText("Dr(a). "+medicosLista.get(position).getNombre() + " "
                + medicosLista.get(position).getApellido());
        holder.txtEspecialidad.setText("Especialidad: " + medicosLista.get(position).getEspecialidad());
        holder.txtCedula.setText("Identificación: " + medicosLista.get(position).getCedula());
        //holder.logoClinica.setImageResource();
    }

    @Override
    public int getItemCount() {
        return medicosLista.size();
    }
}