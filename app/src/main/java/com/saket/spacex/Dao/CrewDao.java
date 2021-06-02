package com.saket.spacex.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.saket.spacex.model.Crew;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CrewDao {

    @Query("SELECT * FROM Crew_Table")
    LiveData<List<Crew>> getCrewList();
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Crew> crewList);
    @Query("DELETE FROM Crew_Table")
    void deleteTable();
}
