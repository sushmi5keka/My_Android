package com.example.wabview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    WebView webView;
    Button button1,button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button)findViewById(R.id.load);
        button1.setOnClickListener(this);
        button2=(Button)findViewById(R.id.web);
        button2.setOnClickListener(this);
        webView = (WebView)findViewById(R.id.webView);
        webView.setWebChromeClient(new  MyWebViewClint());
        String url="http://coderbd.com";
        button2.getSettings().setJavaScriptEnabaled(true);
        button2.loadUrl(url);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.load;

        }

    }
}
