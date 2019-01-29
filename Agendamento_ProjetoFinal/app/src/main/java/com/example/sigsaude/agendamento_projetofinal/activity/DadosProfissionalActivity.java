package com.example.sigsaude.agendamento_projetofinal.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sigsaude.agendamento_projetofinal.R;
import com.example.sigsaude.agendamento_projetofinal.model.Profissional;
import com.example.sigsaude.agendamento_projetofinal.utils.NotificationUtils;
import com.example.sigsaude.agendamento_projetofinal.utils.Unidades;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DadosProfissionalActivity extends AppCompatActivity {

    private EditText edtProfissional_Nome;
    private Spinner edtProfissional_Unidade;
    private EditText edtProfissional_Especialidade;
    private ArrayList<Profissional> aux = new ArrayList<>();
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dados_profissional);

        myRef = FirebaseDatabase.getInstance().getReference("profissional");

        //Spinner
        edtProfissional_Nome = (EditText) findViewById(R.id.edtProfissional_Nome);
        edtProfissional_Unidade = (Spinner) findViewById(R.id.edtProfissional_Unidade);
        String[] itens = new String[]{Unidades.DAS.toString(), Unidades.Departamento_de_nutrição.toString(), Unidades.Departamento_de_Odontologia.toString() , Unidades.SEPA.toString()};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, itens);
        edtProfissional_Unidade.setAdapter(adapter);

        edtProfissional_Especialidade = (EditText) findViewById(R.id.edtProfissional_Especialidade);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void cadastro(View view){

        String unidade = edtProfissional_Unidade.getSelectedItem().toString();
        String nome  = edtProfissional_Nome.getText().toString();
        String especialidade = edtProfissional_Especialidade.getText().toString();

        if (!TextUtils.isEmpty(nome)) {

            String id = myRef.push().getKey();
            Profissional profissional = new Profissional(nome, especialidade, unidade, id);
            aux.add(profissional);

            myRef.child(id).setValue(profissional);
            Toast.makeText(this, "Profissional cadastrado", Toast.LENGTH_LONG).show();

            edtProfissional_Nome.setText("");
            edtProfissional_Especialidade.setText("");

        } else{
            Toast.makeText(this, "Preencha os dados", Toast.LENGTH_LONG).show();
        }
        notify(view);
    }

    //Notificação de Horarios
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void notify(View view) {
        Notification.Builder builder = new Notification.Builder(this, NotificationUtils.getChannelId(this));

        builder.setContentTitle("Notificação");
        builder.setContentText("Lembre-se de cadastrar os Horários dos profissionais cadastrados");
        builder.setSmallIcon(android.R.drawable.sym_action_chat);

        Intent intent = new Intent(this,MainAdmActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        Notification notification =  builder.build();
        NotificationManager nm =  (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(50,notification);
    }


    public void cancelar(View view) {
        startActivity(new Intent(this, MainAdmActivity.class));
    }
}
