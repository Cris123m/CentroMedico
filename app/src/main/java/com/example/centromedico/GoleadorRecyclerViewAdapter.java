package com.example.centromedico;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.centromedico.model.GoleadorModel;

import java.util.List;

public class GoleadorRecyclerViewAdapter extends RecyclerView.Adapter<GoleadorRecyclerViewAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNGoleador,txtNomClinica,txtGoles;
        private ImageView imgGoleador;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNGoleador = (TextView) itemView.findViewById(R.id.txtNGoleador);
            txtNomClinica = (TextView) itemView.findViewById(R.id.txtNomClinica);
            txtGoles = (TextView) itemView.findViewById(R.id.txtGoles);
            imgGoleador = (ImageView) itemView.findViewById(R.id.imgGoleador);
        }
    }

    public List<GoleadorModel> goleadoresLista;

    public GoleadorRecyclerViewAdapter(List<GoleadorModel> goleadoresLista) {
        this.goleadoresLista = goleadoresLista;
    }

    @Override
    public GoleadorRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goleador,parent,false);
        GoleadorRecyclerViewAdapter.ViewHolder viewHolder = new GoleadorRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GoleadorRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.txtNGoleador.setText(goleadoresLista.get(position).getPaciente().getPrimerNombre() + " "
                                    + goleadoresLista.get(position).getPaciente().getPrimerApellido());
        holder.txtNomClinica.setText(goleadoresLista.get(position).getPaciente().getClinica().getNombre());
        String textoGoles="goles";
        if(goleadoresLista.get(position).getGoles()==1){
            textoGoles="gol";
        }
        holder.txtGoles.setText(Integer.toString(goleadoresLista.get(position).getGoles())+" "+textoGoles);
        //holder.logoClinica.setImageResource();
    }

    @Override
    public int getItemCount() {
        return goleadoresLista.size();
    }
}
