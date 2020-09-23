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

import com.example.centromedico.PacienteRecyclerViewAdapter;
import com.example.centromedico.R;
import com.example.centromedico.model.PacienteModel;
import com.example.centromedico.model.ClinicaModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PacientesFragment extends Fragment {

    View vista;
    private RecyclerView recyclerViewPaciente;
    private PacienteRecyclerViewAdapter adapterPaciente;

    //Conexion con Firebase
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        vista=inflater.inflate(R.layout.fragment_pacientes, container, false);

        recyclerViewPaciente=(RecyclerView)vista.findViewById(R.id.recyclerPacientes);
        recyclerViewPaciente.setLayoutManager(new LinearLayoutManager(this.getContext()));

        listarPaciente();
        //adapterPaciente = new PacienteRecyclerViewAdapter(obtenerPacientes());
        //recyclerViewPaciente.setAdapter(adapterPaciente);

        return vista;
    }

    private void listarPaciente(){
        mDatabase = database.getReference();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    List<PacienteModel> pacientes = obtenerPacientes(dataSnapshot);
                    adapterPaciente = new PacienteRecyclerViewAdapter(pacientes);
                    recyclerViewPaciente.setAdapter(adapterPaciente);
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Error: "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public ClinicaModel obtenerClinica(DataSnapshot dataSnapshot,String clinicaId){
        ClinicaModel clinica = new ClinicaModel();
        DataSnapshot dsClinica = dataSnapshot.child("clinica").child(clinicaId);
        clinica.setIdClinica(dsClinica.getValue().toString());
        clinica.setNombre(dsClinica.child("nombre").getValue().toString());
        clinica.setDireccion(dsClinica.child("direccion").getValue().toString());
        return clinica;
    }

    public List<PacienteModel> obtenerPacientes(DataSnapshot dataSnapshot){
        List<PacienteModel> pacientes = new ArrayList<>();
        for(DataSnapshot clinicaSnapshot: dataSnapshot.child("paciente").getChildren()){
            if(clinicaSnapshot.exists()){
                PacienteModel paciente = new PacienteModel();
                String idPaciente = clinicaSnapshot.child("idPaciente").getValue().toString();
                String cedula = clinicaSnapshot.child("cedula").getValue().toString();
                String historiaClinica = clinicaSnapshot.child("historiaClinica").getValue().toString();
                String primerNombre = clinicaSnapshot.child("primerNombre").getValue().toString();
                String segundoNombre = clinicaSnapshot.child("segundoNombre").getValue().toString();
                String primerApellido = clinicaSnapshot.child("primerApellido").getValue().toString();
                String segundoApellido = clinicaSnapshot.child("segundoApellido").getValue().toString();
                String clinicaId = clinicaSnapshot.child("clinicaId").getValue().toString();
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
                pacientes.add(paciente);
            }
        }
        return pacientes;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
