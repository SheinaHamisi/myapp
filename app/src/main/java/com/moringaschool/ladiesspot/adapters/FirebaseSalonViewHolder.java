package com.moringaschool.ladiesspot.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;


import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.ladiesspot.Constant;
import com.moringaschool.ladiesspot.models.Salon;

import java.util.ArrayList;

public class FirebaseSalonViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener  {
    View mView;
    Context mContext;
    public FirebaseSalonViewHolder( View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Salon> salons = new ArrayList<>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constant.FIREBASE_CHILD_SALON).child(uid);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    salons.add(snapshot.getValue(Salon.class));
                }

                int itemPosition = getLayoutPosition();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
