package com.example.ramunisreallt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity8 extends AppCompatActivity {

    private WebView wb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        wb = (WebView) findViewById(R.id.wb);
        wb.loadUrl("http://192.168.64/reg");
        wb.setWebViewClient(new WebViewClient());

        WebSettings myWebSettings = wb.getSettings();
        myWebSettings.setJavaScriptEnabled(true);
    }
}