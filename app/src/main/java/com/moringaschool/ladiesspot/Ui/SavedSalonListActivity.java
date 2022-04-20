package com.moringaschool.ladiesspot.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.ladiesspot.Constant;
import com.moringaschool.ladiesspot.R;
import com.moringaschool.ladiesspot.adapters.FirebaseSalonViewHolder;
import com.moringaschool.ladiesspot.models.Salon;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedSalonListActivity extends AppCompatActivity {

    private DatabaseReference mSalonReference;
    private FirebaseRecyclerAdapter<Salon, FirebaseSalonViewHolder> mFirebaseAdapter;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_salon_list);
        ButterKnife.bind(this);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        mSalonReference = FirebaseDatabase
                .getInstance()
                .getReference(Constant.FIREBASE_CHILD_SALON)
                .child(uid);
                setUpFirebaseAdapter();
                hideProgressBar();
                showSalons();
    }

    private void showSalons() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    private void setUpFirebaseAdapter() {
        FirebaseRecyclerOptions<Salon> options =
                new FirebaseRecyclerOptions.Builder<Salon>()
                        .setQuery(mSalonReference, Salon.class)
                        .build();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Salon, FirebaseSalonViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseSalonViewHolder holder, int position, @NonNull Salon salons) {
            }

            @Override
            public FirebaseSalonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_saloon_strip, parent, false);
                return new FirebaseSalonViewHolder(view);
            }
        };

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);

    }
    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mFirebaseAdapter!= null) {
            mFirebaseAdapter.stopListening();
        }
    }
}