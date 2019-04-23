package com.seven.lib_router.router;

/**
 * @auhtor seven
 * email:frankchenlei@163.com
 * 2018/3/7
 */

public class RouterPath {

    public static final String SERVICE_JSON = "/service/JsonServiceImpl";

    /* module app */
    public static final String ACTIVITY_HOME = "/app/HomeActivity";


    /* module common */
//    public static final String ACTIVITY_COMMON_SELECT = "/common/CommonSelectActivity";


    /* module home */
    public static final String FRAGMENT_HOME = "/home/HomeFragment";
    public static final String ACTIVITY_COMMODITY="/home/CommodityListActivity";
    public static final String ACTIVITY_COMMODITY_DETAILS="/home/CommodityDetailsActivity";
    public static final String ACTIVITY_COMMODITY_ORDER="/home/CommodityOrderActivity";
    public static final String ACTIVITY_PAY="/home/PayActivity";

    /* module extension */
    public static final String FRAGMENT_EXTENSION = "/extension/ExtensionFragment";

    /* module model */
    public static final String FRAGMENT_MODEL = "/model/ModelFragment";
    public static final String ACTIVITY_RELEASE_DEMAND = "/model/ReleaseDemandActivity";
    public static final String ACTIVITY_MESSAGE= "/model/MessageActivity";
    public static final String ACTIVITY_VOUCHER= "/model/VoucherActivity";
    public static final String ACTIVITY_TRANSACTION_DETAILS= "/model/TransactionDetailsActivity";
    public static final String ACTIVITY_UPLOAD_VOUCHER= "/model/UploadVoucherActivity";

    /* module user */
    public static final String FRAGMENT_USER = "/user/UserFragment";

    public static final String ACTIVITY_LOGIN="/user/LoginActivity";
    public static final String ACTIVITY_REGISTER="/user/RegisterActivity";
    public static final String ACTIVITY_PASSWORD="/user/PasswordActivity";
    public static final String ACTIVITY_MOBILE="/user/MobileActivity";
}
