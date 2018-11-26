package com.chenqi.mvvm.http;

import com.chenqi.mvvm.BuildConfig;
import com.chenqi.mvvm.net.ApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpClient {

    private static HttpClient httpClient;
    private ApiService client;

    private HttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        //设置所有网络请求超时时间和开启自动重连
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
//                .addInterceptor(chain -> {
//                    //获得请求信息，此处如有需要可以添加headers信息
//                    Request request = chain.request();
//                    //添加Cookie信息
//                    // TODO: 2017/10/20  设置请求header
////                    request = request.newBuilder().addHeader("Content-Type", "application/json").addHeader("x-token", CreditMoneyApplication.getAppInstance().getUserDetails() == null ? "" : CreditMoneyApplication.getAppInstance().getUserDetails().getToken()).build();
//                    //打印请求信息
//                    if (Log) {
//                        LogUtils.e("url:" + request.url());
//                        LogUtils.e("method:" + request.method());
//                        LogUtils.e("header:" + request.headers().toString());
//                        Buffer buffers = new Buffer();
//                        RequestBody body = request.body();
//                        if (body != null) {
//                            body.writeTo(buffers);
//                        }
//                        LogUtils.e("request-body:" + buffers);
//                    }
//                    //记录请求耗时
//                    long startNs = System.nanoTime();
//                    Response response;
//                    //发送请求，获得相应，
//                    response = chain.proceed(request);
//                    if (Log) {
//                        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
//                        //打印请求耗时
//                        LogUtils.e("耗时:" + tookMs + "ms");
//                        //使用response获得headers(),可以更新本地Cookie。
//                        LogUtils.e("headers==========");
//                        Headers headers = response.headers();
//                        LogUtils.e(headers.toString());
//
//                        //获得返回的body，注意此处不要使用responseBody.string()获取返回数据，原因在于这个方法会消耗返回结果的数据(buffer)
//                        ResponseBody responseBody = response.body();
//
//                        //为了不消耗buffer，我们这里使用source先获得buffer对象，然后clone()后使用
//                        BufferedSource source;
//                        if (responseBody != null) {
//                            source = responseBody.source();
//
//                            source.request(Long.MAX_VALUE); // Buffer the entire body.
//                            //获得返回的数据
//                            Buffer buffer = source.buffer();
//                            //使用前clone()下，避免直接消耗
//                            LogUtils.e("response:" + buffer.clone().readString(Charset.forName("UTF-8")));
//                        }
//                    }
//                    return response;
//                })
//                .addNetworkInterceptor(new ResponseInterceptor())
                .connectTimeout(20, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.wanandroid.com/") //测试
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        client = retrofit.create(ApiService.class);
    }

    public static ApiService getClient() {
        return getInstance().client;
    }


    private static HttpClient getInstance() {
        if (httpClient == null) {
            httpClient = new HttpClient();
        }
        return httpClient;
    }

}
