package com.culmyca.ec_2020_app.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.culmyca.ec_2020_app.R;
import com.culmyca.ec_2020_app.model.EventData;
import com.culmyca.ec_2020_app.model.EventList;
import com.culmyca.ec_2020_app.utils.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class EventsAdapter3 extends RecyclerView.Adapter<EventsAdapter3.EventsHolder3> {

    private EventData eventData;
    Context ctx;
    int pos;

    public EventsAdapter3(Context context,EventData edata,int position) {
        this.eventData = edata;
        ctx = context;
        pos=position;
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


        EventList eventList = eventData.getList().get(position).get(0);

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        String time = eventList.getEventTime();
        Date date = null;
        try {
            date = inputFormat.parse(time);
            time = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            time=eventList.getEventTime();
        }


        holder.event_name.setText(eventList.getTitle());
        holder.event_time.setText(time);
        holder.event_venue.setText(eventList.getVenue());
        holder.tv_event_description.setText(eventList.getDescription());


        holder.visitInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(Utils.instaPages[pos]);
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    ctx.startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    ctx.startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse(Utils.instaPages[pos])));
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return eventData.getTotal();
    }

    public class EventsHolder3 extends RecyclerView.ViewHolder {

        TextView event_name,event_time,tv_event_description,event_venue;
        Button visitInsta;

        public EventsHolder3(@NonNull View itemView) {
            super(itemView);

            event_name = itemView.findViewById(R.id.event_name);
            event_time = itemView.findViewById(R.id.event_time);
            tv_event_description = itemView.findViewById(R.id.tv_event_description);
            event_venue = itemView.findViewById(R.id.event_venue);
            visitInsta = itemView.findViewById(R.id.visitInsta);

        }
    }
}
