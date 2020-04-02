package com.example.ec_2020_app;

import android.os.Bundle;

import com.example.ec_2020_app.adapter.EventsAdapter3;
import com.example.ec_2020_app.interfacee.EventDataService;
import com.example.ec_2020_app.model.EventData;
import com.github.ybq.android.spinkit.SpinKitView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventActivity extends AppCompatActivity {


    EventData eventData;
    SpinKitView progressBar;
    RecyclerView recyclerView;
    int position;
    //"Jhalak","NIRAMAYAM","SRIJAN CLUB","VIVEKANAND MANCH","NATRAJA ","VIVIDHA","IEEE CLUB","MICROBIRD CLUB","MECHNEXT CLUB","Anaya"

    String[] clubName={"Jhalak","NIRAMAYAM","SRIJAN CLUB","VIVEKANAND MANCH","NATRAJA","VIVIDHA","IEEE CLUB","MICROBIRD CLUB","MECHNEXT CLUB","Ananya"};
    String[] clubID={"201482940469739525","201483219625836550","201483803028357128","201484100471619593","201484430101970954","201485028184555532","201486616257101838","201487915635703825","201488056715313170","201565261562642477"};
    Integer[] clubLogo = {R.drawable.jhalak,R.drawable.nirmayam,R.drawable.srijan,R.drawable.vivekanand,R.drawable.natraj,R.drawable.vividha,R.drawable.ieee,R.drawable.microbird,R.drawable.mechnext,R.drawable.ananya};
    ImageView clubLogoIm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        progressBar = findViewById(R.id.spin_kit);
        progressBar.setVisibility(View.VISIBLE);
        Button button = findViewById(R.id.eventBackButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        position = getIntent().getIntExtra("position",0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarEvents);
        setSupportActionBar(toolbar);
       // getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(clubName[position]);

        clubLogoIm = findViewById(R.id.clubLogo);
        clubLogoIm.setImageResource(clubLogo[position]);


        recyclerView = findViewById(R.id.eventView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        EventDataService webServiceData = EventDataService.retrofitt.create(EventDataService.class);

        Call<EventData> mEventData = webServiceData.getEventsData(clubID[position],"20");

        mEventData.enqueue(new Callback<EventData>() {
            @Override
            public void onResponse(Call<EventData> call, Response<EventData> response) {
                if(response.isSuccessful()){
                    eventData = response.body();

                    progressBar.setVisibility(View.GONE);
                    recyclerView.setAdapter(new EventsAdapter3(eventData));

                }

            }

            @Override
            public void onFailure(Call<EventData> call, Throwable t) {
                Log.i("TAG","Error !!");
                Toast.makeText(EventActivity.this, "Network Error !!", Toast.LENGTH_SHORT).show();

            }
        });




    }

}
