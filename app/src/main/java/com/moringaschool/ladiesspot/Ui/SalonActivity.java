package com.moringaschool.ladiesspot.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.ladiesspot.Constant;
import com.moringaschool.ladiesspot.Network.RetrofitBuilder;
import com.moringaschool.ladiesspot.Network.SalonApi;
import com.moringaschool.ladiesspot.R;
import com.moringaschool.ladiesspot.adapters.SalonAdapter;
import com.moringaschool.ladiesspot.models.Salon;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.internal.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SalonActivity extends AppCompatActivity{
    @BindView(R.id.recyclerview) RecyclerView recyclerView;
    RetrofitBuilder retrofitBuilder;
    List<Salon> allSalons;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentSalon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saloon);
        ButterKnife.bind(this);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentSalon = mSharedPreferences.getString(Constant.PREFERENCES_SALON_KEY, null);
         mEditor = mSharedPreferences.edit();
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        inflater.inflate(R.menu.menu_main, menu);
        ButterKnife.bind(this);


        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String allSalons ){
                addToSharedPreferences(allSalons);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String allSalons ) {
                return false;
            }
        });

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(SalonActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    private void addToSharedPreferences(String allSalons) {
        mEditor.putString(Constant.PREFERENCES_SALON_KEY, allSalons).apply();
    }

    private void startAdapter(List<Salon> allSalons) {
        SalonAdapter salonAdapter = new SalonAdapter(SalonActivity.this, allSalons);
        recyclerView.setAdapter(salonAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }



}