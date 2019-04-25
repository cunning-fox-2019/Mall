package com.seven.module_extension.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.seven.lib_common.base.activity.BaseTitleActivity;
import com.seven.lib_common.utils.GsonUtils;
import com.seven.lib_model.ApiManager;
import com.seven.lib_model.BaseResult;
import com.seven.lib_model.model.extension.MyInterViewEntity;
import com.seven.lib_model.model.user.UserEntity;
import com.seven.lib_router.Constants;
import com.seven.lib_router.db.shard.SharedData;
import com.seven.lib_router.router.RouterPath;
import com.seven.module_extension.R;

import java.lang.reflect.Type;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Route(path = RouterPath.ACTIVITY_MY_INTERVIEW)
public class MyInterviewActivity extends BaseTitleActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.me_acitvity_myinterview;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        statusBar = StatusBar.LIGHT;
        setTitleText(R.string.me_my_interview_title);
        UserEntity userEntity = new Gson().fromJson(SharedData.getInstance().getUserInfo(),UserEntity.class);
        int userId = userEntity.getId();
        ApiManager.getMyInterView(String.valueOf(userId))
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Observer<BaseResult<MyInterViewEntity>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResult<MyInterViewEntity> myInterViewEntityBaseResult) {
                Log.e("xxxxxxH","onNext"+myInterViewEntityBaseResult);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("xxxxxxH","onError"+e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
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
