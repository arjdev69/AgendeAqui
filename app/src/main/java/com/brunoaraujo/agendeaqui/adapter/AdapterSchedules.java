package com.brunoaraujo.agendeaqui.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brunoaraujo.agendeaqui.R;
import com.brunoaraujo.agendeaqui.model.Appointments;
import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AdapterSchedules extends RecyclerView.Adapter<AdapterSchedules.MyViewHolder> {

    private Context contx;
    private List<Appointments> appointments = new ArrayList<>();

    public AdapterSchedules(Context contx, List<Appointments> appointments) {
        this.contx = contx;
        this.appointments = appointments;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemViewList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_schedules, parent, false);
        return new MyViewHolder(itemViewList);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        Appointments appoints = appointments.get(position);

        Uri uri = Uri.parse(appoints.getProvider().getAvatar().getUrl());
        Log.d("Image ", String.valueOf(uri));
        Glide.with(contx).load( uri ).into( holder.photo );

        //holder.photo.setImageURI();
        holder.title.setText(appoints.getProvider().getName());
        holder.date.setText(appoints.getDate());
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView photo;
        TextView title;
        TextView date;

        public MyViewHolder(View itemView){
            super(itemView);

            photo = itemView.findViewById(R.id.imageProvider);
            title = itemView.findViewById(R.id.textViewTitle);
            date = itemView.findViewById(R.id.textViewDate);
        }
    }

    public void adiciona(List<Appointments> appoints){
        appointments = appoints;
        notifyDataSetChanged();
    }


}
