package com.example.ec_2020_app.interfacee;


import com.example.ec_2020_app.model.EventData;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EventDataService {

     // elcl.herokuapp.com/api/event?perPage=5&page=0&club=manan

     String BASE="https://elcl.herokuapp.com/api/";

     Retrofit retrofitt = new Retrofit.Builder().baseUrl(BASE)
             .addConverterFactory(GsonConverterFactory.create())
             .build();

     @GET("event")
     Call<EventData> getEventsData(@Query("club") String clubName,@Query("perPage") String perPage);


}
