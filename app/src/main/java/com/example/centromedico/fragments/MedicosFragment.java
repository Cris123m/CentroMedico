package com.example.centromedico.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.centromedico.MedicosRecyclerViewAdapter;
import com.example.centromedico.MedicosRecyclerViewAdapter;
import com.example.centromedico.R;
import com.example.centromedico.fragments.dummy.DummyContent;
import com.example.centromedico.model.ClinicaModel;
import com.example.centromedico.model.MedicoModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class MedicosFragment extends Fragment {

    View vista;
    private RecyclerView recyclerViewMedico;
    private MedicosRecyclerViewAdapter adapterMedico;

    //Conexion con Firebase
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        vista=inflater.inflate(R.layout.fragment_medico, container, false);

        recyclerViewMedico=(RecyclerView)vista.findViewById(R.id.recyclerMedicos);
        recyclerViewMedico.setLayoutManager(new LinearLayoutManager(this.getContext()));

        listarMedico();
        //adapterMedico = new MedicosRecyclerViewAdapter(obtenerMedicos());
        //recyclerViewMedico.setAdapter(adapterMedico);

        return vista;
    }

    private void listarMedico(){
        mDatabase = database.getReference();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    List<MedicoModel> medicos = obtenerMedicos(dataSnapshot);
                    adapterMedico = new MedicosRecyclerViewAdapter(medicos);
                    recyclerViewMedico.setAdapter(adapterMedico);
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Error: "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public ClinicaModel obtenerClinica(DataSnapshot dataSnapshot, String clinicaId){
        ClinicaModel clinica = new ClinicaModel();
        DataSnapshot dsClinica = dataSnapshot.child("clinica").child(clinicaId);
        clinica.setIdClinica(dsClinica.getValue().toString());
        clinica.setNombre(dsClinica.child("nombre").getValue().toString());
        clinica.setDireccion(dsClinica.child("direccion").getValue().toString());
        return clinica;
    }

    public List<MedicoModel> obtenerMedicos(DataSnapshot dataSnapshot){
        List<MedicoModel> medicos = new ArrayList<>();
        for(DataSnapshot clinicaSnapshot: dataSnapshot.child("medico").getChildren()){
            if(clinicaSnapshot.exists()){
                MedicoModel medico = new MedicoModel();
                String idMedico = clinicaSnapshot.child("idMedico").getValue().toString();
                String cedula = clinicaSnapshot.child("cedula").getValue().toString();
                String nombre = clinicaSnapshot.child("nombre").getValue().toString();
                String apellido = clinicaSnapshot.child("apellido").getValue().toString();
                String especialidad = clinicaSnapshot.child("especialidad").getValue().toString();
                medico.setIdDoctor(idMedico);
                medico.setCedula(cedula);
                medico.setNombre(nombre);
                medico.setApellido(apellido);
                medico.setEspecialidad(especialidad);
                medicos.add(medico);
            }
        }
        return medicos;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}