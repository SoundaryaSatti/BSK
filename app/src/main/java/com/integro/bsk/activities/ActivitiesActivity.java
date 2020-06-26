package com.integro.bsk.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.integro.bsk.R;
import com.integro.bsk.adapters.ActivitiesAdapter;
import com.integro.bsk.adapters.NewsLetterAdapter;
import com.integro.bsk.api.ApiClients;
import com.integro.bsk.api.ApiServices;
import com.integro.bsk.model.Activities;
import com.integro.bsk.model.ActivitiesList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ActivitiesActivity extends AppCompatActivity {

    ApiServices apiServices;
    RecyclerView rvActivities;
    LinearLayoutManager manager;
    ActivitiesAdapter activitiesAdapter;
    ArrayList<Activities> activitiesArrayList;
    Call<ActivitiesList> activitiesListCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);
       // getSupportActionBar().hide();
       /* if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);*/

        apiServices = ApiClients.getClients().create(ApiServices.class);
        rvActivities = findViewById(R.id.rv_Activities);
        manager = new LinearLayoutManager(this);
        rvActivities.setLayoutManager(manager);
        activitiesArrayList = new ArrayList<>();
        getActivitiesList();
    }

    public void getActivitiesList() {
        String date = "";
        activitiesListCall = apiServices.getActivitiesList(date);
        activitiesListCall.enqueue(new Callback<ActivitiesList>() {
            @Override
            public void onResponse(Call<ActivitiesList> call, Response<ActivitiesList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getActivitiesArrayList() != null) {
                        int size = response.body().getActivitiesArrayList().size();
                        Log.d("RESPONSE", "news Size " + size);

                        activitiesArrayList.addAll(response.body().getActivitiesArrayList());

                        if (activitiesArrayList.size() > 0) {
                            activitiesAdapter = new ActivitiesAdapter(getApplicationContext(), activitiesArrayList);
                            rvActivities.setAdapter(activitiesAdapter);


                        } else {
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }else {
                    Toast.makeText(getApplicationContext(), "Something went wrong, try again", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ActivitiesList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onfailure:" + t.getMessage());

            }
        });

    }
}