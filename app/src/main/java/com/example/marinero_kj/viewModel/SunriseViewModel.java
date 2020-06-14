package com.example.marinero_kj.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.marinero_kj.persistance.networking.SunriseRepository;
import com.example.marinero_kj.pojo.Sunrise;

import java.io.Serializable;

public class SunriseViewModel extends ViewModel implements Serializable {

    private SunriseRepository repository;

    public SunriseViewModel(){
        repository= SunriseRepository.getInstance();
    }

    public LiveData<Sunrise> getSunrise(){
        return repository.getSunrise();
    }

    public LiveData<Boolean> getIsLoading(){
        return repository.getIsLoading();
    }

    public void updateSunrise(double lon, double lat){
        repository.updateSunrise(lon, lat);
    }
}
