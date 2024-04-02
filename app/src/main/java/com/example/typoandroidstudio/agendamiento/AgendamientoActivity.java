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
import android.widget.ImageButton;
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
    // Método para regresar a la actividad anterior
    public void back(View view) {
        finish();
    }

    // Declaración de variables
    MascotaAPIService service;
    private ImageButton btnfecha;
    private Button btnguardar;
    private Spinner spinnerMascotas, spinnerActividades, spinnerTiempo;
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
        btnfecha = findViewById(R.id.btnfecha);
        spinnerMascotas = findViewById(R.id.spinnerMascotas);
        spinnerActividades = findViewById(R.id.spinnerActividades);
        btnguardar = findViewById(R.id.btnguardar);
        textViewFecha = findViewById(R.id.textViewFecha);
        spinnerTiempo = findViewById(R.id.spinnerTiempo);
        fechaAgendamiento = Calendar.getInstance();

        // Obtención de referencias a los spinners
        Spinner caja1 = findViewById(R.id.spinnerMascotas);
        Log.i("Spinner", String.valueOf(caja1));
        Spinner caja2 = findViewById(R.id.spinnerActividades);
        Log.i("Spinner", String.valueOf(caja2));
        Spinner caja3 = findViewById(R.id.spinnerTiempo);
        Log.i("Spinner", String.valueOf(caja3));

        Spinner spinnerTiempo = findViewById(R.id.spinnerTiempo);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ArrayTiempo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTiempo.setAdapter(adapter);

        // Inicialización del servicio para obtener datos de la API
        service = MascotaAPIClient.getMascotaInstance();

        // Configuración de listeners para botones y spinners
        btnfecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDateTimePickerDialog();
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

    // Método onStart para cargar las mascotas al iniciar la actividad
    @Override
    protected void onStart() {
        super.onStart();
        service.getAll(Datainfo.restLogin.getToken_type() + " " + Datainfo.restLogin.getAccess_token())
                .enqueue(new Callback<List<Mascota>>() {
                    @Override
                    public void onResponse(Call<List<Mascota>> call, Response<List<Mascota>> response) {
                        cargarMascotas(response.body());
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


    // Método para cargar las actividades de una mascota específica
    private void loadActividades(long idMascota) {
        // Llamar al servicio API para obtener las actividades de la mascota
        service.getActividad(idMascota, Datainfo.restLogin.getToken_type() + " " + Datainfo.restLogin.getAccess_token())
                .enqueue(new Callback<List<Actividad>>() {
                    @Override
                    public void onResponse(Call<List<Actividad>> call, Response<List<Actividad>> response) {
                        if (response.isSuccessful()) {
                            // Si la respuesta es exitosa, cargar las actividades en el spinner
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

    // Método para cargar las actividades en el spinner de actividades
    private void cargarActividad(List<Actividad> actividades) {
        // Crear un adaptador para las actividades y asignarlo al spinner
        ArrayAdapter<Actividad> actividadArrayAdapter = new ArrayAdapter<>(
                AgendamientoActivity.this,
                android.R.layout.simple_spinner_item,
                actividades
        );
        actividadArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerActividades.setAdapter(actividadArrayAdapter);
    }

    // Método para mostrar el diálogo de selección de fecha y hora
    private void mostrarDateTimePickerDialog() {
        // Obtener la fecha y hora actuales
        Calendar calendarioActual = Calendar.getInstance();
        int añoActual = calendarioActual.get(Calendar.YEAR);
        int mesActual = calendarioActual.get(Calendar.MONTH);
        int diaActual = calendarioActual.get(Calendar.DAY_OF_MONTH);
        int horaActual = calendarioActual.get(Calendar.HOUR_OF_DAY);
        int minutoActual = calendarioActual.get(Calendar.MINUTE);

        // Mostrar el diálogo de selección de fecha y hora
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        TimePickerDialog timePickerDialog = new TimePickerDialog(
                                AgendamientoActivity.this,
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                                        // Combinar la fecha y la hora seleccionadas
                                        Calendar fechaSeleccionada = Calendar.getInstance();
                                        fechaSeleccionada.set(year, month, dayOfMonth, hourOfDay, minute);

                                        // Validar que la fecha seleccionada sea a partir de hoy
                                        if (fechaSeleccionada.compareTo(calendarioActual) >= 0) {
                                            // La fecha seleccionada es válida, mostrarla en el TextView de fecha
                                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
                                            textViewFecha.setText(dateFormat.format(fechaSeleccionada.getTime()));
                                        } else {
                                            // Mostrar un mensaje de error si la fecha seleccionada es anterior a hoy
                                            Toast.makeText(AgendamientoActivity.this, "Selecciona una fecha y hora a partir de hoy", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                },
                                horaActual, minutoActual, true
                        );
                        timePickerDialog.show();
                    }
                },
                añoActual, mesActual, diaActual
        );

        // Establecer la fecha mínima seleccionable como hoy
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        datePickerDialog.show();
    }

    // Método para guardar una actividad
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

        // Crear un objeto JSON con los datos de la actividad
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("tiempo_asignado_actividad", spinnerTiempo.getSelectedItem());
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



