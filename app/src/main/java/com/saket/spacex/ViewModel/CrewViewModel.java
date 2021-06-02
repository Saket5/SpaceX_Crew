package com.saket.spacex.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.saket.spacex.Repository.CrewRepository;
import com.saket.spacex.model.Crew;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CrewViewModel extends androidx.lifecycle.ViewModel {
    private CrewRepository crewRepository;
    private LiveData<List<Crew>> getAll;

    public CrewViewModel(CrewRepository crewRepository) {
        this.crewRepository=crewRepository;
        getAll=crewRepository.getCrewList();
    }

    public void insertAll(List<Crew> crewList)
    {
        crewRepository.insertAll(crewList);
    }
    public LiveData<List<Crew>> getCrewList()
    {
        return getAll;
    }
    public void deleteALl(){crewRepository.deleteAll();}
}
