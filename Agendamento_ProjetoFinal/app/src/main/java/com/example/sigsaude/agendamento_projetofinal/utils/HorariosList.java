package com.example.sigsaude.agendamento_projetofinal.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sigsaude.agendamento_projetofinal.R;
import com.example.sigsaude.agendamento_projetofinal.model.Horario;

import java.util.List;

public class HorariosList  extends ArrayAdapter<Horario>  {
    private Activity context;
    List<Horario> horarios;


    public HorariosList(Activity context, List<Horario> horarios) {
        super(context, R.layout.layout_horarios_list, horarios);
        this.context = context;
        this.horarios = horarios;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_horarios_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewGenre = (TextView) listViewItem.findViewById(R.id.textViewGenre);

        Horario horario = horarios.get(position);
        textViewName.setText(horario.getData());
        textViewGenre.setText(horario.getProfissional().toString());

        return listViewItem;
    }
}
