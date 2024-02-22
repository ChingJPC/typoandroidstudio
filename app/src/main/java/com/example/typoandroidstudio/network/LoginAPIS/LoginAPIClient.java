package com.example.typoandroidstudio.network.LoginAPIS;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginAPIClient {
    private static final String URL = " http://10.201.194.28:8000/api/";

    private static LoginAPIService instance;
    public static LoginAPIService getLoginService(){
        if(instance==null){
            Retrofit http = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            instance = http.create(LoginAPIService.class);
        }
        return instance;
    }
}
