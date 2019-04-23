package com.seven.module_user.ui.fragment.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Edison on 2016/7/22.
 * 自定义高度的空白分割线
 */
public class DividerSpaceItemDecoration extends RecyclerView.ItemDecoration{

    private int space;

    public DividerSpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if(parent.getChildLayoutPosition(view) != 0)
            outRect.top = space;
    }
}