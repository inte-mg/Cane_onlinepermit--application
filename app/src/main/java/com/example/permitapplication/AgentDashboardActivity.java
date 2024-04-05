package com.example.permitapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.permitapplication.databinding.ActivityAgentDashboardBinding;
import com.example.permitapplication.databinding.ActivityGeneratePermitBinding;
import com.example.permitapplication.databinding.ActivityPermitBinding;
import com.example.permitapplication.model.AgentDetails;
import com.example.permitapplication.model.FarmDetails;
import com.example.permitapplication.model.Permit;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class AgentDashboardActivity extends AppCompatActivity {
    ActivityAgentDashboardBinding binding;
    RecyclerView recyclerView;
    ArrayList<Permit> permits;
    PermitAdapter.OnPermitClicked onPermitClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAgentDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);


        onPermitClicked = position -> startActivity(new Intent(AgentDashboardActivity.this, PermitDetailsActivity.class)
                .putExtra("farmerName", permits.get(position).getFarmerName())
                .putExtra("mobileNo", permits.get(position).getMobileNo())
                .putExtra("emailAddress", permits.get(position).getEmailAddress())
                .putExtra("idNo", permits.get(position).getIdNo())
                .putExtra("county", permits.get(position).getCounty())
                .putExtra("subCounty", permits.get(position).getSubCounty())
                .putExtra("location", permits.get(position).getLocation())
                .putExtra("farmSize", permits.get(position).getFarmSize())
                .putExtra("landOwnership", permits.get(position).getLandOwnership())
                .putExtra("waterSource", permits.get(position).getWaterSource())
                .putExtra("variety", permits.get(position).getSugarcaneVariety())
                .putExtra("farmName", permits.get(position).getFarmName())
                .putExtra("previousYield", permits.get(position).getPreviousYield())
                .putExtra("date", permits.get(position).getDate())
                .putExtra("uid", permits.get(position).getUid())
        );

        recyclerView = binding.appliedRecyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        permits = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Permit Applications");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                permits.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    permits.add(ds.getValue(Permit.class));

                }
                recyclerView.setAdapter(new PermitAdapter(permits, AgentDashboardActivity.this, onPermitClicked));
                binding.appliedRecyclerView.setVisibility(View.VISIBLE);
                binding.applicationsProgress.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navHome:
                            // Start HomeActivity or perform relevant action
                            FirebaseDatabase.getInstance().getReference("Agent Details").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            AgentDetails agentDetails = snapshot.getValue(AgentDetails.class);
                                            if (agentDetails != null) {
                                                startActivity(new Intent(AgentDashboardActivity.this, HomeActivity.class)
                                                        .putExtra("agentDetails" , agentDetails));
                                            } else {
                                                startActivity(new Intent(AgentDashboardActivity.this, HomeActivity.class));
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                            Toast.makeText(AgentDashboardActivity.this, "Home", Toast.LENGTH_SHORT).show();
                            return true;
                        case R.id.navGenPermit:
                            // Start AppliedPermitActivity or perform relevant action
                            startActivity(new Intent(AgentDashboardActivity.this, PermitActivity.class));
                            Toast.makeText(AgentDashboardActivity.this, "Applied Permit", Toast.LENGTH_SHORT).show();
                            return true;
                        case R.id.navGeneratedPermit:
                            // Start GeneratedPermitActivity or perform relevant action
                            startActivity(new Intent(AgentDashboardActivity.this, AllPermit.class));
                            Toast.makeText(AgentDashboardActivity.this, "Generated Permit", Toast.LENGTH_SHORT).show();
                            return true;
                        case R.id.navChangePass:
                            // Start ChangePasswordActivity or perform relevant action
                            startActivity(new Intent(AgentDashboardActivity.this, ForgotPasswordActivity.class));
                            Toast.makeText(AgentDashboardActivity.this, "Change Password", Toast.LENGTH_SHORT).show();
                            return true;
                        case R.id.navLogout:
                            // Sign out the user from Firebase Authentication
                            FirebaseAuth.getInstance().signOut();

                            // Start LogoutActivity or perform relevant action
                            startActivity(new Intent(AgentDashboardActivity.this, LoginActivity.class));
                            Toast.makeText(AgentDashboardActivity.this, "You have successfully Logged out", Toast.LENGTH_SHORT).show();
                            finish();
                    }
                    finish();
                    return false;
                }
            };
}