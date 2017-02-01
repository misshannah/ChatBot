package com.chatbot.mobile;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;


/**
 * Created by hakinyi on 7/29/2016.
 */

public interface FBInterface {
   @Headers("Content-Type: application/json")
    @PUT("addFBRecord")
        Call<FBRegistrationPojo> loadUser(@QueryMap Map<String, String> registeruser);
//Results:PUT http://myweb.symvasi.com:3000/db/
// addFBRecord?IMEnumber=351706071777327&facebookname=Hannah%20Olukoye&facebookemail
// =akinyiwates2@yahoo.com&deviceid=SM-G530H%20samsung&facebookid=10208673947499894
}