package com.chatbot.mobile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * Created by hakinyi on 7/29/2016.
 */

public interface VerInterface {
    @Headers("Content-Type: application/json")
    @GET("status/{phoneno}")

    Call<VerificationPojo> authenticateUser(@Path("phoneno") String phoneno);
    @POST("verify/{phoneno}")
    Call<VerificationPojo> verifyuser(@Path("phoneno") int phoneno);

    //Call<AuthenticationPojo> getUser(@Body Map<String, String> authuser);

}