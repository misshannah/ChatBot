package com.chatbot.mobile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.TELEPHONY_SERVICE;

public class MainActivity extends Activity {

    private LoginButton btnLogin;
    private CallbackManager callbackManager;
    private ProfilePictureView profilePictureView;
    private LinearLayout infoLayout;
    private TextView facebookemail;
    private TextView resetpassword;
    private TextView facebookid;
    private TextView facebookName;
    private Button signupbutton, emailsignin;
    private EditText phonenoText, passwordText;
    private String phoneno, password, fbemail, fbid, fbname, IMEINumber, deviceid;
    boolean cancel = false;
    View focusView = null;
    String url = "http://myweb.symvasi.com:3000/db/";
    String fb_url = "http://myweb.symvasi.com:3000/db/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_main);


        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
       // IMEINumber = tm.getDeviceId();

        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        deviceid = model + " " + manufacturer;

        btnLogin = (LoginButton) findViewById(R.id.login_button);
        facebookemail = (TextView) findViewById(R.id.email);
        facebookName = (TextView) findViewById(R.id.name);

        facebookid = (TextView) findViewById(R.id.facebookid);
        infoLayout = (LinearLayout) findViewById(R.id.layout_info);
        btnLogin.setReadPermissions(Arrays.asList("public_profile, email, user_birthday"));
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().logOut();
        // Set up the login form.
        phonenoText = (EditText) findViewById(R.id.phoneno);
        passwordText = (EditText) findViewById(R.id.password);

        emailsignin = (Button) findViewById(R.id.signin_button);
        emailsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });

        signupbutton = (Button) findViewById(R.id.signup_button);
        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppEventsLogger logger = AppEventsLogger.newLogger(MainActivity.this);

// Add to a button click handler
                logger.logEvent("createnewaccount");
                Intent passIntent = new Intent(MainActivity.this, RegisterUser.class);
                startActivity(passIntent);
            }
        });


        resetpassword = (TextView) findViewById(R.id.resetPassword);
        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater layoutInflater
                        = (LayoutInflater) getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.reset_password, null);
                final PopupWindow popupWindow = new PopupWindow(
                        popupView,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);

                popupWindow.showAsDropDown(resetpassword, 5, -30);

            }
        });


        // Callback registration
        btnLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {

                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.v("Main", response.toString());
                                setProfileToView(object);

                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();
                Intent passIntent = new Intent(MainActivity.this, PickOffer.class);
                startActivity(passIntent);
                AppEventsLogger logger = AppEventsLogger.newLogger(MainActivity.this);

// Add to a button click handler
                logger.logEvent("createnewaccount");
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(MainActivity.this, "error to Login Facebook", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void setProfileToView(JSONObject jsonObject) {
        try {
            facebookemail.setText(jsonObject.getString("email"));
            facebookid.setText(jsonObject.getString("id"));
            facebookName.setText(jsonObject.getString("name"));

            // profilePictureView.setPresetSize(ProfilePictureView.NORMAL);
            //profilePictureView.setProfileId(jsonObject.getString("id"));
            //infoLayout.setVisibility(View.VISIBLE);
            facebookData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void facebookData() {
        fbemail = facebookemail.getText().toString();
        fbid = facebookid.getText().toString();
        fbname = facebookName.getText().toString();

        try {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            httpClient.addInterceptor(logging);


            Retrofit radapter = new Retrofit.Builder()
                    .baseUrl(fb_url)
                    .addConverterFactory(GsonConverterFactory.create())

                    .client(httpClient.build())
                    .build();

            FBInterface restInt = radapter.create(FBInterface.class);

            Map<String, String> data = new HashMap<>();
            data.put("facebookid", fbid);
            data.put("facebookname", (fbname));
            data.put("facebookemail", fbemail);
            data.put("IMEnumber", IMEINumber);
            data.put("deviceid", deviceid);

            Call<FBRegistrationPojo> call = restInt.loadUser(data);

            call.enqueue(new Callback<FBRegistrationPojo>() {
                @Override
                public void onResponse(Call<FBRegistrationPojo> call, Response<FBRegistrationPojo> response) {


                }

                @Override
                public void onFailure(Call<FBRegistrationPojo> call, Throwable t) {

                }
            });


        } catch (Exception e) {
            e.printStackTrace();


        }

    }

    private void attemptLogin() {
        new MainActivity.UserLogin().execute();

    }


    class UserLogin extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
            // Store values at the time of the login attempt.

            phoneno = phonenoText.getText().toString();
            password = passwordText.getText().toString();


            // Check for a valid password, if the user entered one.
            if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
                passwordText.setError("Password is invalid");
                focusView = passwordText;
                cancel = true;
            }
            // Check for a valid email address.
            if (TextUtils.isEmpty(phoneno)) {
                phonenoText.setError("Mobile number is invalid");
                focusView = phonenoText;
                cancel = true;
            } else if (!isPhonenoValid(phoneno)) {
                phonenoText.setError("Mobile number is invalid");
                focusView = phonenoText;
                cancel = true;
            }

        }

        private boolean isPhonenoValid(String phoneno) {
            //TODO: Replace this with your own logic
            return phoneno.length() < 7;
        }

        private boolean isPasswordValid(String password) {
            //TODO: Replace this with your own logic
            return password.length() > 4;
        }


        @Override
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

                AuthInterface authInt = radapter.create(AuthInterface.class);


                Map<String, String> data = new HashMap<>();
                data.put("phoneno", phoneno);
                data.put("password", password);


                Call<AuthenticationPojo> call = authInt.authenticateUser(data);

                // Call<AuthenticationPojo> call = authInt.authenticateUser(String.valueOf(phoneno), password);


                call.enqueue(new Callback<AuthenticationPojo>() {
                    @Override
                    public void onResponse(Call<AuthenticationPojo> call, Response<AuthenticationPojo> response) {


                    }

                    @Override
                    public void onFailure(Call<AuthenticationPojo> call, Throwable t) {

                    }
                });


            } catch (Exception e) {
                e.printStackTrace();
                return null;

            }
            return "good";
        }

        protected void onPostExecute() {
            Intent passIntent = new Intent(MainActivity.this, PickOffer.class);
            startActivity(passIntent);
        }


    }


}
