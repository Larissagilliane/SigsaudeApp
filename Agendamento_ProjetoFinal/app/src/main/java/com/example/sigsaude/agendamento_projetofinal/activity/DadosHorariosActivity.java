package com.example.sigsaude.agendamento_projetofinal.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sigsaude.agendamento_projetofinal.R;
import com.example.sigsaude.agendamento_projetofinal.model.Horario;
import com.example.sigsaude.agendamento_projetofinal.model.Profissional;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class DadosHorariosActivity extends AppCompatActivity implements View.OnClickListener{

    //Variaves
    Button btnDatePicker, btnTimePicker;
    EditText txtData, txtHora;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private AppCompatSpinner edtProfissional;
    private Object profissional;

    //Referencia do firebase
    DatabaseReference myRef;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_horarios);

        //Hora
        btnDatePicker = (Button) findViewById(R.id.btnData);
        btnTimePicker = (Button) findViewById(R.id.btnHora);
        txtData = (EditText) findViewById(R.id.edt_data);
        txtHora = (EditText) findViewById(R.id.edt_hora);

        //Spinner
        edtProfissional = (AppCompatSpinner) findViewById(R.id.edtProfissional);
        myRef = FirebaseDatabase.getInstance().getReference("profissional");
        spinnerData();


        btnTimePicker.setOnClickListener(this);
        btnDatePicker.setOnClickListener(this);

    }

    //Alimenta spinner
    public void spinnerData(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.e("Spinner Data", "Spinner data is changed!");

                DataSnapshot data = dataSnapshot;
                Iterable<DataSnapshot> temp = data.getChildren();

                ArrayList<Object> itens = new ArrayList<>();

                for (DataSnapshot lists : temp){
                    Log.d("ddd","Array List: "+lists.getValue().toString());
                    itens.add(lists.getValue(Profissional.class));
                }
                showDataInSpinner(itens);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("Error", "Failed to read user", error.toException());
            }
        });
    }


    //Exibe spinner
    public void showDataInSpinner(final ArrayList<Object> data) {
        ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(
                this, android.R.layout.simple_spinner_item, data
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edtProfissional.setAdapter(adapter);

        edtProfissional.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                profissional =  data.get(position);
                String nomeP = profissional.toString();
                Toast.makeText(parent.getContext(), "Selecionado" + nomeP , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        if(v==btnDatePicker){
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                    txtData.setText(dayOfMonth + "/" + (month +1) + "/" + year);
                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

        if(v == btnTimePicker){
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
                    txtHora.setText(hourOfDay + ":" + minute);
                }
            }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }


    //Cadastra horario
    public void cadastro(View view){
        String data = txtData.getText().toString();
        String hora  = txtHora.getText().toString();
        Profissional aux = (Profissional) profissional;

        if (!TextUtils.isEmpty(data)) {

            myRef = FirebaseDatabase.getInstance().getReference("horario");
            String id = myRef.push().getKey();
            Horario horario = new Horario(data, hora, true, aux, id);


            myRef.child(id).setValue(horario);
            Toast.makeText(this, "Horario cadastrado", Toast.LENGTH_LONG).show();

            txtData.setText("");
            txtHora.setText("");

        } else{
            Toast.makeText(this, "Preencha os dados", Toast.LENGTH_LONG).show();
        }
    }

    public void cancelar(View view) {
        startActivity(new Intent(this, MainAdmActivity.class));
    }
}
