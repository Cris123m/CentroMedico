package com.example.centromedico;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.centromedico.model.ClinicaModel;
import com.example.centromedico.model.PosicionModel;

import java.util.List;

public class PosicionRecyclerViewAdapter extends RecyclerView.Adapter<PosicionRecyclerViewAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView posicion,nombreClinica,direccionClinica;
        private ImageView logoClinica;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            posicion = (TextView) itemView.findViewById(R.id.txtPosicion);
            nombreClinica = (TextView) itemView.findViewById(R.id.txtNClinica);
            direccionClinica = (TextView) itemView.findViewById(R.id.txtDClinica);
            logoClinica = (ImageView) itemView.findViewById(R.id.imgClinica);
        }
    }

    public List<PosicionModel> posicionLista;

    public PosicionRecyclerViewAdapter(List<PosicionModel> posicionLista) {
        this.posicionLista = posicionLista;
    }

    @Override
    public PosicionRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_posicion,parent,false);
        PosicionRecyclerViewAdapter.ViewHolder viewHolder = new PosicionRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PosicionRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.posicion.setText(Integer.toString(posicionLista.get(position).getPuesto()));
        holder.nombreClinica.setText(posicionLista.get(position).getClinica().getNombre());
        holder.direccionClinica.setText(posicionLista.get(position).getClinica().getDireccion());
        //holder.logoClinica.setImageResource();
    }

    @Override
    public int getItemCount() {
        return posicionLista.size();
    }
}
