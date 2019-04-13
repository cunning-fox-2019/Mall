package com.seven.module_user.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gyf.barlibrary.ImmersionBar;
import com.seven.lib_common.base.activity.BaseActivity;
import com.seven.lib_model.model.user.UserEntity;
import com.seven.module_user.R;
import com.seven.module_user.R2;
import com.seven.module_user.ui.fragment.view.BaseRecyclerView;
import com.seven.module_user.ui.fragment.view.CustomToolbar;
import com.seven.module_user.ui.fragment.view.DividerSpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by xxxxxxH on 2019/3/31.
 */

public class CollectionActivity extends BaseActivity {
    @BindView(R2.id.toolbar)
    public CustomToolbar mToolBar;
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
        statusBar = StatusBar.HIDE;
        return R.layout.mu_activity_collect;
    }


    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void initBundleData(Intent intent) {
        ImmersionBar.with(this).init();
        ImmersionBar.setTitleBar(this, mToolBar);
        List<UserEntity> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new UserEntity());
        }
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        recyclerView.init(manager, new BaseQuickAdapter<UserEntity, BaseViewHolder>(R.layout.item_store_up_layout, list) {

            @Override
            protected void convert(BaseViewHolder helper, UserEntity item) {

            }
        }).changeItemDecoration(new DividerSpaceItemDecoration(4));
    }

    @Override
    public void onClick(View view) {

    }
}
