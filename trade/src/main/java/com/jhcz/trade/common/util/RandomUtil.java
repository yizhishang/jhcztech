package com.jhcz.trade.common.util;

/**
 * @类名: RandomUtil
 * @包名 com.jhcz.trade.common.util
 * @描述: 产生一些随机数
 * @版权: Copyright (c) 2016 
 * @公司:深圳市金恒创智信息技术有限公司
 * @作者 zhonghang
 * @时间 2016-3-26 上午10:43:18
 * @版本 V1.0
 */
public class RandomUtil {
	
	/**
	  * @方法名: randNum
	  * @描述: 随机参数n位随机数
	  * @参数 @param n
	  * @参数 @return   
	  * @返回类型: String 
	  * @throws
	  * @时间:2016-3-26上午10:47:07
	  * @作者: zhonghang
	 */
	public static String randNum(int n){
		StringBuffer sbuffer = new StringBuffer("");
	      for(int i=0;i<n;i++ ) {
	    	  int num = (int)(10*(Math.random()));
	    	  sbuffer.append(num) ;
	      }
	      return sbuffer.toString();
	}
	
}
