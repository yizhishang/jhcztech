package com.jhcz.trade.fundManage.service;

import java.util.List;
import java.util.Map;

import com.jhcz.trade.common.base.DataRow;
import com.jhcz.trade.common.exception.BusinessException;
import com.jhcz.trade.fundManage.bean.FundInfo;

public interface FundService {

	/**
	 * 查询所有基金产品
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<FundInfo> listAllFund(Map<String,String> params) throws BusinessException;
	
	
	/**
	 * 通过基金代码查询基金信息
	 * @param fundCode
	 * @return
	 * @throws Exception
	 */
	public FundInfo getFundByCode(String fundCode) throws BusinessException;
	
	/**
	 * 获取银行卡信息
	 * @param clientNo
	 * @return
	 * @throws Exception
	 */
	public List<DataRow> getDepositBankCard(String clientNo) throws BusinessException;
	
	/**
	 * 基金投资记录查询
	 * @param params
	 * @return
	 * @throws BusinessException
	 */
	public List<DataRow> queryFundInvest(DataRow params) throws BusinessException;
	
	/**
	 * 同步基金信息
	 * @param fundInfo
	 * @throws BusinessException
	 */
	public void saveFundInfo(DataRow fundInfo) throws BusinessException;
	
	/**
	 * 基金是否存在
	 * @param fundCode
	 * @return
	 * @throws BusinessException
	 */
	public int fundIsExist(String fundCode) throws BusinessException;
}
