package com.seven.module_extension.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.flyco.tablayout.SlidingTabLayout;
import com.seven.lib_common.base.activity.BaseAppCompatActivity;
import com.seven.lib_common.base.activity.BaseTitleActivity;
import com.seven.lib_router.router.RouterPath;
import com.seven.module_extension.R;
import com.seven.module_extension.R2;
import com.seven.module_extension.ui.adapter.PagerAdapter;
import com.seven.module_extension.ui.fragment.IncomeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xxxxxxH on 2019/4/20.
 */
@Route(path = RouterPath.ACTIVITY_IN_COME)
public class IncomeActivity extends BaseAppCompatActivity {
    @BindView(R2.id.me_tab)
    SlidingTabLayout meTab;
    @BindView(R2.id.me_viewpager)
    ViewPager meViewpager;
    @BindView(R2.id.me_income_back)
    ImageView me_income_back;

    private IncomeFragment allFragment;
    private IncomeFragment inFragment;
    private IncomeFragment outFragment;
    private IncomeFragment frozenFragment;
    private List<Fragment> fragmentList;
    private PagerAdapter pagerAdapter;

    @Override
    protected int getContentViewId() {
        return R.layout.me_activity_income;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        statusBar = StatusBar.LIGHT;
        fragmentList= new ArrayList<>();
        allFragment = new IncomeFragment();
        inFragment = new IncomeFragment();
        outFragment = new IncomeFragment();
        frozenFragment = new IncomeFragment();
        fragmentList.add(allFragment);
        fragmentList.add(inFragment);
        fragmentList.add(outFragment);
        fragmentList.add(frozenFragment);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(),fragmentList);
        meViewpager.setAdapter(pagerAdapter);
        meTab.setViewPager(meViewpager,new String[]{"全部","收入","支出","冻结"});
        meTab.setCurrentTab(0);
    }

    @Override
    protected void initBundleData(Intent intent) {

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
    public void onClick(View view) {

    }
}
