package com.jhcz.trade.fundManage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.jhcz.trade.common.base.DataRow;
import com.jhcz.trade.common.exception.BusinessException;
import com.jhcz.trade.fundManage.bean.FundInfo;

public interface FundMapper {

	/**
	 * 分页查询所有基金产品
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<FundInfo> queryAllFund(Map<String, String> params) throws BusinessException;
	
	/**
	 * 通过基金代码获取基金信息
	 * @param fundCode
	 * @return
	 * @throws Exception
	 */
	public FundInfo getFundByCode(String fundCode) throws BusinessException;
	
	
	/**
	 * 查询客户银行卡信息
	 * @param clientNo
	 * @return
	 * @throws Exception
	 */
	public List<DataRow> getDepositBankCard(String custNo) throws BusinessException;
	
	/**
	 * 同步基金信息
	 * @param fundInfo
	 * @throws Exception
	 */
	public void insertFund(DataRow fundInfo) throws Exception;
	
	/**
	 * 基金是否存在
	 * @param fundCode
	 * @return
	 * @throws Exception
	 */
	@Select("select count(1) sc from t_os_fund_info t where t.fc_fundcode = #{fundCode}")
	public int fundIsExists(String fundCode) throws Exception;
	
}
