package com.example.ttx.appmessagerme;

import com.example.ttx.appmessagerme.Model.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitMap {
    @GET("api/directions/json?key=AIzaSyAod57EwuHl2N7ffZDq_Thfn78xLFiakVQ")
    Call<Example> getDistance(@Query("units") String units,
                              @Query("origin") String origin,
                              @Query("destination") String destination,
                              @Query("mode") String mode);

}
