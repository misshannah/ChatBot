package com.chatbot.mobile;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;

import com.chatbot.mobile.R;

public class ViewOffer extends Activity {

    private String LinkToPDF = "https://drive.google.com/file/d/0B88ASBYQJTKbTE03eDZOSHJDLVE/view?usp=sharing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_offer);

        WebView mWebView=new WebView(ViewOffer.this);
        mWebView.getSettings().setJavaScriptEnabled(true);
       // mWebView.getSettings().setPluginsEnabled(true);
      //  mWebView.loadUrl("https://docs.google.com/gview?embedded=true&url="+LinkToPDF);
        mWebView.loadUrl("https://docs.google.com/gview?embedded=true&url="+LinkToPDF);
        setContentView(mWebView);
    }
}
