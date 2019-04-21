package com.seven.lib_model.http;


import com.seven.lib_http.retrofit.HttpResponse;
import com.seven.lib_model.model.user.LoginEntity;


import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * @auhtor seven
 * email:frankchenlei@163.com
 * 2017/10/27
 */

public interface RetrofitService {
    String login = "login";
    @POST()
    Observable<HttpResponse> login(@Url String url,@Body LoginEntity requestBody);
}

