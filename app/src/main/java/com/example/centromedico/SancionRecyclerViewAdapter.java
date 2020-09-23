package com.example.centromedico;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.centromedico.model.SancionModel;

import java.util.List;

    public class SancionRecyclerViewAdapter extends RecyclerView.Adapter<com.example.centromedico.SancionRecyclerViewAdapter.ViewHolder> {
        public static class ViewHolder extends RecyclerView.ViewHolder{
            private TextView txtNPaciente,txtNomClinica,txtNumPaciente,txtSancion;
            private ImageView imgPaciente;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                txtNPaciente = (TextView) itemView.findViewById(R.id.txtNPaciente);
                txtNomClinica = (TextView) itemView.findViewById(R.id.txtNomClinica);
                txtNumPaciente = (TextView) itemView.findViewById(R.id.txtNumPaciente);
                txtSancion = (TextView) itemView.findViewById(R.id.txtSancion);
                imgPaciente = (ImageView) itemView.findViewById(R.id.imgPaciente);
            }
        }

        public List<SancionModel> sancionLista;

        public SancionRecyclerViewAdapter(List<SancionModel> sancionLista) {
            this.sancionLista = sancionLista;
        }

        @Override
        public com.example.centromedico.SancionRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sancion,parent,false);
            com.example.centromedico.SancionRecyclerViewAdapter.ViewHolder viewHolder = new com.example.centromedico.SancionRecyclerViewAdapter.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull com.example.centromedico.SancionRecyclerViewAdapter.ViewHolder holder, int position) {
            holder.txtNPaciente.setText(sancionLista.get(position).getPaciente().getPrimerNombre() + " "
                                    + sancionLista.get(position).getPaciente().getPrimerApellido());
            holder.txtNumPaciente.setText(Integer.toString(sancionLista.get(position).getPaciente().getHistoriaClinica()));
            holder.txtNomClinica.setText(sancionLista.get(position).getPaciente().getClinica().getNombre());
            holder.txtSancion.setText(sancionLista.get(position).getDetalle());

            holder.imgPaciente.setImageResource(R.drawable.ic_player);
        }

        @Override
        public int getItemCount() {
            return sancionLista.size();
        }
}
