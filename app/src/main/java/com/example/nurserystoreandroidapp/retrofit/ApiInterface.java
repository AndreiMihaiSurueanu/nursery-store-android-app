package com.example.nurserystoreandroidapp.retrofit;

import com.example.nurserystoreandroidapp.model.IndoorPlant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("data.json")
    Call<List<IndoorPlant>> getAllPlants();

}
