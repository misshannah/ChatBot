package com.chatbot.mobile;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chatbot.mobile.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.facebook.messenger.MessengerUtils.PACKAGE_NAME;

public class PickOffer extends Activity {
    public static final String messenger_page = "https://www.messenger.com/t/1042062062541401";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_offer);
        Button openchat = (Button) findViewById(R.id.createoffer_button);
        openchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new openMessenger();
                try {


                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("fb://messaging/1042062062541401")));
                }
                catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getApplication(),"Please Install Facebook Messenger", Toast.LENGTH_LONG).show();
                }

            }
        });

        Button pdfview = (Button) findViewById(R.id.viewoffer_button);
        pdfview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent passIntent = new Intent(PickOffer.this, ViewOffer.class);
                startActivity(passIntent);
            }
        });

    }



}

