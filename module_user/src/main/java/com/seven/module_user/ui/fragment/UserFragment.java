package com.seven.module_user.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.seven.lib_common.base.fragment.BaseFragment;
import com.seven.lib_router.router.RouterPath;
import com.seven.lib_router.router.RouterUtils;
import com.seven.module_user.R;
import com.seven.module_user.R2;

import butterknife.BindView;

/**
 * @auhtor seven
 * email:frankchenlei@163.com
 * 2019/3/26
 */
@Route(path = RouterPath.FRAGMENT_USER)
public class UserFragment extends BaseFragment {

    @BindView(R2.id.login_btn)
    public RelativeLayout loginBtn;

    @Override
    public int getContentViewId() {
        return R.layout.mu_fragment_user;
    }

    @Override
    public void init(Bundle savedInstanceState) {

        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        RouterUtils.getInstance().routerNormal(RouterPath.ACTIVITY_LOGIN);
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
