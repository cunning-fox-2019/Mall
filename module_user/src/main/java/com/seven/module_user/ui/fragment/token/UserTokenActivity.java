package com.seven.module_user.ui.fragment.token;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.gyf.barlibrary.ImmersionBar;
import com.seven.lib_common.base.activity.BaseAppCompatActivity;
import com.seven.lib_common.base.activity.BaseTitleActivity;
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
    @BindView(R2.id.list_view)
    BaseRecyclerView recyclerView;
    @BindView(R2.id.title)
    TextView textView;

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
        return R.layout.mu_activity_token;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        textView.setText("我的令牌");
    }

    @Override
    protected void initBundleData(Intent intent) {
        List<OrderEntity> list = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.init(layoutManager, new BaseQuickAdapter<OrderEntity, BaseViewHolder>(R.layout.item_token_list_layout, list) {
            @Override
            protected void convert(BaseViewHolder helper, OrderEntity item) {

            }
        }, false).changeItemDecoration(new DividerSpaceItemDecoration(6))
                .setEmptyView(getEmptyView());
    }

    @OnClick(R2.id.all)
    void checkAll() {
        startActivity(new Intent(mContext, UserTokenListActivity.class));
    }

    @OnClick(R2.id.receive_token_btn)
    void receiveToken() {
        startActivity(new Intent(mContext, UserReceiveTokenActivity.class));
    }

    private View getEmptyView() {
        TextView textView = new TextView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER);
        textView.setText("暂无记录");
        return textView;
    }

    @OnClick({R2.id.imgBack})
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgBack) {
            finish();
        }
    }
}
