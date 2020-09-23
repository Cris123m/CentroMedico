package com.example.centromedico.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.centromedico.CalendarioRecyclerViewAdapter;
import com.example.centromedico.ClinicaRecyclerViewAdapter;
import com.example.centromedico.R;
import com.example.centromedico.model.CalendarioModel;
import com.example.centromedico.model.ClinicaModel;
import com.example.centromedico.model.MedicoModel;
import com.example.centromedico.model.PacienteModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalendarioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalendarioFragment extends Fragment {
    View vista;
    private RecyclerView recyclerViewCalendario;
    private CalendarioRecyclerViewAdapter adapterCalendario;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CalendarioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalendarioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalendarioFragment newInstance(String param1, String param2) {
        CalendarioFragment fragment = new CalendarioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista=inflater.inflate(R.layout.fragment_calendario, container, false);

        recyclerViewCalendario=(RecyclerView)vista.findViewById(R.id.recyclerCalendario);
        recyclerViewCalendario.setLayoutManager(new LinearLayoutManager(this.getContext()));

        listarCalendario();
        //adapterCalendario = new CalendarioRecyclerViewAdapter(obtenerCalendario());
        //recyclerViewCalendario.setAdapter(adapterCalendario);

        return vista;
    }

    private void listarCalendario(){
        mDatabase = database.getReference();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    List<CalendarioModel> calendarios = new ArrayList<>();
                    for(DataSnapshot clinicaSnapshot: dataSnapshot.child("calendario").getChildren()){
                        if(clinicaSnapshot.exists()){
                            CalendarioModel calendario = new CalendarioModel();
                            String idCalendario = clinicaSnapshot.child("idCalendario").getValue().toString();
                            String fecha = clinicaSnapshot.child("fecha").getValue().toString();
                            String idFase = clinicaSnapshot.child("idFase").getValue().toString();
                            String medicoId = clinicaSnapshot.child("medicoId").getValue().toString();
                            String pacienteId = clinicaSnapshot.child("pacienteId").getValue().toString();
                            Date fechaD = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
                            calendario.setIdCalendario(idCalendario);
                            calendario.setFecha(fechaD);
                            calendario.setIdFase(idFase);
                            //Clinicas
                            MedicoModel medico = new MedicoModel();
                            PacienteModel paciente = new PacienteModel();
                            DataSnapshot dsMedico = dataSnapshot.child("medico").child(medicoId);
                            DataSnapshot dsPaciente = dataSnapshot.child("paciente").child(pacienteId);
                            medico.setIdDoctor(dsMedico.getValue().toString());
                            medico.setCedula(dsMedico.child("cedula").getValue().toString());
                            medico.setNombre(dsMedico.child("nombre").getValue().toString());
                            medico.setApellido(dsMedico.child("apellido").getValue().toString());
                            medico.setEspecialidad(dsMedico.child("especialidad").getValue().toString());
                            paciente.setIdPaciente(dsPaciente.getValue().toString());
                            paciente.setPrimerNombre(dsPaciente.child("primerNombre").getValue().toString());
                            paciente.setSegundoNombre(dsPaciente.child("segundoNombre").getValue().toString());
                            paciente.setPrimerApellido(dsPaciente.child("primerApellido").getValue().toString());
                            paciente.setSegundoApellido(dsPaciente.child("segundoApellido").getValue().toString());
                            paciente.setCedula(dsPaciente.child("cedula").getValue().toString());
                            paciente.setHistoriaClinica(Integer.parseInt(dsPaciente.child("historiaClinica").getValue().toString()));
                            paciente.setClinica(new ClinicaModel("","",""));
                            calendario.setMedico(medico);
                            calendario.setPaciente(paciente);
                            calendarios.add(calendario);
                        }
                    }
                    adapterCalendario = new CalendarioRecyclerViewAdapter(calendarios);
                    recyclerViewCalendario.setAdapter(adapterCalendario);
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Error: "+e.toString(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /*public List<CalendarioModel> obtenerCalendario(){
        List<CalendarioModel> calendario = new ArrayList<>();
        MedicoModel medico = new MedicoModel("1","Liga","LDU");
        PacienteModel paciente = new PacienteModel("1","Independiente del Valle","IDV");
        calendario.add(new CalendarioModel("1",new Date(),"Primera",medico,paciente));
        calendario.add(new CalendarioModel("2",new Date(),"Segunda",paciente,medico));

        return calendario;
    }*/
}
