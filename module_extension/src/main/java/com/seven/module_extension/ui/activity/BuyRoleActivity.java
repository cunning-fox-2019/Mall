package com.seven.module_extension.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;
import com.seven.lib_common.base.activity.BaseTitleActivity;
import com.seven.lib_common.utils.ToastUtils;
import com.seven.lib_model.ApiManager;
import com.seven.lib_model.BaseResult;
import com.seven.lib_model.model.extension.BuyRoleEntity;
import com.seven.lib_model.model.user.UserEntity;
import com.seven.lib_router.db.shard.SharedData;
import com.seven.lib_router.router.RouterPath;
import com.seven.module_extension.R;
import com.seven.module_extension.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Route(path = RouterPath.ACTIVITY_BUY_ROLE)
public class BuyRoleActivity extends BaseTitleActivity {


    @BindView(R2.id.me_cb_kz)
    CheckBox meCbKz;
    @BindView(R2.id.me_cb_cz)
    CheckBox meCbCz;
    @BindView(R2.id.me_cb_chengz)
    CheckBox meCbChengz;
    @BindView(R2.id.me_cb_apliy)
    CheckBox meCbApliy;
    @BindView(R2.id.me_cb_wechat)
    CheckBox meCbWechat;
    @BindView(R2.id.me_buy_btn)
    Button meBuyBtn;
    @BindView(R2.id.me_kz_price)
    TextView meKzPrice;
    @BindView(R2.id.me_cz_price)
    TextView meCzPrice;
    @BindView(R2.id.me_chengz_price)
    TextView meChengzPrice;

    private String role = "";
    private String pay = "";

    @Override
    protected int getLayoutId() {
        return R.layout.me_activity_bugrole;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        statusBar = StatusBar.LIGHT;
        setTitleText(R.string.me_buyrole_title);
        ApiManager.getRolePrice()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResult<BuyRoleEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<BuyRoleEntity> buyRoleEntityBaseResult) {
                        meKzPrice.setText("￥" + buyRoleEntityBaseResult.getData().getRole_2());
                        meCzPrice.setText("￥" + buyRoleEntityBaseResult.getData().getRole_3());
                        meChengzPrice.setText("￥" + buyRoleEntityBaseResult.getData().getRole_4());
                    }

                    @Override
                    public void onError(Throwable e) {

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


    @OnClick({R2.id.me_cb_kz, R2.id.me_cb_cz, R2.id.me_cb_chengz, R2.id.me_cb_apliy, R2.id.me_cb_wechat, R2.id.me_buy_btn})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.me_cb_kz) {
            meCbKz.setChecked(true);
            meCbCz.setChecked(false);
            meCbChengz.setChecked(false);
            role = "2";
        } else if (i == R.id.me_cb_cz) {
            meCbKz.setChecked(false);
            meCbCz.setChecked(true);
            meCbChengz.setChecked(false);
            role = "3";
        } else if (i == R.id.me_cb_chengz) {
            meCbKz.setChecked(false);
            meCbCz.setChecked(false);
            meCbChengz.setChecked(true);
            role = "4";
        } else if (i == R.id.me_cb_apliy) {
            meCbApliy.setChecked(true);
            meCbWechat.setChecked(false);
            pay = "0";
        } else if (i == R.id.me_cb_wechat) {
            meCbApliy.setChecked(false);
            meCbWechat.setChecked(true);
            pay = "1";
        } else if (i == R.id.me_buy_btn) {
            if (new Gson().fromJson(SharedData.getInstance().getUserInfo(),UserEntity.class).getRole() == 0){
                ToastUtils.showToast(mContext,"需要购买报单成为vip才能购买");
                return;
            }
            if (role.isEmpty()) {
                ToastUtils.showToast(mContext, "请选择购买角色");
                return;
            }
            if (pay.isEmpty()) {
                ToastUtils.showToast(mContext, "请选择支付方式");
                return;
            }

        }
    }
}
