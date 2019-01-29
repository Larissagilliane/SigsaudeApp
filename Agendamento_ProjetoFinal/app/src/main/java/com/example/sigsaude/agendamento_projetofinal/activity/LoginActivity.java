package com.example.sigsaude.agendamento_projetofinal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sigsaude.agendamento_projetofinal.R;
import com.example.sigsaude.agendamento_projetofinal.model.Paciente;
import com.example.sigsaude.agendamento_projetofinal.utils.Codifier;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity{
    private EditText edtCPF;
    private EditText edtSenha;

    private String cpf;
    private String senha;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_login);
        myRef = FirebaseDatabase.getInstance().getReference("paciente");

        edtCPF = findViewById(R.id.edtCPF);
        edtSenha = findViewById(R.id.edtSenha);
    }

    public void entrar(View view){
        cpf = edtCPF.getText().toString().trim();
        senha = Codifier.md5(edtSenha.getText().toString());

        if(cpf.equals("")){
            edtCPF.setError("Este campo precisa ser preenchido!");
            Toast.makeText(this, "Preencha os dados", Toast.LENGTH_LONG).show();
            return;
        }
        if(senha.equals("")){
            edtSenha.setError("Este campo precisa ser preenchido!");
            Toast.makeText(this, "Preencha os dados", Toast.LENGTH_LONG).show();
            return;
        }

        myRef.orderByChild("cpf").equalTo(cpf).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()) {
                    Toast.makeText(getApplicationContext(), "CPF não existe!", Toast.LENGTH_LONG).show();
                } else {

                    for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                        Paciente paciente = userSnapshot.getValue(Paciente.class);
                        //Verifica senha
                        if(paciente.getPassword().equals(senha)) {

                            Toast.makeText(getApplicationContext(), "Olá " + paciente.getName() + "!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), InicialActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Senha inválida!", Toast.LENGTH_LONG).show();
                        }

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode ==10){
            String nome = data.getStringExtra("nome");
            Toast.makeText(this,"Nome"+ nome,Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelar(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
