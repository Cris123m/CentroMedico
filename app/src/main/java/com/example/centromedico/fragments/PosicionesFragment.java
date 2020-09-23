package com.example.centromedico.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.centromedico.PosicionRecyclerViewAdapter;
import com.example.centromedico.R;
import com.example.centromedico.PosicionRecyclerViewAdapter;
import com.example.centromedico.model.ClinicaModel;
import com.example.centromedico.model.PosicionModel;
import com.example.centromedico.model.PosicionModel;
import com.example.centromedico.model.PosicionModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PosicionesFragment extends Fragment {

    View vista;
    private RecyclerView recyclerViewPosicion;
    private PosicionRecyclerViewAdapter adapterPosicion;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        vista=inflater.inflate(R.layout.fragment_posiciones, container, false);

        recyclerViewPosicion=(RecyclerView)vista.findViewById(R.id.recyclerPosiciones);
        recyclerViewPosicion.setLayoutManager(new LinearLayoutManager(this.getContext()));

        listarPosiciones();

        //adapterPosicion = new PosicionRecyclerViewAdapter(obtenerPosiciones());
        //recyclerViewPosicion.setAdapter(adapterPosicion);

        return vista;
    }

    private void listarPosiciones(){
        mDatabase = database.getReference();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    List<PosicionModel> posiciones = obtenerPosiciones(dataSnapshot);
                    adapterPosicion = new PosicionRecyclerViewAdapter(posiciones);
                    recyclerViewPosicion.setAdapter(adapterPosicion);
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Error: "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public List<PosicionModel> obtenerPosiciones(DataSnapshot dataSnapshot){
        List<PosicionModel> posiciones = new ArrayList<>();
        for(DataSnapshot posicionSnapshot: dataSnapshot.child("posicion").getChildren()){
            if(posicionSnapshot.exists()){
                PosicionModel posicion = new PosicionModel();
                String idPosicion = posicionSnapshot.child("idPosicion").getValue().toString();
                String clinicaId = posicionSnapshot.child("clinicaId").getValue().toString();
                String puesto = posicionSnapshot.child("puesto").getValue().toString();
                posicion.setIdPosicion(idPosicion);
                posicion.setClinica(obtenerClinica(dataSnapshot,clinicaId));
                posicion.setPuesto(Integer.parseInt(puesto));
                posiciones.add(posicion);
            }
        }

        return posiciones;
    }

    public ClinicaModel obtenerClinica(DataSnapshot dataSnapshot, String clinicaId){
        ClinicaModel clinica = new ClinicaModel();
        DataSnapshot dsClinica = dataSnapshot.child("clinica").child(clinicaId);
        clinica.setIdClinica(dsClinica.getValue().toString());
        clinica.setNombre(dsClinica.child("nombre").getValue().toString());
        clinica.setDireccion(dsClinica.child("direccion").getValue().toString());
        return clinica;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
