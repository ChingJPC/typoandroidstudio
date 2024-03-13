package com.example.typoandroidstudio.agendamiento;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.typoandroidstudio.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AgendamientoActivity extends AppCompatActivity {

    private TextView btnhora, btnfecha;
    private Spinner spinnerMascotas, spinnerActividades;
    private Button btnguardar;
    private int hora = 0;
    private int minuto = 0;
    //private Calendar fechaAgendamiento;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnhora = findViewById(R.id.btnhora);
        btnfecha = findViewById(R.id.btnfecha);
        spinnerMascotas = findViewById(R.id.spinnerMascotas);
        spinnerActividades = findViewById(R.id.spinnerActividades);
        btnguardar = findViewById(R.id.btnguardar);

        btnhora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarTimePickerDialog();
            }
        });

        btnfecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDatePickerDialog();
            }
        });

        // Aquí debes cargar las mascotas y actividades en los spinners
        cargarMascotas();
        cargarActividades();

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarActividad();
            }
        });
    }

    private void mostrarTimePickerDialog() {
        // Implementar lógica para mostrar el TimePickerDialog y actualizar el TextView
    }

    private void mostrarDatePickerDialog() {
        // Implementar lógica para mostrar el DatePickerDialog y actualizar el TextView
    }

    private void cargarMascotas() {
        // Implementar lógica para cargar las mascotas desde la base de datos y mostrarlas en el spinner
    }

    private void cargarActividades() {
        // Implementar lógica para cargar las actividades desde la base de datos y mostrarlas en el spinner
    }

    private void guardarActividad() {
        // Implementar lógica para guardar la actividad en la base de datos
    }
}

