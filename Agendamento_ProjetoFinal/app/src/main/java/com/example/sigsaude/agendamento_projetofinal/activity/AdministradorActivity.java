package com.example.sigsaude.agendamento_projetofinal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.sigsaude.agendamento_projetofinal.R;

public class AdministradorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);

    }

    public void cadastrarAdm(View view) {
        startActivity(new Intent(this, SingUpAdmActivity.class));
    }

    public void loginAdm(View view) {
        startActivity(new Intent(this, LoginAdmActivity.class));
    }

    public void cancelar(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
