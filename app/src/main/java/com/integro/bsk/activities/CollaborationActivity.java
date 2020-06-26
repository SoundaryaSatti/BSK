package com.integro.bsk.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.integro.bsk.R;
import com.integro.bsk.adapters.CollaborationAdapter;
import com.integro.bsk.adapters.VolunteerAdapter;
import com.integro.bsk.api.ApiClients;
import com.integro.bsk.api.ApiServices;
import com.integro.bsk.model.Collaboration;
import com.integro.bsk.model.CollaborationList;
import com.integro.bsk.model.VolunteerList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class CollaborationActivity extends AppCompatActivity {

    ApiServices apiServices;
    RecyclerView rvCollaboration;
    LinearLayoutManager manager;
    CollaborationAdapter collaborationAdapter;
    ArrayList<Collaboration> collaborationArrayList;
    Call<CollaborationList> collaborationListCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collaboration);
       // getSupportActionBar().hide();
/*
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);
*/

        apiServices = ApiClients.getClients().create(ApiServices.class);
        rvCollaboration = findViewById(R.id.rv_Collaboration);
        manager = new LinearLayoutManager(this);
        rvCollaboration.setLayoutManager(manager);
        collaborationArrayList = new ArrayList<>();
        getCollaborationList();
    }

    public void getCollaborationList() {
        String date = "";
        collaborationListCall = apiServices.getCollaborationList(date);
        collaborationListCall.enqueue(new Callback<CollaborationList>() {
            @Override
            public void onResponse(Call<CollaborationList> call, Response<CollaborationList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCollaborationArrayList() != null) {
                        int size = response.body().getCollaborationArrayList().size();
                        Log.d("RESPONSE", "news Size " + size);
                        /*for (int i = 0; i < size; i++) {
                            collaborationArrayList.add(response.body().getCollaborationArrayList().get(i));
                        }*/
                           collaborationArrayList.addAll(response.body().getCollaborationArrayList());

                        if (collaborationArrayList.size() > 0) {
                            collaborationAdapter = new CollaborationAdapter(getApplicationContext(), collaborationArrayList);
                            rvCollaboration.setAdapter(collaborationAdapter);

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
            public void onFailure(Call<CollaborationList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onfailure:" + t.getMessage());
            }
        });
            }
}
