package com.seven.module_user.ui.fragment.order;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.seven.lib_common.base.fragment.BaseFragment;
import com.seven.lib_model.ApiManager;
import com.seven.lib_model.BaseResult;
import com.seven.lib_model.model.user.OrderEntity;
import com.seven.lib_model.model.user.OrderListRequestEntity;
import com.seven.lib_model.model.user.mine.CommonListPageEntity;
import com.seven.lib_model.model.user.mine.GoodsListBean;
import com.seven.module_user.R;
import com.seven.module_user.R2;
import com.seven.module_user.ui.fragment.view.BaseRecyclerView;
import com.seven.module_user.ui.fragment.view.DividerSpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xxxxxxH on 2019/4/9.
 */

public class OrderListFragment extends BaseFragment {

    @BindView(R2.id.list_view)
    BaseRecyclerView recyclerView;

    private int currentListType;
    private int currentPage = 1;

    public static OrderListFragment getInstance(int type) {
        OrderListFragment fragment = new OrderListFragment();
        fragment.currentListType = type;
        return fragment;
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
    public int getContentViewId() {
        return R.layout.mu_common_list;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        getData();


    }

    @Override
    public void onClick(View view) {

    }

    private void initWaitPay() {
        List<String> cancelReasons = new ArrayList<>();
        cancelReasons.add("原因1");
        cancelReasons.add("原因2");
        cancelReasons.add("原因3");
        cancelReasons.add("原因4");
        OptionsPickerView cancelReasonPickerView = new OptionsPickerBuilder(getActivity(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {

            }
        }).setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                .setSelectOptions(0)//默认选中项
                .setBgColor(Color.WHITE)
                .setTitleBgColor(getResources().getColor(R.color.color_eee))
                .setCancelColor(getResources().getColor(R.color.color_6c))
                .setSubmitColor(getResources().getColor(R.color.color_1e1d1d))
                .setTextColorCenter(getResources().getColor(R.color.color_1e1d1d))
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setOutSideColor(0x00000000) //设置外部遮罩颜色
                .build();
        cancelReasonPickerView.setPicker(cancelReasons);
        cancelReasonPickerView.show();
    }

    private void getData() {
        OrderListRequestEntity entity = new OrderListRequestEntity();
        entity.setPage(currentPage);
        entity.setStatus(currentListType);
        ApiManager.getOrderList(entity)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResult<CommonListPageEntity<OrderEntity>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<CommonListPageEntity<OrderEntity>> commonListPageEntityBaseResult) {
                        setData(commonListPageEntityBaseResult.getData());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void setData(CommonListPageEntity<OrderEntity> data) {
        if (recyclerView.getAdapter() == null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.init(layoutManager, new BaseQuickAdapter<OrderEntity, BaseViewHolder>(R.layout.item_order_list_layout, null) {
                @Override
                protected void convert(BaseViewHolder helper, OrderEntity item) {
                    GoodsListBean goods = item.getGoods_list().get(0);
                    helper.addOnClickListener(R.id.button_1)
                            .addOnClickListener(R.id.pay_btn)
                            .setText(R.id.order_number, "订单号：" + item.getOrder_sn())
                            .setText(R.id.goods_name, goods.getGoods_name())
                            .setText(R.id.goods_money, "￥" + goods.getPrice())
                            .setText(R.id.goods_number, "X" + goods.getNumber())
                            .setText(R.id.order_number_total, "共" + item.getGoods_list().size() + "件商品")
                            .setText(R.id.order_money_total, "合计：￥" + item.getTotal());
                }
            }).setEmptyView(getEmptyView())
                    .setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            OrderListFragment.this.onRefresh();
                        }
                    })
                    .setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                        @Override
                        public void onLoadMoreRequested() {
                            onLoadMore();
                        }
                    })
                    .changeItemDecoration(new DividerSpaceItemDecoration(6))
                    .addOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                            if (currentListType == 1) {
                                startActivity(new Intent(getActivity(), UserOrderDetailActivity.class));
                            }
                        }
                    })
                    .addOnItemChildClickListener(new OnItemChildClickListener() {
                        @Override
                        public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                            if (currentListType == 1) {
                                if (view.getId() == R.id.button_1) {
                                    initWaitPay();
                                }
                            }
                        }
                    });
        }
        if (currentPage == 1) {
            recyclerView.getAdapter().setNewData(data.getItems());
            recyclerView.setRefreshing(false);
        } else {
            recyclerView.addDataList(data.getItems());
            recyclerView.getAdapter().loadMoreComplete();
        }

        if (data.getPagination().getTotal_page() == 1) {
            recyclerView.setEnableLoadMore(false);
        } else {
            recyclerView.setEnableLoadMore(true);
        }
    }

    private View getEmptyView() {
        TextView textView = new TextView(getActivity());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER);
        textView.setText("暂无数据");
        return textView;
    }

    private void onRefresh() {
        currentPage = 1;
        getData();
    }

    private void onLoadMore() {
        currentPage++;
        getData();
    }
}
