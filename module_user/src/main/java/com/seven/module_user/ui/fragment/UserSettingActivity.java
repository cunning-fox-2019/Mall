package com.seven.module_user.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.seven.lib_common.base.activity.BaseActivity;
import com.seven.lib_common.base.activity.BaseAppCompatActivity;
import com.seven.lib_model.model.user.UserEntity;
import com.seven.lib_model.user.UserPresenter;
import com.seven.lib_router.Constants;
import com.seven.module_user.R;
import com.seven.module_user.R2;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ouyang on 2019/3/27.
 */

public class UserSettingActivity extends BaseAppCompatActivity {
    @BindView(R2.id.toolbar)
    Toolbar mToolBar;
    private UserPresenter presenter;

    private List<UserEntity> list;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
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
    protected int getContentViewId() {
        return R.layout.mu_activity_setting;
    }

    private void request() {
        presenter.getUserInfo(Constants.RequestConfig.USER_INFO, "name");
    }

    @Override
    public void result(int code, Boolean hasNextPage, String response, Object object) {
        super.result(code, hasNextPage, response, object);
        if (code == Constants.RequestConfig.USER_INFO) {

            list = (List<UserEntity>) object;
        }
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        //presenter = new UserPresenter(this,this);
    }

    @Override
    protected void initBundleData(Intent intent) {
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("设置");
    }

    @Override
    public void onClick(View view) {

    }
}
