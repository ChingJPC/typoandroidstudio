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
        service = MascotaAPIClient.getMascotaInstance();
        Spinner caja7 = findViewById(R.id.spinner);

        Log.i("Spinner", String.valueOf(caja7));

        // Configuración del spinner de sexo
        Spinner spinnerSexo = findViewById(R.id.txtgenero);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ArraySexo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSexo.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Obtener tipos de mascotas
        service.getTipos(Datainfo.restLogin.getToken_type() + " " +
                Datainfo.restLogin.getAccess_token()).enqueue(new Callback<List<Tipomascota>>() {
            @Override
            public void onResponse(Call<List<Tipomascota>> call, Response<List<Tipomascota>> response) {
                if (response.isSuccessful()) {
                    cargarspinner(response.body()); // Cargar el spinner con los tipos de mascotas obtenidos
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
        Spinner caja7 = findViewById(R.id.spinner);
        ArrayAdapter<Tipomascota> tipomascotaArrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_expandable_list_item_1,
                body
        );
        caja7.setAdapter(tipomascotaArrayAdapter);
    }

    // Método para agregar una nueva mascota
    public void click(View view) {
        if (view.getId() == R.id.btnadd) {
            EditText caja1 = findViewById(R.id.txtnombre);
            String value1 = caja1.getText().toString();
            EditText caja2 = findViewById(R.id.txtedad);
            String value2 = caja2.getText().toString();
            EditText caja3 = findViewById(R.id.txtraza);
            String value3 = caja3.getText().toString();
            EditText caja4 = findViewById(R.id.txtpeso);
            String value4 = caja4.getText().toString();
            Spinner spinnerSexo = findViewById(R.id.txtgenero);
            String value6 = (String) spinnerSexo.getItemAtPosition(spinnerSexo.getSelectedItemPosition());
            EditText caja5 = findViewById(R.id.txttamaño);
            String value5 = caja5.getText().toString();

            // Validar que se haya ingresado un nombre válido
            if (value1.length() > 0) {
                // Crear una nueva mascota con los datos ingresados
                Mascota nuevaMascota = new Mascota(value1, Integer.parseInt(value2), value3, Integer.parseInt(value4), Float.parseFloat(value5), value6);
                nuevaMascota.setUser_id(Datainfo.restLogin.getUser().getId());
                Spinner spinner = findViewById(R.id.spinner);
                Tipomascota tipomascota = (Tipomascota) spinner.getItemAtPosition(spinner.getSelectedItemPosition());
                nuevaMascota.setId_tipomascota(tipomascota.getId());
                // Agregar la nueva mascota utilizando el servicio
                service.add(Datainfo.restLogin.getToken_type() + " " +
                        Datainfo.restLogin.getAccess_token(), nuevaMascota).enqueue(new Callback<Mascota>() {
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
            } else {
                Toast.makeText(AddMascotaActivity.this, "Ingrese un número válido", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

