package com.chenqi.mvvm.http.rx;

import android.arch.lifecycle.MutableLiveData;

import com.chenqi.mvvm.bean.BaseBean;
import com.chenqi.mvvm.common.State;
import com.chenqi.mvvm.common.StateType;

import io.reactivex.observers.DisposableObserver;

public class BaseObserver<T extends BaseBean> extends DisposableObserver<T> {


    private MutableLiveData<T> liveData;
    private MutableLiveData<State> loadState;

    private int SUCCESS = 0;

    public BaseObserver(MutableLiveData<T> liveData, MutableLiveData<State> loadState) {
        this.liveData = liveData;
        this.loadState = loadState;
    }

    @Override
    public void onNext(T response) {
        if (response.getCode() == SUCCESS) {
            // 加载成功
            loadState.postValue(new State(StateType.SUCCESS));
            // 正常返回
            liveData.postValue(response);
        } else {
            loadState.postValue(new State(StateType.ERROR, response.getMessage()));
        }
    }

    @Override
    public void onError(Throwable e) {
        loadState.postValue(new State(StateType.NETWORK));
    }

    @Override
    public void onComplete() {

    }

}
