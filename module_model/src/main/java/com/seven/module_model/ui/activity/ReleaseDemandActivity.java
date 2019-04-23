package com.seven.module_model.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.seven.lib_common.base.activity.BaseTitleActivity;
import com.seven.lib_common.stextview.TypeFaceEdit;
import com.seven.lib_common.stextview.TypeFaceView;
import com.seven.lib_router.router.RouterPath;
import com.seven.module_model.R;
import com.seven.module_model.R2;

import butterknife.BindView;

/**
 * @auhtor seven
 * email:frankchenlei@163.com
 * 2019/4/19
 */
@Route(path = RouterPath.ACTIVITY_RELEASE_DEMAND)
public class ReleaseDemandActivity extends BaseTitleActivity {

    @BindView(R2.id.buy_btn)
    public RelativeLayout buyBtn;
    @BindView(R2.id.buy_iv)
    public ImageView buyIv;
    @BindView(R2.id.sell_btn)
    public RelativeLayout sellBtn;
    @BindView(R2.id.sell_iv)
    public ImageView sellIv;

    @BindView(R2.id.token_et)
    public TypeFaceEdit tokenEt;
    @BindView(R2.id.buy_token_et)
    public TypeFaceEdit buyTokenEt;
    @BindView(R2.id.price_tv)
    public TypeFaceView priceTv;
    @BindView(R2.id.price_et)
    public TypeFaceEdit priceEt;
    @BindView(R2.id.buy_price_et)
    public TypeFaceEdit buyPriceEt;

    @BindView(R2.id.sell_layout)
    public LinearLayout sellLayout;
    @BindView(R2.id.alipay_account_et)
    public TypeFaceEdit alipayEt;
    @BindView(R2.id.wechat_account_et)
    public TypeFaceEdit wechatEt;

    @BindView(R2.id.demand_tv)
    public TypeFaceView demandTv;

    @Override
    protected int getLayoutId() {
        return R.layout.mm_activity_release_demand;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        setTitleText(R.string.label_demand);

    }

    @Override
    protected void initBundleData(Intent intent) {

        selectTab(buyBtn);

    }

    private void selectTab(View view) {

        if (!view.isSelected()) {
            buyBtn.setSelected(buyBtn == view);
            sellBtn.setSelected(sellBtn == view);

            buyIv.setVisibility(buyBtn.isSelected() ? View.VISIBLE : View.GONE);
            sellIv.setVisibility(sellBtn.isSelected() ? View.VISIBLE : View.GONE);

            priceTv.setText(buyBtn.isSelected() ? R.string.label_sale_price : R.string.label_buy_price);
            tokenEt.setVisibility(buyBtn.isSelected() ? View.VISIBLE : View.GONE);
            buyTokenEt.setVisibility(buyBtn.isSelected() ? View.GONE : View.VISIBLE);
            priceEt.setVisibility(buyBtn.isSelected() ? View.VISIBLE : View.GONE);
            buyPriceEt.setVisibility(buyBtn.isSelected() ? View.GONE : View.VISIBLE);

            sellLayout.setVisibility(buyBtn.isSelected() ? View.VISIBLE : View.GONE);
            demandTv.setText(buyBtn.isSelected() ? R.string.button_release_sale : R.string.button_release_buy);
        }
    }

    public void btClick(View view) {

        if (view.getId() == R.id.buy_btn)
            selectTab(buyBtn);

        if (view.getId() == R.id.sell_btn)
            selectTab(sellBtn);

        if (view.getId() == R.id.demand_btn) {
        }
    }

    @Override
    protected void rightTextBtnClick(View v) {

    }

    @Override
    protected void rightBtnClick(View v) {

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
