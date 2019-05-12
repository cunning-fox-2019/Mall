package com.seven.module_extension.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.seven.lib_common.base.activity.BaseTitleActivity;
import com.seven.lib_model.model.extension.RewardInfoLlistEntity;
import com.seven.lib_model.model.extension.RewardListEntity;
import com.seven.lib_model.presenter.extension.ExActivityPresenter;
import com.seven.lib_router.router.RouterPath;
import com.seven.module_extension.R;
import com.seven.module_extension.R2;
import com.seven.module_extension.ui.adapter.RewardInfoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = RouterPath.ACTIVITY_REWARD_LIST)
public class ReceivAwardsActivity extends BaseTitleActivity {
    @Autowired(name = "id")
    int id = 0;
    @BindView(R2.id.me_lingqu_rv)
    RecyclerView meLingquRv;
    @BindView(R2.id.me_lingqu_refresh)
    SwipeRefreshLayout meLingquRefresh;

    private ExActivityPresenter presenter;

    private List<RewardInfoLlistEntity> rewardList;

    private RewardInfoAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.me_acitvity_lingqu_lingpai;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //setTitleText(R.string);i
        presenter = new ExActivityPresenter(this,this);
    }

    @Override
    protected void rightTextBtnClick(View v) {

    }

    @Override
    protected void rightBtnClick(View v) {

    }

    @Override
    protected void initBundleData(Intent intent) {
        if (intent == null)intent=getIntent();
        id =intent.getIntExtra("id",-1);
        setRv();
        request(id);
    }

    private void setRv(){
        adapter = new RewardInfoAdapter(R.layout.me_item_lingqu,rewardList);
        meLingquRv.setLayoutManager(new LinearLayoutManager(mContext));
        meLingquRv.setAdapter(adapter);
    }

    private void request(int id){
        presenter.rewardInfo(1,id);
    }

    @Override
    public void result(int code, Boolean hasNextPage, String response, Object object) {
        super.result(code, hasNextPage, response, object);
        if (code == 1){
            if (object == null)return;
            rewardList = new ArrayList<>();
            rewardList = (List<RewardInfoLlistEntity>) object;
            if (rewardList.size()>0){
                adapter.setNewData(rewardList);
            }
        }
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

}
