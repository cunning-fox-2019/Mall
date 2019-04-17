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

    /* module extension */
    public static final String FRAGMENT_EXTENSION = "/extension/ExtensionFragment";

    /* module model */
    public static final String FRAGMENT_MODEL = "/model/ModelFragment";

    /* module user */
    public static final String FRAGMENT_USER = "/user/UserFragment";

    public static final String ACTIVITY_LOGIN="/user/LoginActivity";
    public static final String ACTIVITY_REGISTER="/user/RegisterActivity";
    public static final String ACTIVITY_PASSWORD="/user/PasswordActivity";
    public static final String ACTIVITY_MOBILE="/user/MobileActivity";
}
