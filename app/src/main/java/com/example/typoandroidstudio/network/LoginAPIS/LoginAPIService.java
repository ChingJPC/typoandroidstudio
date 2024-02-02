package com.example.typoandroidstudio.network.LoginAPIS;


import com.example.typoandroidstudio.model.Mascota;
import com.example.typoandroidstudio.model.RestLogin;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

    public interface LoginAPIService {
        @FormUrlEncoded
        @POST("auth/login")
        Call<RestLogin> login(@Field("email")String email,
                              @Field("password")String password);
        @GET("auth/Informacion")
        Call<List<Mascota>> getMascota(@Header("Authorization")String auth);
    }

