package com.jhcz.trade.fundManage.service.imp;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhcz.trade.common.base.DataRow;
import com.jhcz.trade.common.exception.BusinessException;
import com.jhcz.trade.common.wtc.CallWTC;
import com.jhcz.trade.fundManage.bean.FundInfo;
import com.jhcz.trade.fundManage.dao.FundMapper;
import com.jhcz.trade.fundManage.service.FundService;

@Service("fundService")
public class FundServiceImpl implements FundService{

	private Logger logger = Logger.getLogger(FundServiceImpl.class);
	
	@Autowired
	private FundMapper mapper;
	
	@Override
	public List<FundInfo> listAllFund(Map<String, String> params) throws BusinessException {
		return mapper.queryAllFund(params);
	}
	
	@Override
	public FundInfo getFundByCode(String fundCode) throws BusinessException {
		if(StringUtils.isBlank(fundCode)){
			return null;
		}
		return mapper.getFundByCode(fundCode);
	}
	
	@Override
	public List<DataRow> getDepositBankCard(String clientNo) throws BusinessException {
		return mapper.getDepositBankCard(clientNo);
	}
	
	@Override
	public List<DataRow> queryFundInvest(DataRow params) throws BusinessException {
		try {
			CallWTC.execute("", params);
		} catch (Exception e) {
			logger.error("基金投资记录查询错误，"+e);
		}
		
		return null;
	}
	
	@Override
	public void saveFundInfo(DataRow fundInfo) throws BusinessException {
		
		try {
			mapper.insertFund(fundInfo);
		} catch (Exception e) {
			throw new BusinessException("110", e.getMessage());
		}
	}
	
	@Override
	public int fundIsExist(String fundCode) throws BusinessException {
		try {
			return mapper.fundIsExists(fundCode);
		} catch (Exception e) {
			throw new BusinessException("120", e.getMessage());
		}
	}

}
