package com.integro.bsk.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.integro.bsk.R;
import com.integro.bsk.model.AboutUs;
import com.integro.bsk.model.Initiatives;
import com.integro.bsk.model.NewsLetter;

import java.util.ArrayList;

public class AboutUsAdapter extends RecyclerView.Adapter<AboutUsAdapter.MyViewHolder> {
    Context context;
    ArrayList<AboutUs> aboutUsArrayList;

    public AboutUsAdapter(Context context, ArrayList<AboutUs> aboutUsArrayList) {
        this.context = context;
        this.aboutUsArrayList=aboutUsArrayList;
    }


    @NonNull
    @Override
    public AboutUsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_about_us, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AboutUsAdapter.MyViewHolder holder, int position) {
        final AboutUs aboutUs = aboutUsArrayList.get(position);

        holder.tvTitle.setText(aboutUs.getTitle());
        holder.tvContent.setText(aboutUs.getContent());
    }
    @Override
    public int getItemCount()
    {
        return aboutUsArrayList.size();
    }


 public class MyViewHolder extends RecyclerView.ViewHolder {
     TextView tvTitle, tvContent;

     public MyViewHolder(@NonNull View itemView) {
         super(itemView);
         tvTitle = itemView.findViewById(R.id.tv_titleAu);
         tvContent = itemView.findViewById(R.id.tv_ContentAv);
     }
 }
}
