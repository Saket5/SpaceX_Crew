package com.saket.spacex.Remote;

import com.saket.spacex.model.Crew;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("crew")
    Call <List<Crew>> getCrewListRetro();
}
