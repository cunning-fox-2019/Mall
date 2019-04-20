package com.seven.module_user.ui.fragment.token;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.gyf.barlibrary.ImmersionBar;
import com.seven.lib_common.base.activity.BaseAppCompatActivity;
import com.seven.lib_model.model.user.OrderEntity;
import com.seven.module_user.R;
import com.seven.module_user.R2;
import com.seven.module_user.ui.fragment.order.UserOrderDetailActivity;
import com.seven.module_user.ui.fragment.view.BaseRecyclerView;
import com.seven.module_user.ui.fragment.view.DividerSpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xxxxxxH on 2019/4/10.
 * 我的令牌
 */

public class UserTokenActivity extends BaseAppCompatActivity {
    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.list_view)
    BaseRecyclerView recyclerView;

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
    protected int getContentViewId() {
        statusBar = StatusBar.LIGHT;
        return R.layout.mu_activity_token;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void initBundleData(Intent intent) {
        ImmersionBar.with(this).init();
        ImmersionBar.setTitleBar(this, toolbar);
        List<OrderEntity> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new OrderEntity());
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.init(layoutManager, new BaseQuickAdapter<OrderEntity, BaseViewHolder>(R.layout.item_token_list_layout, list) {
            @Override
            protected void convert(BaseViewHolder helper, OrderEntity item) {

            }
        },false).changeItemDecoration(new DividerSpaceItemDecoration(6))
                .addOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                    }
                });
    }

    @Override
    public void onClick(View view) {

    }

    @OnClick(R2.id.all)
    void checkAll() {
        startActivity(new Intent(mContext, UserTokenListActivity.class));
    }

    @OnClick(R2.id.receive_token_btn)
    void receiveToken() {
        startActivity(new Intent(mContext, UserReceiveTokenActivity.class));
    }

    @OnClick(R2.id.back)
    void back() {
        finish();
    }
}
