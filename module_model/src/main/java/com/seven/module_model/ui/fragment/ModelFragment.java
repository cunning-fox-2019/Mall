package com.seven.module_model.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.seven.lib_common.base.fragment.BaseFragment;
import com.seven.lib_common.utils.NetWorkUtils;
import com.seven.lib_common.utils.ResourceUtils;
import com.seven.lib_common.utils.ToastUtils;
import com.seven.lib_model.model.model.OrderEntity;
import com.seven.lib_opensource.application.SSDK;
import com.seven.lib_router.Constants;
import com.seven.lib_router.router.RouterPath;
import com.seven.lib_router.router.RouterUtils;
import com.seven.module_model.R;
import com.seven.module_model.R2;
import com.seven.module_model.adapter.OrderAdapter;
import com.seven.module_model.widget.decoration.OrderDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auhtor seven
 * email:frankchenlei@163.com
 * 2019/3/26
 */
@Route(path = RouterPath.FRAGMENT_MODEL)
public class ModelFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {

    @BindView(R2.id.title_rl)
    public RelativeLayout titleRl;

    @BindView(R2.id.buy_btn)
    public RelativeLayout buyBtn;
    @BindView(R2.id.buy_iv)
    public ImageView buyIv;
    @BindView(R2.id.sell_btn)
    public RelativeLayout sellBtn;
    @BindView(R2.id.sell_iv)
    public ImageView sellIv;

    @BindView(R2.id.demand_btn)
    public LinearLayout demandBtn;
    @BindView(R2.id.message_btn)
    public LinearLayout messageBtn;
    @BindView(R2.id.voucher_btn)
    public LinearLayout voucherBtn;

    @BindView(R2.id.sort_time_rl)
    public RelativeLayout sortTime;
    @BindView(R2.id.sort_price_rl)
    public RelativeLayout sortPrice;

    private int page = 1;
    private int pageSize = 10;
    public boolean isRefresh;
    private boolean isMoreEnd;
    @BindView(R2.id.swipe_refresh)
    public SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R2.id.recycler_view)
    public RecyclerView recyclerView;
    private OrderAdapter adapter;
    private List<OrderEntity> orderList;

    @Override
    public int getContentViewId() {
        return R.layout.mm_fragment_model;
    }

    @Override
    public void init(Bundle savedInstanceState) {

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) titleRl.getLayoutParams();
        params.topMargin = notificationHeight;
        titleRl.setLayoutParams(params);

        buyBtn.setOnClickListener(this);
        sellBtn.setOnClickListener(this);
        sortTime.setOnClickListener(this);
        sortPrice.setOnClickListener(this);
        demandBtn.setOnClickListener(this);
        messageBtn.setOnClickListener(this);
        voucherBtn.setOnClickListener(this);

        selectTab(buyBtn);
        selectSort(sortTime);

        setRecyclerView();
    }

    private void selectTab(View view) {

        if (!view.isSelected()) {
            buyBtn.setSelected(buyBtn == view);
            sellBtn.setSelected(sellBtn == view);

            buyIv.setVisibility(buyBtn.isSelected() ? View.VISIBLE : View.GONE);
            sellIv.setVisibility(sellBtn.isSelected() ? View.VISIBLE : View.GONE);
        }
    }

    private void selectSort(View view) {
        if (!view.isSelected()) {
            sortTime.setSelected(sortTime == view);
            sortPrice.setSelected(sortPrice == view);
        }
    }

    private void request(int page) {

    }

    private void setRecyclerView() {

        orderList = new ArrayList<>();
        OrderEntity orderEntity = null;

        for (int i = 0; i < 10; i++) {
            orderEntity = new OrderEntity();
            orderEntity.setAvatar("http://b-ssl.duitang.com/uploads/item/201201/08/20120108130517_Ra8f2.jpg");
            orderEntity.setName("张三" + i);
            orderEntity.setPayType(1);
            orderEntity.setPrice(10400.00);
            orderEntity.setRatio(92);
            orderEntity.setStatus(1);
            orderEntity.setVolume(10);
            orderEntity.setToken(90.00);
            orderList.add(orderEntity);
        }

        adapter = new OrderAdapter(R.layout.mm_item_order, orderList);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        }, recyclerView);
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(SSDK.getInstance().getContext()));
        recyclerView.addItemDecoration(new OrderDecoration());
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setColorSchemeResources(
                R.color.refresh_1,
                R.color.refresh_2,
                R.color.refresh_3,
                R.color.refresh_4);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!NetWorkUtils.isNetWork()) {
                    swipeRefreshLayout.setRefreshing(false);
                    return;
                }
                isRefresh = true;
                page = 1;
                request(page);
            }
        });
    }

    private void loadMore() {

        if (isMoreEnd) {
            ToastUtils.showToast(SSDK.getInstance().getContext(), ResourceUtils.getText(R.string.hint_more_not));
            return;
        }
        page++;
        request(page);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.buy_btn)
            selectTab(buyBtn);

        if (v.getId() == R.id.sell_btn)
            selectTab(sellBtn);

        if (v.getId() == R.id.demand_btn)
            RouterUtils.getInstance().routerNormal(RouterPath.ACTIVITY_RELEASE_DEMAND);

        if (v.getId() == R.id.message_btn)
            RouterUtils.getInstance().routerNormal(RouterPath.ACTIVITY_MESSAGE);

        if (v.getId() == R.id.voucher_btn)
            RouterUtils.getInstance().routerNormal(RouterPath.ACTIVITY_VOUCHER);

        if (v.getId() == R.id.sort_time_rl)
            selectSort(sortTime);

        if (v.getId() == R.id.sort_price_rl)
            selectSort(sortPrice);

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

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


        ARouter.getInstance().build(RouterPath.ACTIVITY_TRANSACTION_DETAILS)
                .withInt(Constants.BundleConfig.TYPE, buyBtn.isSelected() ?
                        Constants.BundleConfig.TYPE_BUY : Constants.BundleConfig.TYPE_SELL)
                .navigation();

    }
}
