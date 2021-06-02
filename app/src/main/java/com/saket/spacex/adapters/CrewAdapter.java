package com.saket.spacex.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.saket.spacex.R;
import com.saket.spacex.model.Crew;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CrewAdapter extends RecyclerView.Adapter< CrewAdapter.CrewViewHolder> {

    private List<Crew> crewList;
    private Activity activity;

    public CrewAdapter() {
    }

    public CrewAdapter(Activity activity,List<Crew> crew) {
        this.activity=activity;
        this.crewList=crew;
    }

    @NonNull
    @NotNull
    @Override
    public CrewViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view= inflater.inflate(R.layout.crew_item, parent, false);
        return new CrewAdapter.CrewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CrewViewHolder holder, int position) {

        Crew crew = crewList.get(position);
        holder.name.setText("Name: "+crew.getName());
        holder.agency.setText("Agency: "+crew.getAgency());
        holder.id.setText("ID: "+crew.getId());
        holder.status.setText("Status: "+crew.getStatus());
        holder.wiki.setText(crew.getWiki());
        Glide.with(activity).load(crew.getImage()).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.avatar);


    }

    @Override
    public int getItemCount() {
        return crewList.size();
    }

    public class CrewViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView id;
        TextView agency;
        TextView status;
        TextView wiki;
        ImageView avatar;
        public CrewViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            this.name=itemView.findViewById(R.id.txt_FullName);
            this.id=itemView.findViewById(R.id.txt_Id);
            this.agency=itemView.findViewById(R.id.txt_Agency);
            this.status=itemView.findViewById(R.id.txt_Status);
            this.wiki=itemView.findViewById(R.id.txt_Wiki);
            this.avatar=itemView.findViewById(R.id.avatarImage);
        }
    }
}
