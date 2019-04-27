package com.seven.module_user.ui.fragment.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.seven.lib_common.base.activity.BaseTitleActivity;
import com.seven.lib_common.utils.ToastUtils;
import com.seven.module_user.R;
import com.seven.module_user.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class UserSettingActivity extends BaseTitleActivity {


    @BindView(R2.id.update_password)
    TextView updatePassword;
    @BindView(R2.id.lp_payment_password)
    TextView lpPaymentPassword;
    @BindView(R2.id.payment_account)
    TextView paymentAccount;
    @BindView(R2.id.cancel_account)
    TextView cancelAccount;

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
        return R.layout.mu_activity_setting;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleText(R.string.user_setting);
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

    @OnClick({R2.id.update_password, R2.id.lp_payment_password, R2.id.payment_account, R2.id.cancel_account})
    public void onViewClicked(View view) {
        if (view == updatePassword) {
            startActivity(new Intent(mContext, UserModifyPassWordActivity.class));
        } else if (view == lpPaymentPassword) {
            startActivity(new Intent(mContext, UserSetPayAccountActivity.class));
        } else if (view == paymentAccount) {
            startActivity(new Intent(mContext, UserSetPayAccountActivity.class));
        } else if (view == cancelAccount) {
            ToastUtils.showToast(mContext, "注销成功");
        }
    }


}
