package com.seven.lib_router;

/**
 * @auhtor seven
 * email:frankchenlei@163.com
 * 2019/3/12
 */

public class Constants {

    public static class SharedConfig {

        public static final String SHARED_NAME = "seven";

        public static final String FIRST = "first";
    }

    public static class BundleConfig {
        public static final String MEDIA = "media";
    }

    public static class TimeConfig {

        public static final int SPLASH_TIME = 2000;

        public static final int TRANS_TIME = 300;
        public static final int TRANS_TIME_LONG = 750;

        public static final int ROTATION_TIME = 300;
        public static final int ROTATION_TIME_LONG = 1000;

    }

    public static class AnimName {

        public static final String TRANS_X = "translationX";
        public static final String TRANS_Y = "translationY";
        public static final String ALPHA = "alpha";
        public static final String ROTATION = "rotation";
    }

    public static class RequestConfig {

    }

    public static class EventConfig {

    }

    public static class ResourceConfig {

    }

    public static class ShareConfig {


    }

    public static class PayConfig {

        public static final int PAY_ALI = 1;//支付宝
        public static final int PAY_WX = 2;//微信

        /**
         * 支付来源
         */
        public static final int STORE = 1;
        public static final int APP = 2;
        public static final int H5 = 3;

        /**
         * 支付方式
         */
        public static final String CASH = "cash";
        public static final String ALIPAY = "alipay";
        public static final String WECHAT = "wechat";
        public static final String CASH_CARD = "cashCard";

        /**
         * order type
         */
        public static final int MEMBER = 3;//会员卡

        /**
         * activity type
         */
        public static final int DISCOUNT = 1;//折扣
        public static final int DIRECT_REDUCTION = 2;//直减
    }

    public static class MediaConfig {

        public static final String TYPE_VIDEO_MP4 = "video/mp4";
        public static final String TYPE_IMAGE_PNG = "image/png";
        public static final String TYPE_IMAGE_JPG = "image/jpeg";

    }

    public static class PermissionConfig {

        public static final int SPLASH = 1;

    }
}
