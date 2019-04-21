package com.seven.module_user.ui.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.seven.lib_common.base.activity.BaseAppCompatActivity;
import com.seven.lib_common.stextview.TypeFaceEdit;
import com.seven.lib_model.ApiManager;
import com.seven.lib_model.BaseResult;
import com.seven.lib_model.model.user.LoginEntity;
import com.seven.lib_model.model.user.TokenEntity;
import com.seven.lib_model.user.UserActivityPresenter;
import com.seven.lib_router.Constants;
import com.seven.lib_router.router.RouterPath;
import com.seven.lib_router.router.RouterUtils;
import com.seven.module_user.R;
import com.seven.module_user.R2;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @auhtor seven
 * email:frankchenlei@163.com
 * 2019/3/26
 */
@Route(path = RouterPath.ACTIVITY_LOGIN)
public class LoginActivity extends BaseAppCompatActivity {

    @BindView(R2.id.password_et)
    public TypeFaceEdit passwordEt;
    @BindView(R2.id.password_hide_btn)
    public RelativeLayout passwordHide;

    UserActivityPresenter presenter;

    @Override
    protected int getContentViewId() {
        return R.layout.mu_activity_login;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        presenter = new UserActivityPresenter(this, this);
    }

    @Override
    protected void initBundleData(Intent intent) {

    }

    public void btClick(View view) {

        if (view.getId() == R.id.close_btn)
            onBackPressed();
        else if (view.getId() == R.id.password_hide_btn) {
            passwordEt.setTransformationMethod(passwordHide.isSelected() ? PasswordTransformationMethod.getInstance() : HideReturnsTransformationMethod.getInstance());
            passwordHide.setSelected(!passwordHide.isSelected());
            passwordEt.setSelection(passwordEt.getText().toString().length());
        } else if (view.getId() == R.id.login_btn)
            login();
        else if (view.getId() == R.id.forget_btn)
            RouterUtils.getInstance().routerNormal(RouterPath.ACTIVITY_PASSWORD);
        else if (view.getId() == R.id.register_btn)
            RouterUtils.getInstance().routerNormal(RouterPath.ACTIVITY_REGISTER);


    }

    private void login() {
        //RouterUtils.getInstance().routerNormal(RouterPath.ACTIVITY_MOBILE);
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setPassword("159357");
        loginEntity.setPhone("15680609620");
        //  presenter.login(Constants.RequestConfig.LONGIN, "http://zhongfu.lerqin.com/login",loginEntity);
        ApiManager.login(loginEntity)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResult<TokenEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<TokenEntity> tokenEntityBaseResult) {
                        Log.e("token", tokenEntityBaseResult.getData().getToken());
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void result(int code, Boolean hasNextPage, String response, Object object) {
        super.result(code, hasNextPage, response, object);
        if (code == Constants.RequestConfig.LONGIN) {
            if (object == null) return;
            TokenEntity entity = (TokenEntity) object;
            Log.e("token", entity.getToken());

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

        dismissLoadingDialog();

    }

    @Override
    public void showToast(String msg) {

    }
}
