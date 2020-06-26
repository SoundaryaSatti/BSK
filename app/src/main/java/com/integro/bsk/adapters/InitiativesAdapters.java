package com.integro.bsk.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.integro.bsk.R;
import com.integro.bsk.model.Initiatives;

import java.util.ArrayList;

public class InitiativesAdapters extends RecyclerView.Adapter<InitiativesAdapters.MyViewHolder> {
    Context context;
    ArrayList<Initiatives>initiativesArrayList;

    public InitiativesAdapters(Context context, ArrayList<Initiatives> initiativesArrayList) {
        this.context = context;
        this.initiativesArrayList = initiativesArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_initiatives, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Initiatives initiatives = initiativesArrayList.get(position);

        holder.tvTitileI.setText(initiatives.getTitle());
        holder.tvDescriptionI.setText(initiatives.getDescription());
    }

    @Override
    public int getItemCount() {
        return initiativesArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitileI, tvDescriptionI;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitileI = itemView.findViewById(R.id.tv_InitiativesTitle);
            tvDescriptionI = itemView.findViewById(R.id.tv_InitiativeDescription);
        }
    }
}