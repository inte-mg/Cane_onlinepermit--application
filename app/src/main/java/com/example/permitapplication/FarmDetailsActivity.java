package com.example.permitapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.permitapplication.model.FarmDetails;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class FarmDetailsActivity extends AppCompatActivity {

    EditText name, mobile, address, id, county, subcounty, location, size, ownership, water, variety;
    LinearLayout submit;
    ImageView imageView;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_details);

        name = findViewById(R.id.etName);
        mobile = findViewById(R.id.etMobile);
        address = findViewById(R.id.etEmailAddress);
        id = findViewById(R.id.etIDNo);
        county = findViewById(R.id.etCounty);
        subcounty = findViewById(R.id.etSubCounty);
        location = findViewById(R.id.etLocation);
        size = findViewById(R.id.etFarmSize);
        ownership = findViewById(R.id.etLandOwnership);
        water = findViewById(R.id.etWaterSource);
        variety = findViewById(R.id.etVariety);
        imageView = findViewById(R.id.IV_back);
        progressBar = findViewById(R.id.progress);
        submit = findViewById(R.id.LL_submit);

        imageView.setOnClickListener(view -> startActivity(new Intent(FarmDetailsActivity.this, FarmerDashboardActivity.class)));

        FarmDetails farmDetails = (FarmDetails) getIntent().getSerializableExtra("farmDetails");
        if (farmDetails != null){
            name.setText(farmDetails.getFarmerName());
            mobile.setText(farmDetails.getMobileNo());
            address.setText(farmDetails.getEmailAddress());
            id.setText(farmDetails.getIdNo());
            county.setText(farmDetails.getCounty());
            subcounty.setText(farmDetails.getSubCounty());
            location.setText(farmDetails.getLocation());
            size.setText(farmDetails.getFarmSize());
            ownership.setText(farmDetails.getLandOwnership());
            water.setText(farmDetails.getWaterSource());
            variety.setText(farmDetails.getSugarcaneVariety());

            submit.setOnClickListener(view -> updateFarmProfile());
        } else {
            submit.setOnClickListener(view -> updateFarmProfile());
        }

    }
    private void updateFarmProfile() {

        String farmername = name.getText().toString();
        String contact = mobile.getText().toString();
        String email = address.getText().toString();
        String idno = id.getText().toString();
        String County = county.getText().toString();
        String subCounty = subcounty.getText().toString();
        String Location = location.getText().toString();
        String Size = size.getText().toString();
        String owner = ownership.getText().toString();
        String waterSource = water.getText().toString();
        String sugarcaneVariety = variety.getText().toString().trim();

        if (farmername.isEmpty()) {
            Toast.makeText(FarmDetailsActivity.this, "Enter your full name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (contact.isEmpty()) {
            Toast.makeText(FarmDetailsActivity.this, "Enter your Mobile", Toast.LENGTH_SHORT).show();
            return;
        }
        if (email.isEmpty()) {
            Toast.makeText(FarmDetailsActivity.this, "Enter your Email address", Toast.LENGTH_SHORT).show();
            return;
        }
        if (idno.isEmpty()) {
            Toast.makeText(FarmDetailsActivity.this, "Enter your ID number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (County.isEmpty()) {
            Toast.makeText(FarmDetailsActivity.this, "Enter your County", Toast.LENGTH_SHORT).show();
            return;
        }
        if (subCounty.isEmpty()) {
            Toast.makeText(FarmDetailsActivity.this, "Enter your SubCounty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Location.isEmpty()) {
            Toast.makeText(FarmDetailsActivity.this, "Enter farm Location", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Size.isEmpty()) {
            Toast.makeText(FarmDetailsActivity.this, "Enter Farm size ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (owner.isEmpty()) {
            Toast.makeText(FarmDetailsActivity.this, "Enter ownership", Toast.LENGTH_SHORT).show();
            return;
        }
        if (waterSource.isEmpty()) {
            Toast.makeText(FarmDetailsActivity.this, "Enter Farm Water Source", Toast.LENGTH_SHORT).show();
            return;
        }
        if (sugarcaneVariety.isEmpty()) {
            Toast.makeText(FarmDetailsActivity.this, "Enter SugarCane Variety", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        FirebaseDatabase.getInstance().getReference("Farm Details/" + FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(
                        new FarmDetails(
                                farmername, contact, email, idno, County, subCounty, Location,
                                Size, owner, waterSource, sugarcaneVariety
                        )
                ).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Farm Details Updated", Toast.LENGTH_SHORT).show();
                        name.setText("");
                        mobile.setText("");
                        address.setText("");
                        id.setText("");
                        county.setText("");
                        subcounty.setText("");
                        location.setText("");
                        size.setText("");
                        ownership.setText("");
                        water.setText("");
                        variety.setText("");

                        startActivity(new Intent(this, FarmerDashboardActivity.class));

                    } else {
                        Toast.makeText(this, Objects.requireNonNull(task.getException()).getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

}


