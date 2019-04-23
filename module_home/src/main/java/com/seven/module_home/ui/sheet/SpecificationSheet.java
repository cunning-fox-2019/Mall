package com.seven.module_home.ui.sheet;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.seven.lib_common.base.sheet.IBaseSheet;
import com.seven.lib_common.stextview.TypeFaceView;
import com.seven.lib_common.utils.FormatUtils;
import com.seven.lib_common.utils.ResourceUtils;
import com.seven.lib_common.utils.ToastUtils;
import com.seven.lib_common.utils.glide.GlideUtils;
import com.seven.lib_common.widget.flowlayout.FlowLayout;
import com.seven.lib_common.widget.flowlayout.TagAdapter;
import com.seven.lib_common.widget.flowlayout.TagFlowLayout;
import com.seven.lib_model.model.home.CommodityDetailsEntity;
import com.seven.module_home.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * @auhtor seven
 * email:frankchenlei@163.com
 * 2019/4/18
 */

public class SpecificationSheet extends IBaseSheet {

    private CommodityDetailsEntity entity;

    private ImageView closeIv;
    private ImageView subtractionIv;
    private ImageView addIv;
    private RelativeLayout shoppingRl;
    private RelativeLayout buyRl;

    private ImageView coverIv;
    private TypeFaceView priceTv;
    private TypeFaceView stockTv;

    private TagFlowLayout colorFlowLayout;
    private TagAdapter colorAdapter;
    private List<CommodityDetailsEntity.SkuAttrListBean.AttrValuesBean> colorList;

    private TagFlowLayout sizeFlowLayout;
    private TagAdapter sizeAdapter;
    private List<CommodityDetailsEntity.SkuAttrListBean.AttrValuesBean> sizeList;

    private TypeFaceView numberTv;

    private String colorSku;
    private String sizeSku;
    private CommodityDetailsEntity.SkuListBean skuBean;

    public SpecificationSheet(Activity activity, int theme, com.seven.lib_common.listener.OnClickListener listener) {
        super(activity, theme, listener);
    }

    public SpecificationSheet(Activity activity, int theme, com.seven.lib_common.listener.OnClickListener listener, CommodityDetailsEntity entity) {
        this(activity, theme, listener);
        this.entity = entity;
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.sheet_specification;
    }

    @Override
    public void onInitRootView(Bundle savedInstanceState) {

        initView();

        initData();
    }

    @Override
    public void initView() {

        closeIv = getView(closeIv, R.id.close_iv);
        subtractionIv = getView(subtractionIv, R.id.subtraction_iv);
        addIv = getView(addIv, R.id.add_iv);
        shoppingRl = getView(shoppingRl, R.id.shopping_add_rl);
        buyRl = getView(buyRl, R.id.buy_rl);

        coverIv = getView(coverIv, R.id.cover_iv);
        priceTv = getView(priceTv, R.id.price_tv);
        stockTv = getView(stockTv, R.id.stock_tv);

        colorFlowLayout = getView(colorFlowLayout, R.id.color_flow);
        sizeFlowLayout = getView(sizeFlowLayout, R.id.size_flow);

        numberTv = getView(numberTv, R.id.number_tv);
    }

    @Override
    public void initData() {

        closeIv.setOnClickListener(this);
        subtractionIv.setOnClickListener(this);
        addIv.setOnClickListener(this);
        shoppingRl.setOnClickListener(this);
        buyRl.setOnClickListener(this);

        priceTv.setText(FormatUtils.formatCurrencyD(entity.getPrice()));
        stockTv.setText(ResourceUtils.getText(R.string.hint_sheet_stock));

        GlideUtils.loadImage(activity, entity.getThumb(), coverIv);

        setColorFlowLayout();
        setSizeFlowLayout();

        numberTv.setText("0");
    }

