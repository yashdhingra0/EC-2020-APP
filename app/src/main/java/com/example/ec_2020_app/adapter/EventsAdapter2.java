package com.example.ec_2020_app.adapter;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ec_2020_app.EventActivity;
import com.example.ec_2020_app.R;
import com.example.ec_2020_app.utils.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.StringTokenizer;

public class EventsAdapter2 extends RecyclerView.Adapter<EventsAdapter2.EventViewHolder> {

    private Context context;

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

        holder.clubNames.setText(Utils.clubName[position]);
        StringTokenizer str=new StringTokenizer(Utils.clubName[position]);
        String clubname=str.nextToken().trim().toLowerCase();
        String backPath="club_card_view_background/"+clubname+".jpg";
        AssetManager assetManager = context.getAssets();
        try (
                InputStream inputStream = assetManager.open(backPath);
        ) {
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            holder.back.setImageBitmap(bitmap);
        } catch (IOException ex) {
            //
        }


        holder.itemView.setOnClickListener(v -> {
            Intent eventsIntent = new Intent(context, EventActivity.class);
            eventsIntent.putExtra("position",position);
            context.startActivity(eventsIntent);
        });

    }

    @Override
    public int getItemCount() {
        return Utils.clubName.length;
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {
        TextView clubNames;
        ImageView back;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            clubNames = itemView.findViewById(R.id.clubNames);
            back=itemView.findViewById(R.id.backgroundImage);
        }
    }
}
