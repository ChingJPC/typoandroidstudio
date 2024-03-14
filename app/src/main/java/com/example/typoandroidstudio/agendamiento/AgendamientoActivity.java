package com.example.typoandroidstudio.agendamiento;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import com.example.typoandroidstudio.Datainfo;
import com.example.typoandroidstudio.R;
import com.example.typoandroidstudio.model.Mascota;
import com.example.typoandroidstudio.network.MascotaAPIS.MascotaAPIClient;
import com.example.typoandroidstudio.network.MascotaAPIS.MascotaAPIService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgendamientoActivity extends AppCompatActivity {
    MascotaAPIService service;
    private Button btnhora;
    private Button btnfecha;
    private Button btnguardar;
    private Spinner spinnerMascotas, spinnerActividades;
    private TextView textViewTiempo, textViewFecha;
    private int hora = 0;
    private int minuto = 0;
    private Calendar fechaAgendamiento;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamiento);

        btnhora = findViewById(R.id.btnhora);
        btnfecha = findViewById(R.id.btnfecha);
        spinnerMascotas = findViewById(R.id.spinnerMascotas);
        spinnerActividades = findViewById(R.id.spinnerActividades);
        btnguardar = findViewById(R.id.btnguardar);

        textViewFecha = findViewById(R.id.textViewFecha);
        textViewTiempo = findViewById(R.id.textViewTiempo);
        fechaAgendamiento = Calendar.getInstance();

        //Spinner Mascota
        Spinner caja1 = findViewById(R.id.spinnerMascotas);
        Log.i("Spinner", String.valueOf(caja1));

        service = MascotaAPIClient.getMascotaInstance();

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

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarActividad();
            }
        });
    }

    // Método onStart fuera de onCreate
    @Override
    protected void onStart() {
        super.onStart();
        service.getAll(Datainfo.restLogin.getToken_type() + " " + Datainfo.restLogin.getAccess_token())
                .enqueue(new Callback<List<Mascota>>() {
                    @Override
                    public void onResponse(Call<List<Mascota>> call, Response<List<Mascota>> response) {
                        cargarMascotas(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Mascota>> call, Throwable t) {

                    }
                });

    }

    private void cargarMascotas(List<Mascota> mascotas) {
        ArrayAdapter<Mascota> mascotaArrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                mascotas
        );
        mascotaArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMascotas.setAdapter(mascotaArrayAdapter);
    }

    private void mostrarTimePickerDialog() {
        // Método para mostrar el diálogo de selección de hora
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

    private void mostrarDatePickerDialog() {
        // Método para mostrar el diálogo de selección de fecha
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

    private void guardarActividad() {
        // Método para guardar la actividad en la base de datos
    }
}


