package com.seven.module_user.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.seven.lib_common.base.activity.BaseAppCompatActivity;
import com.seven.lib_common.base.activity.BaseTitleActivity;
import com.seven.lib_common.utils.ToastUtils;
import com.seven.lib_common.utils.glide.GlideUtils;
import com.seven.lib_model.ApiManager;
import com.seven.lib_model.BaseResult;
import com.seven.lib_model.model.home.CartEntity;
import com.seven.lib_model.model.user.mine.ShopEntity;
import com.seven.lib_opensource.event.MessageEvent;
import com.seven.lib_opensource.event.ObjectsEvent;
import com.seven.lib_router.Constants;
import com.seven.lib_router.router.RouterPath;
import com.seven.lib_router.router.RouterUtils;
import com.seven.module_user.R;
import com.seven.module_user.R2;
import com.seven.module_user.ui.fragment.view.BaseRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xxxxxxH on 2019/4/13.
 */
@Route(path = RouterPath.ACTIVITY_SHOPPING_CART)
public class UserShoppingCartActivity extends BaseTitleActivity {

    @Autowired(name = Constants.BundleConfig.EVENT_CODE)
    int code = 0;

    @BindView(R2.id.list_view)
    BaseRecyclerView recyclerView;
    @BindView(R2.id.pay_btn_layout)
    LinearLayout payBtnLayout;
    @BindView(R2.id.select_all_img)
    ImageView select_all_img;
    @BindView(R2.id.select_all_tv)
    TextView select_all_tv;
    @BindView(R2.id.select_num)
    TextView select_num;
    @BindView(R2.id.money)
    TextView moneyTv;

    private boolean isSelectAll = false;

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
        return R.layout.mu_activity_shopping_cart;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleText(R.string.user_shop_cart);

        ApiManager.getCartList().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResult<ShopEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<ShopEntity> data) {
                        initListView(data.getData().getItems());
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

    @Override
    protected void initBundleData(Intent intent) {

    }

    private void initListView(List<CartEntity> list) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.init(layoutManager, new BaseQuickAdapter<CartEntity, BaseViewHolder>(R.layout.item_shopping_cart_layout, list) {

            @Override
            protected void convert(BaseViewHolder helper, CartEntity item) {
                helper.setText(R.id.goods_name, item.getGoods_name())
                        .setText(R.id.sales, item.getSales() + "人付款")
                        .setText(R.id.money, String.valueOf(item.getPrice()))
                        .addOnClickListener(R.id.is_select_btn)
                        .setImageResource(R.id.is_select_btn,
                                item.isSelect() ? R.drawable.item_shopping_cart_selector : R.drawable.item_shopping_cart_default);
                ImageView imageView = helper.getView(R.id.goods_img);
                GlideUtils.loadImage(mContext, item.getThumb(), imageView);
            }
        }, true)
                .addOnItemChildClickListener(new OnItemChildClickListener() {
                    @Override
                    public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        CartEntity entity = (CartEntity) adapter.getData().get(position);
                        if (view.getId() == R.id.is_select_btn) {
                            entity.setSelect(!entity.isSelect());
                            adapter.notifyItemChanged(position);
                            changeSelectNum();
                        }
                    }
                })
                .removeItemDecoration();
    }

    StringBuilder shopIds = new StringBuilder();

    @OnClick(R2.id.pay_btn_layout)
    void pay() {
        List<CartEntity> list = recyclerView.getData();
        List<CartEntity> selectList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isSelect()) {
                selectList.add(list.get(i));
                shopIds.append(list.get(i).getId());
                if (i != list.size() - 1) {
                    shopIds.append(",");
                }

            }
        }
        if (selectList.size() < 1) {
            ToastUtils.showToast(mContext, "没有选择商品");
            return;
        }

        RouterUtils.getInstance().routerNormal(RouterPath.ACTIVITY_COMMODITY_ORDER);
        //todo shopIds.toString()是拼接好的id字符串可以直接用
        EventBus.getDefault().post(new ObjectsEvent(code, shopIds));


    }

    @OnClick({R2.id.select_all_img, R2.id.select_all_tv})
    void selectAll() {
        isSelectAll = !isSelectAll;
        if (isSelectAll) {
            select_all_img.setImageDrawable(getResources().getDrawable(R.drawable.item_shopping_cart_selector));
            select_all_tv.setTextColor(getResources().getColor(R.color.add_address_default_c));
        } else {
            select_all_img.setImageDrawable(getResources().getDrawable(R.drawable.item_shopping_cart_default));
            select_all_tv.setTextColor(getResources().getColor(R.color.add_address_default_n));
        }
        changeListDataState(isSelectAll);
    }

    private void changeListDataState(boolean isSelect) {
        List<CartEntity> entityList = recyclerView.getAdapter().getData();
        for (CartEntity entity : entityList) {
            entity.setSelect(isSelect);
        }
        recyclerView.notifyDataSetChanged();
        changeSelectNum();
    }

    private void changeSelectNum() {
        int num = 0;
        double money = 0.0f;
        List<CartEntity> entityList = recyclerView.getAdapter().getData();
        for (CartEntity entity : entityList) {
            if (entity.isSelect()) {
                num++;
                money += entity.getPrice();
            }
        }
        select_num.setText("(" + num + ")");
        moneyTv.setText("总价：￥" + money);
    }
}
