package com.seven.module_user.ui.fragment.order;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.seven.lib_common.base.fragment.BaseFragment;
import com.seven.lib_model.model.user.OrderEntity;
import com.seven.module_user.R;
import com.seven.module_user.R2;
import com.seven.module_user.ui.fragment.view.BaseRecyclerView;
import com.seven.module_user.ui.fragment.view.DividerSpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by xxxxxxH on 2019/4/9.
 */

public class OrderListFragment extends BaseFragment {

    @BindView(R2.id.list_view)
    BaseRecyclerView recyclerView;

    private String currentListType;

    public static OrderListFragment getInstance(String type) {
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
        List<OrderEntity> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new OrderEntity());
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.init(layoutManager, new BaseQuickAdapter<OrderEntity, BaseViewHolder>(R.layout.item_order_list_layout, list) {
            @Override
            protected void convert(BaseViewHolder helper, OrderEntity item) {
                helper.addOnClickListener(R.id.button_1);
            }
        }).changeItemDecoration(new DividerSpaceItemDecoration(6))
                .addOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                        if (currentListType.equals("wait_pay")) {
                            startActivity(new Intent(getActivity(), UserOrderDetailActivity.class));
                        }
                    }
                })
                .addOnItemChildClickListener(new OnItemChildClickListener() {
                    @Override
                    public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        if (currentListType.equals("wait_pay")) {
                            if (view.getId() == R.id.button_1) {
                                initWaitPay();
                            }
                        }
                    }
                });
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
}
