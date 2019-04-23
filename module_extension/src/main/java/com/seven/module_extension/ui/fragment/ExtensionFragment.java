package com.seven.module_extension.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.seven.lib_common.base.fragment.BaseFragment;
import com.seven.lib_common.stextview.TypeFaceView;
import com.seven.lib_common.utils.ScreenUtils;
import com.seven.lib_model.ApiManager;
import com.seven.lib_model.BaseResult;
import com.seven.lib_model.model.user.extension.ReceiveGoodsEntity;
import com.seven.lib_router.router.RouterPath;
import com.seven.lib_router.router.RouterUtils;
import com.seven.module_extension.R;
import com.seven.module_extension.R2;

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
    @BindView(R2.id.me_rv_everyreward_sp)
    Spinner meRvEveryrewardSp;
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


    @Override
    public int getContentViewId() {

        return R.layout.me_fragment_extension;
    }

    @Override
    public void init(Bundle savedInstanceState) {
//        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) meTitle.getLayoutParams();
//        params.height = ScreenUtils.getScreenHeight(getActivity()) / 4;
//        meTitle.setLayoutParams(params);
        meProfitDetails.setOnClickListener(this);
        me_buy_up_rl.setOnClickListener(this);
        me_ext_up_rl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.me_profit_details) {
            RouterUtils.getInstance().routerNormal(RouterPath.ACTIVITY_IN_COME);
        } else if (v.getId() == R.id.me_buy_up_rl) {
             RouterUtils.getInstance().routerNormal(RouterPath.ACTIVITY_BUY_ROLE);
//            getRecive();
        }

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
