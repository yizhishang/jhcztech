/*
 * Copyright (c) 2007 Your Corporation. All Rights Reserved.
 */

package com.yizhishang.timerengine.util;

import java.util.Timer;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-13
 * 创建时间: 14:40:34
 */
public class TimerGenerate
{

    private static TimerGenerate instance = new TimerGenerate();

    private TimerGenerate()
    {
        super();
    }

    public static TimerGenerate getInstance()
    {
        return instance;
    }

    public Timer getTimer()
    {
        return new Timer();
    }

}
