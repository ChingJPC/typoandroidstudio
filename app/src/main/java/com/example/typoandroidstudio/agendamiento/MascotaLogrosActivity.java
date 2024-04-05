package com.example.typoandroidstudio.agendamiento;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.typoandroidstudio.Datainfo;
import com.example.typoandroidstudio.R;
import com.example.typoandroidstudio.adapter.AgendamientoAdapter;
import com.example.typoandroidstudio.adapter.MascotaAdapter;
import com.example.typoandroidstudio.adapter.MascotaLogrosAdapter;
import com.example.typoandroidstudio.infomascota.AgendamientoMascotaActivity;
import com.example.typoandroidstudio.model.Agendamiento;
import com.example.typoandroidstudio.model.Mascotalogros;
import com.example.typoandroidstudio.network.MascotaAPIS.MascotaAPIClient;
import com.example.typoandroidstudio.network.MascotaAPIS.MascotaAPIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MascotaLogrosActivity extends AppCompatActivity {
    private MascotaAPIService service;
    private ListView listalogrosmascota;
    private MascotaLogrosAdapter adapter;
    long id;

    public void back(View view) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mascota_logros);

        service = MascotaAPIClient.getMascotaInstance();
        listalogrosmascota = findViewById(R.id.listalogrosmascota);

        // Supongamos que tienes el ID de la mascota que deseas obtener
        id = getIntent().getLongExtra("id", 0);

        // Cargar los logros de la mascota
        loadData();
    }

    public void loadData() {
        service.obtenerLogrosDeMascota(id).enqueue(new Callback<List<Mascotalogros>>() {
            @Override
            public void onResponse(Call<List<Mascotalogros>> call, Response<List<Mascotalogros>> response) {
                if (response.isSuccessful()) {
                    List<Mascotalogros> mascotalogros = response.body();
                    Log.e("Tag de éxito", "La respuesta fue exitosa");
                    adapter = new MascotaLogrosAdapter(MascotaLogrosActivity.this, mascotalogros);
                    listalogrosmascota.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Mascotalogros>> call, Throwable t) {
                // Manejar la falla de la solicitud aquí
            }
        });
    }
}

