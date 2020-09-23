package com.example.centromedico.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.centromedico.ClinicaRecyclerViewAdapter;
import com.example.centromedico.R;
import com.example.centromedico.model.ClinicaModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ClinicasFragment extends Fragment {

    View vista;
    private RecyclerView recyclerViewClinica;
    private ClinicaRecyclerViewAdapter adapterClinica;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        vista=inflater.inflate(R.layout.fragment_clinicas, container, false);

        recyclerViewClinica=(RecyclerView)vista.findViewById(R.id.recyclerClinicas);
        recyclerViewClinica.setLayoutManager(new LinearLayoutManager(this.getContext()));
        listarClinicas();

        //adapterClinica = new ClinicaRecyclerViewAdapter(obtenerClinicas());
        //recyclerViewClinica.setAdapter(adapterClinica);

        return vista;
    }

    private void listarClinicas(){
        mDatabase = database.getReference("clinica");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<ClinicaModel> clinicas = new ArrayList<>();
                for(DataSnapshot clinicaSnapshot: dataSnapshot.getChildren()){
                    if(clinicaSnapshot.exists()){
                        ClinicaModel clinicaDB = new ClinicaModel();
                        String idClinica = clinicaSnapshot.child("idClinica").getValue().toString();
                        String nombre = clinicaSnapshot.child("nombre").getValue().toString();
                        String direccion = clinicaSnapshot.child("direccion").getValue().toString();
                        clinicaDB.setIdClinica(idClinica);
                        clinicaDB.setNombre(nombre);
                        clinicaDB.setDireccion(direccion);
                        clinicas.add(clinicaDB);
                    }
                }
                adapterClinica = new ClinicaRecyclerViewAdapter(clinicas);
                recyclerViewClinica.setAdapter(adapterClinica);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public List<ClinicaModel> obtenerClinicas(){
        mDatabase = database.getReference("clinica");
        List<ClinicaModel> clinicas = new ArrayList<>();
        clinicas.add(new ClinicaModel("1","Liga","LDU"));
        clinicas.add(new ClinicaModel("1","Independiente del Valle","IDV"));
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot clinicaSnapshot: dataSnapshot.getChildren()){
                    if(dataSnapshot.exists()){
                        ClinicaModel clinicaDB = new ClinicaModel();
                        String idClinica = dataSnapshot.child("idClinica").getValue().toString();
                        String nombre = dataSnapshot.child("nombre").getValue().toString();
                        String direccion = dataSnapshot.child("direccion").getValue().toString();
                        clinicaDB.setIdClinica(idClinica);
                        clinicaDB.setNombre(nombre);
                        clinicaDB.setDireccion(direccion);
                        //clinicas.add(clinicaDB);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return clinicas;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
