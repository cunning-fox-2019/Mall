package com.seven.module_model.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.seven.lib_common.base.activity.BaseTitleActivity;
import com.seven.lib_common.listener.OnClickListener;
import com.seven.lib_common.stextview.TypeFaceView;
import com.seven.lib_common.ui.dialog.MallDialog;
import com.seven.lib_common.utils.ResourceUtils;
import com.seven.lib_common.utils.ScreenUtils;
import com.seven.lib_common.utils.glide.GlideUtils;
import com.seven.lib_router.Constants;
import com.seven.lib_router.router.RouterPath;
import com.seven.lib_router.router.RouterUtils;
import com.seven.module_model.R;
import com.seven.module_model.R2;

import butterknife.BindView;

/**
 * @auhtor seven
 * email:frankchenlei@163.com
 * 2019/4/19
 */
@Route(path = RouterPath.ACTIVITY_TRANSACTION_DETAILS)
public class TransactionDetailsActivity extends BaseTitleActivity {

    @Autowired(name = Constants.BundleConfig.TYPE)
    public int type;

    @BindView(R2.id.info_hint)
    public TypeFaceView infoHint;
    @BindView(R2.id.name_tv)
    public TypeFaceView nameTv;
    @BindView(R2.id.avatar_iv)
    public ImageView avatarIv;
    @BindView(R2.id.radio_tv)
    public TypeFaceView radioTv;
    @BindView(R2.id.volume_tv)
    public TypeFaceView volumeTv;

    @BindView(R2.id.alipay_account_tv)
    public TypeFaceView alipayAccountTv;
    @BindView(R2.id.wechat_account_tv)
    public TypeFaceView wechatAccountTv;
    @BindView(R2.id.status_tv)
    public TypeFaceView statusTv;
    @BindView(R2.id.token_tv)
    public TypeFaceView tokenTv;
    @BindView(R2.id.price_tv)
    public TypeFaceView priceTv;

    @BindView(R2.id.voucher_layout)
    public LinearLayout voucherLayout;
    @BindView(R2.id.voucher_iv)
    public ImageView voucherIv;

    @BindView(R2.id.transaction_btn)
    public RelativeLayout transactionBtn;
    @BindView(R2.id.transaction_tv)
    public TypeFaceView transactionTv;

    private MallDialog transactionDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.mm_acitivity_transaction_details;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        ARouter.getInstance().inject(this);

        setTitleText(R.string.title_transaction_details);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) voucherIv.getLayoutParams();
        params.width = (screenWidth - ScreenUtils.dip2px(mContext, 16 * 2)) / 3;
        params.height = (screenWidth - ScreenUtils.dip2px(mContext, 16 * 2)) / 3;
        voucherIv.setLayoutParams(params);
    }

    @Override
    protected void initBundleData(Intent intent) {

        showInfo();

    }

    private void showInfo() {

        infoHint.setText(type == Constants.BundleConfig.TYPE_SELL ?
                R.string.label_buy_info : R.string.label_sell_info);
        transactionTv.setText(type == Constants.BundleConfig.TYPE_SELL ?
                R.string.button_launch_sell : R.string.button_launch_buy);

        GlideUtils.loadCircleImage(mContext, "http://b-ssl.duitang.com/uploads/item/201201/08/20120108130517_Ra8f2.jpg", avatarIv);
        GlideUtils.loadImage(mContext, "http://b-ssl.duitang.com/uploads/item/201201/08/20120108130517_Ra8f2.jpg", voucherIv);

        nameTv.setText("张三");
        radioTv.setText(ResourceUtils.getFormatText(R.string.radio, 92 + "%"));
        volumeTv.setText(ResourceUtils.getFormatText(R.string.volume, 226));
        alipayAccountTv.setText("：15528001078");
        wechatAccountTv.setText("：15528001078");
        statusTv.setText("(交易状态)");
        tokenTv.setText("889.0");
        priceTv.setText("14040.01");
    }

    public void btClick(View view) {

        if (view.getId() == R.id.transaction_btn) {

            if (transactionTv.getText().toString().equals(ResourceUtils.getText(R.string.button_voucher)))
                RouterUtils.getInstance().routerNormal(RouterPath.ACTIVITY_UPLOAD_VOUCHER);
            else{
                if (type == Constants.BundleConfig.TYPE_BUY)
                    transactionTv.setText(R.string.button_voucher);
                else
                    transactionBtn.setVisibility(View.GONE);

                showBuyDialog();
            }
        }

    }

    private void showBuyDialog() {

        if (transactionDialog == null) {
            transactionDialog = new MallDialog(this, R.style.Dialog, new OnClickListener() {
                @Override
                public void onCancel(View v, Object... objects) {

                }

                @Override
                public void onClick(View v, Object... objects) {

                }
            }, MallDialog.TRANSACTION, ResourceUtils.getText(type == Constants.BundleConfig.TYPE_SELL ?
                    R.string.hint_sell_info : R.string.hint_buy_info));
        }

        if (!transactionDialog.isShowing())
            transactionDialog.show();
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
