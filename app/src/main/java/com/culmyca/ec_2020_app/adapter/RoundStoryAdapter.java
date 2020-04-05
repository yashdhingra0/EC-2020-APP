package com.culmyca.ec_2020_app.adapter;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.culmyca.ec_2020_app.R;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.StringTokenizer;

//public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsHolder> {
//
//    static int size;
//    static String from[];
//    static boolean readState[];
//
////    EventsAdapter(String from[], boolean readState[]){
////        this.from=new String[from.length];
////        this.from=from;
////        this.size=from.length;
////        this.readState=new boolean[readState.length];
////        this.readState=readState;
////    }
//
//
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View v = inflater.inflate(R.layout.item_of_events,parent,false);
//        RecyclerView.ViewHolder viewholder=new ViewHoler(v);
//        return viewholder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull EventsHolder holder, int position) {
//        holder.itemView.
//    }
//
//    @Override
//    public int getItemCount() {
//        return 10;
//    }
//
//    public class EventsHolder extends RecyclerView.ViewHolder{
//        ImageView im;
//
//        public EventsHolder(@NonNull View itemView) {
//            super(itemView);
//            im = itemView.findViewById(R.id.roundedimgframe);
//
//        }
//    }
//
//
//}

public class RoundStoryAdapter extends RecyclerView.Adapter<RoundStoryAdapter.ViewHolder> {

    private List<String> from;
    private List<Boolean> readState;
    private LayoutInflater mInflater;
    private Context ctx;

    // data is passed into the constructor
    public RoundStoryAdapter(Context context, List<String> data, List<Boolean> readState) {
        this.mInflater = LayoutInflater.from(context);
        this.from = data;
        this.readState=readState;
        ctx=context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_of_events, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String from = this.from.get(position);
        StringTokenizer stringTokenizer=new StringTokenizer(from);
        int count=1;
        String from2=from;
        int n=stringTokenizer.countTokens();
        from="";
        if(n>1)
        while (count<=n){
            String k=stringTokenizer.nextToken();
            from=from+" "+k.charAt(0)+".";
            count++;
        }
        else
            from=from2;
        holder.storyFrom.setText(from);
        AssetManager assetManager = ctx.getAssets();
        try (
                InputStream inputStream = assetManager.open("club_logos/"+from2+".png");
        ) {
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            holder.roundFrame.setImageBitmap(bitmap);
        } catch (IOException ex) {
            //Toast.makeText(ctx,"path - "+" club_logo/"+"from2"+".png",Toast.LENGTH_SHORT).show();
        }

        Boolean state=this.readState.get(position);
        if(state){
            holder.roundFrame.setBorderColor(Color.parseColor("#192A56"));
        }

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return from.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView storyFrom;
        CircularImageView roundFrame;

        ViewHolder(View itemView) {
            super(itemView);
            storyFrom = itemView.findViewById(R.id.story_from);
            roundFrame = itemView.findViewById(R.id.roundedimgframe);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

}
