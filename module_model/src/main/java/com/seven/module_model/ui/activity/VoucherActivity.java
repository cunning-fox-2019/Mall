package com.seven.module_model.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.seven.lib_common.base.activity.BaseTitleActivity;
import com.seven.lib_common.utils.NetWorkUtils;
import com.seven.lib_common.utils.ResourceUtils;
import com.seven.lib_common.utils.ToastUtils;
import com.seven.lib_model.model.model.OrderEntity;
import com.seven.lib_opensource.application.SSDK;
import com.seven.lib_router.router.RouterPath;
import com.seven.module_model.R;
import com.seven.module_model.R2;
import com.seven.module_model.adapter.OrderAdapter;
import com.seven.module_model.widget.decoration.VoucherDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auhtor seven
 * email:frankchenlei@163.com
 * 2019/4/19
 */
@Route(path = RouterPath.ACTIVITY_VOUCHER)
public class VoucherActivity extends BaseTitleActivity implements BaseQuickAdapter.OnItemClickListener {

    @BindView(R2.id.sell_btn)
    public RelativeLayout sellBtn;
    @BindView(R2.id.sell_iv)
    public ImageView sellIv;
    @BindView(R2.id.buy_btn)
    public RelativeLayout buyBtn;
    @BindView(R2.id.buy_iv)
    public ImageView buyIv;

    @BindView(R2.id.all_btn)
    public RelativeLayout allBtn;
    @BindView(R2.id.all_iv)
    public ImageView allIv;
    @BindView(R2.id.upload_btn)
    public RelativeLayout uploadBtn;
    @BindView(R2.id.upload_iv)
    public ImageView uploadIv;
    @BindView(R2.id.sure_btn)
    public RelativeLayout sureBtn;
    @BindView(R2.id.sure_iv)
    public ImageView sureIv;
    @BindView(R2.id.end_btn)
    public RelativeLayout endBtn;
    @BindView(R2.id.end_iv)
    public ImageView endIv;

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
    protected int getLayoutId() {
        return R.layout.mm_activity_voucher;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        setTitleText(R.string.label_voucher);

    }

    @Override
    protected void initBundleData(Intent intent) {

        selectTab(sellBtn);
        selectTabSecond(allBtn);

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

    private void selectTabSecond(View view) {

        if (!view.isSelected()) {
            allBtn.setSelected(allBtn == view);
            uploadBtn.setSelected(uploadBtn == view);
            sureBtn.setSelected(sureBtn == view);
            endBtn.setSelected(endBtn == view);

            allIv.setVisibility(allBtn.isSelected() ? View.VISIBLE : View.GONE);
            uploadIv.setVisibility(uploadBtn.isSelected() ? View.VISIBLE : View.GONE);
            sureIv.setVisibility(sureBtn.isSelected() ? View.VISIBLE : View.GONE);
            endIv.setVisibility(endBtn.isSelected() ? View.VISIBLE : View.GONE);
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
        recyclerView.addItemDecoration(new VoucherDecoration());
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

    public void btClick(View view) {

        if (view.getId() == R.id.sell_btn)
            selectTab(sellBtn);

        if (view.getId() == R.id.buy_btn)
            selectTab(buyBtn);

        if (view.getId() == R.id.all_btn)
            selectTabSecond(allBtn);

        if (view.getId() == R.id.upload_btn)
            selectTabSecond(uploadBtn);

        if (view.getId() == R.id.sure_btn)
            selectTabSecond(sureBtn);

        if (view.getId() == R.id.end_btn)
            selectTabSecond(endBtn);
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

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
