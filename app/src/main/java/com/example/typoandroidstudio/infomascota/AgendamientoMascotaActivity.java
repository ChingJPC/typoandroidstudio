 package com.example.typoandroidstudio.infomascota;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.typoandroidstudio.Datainfo;
import com.example.typoandroidstudio.R;
import com.example.typoandroidstudio.adapter.AgendamientoAdapter;
import com.example.typoandroidstudio.model.Agendamiento;
import com.example.typoandroidstudio.network.MascotaAPIS.MascotaAPIClient;
import com.example.typoandroidstudio.network.MascotaAPIS.MascotaAPIService;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

 public class AgendamientoMascotaActivity extends AppCompatActivity {
     private MascotaAPIService service;
     private ListView listagendamiento;
     private AgendamientoAdapter adapter;
     long id;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_agendamiento_mascota);

         service = MascotaAPIClient.getMascotaInstance();
         listagendamiento = findViewById(R.id.listagendamientos);

         // Supongamos que tienes el ID de la mascota que deseas obtener
         id = getIntent().getLongExtra("id", 0);

         loadData();
     }

     public void loadData() {
         service.getAgendamiento(Datainfo.restLogin.getToken_type() + " " +
                 Datainfo.restLogin.getAccess_token(), id).enqueue(new Callback<List<Agendamiento>>() {
             @Override
             public void onResponse(Call<List<Agendamiento>> call, Response<List<Agendamiento>> response) {
                 if (response.isSuccessful()) {
                     List<Agendamiento> agendamiento = response.body();
                     Log.e("Tag de éxito", "La respuesta fue exitosa");
                     adapter = new AgendamientoAdapter(agendamiento, AgendamientoMascotaActivity.this);
                     listagendamiento.setAdapter(adapter);
                 }
             }

             @Override
             public void onFailure(Call<List<Agendamiento>> call, Throwable t) {
                 Log.e("TYPO", t.getMessage());
             }
         });
     }

     public void actualizarEstadoCumplida(long agendamientoId, byte cumplida) {
         service.actualizarTiempoMascotas(Datainfo.restLogin.getToken_type() + " " +
                         Datainfo.restLogin.getAccess_token(), new Agendamiento(agendamientoId, cumplida))
                 .enqueue(new Callback<Void>() {
                     @Override
                     public void onResponse(Call<Void> call, Response<Void> response) {
                         if (response.isSuccessful()) {
                             // Actualización exitosa
                             // Aquí podrías actualizar la lista de agendamientos y notificar al Adapter
                             // para que actualice la vista
                             loadData(); // Por ejemplo, puedes volver a cargar los datos
                         } else {
                             // Error en la actualización
                             Log.e("TAG", "Error en la actualización: " + response.message());
                         }
                     }

                     @Override
                     public void onFailure(Call<Void> call, Throwable t) {
                         // Error en la conexión
                         Log.e("TAG", "Error en la conexión: " + t.getMessage());
                     }
                 });
     }
 }



