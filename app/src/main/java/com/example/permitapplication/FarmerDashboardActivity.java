package com.example.permitapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.permitapplication.model.FarmDetails;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FarmerDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_dashboard);

        CardView farm = findViewById(R.id.cardFarmDetails);
        farm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference("Farm Details").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                FarmDetails farmDetails = snapshot.getValue(FarmDetails.class);
                                if (farmDetails != null) {
                                    startActivity(new Intent(FarmerDashboardActivity.this, FarmDetailsActivity.class)
                                            .putExtra("farmDetails" , farmDetails));
                                } else {
                                    startActivity(new Intent(FarmerDashboardActivity.this, FarmDetailsActivity.class));
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
            }
        });

        CardView permit = findViewById(R.id.cardApply);
        permit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference("Farm Details").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                FarmDetails farmDetails = snapshot.getValue(FarmDetails.class);
                                if (farmDetails != null) {
                                    startActivity(new Intent(FarmerDashboardActivity.this, ApplyPermitActivity.class)
                                            .putExtra("farmDetails" , farmDetails));
                                } else {
                                    startActivity(new Intent(FarmerDashboardActivity.this, ApplyPermitActivity.class));
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
            }
        });

        CardView view = findViewById(R.id.cardViewPermit);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerDashboardActivity.this, ViewPermitActivity.class));
            }
        });

        CardView exit = findViewById(R.id.cardLogout);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Sign out the user from Firebase Authentication
                FirebaseAuth.getInstance().signOut();

                startActivity(new Intent(FarmerDashboardActivity.this, LoginActivity.class));
                finish();
            }
        });

    }
}