package com.seven.module_model.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.seven.lib_common.base.activity.BaseTitleActivity;
import com.seven.lib_common.utils.NetWorkUtils;
import com.seven.lib_common.utils.ResourceUtils;
import com.seven.lib_common.utils.ToastUtils;
import com.seven.lib_model.model.model.MessageEntity;
import com.seven.lib_opensource.application.SSDK;
import com.seven.lib_router.router.RouterPath;
import com.seven.module_model.R;
import com.seven.module_model.R2;
import com.seven.module_model.adapter.MessageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auhtor seven
 * email:frankchenlei@163.com
 * 2019/4/19
 */
@Route(path = RouterPath.ACTIVITY_MESSAGE)
public class MessageActivity extends BaseTitleActivity implements BaseQuickAdapter.OnItemClickListener {

    private int page = 1;
    private int pageSize = 10;
    public boolean isRefresh;
    private boolean isMoreEnd;

    @BindView(R2.id.swipe_refresh)
    public SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R2.id.recycler_view)
    public RecyclerView recyclerView;
    public MessageAdapter adapter;
    private List<MessageEntity> messageList;

    @Override
    protected int getLayoutId() {
        return R.layout.mm_activity_message;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        setTitleText(R.string.label_message);

    }

    @Override
    protected void initBundleData(Intent intent) {

        setRecyclerView();

    }

    private void request(int page) {

    }

    private void setRecyclerView() {

        messageList = new ArrayList<>();
        MessageEntity messageEntity = null;

        for (int i = 0; i < 20; i++) {
            messageEntity = new MessageEntity();
            messageEntity.setRead(i % 2 == 0);
            messageEntity.setContent("内容"+i);
            messageEntity.setTime(System.currentTimeMillis());
            messageList.add(messageEntity);
        }

        adapter = new MessageAdapter(R.layout.mh_item_message, messageList);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        }, recyclerView);
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(SSDK.getInstance().getContext()));
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
            ToastUtils.showToast(mContext, ResourceUtils.getText(R.string.hint_more_not));
            return;
        }
        page++;
        request(page);
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
