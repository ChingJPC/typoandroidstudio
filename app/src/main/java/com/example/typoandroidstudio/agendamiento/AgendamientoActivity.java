package com.example.typoandroidstudio.agendamiento;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.typoandroidstudio.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AgendamientoActivity extends AppCompatActivity {

    private TextView textViewFecha;
    private Button btnSeleccionarFecha;
    private TextView textViewTiempo;
    private EditText editTextTiempo;
    private RadioGroup radioGroupCumplida;
    private Button btnAgregarAgendamiento;
    private int hora = 0;
    private int minuto = 0;
    private Calendar fechaAgendamiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamiento);

        textViewFecha = findViewById(R.id.textViewFecha);
        btnSeleccionarFecha = findViewById(R.id.btnSeleccionarFecha);
        textViewTiempo = findViewById(R.id.textViewTiempo);
        editTextTiempo = findViewById(R.id.editTextTiempo);
        radioGroupCumplida = findViewById(R.id.radioGroupCumplida);
        btnAgregarAgendamiento = findViewById(R.id.btnAgregarAgendamiento);

        fechaAgendamiento = Calendar.getInstance();

        btnSeleccionarFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarTimePicker();
            }
        });

        btnAgregarAgendamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarAgendamiento();
            }
        });
    }

    private void mostrarDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        fechaAgendamiento.set(year, month, dayOfMonth);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        textViewFecha.setText(dateFormat.format(fechaAgendamiento.getTime()));
                    }
                },
                fechaAgendamiento.get(Calendar.YEAR),
                fechaAgendamiento.get(Calendar.MONTH),
                fechaAgendamiento.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void mostrarTimePicker() {

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this, new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                TextView editTextTiempo = findViewById(R.id.textViewTiempo);
                editTextTiempo.setText(String.format("%02d:%02d", hourOfDay, minute));
            }
        },
                hora,minuto,false
        );
        timePickerDialog.show();
    }

    private void insertarAgendamiento() {
        int tiempoAsignado = Integer.parseInt(editTextTiempo.getText().toString());
        boolean cumplida = radioGroupCumplida.getCheckedRadioButtonId() == R.id.radioButtonSi;
        finish();
    }
}
