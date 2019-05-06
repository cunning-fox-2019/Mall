package com.seven.module_extension.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.seven.lib_common.base.fragment.BaseFragment;
import com.seven.lib_common.listener.OnClickListener;
import com.seven.lib_common.stextview.TypeFaceView;
import com.seven.lib_common.utils.ScreenUtils;
import com.seven.lib_model.ApiManager;
import com.seven.lib_model.BaseResult;
import com.seven.lib_model.model.extension.ReceiveGoodsEntity;
import com.seven.lib_model.model.extension.RewardRuleEntity;
import com.seven.lib_model.presenter.extension.ExFragmentPresenter;
import com.seven.lib_router.router.RouterPath;
import com.seven.lib_router.router.RouterUtils;
import com.seven.module_extension.R;
import com.seven.module_extension.R2;
import com.seven.module_extension.ui.adapter.RewardRuleAdapter;
import com.seven.module_extension.ui.dialog.SelectUserTypeDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @auhtor seven
 * email:frankchenlei@163.com
 * 2019/3/26
 */

@Route(path = RouterPath.FRAGMENT_EXTENSION)
public class ExtensionFragment extends BaseFragment {


    @BindView(R2.id.me_userlevel)
    ImageView meUserlevel;
    @BindView(R2.id.me_profit_details)
    TypeFaceView meProfitDetails;
    @BindView(R2.id.me_profit_num)
    TypeFaceView meProfitNum;
    @BindView(R2.id.me_title)
    LinearLayout meTitle;
    @BindView(R2.id.me_tab)
    LinearLayout meTab;
    @BindView(R2.id.me_buy_receive)
    TypeFaceView meBuyReceive;
    @BindView(R2.id.me_buy_arrow)
    ImageView meBuyArrow;
    @BindView(R2.id.me_buy_bd)
    RelativeLayout meBuyBd;
    @BindView(R2.id.me_interviewpeo)
    TypeFaceView meInterviewpeo;
    @BindView(R2.id.me_inyerview_arrow)
    ImageView meInyerviewArrow;
    @BindView(R2.id.me_buy_interview)
    RelativeLayout meBuyInterview;
    @BindView(R2.id.me_rv_everyreward)
    RecyclerView meRvEveryreward;
    @BindView(R2.id.me_rv_rewardrules)
    RecyclerView meRvRewardrules;
    @BindView(R2.id.me_content)
    LinearLayout meContent;
    @BindView(R2.id.me_title_right)
    ImageView meTitleRight;
    @BindView(R2.id.me_buy_up_rl)
    RelativeLayout me_buy_up_rl;
    @BindView(R2.id.me_ext_up_rl)
    RelativeLayout me_ext_up_rl;
    @BindView(R2.id.me_rv_slice)
    TextView me_rv_slice;
    @BindView(R2.id.me_reward_tv1)
    TextView meRewardTv1;
    @BindView(R2.id.me_reward_tv1_1)
    TextView meRewardTv11;
    @BindView(R2.id.me_reward_tv1_2)
    TextView meRewardTv12;
    @BindView(R2.id.me_reward_rl1)
    RelativeLayout meRewardRl1;
    @BindView(R2.id.me_reward_tv2)
    TextView meRewardTv2;
    @BindView(R2.id.me_reward_tv2_1)
    TextView meRewardTv21;
    @BindView(R2.id.me_reward_tv2_2)
    TextView meRewardTv22;
    @BindView(R2.id.me_reward_rl2)
    RelativeLayout meRewardRl2;
    @BindView(R2.id.me_reward_tv3)
    TextView meRewardTv3;
    @BindView(R2.id.me_reward_tv3_1)
    TextView meRewardTv31;
    @BindView(R2.id.me_reward_rl3)
    RelativeLayout meRewardRl3;
    @BindView(R2.id.me_reward_tv4)
    TextView meRewardTv4;
    @BindView(R2.id.me_reward_tv4_1)
    TextView meRewardTv41;
    @BindView(R2.id.me_reward_rl4)
    RelativeLayout meRewardRl4;
    @BindView(R2.id.me_reward_tv5)
    TextView meRewardTv5;
    @BindView(R2.id.me_reward_tv5_1)
    TextView meRewardTv51;
    @BindView(R2.id.me_reward_rl5)
    RelativeLayout meRewardRl5;
    @BindView(R2.id.me_reward_tv6)
    TextView meRewardTv6;
    @BindView(R2.id.me_reward_tv6_1)
    TextView meRewardTv61;
    @BindView(R2.id.me_reward_rl6)
    RelativeLayout meRewardRl6;
    @BindView(R2.id.me_reward_tv7)
    TextView meRewardTv7;
    @BindView(R2.id.me_reward_tv7_1)
    TextView meRewardTv71;
    @BindView(R2.id.me_reward_rl7)
    RelativeLayout meRewardRl7;

    private ExFragmentPresenter presenter;
    private List<RewardRuleEntity> list;
    private SelectUserTypeDialog dialog;
    private int type = 0;
    private RewardRuleAdapter adapter;

    @Override
    public int getContentViewId() {

        return R.layout.me_fragment_extension;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        presenter = new ExFragmentPresenter(this, this);
        getData(0);
        meProfitDetails.setOnClickListener(this);
        me_buy_up_rl.setOnClickListener(this);
        me_ext_up_rl.setOnClickListener(this);
        me_rv_slice.setOnClickListener(this);
    }

    private void getData(int role) {
        presenter.rewardRule(1, role);
    }

    @Override
    public void result(int code, Boolean hasNextPage, String response, Object object) {
        super.result(code, hasNextPage, response, object);
        if (code == 1) {
            if (object == null) return;
            list = new ArrayList<>();
            list = (List<RewardRuleEntity>) object;
        }
        //setRules(type,list);
        setRv(list);
    }

    private void setRv(List<RewardRuleEntity> data){
        adapter = new RewardRuleAdapter(R.layout.me_item_newrewardrole,data);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        meRvRewardrules.setLayoutManager(manager);
        meRvRewardrules.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.me_profit_details) {
            RouterUtils.getInstance().routerNormal(RouterPath.ACTIVITY_IN_COME);
        } else if (v.getId() == R.id.me_buy_up_rl) {
            // RouterUtils.getInstance().routerNormal(RouterPath.ACTIVITY_BUY_ROLE);
        } else if (v.getId() == R.id.me_rv_slice) {
            showDialog();
        }

    }

    private void showDialog() {
        dialog = new SelectUserTypeDialog(getActivity(), R.style.Dialog, new OnClickListener() {
            @Override
            public void onCancel(View v, Object... objects) {

            }

            @Override
            public void onClick(View v, Object... objects) {
                String userType = (String) objects[0];
                me_rv_slice.setText("筛选：" + userType);
                if (userType.equals("普通用户")) {
                    type = 0;
                } else if (userType.equals("VIP")) {
                    type = 1;
                } else if (userType.equals("矿主")) {
                    type = 2;
                } else if (userType.equals("场主")) {
                    type = 3;
                } else if (userType.equals("城主")) {
                    type = 4;
                }
                getData(type);
            }
        });
        if (!dialog.isShowing())
            dialog.showDialog(0, -(ScreenUtils.getScreenHeight(getActivity())));
    }

    private void getRecive() {
        ApiManager.getReciveGoodsList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResult<ReceiveGoodsEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<ReceiveGoodsEntity> receiveGoodsEntityBaseResult) {
                        Log.e("xxxxxxH", receiveGoodsEntityBaseResult.getData().getGoods_list() + "");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
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
