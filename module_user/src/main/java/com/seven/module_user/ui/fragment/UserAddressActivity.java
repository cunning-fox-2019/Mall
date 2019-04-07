package com.seven.module_user.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gyf.barlibrary.ImmersionBar;
import com.seven.lib_common.base.activity.BaseAppCompatActivity;

import com.seven.lib_model.model.user.AddressEntity;
import com.seven.module_user.R;
import com.seven.module_user.R2;
import com.seven.module_user.ui.fragment.view.BaseRecyclerView;

import butterknife.BindView;
import butterknife.OnClick;


public class UserAddressActivity extends BaseAppCompatActivity {
    @BindView(R2.id.toolbar)
    Toolbar mToolBar;
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
        return R.layout.mu_activity_address;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ImmersionBar.with(this).init();
    }

    @Override
    protected void initBundleData(Intent intent) {
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("地址管理");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.init(layoutManager, new BaseQuickAdapter<AddressEntity, BaseViewHolder>(R.layout.item_address_layout, null) {
            @Override
            protected void convert(BaseViewHolder helper, AddressEntity item) {

            }
        }, false)
                .setEmptyView(getEmptyView())
                .removeItemDecoration();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.empty_add_address) {
            startActivity(new Intent(mContext, UserCreateAddressActivity.class));
        }

    }

    private View getEmptyView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.empty_address_layout, null);
        view.findViewById(R.id.empty_add_address).setOnClickListener(this);
        return view;
    }

    @OnClick(R2.id.add_address)
    void addAddress(){
        startActivity(new Intent(mContext, UserCreateAddressActivity.class));
    }
}
