package com.example.typoandroidstudio.network.MascotaAPIS;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MascotaAPIClient {
    //private static final String URL = "https://typo.api.adsocidm.com/api/";
    private static final String URL = "http://10.201.194.39:8000/api/";
    //private static final String URL = "http://192.168.56.1:8000/api/";
    private static MascotaAPIService instance;

    public static MascotaAPIService getMascotaInstance(){
        if (instance==null){
            final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);

            Retrofit retro = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
            instance = retro.create(MascotaAPIService.class);
        }
        return instance;
    }
}
