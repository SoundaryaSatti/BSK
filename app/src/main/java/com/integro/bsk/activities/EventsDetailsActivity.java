package com.integro.bsk.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.integro.bsk.R;
import com.integro.bsk.model.Activities;
import com.integro.bsk.model.News;

public class EventsDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_details);
       // getSupportActionBar().hide();
/*
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);
*/

        final  String TAG="EventsDetailsActivity";
        Activities activities = (Activities) getIntent().getSerializableExtra("ACTIVITY");

        ImageView ivImage=findViewById(R.id.iv_Event);
        TextView tvTitle = findViewById(R.id.tv_TitleEvent);
        TextView tvDiscription = findViewById(R.id.tv_DescriptionEvent);

        tvTitle.setText(activities.getTitle());
        tvDiscription.setText(activities.getDescription());
        Glide.with(getApplicationContext())
                .load(activities.getImage())
                .into(ivImage);
    }
}
