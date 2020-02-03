package com.mario.myapplication.retrofit.services;

import com.mario.myapplication.responses.LoginResponse;
import com.mario.myapplication.responses.Register;
import com.mario.myapplication.responses.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LoginService {

    @POST("auth")
    Call<LoginResponse> doLogin(@Header("Authorization") String authorization);


    @POST("users")
    Call<SignUpResponse> doSignUp(@Body Register register);


}

