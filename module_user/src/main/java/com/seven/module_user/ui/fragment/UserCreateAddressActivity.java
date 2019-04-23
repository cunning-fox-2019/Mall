package com.seven.module_user.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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
import com.seven.lib_common.base.activity.BaseTitleActivity;
import com.seven.lib_common.utils.ToastUtils;
import com.seven.lib_model.ApiManager;
import com.seven.lib_model.BaseResult;
import com.seven.lib_model.model.user.mine.AddAddressEntity;
import com.seven.module_user.R;
import com.seven.module_user.R2;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ouyang on 2019/4/4.
 */

public class UserCreateAddressActivity extends BaseTitleActivity {

    @BindView(R2.id.address_tx)
    TextView addressTx;
    @BindView(R2.id.is_default_address_img)
    ImageView isDefaultAddressImg;
    @BindView(R2.id.is_default_address_tx)
    TextView isDefaultAddressTx;
    @BindView(R2.id.name_edit)
    EditText nameEdit;
    @BindView(R2.id.phone_edit)
    EditText phoneEdit;
    @BindView(R2.id.address_detail)
    EditText addressDetail;

    private boolean isDefault;


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
    protected int getLayoutId() {
        return R.layout.mu_activity_create_address;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleText(R.string.user_add_address);
    }

    @Override
    protected void rightTextBtnClick(View v) {

    }

    @Override
    protected void rightBtnClick(View v) {

    }

    @Override
    protected void initBundleData(Intent intent) {

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
        isDefaultAddressImg.setImageDrawable(isDefault ? getDrawable(R.drawable.item_shopping_cart_default) : getDrawable(R.drawable.item_shopping_cart_selector));
        isDefaultAddressTx.setTextColor(isDefault ? getResources().getColor(R.color.add_address_default_n) : getResources().getColor(R.color.add_address_default_c));
        isDefault = !isDefault;
    }

    @OnClick(R2.id.add_address)
    void prepareCommit() {
        if (nameEdit.getText().toString().isEmpty()) {
            nameEdit.setError("联系人不能为空！");
            nameEdit.requestFocus();
            return;
        } else if (phoneEdit.getText().toString().isEmpty()) {
            phoneEdit.setError("联系人电话不能为空！");
            phoneEdit.requestFocus();
            return;
        } else if (addressDetail.getText().toString().isEmpty()) {
            addressDetail.setError("请输出详细地址!");
            addressDetail.requestFocus();
            return;
        } else if (addressTx.getText().toString().isEmpty()) {
            ToastUtils.showToast(mContext, "请选择地址!");
            return;
        }
        AddAddressEntity entity = new AddAddressEntity();
        entity.setAddress(addressDetail.getText().toString());
        entity.setContact_name(nameEdit.getText().toString());
        entity.setContact_phone(phoneEdit.getText().toString());
        entity.setIs_default(isDefault ? 1 : 0);
        commit(entity);
    }

    private void commit(AddAddressEntity entity) {
        ApiManager.addAddress(entity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult baseResult) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
