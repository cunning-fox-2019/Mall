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
        public static final String TOKEN = "token";
    }

    public static class BundleConfig {
        public static final String ENTITY = "entity";


        public static final String FLOW = "flow";
        public static final int FLOW_ENTRANCE = 1;
        public static final int FLOW_SEARCH = 2;

        public static final String TYPE = "type";
        public static final int TYPE_SELL = 1;
        public static final int TYPE_BUY = 2;

        public static final String ID = "id";
        public static final String EVENT_CODE="eventCode";
        public static final int EVENT_CODE_INT= 10000;
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

        /* common */
        public static final int SMS = 1000;

        /*home*/
        public static final int BANNER = 3001;
        public static final int ENTRANCE = 3002;
        public static final int COMMODITY_LIST = 3003;
        public static final int COMMODITY_DETAILS = 3004;
        public static final int CART_TOTAL = 3005;
        public static final int CART_ADD = 3006;
        public static final int ORDER_PAYMENT = 3007;
        public static final int ORDER_ADD = 3008;
        public static final int CONTACT_DEFAULT = 3009;
        public static final int ORDER_PAY = 3010;

        /*user*/
        public static final int REGISTER = 6001;
        public static final int LOGIN = 6002;
        public static final int PASSWORD = 6003;

        public static final int USER_INFO = 300;
    }

    public static class EventConfig {

        public static final int REGISTER = 100;
        public static final int LOGIN = 101;

        /* 第三方sdk */
        public static final int PAY_RESULT = 1000;

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

    public static class SMSConfig {
        public static final String REGISTER = "register";
        public static final String PASSWORD = "password";
        public static final String PAY_PASSWORD = "pay_password";
    }

    public static class OrderConfig {

        public static final String CART="cart";
        public static final String GOODS_DETAILS="goods_detail";

        public static final String PAY_ALI="alipay";
        public static final String PAY_WX="wxpay";
        public static final String PAY_APP="tokenPay";

    }
}
