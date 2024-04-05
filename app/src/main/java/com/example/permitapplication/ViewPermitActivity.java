package com.example.permitapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.permitapplication.databinding.ActivityViewPermitBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class ViewPermitActivity extends AppCompatActivity {

    ActivityViewPermitBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewPermitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseDatabase.getInstance().getReference("Granted Permits/"+ Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        binding.textViewname.setText(snapshot.child("farmerName").getValue(String.class));
                        binding.textViewFarmName.setText(snapshot.child("farmName").getValue(String.class));
                        binding.textViewArea.setText(snapshot.child("farmArea").getValue(String.class));
                        binding.textViewSubCounty.setText(snapshot.child("subCounty").getValue(String.class));
                        binding.textViewCounty.setText(snapshot.child("county").getValue(String.class));
                        binding.textViewAgentName.setText(snapshot.child("agentName").getValue(String.class));
                        binding.textViewAmount.setText(snapshot.child("amount").getValue(String.class));
                        binding.textViewDate.setText(snapshot.child("expireDate").getValue(String.class));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}