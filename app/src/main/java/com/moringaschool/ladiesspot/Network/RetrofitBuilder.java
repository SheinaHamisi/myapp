package com.moringaschool.ladiesspot.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    public RetrofitBuilder() {
    }

    public Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl("https://ladies-spot.herokuapp.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
