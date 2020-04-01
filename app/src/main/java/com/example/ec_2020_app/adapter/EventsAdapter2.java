package com.example.ec_2020_app.adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ec_2020_app.EventActivity;
import com.example.ec_2020_app.R;

public class EventsAdapter2 extends RecyclerView.Adapter<EventsAdapter2.EventViewHolder> {

    Context context;

    public EventsAdapter2(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_of_events2,parent,false);

        return new EventViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {

        holder.itemView.setOnClickListener(v -> {
            Intent eventsIntent = new Intent(context, EventActivity.class);
            eventsIntent.putExtra("position",position);
            context.startActivity(eventsIntent);
        });

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {
        ImageView clubImage;
        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            clubImage = itemView.findViewById(R.id.clubImage);
        }
    }
}
