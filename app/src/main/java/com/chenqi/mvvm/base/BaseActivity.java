package com.chenqi.mvvm.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    protected LoadService loadService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initSir();
        initView();
        initData();
    }

    protected void initSir() {
        loadService = LoadSir.getDefault().register(this, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                reLoad();
            }
        });
        loadService.showSuccess();
    }


    public abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void reLoad();
}
