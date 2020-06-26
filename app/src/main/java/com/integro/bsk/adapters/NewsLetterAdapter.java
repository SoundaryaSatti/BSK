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
import com.integro.bsk.model.NewsLetter;

import java.util.ArrayList;

public class NewsLetterAdapter extends RecyclerView.Adapter<NewsLetterAdapter.MyViewHolder> {
    Context context;
    ArrayList<NewsLetter> newsLetterArrayList;

    public NewsLetterAdapter(Context context, ArrayList<NewsLetter> newsLetterArrayList) {
        this.context = context;
        this.newsLetterArrayList = newsLetterArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_newsletter, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final NewsLetter newsLetter = newsLetterArrayList.get(position);

        holder.tvDate.setText(newsLetter.getDate());
        holder.tvTitle.setText(newsLetter.getTitle());

        Glide.with(context)
                .load(newsLetter.getImage())
                .into(holder.ivImage);

        holder.tvDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse(newsLetter.getPdf()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsLetterArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView ivImage;
        TextView tvTitle, tvDownload, tvDate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage=itemView.findViewById(R.id.imageViewnl);
            tvTitle=itemView.findViewById(R.id.tvnltitle);
            tvDownload=itemView.findViewById(R.id.tvnlpdf);
            tvDate=itemView.findViewById(R.id.tvnldate);
        }

    }
}


