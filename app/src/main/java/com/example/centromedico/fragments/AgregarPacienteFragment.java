package com.example.centromedico.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.centromedico.ClinicaRecyclerViewAdapter;
import com.example.centromedico.R;
import com.example.centromedico.model.ClinicaModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.List;

public class AgregarPacienteFragment extends Fragment {

    //Conexion con Firebase
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_agregar_paciente, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AgregarPacienteFragment.this)
                        .navigate(R.id.action_AgregarPacienteFragment_to_PacienteFragment);
            }
        });
        listarClinicas();
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
                Spinner spinner = (Spinner) getView().findViewById(R.id.spClinicas);
                // Create an ArrayAdapter using the string array and a default spinner layout
                ArrayAdapter<ClinicaModel> adapter =
                        new ArrayAdapter<ClinicaModel>(getContext(),android.R.layout.simple_spinner_item,clinicas);
                /*ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                        R.array.planets_array, android.R.layout.simple_spinner_item);*/
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // Apply the adapter to the spinner
                spinner.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
