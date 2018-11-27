package com.chenqi.mvvm.login.vm;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.chenqi.mvvm.base.BaseViewModel;
import com.chenqi.mvvm.bean.BaseBean;
import com.chenqi.mvvm.login.repository.LoginRepository;


public class LoginViewModel extends BaseViewModel<LoginRepository> {

    public MutableLiveData<BaseBean> mLoginData = new MutableLiveData<>();


    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public void login(String username, String password) {
        mRepository.login(username, password, mLoginData);
    }

}
