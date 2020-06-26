package com.integro.bsk.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.integro.bsk.R;
import com.integro.bsk.activities.EventsDetailsActivity;
import com.integro.bsk.activities.NewsDetailsActivity;
import com.integro.bsk.model.Activities;

import java.util.ArrayList;

public class ActivitiesAdapter extends RecyclerView.Adapter<ActivitiesAdapter.MyViewHolder> {
    Context context;
    ArrayList<Activities>activitiesArrayList;

    public ActivitiesAdapter(Context context, ArrayList<Activities> activitiesArrayList) {
        this.context = context;
        this.activitiesArrayList = activitiesArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_activities,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Activities activities=activitiesArrayList.get(position);

        holder.tvTitleA.setText(activities.getTitle());
        holder.tvDescriptionA.setText(activities.getDescription());
        Glide.with(context)
                .load(activities.getImage())
                .into(holder.ivActivitiesA);
        holder.tvReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,EventsDetailsActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("ACTIVITY",activities);
                context.startActivity(i);
            }
        });
       /*holder.cvEventDetailsA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,EventsDetailsActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("ACTIVITY",activities);
                context.startActivity(i);

            }
        });*/
    }

    @Override
    public int getItemCount() {
        return activitiesArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
         CardView cvEventDetailsA;
        ImageView ivActivitiesA;
        TextView tvTitleA,tvDescriptionA,tvReadMore;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cvEventDetailsA=itemView.findViewById(R.id.cr_EventD);
            ivActivitiesA=itemView.findViewById(R.id.iv_Activity);
            tvTitleA=itemView.findViewById(R.id.tv_titleA);
            tvDescriptionA=itemView.findViewById(R.id.tv_descriptionA);
            tvReadMore=itemView.findViewById(R.id.tv_ReadMoreA);
        }

    }
}

