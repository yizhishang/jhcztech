package com.jhcz.trade.account.biz.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.jhcz.trade.account.biz.service.MemberService;
import com.jhcz.trade.common.base.DataRow;
import com.jhcz.trade.common.wtc.CallWTC;

/**
 * @类名: MemberServiceImpl
 * @包名 com.jhcz.trade.account.biz.service.impl
 * @描述: 会员中心接口实现类
 * @版权: Copyright (c) 2016 
 * @公司:深圳市金恒创智信息技术有限公司
 * @作者 zhonghang
 * @时间 2016-4-20 上午10:37:44
 * @版本 V1.0
 */
@Service("memberService")
public class MemberServiceImpl implements MemberService{
	
	/**
	 * 客户资产查询
	 */
	@Override
	public Map queryCustAssets(DataRow params) throws Exception {
		return CallWTC.execute("1002410014", params);
	}

}
