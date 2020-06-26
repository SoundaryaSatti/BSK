package com.integro.bsk.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.integro.bsk.activities.VolunteerDetails;
import com.integro.bsk.model.Activities;
import com.integro.bsk.model.Volunteer;

import java.util.ArrayList;

public class VolunteerAdapter extends RecyclerView.Adapter<VolunteerAdapter.MyViewHolder> {
    Context context;
    ArrayList<Volunteer>volunteerArrayList;

    public VolunteerAdapter(Context context, ArrayList<Volunteer> volunteerArrayList) {
        this.context = context;
        this.volunteerArrayList = volunteerArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_volunteer,parent,false);
        return new MyViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Volunteer volunteer=volunteerArrayList.get(position);

        holder.tvTitle.setText(volunteer.getTitle());
        holder.tvDescription.setText(volunteer.getDescription());
        Glide.with(context)
                .load(volunteer.getImage())
                .into(holder.ivImageVolunteer);

        holder.tvReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, VolunteerDetails.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("VOLUNTEER",volunteer);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return volunteerArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewVolunteer;
        ImageView ivImageVolunteer;
        TextView tvTitle,tvDescription,tvReadMore;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewVolunteer=itemView.findViewById(R.id.cr_Volunteer);
            ivImageVolunteer=itemView.findViewById(R.id.iv_ImageVolunteer);
            tvTitle=itemView.findViewById(R.id.tv_titleV);
            tvDescription=itemView.findViewById(R.id.tv_descriptionV);
            tvReadMore=itemView.findViewById(R.id.tv_ReadMoreV);

        }
    }
}





