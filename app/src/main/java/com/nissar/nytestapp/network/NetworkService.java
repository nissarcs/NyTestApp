package com.nissar.nytestapp.network;

import com.nissar.nytestapp.model.Response;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface NetworkService {


    @GET("svc/mostpopular/v2/mostviewed/all-sections/7.json")
    Observable<Response> getBaseURL(@Query("api-key") String apiKey);
}
