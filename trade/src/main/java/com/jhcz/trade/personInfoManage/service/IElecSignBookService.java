package com.jhcz.trade.personInfoManage.service;

import java.util.Map;

import com.jhcz.trade.common.base.DataRow;

/**
 * 
 * @Description：电子签名书接口
 * @author 邱文豪
 * @created 2016年3月30日 上午10:35:52
 */
public interface IElecSignBookService {
    /**
     * 
     * @Description：同意签署
     * @author 邱文豪
     * @created 2016年3月30日 上午10:37:40 
     * @param dr
     * @return
     * @throws Exception
     */
	public Map agreeSign(DataRow dr ) throws Exception;	
	
	/**
     * 
     * @Description：取消签署
     * @author 邱文豪
     * @created 2016年3月30日 上午10:37:40 
     * @param dr
     * @return
     * @throws Exception
     */
	public Map unAgreeSign(DataRow dr ) throws Exception;	
	
}
