package com.chenqi.mvvm;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.chenqi.mvvm.base.LifecycleActivity;
import com.chenqi.mvvm.bean.BaseBean;
import com.chenqi.mvvm.vm.LoginViewModel;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadSir;

public class MainActivity extends LifecycleActivity<LoginViewModel> {

    @Override
    protected void initSir() {
        FrameLayout fl  = findViewById(R.id.fl);
        loadService = LoadSir.getDefault().register(fl, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                reLoad();
            }
        });
    }

    @Override
    protected void initView() {

        super.initView();
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.login("", "");
            }
        });
        showSuccess();
    }

    @Override
    protected void dataObserver() {
        mViewModel.mLoginData.observe(this, new Observer<BaseBean>() {
            @Override
            public void onChanged(@Nullable BaseBean baseBean) {
                Toast.makeText(MainActivity.this, baseBean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void reLoad() {

    }
}


