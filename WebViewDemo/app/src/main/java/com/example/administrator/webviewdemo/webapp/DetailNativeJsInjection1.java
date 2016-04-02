package com.example.administrator.webviewdemo.webapp;

import android.webkit.JavascriptInterface;

/**
 * @创建者 ：yqlee
 * @时间 ：2016/4/2  18:12
 * @描述 :具体的Native与Js交互类
 */
public class DetailNativeJsInjection1 implements INativeJsInjection {

    @Override
    public void jsUseNativeMethod() {
    }

    /**
     * Js调用Native的方法
     */
    @JavascriptInterface
    public void jsUseNativeMethod1() {
        //js调用Native的方法
    }

    /**
     * native调用Js的方法
     */
    public void nativeUseJsMethod1() {
        //
//    progressWebView.loadUrl("javascript:C.Api.onChange()");
    }

}
