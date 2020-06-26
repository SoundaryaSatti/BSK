package com.integro.bsk.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.integro.bsk.R;
import com.integro.bsk.model.Activities;
import com.integro.bsk.model.Volunteer;

public class VolunteerDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_details);
        //getSupportActionBar().hide();
        /*if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);
*/

        final  String TAG="VolunteerDetails";
        Volunteer volunteer = (Volunteer) getIntent().getSerializableExtra("VOLUNTEER");

        ImageView ivImageVolunteerD=findViewById(R.id.iv_VolunteerD);
        TextView tvTitleVolunteerD = findViewById(R.id.tv_TitleVolunteerD);
        TextView tvDiscriptionVolunteerD = findViewById(R.id.tv_DescriptionVolunteerD);

        tvTitleVolunteerD.setText(volunteer.getTitle());
        tvDiscriptionVolunteerD.setText(volunteer.getDescription());
        Glide.with(getApplicationContext()).load(volunteer.getImage()).into(ivImageVolunteerD);

           }
}
