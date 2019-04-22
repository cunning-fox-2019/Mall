package com.seven.mall.application;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.seven.lib_common.stextview.STextViewSDK;
import com.seven.lib_model.HttpConfig;
import com.seven.lib_model.HttpSDK;
import com.seven.lib_opensource.application.SConfig;
import com.seven.lib_opensource.application.SSDK;
import com.seven.lib_opensource.application.SevenApplication;
import com.seven.lib_router.RouterSDK;
import com.seven.lib_router.Variable;

/**
 * @auhtor seven
 * email:frankchenlei@163.com
 * 2019/3/26
 */

public class MallApplication extends SevenApplication {

    public static final int MIN_CODE = 400;
    public static final int MAX_CODE = 500;
    public static final int EVENT_CODE = 9000;

    public static final String BASE_URL="http://zhongfu.lerqin.com/";
    public static final String STORE_URL="";
    public static final String APP_KEY="";
    public static final String STORE_KEY="";

    private boolean release = false;

    @Override
    public void initApp() {
        super.initApp();

        SConfig config = new SConfig();
        config.setEventCode(EVENT_CODE);
        config.setMinCode(MIN_CODE);
        config.setMaxCode(MAX_CODE);
        SSDK.getInstance().initSDK(getInstance(), config);
        SSDK.getInstance().setLoggerDebug(!release);
        STextViewSDK.getInstance().initSDK(getInstance());

        RouterSDK.getInstance().initSDK(getInstance());

        isLoggerDebug = !release;

        if (release) {

        }else {

        }

    }

    private static HttpConfig initHttpConfig() {

        HttpConfig.Builder builder = new HttpConfig.Builder();
        HttpConfig config = builder
                .uuid(Variable.getInstance().getUuid())
                .token(Variable.getInstance().getToken())
                .language(Variable.getInstance().getLanguage())
                .appUrl(BASE_URL)
                .storeUrl(STORE_URL)
                .appKey(APP_KEY)
                .storeKey(STORE_KEY)
                .build();

        return config;
    }

    public static void changeHttpConfig() {
        HttpSDK.getInstance().setConfig(initHttpConfig());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
