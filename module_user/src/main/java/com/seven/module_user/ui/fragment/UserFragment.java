package com.seven.module_user.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.seven.lib_common.base.fragment.BaseFragment;
import com.seven.lib_common.utils.ToastUtils;
import com.seven.lib_opensource.application.SSDK;
import com.seven.lib_router.router.RouterPath;
import com.seven.lib_router.router.RouterUtils;
import com.seven.module_user.R;
import com.seven.module_user.R2;
import com.seven.module_user.ui.activity.login.LoginActivity;
import com.seven.module_user.ui.activity.login.RegisterActivity;
import com.seven.module_user.ui.fragment.order.UserOrderListActivity;
import com.seven.module_user.ui.fragment.token.UserTokenActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auhtor seven
 * email:frankchenlei@163.com
 * 2019/3/26
 */
@Route(path = RouterPath.FRAGMENT_USER)
public class UserFragment extends BaseFragment {
    @BindView(R2.id.wait_pay)
    TextView waitPay;
    @BindView(R2.id.wait_send)
    TextView waitSend;
    @BindView(R2.id.shop_received)
    TextView shopReceived;
    @BindView(R2.id.shop_cart)
    TextView shop_cart;


    @Override
    public int getContentViewId() {

        return R.layout.mu_fragment_user;
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

    @OnClick(R2.id.shop_cart)
    void goShopCar(){
        RouterUtils.getInstance().routerNormal(RouterPath.ACTIVITY_SHOPPING_CART);
    }

    @OnClick(R2.id.user_info_layout)
    void setUserInfo() {
        startActivity(new Intent(getActivity(), EditUserInfoActivity.class));
        //RouterUtils.getInstance().routerWithFade(RouterPath.FRAGMENT_USER_EDIT_UESR_INFO, SSDK.getInstance().getContext());
    }

    @OnClick(R2.id.my_setting)
    void setting() {
        startActivity(new Intent(getActivity(), UserSettingActivity.class));
        //RouterUtils.getInstance().routerWithFade(RouterPath.FRAGMENT_USER_EDIT_UESR_INFO, SSDK.getInstance().getContext());
    }

    @OnClick(R2.id.my_shoucang)
    void storeUp() {
        startActivity(new Intent(getActivity(), CollectionActivity.class));
    }

    @OnClick(R2.id.my_address)
    void address() {
        startActivity(new Intent(getActivity(), UserAddressActivity.class));
    }

    @OnClick(R2.id.logout)
    void logout() {
        startActivity(new Intent(getActivity(), LoginActivity.class));
        //ToastUtils.showToast(getActivity(), "退出");
    }

    @OnClick(R2.id.my_clear_cache)
    void clearCache() {
        ToastUtils.showToast(getActivity(), "清除成功");
    }

    @OnClick(R2.id.all_order)
    void allOrder() {
        startActivity(new Intent(getActivity(), UserOrderListActivity.class));
    }

    @OnClick(R2.id.my_token)
    void token() {
        startActivity(new Intent(getActivity(), UserTokenActivity.class));
    }

    @OnClick({R2.id.wait_pay, R2.id.wait_send, R2.id.shop_received})
    void shop(View view) {
        Intent intent = new Intent(getActivity(), UserOrderListActivity.class);
        if (view == waitPay) {
            intent.putExtra("type", 1);
        } else if (view == waitSend) {
            intent.putExtra("type", 2);
        } else if (view == shopReceived) {
            intent.putExtra("type", 3);
        }
        startActivity(intent);
    }
}
