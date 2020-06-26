package com.integro.bsk.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.integro.bsk.R;
import com.integro.bsk.adapters.AboutUsAdapter;
import com.integro.bsk.adapters.NewsLetterAdapter;
import com.integro.bsk.api.ApiClients;
import com.integro.bsk.api.ApiServices;
import com.integro.bsk.model.AboutUs;
import com.integro.bsk.model.AboutUsList;
import com.integro.bsk.model.NewsLetter;
import com.integro.bsk.model.NewsLetterList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class AboutUsActivity extends AppCompatActivity {

    ApiServices apiServices;
    RecyclerView rvAboutUs;
    LinearLayoutManager manager;
    AboutUsAdapter aboutUsAdapter;
    ArrayList<AboutUs> aboutUsArrayList;
    Call<AboutUsList> aboutUsListCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
       // getSupportActionBar().hide();
        /*if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);
*/
        apiServices= ApiClients.getClients().create(ApiServices.class);
        rvAboutUs=findViewById(R.id.rv_AboutUs);
        manager=new LinearLayoutManager(this);
        rvAboutUs.setLayoutManager(manager);
        aboutUsArrayList=new ArrayList<>();
        getAboutUsList();

    }
    public void getAboutUsList() {
        String date = "2020-04-06 07:28:58";
        aboutUsListCall = apiServices.getAbouUsList("date");
        aboutUsListCall.enqueue(new Callback<AboutUsList>() {
            @Override
            public void onResponse(Call<AboutUsList> call, Response<AboutUsList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getAboutUsArrayList() != null) {
                        int size = response.body().getAboutUsArrayList().size();
                        Log.d("RESPONSE", "news Size " + size);

                        aboutUsArrayList.addAll(response.body().getAboutUsArrayList());

                        if (aboutUsArrayList.size() > 0) {
                            aboutUsAdapter = new AboutUsAdapter(getApplicationContext(), aboutUsArrayList);
                            rvAboutUs.setAdapter(aboutUsAdapter);
                            rvAboutUs.setHasFixedSize(true);
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
            public void onFailure(Call<AboutUsList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onfailure:" + t.getMessage());

            }

        });

    }

}
