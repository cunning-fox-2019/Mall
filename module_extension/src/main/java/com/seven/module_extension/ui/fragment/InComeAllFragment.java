package com.seven.module_extension.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seven.lib_common.base.fragment.BaseFragment;
import com.seven.lib_model.model.extension.InComeItem;
import com.seven.module_extension.R;
import com.seven.module_extension.R2;
import com.seven.module_extension.ui.adapter.InComeDetailsAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xxxxxxH on 2019/5/4.
 */
@SuppressLint("ValidFragment")
public class InComeAllFragment extends BaseFragment {

    @BindView(R2.id.me_income_rv)
    RecyclerView meIncomeRv;
    private InComeDetailsAdapter adapter;

    @Override
    public int getContentViewId() {
        return R.layout.me_fragment_income;
    }


    public InComeAllFragment() {

    }

    public void setRv(List<InComeItem> list) {
        if (list != null && list.size() > 0) {
            adapter = new InComeDetailsAdapter(R.layout.me_item_income_details, list);
            meIncomeRv.setLayoutManager(new LinearLayoutManager(getActivity()));
            meIncomeRv.setAdapter(adapter);
        }
    }

    @Override
    public void init(Bundle savedInstanceState) {

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
