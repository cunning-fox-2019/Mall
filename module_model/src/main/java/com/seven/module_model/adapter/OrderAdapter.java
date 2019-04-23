package com.seven.module_model.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seven.lib_common.utils.ResourceUtils;
import com.seven.lib_common.utils.glide.GlideUtils;
import com.seven.lib_model.model.model.OrderEntity;
import com.seven.lib_opensource.application.SSDK;
import com.seven.module_model.R;

import java.util.List;

/**
 * @auhtor seven
 * email:frankchenlei@163.com
 * 2019/4/19
 */

public class OrderAdapter extends BaseQuickAdapter<OrderEntity, BaseViewHolder> {

    public OrderAdapter(int layoutResId, @Nullable List<OrderEntity> data) {
        super(layoutResId, data);
        mContext = SSDK.getInstance().getContext();
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderEntity item) {

        GlideUtils.loadCircleImage(mContext, item.getAvatar(), (ImageView) helper.getView(R.id.avatar_iv));

        helper.setText(R.id.name_tv, item.getName())
                .setText(R.id.radio_tv, ResourceUtils.getFormatText(R.string.radio, item.getRatio() + "%"))
                .setText(R.id.volume_tv, ResourceUtils.getFormatText(R.string.volume, item.getVolume()))
                .setText(R.id.token_tv, item.getToken() + "")
                .setText(R.id.price_tv, item.getPrice() + "")
                .setText(R.id.status_tv, item.getStatus() + "");
    }
}
