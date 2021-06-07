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
import com.brunoaraujo.agendeaqui.model.Provider;
import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AdapterProviders extends RecyclerView.Adapter<AdapterProviders.MyViewHolder> {
    private Context contx;
    private List<Provider> providers = new ArrayList<>();

    public AdapterProviders(Context contx, List<Provider> providers) {
        this.contx = contx;
        this.providers = providers;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemViewList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_provider, parent, false);
        return new MyViewHolder(itemViewList);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        Provider provider = providers.get(position);

        Uri uri = Uri.parse(provider.getAvatar().getUrl());
        Log.d("Image ", String.valueOf(uri));
        Glide.with(contx).load( uri ).into( holder.photo );

        //holder.photo.setImageURI();
        holder.title.setText(provider.getName());
    }

    @Override
    public int getItemCount() {
        return providers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView photo;
        TextView title;

        public MyViewHolder(View itemView){
            super(itemView);

            photo = itemView.findViewById(R.id.imageProvider);
            title = itemView.findViewById(R.id.textViewTitle);
        }
    }

    public void callData(List<Provider> _providers){
        providers = _providers;
        notifyDataSetChanged();
    }
}
