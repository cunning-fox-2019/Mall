package com.seven.module_extension.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.seven.lib_common.base.activity.BaseAppCompatActivity;
import com.seven.lib_common.base.activity.BaseTitleActivity;
import com.seven.lib_router.router.RouterPath;
import com.seven.module_extension.R;

@Route(path = RouterPath.ACTIVITY_BUY_ROLE)
public class BuyRoleActivity extends BaseAppCompatActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.me_activity_bugrole;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        statusBar = StatusBar.LIGHT;
    }

    @Override
    protected void initBundleData(Intent intent) {

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

    @Override
    public void onClick(View view) {

    }
}
