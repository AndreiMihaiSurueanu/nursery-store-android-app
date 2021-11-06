package com.example.nurserystoreandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.nurserystoreandroidapp.adapter.IndoorPlantsAdapter;
import com.example.nurserystoreandroidapp.adapter.PotsAdapter;
import com.example.nurserystoreandroidapp.model.IndoorPlant;
import com.example.nurserystoreandroidapp.model.Pot;
import com.example.nurserystoreandroidapp.model.Recommended;
import com.example.nurserystoreandroidapp.retrofit.ApiInterface;
import com.example.nurserystoreandroidapp.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiInterface anInterface;
    RecyclerView plantRecyclerView, potRecyclerView;
    private IndoorPlantsAdapter indoorPlantsAdapter;
    private PotsAdapter potsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);

        Call<List<IndoorPlant>> call = anInterface.getAllPlants();
        call.enqueue(new Callback<List<IndoorPlant>>() {
            @Override
            public void onResponse(Call<List<IndoorPlant>> call, Response<List<IndoorPlant>> response) {

                List<IndoorPlant> s = response.body();
                Log.d("data", s.toString());

                // fetched data from server
                // set data to recyclerview adapter.

                getPlants(s.get(0).getRecommended());

                getPots(s.get(0).getPots());
            }

            @Override
            public void onFailure(Call<List<IndoorPlant>> call, Throwable t) {
            }
        });
    }

    private void getPlants(List<Recommended> recommendedList){

        plantRecyclerView = findViewById(R.id.plant_recycler);
        indoorPlantsAdapter = new IndoorPlantsAdapter(this, recommendedList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        plantRecyclerView.setLayoutManager(layoutManager);
        plantRecyclerView.setAdapter(indoorPlantsAdapter);
    }

    private  void getPots(List<Pot> potList){

        potRecyclerView = findViewById(R.id.pot_recycler);
        potsAdapter = new PotsAdapter(this, potList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        gridLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        potRecyclerView.setLayoutManager(gridLayoutManager);
        potRecyclerView.setAdapter(potsAdapter);
    }
}
