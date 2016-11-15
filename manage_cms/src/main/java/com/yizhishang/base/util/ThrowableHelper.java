package com.yizhishang.base.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 描述:	 异常工具类
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-6
 * 创建时间: 14:40:59
 */
public class ThrowableHelper
{

    /**
     * 把异常和错误的信息转化为字串
     *
     * @param throwObj Throwable类或接口
     * @return
     */
    public static String toString(Throwable throwObj)
    {
        StringWriter writer = new StringWriter();
        PrintWriter pWriter = new PrintWriter(writer);
        throwObj.printStackTrace(pWriter);
        return writer.getBuffer().toString();
    }

    /**
     * 显示调用者
     */
    public static void getCaller()
    {
        StackTraceElement stack[] = (new Throwable()).getStackTrace();
        for (int i = 0; i < stack.length; i++) {
            StackTraceElement ste = stack[i];
            System.out.println(ste.getClassName() + "." + ste.getMethodName() + "(...)");
            System.out.println(i + "--" + ste.getMethodName());
            System.out.println(i + "--" + ste.getFileName());
            System.out.println(i + "--" + ste.getLineNumber());
        }
    }

}
