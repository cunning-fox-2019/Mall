package com.seven.module_home.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seven.lib_model.model.home.BannerEntranceEntity;
import com.seven.lib_opensource.application.SSDK;
import com.seven.module_home.R;

import java.util.List;

/**
 * @auhtor seven
 * email:frankchenlei@163.com
 * 2019/4/14
 */

public class BannerEntranceAdapter extends BaseQuickAdapter<BannerEntranceEntity, BaseViewHolder> {

    private int radius;

    public BannerEntranceAdapter(int layoutResId, @Nullable List<BannerEntranceEntity> data, int screenWith) {
        super(layoutResId, data);
        mContext = SSDK.getInstance().getContext();
        radius = screenWith / 5;
    }

    @Override
    protected void convert(BaseViewHolder helper, BannerEntranceEntity item) {

        LinearLayout layout=helper.getView(R.id.item_layout);

        RecyclerView.LayoutParams params= (RecyclerView.LayoutParams) layout.getLayoutParams();
        params.height=radius;
        layout.setLayoutParams(params);

        helper.setImageResource(R.id.cover_iv,item.getId())
                .setText(R.id.title_tv,item.getTitle());

    }
}
