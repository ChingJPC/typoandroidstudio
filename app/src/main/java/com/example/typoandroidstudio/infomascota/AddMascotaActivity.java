package com.example.typoandroidstudio.infomascota;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.typoandroidstudio.Datainfo;
import com.example.typoandroidstudio.R;
import com.example.typoandroidstudio.model.Mascota;
import com.example.typoandroidstudio.model.Tipomascota;
import com.example.typoandroidstudio.network.MascotaAPIS.MascotaAPIClient;
import com.example.typoandroidstudio.network.MascotaAPIS.MascotaAPIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMascotaActivity extends AppCompatActivity {
    private MascotaAPIService service;
    private Mascota mascotaSeleccionada;

    // Método para volver atrás
    public void back(View view) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mascota);

        // Obtener instancia del servicio
        service = MascotaAPIClient.getMascotaInstance();

        // Configuración del spinner de sexo
        Spinner spinnerSexo = findViewById(R.id.txtgenero);
        ArrayAdapter<CharSequence> adapterSexo = ArrayAdapter.createFromResource(this, R.array.ArraySexo, android.R.layout.simple_spinner_item);
        adapterSexo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSexo.setAdapter(adapterSexo);

        // Configuración del spinner de raza
        Spinner spinnerRaza = findViewById(R.id.txtraza);
        ArrayAdapter<CharSequence> adapterRaza = ArrayAdapter.createFromResource(this, R.array.ArrayRaza, android.R.layout.simple_spinner_item);
        adapterRaza.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRaza.setAdapter(adapterRaza);

        // Obtener tipos de mascotas al iniciar la actividad
        service.getTipos(Datainfo.restLogin.getToken_type() + " " + Datainfo.restLogin.getAccess_token())
                .enqueue(new Callback<List<Tipomascota>>() {
                    @Override
                    public void onResponse(Call<List<Tipomascota>> call, Response<List<Tipomascota>> response) {
                        if (response.isSuccessful()) {
                            // Cargar el spinner con los tipos de mascotas obtenidos
                            cargarspinner(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Tipomascota>> call, Throwable t) {
                        // Manejo del error en caso de fallo en la obtención de los tipos de mascotas
                    }
                });
    }

    // Método para cargar el spinner con los tipos de mascotas obtenidos
    private void cargarspinner(List<Tipomascota> body) {
        Spinner spinnerTipos = findViewById(R.id.spinner);
        ArrayAdapter<Tipomascota> adapterTipos = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, body);
        adapterTipos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipos.setAdapter(adapterTipos);
    }

    // Método para agregar una nueva mascota
    public void click(View view) {
        if (view.getId() == R.id.btnadd) {
            EditText caja1 = findViewById(R.id.txtnombre);
            String nombre = caja1.getText().toString();
            EditText caja2 = findViewById(R.id.txtedad);
            int edad = Integer.parseInt(caja2.getText().toString());
            Spinner spinnerRaza = findViewById(R.id.txtraza);
            String raza = (String) spinnerRaza.getSelectedItem();
            EditText caja4 = findViewById(R.id.txtpeso);
            int peso = Integer.parseInt(caja4.getText().toString());
            Spinner spinnerSexo = findViewById(R.id.txtgenero);
            String sexo = (String) spinnerSexo.getSelectedItem();
            EditText caja5 = findViewById(R.id.txttamaño);
            float tamaño = Float.parseFloat(caja5.getText().toString());
            Spinner spinnerTipo = findViewById(R.id.spinner);
            Tipomascota tipoMascota = (Tipomascota) spinnerTipo.getSelectedItem();

            // Crear una nueva mascota con los datos ingresados
            Mascota nuevaMascota = new Mascota(nombre, edad, raza, peso, tamaño, sexo);
            nuevaMascota.setUser_id(Datainfo.restLogin.getUser().getId());
            nuevaMascota.setId_tipomascota(tipoMascota.getId());

            // Agregar la nueva mascota utilizando el servicio
            service.add(Datainfo.restLogin.getToken_type() + " " + Datainfo.restLogin.getAccess_token(), nuevaMascota)
                    .enqueue(new Callback<Mascota>() {
                        @Override
                        public void onResponse(Call<Mascota> call, Response<Mascota> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(AddMascotaActivity.this, "Mascota Agregada Correctamente", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(AddMascotaActivity.this, "Error al Agregar una Mascota", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Mascota> call, Throwable t) {
                            Toast.makeText(AddMascotaActivity.this, "Error al crear la mascota", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}

