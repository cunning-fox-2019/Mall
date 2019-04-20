package com.seven.lib_model.user;

import com.seven.lib_common.base.fragment.BaseFragment;
import com.seven.lib_common.mvp.presenter.BasePresenter;
import com.seven.lib_common.mvp.view.IBaseView;
import com.seven.lib_http.observer.HttpRxObservable;
import com.seven.lib_http.observer.HttpRxObserver;
import com.seven.lib_model.http.RequestHelper;
import com.seven.lib_model.model.user.UserEntity;
import com.trello.rxlifecycle2.android.FragmentEvent;

/**
 * Created by ouyang on 2019/3/31.
 */

public class UserPresenter extends BasePresenter<IBaseView,BaseFragment>{
    public UserPresenter(IBaseView view, BaseFragment activity) {
        super(view, activity);
    }

    public void getUserInfo(int requestCode,String name){
        HttpRxObserver rxObserver = getList(getView(),requestCode, UserEntity.class,"data",true);
        if (rxObserver == null)
            return;
       // HttpRxObservable.getObservable(RequestHelper.getInstance().getUserInfo(name),getActivity(), FragmentEvent.PAUSE).subscribe(rxObserver);
    }
}