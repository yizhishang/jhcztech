package com.jhcz.trade.account.biz.service;

import java.util.Map;

import com.jhcz.trade.common.base.DataRow;

/**
 * @类名: MemberService
 * @包名 com.jhcz.trade.account.biz.service
 * @描述: 会员中心接口
 * @版权: Copyright (c) 2016 
 * @公司:深圳市金恒创智信息技术有限公司
 * @作者 zhonghang
 * @时间 2016-4-20 上午10:38:10
 * @版本 V1.0
 */
public interface MemberService {
	
	/**
	  * @方法名: queryCustAssets
	  * @描述: 查询客户资产
	  * @参数 @param params
	  * @参数 @return
	  * @参数 @throws Exception   
	  * @返回类型: Map 
	  * @throws
	  * @时间:2016-4-20上午10:41:10
	  * @作者: zhonghang
	 */
	public Map queryCustAssets(DataRow params) throws Exception;
}
