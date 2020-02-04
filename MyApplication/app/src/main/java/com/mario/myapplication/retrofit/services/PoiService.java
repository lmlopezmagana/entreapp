package com.mario.myapplication.retrofit.services;

import com.mario.myapplication.responses.PoiResponse;
import com.mario.myapplication.responses.ResponseContainer;
import com.mario.myapplication.responses.UserFavResponse;
import com.mario.myapplication.responses.UserResponse;
import com.mario.myapplication.responses.VisitPoiResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface PoiService {

    String BASE_URL = "pois";

    @GET(BASE_URL)
    Call<ResponseContainer<PoiResponse>> listPois();

    @GET(BASE_URL)
    Call<ResponseContainer<PoiResponse>> listPois(@Query("near") String latlng, @Query("max_distance") int maxDistance);

    @GET(BASE_URL + "/visited")
    Call<ResponseContainer<PoiResponse>> listVisitedPois();

    @GET(BASE_URL + "/favs")
    Call<ResponseContainer<PoiResponse>> listFavPois();

    @GET(BASE_URL + "/{id}")
    Call<PoiResponse> getPoi(@Path("id") String id);

    @GET(BASE_URL + "/{id}/{idLang}")
    Call<PoiResponse> getPoiLang(@Path("id") String id, @Path("idLang") String idLang);

    @PUT(BASE_URL + "visited/{id}")
    Call<PoiResponse> visitPoi(@Path("id") String id);

    @PUT(BASE_URL + "/visit/{uniqueName}")
    Call<VisitPoiResponse> qrScan(@Path("uniqueName") String uniqueName);

    @PUT(BASE_URL + "/fav/add/{id}")
    Call<UserFavResponse> addPoiFav(@Path("id") String id);

    @PUT(BASE_URL + "/fav/del/{id}")
    Call<UserFavResponse> delPoiFav(@Path("id") String id);
}
