package com.example.marinero_kj.persistance.localStorage;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.marinero_kj.pojo.Sight;

import java.util.List;

public class SightRepository {

    private SightDao sightDao;
    private static SightRepository instance;
    private LiveData<List<Sight>> sights;

    private SightRepository(Application application){
        SightDatabase db= SightDatabase.getInstance(application);
        sightDao=db.sightDao();
        sights = new MutableLiveData<>();
        sights=sightDao.getAllSights();
    }

    public LiveData<List<Sight>> getAllSights(){return sights;}

    public static SightRepository getInstance(Application application){
        if(instance==null)
            instance= new SightRepository(application);
        return instance;
    }

    public void addSight(Sight sight){
        new InsertSightAsync(sightDao).execute(sight);
    }

    public void deleteSights(){
        new InsertSightAsync(sightDao).execute();
    }

    public void updateSight(Sight sight){
        new UpdateSightAsync(sightDao).execute(sight);
    }

//--------------------------------------------------------------------------------------
    private static class InsertSightAsync extends AsyncTask<Sight, Void, Void> {
        private SightDao sightDao;

        private InsertSightAsync(SightDao  sDao){
            this.sightDao=sDao;
        }

        @Override
        protected Void doInBackground(Sight... sights) {
            try {
                sightDao.insert(sights[0]);
                return null;
            }catch (Exception e){
                return null;
            }
        }
    }

    private static class DeleteSightsAsync extends AsyncTask<Void, Void, Void> {
        private SightDao sightDao;

        private DeleteSightsAsync(SightDao  sDao){
            this.sightDao=sDao;
        }

        @Override
        protected Void doInBackground(Void... sights) {
            sightDao.deleteAllSights();
            return null;
        }
    }

    private static class UpdateSightAsync extends AsyncTask<Sight, Void, Void> {
        private SightDao sightDao;

        private UpdateSightAsync(SightDao  sDao){
            this.sightDao=sDao;
        }

        @Override
        protected Void doInBackground(Sight... sights) {
            try {
                sightDao.update(sights[0]);
                return null;
            }catch (Exception e){
                return null;
            }
        }
    }

}
