package com.seven.module_user.ui.fragment.token;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.seven.lib_common.base.activity.BaseAppCompatActivity;
import com.seven.module_user.R;
import com.seven.module_user.R2;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by xxxxxxH on 2019/4/10.
 * 令牌明细
 */

public class UserTokenListActivity extends BaseAppCompatActivity {
    @BindView(R2.id.toolbar)
    Toolbar mToolBar;
    @BindView(R2.id.tabLayout)
    SlidingTabLayout tabLayout;
    @BindView(R2.id.viewPager)
    ViewPager viewPager;
    private final String[] mTitles = {
            "全部", "收入", "支出", "冻结"};
    private List<Fragment> mFragments = new ArrayList<>();
    private final String[] mType = {"all", "income", "expend", "frozen"};

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
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
    protected int getContentViewId() {
        statusBar = StatusBar.LIGHT;
        return R.layout.mu_activity_token_list_layout;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void initBundleData(Intent intent) {
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("令牌明细");
        ImmersionBar.with(this).init();
        ImmersionBar.setTitleBar(this, mToolBar);
        for (String type : mType) {
            mFragments.add(TokenListFragment.getInstance(type));
        }
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tabLayout.setViewPager(viewPager);
    }

    @Override
    public void onClick(View view) {

    }
    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
