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
import com.integro.bsk.activities.CollaborationDetailsActivity;
import com.integro.bsk.activities.VolunteerDetails;
import com.integro.bsk.model.Collaboration;


import java.util.ArrayList;

public class CollaborationAdapter extends RecyclerView.Adapter<CollaborationAdapter.MyViewHolder> {

    Context context;
    ArrayList<Collaboration>collaborationArrayList;

    public CollaborationAdapter(Context context, ArrayList<Collaboration> collaborationArrayList) {
        this.context = context;
        this.collaborationArrayList = collaborationArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_collaborations,parent,false);
        return new CollaborationAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Collaboration collaboration=collaborationArrayList.get(position);


        Glide.with(context).load(collaboration.getImage()).into(holder.ivImageCollaboration);
        holder.tvTitle.setText(collaboration.getTitle());
        holder.tvDescription.setText(collaboration.getDescription());


        holder.tvReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, CollaborationDetailsActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("COLLABORATION",collaboration);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return collaborationArrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewCollaboration;
        ImageView ivImageCollaboration;
        TextView tvTitle, tvDescription,tvReadMore;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewCollaboration = itemView.findViewById(R.id.cr_Collaborations);
            ivImageCollaboration = itemView.findViewById(R.id.iv_ImageCollaborations);
            tvTitle = itemView.findViewById(R.id.tv_titleC);
            tvDescription = itemView.findViewById(R.id.tv_descriptionC);
            tvReadMore=itemView.findViewById(R.id.tv_ReadMoreC);

        }
    }
}
