package com.integro.bsk.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.integro.bsk.R;
import com.integro.bsk.adapters.ActivitiesAdapter;
import com.integro.bsk.adapters.VolunteerAdapter;
import com.integro.bsk.api.ApiClients;
import com.integro.bsk.api.ApiServices;
import com.integro.bsk.model.Volunteer;
import com.integro.bsk.model.VolunteerList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class VolunteerActivity extends AppCompatActivity {

    ApiServices apiServices;
    RecyclerView rvVolunteer;
    LinearLayoutManager manager;
    VolunteerAdapter volunteerAdapter;
    ArrayList<Volunteer> volunteerArrayList;
    Call<VolunteerList> volunteerListCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);
        //getSupportActionBar().hide();
       /* if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);*/


        apiServices = ApiClients.getClients().create(ApiServices.class);
        rvVolunteer = findViewById(R.id.rv_Volunteer);
        manager = new LinearLayoutManager(this);
        rvVolunteer.setLayoutManager(manager);
        volunteerArrayList = new ArrayList<>();
        getVolunteerList();
    }

    public void getVolunteerList() {
        String date = "2020-04-06";
        volunteerListCall = apiServices.getVolunteerList(date);
        volunteerListCall.enqueue(new Callback<VolunteerList>() {
            @Override
            public void onResponse(Call<VolunteerList> call, Response<VolunteerList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getVolunteerArrayList() != null) {
                        int size = response.body().getVolunteerArrayList().size();
                        Log.d("RESPONSE", "news Size " + size);
                        volunteerArrayList.addAll(response.body().getVolunteerArrayList());
                        if (volunteerArrayList.size() > 0) {
                            volunteerAdapter = new VolunteerAdapter(getApplicationContext(), volunteerArrayList);
                            rvVolunteer.setAdapter(volunteerAdapter);
                        } else {
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Something went wrong, try again", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<VolunteerList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onfailure:" + t.getMessage());

            }
        });

    }
}
