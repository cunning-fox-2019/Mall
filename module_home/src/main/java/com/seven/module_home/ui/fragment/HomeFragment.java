package com.seven.module_home.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.seven.lib_common.base.fragment.BaseFragment;
import com.seven.lib_common.utils.NetWorkUtils;
import com.seven.lib_common.utils.OutlineUtils;
import com.seven.lib_common.utils.ResourceUtils;
import com.seven.lib_common.utils.ToastUtils;
import com.seven.lib_common.utils.glide.loader.GlideImageLoader;
import com.seven.lib_model.model.home.BannerEntity;
import com.seven.lib_model.model.home.BannerEntranceEntity;
import com.seven.lib_model.model.home.CommodityEntity;
import com.seven.lib_opensource.application.SSDK;
import com.seven.lib_router.router.RouterPath;
import com.seven.lib_router.router.RouterUtils;
import com.seven.module_home.R;
import com.seven.module_home.R2;
import com.seven.module_home.adapter.BannerEntranceAdapter;
import com.seven.module_home.adapter.HomeAdapter;
import com.seven.module_home.widget.decoration.HomeDecoration;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auhtor seven
 * email:frankchenlei@163.com
 * 2019/3/26
 */
@Route(path = RouterPath.FRAGMENT_HOME)
public class HomeFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {

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

    //banner
    private LinearLayout searchLayout;

    private RelativeLayout bannerLayout;
    private Banner banner;
    private List<BannerEntity> bannerList;
    private List<String> banners;

    private RecyclerView bannerRecycler;
    private BannerEntranceAdapter entranceAdapter;
    private List<BannerEntranceEntity> entranceList;
    private BannerEntranceEntity entranceEntity;
    private int[] imgIds;
    private String[] titles;

    @Override
    public int getContentViewId() {
        return R.layout.mh_fragment_home;
    }

    @Override
    public void init(Bundle savedInstanceState) {

        setRecyclerView();

        bannerList = new ArrayList<>();

        BannerEntity bannerEntity = null;
        for (int i = 0; i < 5; i++) {
            bannerEntity = new BannerEntity();
            bannerEntity.setImagePath("http://img3.imgtn.bdimg.com/it/u=3457809768,2723363614&fm=214&gp=0.jpg");
            bannerList.add(bannerEntity);
        }

        setBannerView();
        setBannerRecycler();
    }

    private void request(int page) {

        closeLoading();

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
        adapter.addHeaderView(getHeaderView());
        recyclerView.setLayoutManager(new GridLayoutManager(SSDK.getInstance().getContext(), 2));
        recyclerView.addItemDecoration(new HomeDecoration(adapter.getHeaderLayoutCount()));
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
            ToastUtils.showToast(getActivity(), ResourceUtils.getText(R.string.hint_more_not));
            return;
        }
        page++;
        request(page);
    }

    private View getHeaderView() {
        View bannerView = LayoutInflater.from(SSDK.getInstance().getContext()).inflate(
                R.layout.mh_header_banner, (ViewGroup) recyclerView.getParent(), false);

        searchLayout = getView(bannerView, searchLayout, R.id.search_layout);
        searchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RouterUtils.getInstance().routerNormal(RouterPath.ACTIVITY_COMMODITY);
            }
        });

        bannerLayout = getView(bannerView, bannerLayout, R.id.banner_layout);
        banner = getView(bannerView, banner, R.id.banner_view);

        OutlineUtils.getInstance().outlineView(bannerLayout, 0);

        bannerRecycler = getView(bannerView, bannerRecycler, R.id.recycler_view);
        entranceAdapter = new BannerEntranceAdapter(R.layout.mh_item_banner_entrance, entranceList, screenWidth);
        bannerRecycler.setLayoutManager(new GridLayoutManager(SSDK.getInstance().getContext(), 5));
        bannerRecycler.setAdapter(entranceAdapter);

        return bannerView;
    }

    private void setBannerView() {

        banners = new ArrayList<>();
        for (BannerEntity bannerEntity : bannerList)
            banners.add(bannerEntity.getImagePath());

        banner.setImages(banners)
                .setImageLoader(new GlideImageLoader())
                .start();

    }

    private void setBannerRecycler() {

        imgIds = new int[]{
                R.drawable.entrance_1,
                R.drawable.entrance_2,
                R.drawable.entrance_3,
                R.drawable.entrance_4,
                R.drawable.entrance_5,
                R.drawable.entrance_6,
                R.drawable.entrance_7,
                R.drawable.entrance_8,
                R.drawable.entrance_9,
                R.drawable.entrance_10
        };
        titles = new String[]{
                ResourceUtils.getText(R.string.label_entrance_1),
                ResourceUtils.getText(R.string.label_entrance_2),
                ResourceUtils.getText(R.string.label_entrance_3),
                ResourceUtils.getText(R.string.label_entrance_4),
                ResourceUtils.getText(R.string.label_entrance_5),
                ResourceUtils.getText(R.string.label_entrance_6),
                ResourceUtils.getText(R.string.label_entrance_7),
                ResourceUtils.getText(R.string.label_entrance_8),
                ResourceUtils.getText(R.string.label_entrance_9),
                ResourceUtils.getText(R.string.label_entrance_10)
        };

        entranceList = new ArrayList<>();

        if (imgIds.length == titles.length) {
            int count = 0;
            for (int id : imgIds) {
                entranceEntity = new BannerEntranceEntity();
                entranceEntity.setId(id);
                entranceEntity.setTitle(titles[count]);
                entranceList.add(entranceEntity);
                count++;
            }
            entranceAdapter.setNewData(entranceList);
        }
    }

    @Override
    public void onClick(View v) {

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
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        ToastUtils.showToast(SSDK.getInstance().getContext(),position+"");

    }
}
