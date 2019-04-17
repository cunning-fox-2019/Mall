package com.seven.lib_common.utils;

import java.text.DecimalFormat;

/**
 * @auhtor seven
 * email:frankchenlei@163.com
 * 2019/4/14
 */

public class FormatUtils {

    public static String formatCurrency(double price){
       return new DecimalFormat("0.0").format(price);
    }

}
