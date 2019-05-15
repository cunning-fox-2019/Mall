package com.seven.module_extension.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.seven.lib_common.base.activity.BaseTitleActivity;
import com.seven.lib_common.stextview.TypeFaceView;
import com.seven.lib_model.ApiManager;
import com.seven.lib_model.BaseResult;
import com.seven.lib_model.model.extension.BdGoodsEntity;
import com.seven.lib_model.model.extension.GoodsItemEntity;
import com.seven.lib_model.model.home.OrderEntity;
import com.seven.lib_model.model.user.UserEntity;
import com.seven.lib_model.model.user.mine.AddressEntity;
import com.seven.lib_model.presenter.extension.ExActivityPresenter;
import com.seven.lib_opensource.event.Event;
import com.seven.lib_opensource.event.ObjectsEvent;
import com.seven.lib_router.Constants;
import com.seven.lib_router.db.shard.SharedData;
import com.seven.lib_router.router.RouterPath;
import com.seven.lib_router.router.RouterUtils;
import com.seven.module_extension.R;
import com.seven.module_extension.R2;
import com.seven.module_extension.ui.adapter.BuyBdAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Route(path = RouterPath.ACTIVITY_BUY_BD)
public class BuyActivity extends BaseTitleActivity {
    @Autowired(name = Constants.BundleConfig.EVENT_CODE)
    int code = 0;

    @BindView(R2.id.me_buy_bd_rv)
    RecyclerView meBuyBdRv;
    @BindView(R2.id.me_buy_price)
    TypeFaceView me_buy_price;
    @BindView(R2.id.me_buy_bd_address1)
    TypeFaceView meBuyBdAddress1;
    @BindView(R2.id.me_buy_bd_address2)
    TypeFaceView meBuyBdAddress2;
    @BindView(R2.id.me_buy_bd_ll)
    LinearLayout meBuyBdLl;
    @BindView(R2.id.me_buy_bd_btn)
    TypeFaceView meBuyBdBtn;
    private BuyBdAdapter adapter;
    private String shopIds = "";
    ExActivityPresenter presenter;
    UserEntity entity;
    private OrderEntity orderEntity;

    @Override
    protected int getLayoutId() {
        return R.layout.me_activity_buy_bd;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.me_buy_bd_ll) {
            ARouter.getInstance()
                    .build(RouterPath.ACTIVITY_ADDRESS)
                    .withInt(Constants.BundleConfig.EVENT_CODE, 159357)
                    .navigation();
        }
        if (view.getId() == R.id.me_buy_bd_btn) {
            if (!TextUtils.isEmpty(meBuyBdAddress1.getText().toString()) && !TextUtils.isEmpty(meBuyBdAddress2.getText().toString())){
                //EventBus.getDefault().post(new ObjectsEvent(code, shopIds));
                presenter.getOrder(1,entity.getId());
                //RouterUtils.getInstance().routerNormal(RouterPath.ACTIVITY_PAY);
            }
        }
    }

    @Override
    public void result(int code, Boolean hasNextPage, String response, Object object) {
        super.result(code, hasNextPage, response, object);
        if (code == 1){
            if (object == null)return;
            orderEntity = (OrderEntity) object;
            if (orderEntity != null){
                OrderEntity newOrder = new OrderEntity();
                newOrder.setOrder_sn(orderEntity.getOrder_sn());
                newOrder.setTotal(orderEntity.getTotal());
                newOrder.setSubject(orderEntity.getSubject());
                RouterUtils.getInstance().routerWithSerializable(RouterPath.ACTIVITY_PAY,Constants.BundleConfig.ENTITY,newOrder);
            }
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        statusBar = StatusBar.LIGHT;
        EventBus.getDefault().register(this);
        presenter = new ExActivityPresenter(this,this);
        String userInfo = SharedData.getInstance().getUserInfo();
        entity = new Gson().fromJson(userInfo,UserEntity.class);
        setTitleText(R.string.me_buy_bd_title);
        meBuyBdLl.setOnClickListener(this);
        meBuyBdBtn.setOnClickListener(this);
        ApiManager.getBdGoods()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResult<BdGoodsEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<BdGoodsEntity> bdGoodsEntityBaseResult) {
                        Log.e("xxxxxxH", bdGoodsEntityBaseResult.getData().getGoods_list() + "");
                        me_buy_price.setText("总价：" + bdGoodsEntityBaseResult.getData().getPrice());
                        initRv(bdGoodsEntityBaseResult.getData().getGoods_list());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initRv(List<GoodsItemEntity> list) {
        adapter = new BuyBdAdapter(R.layout.me_item_buybd, list);
        meBuyBdRv.setLayoutManager(new LinearLayoutManager(mContext));
        meBuyBdRv.setAdapter(adapter);
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

    @SuppressLint("SetTextI18n")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Event event) {
        if (event.getWhat() == Constants.EventConfig.BUY_BD) {
            AddressEntity entity = (AddressEntity) ((ObjectsEvent) event).getObjects()[0];
            meBuyBdAddress1.setText(entity.getContact_name() + " " + entity.getContact_phone());
            meBuyBdAddress2.setText(entity.getProvince_name() + entity.getCity_name() + entity.getDistrict_name() + entity.getAddress());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
