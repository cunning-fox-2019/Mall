package com.seven.lib_model.http;


import com.seven.lib_http.retrofit.HttpResponse;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @auhtor seven
 * email:frankchenlei@163.com
 * 2017/10/27
 */

public interface RetrofitService {

    String sms = "sms/send";

    String register = "register";
    String login = "login";
    String password="password/reset";

    String banner="ad/index/banner";

    String entrance="goods/category/list";
    String commodityRecommendList="goods/top/list";
    String commodityDetails="goods/info";
    String commodityList="goods/list";

    String cartTotal="cart/total";
    String cartAdd="cart/add";

    String orderPayment="order/payment";
    String orderAdd="order/add";
    String orderPay="order/pay";
    String collect="collect/add";

    String contactDefault="user/contact/default/info";

    @POST(sms)
    Observable<HttpResponse> sms(@Body RequestBody requestBody);

    @POST(register)
    Observable<HttpResponse> register(@Body RequestBody requestBody);

    @POST(login)
    Observable<HttpResponse> login(@Body RequestBody requestBody);

    @POST(password)
    Observable<HttpResponse> password(@Body RequestBody requestBody);

    @POST(banner)
    Observable<HttpResponse> banner();

    @POST(entrance)
    Observable<HttpResponse> entrance();

    @POST(commodityRecommendList)
    Observable<HttpResponse> commodityRecommendList(@Body RequestBody requestBody);

    @POST(commodityDetails)
    Observable<HttpResponse> commodityDetails(@Body RequestBody requestBody);

    @POST(cartTotal)
    Observable<HttpResponse> cartTotal();

    @POST(cartAdd)
    Observable<HttpResponse> cartAdd(@Body RequestBody requestBody);

    @POST(orderPayment)
    Observable<HttpResponse> orderPayment(@Body RequestBody requestBody);

    @POST(orderAdd)
    Observable<HttpResponse> orderAdd(@Body RequestBody requestBody);

    @POST(contactDefault)
    Observable<HttpResponse> contactDefault();

    @POST(orderPay)
    Observable<HttpResponse> orderPay(@Body RequestBody requestBody);

    @POST(collect)
    Observable<HttpResponse> collect(@Body RequestBody requestBody);

    @POST(commodityList)
    Observable<HttpResponse> commodityList(@Body RequestBody requestBody);
}

