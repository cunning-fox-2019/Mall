package com.seven.module_user.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.seven.lib_common.base.activity.BaseTitleActivity;
import com.seven.lib_model.ApiManager;
import com.seven.lib_model.BaseResult;
import com.seven.lib_model.model.user.mine.AddressEntity;
import com.seven.module_user.R;
import com.seven.module_user.R2;
import com.seven.module_user.ui.fragment.view.BaseRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class UserAddressActivity extends BaseTitleActivity {
    @BindView(R2.id.list_view)
    BaseRecyclerView recyclerView;
    @BindView(R2.id.add_address)
    TextView addAddress;
    private boolean isChoose = false;//false 从账号中心进来查看 不能点击；true 从付款进来 可以点击

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
        return R.layout.mu_activity_address;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleText(R.string.user_address);
        ApiManager.getAddressList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResult<List<AddressEntity>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<List<AddressEntity>> listBaseResult) {
                        initListView(listBaseResult.getData());
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
    protected void rightTextBtnClick(View v) {

    }

    @Override
    protected void rightBtnClick(View v) {

    }

    private void initListView(List<AddressEntity> list) {
        addAddress.setVisibility(list != null && list.size() > 0 ? View.VISIBLE : View.GONE);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.init(layoutManager, new BaseQuickAdapter<AddressEntity, BaseViewHolder>(R.layout.item_address_layout, list) {
            @Override
            protected void convert(BaseViewHolder helper, AddressEntity item) {
                helper.setText(R.id.address_name, item.getContact_name())
                        .setText(R.id.address_phone_number, item.getContact_phone())
                        .setText(R.id.address, item.getProvince_name() + " " + item.getCity_name() + " " + item.getDistrict_name() + " " + item.getAddress())
                        .addOnClickListener(R.id.is_default_address)
                        .addOnClickListener(R.id.edit_address)
                        .addOnClickListener(R.id.delete_address);
            }
        }, false)
                .addOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                        //todo 选择是活得地址
                        if (isChoose) {
                            AddressEntity entity = (AddressEntity) adapter.getData().get(position);
                        }
                    }
                })
                .setEmptyView(getEmptyView())
                .removeItemDecoration();
    }

    @Override
    protected void initBundleData(Intent intent) {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.empty_add_address) {
            startActivity(new Intent(mContext, UserCreateAddressActivity.class));
        }

    }

    private View getEmptyView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.empty_address_layout, null);
        view.findViewById(R.id.empty_add_address).setOnClickListener(this);
        return view;
    }

    @OnClick(R2.id.add_address)
    void addAddress() {
        startActivity(new Intent(mContext, UserCreateAddressActivity.class));
    }
}
