package com.seven.lib_model.presenter.model;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.seven.lib_common.base.activity.BaseActivity;
import com.seven.lib_common.mvp.presenter.BasePresenter;
import com.seven.lib_common.mvp.view.IBaseView;
import com.seven.lib_http.observer.HttpRxObservable;
import com.seven.lib_http.observer.HttpRxObserver;
import com.seven.lib_model.builder.common.PayPasswordBuilder;
import com.seven.lib_model.builder.model.BusinessBuilder;
import com.seven.lib_model.builder.model.BusinessOrderListBuilder;
import com.seven.lib_model.http.RequestHelper;
import com.seven.lib_model.model.model.BusinessEntity;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * @auhtor seven
 * email:frankchenlei@163.com
 * 2019/5/5
 */

public class ActModelPresenter extends BasePresenter<IBaseView, BaseActivity> {
    public ActModelPresenter(IBaseView view, BaseActivity activity) {
        super(view, activity);
    }

    public void payPassword(int requestCode, String password) {

        PayPasswordBuilder.Builder builder = new PayPasswordBuilder.Builder();
        PayPasswordBuilder json = builder
                .pay_password(password)
                .build();
        String jsonStr = new Gson().toJson(json);
        Logger.i(jsonStr);

        HttpRxObserver rxObserver = get(getView(), requestCode);
        if (rxObserver == null)
            return;
        HttpRxObservable.getObservable(RequestHelper.getInstance().payPassword(jsonStr), getActivity(), ActivityEvent.PAUSE).subscribe(rxObserver);
    }

    public void business(int requestCode, int type, double token, double price, String ali, String wx) {

        BusinessBuilder.Builder builder = new BusinessBuilder.Builder();
        BusinessBuilder json = builder
                .type(type)
                .token_number(token)
                .price(price)
                .ali_account(ali)
                .wx_account(wx)
                .build();
        String jsonStr = new Gson().toJson(json);
        Logger.i(jsonStr);

        HttpRxObserver rxObserver = get(getView(), requestCode);
        if (rxObserver == null)
            return;
        HttpRxObservable.getObservable(RequestHelper.getInstance().business(jsonStr), getActivity(), ActivityEvent.PAUSE).subscribe(rxObserver);
    }

    public void businessOrderList(int requestCode, int type, int status, int page, int pageSize) {

        BusinessOrderListBuilder.Builder builder = new BusinessOrderListBuilder.Builder();
        BusinessOrderListBuilder json = (BusinessOrderListBuilder) builder
                .type(type)
                .status(status)
                .page(page)
                .page_size(pageSize)
                .build();
        String jsonStr = new Gson().toJson(json);
        Logger.i(jsonStr);

        HttpRxObserver rxObserver = get(getView(), requestCode, BusinessEntity.class, "items", true);
        if (rxObserver == null)
            return;
        HttpRxObservable.getObservable(RequestHelper.getInstance().businessOrderList(jsonStr), getActivity(), ActivityEvent.PAUSE).subscribe(rxObserver);
    }

}
