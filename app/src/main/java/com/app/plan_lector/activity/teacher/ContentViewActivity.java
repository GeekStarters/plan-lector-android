package com.app.plan_lector.activity.teacher;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.app.plan_lector.R;

public class ContentViewActivity extends Activity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);

        String displayString = getIntent().getExtras().getString("display");
        if(displayString != null)
            webView.loadData(displayString, "text/html; charset=utf-8", "UTF-8");
    }
}
