package com.example.typoandroidstudio.agendamiento;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import com.example.typoandroidstudio.Datainfo;
import com.example.typoandroidstudio.R;
import com.example.typoandroidstudio.model.Actividad;
import com.example.typoandroidstudio.model.Mascota;
import com.example.typoandroidstudio.model.Tipomascota;
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
    private Tipomascota tipoMascota; // Variable para almacenar el tipo de mascota seleccionado

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamiento);

        // Inicialización de los elementos de la interfaz
        btnhora = findViewById(R.id.btnhora);
        btnfecha = findViewById(R.id.btnfecha);
        spinnerMascotas = findViewById(R.id.spinnerMascotas);
        spinnerActividades = findViewById(R.id.spinnerActividades);
        btnguardar = findViewById(R.id.btnguardar);

        textViewFecha = findViewById(R.id.textViewFecha);
        textViewTiempo = findViewById(R.id.textViewTiempo);
        fechaAgendamiento = Calendar.getInstance();

        // Obtención de referencias a los spinners
        Spinner caja1 = findViewById(R.id.spinnerMascotas);
        Log.i("Spinner", String.valueOf(caja1));
        Spinner caja2 = findViewById(R.id.spinnerActividades);
        Log.i("Spinner", String.valueOf(caja2));

        // Inicialización del servicio para obtener datos de la API
        service = MascotaAPIClient.getMascotaInstance();

        // Configuración de listeners para botones y spinners
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

        // Listener para spinner de mascotas
        spinnerMascotas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Obtener la mascota seleccionada
                Mascota mascotaSeleccionada = (Mascota) parent.getItemAtPosition(position);
                if (mascotaSeleccionada != null) {
                    // Obtener el tipo de mascota de la mascota seleccionada
                    Tipomascota tipomascota = mascotaSeleccionada.getTipomascota();
                    if (tipomascota != null) {
                        // Almacenar el tipo de mascota seleccionado
                        tipoMascota = tipomascota;
                        // Llamar al método para cargar las actividades del tipo de mascota
                        long tipoMascotaId = tipomascota.getId();
                        cargarActividad(tipoMascotaId);
                    } else {
                        Log.e("TAG", "tipomascota es nulo");
                    }
                } else {
                    Log.e("TAG", "mascotaSeleccionada es nulo");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No hacer nada si no hay nada seleccionado
            }
        });

    }

    // Método onStart fuera de onCreate
    @Override
    protected void onStart() {
        super.onStart();
        // Cargar todas las mascotas al iniciar la actividad
        service.getAll(Datainfo.restLogin.getToken_type() + " " + Datainfo.restLogin.getAccess_token())
                .enqueue(new Callback<List<Mascota>>() {
                    @Override
                    public void onResponse(Call<List<Mascota>> call, Response<List<Mascota>> response) {
                        cargarMascotas(response.body());
                        // Cargar actividades de la primera mascota (si existe) al iniciar la actividad
                        List<Mascota> mascotas = response.body();
                        if (mascotas != null && !mascotas.isEmpty()) {
                            Mascota primeraMascota = mascotas.get(0);
                            cargarActividad(primeraMascota.getId());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Mascota>> call, Throwable t) {
                        // Manejar el caso en que la llamada falle
                    }
                });
    }

    // Método para cargar las mascotas en el spinner de mascotas
    private void cargarMascotas(List<Mascota> mascotas) {
        ArrayAdapter<Mascota> mascotaArrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                mascotas
        );
        mascotaArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMascotas.setAdapter(mascotaArrayAdapter);
    }

    // Método para cargar las actividades de un tipo de mascota específico en el spinner de actividades
    private void cargarActividad(long id) {
        service.getActividad(id, Datainfo.restLogin.getToken_type() + " " + Datainfo.restLogin.getAccess_token())
                .enqueue(new Callback<List<Actividad>>() {
                    @Override
                    public void onResponse(Call<List<Actividad>> call, Response<List<Actividad>> response) {
                        if (response.isSuccessful()) {
                            List<Actividad> actividades = response.body();
                            ArrayAdapter<Actividad> actividadArrayAdapter = new ArrayAdapter<>(
                                    AgendamientoActivity.this,
                                    android.R.layout.simple_spinner_item,
                                    actividades
                            );
                            actividadArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinnerActividades.setAdapter(actividadArrayAdapter);
                        } else {
                            // Manejar el caso en que la respuesta no fue exitosa
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Actividad>> call, Throwable t) {
                        // Manejar el caso en que la llamada falló
                    }
                });
    }

    // Método para mostrar el diálogo de selección de hora
    private void mostrarTimePickerDialog() {
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

    // Método para mostrar el diálogo de selección de fecha
    private void mostrarDatePickerDialog() {
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


