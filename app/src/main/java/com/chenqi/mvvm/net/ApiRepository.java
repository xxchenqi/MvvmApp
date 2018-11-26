package com.chenqi.mvvm.net;

import com.chenqi.mvvm.base.BaseRepository;
import com.chenqi.mvvm.http.HttpClient;

public class ApiRepository extends BaseRepository {

    protected ApiService apiService;

    public ApiRepository() {
        if (null == apiService) {
            apiService = HttpClient.getClient();
        }
    }


}
