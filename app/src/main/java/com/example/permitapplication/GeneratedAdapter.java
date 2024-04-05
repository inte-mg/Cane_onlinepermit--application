package com.example.permitapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.permitapplication.model.GrantedPermit;
import com.example.permitapplication.model.Permit;

import java.util.ArrayList;

public class GeneratedAdapter extends RecyclerView.Adapter<GeneratedAdapter.ViewHolder> {

    private final ArrayList<GrantedPermit> granted;
    Context context;
    static OnGrantClicked onGrantClicked;

    public GeneratedAdapter(ArrayList<GrantedPermit> granted, Context context, OnGrantClicked onMessageClicked) {
        this.granted = granted;
        this.context = context;
        GeneratedAdapter.onGrantClicked = onMessageClicked;
    }

    public interface OnGrantClicked {
        void onPermitClicked(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.granted_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView1.setText(granted.get(position).getFarmerName());
        holder.textView2.setText(granted.get(position).getFarmName());
        holder.textView3.setText(granted.get(position).getFarmArea());

        holder.itemView.setOnClickListener(view -> {
            onGrantClicked.onPermitClicked(position);
        });

    }

    @Override
    public int getItemCount() {
        return granted.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1, textView2, textView3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.TV_farmerName);
            textView2 = itemView.findViewById(R.id.TV_farmname);
            textView3 = itemView.findViewById(R.id.TV_farmArea);
        }
    }

}