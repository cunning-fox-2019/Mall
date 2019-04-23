package com.seven.module_user.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.seven.lib_common.base.activity.BaseAppCompatActivity;
import com.seven.module_user.R;
import com.seven.module_user.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ouyang on 2019/4/4.
 */

public class UserCreateAddressActivity extends BaseAppCompatActivity {
    @BindView(R2.id.toolbar)
    Toolbar mToolBar;
    @BindView(R2.id.address_tx)
    TextView addressTx;
    @BindView(R2.id.is_default_address_img)
    ImageView isDefaultAddressImg;
    @BindView(R2.id.is_default_address_tx)
    TextView isDefaultAddressTx;

    private boolean isDefault;

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
        return R.layout.mu_activity_create_address;
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
        getSupportActionBar().setTitle("添加地址");
    }

    @Override
    public void onClick(View view) {

    }

    @OnClick(R2.id.address_tx)
    void chooseCity() {
        final StringBuilder builder = new StringBuilder();
        CityPickerView mPicker = new CityPickerView();
        mPicker.init(mContext);
        CityConfig cityConfig = new CityConfig.Builder()
                .confirTextColor("#000000")
                .province("四川省")//默认显示的省份
                .city("成都市")//默认显示省份下面的城市
                .district("武侯区")//默认显示省市下面的区县数据
                .provinceCyclic(false)//省份滚轮是否可以循环滚动
                .cityCyclic(false)//城市滚轮是否可以循环滚动
                .districtCyclic(false)//区县滚轮是否循环滚动
                .build();
        mPicker.setConfig(cityConfig);
        mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                super.onSelected(province, city, district);
                builder.append(province.getName())
                        .append(" ")
                        .append(city.getName())
                        .append(" ")
                        .append(district.getName());
                addressTx.setText(builder.toString());

            }

            @Override
            public void onCancel() {
                super.onCancel();
            }
        });
        mPicker.showCityPicker();
    }

    @OnClick(R2.id.is_default_address)
    void setDefaultAddress() {
        isDefaultAddressImg.setImageDrawable(isDefault?getDrawable(R.drawable.item_shopping_cart_default):getDrawable(R.drawable.item_shopping_cart_selector));
        isDefaultAddressTx.setTextColor(isDefault?getResources().getColor(R.color.add_address_default_n):getResources().getColor(R.color.add_address_default_c));
        isDefault = !isDefault;
    }
}
