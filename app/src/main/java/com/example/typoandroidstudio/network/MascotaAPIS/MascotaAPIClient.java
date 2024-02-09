package com.example.typoandroidstudio.network.MascotaAPIS;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MascotaAPIClient {
    private static final String URL = "http://10.201.194.20:8000/api/";

    private static MascotaAPIService instance;

    public static MascotaAPIService getMascotaInstance(){
        if (instance==null){
            Retrofit retro = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            instance = retro.create(MascotaAPIService.class);
        }
        return instance;
    }
}
