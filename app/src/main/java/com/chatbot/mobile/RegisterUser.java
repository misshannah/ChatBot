package com.chatbot.mobile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterUser extends Activity {
    private EditText phonenoText, passwordText,emailText, fullnameText,confirmpasswordText;
    private Button registerbutton;

    String url = "http://myweb.symvasi.com:3000/db/"; //Instead of localhost
    //String url = "https://0a1b1300.ngrok.io/db/";

    String verify_url = "https://myweb.symvasi.com/";
String email, fullname, phoneno, password, passwordconfirmation,IMEINumber,deviceid;
    boolean cancel = false;
    View focusView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        // Set up the registration form.
        emailText = (EditText) findViewById(R.id.emailText);
        fullnameText = (EditText) findViewById(R.id.fullname);
        phonenoText = (EditText) findViewById(R.id.phoneno);
        passwordText = (EditText) findViewById(R.id.password);
        confirmpasswordText = (EditText) findViewById(R.id.passwordconfirm);
        registerbutton = (Button) findViewById(R.id.register_button);



        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        IMEINumber = tm.getDeviceId();

        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        deviceid = model + " " + manufacturer;

        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new user_registration().execute();
            }
        });

    }


    class user_registration extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {

            email = emailText.getText().toString();
            fullname = fullnameText.getText().toString();
            phoneno = phonenoText.getText().toString();
            password = passwordText.getText().toString();
            passwordconfirmation = confirmpasswordText.getText().toString();


            // Check for a valid address
            if (TextUtils.isEmpty(email)) {
                emailText.setError("Email is required");
                focusView = emailText;
                cancel = true;
            }
            // Check for a valid city
            if (TextUtils.isEmpty(fullname)) {
                fullnameText.setError("Full Name is required");
                focusView = fullnameText;
                cancel = true;

                //Check for valid province
                if (TextUtils.isEmpty(phoneno)) {
                    phonenoText.setError("Phone Number is required");
                    focusView = phonenoText;
                    cancel = true;

                    //Check for valid postal
                    if (TextUtils.isEmpty(password)) {
                        passwordText.setError("Password is required");
                        focusView = passwordText;
                        cancel = true;
                    }
                        // Check for a valid confirm password, if the user entered one.
                        if (TextUtils.isEmpty(passwordconfirmation)) {
                            confirmpasswordText.setError("Password is required");
                            focusView = confirmpasswordText;
                            cancel = true;
                        } else if (!password.equals(passwordconfirmation)) {
                            confirmpasswordText.setError("Passwords don't match");
                            focusView = confirmpasswordText;
                            cancel = true;
                        }
                    }

            } else {

                AlertDialog alertDialog = new AlertDialog.Builder(
                        RegisterUser.this).create();

                // Setting Dialog Title
                alertDialog.setTitle("Success");
// Setting Icon to Dialog
                alertDialog.setIcon(R.drawable.success);
                // Setting Dialog Message
                alertDialog.setMessage("Almost there. " +
                        "Your registration was successful. Click OK to continue to verification");

                // Setting OK Button
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        sendVerification();

                    }
                });

                // Showing Alert Message
                alertDialog.show();
            }
        }
        public void sendVerification() {
            try {
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

                logging.setLevel(HttpLoggingInterceptor.Level.BODY);

                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

                httpClient.addInterceptor(logging);


                Retrofit radapter = new Retrofit.Builder()
                        .baseUrl(verify_url)
                        .addConverterFactory(GsonConverterFactory.create())

                        .client(httpClient.build())
                        .build();

                VerInterface authInt = radapter.create(VerInterface.class);


                Call<VerificationPojo> call = authInt.authenticateUser(String.valueOf(phoneno));


                call.enqueue(new Callback<VerificationPojo>() {
                    @Override
                    public void onResponse(Call<VerificationPojo> call, Response<VerificationPojo> response) {

                        Intent passIntent = new Intent(RegisterUser.this, PickOffer.class);
                        startActivity(passIntent);
                    }

                    @Override
                    public void onFailure(Call<VerificationPojo> call, Throwable t) {

                    }
                });


            } catch (Exception e) {
                e.printStackTrace();


            }

        }

        protected String doInBackground(Void... urls) {

            try {
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

                logging.setLevel(HttpLoggingInterceptor.Level.BODY);

                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

                httpClient.addInterceptor(logging);


                Retrofit radapter = new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())

                        .client(httpClient.build())
                        .build();

                RInterface restInt = radapter.create(RInterface.class);

                Map<String, String> data = new HashMap<>();
                data.put("fname", fullname);
                data.put("mo", (phoneno));
                data.put("email", email);
                data.put("IMEnumber", IMEINumber);
                data.put("deviceid", deviceid);
                data.put("pass", password);
                Call<RegistrationPojo> call = restInt.getUser(data);



              //  Call<RegistrationPojo> call = restInt.getUser(fullname,phoneno,email,password);

                // Call<RegistrationPojo> call = restInt.getUser(fullname,mobileno,email,password,secretanswer);*/
                call.enqueue(new Callback<RegistrationPojo>() {
                    @Override
                    public void onResponse(Call<RegistrationPojo> call, Response<RegistrationPojo> response) {


                    }

                    @Override
                    public void onFailure(Call<RegistrationPojo> call, Throwable t) {

                    }
                });


            } catch (Exception e) {
                e.printStackTrace();
                return "failure";

            }
            return url;
        }


    }

}
