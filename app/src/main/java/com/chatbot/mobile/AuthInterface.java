package com.chatbot.mobile;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;


/**
 * Created by hakinyi on 7/29/2016.
 */

public interface AuthInterface {
    @Headers("Content-Type: application/json")
    @GET("loginUsers")
    Call<AuthenticationPojo> authenticateUser(@QueryMap Map<String, String> authenticateuser);
    //Call<AuthenticationPojo> authenticateUser(@Path("phoneno") String phoneno, @Path("password") String password);

}