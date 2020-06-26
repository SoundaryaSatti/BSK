package com.integro.bsk.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.integro.bsk.R;
import com.integro.bsk.adapters.NewsLetterAdapter;
import com.integro.bsk.api.ApiClients;
import com.integro.bsk.api.ApiServices;
import com.integro.bsk.model.NewsLetter;
import com.integro.bsk.model.NewsLetterList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class NewsLetterActivity extends AppCompatActivity {

    ApiServices apiServices;
    RecyclerView rvNewsletter;
    LinearLayoutManager manager;
    NewsLetterAdapter newsLetterAdapter;
    ArrayList<NewsLetter> newsLetterArrayList;
    Call<NewsLetterList> newsLetterlistCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_letter);
        //((AppCompatActivity) getApplicationContext()).getSupportActionBar().hide();
       /* if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);
*/

        apiServices= ApiClients.getClients().create(ApiServices.class);
        rvNewsletter=findViewById(R.id.rv_newsLetter);
        manager=new LinearLayoutManager(this);
         //getSupportActionBar().hide();


        rvNewsletter.setLayoutManager(manager);
        newsLetterArrayList=new ArrayList<>();
        getNewsLetterList();

    }
    public void getNewsLetterList(){
        String date="";
        newsLetterlistCall=apiServices.getNewsLetterList("date");
        newsLetterlistCall.enqueue(new Callback<NewsLetterList>() {
            @Override
            public void onResponse(Call<NewsLetterList> call, Response<NewsLetterList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getNewsLetterArrayList() != null) {
                        int size = response.body().getNewsLetterArrayList().size();
                        Log.d("RESPONSE", "news Size " + size);

                        newsLetterArrayList.addAll(response.body().getNewsLetterArrayList());

                        if (newsLetterArrayList.size() > 0) {
                            newsLetterAdapter = new NewsLetterAdapter(getApplicationContext(), newsLetterArrayList);
                            rvNewsletter.setAdapter(newsLetterAdapter);
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
            public void onFailure(Call<NewsLetterList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onfailure:" + t.getMessage());

            }
        });

    }


}
