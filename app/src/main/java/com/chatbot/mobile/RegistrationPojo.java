package com.chatbot.mobile;

/**
 * Created by hakinyi on 8/3/2016.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class RegistrationPojo {


    @SerializedName("phoneno")
    @Expose
    public String phoneno;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("emailaddress")
    @Expose
    public String emailaddress;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("deviceid")
    @Expose
    public String deviceid;
    @SerializedName("IMEnumber")
    @Expose
    public String IMEnumber;

    /**
     *
     * @return
     * The phoneno
     */
    public String getPhoneno() {
        return phoneno;
    }

    /**
     *
     * @param phoneno
     * The phoneno
     */
    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    /**
     *
     * @return
     * The fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     *
     * @param fullname
     * The fullname
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     *
     * @return
     * The emailaddress
     */
    public String getEmailaddress() {
        return emailaddress;
    }

    /**
     *
     * @param emailaddress
     * The emailaddress
     */
    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    /**
     *
     * @return
     * The deviceid
     */
    public String getDeviceid() {
        return deviceid;
    }

    /**
     *
     * @param deviceid
     * The deviceid
     */
    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    /**
     *
     * @return
     * The registerchannel
     */
    public String getIMEnumber() {
        return IMEnumber;
    }

    /**
     *
     * @param IMEnumber
     * The registerchannel
     */
    public void setIMEnumber(String IMEnumber) {
        this.IMEnumber = IMEnumber;
    }
    /**
     *
     * @return
     * The password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     * The password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
