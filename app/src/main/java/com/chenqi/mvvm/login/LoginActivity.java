package com.chenqi.mvvm.login;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.chenqi.mvvm.MainActivity;
import com.chenqi.mvvm.R;
import com.chenqi.mvvm.base.LifecycleActivity;
import com.chenqi.mvvm.bean.BaseBean;
import com.chenqi.mvvm.login.vm.LoginViewModel;

/**
 * @author cq
 * @date 2018-11-27
 * @Description 登录页
 */
public class LoginActivity extends LifecycleActivity<LoginViewModel> {

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
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void reLoad() {
        mViewModel.login("", "");
    }

    @Override
    protected void showNetWork() {
        Toast.makeText(this, "网络异常", Toast.LENGTH_SHORT).show();
    }
}


