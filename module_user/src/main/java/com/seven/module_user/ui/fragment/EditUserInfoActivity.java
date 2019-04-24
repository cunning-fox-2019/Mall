package com.seven.module_user.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.seven.lib_common.base.activity.BaseActivity;
import com.seven.lib_common.base.activity.BaseAppCompatActivity;
import com.seven.lib_model.user.UserActivityPresenter;
import com.seven.module_user.R;
import com.seven.module_user.R2;
import com.seven.module_user.ui.fragment.view.CustomToolbar;

import butterknife.BindView;



public class EditUserInfoActivity extends BaseAppCompatActivity {
    @BindView(R2.id.toolbar)
    Toolbar mToolBar;


    private UserActivityPresenter presenter;


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
        statusBar = StatusBar.LIGHT;
        return R.layout.mu_activity_edit_user_info;
    }

    private void getUserInfoDetail() {
        presenter.getUserInfoDetail(1, 1);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("编辑资料");
        ImmersionBar.with(this).init();
        ImmersionBar.setTitleBar(this, mToolBar);
        presenter = new UserActivityPresenter(this, this);
    }

    @Override
    public void result(int code, Boolean hasNextPage, String response, Object object) {
        super.result(code, hasNextPage, response, object);

    }

    @Override
    protected void initBundleData(Intent intent) {

    }

    @Override
    public void onClick(View view) {

    }
}
