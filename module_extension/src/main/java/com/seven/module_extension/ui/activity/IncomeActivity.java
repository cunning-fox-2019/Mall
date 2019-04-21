package com.seven.module_extension.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.seven.lib_common.base.activity.BaseTitleActivity;
import com.seven.lib_router.router.RouterPath;
import com.seven.module_extension.R;

/**
 * Created by xxxxxxH on 2019/4/20.
 */
@Route(path = RouterPath.ACTIVITY_IN_COME)
public class IncomeActivity extends BaseTitleActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.me_activity_income;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleText(R.string.me_income);
    }

    @Override
    protected void rightTextBtnClick(View v) {

    }

    @Override
    protected void rightBtnClick(View v) {

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
}
