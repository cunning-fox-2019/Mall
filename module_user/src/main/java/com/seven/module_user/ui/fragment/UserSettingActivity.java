package com.seven.module_user.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.seven.lib_common.base.activity.BaseActivity;
import com.seven.lib_model.model.user.UserEntity;
import com.seven.lib_model.user.UserPresenter;
import com.seven.lib_router.Constants;
import com.seven.module_user.R;

import java.util.List;

/**
 * Created by xh on 2019/3/27.
 */

public class UserSettingActivity extends BaseActivity {

    private UserPresenter presenter;

    private List<UserEntity> list;

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
    protected int getContentViewId() {
        return R.layout.mu_activity_setting;
    }

    private void request(){
        presenter.getUserInfo(Constants.RequestConfig.USER_INFO,"name");
    }

    @Override
    public void result(int code, Boolean hasNextPage, String response, Object object) {
//        super.result(code, hasNextPage, response, object);
//        if (code == Constants.RequestConfig.USER_INFO){
//
//            list = (List<UserEntity>) object;
//        }
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        //presenter = new UserPresenter(this,this);
    }

    @Override
    protected void initBundleData(Intent intent) {
       // ImmersionBar.with(this).transparentStatusBar().init();
    }

    @Override
    public void onClick(View view) {

    }
}
