package com.moringaschool.ladiesspot.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.moringaschool.ladiesspot.R;
import com.moringaschool.ladiesspot.models.Salon;

import java.util.ArrayList;
import java.util.List;

public class SalonAdapter extends RecyclerView.Adapter<SalonAdapter.SalonViewHolder>{
    private Context context;
    List<Salon> salon;

    public SalonAdapter(Context context, List<Salon> salon) {
        this.context = context;
        this.salon = salon;
    }

    @NonNull
    @Override
    public SalonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_saloon_strip,parent,false);
        return new SalonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SalonViewHolder holder, int position) {
        holder.nameDisplay.setText(salon.get(position).getName());
        holder.locationDisplay.setText(salon.get(position).getLocation());
        holder.phoneDisplay.setText(salon.get(position).getPhone());
        holder.workDisplay.setText(salon.get(position).getWorkingHours());
        Glide.with(context)
                .asBitmap()
                .load(salon.get(position).getImage())
                .into(holder.image);



    }

    @Override
    public int getItemCount() {
        return salon.size();
    }

    public class SalonViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView nameDisplay;
        TextView locationDisplay;
        TextView phoneDisplay;
        TextView workDisplay;
        ImageView image;


        public SalonViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview);
            nameDisplay = itemView.findViewById(R.id.NameDisplay);
            locationDisplay = itemView.findViewById(R.id.LocationDisplay);
            phoneDisplay= itemView.findViewById(R.id.PhoneDisplay);
            workDisplay= itemView.findViewById(R.id.workDisplay);
            image= itemView.findViewById(R.id.image);

        }
    }



}
