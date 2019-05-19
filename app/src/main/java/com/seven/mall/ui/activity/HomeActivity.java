package com.seven.mall.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.seven.lib_common.base.activity.BaseAppCompatActivity;
import com.seven.lib_common.task.ActivityStack;
import com.seven.lib_model.model.user.RegisterEntity;
import com.seven.lib_opensource.event.Event;
import com.seven.lib_opensource.event.ObjectsEvent;
import com.seven.lib_router.Constants;
import com.seven.lib_router.Variable;
import com.seven.lib_router.db.shard.SharedData;
import com.seven.lib_router.router.RouterPath;
import com.seven.lib_router.router.RouterUtils;
import com.seven.mall.R;
import com.seven.mall.application.MallApplication;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;

/**
 * @auhtor seven
 * email:frankchenlei@163.com
 * 2019/3/26
 */
@Route(path = RouterPath.ACTIVITY_HOME)
public class HomeActivity extends BaseAppCompatActivity {

    @BindView(R.id.home_actionbar_rl)
    public RelativeLayout tabBar;

    @BindView(R.id.home_container_fl)
    public FrameLayout frameLayout;

    @BindView(R.id.tab_home_ll)
    public LinearLayout tabHome;
    @BindView(R.id.tab_extension_ll)
    public LinearLayout tabExtension;
    @BindView(R.id.tab_model_ll)
    public LinearLayout tabModel;
    @BindView(R.id.tab_user_ll)
    public LinearLayout tabUser;

    private Fragment homeFg;
    private Fragment extensionFg;
    private Fragment modelFg;
    private Fragment userFg;
    private Fragment fromFg;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        EventBus.getDefault().register(this);

        ActivityStack.getInstance().finishActivity(SplashActivity.class);
    }

    @Override
    protected void initBundleData(Intent intent) {

        homeFg = (Fragment) ARouter.getInstance().build(RouterPath.FRAGMENT_HOME).navigation();
        changeTabSelected(tabHome, null, homeFg);

        initAppValue();
    }

    private void initAppValue() {

        MallApplication.getInstance().setToken(SharedData.getInstance().getToken());
        Variable.getInstance().setToken(SharedData.getInstance().getToken());
    }

    public void btClick(View view) {

        LinearLayout tabLayout = null;
        Fragment fragment = null;

        switch (view.getId()) {

            case R.id.tab_home_ll:

                if (homeFg == null)
                    homeFg = (Fragment) ARouter.getInstance().build(RouterPath.FRAGMENT_HOME).navigation();

                fragment = homeFg;
                tabLayout = tabHome;

                break;

            case R.id.tab_extension_ll:

                if (extensionFg == null)
                    extensionFg = (Fragment) ARouter.getInstance().build(RouterPath.FRAGMENT_EXTENSION).navigation();

                fragment = extensionFg;
                tabLayout = tabExtension;

                break;

            case R.id.tab_model_ll:

                if (modelFg == null)
                    modelFg = (Fragment) ARouter.getInstance().build(RouterPath.FRAGMENT_MODEL).navigation();

                fragment = modelFg;
                tabLayout = tabModel;

                break;

            case R.id.tab_user_ll:

                if (userFg == null)
                    userFg = (Fragment) ARouter.getInstance().build(RouterPath.FRAGMENT_USER).navigation();

                fragment = userFg;
                tabLayout = tabUser;

                break;
        }

        if (tabLayout != null && fragment != null)
            changeTabSelected(tabLayout, fromFg, fragment);

    }

    private void changeTabSelected(LinearLayout layout, Fragment from, Fragment to) {
        if (!layout.isSelected()) {
            tabHome.setSelected(layout == tabHome);
            tabExtension.setSelected(layout == tabExtension);
            tabModel.setSelected(layout == tabModel);
            tabUser.setSelected(layout == tabUser);
            switchFragment(R.id.home_container_fl, from, to);
            fromFg = to;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Event event) {
        switch (event.getWhat()) {

            case Constants.EventConfig.LOGIN:
            case Constants.EventConfig.REGISTER:

                RegisterEntity registerEntity = (RegisterEntity) ((ObjectsEvent) event).getObjects()[0];

                if (registerEntity == null) return;

//                MallApplication.getInstance().setAlias("seven");

                MallApplication.getInstance().setToken(registerEntity.getToken());
                Variable.getInstance().setToken(registerEntity.getToken());
                SharedData.getInstance().setToken(registerEntity.getToken());

                JPushInterface.getRegistrationID(this);

                break;

            case Constants.EventConfig.LOGOUT:
//                if (homeFg == null)
//                    homeFg = (Fragment) ARouter.getInstance().build(RouterPath.FRAGMENT_HOME).navigation();
//                changeTabSelected(tabHome, fromFg, homeFg);
//                MallApplication.getInstance().clearAlias();

                break;

            case MallApplication.EVENT_CODE:

                RouterUtils.getInstance().routerNormal(RouterPath.ACTIVITY_LOGIN);

                break;
            case Constants.EventConfig.ADDRESS:

                break;

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

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
