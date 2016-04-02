package com.example.administrator.webviewdemo.webapp;

import android.webkit.JavascriptInterface;

/**
 * @创建者 ：yqlee
 * @时间 ：2016/4/2  18:11
 * @描述 :Native与Js交互的抽象接口
 */
public interface INativeJsInjection {
    @JavascriptInterface
    void jsUseNativeMethod();
}
