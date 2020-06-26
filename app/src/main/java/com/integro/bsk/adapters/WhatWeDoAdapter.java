package com.integro.bsk.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.integro.bsk.R;
import com.integro.bsk.activities.NewsDetailsActivity;
import com.integro.bsk.activities.WebActivity;
import com.integro.bsk.activities.WhatWeDoActivity;
import com.integro.bsk.model.News;
import com.integro.bsk.model.WhatWeDo;

import java.util.ArrayList;

public class WhatWeDoAdapter extends RecyclerView.Adapter<WhatWeDoAdapter.MyViewHolder> {

    Context context;
    ArrayList<WhatWeDo> whatWeDoArrayList;

    public WhatWeDoAdapter(Context context, ArrayList<WhatWeDo> whatWeDoArrayList) {
        this.context = context;
        this.whatWeDoArrayList = whatWeDoArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_what_we_do, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final WhatWeDo whatWeDo=whatWeDoArrayList.get(position);

        holder.ivWhatWeDo.setImageResource(whatWeDo.getImageId());

        holder.tvWwdTitle.setText(whatWeDo.getTitle());
        holder.tvWwdContent.setText(whatWeDo.getDesc());

        holder.crWhatWeDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, WebActivity.class);
                String url = whatWeDo.getLink();
                intent.putExtra("TAG", url);
                context.startActivity(intent);
           }
        });

    }

    @Override
    public int getItemCount() {
        return whatWeDoArrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvWwdTitle, tvWwdContent;
        CardView crWhatWeDo;
        ImageView ivWhatWeDo;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivWhatWeDo=itemView.findViewById(R.id.iv_Wwd);
            tvWwdTitle = itemView.findViewById(R.id.tv_WwdTitle);
            tvWwdContent = itemView.findViewById(R.id.tv_WwdDescription);
            crWhatWeDo=itemView.findViewById(R.id.cr_WWd);
        }
    }
}
