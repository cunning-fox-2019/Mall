package com.seven.lib_model.http;

import com.seven.lib_http.retrofit.HttpResponse;
import com.seven.lib_model.HttpSDK;
import com.seven.lib_model.model.user.LoginEntity;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @auhtor seven
 * email:frankchenlei@163.com
 * 2017/10/26
 */

public class RequestHelper {

    private static RequestHelper requestHelper;

    private RetrofitService appService;
    private RetrofitService storeService;

    private RequestHelper() {

        appService = getApi(HttpSDK.getInstance().getConfig().getAppUrl());
        storeService = getApi(HttpSDK.getInstance().getConfig().getStoreUrl());

    }

    public static RequestHelper getInstance() {

        if (requestHelper == null)
            requestHelper = new RequestHelper();

        return requestHelper;
    }

    private RetrofitService getApi(String key) {

        return RetrofitHelper.getInstance().getApi(key, new AppInterceptor());
    }

    private <K> Observable<K> addSchedulers(Observable<K> observable) {
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public RequestBody requestBody(String json) {
        return RequestBody.create(MediaType.parse("application/json"), json);
    }

    //
//    public Observable<UploadEntity> uploadFile(@NonNull File file) {
//        return getApi().upload(file);
//    }
//
    public Observable<HttpResponse> sms(String json) {
        return appService.sms(requestBody(json));
    }

    public Observable<HttpResponse> register(String json) {
        return appService.register(requestBody(json));
    }

    public Observable<HttpResponse> login(String json) {
        return appService.login(requestBody(json));
    }

    public Observable<HttpResponse> password(String json) {
        return appService.password(requestBody(json));
    }

    public Observable<HttpResponse> banner() {
        return appService.banner();
    }

    public Observable<HttpResponse> entrance() {
        return appService.entrance();
    }

    public Observable<HttpResponse> commodityList(String json) {
        return appService.commodityList(requestBody(json));
    }

    public Observable<HttpResponse> commodityDetails(String json) {
        return appService.commodityDetails(requestBody(json));
    }

    public Observable<HttpResponse> cartTotal() {
        return appService.cartTotal();
    }

    public Observable<HttpResponse> cartAdd(String json) {
        return appService.cartAdd(requestBody(json));
    }

    public Observable<HttpResponse> orderPayment(String json) {
        return appService.orderPayment(requestBody(json));
    }

    public Observable<HttpResponse> orderAdd(String json) {
        return appService.orderAdd(requestBody(json));
    }

    public Observable<HttpResponse> contactDefault() {
        return appService.contactDefault();
    }

    public Observable<HttpResponse> orderPay(String json) {
        return appService.orderPay(requestBody(json));
    }

    //extension
    public Observable<HttpResponse> rewardrule(String json){
        return appService.rewardrule(requestBody(json));
    }
}
