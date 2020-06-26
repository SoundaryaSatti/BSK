package com.integro.bsk.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.integro.bsk.R;
import com.integro.bsk.model.Collaboration;
import com.integro.bsk.model.Volunteer;

public class CollaborationDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collaboration_details);
        //getSupportActionBar().hide();
/*
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);
*/


        final  String TAG="CollaborationDetailsActivity";
        Collaboration collaboration = (Collaboration) getIntent().getSerializableExtra("COLLABORATION");

        ImageView ivImageCollaborationD=findViewById(R.id.iv_CollaborationD);
        TextView tvTitleCollaborationD = findViewById(R.id.tv_TitleCollaborationD);
        TextView tvDiscriptionCollaborationD = findViewById(R.id.tv_DescriptionCollaborationD);

        tvTitleCollaborationD.setText(collaboration.getTitle());
        tvDiscriptionCollaborationD.setText(collaboration.getDescription());
        Glide.with(getApplicationContext()).load(collaboration.getImage()).into(ivImageCollaborationD);

    }
}
