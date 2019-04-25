package com.seven.lib_model.model.extension;

import java.util.List;

/**
 * Created by xxxxxxH on 2019/4/25.
 */
public class BdGoodsEntity {
    private double price;
    private List<GoodsItemEntity> goods_list;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<GoodsItemEntity> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsItemEntity> goods_list) {
        this.goods_list = goods_list;
    }
}
