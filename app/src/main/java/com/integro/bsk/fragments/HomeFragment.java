package com.integro.bsk.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.integro.bsk.R;
import com.integro.bsk.Utilities.AutoScrollViewPager;
import com.integro.bsk.activities.AboutUsActivity;
import com.integro.bsk.activities.ActivitiesActivity;
import com.integro.bsk.activities.CollaborationActivity;
import com.integro.bsk.activities.MainActivity;
import com.integro.bsk.activities.NewsLetterActivity;
import com.integro.bsk.activities.VolunteerActivity;
import com.integro.bsk.activities.WebActivity;
import com.integro.bsk.activities.WhatWeDoActivity;
import com.integro.bsk.adapters.ImageViewPagerAdapter;
import com.integro.bsk.api.ApiClients;
import com.integro.bsk.api.ApiServices;
import com.integro.bsk.model.Activities;
import com.integro.bsk.model.News;
import com.integro.bsk.model.NewsImages;
import com.integro.bsk.model.NewsList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class HomeFragment extends Fragment implements View.OnClickListener {
    TextView tvAboutUs, tvActivities, tvVolunteer, tvNewsLetter, tvCalender, tvNewsEvents, tvPolicies, tvCollaborations, tvWwd, tvSupportUs, tvWebSite, tvdonate;
     ImageView ivVolImage;

    private ApiServices apiServices;
    private AutoScrollViewPager vpImages;
    private ArrayList<News> newsArrayList;
    Call<NewsList> newsListCall;
    private ImageViewPagerAdapter imageViewPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        apiServices = ApiClients.getClients().create(ApiServices.class);
        newsArrayList = new ArrayList<>();
        vpImages = view.findViewById(R.id.vp_Images);


        tvPolicies = view.findViewById(R.id.tv_policies);
        tvNewsLetter = view.findViewById(R.id.tv_newsletter);
        tvCalender = view.findViewById(R.id.tv_Calender);
        tvAboutUs = view.findViewById(R.id.tv_AboutUs);
        tvActivities = view.findViewById(R.id.tv_Activities);
        tvVolunteer = view.findViewById(R.id.tv_Volunteer);
        tvCollaborations = view.findViewById(R.id.tv_Collaborations);
        tvWwd = view.findViewById(R.id.tv_wwd);
        tvSupportUs = view.findViewById(R.id.tv_Support_us);
        tvWebSite = view.findViewById(R.id.tv_Website);
        tvdonate = view.findViewById(R.id.tv_Donate);
        tvNewsEvents = view.findViewById(R.id.tv_News_Events);
        ivVolImage=view.findViewById(R.id.iv_VolImage);

        tvPolicies.setOnClickListener(this);
        tvNewsLetter.setOnClickListener(this);
        tvCalender.setOnClickListener(this);
        tvAboutUs.setOnClickListener(this);
        tvActivities.setOnClickListener(this);
        tvVolunteer.setOnClickListener(this);
        tvCollaborations.setOnClickListener(this);
        tvWwd.setOnClickListener(this);
        tvSupportUs.setOnClickListener(this);
        tvdonate.setOnClickListener(this);
        tvWebSite.setOnClickListener(this);
        tvNewsEvents.setOnClickListener(this);
        ivVolImage.setOnClickListener(this);
        getNewsList();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_Calender:
                Intent intentCal = new Intent(getContext(), WebActivity.class);
                String url2 = "http://bosconews.org/calender.html";
                intentCal.putExtra("TAG", url2);
                startActivity(intentCal);
                break;
            case R.id.tv_AboutUs:
                Intent intentAboutUs = new Intent(getContext(), AboutUsActivity.class);
                startActivity(intentAboutUs);
                break;
            case R.id.tv_News_Events:
                break;
            case R.id.tv_wwd:
                Intent intentWhatWeDo = new Intent(getContext(), WhatWeDoActivity.class);
                startActivity(intentWhatWeDo);
                break;
            case R.id.tv_Support_us:
                Toast.makeText(getContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_Donate:
                Toast.makeText(getContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_VolImage:
                Intent intentVolImage=new Intent(getContext(),VolunteerActivity.class);
                startActivity(intentVolImage);
                break;
            case R.id.tv_Website:
                 Intent intentWeb=new Intent(Intent.ACTION_VIEW);
                 Uri webData=Uri.parse("http://boscosevakendra.org/");
                 intentWeb.setData(webData);
                 startActivity(intentWeb);
                 break;
            case R.id.tv_policies:
                getPolicies();
                break;
            case R.id.tv_Activities:
                Intent intentActivities = new Intent(getContext(), ActivitiesActivity.class);
                startActivity(intentActivities);
                break;

            case R.id.tv_Volunteer:
                Intent intentVolunteer = new Intent(getContext(), VolunteerActivity.class);
                startActivity(intentVolunteer);
                break;

            case R.id.tv_Collaborations:
                Intent intentCollaboration = new Intent(getContext(), CollaborationActivity.class);
                startActivity(intentCollaboration);
                break;

            case R.id.tv_newsletter:
                Intent i = new Intent(getContext(), NewsLetterActivity.class);
                startActivity(i);
        }

    }

    private void getNewsList() {
        String date = "2018-11-26 02:54:04";
        newsListCall = apiServices.getNewsList(date);
        newsListCall.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                //checking response success or not
                if (response.isSuccessful()) {
                    //success api response

                    //response contains data or not
                    if (response.body().getSuccess() == 1) {
                        //data available

                        //getting the size of the arraylist
                        int size = response.body().getNewsArrayList().size();

                        //checking size is 0 or not
                        if (size > 0) {
                            newsArrayList.addAll(response.body().getNewsArrayList());
                            imageViewPagerAdapter = new ImageViewPagerAdapter(getContext(), newsArrayList);
                            vpImages.setAdapter(imageViewPagerAdapter);
                            vpImages.startAutoScroll(3000);
                            vpImages.setCycle(true);
                        } else {
                            // showing message if size is 0
                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        //data not available
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }


                } else {
                    //failure api response
                    Toast.makeText(getContext(), "Something went wrong, try again", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onfailure:" + t.getMessage());

            }
        });
    }

    public void getPolicies() {
        TextView tvChildPolicy, tvChildProtectionPolicy;
        final AlertDialog.Builder dialogBulder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.dilouge_policies, null);
        dialogBulder.setView(view);
        tvChildPolicy = view.findViewById(R.id.tv_ChildPolicy);
        tvChildProtectionPolicy = view.findViewById(R.id.tv_ChildProtectionPolicy);

        tvChildPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCp = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("http://bosconews.org/pdf/child_policy.pdf");
                intentCp.setData(data);
                startActivity(intentCp);
            }
        });

        tvChildProtectionPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCpp = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("http://bosconews.org/pdf/Don_Bosco-Child-Protection-Policy.pdf");
                intentCpp.setData(data);
                startActivity(intentCpp);

               /* Intent intentCpp = new Intent(getContext(), WebActivity.class);
                String url1 = "http://bosconews.org/pdf/Don_Bosco-Child-Protection-Policy.pdf";
                intentCpp.putExtra("TAG", url1);
                startActivity(intentCpp);*/
            }
        });
        dialogBulder.show();
    }

}
