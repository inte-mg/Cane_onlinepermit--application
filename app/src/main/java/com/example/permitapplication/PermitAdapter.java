package com.example.permitapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.permitapplication.model.Permit;

import java.util.ArrayList;

public class PermitAdapter extends RecyclerView.Adapter<PermitAdapter.ViewHolder> {

    private final ArrayList<Permit> permits;
    Context context;
    static OnPermitClicked onPermitClicked;


    public PermitAdapter(ArrayList<Permit> permits, Context context, OnPermitClicked onMessageClicked) {
        this.permits = permits;
        this.context = context;
        PermitAdapter.onPermitClicked = onMessageClicked;
    }

    public interface OnPermitClicked {
        void onPermitClicked(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.applied_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView1.setText(permits.get(position).getFarmerName());
        holder.textView2.setText(permits.get(position).getFarmName());
        holder.textView3.setText(permits.get(position).getDate());

        holder.itemView.setOnClickListener(view -> {
            onPermitClicked.onPermitClicked(position);
        });

    }

    @Override
    public int getItemCount() {
        return permits.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1, textView2, textView3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.TV_farmerName);
            textView2 = itemView.findViewById(R.id.TV_farmname);
            textView3 = itemView.findViewById(R.id.TV_date);
        }
    }

}