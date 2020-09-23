package com.example.centromedico.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.centromedico.SancionRecyclerViewAdapter;
import com.example.centromedico.R;
import com.example.centromedico.model.ClinicaModel;
import com.example.centromedico.model.SancionModel;
import com.example.centromedico.model.PacienteModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SancionesFragment extends Fragment {
    View vista;
    private RecyclerView recyclerViewSancion;
    private SancionRecyclerViewAdapter adapterSancion;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        vista=inflater.inflate(R.layout.fragment_sanciones, container, false);

        recyclerViewSancion=(RecyclerView)vista.findViewById(R.id.recyclerSanciones);
        recyclerViewSancion.setLayoutManager(new LinearLayoutManager(this.getContext()));

        listarSanciones();
        //adapterSancion = new SancionRecyclerViewAdapter(obtenerSanciones());
        //recyclerViewSancion.setAdapter(adapterSancion);

        return vista;
    }

    private void listarSanciones(){
        mDatabase = database.getReference();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    List<SancionModel> sanciones = obtenerSanciones(dataSnapshot);
                    adapterSancion = new SancionRecyclerViewAdapter(sanciones);
                    recyclerViewSancion.setAdapter(adapterSancion);
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Error: "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public List<SancionModel> obtenerSanciones(DataSnapshot dataSnapshot){
        List<SancionModel> sanciones = new ArrayList<>();
        for(DataSnapshot sancionSnapshot: dataSnapshot.child("sancion").getChildren()){
            if(sancionSnapshot.exists()){
                SancionModel sancion = new SancionModel();
                String idSancion = sancionSnapshot.child("idSancion").getValue().toString();
                String pacienteId = sancionSnapshot.child("pacienteId").getValue().toString();
                String detalle = sancionSnapshot.child("detalle").getValue().toString();
                sancion.setIdSancion(idSancion);
                sancion.setPaciente(obtenerPaciente(dataSnapshot,pacienteId));
                sancion.setDetalle(detalle);
                sanciones.add(sancion);
            }
        }

        return sanciones;
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
