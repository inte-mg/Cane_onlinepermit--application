package com.example.permitapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.permitapplication.databinding.ActivityApplyPermitBinding;
import com.example.permitapplication.model.FarmDetails;
import com.example.permitapplication.model.Permit;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
;import java.util.Objects;

public class ApplyPermitActivity extends AppCompatActivity {
    TextView name, mobile, address, id, county, subcounty, location, size, ownership, water, variety;
    EditText farmname, yield, date;
    ImageView imageView;
    ProgressBar progressBar;
    LinearLayout apply;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        com.example.permitapplication.databinding.ActivityApplyPermitBinding binding = ActivityApplyPermitBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        name = findViewById(R.id.tvName);
        mobile = findViewById(R.id.tvMobile);
        address = findViewById(R.id.tvEmailAddress);
        id = findViewById(R.id.tvIDNo);
        county = findViewById(R.id.tvCounty);
        subcounty = findViewById(R.id.tvSubCounty);
        location = findViewById(R.id.tvLocation);
        size = findViewById(R.id.tvFarmSize);
        ownership = findViewById(R.id.tvLandOwnership);
        water = findViewById(R.id.tvWaterSource);
        variety = findViewById(R.id.tvVariety);

        farmname = findViewById(R.id.etFarmName);
        yield = findViewById(R.id.etYield);
        date = findViewById(R.id.etDate);

        imageView = findViewById(R.id.IV_back);
        progressBar = findViewById(R.id.progressBar);
        apply = findViewById(R.id.LL_submit);

        imageView.setOnClickListener(view -> startActivity(new Intent(ApplyPermitActivity.this, FarmerDashboardActivity.class)));

        FarmDetails details = (FarmDetails) getIntent().getSerializableExtra("farmDetails");
        // Autofill the fields if the farmer has already updated their farm details
        if (details != null) {
            binding.tvName.setText(details.getFarmerName());
            binding.tvMobile.setText(details.getMobileNo());
            binding.tvEmailAddress.setText(details.getEmailAddress());
            binding.tvIDNo.setText(details.getIdNo());
            binding.tvCounty.setText(details.getCounty());
            binding.tvSubCounty.setText(details.getSubCounty());
            binding.tvLocation.setText(details.getLocation());
            binding.tvFarmSize.setText(details.getFarmSize());
            binding.tvLandOwnership.setText(details.getLandOwnership());
            binding.tvWaterSource.setText(details.getWaterSource());
            binding.tvVariety.setText(details.getSugarcaneVariety());

            apply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String Date = date.getText().toString().trim();
                    if (Date.isEmpty()) {
                        Toast.makeText(ApplyPermitActivity.this, "Enter date of application/ Enter today's date", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    applyPermit(name, mobile, address, id, county, subcounty, location,size,ownership,water,variety,farmname,yield,date);
                    Toast.makeText(ApplyPermitActivity.this, "Permit Application successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ApplyPermitActivity.this, FarmerDashboardActivity.class));
                }
            });

        } else {
            // If the user has not updated their profile, fill the fields manually.
            // Continue with your application functionality here. That is what happens when the job seeker clicks on the apply button.
        }
    }
        private void applyPermit (TextView name, TextView mobile, TextView address, TextView id, TextView county, TextView subcounty,
                                  TextView location, TextView size, TextView ownership, TextView water, TextView variety, EditText farmname,
                                  EditText yield, EditText date){

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

            String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
            Permit permit = new Permit(name.getText().toString(), mobile.getText().toString(), address.getText().toString(),id.getText().toString(),
                    county.getText().toString(), subcounty.getText().toString(), location.getText().toString(), size.getText().toString(),
                    ownership.getText().toString(), water.getText().toString(), variety.getText().toString(), farmname.getText().toString(),
                    yield.getText().toString(), date.getText().toString() , uid);


            ref.child("Permit Applications").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                    .setValue(permit).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(ApplyPermitActivity.this, "Insertion Complete", Toast.LENGTH_SHORT).show();
                }
            });
        }
}