package com.yizhishang.base.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 描述: 数字工具类
 * 版权: Copyright (c) 2010
 * 公司: 思迪
 * 作者:	 袁永君
 * 版本: 1.0
 * 创建日期: Mar 10, 2010
 * 创建时间: 11:47:54 AM
 */
public class NumHelper
{

    public static String decimalFormat(String str)
    {
        if (StringHelper.isNotEmpty(str) && str.indexOf("E") > -1) {
            DecimalFormat df = new DecimalFormat("0");
            str = df.format(Double.parseDouble(str));
        }
        return str;
    }

    /**
     * 描述：通过一个整数i获取你所要的哪几个(从0开始)
     * i为 多个2的n次方之和，如i=7，那么根据原值是2的n次方之各，你的原值必定是1，2，4 。
     * 作者：李建
     * 时间：Feb 6, 2009 6:35:51 PM
     *
     * @param i 数值
     * @return
     */
    public static List<Integer> getWhich(long i)
    {
        List<Integer> aTemp = new ArrayList<Integer>();
        for (int n = 0; (1 << n) < i + 1; n++) {
            if ((1 << (n + 1)) > i && (1 << n) < (i + 1)) {
                aTemp.add(new Integer(n));
                i -= 1 << n;
                if (i == 1) {
                    aTemp.add(new Integer(0));
                }
                n = 0;
            }
        }

        Collections.sort(aTemp);
        return aTemp;
    }

    /**
     * 描述：非四舍五入取整处理
     * 作者：李建
     * 时间：Nov 15, 2010 2:03:21 AM
     *
     * @param v 需要四舍五入的数字
     * @return
     */
    public static int roundDown(double v)
    {
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, 0, BigDecimal.ROUND_DOWN).intValue();
    }

    /**
     * 描述：四舍五入取整处理
     * 作者：李建
     * 时间：Nov 15, 2010 2:03:05 AM
     *
     * @param v 需要四舍五入的数字
     * @return
     */
    public static int roundUp(double v)
    {
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, 0, BigDecimal.ROUND_UP).intValue();
    }

    /**
     * 描述：提供精确的小数位四舍五入处理。
     * 作者：李建
     * 时间：Nov 15, 2010 2:02:29 AM
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale)
    {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }

        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
