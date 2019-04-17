package com.seven.module_home.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.seven.lib_common.base.activity.BaseAppCompatActivity;
import com.seven.lib_common.listener.IKeyBoardVisibleListener;
import com.seven.lib_common.stextview.TypeFaceEdit;
import com.seven.lib_common.utils.NetWorkUtils;
import com.seven.lib_common.utils.ResourceUtils;
import com.seven.lib_common.utils.ScreenUtils;
import com.seven.lib_common.utils.ToastUtils;
import com.seven.lib_model.model.home.CommodityEntity;
import com.seven.lib_opensource.application.SSDK;
import com.seven.lib_router.router.RouterPath;
import com.seven.module_home.R;
import com.seven.module_home.R2;
import com.seven.module_home.adapter.HomeAdapter;
import com.seven.module_home.widget.decoration.CommodityDecoration;
import com.seven.module_home.widget.decoration.HomeDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auhtor seven
 * email:frankchenlei@163.com
 * 2019/4/14
 */

@Route(path = RouterPath.ACTIVITY_COMMODITY)
public class CommodityListActivity extends BaseAppCompatActivity implements IKeyBoardVisibleListener,
        BaseQuickAdapter.OnItemClickListener {

    @BindView(R2.id.left_btn)
    public RelativeLayout leftBtn;
    @BindView(R2.id.right_text_btn)
    public RelativeLayout rightBtn;

    @BindView(R2.id.search_layout)
    public LinearLayout searchLayout;
    @BindView(R2.id.search_et)
    public TypeFaceEdit searchEt;

    private RelativeLayout.LayoutParams layoutParams;
    private InputMethodManager imm;
    private Handler handler;
    private AnimRunnable thread;

    @BindView(R2.id.global_btn)
    public LinearLayout globalBtn;
    @BindView(R2.id.sales_volume_btn)
    public LinearLayout salesVolumeBtn;
    @BindView(R2.id.price_btn)
    public LinearLayout priceBtn;
    @BindView(R2.id.sales_volume_iv)
    public ImageView salesVolumeIv;
    @BindView(R2.id.price_iv)
    public ImageView priceIv;
    private boolean isDown;

    private int page = 1;
    private int pageSize = 10;
    public boolean isRefresh;
    private boolean isMoreEnd;

    @BindView(R2.id.swipe_refresh)
    public SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R2.id.recycler_view)
    public RecyclerView recyclerView;
    public HomeAdapter adapter;
    private List<CommodityEntity> commodityList;

    @Override
    protected int getContentViewId() {
        return R.layout.mh_activity_commodity;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void initBundleData(Intent intent) {

        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        handler = new Handler();
        thread = new AnimRunnable();

        setScreenSelected(globalBtn);

        setRecyclerView();
    }

    private void request(int page) {

        closeLoading();

    }

    private void setScreenSelected(View view) {

        if (view.isSelected()) {
            if (isDown) {

                if (view == salesVolumeBtn)
                    salesVolumeIv.setImageResource(R.drawable.label_screen_3);
                else
                    priceIv.setImageResource(R.drawable.label_screen_3);
                isDown = false;
            } else {
                if (view == salesVolumeBtn)
                    salesVolumeIv.setImageResource(R.drawable.label_screen_2);
                else
                    priceIv.setImageResource(R.drawable.label_screen_2);
                isDown = true;
            }
        } else {
            globalBtn.setSelected(globalBtn == view);
            salesVolumeBtn.setSelected(salesVolumeBtn == view);
            priceBtn.setSelected(priceBtn == view);
            isDown = true;

            if (globalBtn.isSelected()) {
                salesVolumeIv.setImageResource(R.drawable.label_screen_1);
                priceIv.setImageResource(R.drawable.label_screen_1);
            }
            if (salesVolumeBtn.isSelected()) {
                salesVolumeIv.setImageResource(R.drawable.label_screen_2);
                priceIv.setImageResource(R.drawable.label_screen_1);
            }
            if (priceBtn.isSelected()) {
                salesVolumeIv.setImageResource(R.drawable.label_screen_1);
                priceIv.setImageResource(R.drawable.label_screen_2);
            }
        }
    }

    private void setRecyclerView() {

        commodityList = new ArrayList<>();
        CommodityEntity entity = null;
        for (int i = 0; i < 20; i++) {
            entity = new CommodityEntity();
            entity.setTitle("全新上市祖马龙茉莉与金盏香水 祖马龙500ml哥弟反黑" + i);
            entity.setImagePath("http://b-ssl.duitang.com/uploads/item/201201/08/20120108130517_Ra8f2.jpg");
            entity.setPrice(1000D);
            entity.setCount(i);
            commodityList.add(entity);
        }

        adapter = new HomeAdapter(R.layout.mh_item_home, commodityList, screenWidth);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        }, recyclerView);
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(SSDK.getInstance().getContext(), 2));
        recyclerView.addItemDecoration(new CommodityDecoration(adapter.getHeaderLayoutCount()));
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setColorSchemeResources(
                R.color.refresh_1,
                R.color.refresh_2,
                R.color.refresh_3,
                R.color.refresh_4);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!NetWorkUtils.isNetWork()) {
                    swipeRefreshLayout.setRefreshing(false);
                    return;
                }
                isRefresh = true;
                page = 1;
                request(page);
            }
        });

    }

    private void loadMore() {

        if (isMoreEnd) {
            ToastUtils.showToast(mContext, ResourceUtils.getText(R.string.hint_more_not));
            return;
        }
        page++;
        request(page);
    }

    public void btClick(View view) {

        if (view.getId() == R.id.left_btn)
            onBackPressed();

        if (view.getId() == R.id.right_text_btn)
            if (imm != null && searchEt != null)
                imm.hideSoftInputFromWindow(searchEt.getWindowToken(), 0);

        if (view.getId() == R.id.global_btn)
            setScreenSelected(globalBtn);

        if (view.getId() == R.id.sales_volume_btn)
            setScreenSelected(salesVolumeBtn);

        if (view.getId() == R.id.price_btn)
            setScreenSelected(priceBtn);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        ToastUtils.showToast(SSDK.getInstance().getContext(), position + "");

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void closeLoading() {

        dismissLoadingDialog();

        if (swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        ScreenUtils.addOnSoftKeyBoardVisibleListener(CommodityListActivity.this, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ScreenUtils.removeOnSoftKeyBoardVisibleListener();

        if (imm != null && searchEt != null)
            imm.hideSoftInputFromWindow(searchEt.getWindowToken(), 0);
    }

    @Override
    protected void onDestroy() {
        if (handler != null && thread != null) {
            handler.removeCallbacks(thread);
            handler = null;
            thread = null;
        }
        super.onDestroy();
    }

    @Override
    public void onSoftKeyBoardVisible(boolean visible, int windowBottom) {

        searchEt.setCursorVisible(visible);

        resetSearchLayout(visible);
    }

    private void resetSearchLayout(boolean visible) {

        if (!visible)
            leftBtn.setVisibility(View.VISIBLE);

        if (visible)
            rightBtn.setVisibility(View.VISIBLE);


        thread.setVisible(visible);
        handler.postDelayed(thread, 10);
    }

    private class AnimRunnable implements Runnable {

        private boolean visible;
        private int leftStart;
        private int leftEnd;
        private int rightStart;
        private int rightEnd;

        private int leftMargin;
        private int rightMargin;

        public void setVisible(boolean visible) {
            this.visible = visible;
            leftStart = visible ? 48 : 16;
            leftEnd = visible ? 16 : 48;
            rightStart = visible ? 16 : 56;
            rightEnd = visible ? 56 : 16;

            leftMargin = leftStart;
            rightMargin = rightStart;
        }

        @Override
        public void run() {


            if (visible && leftMargin > leftEnd) {
                leftMargin -= 2;
                if (leftMargin == leftEnd)
                    leftBtn.setVisibility(View.GONE);
            }

            if (!visible && leftMargin < leftEnd)
                leftMargin += 2;

            if (visible && rightMargin < rightEnd)
                rightMargin += 2;

            if (!visible && rightMargin > rightEnd) {
                rightMargin -= 2;
                if (rightMargin == rightEnd)
                    rightBtn.setVisibility(View.GONE);
            }

            layoutParams = (RelativeLayout.LayoutParams) searchLayout.getLayoutParams();
            layoutParams.leftMargin = ScreenUtils.dip2px(mContext, leftMargin);
            layoutParams.rightMargin = ScreenUtils.dip2px(mContext, rightMargin);
            searchLayout.setLayoutParams(layoutParams);

            if (rightMargin != rightEnd)
                handler.postDelayed(this, 10);
        }
    }
}
