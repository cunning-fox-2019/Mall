package com.seven.lib_model.http;

import com.seven.lib_model.HttpSDK;

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

        return RetrofitHelper.getInstance().getApi(key, new KoloInterceptor());
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

}
