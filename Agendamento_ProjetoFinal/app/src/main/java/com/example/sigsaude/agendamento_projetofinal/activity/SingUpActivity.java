package com.example.sigsaude.agendamento_projetofinal.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.sigsaude.agendamento_projetofinal.R;
import com.example.sigsaude.agendamento_projetofinal.model.Paciente;

import com.example.sigsaude.agendamento_projetofinal.utils.Codifier;
import com.example.sigsaude.agendamento_projetofinal.utils.ToastReceiver;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SingUpActivity extends AppCompatActivity {

    private EditText edtSenha;
    private EditText edtEmail;
    private EditText edtNome;
    private EditText edtmother_name;
    private EditText edtbirth;
    private EditText edtcpf;
    private EditText edtubs;
    private RadioButton woman;
    DatabaseReference myRef;
    private ToastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_singup);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        myRef = FirebaseDatabase.getInstance().getReference("paciente");

        edtEmail = findViewById(R.id.edtEmailCad);
        edtNome = findViewById(R.id.edtNomeCad);
        edtSenha = findViewById(R.id.edtSenhaCad);
        edtmother_name = findViewById(R.id.edtmother_nameCad);
        edtcpf = findViewById(R.id.edtcpfCad);
        edtubs = findViewById(R.id.edtubsCad);
        edtbirth = findViewById(R.id.inputBirthday);
        woman = findViewById( R.id.rbtnWoman);

        receiver = new ToastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.sigsaude.android.broadcast.TOAST");
        registerReceiver(receiver,intentFilter);

    }

    @Override
    protected void onDestroy() {
        if(receiver != null){
            unregisterReceiver(receiver);
        }

        super.onDestroy();
    }

    public void cadastro(View view){

        String email = edtEmail.getText().toString().trim();
        String senha = Codifier.md5(edtSenha.getText().toString());
        String name  = edtNome.getText().toString();
        String mother_name = edtmother_name.getText().toString();
        String cpf = edtcpf.getText().toString();
        String ubs = edtubs.getText().toString();
        String birth = edtbirth.getText().toString();
        String gender = "";

        if (woman.isChecked())
            gender = "F";
        else
            gender = "M";

        if (!TextUtils.isEmpty(name)) {

            String id = myRef.push().getKey();
            Paciente paciente = new Paciente(id, name, mother_name, birth, cpf, ubs, email, senha, gender);

            myRef.child(id).setValue(paciente);

            edtEmail.setText("");
            edtSenha.setText("");
            edtNome.setText("");
            edtmother_name.setText("");
            edtcpf.setText("");
            edtubs.setText("");
            edtbirth.setText("");

        } else{
            Toast.makeText(this, "Preencha os dados", Toast.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent("com.example.sigsaude.android.broadcast.TOAST");
        intent.putExtra("msg","Paciente cadastrado");
        sendBroadcast(intent);

    }
    public void cancelar(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
