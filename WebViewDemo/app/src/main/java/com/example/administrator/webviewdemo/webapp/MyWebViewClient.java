package com.example.administrator.webviewdemo.webapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @创建者 ：yqlee
 * @时间 ：2016/4/2  18:17
 * @描述 :自定义WebViewClient
 */
public class MyWebViewClient extends WebViewClient {

    private Context mContext;

    public MyWebViewClient(Context context){
        mContext = context;
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        handler.proceed();  // 接受所有网站的证书
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (!url.contains("tel:")) {
            if (url.contains(mContext.getPackageName())) {
                view.loadUrl(url);
            } else if (url.equals("local://redo")) {

            } else {
                view.loadUrl("file:///android_asset/noWifi.html");
            }
        }
        return true;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        //缓存错误页面
        view.stopLoading();
        view.clearView();
        view.loadUrl("javascript:document.body.innerHTML=\"" + "" + "\"");
        view.loadUrl("file:///android_asset/noWifi.html");
        super.onReceivedError(view, errorCode, description, failingUrl);
    }
}
