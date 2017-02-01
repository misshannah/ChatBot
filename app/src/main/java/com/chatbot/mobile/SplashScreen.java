package com.chatbot.mobile;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.amplitude.api.Amplitude;
import com.facebook.appevents.AppEventsLogger;

import org.json.JSONObject;

import io.branch.referral.Branch;
import io.branch.referral.BranchError;

public class SplashScreen extends Activity {

    private static int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



       // Branch branch = Branch.getInstance();
        Amplitude.getInstance().initialize(this, "04e2231323443c2b3e04ae873187b4e5").enableForegroundTracking(getApplication());

        setContentView(R.layout.activity_splash_screen);
/*
        branch.initSession(new Branch.BranchReferralInitListener(){
            @Override
            public void onInitFinished(JSONObject referringParams, BranchError error) {
                if (error == null) {
                    // params are the deep linked params associated with the link that the user clicked -> was re-directed to this app
                    // params will be empty if no data found
                    // ... insert custom logic here ...
                } else {
                    Log.i("MyApp", error.getMessage());
                }
            }
        }, this.getIntent().getData(), SplashScreen.this);

*/
        Amplitude.getInstance().logEvent("amplitudeevent");
      //  TagManager tagManager = TagManager.getInstance(SplashScreen.this);
        //PendingResult<ContainerHolder> pending = tagManager.loadContainerPreferNonDefault("gtm", R.raw.gtm);


        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                //new openMessenger();
                try {
                    Intent i = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(i);
                }
                catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getApplication(),"Please Install Facebook Messenger and App", Toast.LENGTH_LONG).show();
                }


                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(this);
    }


}
