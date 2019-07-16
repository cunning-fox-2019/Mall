package com.seven.module_extension.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.seven.lib_common.base.activity.BaseTitleActivity;
import com.seven.lib_common.listener.OnClickListener;
import com.seven.lib_common.stextview.TypeFaceView;
import com.seven.lib_common.utils.glide.GlideUtils;
import com.seven.lib_model.model.extension.ItemsBean;
import com.seven.lib_model.model.extension.MyInterViewEntity;
import com.seven.lib_model.model.extension.ParentInfo;
import com.seven.lib_model.model.user.UserEntity;
import com.seven.lib_model.presenter.extension.ExActivityPresenter;
import com.seven.lib_router.db.shard.SharedData;
import com.seven.lib_router.router.RouterPath;
import com.seven.module_extension.R;
import com.seven.module_extension.R2;
import com.seven.module_extension.ui.adapter.MyInviteAdapter;
import com.seven.module_extension.ui.dialog.ShareDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.http.Body;

@Route(path = RouterPath.ACTIVITY_MY_INTERVIEW)
public class MyInterviewActivity extends BaseTitleActivity {

    @Autowired(name = "id")
    String id = "";
    @Autowired(name = "name")
    String name = "";

    @BindView(R2.id.me_rv_myinterview)
    RecyclerView meRvMyinterview;
    @BindView(R2.id.me_empty)
    TextView me_empty;
    @BindView(R2.id.me_invite_btn)
    Button me_invite_btn;
    private ExActivityPresenter presenter;
    private List<MyInterViewEntity> interViewList;
    private MyInviteAdapter adapter;
    ShareDialog dialog;
    private String userId = "";

    @Override
    protected int getLayoutId() {
        return R.layout.me_acitvity_myinterview;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        statusBar = StatusBar.LIGHT;

        me_invite_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog == null) {
                    dialog = new ShareDialog(MyInterviewActivity.this, R.style.Dialog, null);
                }
                if (!dialog.isShowing())
                    dialog.showDialog(0, -screenHeight);
            }
        });
    }

    @Override
    public void result(int code, Boolean hasNextPage, String response, Object object) {
        super.result(code, hasNextPage, response, object);
        if (code == 1) {
            if (object == null) return;
            interViewList = new ArrayList<>();
            MyInterViewEntity entity = (MyInterViewEntity) object;
            if (entity != null && entity.getParent_info() != null) {
                interViewList.add(entity);
                setRv(interViewList);
            } else {
                if (entity.getItems().size() > 0) {
                    interViewList.add(entity);
                    setRv(interViewList);
                    me_empty.setVisibility(View.GONE);
                    meRvMyinterview.setVisibility(View.VISIBLE);
                } else {
                    me_empty.setVisibility(View.VISIBLE);
                    meRvMyinterview.setVisibility(View.GONE);
                }
            }


        }
    }


    private void setRv(List<MyInterViewEntity> list) {
        adapter = new MyInviteAdapter(R.layout.me_item_myinterview, list.get(0).getItems());
        meRvMyinterview.setLayoutManager(new LinearLayoutManager(mContext));
        meRvMyinterview.setAdapter(adapter);
        adapter.addHeaderView(headerView(list.get(0).getParent_info()));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<ItemsBean> list1 = adapter.getData();
                int id = list1.get(position).getId();
                name = list1.get(position).getUsername();
                ARouter.getInstance().build(RouterPath.ACTIVITY_MY_INTERVIEW)
                        .withString("id", id + "")
                        .withString("name", name)
                        .navigation();
            }
        });
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
        userId = String.valueOf(userEntity.getId());
        if (intent == null) intent = getIntent();
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        if (TextUtils.isEmpty(name))
            setTitleText("我的团队");
        else
            setTitleText(name + "的团队");
        presenter = new ExActivityPresenter(this, this);
        if (TextUtils.isEmpty(id)) {
            return;
        } else {
            presenter.invite(1, Integer.parseInt(id), 1, 20);
        }


    }

    private View headerView(ParentInfo data) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.me_header_myinvite, null);
        ImageView me_headr_myinterview_iv = view.findViewById(R.id.me_headr_myinterview_iv);
        TypeFaceView me_headr_interview_name = view.findViewById(R.id.me_headr_interview_name);
        TypeFaceView me_headr_interview_my_leader = view.findViewById(R.id.me_headr_interview_my_leader);
        TypeFaceView me_headr_interview_mazai = view.findViewById(R.id.me_headr_interview_mazai);
        ImageView me_headr_interview_sex = view.findViewById(R.id.me_headr_interview_sex);
        ImageView me_headr_interview_level = view.findViewById(R.id.me_headr_interview_level);
        if (data != null) {
            if (!TextUtils.isEmpty(name)) {
                me_headr_interview_my_leader.setText(name + "的上级");
                me_headr_interview_mazai.setText(name + "的下级");
            }
            GlideUtils.loadCircleImage(mContext, data.getAvatar(), me_headr_myinterview_iv);
            me_headr_interview_name.setText(data.getUsername());
            if (data.getSex() != null) {
                if (data.getSex().equals("male")) {
                    me_headr_interview_sex.setBackgroundResource(R.drawable.me_male);
                } else {
                    me_headr_interview_sex.setBackgroundResource(R.drawable.me_famale);
                }
            }
            if (String.valueOf(data.getRole()) != null) {
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
            }
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
