package com.example.middleproject;

import android.content.Intent;
import android.os.Bundle;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by stomb on 2018-04-04.
 */

public class ActivityStockDetailWebview extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_detail_webview);

        Intent getIntent = getIntent();
        String url = getIntent.getExtras().getString("url");

        mWebView = (WebView)findViewById(R.id.stock_detail_webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(url);
        mWebView.setWebViewClient(new WebViewClient());
    }
}
