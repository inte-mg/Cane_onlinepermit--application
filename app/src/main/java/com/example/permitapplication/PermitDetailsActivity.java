package com.example.permitapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.permitapplication.model.FarmDetails;
import com.example.permitapplication.model.GrantedPermit;
import com.example.permitapplication.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class PermitDetailsActivity extends AppCompatActivity {
    TextView name, mobile, address, id, County, subcounty, Location, size, ownership, water, Variety, farmname, yield, Date;
    ArrayList<FarmDetails> arrayList;
    LinearLayout submit;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permit_details);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        arrayList = new ArrayList<>();

        imageView = findViewById(R.id.IV_back);
        submit = findViewById(R.id.LL_submit);


        imageView.setOnClickListener(view -> startActivity(new Intent(PermitDetailsActivity.this, PermitActivity.class)));

        name = findViewById(R.id.tvName);
        mobile = findViewById(R.id.tvMobile);
        address = findViewById(R.id.tvEmailAddress);
        id = findViewById(R.id.tvIDNo);
        County = findViewById(R.id.tvCounty);
        subcounty = findViewById(R.id.tvSubCounty);
        Location = findViewById(R.id.tvLocation);
        size = findViewById(R.id.tvFarmSize);
        ownership = findViewById(R.id.tvLandOwnership);
        water = findViewById(R.id.tvWaterSource);
        Variety = findViewById(R.id.tvVariety);
        farmname = findViewById(R.id.tvFarmName);
        yield = findViewById(R.id.tvYield);
        Date = findViewById(R.id.tvDate);

        String farmerName = getIntent().getStringExtra("farmerName");
        String mobileNo = getIntent().getStringExtra("mobileNo");
        String emailAddress = getIntent().getStringExtra("emailAddress");
        String idNo = getIntent().getStringExtra("idNo");
        String county = getIntent().getStringExtra("county");
        String subCounty = getIntent().getStringExtra("subCounty");
        String location = getIntent().getStringExtra("location");
        String farmSize = getIntent().getStringExtra("farmSize");
        String landOwnership = getIntent().getStringExtra("landOwnership");
        String waterSource = getIntent().getStringExtra("waterSource");
        String variety = getIntent().getStringExtra("variety");
        String farmName = getIntent().getStringExtra("farmName");
        String previousYield = getIntent().getStringExtra("previousYield");
        String date = getIntent().getStringExtra("date");
        String uid = getIntent().getStringExtra("uid");

        submit.setOnClickListener(view -> {
                    String agentUid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
                    FirebaseDatabase.getInstance().getReference("Users").child(agentUid)
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String agentName = Objects.requireNonNull(snapshot.getValue(User.class)).getUsername();

                                    assert uid != null;
                                    FirebaseDatabase.getInstance().getReference("Granted Permits").child(uid)
                                            .setValue(new GrantedPermit(
                                                    farmerName,
                                                    farmName,
                                                    location,
                                                    subCounty,
                                                    county,
                                                    agentName,
                                                    "",
                                                    ""

                                            )).addOnCompleteListener(task -> {
                                                if (task.isSuccessful()) {
                                                    startActivity(new Intent(PermitDetailsActivity.this, GeneratePermitActivity.class)
                                                            .putExtra("farmerName", farmerName)
                                                            .putExtra("farmName", farmName)
                                                            .putExtra("location", location)
                                                            .putExtra("subCounty", subCounty)
                                                            .putExtra("county", county)
                                                            .putExtra("agentName", agentName)
                                                            .putExtra("uid", uid)

                                                    );
                                                    Toast.makeText(PermitDetailsActivity.this, "Done", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });


                }
        );

        name.setText(farmerName);
        mobile.setText(mobileNo);
        address.setText(emailAddress);
        id.setText(idNo);
        County.setText(county);
        subcounty.setText(subCounty);
        Location.setText(location);
        size.setText(farmSize);
        ownership.setText(landOwnership);
        water.setText(waterSource);
        Variety.setText(variety);
        farmname.setText(farmName);
        yield.setText(previousYield);
        Date.setText(date);

    }
}
