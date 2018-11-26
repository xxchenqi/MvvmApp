package com.chenqi.mvvm.base;

import android.app.Application;

import com.chenqi.mvvm.common.EmptyCallback;
import com.chenqi.mvvm.common.ErrorCallback;
import com.chenqi.mvvm.common.LoadingCallback;
import com.kingja.loadsir.core.LoadSir;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        configLoadSir();
    }


    private void configLoadSir() {
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new EmptyCallback())
                .commit();
    }


}
