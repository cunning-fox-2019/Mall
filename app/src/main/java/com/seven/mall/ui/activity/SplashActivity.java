package com.seven.mall.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.seven.lib_common.base.activity.BaseAppCompatActivity;
import com.seven.lib_router.Constants;
import com.seven.lib_router.router.RouterPath;
import com.seven.lib_router.router.RouterUtils;
import com.seven.mall.R;

public class SplashActivity extends BaseAppCompatActivity {

    @Override
    protected int getContentViewId() {
        statusBar = StatusBar.HIDE;
        return R.layout.activity_splash;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void initBundleData(Intent intent) {

        intentHome();
    }

    private void intentHome() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                RouterUtils.getInstance().routerWithFade(
                        RouterPath.ACTIVITY_HOME, SplashActivity.this, true);
            }
        }, Constants.TimeConfig.SPLASH_TIME);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void closeLoading() {

    }

    @Override
    public void showToast(String msg) {

    }
}
