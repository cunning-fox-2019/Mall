package com.seven.lib_model.http;


import com.seven.lib_http.retrofit.HttpResponse;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * @auhtor seven
 * email:frankchenlei@163.com
 * 2017/10/27
 */

public interface RetrofitService {

    String sms = "sms/send";
    String payPassword = "user/pay_password/is_correct";
    String upload = "image/upload";

    String register = "register";
    String login = "login";
    String password = "password/reset";

    String banner = "ad/index/banner";

    String entrance = "goods/category/list";
    String commodityRecommendList = "goods/top/list";
    String commodityDetails = "goods/info";
    String commodityList = "goods/list";

    String cartTotal = "cart/total";
    String cartAdd = "cart/add";

    String orderPayment = "order/payment";
    String orderAdd = "order/add";
    String orderPay = "order/pay";
    String collect = "collect/add";

    String contactDefault = "user/contact/default/info";

    String looperMessage = "business/notice/message";
    String businessList = "business/list";
    String business = "business/add";
    String businessOrderList = "business/order/list";
    String businessInfo = "business/info";
    String businessOrderInfo = "business/order/info";
    String businessAccept = "business/accept";
    String businessProof = "business/proof/add";
    String businessConfirm = "business/order/confirm";
    String businessCancel= "business/cancel";

    String inviteList = "promotion/invite/list";
    String inComeDetails = "promotion/token/list";

    @POST(sms)
    Observable<HttpResponse> sms(@Body RequestBody requestBody);

    @POST(payPassword)
    Observable<HttpResponse> payPassword(@Body RequestBody requestBody);

    @Multipart
    @POST(upload)
    Observable<HttpResponse> upload(@Part MultipartBody.Part file,
                                    @Part("scene") RequestBody scene);

    /* module user */
    @POST(register)
    Observable<HttpResponse> register(@Body RequestBody requestBody);

    @POST(login)
    Observable<HttpResponse> login(@Body RequestBody requestBody);

    @POST(password)
    Observable<HttpResponse> password(@Body RequestBody requestBody);

    /* module home */
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

    /* module model */
    @POST(looperMessage)
    Observable<HttpResponse> looperMessage();

    @POST(businessList)
    Observable<HttpResponse> businessList(@Body RequestBody requestBody);

    @POST(business)
    Observable<HttpResponse> business(@Body RequestBody requestBody);

    @POST(businessOrderList)
    Observable<HttpResponse> businessOrderList(@Body RequestBody requestBody);

    @POST(businessInfo)
    Observable<HttpResponse> businessInfo(@Body RequestBody requestBody);

    @POST(businessOrderInfo)
    Observable<HttpResponse> businessOrderInfo(@Body RequestBody requestBody);

    @POST(businessAccept)
    Observable<HttpResponse> businessAccept(@Body RequestBody requestBody);

    @POST(businessProof)
    Observable<HttpResponse> businessProof(@Body RequestBody requestBody);

    @POST(businessConfirm)
    Observable<HttpResponse> businessConfirm(@Body RequestBody requestBody);

    @POST(businessCancel)
    Observable<HttpResponse> businessCancel(@Body RequestBody requestBody);

    //extension
    @POST("reward/rule")
    Observable<HttpResponse> rewardrule(@Body RequestBody requestBody);

    @POST(inviteList)
    Observable<HttpResponse> inviteList(@Body RequestBody requestBody);

    @POST(inComeDetails)
    Observable<HttpResponse> inComeDetails(@Body RequestBody requestBody);
}

