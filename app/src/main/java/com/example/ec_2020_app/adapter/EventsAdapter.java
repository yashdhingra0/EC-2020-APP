package com.example.ec_2020_app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ec_2020_app.R;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsHolder> {


    @NonNull
    @Override
    public EventsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_of_events,parent,false);

        return new EventsHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class EventsHolder extends RecyclerView.ViewHolder{
        ImageView im;

        public EventsHolder(@NonNull View itemView) {
            super(itemView);

            im = itemView.findViewById(R.id.itemEventIm);

        }
    }
}
