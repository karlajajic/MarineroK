package com.example.marinero_kj.persistance.localStorage;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.marinero_kj.pojo.Sight;

import java.util.List;

@Dao
public interface SightDao {

    @Insert
    void insert(Sight sight);

    @Update
    void update(Sight sight);

    @Delete
    void delete(Sight sight);

    @Query("Delete from sight_table")
    void deleteAllSights();

    @Query("Select * from sight_table order by name desc")
    LiveData<List<Sight>> getAllSights();

    //isTown is a boolean, Room sets true to 1, false to 0
    @Query("Select * from sight_table where isTown=1")
    LiveData<List<Sight>> getAllTowns();

    @Query("Select * from sight_table where isTown=0")
    LiveData<List<Sight>> getAllBeaches();

    @Query("Select * from sight_table where isHearted=1")
    LiveData<List<Sight>> getAllHarts();
}
