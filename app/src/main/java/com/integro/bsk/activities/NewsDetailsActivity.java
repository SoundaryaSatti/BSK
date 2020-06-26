package com.integro.bsk.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.integro.bsk.R;
import com.integro.bsk.Utilities.AutoScrollViewPager;
import com.integro.bsk.adapters.NewsImagesAdapterVp;
import com.integro.bsk.api.ApiClients;
import com.integro.bsk.api.ApiServices;
import com.integro.bsk.model.News;
import com.integro.bsk.model.NewsImages;
import com.integro.bsk.model.NewsImagesList;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class NewsDetailsActivity extends AppCompatActivity {

    private static final String TAG = "NewsDetailsActivity";

    String itemId;

    ArrayList<NewsImages> newsImagesArrayList;
    AutoScrollViewPager vpNewsImages;
    NewsImagesAdapterVp newsImagesAdapterVp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
         //getSupportActionBar().hide();
       /* if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);
*/
        newsImagesArrayList = new ArrayList<>();


        final String TAG = "NewsDetailsActivity";
        News news = (News) getIntent().getSerializableExtra("NEWS");

           vpNewsImages=findViewById(R.id.vp_NewsImage);
        //ImageView ivImage=findViewById(R.id.iv_news);
            TextView tvDate = findViewById(R.id.tv_date);
            TextView tvTitle = findViewById(R.id.tv_title);
            TextView tvDiscription = findViewById(R.id.tv_description);
             TextView tvShare=findViewById(R.id.tv_Share);


            tvDate.setText(news.getDate());
            tvTitle.setText(news.getTitle());
            tvDiscription.setText(news.getDescription());

            itemId = news.getId();
            Log.i(TAG, "onCreate: " + itemId);
            tvShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, "http://bosconews.org/newsshare.php?id=" + news.getId());
                    startActivity(intent);

                }
            });
       /* Glide.with(getApplicationContext())
                .load(news.getImage())
                .into(ivImage);*/
            // String newsId = getIntent().getStringExtra("NEWS_ID");

        getNewsImagesList();

        }

    public void getNewsImagesList() {

        Call<NewsImagesList> newsImagesCall;
        newsImagesCall = ApiClients.getClients().create(ApiServices.class).getNewsImagesList(itemId);
        newsImagesCall.enqueue(new Callback<NewsImagesList>() {
            @Override
            public void onResponse(Call<NewsImagesList> call, Response<NewsImagesList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getNewsImagesArrayList() != null) {
                        int size = response.body().getNewsImagesArrayList().size();
                        Log.d("response", "NewsImages" + size);
                        if (size > 0) {
                            newsImagesArrayList.addAll(response.body().getNewsImagesArrayList());
                            newsImagesAdapterVp = new NewsImagesAdapterVp(getApplicationContext(), newsImagesArrayList);
                            vpNewsImages.startAutoScroll(3000);
                            vpNewsImages.setCycle(true);
                            vpNewsImages.setAdapter(newsImagesAdapterVp);
                            Log.i(TAG, "onResponse: " + newsImagesArrayList.size());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsImagesList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onfailure:" + t.getMessage());
            }
        });
    }
}
