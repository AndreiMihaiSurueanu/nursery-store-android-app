package com.example.nurserystoreandroidapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nurserystoreandroidapp.PlantDetails;
import com.example.nurserystoreandroidapp.R;
import com.example.nurserystoreandroidapp.model.Recommended;

import java.util.List;

public class IndoorPlantsAdapter extends RecyclerView.Adapter<IndoorPlantsAdapter.IndoorPlantViewHolder> {

    public IndoorPlantsAdapter(Context context, List<Recommended> recommendedList) {
        this.context = context;
        this.recommendedList = recommendedList;
    }

    Context context;
    List<Recommended> recommendedList;


    @NonNull
    @Override
    public IndoorPlantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.indoor_row_items, parent, false);
        return new IndoorPlantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final IndoorPlantViewHolder holder, final int position) {

        holder.plantName.setText(recommendedList.get(position).getName());
        holder.plantPrice.setText(String.format("$ %s", recommendedList.get(position).getPrice()));

        Glide.with(context).load(recommendedList.get(position).getImageUrl()).into(holder.plantImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, PlantDetails.class);

                i.putExtra("name", recommendedList.get(position).getName());
                i.putExtra("cat", recommendedList.get(position).getCategory());
                i.putExtra("size", recommendedList.get(position).getSize());
                i.putExtra("price", recommendedList.get(position).getPrice());
                i.putExtra("type", recommendedList.get(position).getType());
                i.putExtra("placement", recommendedList.get(position).getPlacement());
                i.putExtra("pot", recommendedList.get(position).getPot());
                i.putExtra("layer", recommendedList.get(position).getLayer());
                i.putExtra("height", recommendedList.get(position).getHeight());
                i.putExtra("dim", recommendedList.get(position).getDimention());
                i.putExtra("imgurl", recommendedList.get(position).getImageUrl());
                i.putExtra("desc", recommendedList.get(position).getDesc());

                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recommendedList.size();
    }

    public static class IndoorPlantViewHolder extends RecyclerView.ViewHolder{

        ImageView plantImage;
        TextView plantName, plantPrice;

        public IndoorPlantViewHolder(@NonNull View itemView) {
            super(itemView);

            plantImage = itemView.findViewById(R.id.plant_image);
            plantName = itemView.findViewById(R.id.name);
            plantPrice = itemView.findViewById(R.id.plant_price);
        }
    }
}
