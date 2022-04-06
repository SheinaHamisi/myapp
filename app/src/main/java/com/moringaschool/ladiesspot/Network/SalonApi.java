package com.moringaschool.ladiesspot.Network;



import com.moringaschool.ladiesspot.models.Salon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SalonApi {
    @GET ("salons")
    Call<List<Salon>> getAllSalons();
}
