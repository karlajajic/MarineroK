package com.example.marinero_kj.persistance.localStorage;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.marinero_kj.pojo.Sight;

@Database(entities = {Sight.class}, version =6 )
public abstract class SightDatabase extends RoomDatabase {

    private static SightDatabase instance;
    public abstract SightDao sightDao();

    public static synchronized SightDatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    SightDatabase.class, "sight_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
