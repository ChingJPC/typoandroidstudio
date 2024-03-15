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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.typoandroidstudio.Datainfo;
import com.example.typoandroidstudio.R;
import com.example.typoandroidstudio.model.Actividad;
import com.example.typoandroidstudio.model.Mascota;
import com.example.typoandroidstudio.model.Tipomascota;
import com.example.typoandroidstudio.network.MascotaAPIS.MascotaAPIClient;
import com.example.typoandroidstudio.network.MascotaAPIS.MascotaAPIService;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.RequestBody;
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
                        loadActividades(tipoMascotaId);
                    } else {
                        loadActividades(mascotaSeleccionada.getId_tipomascota());
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
                        cargarMascotas(mascotas);
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
        Mascota mascota = mascotas.get(0);
        loadActividades(mascota.getId_tipomascota());
    }

    private void loadActividades(long id) {
        service.getActividad(id, Datainfo.restLogin.getToken_type() + " " + Datainfo.restLogin.getAccess_token())
                .enqueue(new Callback<List<Actividad>>() {
                    @Override
                    public void onResponse(Call<List<Actividad>> call, Response<List<Actividad>> response) {
                        if (response.isSuccessful()) {
                            List<Actividad> actividades = response.body();
                            cargarActividad(actividades);
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

    // Método para cargar las actividades de un tipo de mascota específico en el spinner de actividades
    private void cargarActividad(List<Actividad> actividades) {
        ArrayAdapter<Actividad> actividadArrayAdapter = new ArrayAdapter<>(
                AgendamientoActivity.this,
                android.R.layout.simple_spinner_item,
                actividades
        );
        actividadArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerActividades.setAdapter(actividadArrayAdapter);
    }

    // Método para mostrar el diálogo de selección de hora
    private void mostrarTimePickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                TextView editTextTiempo = findViewById(R.id.textViewTiempo);
                editTextTiempo.setText(String.format("%02d:%02d", hourOfDay, minute));
            }
        },
                hora, minuto, false
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
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
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
        // Obtener la mascota seleccionada
        Mascota mascotaSeleccionada = (Mascota) spinnerMascotas.getSelectedItem();
        if (mascotaSeleccionada == null) {
            // Manejar el caso en que no se ha seleccionado una mascota
            return;
        }

        // Obtener la actividad seleccionada
        Actividad actividadSeleccionada = (Actividad) spinnerActividades.getSelectedItem();
        if (actividadSeleccionada == null) {
            // Manejar el caso en que no se ha seleccionado una actividad
            return;
        }

        // Obtener la fecha y hora seleccionadas
        String fecha = textViewFecha.getText().toString();
        String hora = textViewTiempo.getText().toString();

        // Crear un objeto Actividad con los datos seleccionados
        Actividad actividad = new Actividad(actividadSeleccionada.getNombre_actividad(), actividadSeleccionada.getDescripcion_actividad());

        // Crear un objeto JSON con los datos de la actividad
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("tiempo_asignado_actividad", hora);
            jsonBody.put("cumplida", false);
            jsonBody.put("Fecha_Agendamiento", fecha);
            jsonBody.put("infomascota_id", String.valueOf(mascotaSeleccionada.getId()));
            jsonBody.put("actividades_id", String.valueOf(actividadSeleccionada.getId()));
            jsonBody.put("user_id", String.valueOf(Datainfo.restLogin.getUser().getId()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Crear un RequestBody con el objeto JSON
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonBody.toString());

        // Llamar al servicio API para guardar la actividad
        service.addActividad(requestBody, Datainfo.restLogin.getToken_type() + " " +
                        Datainfo.restLogin.getAccess_token())
                .enqueue(new Callback<Actividad>() {
                    @Override
                    public void onResponse(Call<Actividad> call, Response<Actividad> response) {
                        if (response.isSuccessful()) {
                            // Actividad guardada correctamente
                            Toast.makeText(AgendamientoActivity.this, "Actividad guardada correctamente", Toast.LENGTH_SHORT).show();
                        } else {
                            // Error al guardar la actividad
                            Toast.makeText(AgendamientoActivity.this, "Error al guardar la actividad", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Actividad> call, Throwable t) {
                        // Error en la comunicación con el servidor
                        Toast.makeText(AgendamientoActivity.this, "Error en la comunicación con el servidor", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}



