package com.example.sigsaude.agendamento_projetofinal.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.sigsaude.agendamento_projetofinal.R;
import com.example.sigsaude.agendamento_projetofinal.model.Administrador;
import com.example.sigsaude.agendamento_projetofinal.model.Paciente;
import com.example.sigsaude.agendamento_projetofinal.utils.Codifier;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SingUpAdmActivity extends AppCompatActivity {

    private EditText edtSenha;
    private EditText edtNome;
    private EditText edtcpf;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_singup_adm);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        myRef = FirebaseDatabase.getInstance().getReference("administrador");

        edtNome = findViewById(R.id.edtNomeCad);
        edtSenha = findViewById(R.id.edtSenhaCad);
        edtcpf = findViewById(R.id.edtcpfCad);

    }

    public void cadastro(View view){

        String senha = Codifier.md5(edtSenha.getText().toString());
        String name  = edtNome.getText().toString();
        String cpf = edtcpf.getText().toString();

        if (!TextUtils.isEmpty(name)) {

            String id = myRef.push().getKey();
            Administrador adm = new Administrador(id, name, cpf, senha);

            myRef.child(id).setValue(adm);
            Toast.makeText(this, "Administrador cadastrado", Toast.LENGTH_LONG).show();

            edtSenha.setText("");
            edtNome.setText("");
            edtcpf.setText("");

        } else{
            Toast.makeText(this, "Preencha os dados", Toast.LENGTH_LONG).show();
        }

    }
}
