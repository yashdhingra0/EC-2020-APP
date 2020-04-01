package com.example.ec_2020_app;

import android.os.Bundle;

import com.example.ec_2020_app.adapter.EventsAdapter3;
import com.example.ec_2020_app.model.EventData;
import com.example.ec_2020_app.model.EventList;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventActivity extends AppCompatActivity {


    EventData eventData;
    SpinKitView progressBar;
    RecyclerView recyclerView;
    int position;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        progressBar = findViewById(R.id.spin_kit);
        progressBar.setVisibility(View.VISIBLE);

        position = getIntent().getIntExtra("position",0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarEvents);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        recyclerView = findViewById(R.id.eventView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        EventDataService webServiceData = EventDataService.retrofitt.create(EventDataService.class);

        Call<EventData> mEventData = webServiceData.getEventsData();

        mEventData.enqueue(new Callback<EventData>() {
            @Override
            public void onResponse(Call<EventData> call, Response<EventData> response) {
                if(response.isSuccessful()){
                    eventData = response.body();

                    progressBar.setVisibility(View.GONE);
                    EventList eventList =eventData.getList().get(position).get(0);
                    // Log.i("Data :: ",eventData.getList().get(0).get(1).getFullName());
                    recyclerView.setAdapter(new EventsAdapter3(eventList));

                }
//eventData.getList().get(position).get(2).getName()
            }

            @Override
            public void onFailure(Call<EventData> call, Throwable t) {
                Log.i("TAG","Error !!");
                Toast.makeText(EventActivity.this, "Network Error !!", Toast.LENGTH_SHORT).show();

            }
        });




    }

}
