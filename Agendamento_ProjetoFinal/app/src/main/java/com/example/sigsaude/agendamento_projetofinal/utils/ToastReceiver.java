package com.example.sigsaude.agendamento_projetofinal.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ToastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String msg = intent.getStringExtra("msg");
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
