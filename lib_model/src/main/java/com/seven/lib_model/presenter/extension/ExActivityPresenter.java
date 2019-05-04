package com.seven.lib_model.presenter.extension;

import com.google.gson.Gson;
import com.seven.lib_common.base.activity.BaseActivity;
import com.seven.lib_common.mvp.presenter.BasePresenter;
import com.seven.lib_common.mvp.view.IBaseView;
import com.seven.lib_http.observer.HttpRxObservable;
import com.seven.lib_http.observer.HttpRxObserver;
import com.seven.lib_model.builder.common.PageBuilder;
import com.seven.lib_model.builder.extension.InviteBuilder;
import com.seven.lib_model.http.RequestHelper;
import com.seven.lib_model.model.extension.InComeDetailsEntity;
import com.seven.lib_model.model.extension.MyInterViewEntity;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * Created by xxxxxxH on 2019/5/3.
 */
public class ExActivityPresenter extends BasePresenter<IBaseView, BaseActivity> {
    public ExActivityPresenter(IBaseView view, BaseActivity activity) {
        super(view, activity);
    }

    public void invite(int code, int user_id, int page, int page_size) {
        InviteBuilder.Builder builder = new InviteBuilder.Builder();
        InviteBuilder json = builder
                .user_id(user_id)
                .page(page)
                .page_size(page_size)
                .build();
        String jsonStr = new Gson().toJson(json);
        HttpRxObserver rxObserver = get(getView(), code, MyInterViewEntity.class, null, false);
        if (rxObserver == null) return;
        HttpRxObservable.getObservable(RequestHelper.getInstance().inviteList(jsonStr), getActivity(), ActivityEvent.PAUSE).subscribe(rxObserver);
    }

}
