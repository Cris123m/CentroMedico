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

import com.example.centromedico.HistoriaClinicaRecyclerViewAdapter;
import com.example.centromedico.R;
import com.example.centromedico.model.ClinicaModel;
import com.example.centromedico.model.DetalleHistoriaClinicaModel;
import com.example.centromedico.model.HistoriaClinicaModel;
import com.example.centromedico.model.MedicoModel;
import com.example.centromedico.model.PacienteModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HistoriaClinicaFragment extends Fragment {

    View vista;
    private RecyclerView recyclerViewHistoriaClinica;
    private HistoriaClinicaRecyclerViewAdapter adapterHistoriaClinica;

    //Conexion con Firebase
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        vista=inflater.inflate(R.layout.fragment_historia_clinica, container, false);

        recyclerViewHistoriaClinica=(RecyclerView)vista.findViewById(R.id.recyclerHistoriaClinica);
        recyclerViewHistoriaClinica.setLayoutManager(new LinearLayoutManager(this.getContext()));

        listarHistoriaClinica();
        //adapterHistoriaClinica = new HistoriaClinicaRecyclerViewAdapter(obtenerHistoriaClinicas());
        //recyclerViewHistoriaClinica.setAdapter(adapterHistoriaClinica);

        return vista;
    }

    private void listarHistoriaClinica(){
        mDatabase = database.getReference();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    //List<HistoriaClinicaModel> historiaClinicas = obtenerHistoriaClinicas(dataSnapshot);
                    //adapterHistoriaClinica = new HistoriaClinicaRecyclerViewAdapter(historiaClinicas);
                    List<DetalleHistoriaClinicaModel> detalleHistoriaClinicas = obtenerDetallesHistoriasClinicas(dataSnapshot);
                    adapterHistoriaClinica = new HistoriaClinicaRecyclerViewAdapter(detalleHistoriaClinicas);
                    recyclerViewHistoriaClinica.setAdapter(adapterHistoriaClinica);
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Error HCF: "+e.toString(), Toast.LENGTH_SHORT).show();
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

    public PacienteModel obtenerPaciente(DataSnapshot dataSnapshot,String pacienteId){
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

    public MedicoModel obtenerMedico (DataSnapshot dataSnapshot,String medicoId){
        MedicoModel medico = new MedicoModel();
        DataSnapshot dsMedico = dataSnapshot.child("medico").child(medicoId);
        medico.setIdDoctor(dsMedico.getValue().toString());
        medico.setCedula(dsMedico.child("cedula").getValue().toString());
        medico.setNombre(dsMedico.child("nombre").getValue().toString());
        medico.setApellido(dsMedico.child("apellido").getValue().toString());
        medico.setEspecialidad(dsMedico.child("especialidad").getValue().toString());
        return medico;
    }

    public HistoriaClinicaModel obtenerHistoriaClinica (DataSnapshot dataSnapshot,String historiaClinicaId){
        HistoriaClinicaModel historiaClinica = new HistoriaClinicaModel();
        DataSnapshot dsHistoriaClinica = dataSnapshot.child("historiaClinica").child(historiaClinicaId);
        historiaClinica.setIdHistoriaClinica(dsHistoriaClinica.child("idHistoriaClinica").getValue().toString());
        PacienteModel paciente = obtenerPaciente(dataSnapshot,dsHistoriaClinica.child("pacienteId").getValue().toString());
        historiaClinica.setPaciente(paciente);
        return historiaClinica;
    }

    public List<HistoriaClinicaModel> obtenerHistoriaClinicas(DataSnapshot dataSnapshot){
        List<HistoriaClinicaModel> historiaClinicas = new ArrayList<>();
        for(DataSnapshot clinicaSnapshot: dataSnapshot.child("historiaClinica").getChildren()){
            if(clinicaSnapshot.exists()){
                HistoriaClinicaModel historiaClinica = new HistoriaClinicaModel();
                String idHistoriaClinica = clinicaSnapshot.child("idHistoriaClinica").getValue().toString();
                historiaClinica=obtenerHistoriaClinica(dataSnapshot,idHistoriaClinica);
                historiaClinicas.add(historiaClinica);
            }
        }
        return historiaClinicas;
    }

    public List<DetalleHistoriaClinicaModel> obtenerDetallesHistoriasClinicas(DataSnapshot dataSnapshot){
        List<DetalleHistoriaClinicaModel> detallesHistoriaClinicas = new ArrayList<>();
        try {
            for(DataSnapshot clinicaSnapshot: dataSnapshot.child("detalleHistoriaClinica").getChildren()){
                if(clinicaSnapshot.exists()){
                    DetalleHistoriaClinicaModel detalleHistoriaClinica = new DetalleHistoriaClinicaModel();
                    String idDetalleHistoriaClinica = clinicaSnapshot.child("idDetalleHistoriaClinica").getValue().toString();
                    HistoriaClinicaModel historiaClinica = obtenerHistoriaClinica(dataSnapshot,clinicaSnapshot.child("historiaClinicaId").getValue().toString());
                    MedicoModel medico = obtenerMedico(dataSnapshot,clinicaSnapshot.child("medicoId").getValue().toString());
                    String procedimiento = clinicaSnapshot.child("procedimiento").getValue().toString();
                    String detalle = clinicaSnapshot.child("detalle").getValue().toString();
                    detalleHistoriaClinica.setIdDetalleHistoriaClinica(idDetalleHistoriaClinica);
                    detalleHistoriaClinica.setHistoriaClinica(historiaClinica);
                    detalleHistoriaClinica.setMedico(medico);
                    detalleHistoriaClinica.setProcedimiento(procedimiento);
                    detalleHistoriaClinica.setDetalle(detalle);
                    detallesHistoriaClinicas.add(detalleHistoriaClinica);
                }
            }
        }catch (Exception e){
            Toast.makeText(getActivity(), "Error ODHC: "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        return detallesHistoriaClinicas;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
