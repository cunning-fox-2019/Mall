package com.seven.module_user.ui.fragment.token;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;
import com.seven.lib_common.base.activity.BaseAppCompatActivity;
import com.seven.lib_common.base.activity.BaseTitleActivity;
import com.seven.module_user.R;
import com.seven.module_user.R2;

import butterknife.BindView;

/**
 * Created by xxxxxxH on 2019/4/10.
 * 领取令牌
 */

public class UserReceiveTokenActivity extends BaseTitleActivity {

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
    protected int getLayoutId() {
        return R.layout.mu_activity_receive_token_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleText(R.string.user_receive_token);
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
}
