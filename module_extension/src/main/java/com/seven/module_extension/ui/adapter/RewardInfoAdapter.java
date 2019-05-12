package com.seven.module_extension.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seven.lib_model.model.extension.RewardInfoLlistEntity;
import com.seven.module_extension.R;

import java.util.List;

/**
 * Created by xxxxxxH on 2019/5/12.
 */
public class RewardInfoAdapter extends BaseQuickAdapter<RewardInfoLlistEntity,BaseViewHolder> {
    public RewardInfoAdapter(int layoutResId, @Nullable List<RewardInfoLlistEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RewardInfoLlistEntity item) {
        helper.setText(R.id.me_item_lingqu_type,item.getReward_name())
                .setText(R.id.me_lingqu_num,item.getReward_number()+"")
                .setText(R.id.me_item_lingqushijian,item.getDatetime());
    }
}
