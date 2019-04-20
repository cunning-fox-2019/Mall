package com.seven.module_user.ui.fragment.token;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.seven.lib_common.base.activity.BaseAppCompatActivity;
import com.seven.lib_common.base.fragment.BaseFragment;
import com.seven.lib_model.model.user.OrderEntity;
import com.seven.module_user.R;
import com.seven.module_user.R2;
import com.seven.module_user.ui.fragment.order.OrderListFragment;
import com.seven.module_user.ui.fragment.order.UserOrderDetailActivity;
import com.seven.module_user.ui.fragment.view.BaseRecyclerView;
import com.seven.module_user.ui.fragment.view.DividerSpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by xxxxxxH on 2019/4/10.
 */

public class TokenListFragment extends BaseFragment {

    @BindView(R2.id.list_view)
    BaseRecyclerView recyclerView;

    private String currentListType;

    public static TokenListFragment getInstance(String type) {
        TokenListFragment fragment = new TokenListFragment();
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
    public void onClick(View view) {

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
        recyclerView.init(layoutManager, new BaseQuickAdapter<OrderEntity, BaseViewHolder>(R.layout.item_token_list_layout, list) {
            @Override
            protected void convert(BaseViewHolder helper, OrderEntity item) {

            }
        }).changeItemDecoration(new DividerSpaceItemDecoration(6))
                .addOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                    }
                });
    }
}
