package com.example.permitapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.permitapplication.databinding.ActivityGeneratePermitBinding;
import com.google.firebase.database.FirebaseDatabase;

public class GeneratePermitActivity extends AppCompatActivity {

    ActivityGeneratePermitBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGeneratePermitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String farmerName = getIntent().getStringExtra("farmerName");
        String farmName = getIntent().getStringExtra("farmName");
        String location = getIntent().getStringExtra("location");
        String subCounty = getIntent().getStringExtra("subCounty");
        String county = getIntent().getStringExtra("county");
        String agentName = getIntent().getStringExtra("agentName");
        String uid = getIntent().getStringExtra("uid");

        binding.textViewname.setText(farmerName);
        binding.textViewFarmName.setText(farmName);
        binding.textViewArea.setText(location);
        binding.textViewSubCounty.setText(subCounty);
        binding.textViewCounty.setText(county);
        binding.textViewAgentName.setText(agentName);

        binding.LLGenerate.setOnClickListener(view -> {
            FirebaseDatabase.getInstance().getReference("Granted Permits").child(uid).child("amount").setValue(
                    binding.textViewAmount.getText().toString()
            );
            FirebaseDatabase.getInstance().getReference("Granted Permits").child(uid).child("expireDate").setValue(
                    binding.textViewDate.getText().toString().trim()
            ).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(this, "Permit generated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(GeneratePermitActivity.this , AllPermit.class));
                    finish();
                }
            });
        });

    }
}