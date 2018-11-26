package com.chenqi.mvvm.net;

import com.chenqi.mvvm.bean.BaseBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    @GET("tools/mockapi/6598/login2")
    Observable<BaseBean> login();

}
