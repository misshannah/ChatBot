package com.chatbot.mobile;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import org.json.JSONObject;

import io.branch.referral.Branch;
import io.branch.referral.BranchError;

/**
 * Created by hakinyi on 9/19/2016.
 */

public class NewSplash extends Activity {


        @Override
        public void onStart () {
            super.onStart();
            Branch branch = Branch.getInstance();

            branch.initSession(new Branch.BranchReferralInitListener() {
                @Override
                public void onInitFinished(JSONObject referringParams, BranchError error) {
                    if (error == null) {

                    } else {
                        Log.i("MyApp", error.getMessage());
                    }
                }
            }, this.getIntent().getData(), this);
        }

        @Override
        public void onNewIntent (Intent intent){
            this.setIntent(intent);
        }

}
