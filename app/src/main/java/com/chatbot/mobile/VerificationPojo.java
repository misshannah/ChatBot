package com.chatbot.mobile;

/**
 * Created by hakinyi on 8/3/2016.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class VerificationPojo {


    @SerializedName("phoneno")
    @Expose
    public String phoneno;

    @SerializedName("emailaddress")
    @Expose
    public String emailaddress;
    @SerializedName("password")
    @Expose
    public String password;

    /**
     * @return The phoneno
     */
    public String getPhoneno() {
        return phoneno;
    }

    /**
     * @param phoneno The phoneno
     */
    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }


    /**
     * @param password The password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
