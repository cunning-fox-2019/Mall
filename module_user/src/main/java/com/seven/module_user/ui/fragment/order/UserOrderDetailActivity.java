package com.seven.module_user.ui.fragment.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seven.lib_common.base.activity.BaseTitleActivity;
import com.seven.lib_common.utils.glide.GlideUtils;
import com.seven.lib_model.ApiManager;
import com.seven.lib_model.BaseResult;
import com.seven.lib_model.model.user.mine.GoodsListBean;
import com.seven.lib_model.model.user.mine.OrderDetailEntity;
import com.seven.lib_model.model.user.mine.OrderDetailRequestEntity;
import com.seven.lib_router.router.RouterPath;
import com.seven.module_user.R;
import com.seven.module_user.R2;
import com.seven.module_user.ui.fragment.view.BaseRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xxxxxxH on 2019/4/9.
 */
@Route(path = RouterPath.ACTIVITY_MINE_SHOP_PAY)
public class UserOrderDetailActivity extends BaseTitleActivity {
    @Autowired(name = "order_id")
    String orderId;
    @BindView(R2.id.address_icon)
    ImageView addressIcon;
    @BindView(R2.id.name_and_phone)
    TextView nameAndPhone;
    @BindView(R2.id.address_tx)
    TextView addressTx;
    @BindView(R2.id.list_view)
    BaseRecyclerView listView;
    @BindView(R2.id.express_fee)
    TextView expressFee;
    @BindView(R2.id.order_money)
    TextView orderMoney;
    @BindView(R2.id.pay_money)
    TextView payMoney;
    @BindView(R2.id.order_number)
    TextView orderNumber;
    @BindView(R2.id.order_time)
    TextView orderTime;
    @BindView(R2.id.order_state)
    TextView orderState;
    @BindView(R2.id.total_money)
    TextView totalMoney;
    @BindView(R2.id.cancel_order)
    TextView cancelOrder;
    @BindView(R2.id.pay_order)
    TextView payOrder;

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
        return R.layout.mu_activity_order_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleText(R.string.user_order_detail);

    }

    @Override
    protected void rightTextBtnClick(View v) {

    }

    @Override
    protected void rightBtnClick(View v) {

    }

    @Override
    protected void initBundleData(Intent intent) {
        if (intent == null) {
            intent = getIntent();
            orderId = intent.getStringExtra("order_id");
            if (!TextUtils.isEmpty(orderId)) {
                getData();
            }
        }
    }

    private void getData() {
        OrderDetailRequestEntity entity = new OrderDetailRequestEntity(Integer.valueOf(orderId));
        ApiManager.getOrderDetailInfo(entity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult<OrderDetailEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<OrderDetailEntity> orderDetailEntityBaseResult) {
                        setData(orderDetailEntityBaseResult.getData());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void setData(OrderDetailEntity data) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        listView.init(layoutManager, new BaseQuickAdapter<GoodsListBean, BaseViewHolder>(R.layout.item_order_detail_goods, data.getGoods_list()) {

            @Override
            protected void convert(BaseViewHolder helper, GoodsListBean item) {
                ImageView imageView = helper.getView(R.id.goods_img);
                helper.setText(R.id.goods_name, item.getGoods_name())
                        .setText(R.id.goods_number, "X" + item.getNumber())
                        .setText(R.id.goods_money, "￥" + item.getPrice());
                GlideUtils.loadImage(mContext, item.getGoods_thumb(), imageView, true);
            }
        }, false)
                .removeItemDecoration();

        addressTx.setText(data.getAddress());
        nameAndPhone.setText(data.getContact_name()+"  "+data.getContact_phone());
        orderNumber.setText(data.getOrder_sn());
        payMoney.setText("￥"+data.getTotal());
        orderMoney.setText("￥"+data.getTotal());
        totalMoney.setText("待支付：￥"+data.getTotal());

    }
}
