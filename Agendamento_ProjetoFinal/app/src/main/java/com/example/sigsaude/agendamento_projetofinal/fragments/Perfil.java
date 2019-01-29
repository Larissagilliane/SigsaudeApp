package com.example.sigsaude.agendamento_projetofinal.fragments;


import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.sigsaude.agendamento_projetofinal.R;
import com.example.sigsaude.agendamento_projetofinal.activity.InicialActivity;
import com.example.sigsaude.agendamento_projetofinal.model.Paciente;

import com.example.sigsaude.agendamento_projetofinal.utils.Codifier;
import com.example.sigsaude.agendamento_projetofinal.utils.ToastReceiver;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Perfil extends Fragment  {

    private EditText edtSenha;
    private EditText edtEmail;
    private EditText edtNome;
    private EditText edtmother_name;
    private EditText edtbirth;
    private EditText edtcpf;
    private EditText edtubs;
    private RadioButton woman;
    private Button btnAtualizar;
    DatabaseReference myRef;
    private Paciente pacienteAux;

    String email;
    String senha ;
    String name ;
    String mother_name ;
    String cpf ;
    String ubs ;
    String birth ;
    String gender ;
    String id ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        edtEmail = view.findViewById(R.id.edtEmailCad);
        edtNome = view.findViewById(R.id.edtNomeCad);
        edtSenha = view.findViewById(R.id.edtSenhaCad);
        edtmother_name = view.findViewById(R.id.edtmother_nameCad);
        edtcpf = view.findViewById(R.id.edtcpfCad);
        edtubs = view.findViewById(R.id.edtubsCad);
        edtbirth = view.findViewById(R.id.inputBirthday);
        woman = view.findViewById( R.id.rbtnWoman);
        btnAtualizar = view.findViewById(R.id.btnAtualizar);
        email = "";
        senha = "";
        name  = "";
        mother_name = "";
        cpf = "";
        ubs = "";
        birth = "";
        gender = "";
        id = "";



        btnAtualizar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                email = edtEmail.getText().toString().trim();
                senha = Codifier.md5(edtSenha.getText().toString());
                name  = edtNome.getText().toString();
                mother_name = edtmother_name.getText().toString();
                cpf = edtcpf.getText().toString().trim();
                ubs = edtubs.getText().toString();
                birth = edtbirth.getText().toString();
                gender = "";
                id = "";

                if (woman.isChecked())
                    gender = "F";
                else
                    gender = "M";


                myRef = FirebaseDatabase.getInstance().getReference("paciente");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                            Paciente paciente = userSnapshot.getValue(Paciente.class);
                            if (paciente.getCpf().equals(cpf))
                            {
                                id = paciente.getId();
                                pacienteAux = new Paciente(id, name, mother_name, birth, cpf, ubs, email, senha, gender);
                                myRef.child(id).setValue(pacienteAux);
                                Toast.makeText(getContext(), "Dados atualizados", Toast.LENGTH_LONG).show();
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
}
