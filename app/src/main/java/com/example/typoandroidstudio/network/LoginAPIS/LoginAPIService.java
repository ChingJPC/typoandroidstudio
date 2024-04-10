package com.example.typoandroidstudio.network.LoginAPIS;


import com.example.typoandroidstudio.model.Mascota;
import com.example.typoandroidstudio.model.RestLogin;
import com.example.typoandroidstudio.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface LoginAPIService {
        @FormUrlEncoded
        @POST("auth/login")
        Call<RestLogin> login(@Field("email")String email,
                              @Field("password")String password);

        @FormUrlEncoded
        @POST ("auth/signup")
        Call<RestLogin> register(@Field("name") String name,
                            @Field("apellido") String apellido,
                            @Field("telefono") String telefono,
                            @Field("fecha_nacimiento") String fecha_nacimiento,
                            @Field("email") String email,
                            @Field("password") String password);

        @FormUrlEncoded
        @PUT("auth/Usuario-user/{id}")
        Call<User> updateProfile(
                @Header("Authorization") String auth,
                @Path("id") long id,
                @Field("name") String name,
                @Field("apellido") String apellido,
                @Field("telefono") String telefono,
                @Field("fecha_nacimiento") String fecha_nacimiento
        );

        @GET("auth/logout")
        Call<RestLogin>logout(@Header("Authorization") String auth);
}

