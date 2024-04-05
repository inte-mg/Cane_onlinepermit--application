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

import com.example.permitapplication.databinding.ActivityPermitBinding;
import com.example.permitapplication.model.Permit;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PermitActivity extends AppCompatActivity {
    ActivityPermitBinding binding;
    RecyclerView recyclerView;
    LinearLayout submit;
    ImageView imageView;
    ArrayList<Permit> permits;
    PermitAdapter.OnPermitClicked onPermitClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPermitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        imageView = findViewById(R.id.IV_back);

        imageView.setOnClickListener(view -> startActivity(new Intent(PermitActivity.this, AgentDashboardActivity.class)));


        onPermitClicked = position -> startActivity(new Intent(PermitActivity.this, PermitDetailsActivity.class)
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
                        recyclerView.setAdapter(new PermitAdapter(permits, PermitActivity.this, onPermitClicked));
                        binding.appliedRecyclerView.setVisibility(View.VISIBLE);
                        binding.applicationsProgress.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}
