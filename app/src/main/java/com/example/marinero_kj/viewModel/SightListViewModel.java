package com.example.marinero_kj.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.marinero_kj.persistance.localStorage.SightDatabase;
import com.example.marinero_kj.persistance.localStorage.SightRepository;
import com.example.marinero_kj.pojo.Sight;

import java.util.List;

public class SightListViewModel extends AndroidViewModel {

    private SightRepository sightRepository;

    public SightListViewModel(Application application){
        super(application);
        sightRepository= SightRepository.getInstance(application);
    }

    public void addSight(Sight sight){sightRepository.addSight(sight);}

    public void deleteSights(){sightRepository.deleteSights();}

    public LiveData<List<Sight>> getAllSights() {return sightRepository.getAllSights();}
}
