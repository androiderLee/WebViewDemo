package com.example.administrator.webviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.administrator.webviewdemo.webapp.DetailNativeJsInjection1;
import com.example.administrator.webviewdemo.webapp.ProgressWebView;

import java.util.ArrayList;

public class WebApp1Activity extends AppCompatActivity {

    private ProgressWebView mProgressWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_app1);
        init();
    }

    private void init() {
        mProgressWebView = (ProgressWebView) findViewById(R.id.progresswebview);
        mProgressWebView.setNativeJsInjecton(new DetailNativeJsInjection1(), "jsPattarn");
        ArrayList<String> cookies = new ArrayList<>();
        cookies.add("cookies1");
        cookies.add("cookies2");
        cookies.add("cookies2");
        mProgressWebView.setCookies(cookies);
    }

}
