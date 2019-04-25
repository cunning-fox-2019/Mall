package com.seven.module_user.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.seven.lib_common.base.activity.BaseTitleActivity;
import com.seven.module_user.R;
import com.seven.module_user.R2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class EditUserInfoActivity extends BaseTitleActivity {


    @BindView(R2.id.user_photo)
    TextView userPhoto;
    @BindView(R2.id.user_nick_name)
    TextView userNickName;
    @BindView(R2.id.choose_sex)
    TextView chooseSex;

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
        return R.layout.mu_activity_edit_user_info;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleText(R.string.user_edit_info);
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

    private void chooseSex() {
        final List<String> sexList = new ArrayList<>();
        sexList.add("男");
        sexList.add("女");
        OptionsPickerView sexPickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                chooseSex.setText(sexList.get(options1));
            }
        }).setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                .setSelectOptions(0)//默认选中项
                .setBgColor(Color.WHITE)
                .setTitleBgColor(getResources().getColor(R.color.color_eee))
                .setCancelColor(getResources().getColor(R.color.color_6c))
                .setSubmitColor(getResources().getColor(R.color.color_1e1d1d))
                .setTextColorCenter(getResources().getColor(R.color.color_1e1d1d))
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setOutSideColor(0x00000000) //设置外部遮罩颜色
                .build();
        sexPickerView.setPicker(sexList);
        sexPickerView.show();
    }


    @OnClick({R2.id.user_photo, R2.id.choose_sex})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.user_photo) {

        } else if (view.getId() == R.id.choose_sex) {
            chooseSex();
        }
    }
}
