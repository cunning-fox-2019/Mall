package com.seven.lib_common.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;


import com.seven.lib_common.R;
import com.seven.lib_common.stextview.TypeFaceView;
import com.seven.lib_opensource.application.SSDK;
import com.seven.lib_opensource.application.SevenApplication;

import java.lang.ref.WeakReference;

/**
 * @auhtor seven
 * email:frankchenlei@163.com
 * 2017/10/24
 */

public class ToastUtils {

    public static final int OFFSET_X = 0;

    public static final int OFFSET_Y = 40;

    private static WeakReference<Toast> mToastRefrence;

    public static void hide() {
        Toast toast;
        if (mToastRefrence != null) {
            toast = mToastRefrence.get();
            if (toast != null) {
                toast.cancel();
            }
        }
    }

    public static void toast(Context context, String text) {
        showToast(context, text, text.length() > 8 ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
    }

    public static void toast(Context context, int textRes) {
        toast(context, context.getString(textRes));
    }


    public static void showToast(Context context, int resId, int duration) {
        showToast(context, context.getString(resId), duration);
    }

    public static void showToast(Context context, String text, int duration) {
        Toast toast = null;
        if (mToastRefrence == null) {
            toast = Toast.makeText(context, text, duration);
            mToastRefrence = new WeakReference<>(toast);
        }
        toast = mToastRefrence.get();
        if (toast == null) {
            toast = Toast.makeText(context, text, duration);
            mToastRefrence = new WeakReference<>(toast);
        } else {
            toast.setText(text);
            toast.setDuration(duration);
        }
        toast.show();
    }

    public static void showToast(Context context, String text) {

        Toast myToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);

        View toastRoot = LayoutInflater.from(context).inflate(R.layout.lb_toast,
                null);
        TypeFaceView message_tv = toastRoot.findViewById(R.id.message_tv);
        message_tv.setText(text);

        myToast.setGravity(Gravity.BOTTOM, OFFSET_X, ScreenUtils.dip2px(SSDK.getInstance().getContext(), OFFSET_Y));
        myToast.setDuration(Toast.LENGTH_SHORT);
        myToast.setView(toastRoot);

        myToast.show();

    }

}
