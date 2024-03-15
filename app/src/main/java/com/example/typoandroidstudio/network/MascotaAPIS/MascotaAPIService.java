package com.example.typoandroidstudio.network.MascotaAPIS;

import com.example.typoandroidstudio.model.Actividad;
import com.example.typoandroidstudio.model.Mascota;
import com.example.typoandroidstudio.model.Tipomascota;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MascotaAPIService {
    @GET("auth/Informacion")
    Call<List<Mascota>> getAll(@Header("Authorization") String Authorization);
    @GET("auth/Actividad")
    Call<List<Actividad>> getActividad(@Query("id") long id, @Header("Authorization") String Authorization);

    @GET("auth/Tipomascota")
    Call<List<Tipomascota>> getTipos(@Query("id")long id, @Header("Authorization") String Authorization);

    @GET("auth/Informacion/{id}")
    Call<Mascota> get(@Path("id") long id);

    @POST("auth/Informacion")
    Call<Mascota> add(@Header("Authorization") String Authorization, @Body Mascota mascota);

    @DELETE("auth/Informacion/{id}")
    Call<Mascota> delete(@Path("id") long id);

    Call<Mascota> edit(@Path("id") long id, @Body Mascota mascota);;
}
