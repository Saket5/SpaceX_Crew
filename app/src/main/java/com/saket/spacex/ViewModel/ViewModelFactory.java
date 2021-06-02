package com.saket.spacex.ViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.saket.spacex.Repository.CrewRepository;

import org.jetbrains.annotations.NotNull;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    CrewRepository crewRepository;

    public ViewModelFactory(CrewRepository crewRepository) {
        this.crewRepository = crewRepository;
    }

    @NonNull
    @Override
    public <T extends androidx.lifecycle.ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(CrewViewModel.class)) {
            return (T) new CrewViewModel(crewRepository);
        }
        return null;

    }
}
