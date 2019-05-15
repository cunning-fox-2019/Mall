package com.seven.module_extension.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;
import com.seven.lib_common.base.activity.BaseTitleActivity;
import com.seven.lib_common.stextview.TypeFaceView;
import com.seven.lib_common.utils.glide.GlideUtils;
import com.seven.lib_model.model.extension.MyInterViewEntity;
import com.seven.lib_model.model.extension.ParentInfo;
import com.seven.lib_model.model.user.UserEntity;
import com.seven.lib_model.presenter.extension.ExActivityPresenter;
import com.seven.lib_router.db.shard.SharedData;
import com.seven.lib_router.router.RouterPath;
import com.seven.module_extension.R;
import com.seven.module_extension.R2;
import com.seven.module_extension.ui.adapter.MyInviteAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = RouterPath.ACTIVITY_MY_INTERVIEW)
public class MyInterviewActivity extends BaseTitleActivity {


    @BindView(R2.id.me_rv_myinterview)
    RecyclerView meRvMyinterview;
    private ExActivityPresenter presenter;
    private List<MyInterViewEntity> interViewList;
    private MyInviteAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.me_acitvity_myinterview;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        statusBar = StatusBar.LIGHT;
        setTitleText(R.string.me_my_interview_title);
    }

    @Override
    public void result(int code, Boolean hasNextPage, String response, Object object) {
        super.result(code, hasNextPage, response, object);
        if (code == 1) {
            if (object == null) return;
            interViewList = new ArrayList<>();
            MyInterViewEntity entity = (MyInterViewEntity) object;
            interViewList.add(entity);
            setRv(interViewList);
        }
    }

    private void setRv(List<MyInterViewEntity> list) {
        adapter = new MyInviteAdapter(R.layout.me_item_myinterview, list.get(0).getItems());
        meRvMyinterview.setLayoutManager(new LinearLayoutManager(mContext));
        meRvMyinterview.setAdapter(adapter);
        adapter.addHeaderView(headerView(list.get(0).getParent_info()));
    }

    @Override
    protected void rightTextBtnClick(View v) {

    }

    @Override
    protected void rightBtnClick(View v) {

    }

    @Override
    protected void initBundleData(Intent intent) {
        UserEntity userEntity = new Gson().fromJson(SharedData.getInstance().getUserInfo(), UserEntity.class);
        int userId = userEntity.getId();
        presenter = new ExActivityPresenter(this, this);
        presenter.invite(1, userId, 1, 20);

    }

    private View headerView(ParentInfo data) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.me_header_myinvite, null);
        ImageView me_headr_myinterview_iv = view.findViewById(R.id.me_headr_myinterview_iv);
        TypeFaceView me_headr_interview_name = view.findViewById(R.id.me_headr_interview_name);
        ImageView me_headr_interview_sex = view.findViewById(R.id.me_headr_interview_sex);
        ImageView me_headr_interview_level = view.findViewById(R.id.me_headr_interview_level);
        GlideUtils.loadCircleImage(mContext, data.getAvatar(), me_headr_myinterview_iv);
        me_headr_interview_name.setText(data.getUsername());
        if (data.getSex().equals("male")) {

        } else {

        }
        switch (data.getRole()) {
            case 0:
                me_headr_interview_level.setBackground(mContext.getResources().getDrawable(R.drawable.me_normaluser));
                break;
            case 1:
                me_headr_interview_level.setBackground(mContext.getResources().getDrawable(R.drawable.me_vip));
                break;
            case 2:
                me_headr_interview_level.setBackground(mContext.getResources().getDrawable(R.drawable.me_kuangzhu));
                break;

            case 3:
                me_headr_interview_level.setBackground(mContext.getResources().getDrawable(R.drawable.me_changzhu));
                break;

            case 4:
                me_headr_interview_level.setBackground(mContext.getResources().getDrawable(R.drawable.ctylord));
                break;
            default:
        }
        return view;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
