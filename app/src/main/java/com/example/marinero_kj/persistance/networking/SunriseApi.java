package com.example.marinero_kj.persistance.networking;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SunriseApi {

    //https://api.sunrise-sunset.org/json?lat=36.7201600&lng=-4.4203400&date=today
    @GET("json")
    Call<SunriseResponse> getSunrise(@Query("lat") double latitude, @Query("lng") double longitude);
}
