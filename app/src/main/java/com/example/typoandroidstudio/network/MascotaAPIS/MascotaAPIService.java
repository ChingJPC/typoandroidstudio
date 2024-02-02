package com.example.typoandroidstudio.network.MascotaAPIS;

import com.example.typoandroidstudio.model.Mascota;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MascotaAPIService {
    @GET("Informacion")
    Call<List<Mascota>> getAll();

    @GET("Informacion/{id}")
    Call<Mascota> get(@Path("id") long id);

    @POST("Informacion")
    Call<Mascota> add(@Body Mascota mascota);

    @DELETE("Informacion/{id}")
    Call<Mascota> delete(@Path("id") long id);

    Call<Mascota> edit(@Path("id") long id, @Body Mascota mascota);;
}
