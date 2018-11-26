package com.chenqi.mvvm.repository;

import android.arch.lifecycle.MutableLiveData;

import com.chenqi.mvvm.bean.BaseBean;
import com.chenqi.mvvm.common.State;
import com.chenqi.mvvm.http.rx.BaseObserver;
import com.chenqi.mvvm.http.rx.RxSchedulers;
import com.chenqi.mvvm.net.ApiRepository;

public class LoginRepository extends ApiRepository {
    private MutableLiveData<State> loadState;

    public LoginRepository(MutableLiveData<State> loadState) {
        this.loadState = loadState;
    }

    public void login(String username, String password, MutableLiveData<BaseBean> liveData) {
        addDisposable(apiService.login()
                .compose(RxSchedulers.<BaseBean>io_main())
                .subscribeWith(new BaseObserver<>(liveData, loadState))
        );
    }
}
