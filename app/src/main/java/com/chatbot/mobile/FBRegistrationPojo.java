package com.chatbot.mobile;

/**
 * Created by hakinyi on 8/3/2016.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class FBRegistrationPojo {


    @SerializedName("facebookid")
    @Expose
    public String fbid;
    @SerializedName("facebookname")
    @Expose
    private String fbname;
    @SerializedName("facebookemail")
    @Expose
    public String fbemail;

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
    public String getFbid() {
        return fbid;
    }

    /**
     *
     * @param fbid
     * The phoneno
     */
    public void setFbid(String fbid) {
        this.fbid = fbid;
    }

    /**
     *
     * @return
     * The fullname
     */
    public String getFbname() {
        return fbname;
    }

    /**
     *
     * @param fbname
     * The fullname
     */
    public void setFbname(String fbname) {
        this.fbname = fbname;
    }

    /**
     *
     * @return
     * The emailaddress
     */
    public String getFbemail() {
        return fbemail;
    }

    /**
     *
     * @param fbemail
     * The emailaddress
     */
    public void setFbemail(String fbemail) {
        this.fbemail = fbemail;
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


}
