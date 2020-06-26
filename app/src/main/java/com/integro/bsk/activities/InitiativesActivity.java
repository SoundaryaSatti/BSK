package com.integro.bsk.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.integro.bsk.R;
import com.integro.bsk.adapters.AboutUsAdapter;
import com.integro.bsk.adapters.InitiativesAdapters;
import com.integro.bsk.api.ApiClients;
import com.integro.bsk.api.ApiServices;
import com.integro.bsk.model.Initiatives;
import com.integro.bsk.model.InitiativesList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class InitiativesActivity extends AppCompatActivity {

    ApiServices apiServices;
    RecyclerView rvInitiatives;
    LinearLayoutManager manager;
    InitiativesAdapters initiativesAdapters;
    ArrayList<Initiatives>initiativesArrayList;
    Call<InitiativesList>initiativesListCall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initiatives);
//        getSupportActionBar().hide();
/*
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);
*/

        apiServices= ApiClients.getClients().create(ApiServices.class);
        rvInitiatives=findViewById(R.id.rv_Initiatives);
        manager=new LinearLayoutManager(this);
        rvInitiatives.setLayoutManager(manager);
        initiativesArrayList=new ArrayList<>();
        getInitiativesList();

    }
    public void getInitiativesList(){
        String date="2020-04-22";
        initiativesListCall=apiServices.getInitiativesList(date);
        initiativesListCall.enqueue(new Callback<InitiativesList>() {
            @Override
            public void onResponse(Call<InitiativesList> call, Response<InitiativesList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getInitiativesArrayList() != null) {
                        int size = response.body().getInitiativesArrayList().size();
                        Log.d("RESPONSE", "news Size " + size);
/*
                        for (int i = 0; i < size; i++) {
                            initiativesArrayList.add(response.body().getInitiativesArrayList().get(i));
                        }
*/
                        initiativesArrayList.addAll(response.body().getInitiativesArrayList());
                        if (initiativesArrayList.size() > 0) {
                            initiativesAdapters = new InitiativesAdapters(getApplicationContext(), initiativesArrayList);
                            rvInitiatives.setAdapter(initiativesAdapters);
                            rvInitiatives.setHasFixedSize(true);
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
            public void onFailure(Call<InitiativesList> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "onfailure:" + t.getMessage());

            }
        });
}
}

