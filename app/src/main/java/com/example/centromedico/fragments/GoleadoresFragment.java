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

import com.example.centromedico.GoleadorRecyclerViewAdapter;
import com.example.centromedico.R;
import com.example.centromedico.model.ClinicaModel;
import com.example.centromedico.model.GoleadorModel;
import com.example.centromedico.model.PacienteModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GoleadoresFragment extends Fragment {

    View vista;
    private RecyclerView recyclerViewGoleador;
    private GoleadorRecyclerViewAdapter adapterGoleador;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        vista=inflater.inflate(R.layout.fragment_goleadores, container, false);

        recyclerViewGoleador=(RecyclerView)vista.findViewById(R.id.recyclerGoleadores);
        recyclerViewGoleador.setLayoutManager(new LinearLayoutManager(this.getContext()));

        listarGoleadores();

        //adapterGoleador = new GoleadorRecyclerViewAdapter(obtenerGoleadores());
        //recyclerViewGoleador.setAdapter(adapterGoleador);

        return vista;
    }

    private void listarGoleadores(){
        mDatabase = database.getReference();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    List<GoleadorModel> goleadores = obtenerGoleadores(dataSnapshot);
                    adapterGoleador = new GoleadorRecyclerViewAdapter(goleadores);
                    recyclerViewGoleador.setAdapter(adapterGoleador);
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Error: "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public List<GoleadorModel> obtenerGoleadores(DataSnapshot dataSnapshot){
        List<GoleadorModel> goleadores = new ArrayList<>();
        for(DataSnapshot goleadorSnapshot: dataSnapshot.child("goleador").getChildren()){
            if(goleadorSnapshot.exists()){
                GoleadorModel goleador = new GoleadorModel();
                String idGoleador = goleadorSnapshot.child("idGoleador").getValue().toString();
                String pacienteId = goleadorSnapshot.child("pacienteId").getValue().toString();
                String goles = goleadorSnapshot.child("goles").getValue().toString();
                goleador.setIdGoleador(idGoleador);
                goleador.setPaciente(obtenerPaciente(dataSnapshot,pacienteId));
                goleador.setGoles(Integer.parseInt(goles));
                goleadores.add(goleador);
            }
        }

        return goleadores;
    }

    public PacienteModel obtenerPaciente(DataSnapshot dataSnapshot, String pacienteId){
        PacienteModel paciente = new PacienteModel();
        DataSnapshot dsPaciente = dataSnapshot.child("paciente").child(pacienteId);
        String idPaciente = dsPaciente.getValue().toString();
        String cedula = dsPaciente.child("cedula").getValue().toString();
        String numero = dsPaciente.child("numero").getValue().toString();
        String primerNombre = dsPaciente.child("primerNombre").getValue().toString();
        String segundoNombre = dsPaciente.child("segundoNombre").getValue().toString();
        String primerApellido = dsPaciente.child("primerApellido").getValue().toString();
        String segundoApellido = dsPaciente.child("segundoApellido").getValue().toString();
        String clinicaId = dsPaciente.child("clinicaId").getValue().toString();
        paciente.setIdPaciente(idPaciente);
        paciente.setCedula(cedula);
        paciente.setHistoriaClinica(Integer.parseInt(numero));
        paciente.setPrimerNombre(primerNombre);
        paciente.setSegundoNombre(segundoNombre);
        paciente.setPrimerApellido(primerApellido);
        paciente.setSegundoApellido(segundoApellido);
        //Clinicas
        ClinicaModel clinica = obtenerClinica(dataSnapshot,clinicaId);
        paciente.setClinica(clinica);
        return paciente;
    }

    public ClinicaModel obtenerClinica(DataSnapshot dataSnapshot,String clinicaId){
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
