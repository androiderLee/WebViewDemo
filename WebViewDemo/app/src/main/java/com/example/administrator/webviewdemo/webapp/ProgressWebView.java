package com.example.administrator.webviewdemo.webapp;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import java.util.List;

/**
 * @创建者 ：yqlee
 * @时间 ：2016/4/2  18:18
 * @描述 :自定义的WebView
 */
public class ProgressWebView extends WebView {

    private ProgressBar mProgressBar;
    private Context mContext;

    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mProgressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        mProgressBar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 10, 0, 0));
        addView(mProgressBar);
        init();
    }

    private void init() {
        setWebChromeClient(new ProgressChromeClient());
        initWebSettings();
        setWebViewClient(new MyWebViewClient(mContext));
    }

    private void initWebSettings() {
        WebSettings webSettings = getSettings();
        webSettings.setJavaScriptEnabled(true);//允许调用JavaScript
        webSettings.setUseWideViewPort(true); //设置内容自适应屏幕大小
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);//提高渲染的优先级
        webSettings.setSupportZoom(false);//页面禁止缩放
        webSettings.setDomStorageEnabled(true);//允许使用本地缓存
        int sdk = Build.VERSION.SDK_INT;
        if (sdk > 10) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setSaveFormData(true);
        webSettings.setAppCacheMaxSize(1024 * 5);
        //存储H5
        String appCacheDir = mContext.getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath() + "h5_cache";
        webSettings.setAppCachePath(appCacheDir);
        webSettings.setAllowFileAccess(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDatabasePath(appCacheDir);
    }

    /**
     * @param injecton
     * @param jsPattern
     */
    public void setNativeJsInjecton(INativeJsInjection injecton, String jsPattern) {
        if (injecton != null && TextUtils.isEmpty(jsPattern)) {
            addJavascriptInterface(injecton, jsPattern);
        }
    }

    /**
     * 给WebView设置Cookie
     *
     * @param cookies
     */
    public void setCookies(List<String> cookies) {
        if (cookies == null) {
            return;
        }
        CookieSyncManager.createInstance(mContext);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        for (int i = 0; i < cookies.size(); i++) {
            cookieManager.setCookie("cookieUrl", cookies.get(i));
        }
        CookieSyncManager.getInstance().sync();
    }

    /**
     * 内部类，默认持有外部类的引用
     */
    class ProgressChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                mProgressBar.setVisibility(View.GONE);
            } else {
                if (mProgressBar.getVisibility() == View.GONE)
                    mProgressBar.setVisibility(View.VISIBLE);
                mProgressBar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }
}
