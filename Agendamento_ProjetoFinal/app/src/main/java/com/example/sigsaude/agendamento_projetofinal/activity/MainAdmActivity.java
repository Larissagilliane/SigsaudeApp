package com.example.sigsaude.agendamento_projetofinal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.sigsaude.agendamento_projetofinal.R;

public class MainAdmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mainadm);

    }

    public void profissional(View view) {
        startActivity(new Intent(this, DadosProfissionalActivity.class));
    }

    public void horario(View view) { startActivity(new Intent(this, DadosHorariosActivity.class)); }

    public void cancelar(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

}
