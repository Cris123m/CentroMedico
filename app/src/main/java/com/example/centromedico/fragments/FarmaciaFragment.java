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

import com.example.centromedico.FarmaciaRecyclerViewAdapter;
import com.example.centromedico.FarmaciaRecyclerViewAdapter;
import com.example.centromedico.R;
import com.example.centromedico.fragments.dummy.DummyContent;
import com.example.centromedico.model.ClinicaModel;
import com.example.centromedico.model.FarmaciaModel;
import com.example.centromedico.model.PacienteModel;
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
public class FarmaciaFragment extends Fragment {

    View vista;
    private RecyclerView recyclerViewFarmacia;
    private FarmaciaRecyclerViewAdapter adapterFarmacia;

    //Conexion con Firebase
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        vista=inflater.inflate(R.layout.fragment_farmacia, container, false);

        recyclerViewFarmacia=(RecyclerView)vista.findViewById(R.id.recyclerFarmacia);
        recyclerViewFarmacia.setLayoutManager(new LinearLayoutManager(this.getContext()));

        listarFarmacia();
        //adapterFarmacia = new FarmaciaRecyclerViewAdapter(obtenerFarmacias());
        //recyclerViewFarmacia.setAdapter(adapterFarmacia);

        return vista;
    }

    private void listarFarmacia(){
        mDatabase = database.getReference();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    List<FarmaciaModel> farmacias = obtenerFarmacias(dataSnapshot);
                    adapterFarmacia = new FarmaciaRecyclerViewAdapter(farmacias);
                    recyclerViewFarmacia.setAdapter(adapterFarmacia);
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Error: "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public PacienteModel obtenerPaciente(DataSnapshot dataSnapshot, String pacienteId){
        PacienteModel paciente = new PacienteModel();
        DataSnapshot dsPaciente = dataSnapshot.child("paciente").child(pacienteId);
        String idPaciente = dsPaciente.getValue().toString();
        String cedula = dsPaciente.child("cedula").getValue().toString();
        String historiaClinica = dsPaciente.child("historiaClinica").getValue().toString();
        String primerNombre = dsPaciente.child("primerNombre").getValue().toString();
        String segundoNombre = dsPaciente.child("segundoNombre").getValue().toString();
        String primerApellido = dsPaciente.child("primerApellido").getValue().toString();
        String segundoApellido = dsPaciente.child("segundoApellido").getValue().toString();
        String clinicaId = dsPaciente.child("clinicaId").getValue().toString();
        paciente.setIdPaciente(idPaciente);
        paciente.setCedula(cedula);
        paciente.setHistoriaClinica(Integer.parseInt(historiaClinica));
        paciente.setPrimerNombre(primerNombre);
        paciente.setSegundoNombre(segundoNombre);
        paciente.setPrimerApellido(primerApellido);
        paciente.setSegundoApellido(segundoApellido);
        //Clinicas
        ClinicaModel clinica = obtenerClinica(dataSnapshot,clinicaId);
        paciente.setClinica(clinica);
        return paciente;
    }

    public ClinicaModel obtenerClinica(DataSnapshot dataSnapshot, String clinicaId){
        ClinicaModel clinica = new ClinicaModel();
        DataSnapshot dsClinica = dataSnapshot.child("clinica").child(clinicaId);
        clinica.setIdClinica(dsClinica.getValue().toString());
        clinica.setNombre(dsClinica.child("nombre").getValue().toString());
        clinica.setDireccion(dsClinica.child("direccion").getValue().toString());
        return clinica;
    }

    public List<FarmaciaModel> obtenerFarmacias(DataSnapshot dataSnapshot){
        List<FarmaciaModel> farmacias = new ArrayList<>();
        for(DataSnapshot clinicaSnapshot: dataSnapshot.child("farmacia").getChildren()){
            if(clinicaSnapshot.exists()){
                FarmaciaModel farmacia = new FarmaciaModel();
                String idFarmacia = clinicaSnapshot.child("idFarmacia").getValue().toString();
                PacienteModel paciente = obtenerPaciente(dataSnapshot,clinicaSnapshot.child("pacienteId").getValue().toString());
                String descripcion = clinicaSnapshot.child("descripcion").getValue().toString();
                farmacia.setIdFarmacia(idFarmacia);
                farmacia.setPaciente(paciente);
                farmacia.setDescripcion(descripcion);
                farmacias.add(farmacia);
            }
        }
        return farmacias;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}