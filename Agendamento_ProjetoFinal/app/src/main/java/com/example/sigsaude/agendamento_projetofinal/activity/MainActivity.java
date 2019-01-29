package com.example.sigsaude.agendamento_projetofinal.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.sigsaude.agendamento_projetofinal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    public void cadastrar(View view) {
        startActivity(new Intent(this, SingUpActivity.class));
    }

    public void login(View view) { startActivity(new Intent(this, LoginActivity.class)); }

    public void administrador(View view) {
        startActivity(new Intent(this, AdministradorActivity.class));
    }
}
