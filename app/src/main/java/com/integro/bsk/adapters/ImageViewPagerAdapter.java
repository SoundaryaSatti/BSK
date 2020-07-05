package com.integro.bsk.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.integro.bsk.R;
import com.integro.bsk.activities.NewsDetailsActivity;
import com.integro.bsk.model.News;

import java.util.ArrayList;

public class ImageViewPagerAdapter extends PagerAdapter {
    Context context;
    ArrayList<News> newsArrayList;
    LayoutInflater inflater;

    public ImageViewPagerAdapter(Context context, ArrayList<News> newsArrayList) {
        this.context = context;
        this.newsArrayList = newsArrayList;
    }

    @Override
    public int getCount() {
        return newsArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView ivImage;
        TextView tvTitle,tvDescription;
         News news=newsArrayList.get(position);
        View view = LayoutInflater.from(context).inflate(R.layout.card_vp_images, container, false);

        ivImage = view.findViewById(R.id.vp_ImageH);
        tvTitle=view.findViewById(R.id.vp_title);
        /*String url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSppVj8MavDX1f4O-Cx_VKoOsdIrKoQNxPNjkvzh_kxPSVCogu02W1OzMG-XA&s=10";
        Glide.with(context)
                .load(url)
                .apply(new BaseRequestOptions<BaseRequestOptions>() {
                })
                .into(ivImage);*/
        /*Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.rescuedchildren)
                        .error(R.drawable.rescuedchildren))
                .into(ivImage);*/
        tvTitle.setText(newsArrayList.get(position).getTitle());
        Glide.with(context).load(newsArrayList.get(position).getImage()).into(ivImage);

        ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, NewsDetailsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("NEWS", news);
                context.startActivity(intent);
            }
        });
        ((ViewPager)container).addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((LinearLayout) object);

    }
}

