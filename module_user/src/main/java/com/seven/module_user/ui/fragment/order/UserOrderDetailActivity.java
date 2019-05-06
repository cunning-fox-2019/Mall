package com.seven.module_user.ui.fragment.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.seven.lib_common.base.activity.BaseTitleActivity;
import com.seven.lib_model.model.home.CartEntity;
import com.seven.lib_router.router.RouterPath;
import com.seven.module_user.R;
import com.seven.module_user.R2;

import java.util.List;

import butterknife.BindView;

/**
 * Created by xxxxxxH on 2019/4/9.
 */
@Route(path = RouterPath.ACTIVITY_MINE_SHOP_PAY)
public class UserOrderDetailActivity extends BaseTitleActivity {
    @BindView(R2.id.toolbar)
    Toolbar mToolBar;
    @Autowired(name = "shop")
    List<CartEntity> shopList;


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
    protected void init(Bundle savedInstanceState) {
    }

    @Override
    protected void initBundleData(Intent intent) {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected int getLayoutId() {
        statusBar = StatusBar.LIGHT;
        return R.layout.mu_activity_order_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void rightTextBtnClick(View v) {

    }

    @Override
    protected void rightBtnClick(View v) {

    }
}
