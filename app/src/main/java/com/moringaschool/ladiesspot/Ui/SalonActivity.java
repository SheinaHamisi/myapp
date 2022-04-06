package com.moringaschool.ladiesspot.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.moringaschool.ladiesspot.Network.RetrofitBuilder;
import com.moringaschool.ladiesspot.Network.SalonApi;
import com.moringaschool.ladiesspot.R;
import com.moringaschool.ladiesspot.adapters.SalonAdapter;
import com.moringaschool.ladiesspot.models.Salon;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SalonActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.recyclerview) RecyclerView recyclerView;
    @BindView(R.id.arrow) ImageView arrow;
    RetrofitBuilder retrofitBuilder;
    List<Salon> allSalons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saloon);
        ButterKnife.bind(this);
        arrow.setOnClickListener(this);
        allSalons =new ArrayList<>();
        retrofitBuilder = new RetrofitBuilder();
        Retrofit retrofit = retrofitBuilder.getRetrofit();
        SalonApi salonApi = retrofit.create(SalonApi.class);
        Call<List<Salon>> call = salonApi.getAllSalons();
        call.enqueue(new Callback<List<Salon>>() {
            @Override
            public void onResponse(Call<List<Salon>> call, Response<List<Salon>> response) {
                if(response.isSuccessful()){
                    List<Salon> salons = response.body();
                    allSalons.addAll(salons);
                    startAdapter(allSalons);
                }
                
            }

            @Override
            public void onFailure(Call<List<Salon>> call, Throwable t) {

            }
        });
    }

    private void startAdapter(List<Salon> allSalons) {
        SalonAdapter salonAdapter = new SalonAdapter(SalonActivity.this, allSalons);
        recyclerView.setAdapter(salonAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void onClick(View view) {
        if(view == arrow){
            onBackPressed();
        }

    }
}