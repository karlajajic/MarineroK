package com.example.marinero_kj.persistance.networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl("https://api.sunrise-sunset.org/")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static SunriseApi sunriseApi = retrofit.create(SunriseApi.class);

    public static SunriseApi getSunriseApi() {
        return sunriseApi;
    }
}