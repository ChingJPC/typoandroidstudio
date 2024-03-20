 package com.example.typoandroidstudio.infomascota;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.typoandroidstudio.model.Actividad;
import com.example.typoandroidstudio.model.Agendamiento;
import com.example.typoandroidstudio.model.Mascota;
import com.example.typoandroidstudio.network.MascotaAPIS.MascotaAPIClient;
import com.example.typoandroidstudio.network.MascotaAPIS.MascotaAPIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

 public class AgendamientoMascotaActivity extends AppCompatActivity {
     private MascotaAPIService service;
     private ListView listagendamiento;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_agendamiento_mascota);

         service = MascotaAPIClient.getMascotaInstance();
         listagendamiento = findViewById(R.id.listagendamientos);

         loadData();
     }

     private void loadData() {
         service.getAgendamiento(Datainfo.restLogin.getToken_type() + " " +
                 Datainfo.restLogin.getAccess_token()).enqueue(new Callback<List<Agendamiento>>() {
             @Override
             public void onResponse(Call<List<Agendamiento>> call, Response<List<Agendamiento>> response) {
                 if (response.isSuccessful()) {
                     List<Agendamiento> agendamiento = response.body();
                     cargarDatos(agendamiento);
                 }
             }

             @Override
             public void onFailure(Call<List<Agendamiento>> call, Throwable t) {
                Log.e("TYPO", t.getMessage());
             }
         });
     }
         private void cargarDatos(List<Agendamiento> agendamientos) {
             Log.i("TYPO", agendamientos.toString());
             AgendamientoAdapter datos = new AgendamientoAdapter(agendamientos, this);
             listagendamiento.setAdapter(datos);
         }
 }