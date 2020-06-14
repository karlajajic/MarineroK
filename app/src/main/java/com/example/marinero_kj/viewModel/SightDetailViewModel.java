package com.example.marinero_kj.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.marinero_kj.persistance.localStorage.SightRepository;
import com.example.marinero_kj.pojo.Sight;

import java.util.List;

public class SightDetailViewModel extends AndroidViewModel {

    private SightRepository sightRepository;

    public SightDetailViewModel(Application application){
        super(application);
        sightRepository= SightRepository.getInstance(application);
    }

    public void updateSight(Sight sight){sightRepository.updateSight(sight);}
}