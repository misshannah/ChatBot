package com.chatbot.mobile;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


/**
 * Created by hakinyi on 7/29/2016.
 */

public interface RInterface {
   @Headers("Content-Type: application/json")

    //http://localhost:3000/db/addRecord?fname=hannah&mobile=32424&email=r@gmail.com&pass=rew
   // @POST("addRecord?fname={fullname}&mo={phoneno}&email={email}&pass={password}")
    @POST("addRecord")
        Call<RegistrationPojo> getUser(@QueryMap Map<String, String> registeruser);
  /*  Call<RegistrationPojo> getUser(
            @Path("fname") String fullname,
            @Path("mo") String phoneno,
            @Path("email") String emailaddress,
            @Path("pass") String password);*/
}