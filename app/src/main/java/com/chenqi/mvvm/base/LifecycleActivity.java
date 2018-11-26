package com.chenqi.mvvm.base;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.chenqi.mvvm.common.ErrorCallback;
import com.chenqi.mvvm.common.LoadingCallback;
import com.chenqi.mvvm.common.State;
import com.chenqi.mvvm.util.TUtil;
import com.kingja.loadsir.callback.SuccessCallback;

public abstract class LifecycleActivity<T extends BaseViewModel> extends BaseActivity {

    protected T mViewModel;

    @Override
    protected void initView() {
        showLoading();
        mViewModel = ViewModelProviders.of(this).get((Class<T>) TUtil.getInstance(this, 0));
        mViewModel.loadState.observe(this, observer);
        dataObserver();
    }


    protected abstract void dataObserver();


    protected void showSuccess() {
        loadService.showCallback(SuccessCallback.class);
    }

    protected void showError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        loadService.showCallback(SuccessCallback.class);
    }

    protected void showLoading() {
        loadService.showCallback(LoadingCallback.class);
    }

    protected void showNetWork() {
        Toast.makeText(this, "网络异常", Toast.LENGTH_SHORT).show();
        loadService.showCallback(ErrorCallback.class);
    }


    private Observer observer = new Observer<State>() {
        @Override
        public void onChanged(@Nullable State state) {
            if (state != null) {
                switch (state.getCode()) {
                    case SUCCESS:
                        showSuccess();
                        break;
                    case ERROR:
                        showError(state.getMsg());
                        break;
                    case NETWORK:
                        showNetWork();
                        break;
                    case LOADING:
                        showLoading();
                        break;
                    case TIPS:
                        break;
                    case EMPTY:
                        break;
                }
            }
        }
    };


}
