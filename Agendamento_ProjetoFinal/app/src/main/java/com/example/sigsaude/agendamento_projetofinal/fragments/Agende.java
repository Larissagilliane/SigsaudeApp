package com.example.sigsaude.agendamento_projetofinal.fragments;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.annotation.NonNull;
        import android.support.v4.app.Fragment;
        import android.support.v7.widget.AppCompatSpinner;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.Toast;

        import com.example.sigsaude.agendamento_projetofinal.R;
        import com.example.sigsaude.agendamento_projetofinal.model.Agendamento;
        import com.example.sigsaude.agendamento_projetofinal.model.Horario;
        import com.example.sigsaude.agendamento_projetofinal.model.Paciente;
        import com.example.sigsaude.agendamento_projetofinal.model.Profissional;
        import com.example.sigsaude.agendamento_projetofinal.utils.HorariosList;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

        import java.io.Serializable;
        import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.List;

public class Agende extends Fragment {

    private AppCompatSpinner edtProfissional;
    private Object profissional;
    private Button btnBuscar;
    private EditText edtcpf;
    private String cpf;
    private Paciente pacienteAux;


    //Exibição
    private ListView listView;

    //Horarios a serem exibidos
    private List<Horario> horarios;


    DatabaseReference myRef;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_agende, container, false);

        //Spinner
        edtProfissional = (AppCompatSpinner) view.findViewById(R.id.edtProfissional);
        edtcpf = (EditText) view.findViewById(R.id.edtcpfCad);
        myRef = FirebaseDatabase.getInstance().getReference("profissional");
        spinnerData();


        btnBuscar = view.findViewById(R.id.btnBuscar);
        listView = (ListView)view.findViewById(R.id.list_view);
        cpf = "";
        pacienteAux = new Paciente();

        horarios = new ArrayList<>();

        //Busca os horarios para aquele profissional
        btnBuscar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                final Profissional aux = (Profissional) profissional;
                cpf = edtcpf.getText().toString();
                myRef = FirebaseDatabase.getInstance().getReference("horario");

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        horarios.clear();

                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            Horario horario = postSnapshot.getValue(Horario.class);
                            if(horario.getProfissional().getId().equals(aux.getId()) && horario.getDisponivel())
                            {
                                horarios.add(horario);
                            }
                        }
                        HorariosList artistAdapter = new HorariosList(getActivity(), horarios);
                        listView.setAdapter(artistAdapter);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });


        //Agenda cosulta ao seleciona horario
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Horario horarioItem = horarios.get(position);

                myRef = FirebaseDatabase.getInstance().getReference("paciente");
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                            Paciente paciente = userSnapshot.getValue(Paciente.class);
                            if (paciente.getCpf().equals(cpf))
                            {
                                pacienteAux = paciente;
                                myRef = FirebaseDatabase.getInstance().getReference("agendamento");
                                String identificador = myRef.push().getKey();
                                horarioItem.setDisponivel(false);
                                Agendamento agnd = new Agendamento(identificador, pacienteAux, horarioItem );
                                myRef.child(identificador).setValue(agnd);
                                Toast.makeText(getContext(),"Agendamento realizado com sucesso!", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        return view;
    }


    public void spinnerData(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.e("Spinner Data", "Spinner data is changed!");

                DataSnapshot data = dataSnapshot;
                Iterable<DataSnapshot> temp = data.getChildren();

                ArrayList<Object> itens = new ArrayList<>();

                for (DataSnapshot lists : temp){
                    Log.d("ddd","Array List: "+lists.getValue().toString());
                    itens.add(lists.getValue(Profissional.class));
                }
                showDataInSpinner(itens);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("Error", "Failed to read user", error.toException());
            }
        });
    }


    public void showDataInSpinner(final ArrayList<Object> data) {
        ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(
                getContext(), android.R.layout.simple_spinner_item, data
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edtProfissional.setAdapter(adapter);

        edtProfissional.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                profissional =  data.get(position);
                String nomeP = profissional.toString();
                Toast.makeText(parent.getContext(), "Selecionado" + nomeP , Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}

