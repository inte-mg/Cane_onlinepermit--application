package com.example.permitapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.permitapplication.databinding.ActivityAllPermitBinding;
import com.example.permitapplication.databinding.ActivityPermitBinding;
import com.example.permitapplication.model.GrantedPermit;
import com.example.permitapplication.model.Permit;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllPermit extends AppCompatActivity {
    ActivityAllPermitBinding binding;
    RecyclerView recyclerView;
    LinearLayout submit;
    ImageView imageView;
    ArrayList<GrantedPermit> granted;
    GeneratedAdapter.OnGrantClicked onGrantClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllPermitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        imageView = findViewById(R.id.IV_back);

        imageView.setOnClickListener(view -> startActivity(new Intent(AllPermit.this, FarmerDashboardActivity.class)));

        onGrantClicked = position -> startActivity(new Intent(AllPermit.this, ViewPermitActivity.class)
                .putExtra("farmerName", granted.get(position).getFarmerName())
                .putExtra("farmName", granted.get(position).getFarmName())
                .putExtra("farmArea", granted.get(position).getFarmArea())
                .putExtra("subCounty", granted.get(position).getSubCounty())
                .putExtra("county", granted.get(position).getCounty())
                .putExtra("subCounty", granted.get(position).getSubCounty())
                .putExtra("agentName", granted.get(position).getAgentName())
                .putExtra("amount", granted.get(position).getAmount())
                .putExtra("expiry", granted.get(position).getExpireDate())
        );

        recyclerView = binding.jobsRecyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        granted = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Granted Permits");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                granted.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    granted.add(ds.getValue(GrantedPermit.class));

                }
                recyclerView.setAdapter(new GeneratedAdapter(granted, AllPermit.this, onGrantClicked));
                binding.jobsRecyclerView.setVisibility(View.VISIBLE);
                binding.jobsProgress.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}