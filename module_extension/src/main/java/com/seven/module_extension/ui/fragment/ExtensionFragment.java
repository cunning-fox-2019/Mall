package com.seven.module_extension.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.seven.lib_common.base.fragment.BaseFragment;
import com.seven.lib_router.router.RouterPath;
import com.seven.module_extension.R;

/**
 * @auhtor seven
 * email:frankchenlei@163.com
 * 2019/3/26
 */

@Route(path = RouterPath.FRAGMENT_EXTENSION)
public class ExtensionFragment extends BaseFragment {

    @Override
    public int getContentViewId() {
        return R.layout.me_fragment_extension;
    }

    @Override
    public void init(Bundle savedInstanceState) {

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