package com.seven.module_user.ui.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.design.widget.BottomSheetDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.seven.lib_common.base.activity.BaseTitleActivity;
import com.seven.lib_common.utils.ToastUtils;
import com.seven.lib_model.ApiManager;
import com.seven.lib_model.BaseResult;
import com.seven.lib_model.CommonObserver;
import com.seven.module_user.R;
import com.seven.module_user.R2;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DefaultObserver;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class EditUserInfoActivity extends BaseTitleActivity {

    private BottomSheetDialog mBottomSheetDialog;
    private RxPermissions mRxPermissions;
    private String TAG = EditUserInfoActivity.class.getName();
    @BindView(R2.id.user_photo)
    TextView userPhoto;
    @BindView(R2.id.user_nick_name)
    TextView userNickName;
    @BindView(R2.id.choose_sex)
    TextView chooseSex;

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
    protected int getLayoutId() {
        return R.layout.mu_activity_edit_user_info;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleText(R.string.user_edit_info);
    }

    @Override
    protected void rightTextBtnClick(View v) {

    }

    @Override
    protected void rightBtnClick(View v) {

    }

    @Override
    protected void initBundleData(Intent intent) {
        mRxPermissions = new RxPermissions(this);
        mRxPermissions.setLogging(true);
    }

    private void chooseSex() {
        final List<String> sexList = new ArrayList<>();
        sexList.add("男");
        sexList.add("女");
        OptionsPickerView sexPickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                chooseSex.setText(sexList.get(options1));
            }
        }).setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                .setSelectOptions(0)//默认选中项
                .setBgColor(Color.WHITE)
                .setTitleBgColor(getResources().getColor(R.color.color_eee))
                .setCancelColor(getResources().getColor(R.color.color_6c))
                .setSubmitColor(getResources().getColor(R.color.color_1e1d1d))
                .setTextColorCenter(getResources().getColor(R.color.color_1e1d1d))
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setOutSideColor(0x00000000) //设置外部遮罩颜色
                .build();
        sexPickerView.setPicker(sexList);
        sexPickerView.show();
    }


    @OnClick({R2.id.user_photo, R2.id.choose_sex})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.user_photo) {
            onChangePic();
        } else if (view.getId() == R.id.choose_sex) {
            chooseSex();
        }
    }

    private void onChangePic() {
        mBottomSheetDialog = new BottomSheetDialog(this);
        View v = LayoutInflater.from(this).inflate(R.layout.dialog_choice_img, null, false);
        v.findViewById(R.id.photo).setOnClickListener(this);
        v.findViewById(R.id.camera).setOnClickListener(this);
        v.findViewById(R.id.cancel).setOnClickListener(this);
        mBottomSheetDialog.setContentView(v);
        mBottomSheetDialog.show();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.cancel) {
            mBottomSheetDialog.dismiss();
        } else if (view.getId() == R.id.photo) {
            selectGallery();
            mBottomSheetDialog.dismiss();
        } else if (view.getId() == R.id.camera) {
            selectCapture();
            mBottomSheetDialog.dismiss();
        }
    }

    //选择相册
    public void selectGallery() {
        mRxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                            intent.setType("image/*");
                            startActivityForResult(intent, 10086);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void selectCapture() {
        mRxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//action is capture
                            intent.putExtra("return-data", false);
                           // intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
                            intent.putExtra("noFaceDetection", true);
                            startActivityForResult(intent, 10010);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri uri;
            String realUri;
            File file;
            switch (requestCode) {
                case 10086:
                    //选择照片
                    uri = data.getData();
                    realUri = uri2filePath(uri, mContext);
                    file = new File(realUri);
                    upLoad(file);
                    break;
                case 10010:
                    //拍照
                    uri = data.getData();
                    realUri = uri2filePath(uri, mContext);
                    file = new File(realUri);
                    upLoad(file);
                    break;

            }
        }
    }

    public String uri2filePath(Uri uri, Context context) {
        String path = "";
        if (uri.toString().startsWith("file")) {
            return uri.getPath();
        }
        Cursor cursor = null;
        try {
            //android 4.4.4会获得另外一种图片uri地址:
            if (Build.VERSION.SDK_INT >= 19 && DocumentsContract.isDocumentUri(context, uri)) {
                String wholeID = DocumentsContract.getDocumentId(uri);
                String id = wholeID.split(":")[1];
                String[] column = {MediaStore.Images.Media.DATA};
                String sel = MediaStore.Images.Media._ID + "=?";
                cursor = context.getContentResolver().query(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, column, sel,
                        new String[]{id}, null);
                int columnIndex = cursor.getColumnIndex(column[0]);
                if (cursor.moveToFirst()) {
                    path = cursor.getString(columnIndex);
                }
            } else {
                String[] projection = {MediaStore.MediaColumns.DATA};
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                if (cursor.moveToFirst()) {
                    path = cursor.getString(column_index);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        } finally {
            Log.i(TAG, "imageUrl:" + path);
            cursor.close();
        }
        return path;
    }

    private void upLoad(File file) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.create(requestBody);
        ApiManager.upLoad(body)
                .subscribe(new CommonObserver<BaseResult>() {
                    @Override
                    public void onNext(BaseResult baseResult) {
                        ToastUtils.showToast(mContext, baseResult.getMessage());
                    }
                });
    }
}
