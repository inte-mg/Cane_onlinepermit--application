package com.example.permitapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.permitapplication.model.AgentDetails;
import com.example.permitapplication.model.FarmDetails;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {
    EditText name, mobile, address, id, county, subcounty, location;
    LinearLayout submit;
    ImageView imageView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        name = findViewById(R.id.etName);
        mobile = findViewById(R.id.etMobile);
        address = findViewById(R.id.etEmailAddress);
        id = findViewById(R.id.etIDNo);
        county = findViewById(R.id.etCounty);
        subcounty = findViewById(R.id.etSubCounty);
        location = findViewById(R.id.etLocation);
        imageView = findViewById(R.id.IV_back);
        progressBar = findViewById(R.id.progress);
        submit = findViewById(R.id.LL_submit);

        imageView.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, AgentDashboardActivity.class)));

        AgentDetails agentDetails = (AgentDetails) getIntent().getSerializableExtra("agentDetails");
        if (agentDetails != null) {
            name.setText(agentDetails.getAgentName());
            mobile.setText(agentDetails.getMobileNo());
            address.setText(agentDetails.getEmailAddress());
            id.setText(agentDetails.getIdNo());
            county.setText(agentDetails.getCounty());
            subcounty.setText(agentDetails.getSubCounty());
            location.setText(agentDetails.getLocation());

            submit.setOnClickListener(view -> updateAgentProfile());
        } else {
            submit.setOnClickListener(view -> updateAgentProfile());
        }

    }
    private void updateAgentProfile() {

        String agentname = name.getText().toString();
        String contact = mobile.getText().toString();
        String email = address.getText().toString();
        String idno = id.getText().toString();
        String County = county.getText().toString();
        String subCounty = subcounty.getText().toString();
        String Location = location.getText().toString();

        if (agentname.isEmpty()) {
            Toast.makeText(HomeActivity.this, "Enter your full name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (contact.isEmpty()) {
            Toast.makeText(HomeActivity.this, "Enter your Mobile", Toast.LENGTH_SHORT).show();
            return;
        }
        if (email.isEmpty()) {
            Toast.makeText(HomeActivity.this, "Enter your Email address", Toast.LENGTH_SHORT).show();
            return;
        }
        if (idno.isEmpty()) {
            Toast.makeText(HomeActivity.this, "Enter your ID number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (County.isEmpty()) {
            Toast.makeText(HomeActivity.this, "Enter your County", Toast.LENGTH_SHORT).show();
            return;
        }
        if (subCounty.isEmpty()) {
            Toast.makeText(HomeActivity.this, "Enter your SubCounty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Location.isEmpty()) {
            Toast.makeText(HomeActivity.this, "Enter your Location", Toast.LENGTH_SHORT).show();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        FirebaseDatabase.getInstance().getReference("Agent Details/" + FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(
                        new AgentDetails(
                                agentname, contact, email, idno, County, subCounty, Location
                        )
                ).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Agent Details Updated", Toast.LENGTH_SHORT).show();
                        name.setText("");
                        mobile.setText("");
                        address.setText("");
                        id.setText("");
                        county.setText("");
                        subcounty.setText("");
                        location.setText("");

                        startActivity(new Intent(this, AgentDashboardActivity.class));

                    } else {
                        Toast.makeText(this, Objects.requireNonNull(task.getException()).getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

}

