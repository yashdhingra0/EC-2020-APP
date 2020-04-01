package com.example.ec_2020_app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ec_2020_app.R;
import com.example.ec_2020_app.model.EventList;

public class EventsAdapter3 extends RecyclerView.Adapter<EventsAdapter3.EventsHolder3> {

    private EventList eventList;

    public EventsAdapter3(EventList eList) {
        this.eventList = eList;
    }

    @NonNull
    @Override
    public EventsHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v= inflater.inflate(R.layout.event_cardview,parent,false);
        return new EventsHolder3(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsHolder3 holder, int position) {


        holder.event_name.setText(eventList.getTitle());
        holder.event_time.setText(eventList.getEventTime());
        holder.event_venue.setText(eventList.getVenue());
        holder.tv_event_description.setText(eventList.getDescription());

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class EventsHolder3 extends RecyclerView.ViewHolder {

        TextView event_name,event_time,tv_event_description,event_venue;

        public EventsHolder3(@NonNull View itemView) {
            super(itemView);

            event_name = itemView.findViewById(R.id.event_name);
            event_time = itemView.findViewById(R.id.event_time);
            tv_event_description = itemView.findViewById(R.id.tv_event_description);
            event_venue = itemView.findViewById(R.id.event_venue);

        }
    }
}
