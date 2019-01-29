package com.example.sigsaude.agendamento_projetofinal.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sigsaude.agendamento_projetofinal.R;
import com.example.sigsaude.agendamento_projetofinal.activity.LoginActivity;


public class Inicial extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inicial, container, false);
        return view;
    }


}
