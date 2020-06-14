package com.example.marinero_kj.persistance.networking;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.marinero_kj.pojo.Sunrise;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SunriseRepository {

    private static SunriseRepository instance;
    private MutableLiveData<Sunrise> sunrise;
    private MutableLiveData<Boolean> isLoading;


    private SunriseRepository(){
        sunrise=new MutableLiveData<>();
        isLoading=new MutableLiveData<>(false);
    }

    public static synchronized SunriseRepository getInstance(){
        if(instance==null){
            instance=new SunriseRepository();
        }
        return instance;
    }

    public LiveData<Sunrise> getSunrise(){
        return sunrise;
    }

    public LiveData<Boolean> getIsLoading(){return isLoading;}

    public void updateSunrise(double lon, double lat){
        SunriseApi sunriseApi= ServiceGenerator.getSunriseApi();

        if(lon==-1 ||lat==-1)
        {
            sunrise.setValue(new Sunrise("error", "error"));
            return;
        }

        isLoading.setValue(true);
        Call<SunriseResponse> call= sunriseApi.getSunrise(lon, lat);
        call.enqueue(new Callback<SunriseResponse>() {
            @Override
            public void onResponse(Call<SunriseResponse> call, Response<SunriseResponse> response) {
                if(response.code()==200)
                    sunrise.setValue(response.body().getSunrise());
                isLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<SunriseResponse> call, Throwable t) {
                sunrise.setValue(new Sunrise("error", "error"));
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }
}
