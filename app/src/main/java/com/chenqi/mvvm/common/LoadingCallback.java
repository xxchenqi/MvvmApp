package com.chenqi.mvvm.common;

import com.chenqi.mvvm.R;
import com.kingja.loadsir.callback.Callback;

public class LoadingCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.layout_loading;
    }
}
