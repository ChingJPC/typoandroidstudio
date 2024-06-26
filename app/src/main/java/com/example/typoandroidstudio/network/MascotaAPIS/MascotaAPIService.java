package com.example.typoandroidstudio.network.MascotaAPIS;

import com.example.typoandroidstudio.model.Actividad;
import com.example.typoandroidstudio.model.Agendamiento;
import com.example.typoandroidstudio.model.CantidadMascota;
import com.example.typoandroidstudio.model.Logros;
import com.example.typoandroidstudio.model.Mascota;
import com.example.typoandroidstudio.model.Mascotalogros;
import com.example.typoandroidstudio.model.Reportes;
import com.example.typoandroidstudio.model.Tipomascota;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MascotaAPIService {
    @GET("auth/Informacion")
    Call<List<Mascota>> getAll(@Header("Authorization") String Authorization);
    @GET("auth/logros-user")
    Call<List<Logros>> getAllLogros(@Header("Authorization") String Authorization);
    @GET("auth/obtenerActividadesMascota/{id}")
    Call<List<Actividad>> getActividad(@Path("id") long idMascota, @Header("Authorization") String Authorization);
    @GET("auth/agendamientos/{id}")
    Call<List<Agendamiento>> getAgendamiento(@Header("Authorization") String Authorization, @Path("id") long id);
    @POST("auth/Agendamiento")
    Call<Actividad> addActividad(@Body RequestBody body, @Header("Authorization") String Authorization);
    @GET("auth/Tipomascota-user")
    Call<List<Tipomascota>> getTipos(@Header("Authorization") String Authorization);
    @GET("auth/Informacion/{id}")
    Call<Mascota> get(@Path("id") long id);
    @POST("auth/Informacion")
    Call<Mascota> add(@Header("Authorization") String Authorization, @Body Mascota mascota);
    @DELETE("auth/Informacion/{id}")
    Call<Mascota> delete(@Path("id") long id);
    @POST("auth/actualizar-tiempo-mascotas")
    Call<Void> actualizarTiempoMascotas(@Header("Authorization") String Authorization, @Body Agendamiento agendamiento);
    Call<Mascota> edit(@Path("id") long id, @Body Mascota mascota);
    @GET("auth/mascotas/{mascotaId}/logros")
    Call<List<Mascotalogros>> obtenerLogrosDeMascota(@Header("Authorization") String Authorization, @Path("mascotaId") long mascotaId);

    @GET("auth/reportes/{usuarioId}/cumplimiento-mensual")
    Call<Reportes> ReporteCumplimiento(@Header("Authorization") String Authorization, @Path("usuarioId") long usuarioId);
    @GET("auth/Cantidadmascota")
    Call<List<CantidadMascota>> getCantidadMascota(@Header("Authorization") String Authorization);
}
