package com.example.sigsaude.agendamento_projetofinal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sigsaude.agendamento_projetofinal.R;
import com.example.sigsaude.agendamento_projetofinal.model.Administrador;
import com.example.sigsaude.agendamento_projetofinal.utils.Codifier;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginAdmActivity extends AppCompatActivity {
    private EditText edtCPF;
    private EditText edtSenha;

    private String cpf;
    private String senha;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_adm);
        myRef = FirebaseDatabase.getInstance().getReference("administrador");

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
                        Administrador adm = userSnapshot.getValue(Administrador.class);
                        String senha2 = adm.getSenha();

                        //Verifica senha
                        if(adm.getSenha().equals(senha)) {

                            Toast.makeText(getApplicationContext(), "Olá " + adm.getNome() + "!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), MainAdmActivity.class);
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
        startActivity(new Intent(this, AdministradorActivity.class));
    }

}
