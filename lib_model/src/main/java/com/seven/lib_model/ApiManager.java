package com.seven.lib_model;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.seven.lib_model.model.extension.ReceiveGoodsEntity;
import com.seven.lib_model.model.extension.RewardRuleEntity;
import com.seven.lib_model.model.extension.RewardRuleParam;
import com.seven.lib_model.model.user.LoginEntity;
import com.seven.lib_model.model.user.OrderEntity;
import com.seven.lib_model.model.user.OrderListRequestEntity;
import com.seven.lib_model.model.user.TokenEntity;
import com.seven.lib_model.model.user.UserEntity;
import com.seven.lib_model.model.user.mine.AddAddressEntity;
import com.seven.lib_model.model.user.mine.AddressEntity;
import com.seven.lib_model.model.user.mine.CommonListPageEntity;
import com.seven.lib_model.model.user.mine.DTEntity;
import com.seven.lib_model.model.user.mine.ShopEntity;
import com.seven.lib_opensource.application.SevenApplication;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * 接口
 * Created  on 16/2/25.
 */
public class ApiManager {
    private static Context mContext;
    private static ApiManagerService apiManagerService;

    private ApiManager() {
    }

    /**
     * 网络请求库初始化
     */
    public static ApiManagerService init(Context context) {
        if (apiManagerService == null) {
            mContext = context;
            /**日志拦截器*/
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            /**设置超时和拦截器*/
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS);
            builder.interceptors().add(new CommonInterceptor());
            builder.interceptors().add(logging);
            /**创建Retrofit实例*/
            GsonBuilder gsonBuilder = new GsonBuilder();

            Gson gson = gsonBuilder.create();
            GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://zhongfu.lerqin.com/")
                    .client(builder.build())
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            apiManagerService = retrofit.create(ApiManagerService.class);
        }
        return apiManagerService;
    }

    /*通用拦截器，用于处理登录等问题*/
    private static class CommonInterceptor implements Interceptor {
        @Override

        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            // 配置全局的token

            String authToken = SevenApplication.getInstance().getToken();
            Request.Builder requestBuilder = original.newBuilder()
                    .header("Authorization", authToken)
                    .method(original.method(), original.body());
            Request request = requestBuilder.build();
            //执行请求
            Response response = null;
            try {
                response = chain.proceed(request);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return response;
        }
    }

    /**
     * 服务接口集合
     */
    private interface ApiManagerService {
        @POST("login")
        Observable<BaseResult<TokenEntity>> login(@Body LoginEntity entity);

        @POST("cart/list")
        Observable<BaseResult<ShopEntity>> getCartList();

        @POST("collect/list")
        Observable<BaseResult<ShopEntity>> getCollectList();

        @POST("order/list")
        Observable<BaseResult> getOrderList(@Query("page") int page, @Query("status") int status);

        @POST("user/contact/list")
        Observable<BaseResult<List<AddressEntity>>> getAddressList();

        @POST("user/contact/add")
        Observable<BaseResult> addAddress(@Body AddAddressEntity entity);

        @POST("region/list")
        Observable<BaseResult<DTEntity>> getRegionList();

        //extension-------------------------------------------
        @POST("promotion/form/goods/list")
        Observable<BaseResult<ReceiveGoodsEntity>> getReciveGoodsList();

        @POST("/reward/rule")
        Observable<BaseResult<RewardRuleEntity>> getRewardRul(@Query("role") int role);

        @POST("user/info")
        Observable<BaseResult<UserEntity>> getUserInfo();

        @POST("order/list")
        Observable<BaseResult<CommonListPageEntity<OrderEntity>>> getOrderList(@Body OrderListRequestEntity entity);
    }

    public static Observable<BaseResult<TokenEntity>> login(LoginEntity entity) {
        return apiManagerService.login(entity);
    }

    public static Observable<BaseResult<ReceiveGoodsEntity>> getReciveGoodsList() {
        return apiManagerService.getReciveGoodsList();
    }

    public static Observable<BaseResult<ShopEntity>> getCartList() {
        return apiManagerService.getCartList();
    }

    public static Observable<BaseResult<ShopEntity>> getCollectList() {
        return apiManagerService.getCollectList();
    }

    public static Observable<BaseResult> getOrderList(int page, int status) {
        return apiManagerService.getOrderList(page, status);
    }

    public static Observable<BaseResult<List<AddressEntity>>> getAddressList() {
        return apiManagerService.getAddressList();
    }

    public static Observable<BaseResult> addAddress(AddAddressEntity entity) {
        return apiManagerService.addAddress(entity);
    }

    public static Observable<BaseResult<DTEntity>> getRegionList() {
        return apiManagerService.getRegionList();
    }

    public static Observable<BaseResult<UserEntity>> getUserInfo() {
        return apiManagerService.getUserInfo();
    }

    public static Observable<BaseResult<CommonListPageEntity<OrderEntity>>> getOrderList(OrderListRequestEntity entity){
        return apiManagerService.getOrderList(entity);
    }
}