    private void setColorFlowLayout() {

        colorList = entity.getSku_attr_list().get(0).getAttr_values();

        colorAdapter = new TagAdapter<CommodityDetailsEntity.SkuAttrListBean.AttrValuesBean>(colorList) {
            @Override
            public View getView(FlowLayout parent, final int position, final CommodityDetailsEntity.SkuAttrListBean.AttrValuesBean item) {
                View view = LayoutInflater.from(activity).inflate(R.layout.mh_specification_tv, colorFlowLayout, false);
                final TypeFaceView tv = view.findViewById(R.id.flow_tv);
                tv.setText(item.getAttr_value_title());
                tv.setSelected(item.isSelect());
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (item.isSelect()) return;

                        for (CommodityDetailsEntity.SkuAttrListBean.AttrValuesBean bean : colorList)
                            if (bean.isSelect())
                                bean.setSelect(false);

                        item.setSelect(true);
                        colorSku = entity.getSku_attr_list().get(0).getAttr_id() + ":" + item.getAttr_value_id();
                        colorAdapter.notifyDataChanged();

                        if (TextUtils.isEmpty(sizeSku)) return;
                        changeStock();
                    }
                });
                return view;
            }
        };
        colorFlowLayout.setAdapter(colorAdapter);
    }

    private void setSizeFlowLayout() {

        sizeList = entity.getSku_attr_list().get(1).getAttr_values();

        sizeAdapter = new TagAdapter<CommodityDetailsEntity.SkuAttrListBean.AttrValuesBean>(sizeList) {
            @Override
            public View getView(FlowLayout parent, final int position, final CommodityDetailsEntity.SkuAttrListBean.AttrValuesBean item) {
                View view = LayoutInflater.from(activity).inflate(R.layout.mh_specification_tv, sizeFlowLayout, false);
                final TypeFaceView tv = view.findViewById(R.id.flow_tv);
                tv.setText(item.getAttr_value_title());
                tv.setSelected(item.isSelect());
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (item.isSelect()) return;

                        for (CommodityDetailsEntity.SkuAttrListBean.AttrValuesBean bean : sizeList)
                            if (bean.isSelect())
                                bean.setSelect(false);

                        item.setSelect(true);
                        sizeSku = entity.getSku_attr_list().get(1).getAttr_id() + ":" + item.getAttr_value_id();
                        sizeAdapter.notifyDataChanged();

                        if (TextUtils.isEmpty(colorSku)) return;
                        changeStock();
                    }
                });
                return view;
            }
        };
        sizeFlowLayout.setAdapter(sizeAdapter);
    }

    private void changeStock() {

       String key = colorSku + "," + sizeSku;

        for (CommodityDetailsEntity.SkuListBean bean : entity.getSku_list())
            if (bean.getSku().equals(key)) {

                numberTv.setText(bean.getStock() > 0 ? "1" : "0");
                stockTv.setText(ResourceUtils.getFormatText(R.string.hint_stock, bean.getStock()));
                GlideUtils.loadImage(activity, bean.getShow_thumb(), coverIv);
                skuBean=bean;

                break;
            }

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.close_iv)
            dismiss();

        if (v.getId() == R.id.subtraction_iv) {
            int number = Integer.parseInt(numberTv.getText().toString());
            if (number == 0) return;
            numberTv.setText(String.valueOf(number - 1));
        }

        if (v.getId() == R.id.add_iv) {
            int number = Integer.parseInt(numberTv.getText().toString());
            if (number == skuBean.getStock()) {
                ToastUtils.showToast(activity, ResourceUtils.getText(R.string.hint_stock_null));
                return;
            }
            numberTv.setText(String.valueOf(number + 1));
        }

        if (v.getId() == R.id.shopping_add_rl) {

            if (skuBean==null) {
                ToastUtils.showToast(activity, ResourceUtils.getText(R.string.hint_sheet_stock));
                return;
            }

            if (skuBean.getStock() == 0) {
                ToastUtils.showToast(activity, ResourceUtils.getText(R.string.hint_stock_null));
                return;
            }

            listener.onCancel(v, skuBean.getId(), Integer.parseInt(numberTv.getText().toString()));
        }

        if (v.getId() == R.id.buy_rl) {

            if (skuBean==null) {
                ToastUtils.showToast(activity, ResourceUtils.getText(R.string.hint_sheet_stock));
                return;
            }

            if (skuBean.getStock() == 0) {
                ToastUtils.showToast(activity, ResourceUtils.getText(R.string.hint_stock_null));
                return;
            }

            listener.onClick(v, skuBean, Integer.parseInt(numberTv.getText().toString()));
        }
    }
}
