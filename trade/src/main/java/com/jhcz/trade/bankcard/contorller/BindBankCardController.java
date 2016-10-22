package com.jhcz.trade.bankcard.contorller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.jhcz.trade.account.biz.bean.UserInfo;
import com.jhcz.trade.account.utils.WebConstants;
import com.jhcz.trade.bankcard.service.BindBankCardService;
import com.jhcz.trade.common.base.DataRow;
import com.jhcz.trade.common.exception.InterfaceCallException;
import com.jhcz.trade.common.sms.SendPhoneMessage;
import com.jhcz.trade.common.util.RandomUtil;

/**
 * @类名: BindBankCardController
 * @包名 com.jhcz.trade.bankcard.contorller
 * @描述: 银行卡绑定控制类
 * @版权: Copyright (c) 2016
 * @公司:深圳市金恒创智信息技术有限公司
 * @作者 zhonghang
 * @时间 2016-3-16 下午5:42:00
 * @版本 V1.0
 */
@Controller
@RequestMapping("/bindBankCard.do")
public class BindBankCardController extends MultiActionController {

	private Logger logger = Logger.getLogger(BindBankCardController.class);
	@Autowired
	private BindBankCardService bindBankCardService;

	/**
	 * @方法名: doInputChenckInfo
	 * @描述: 第一次银行卡绑定
	 * @参数 @return
	 * @返回类型: ModelAndView
	 * @throws
	 * @作者: zhonghang
	 */
	@RequestMapping(params = "method=doInputChenckInfo")
	public ModelAndView doInputChenckInfo(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mva = new ModelAndView("/bindcard/checkIdentityInfo_jsp");// 用户信息校验页面
		return mva;
	}

	/**
	 * @方法名: doSetTradePsd
	 * @描述: 第一次银行卡绑定--设置交易密码
	 * @参数 @return
	 * @返回类型: ModelAndView
	 * @throws
	 * @作者: zhonghang
	 */
	@RequestMapping(params = "method=doSetTradePsd")
	public ModelAndView doSetTradePsd(HttpServletRequest request,HttpServletResponse response) {
		String name = request.getParameter("name");// 姓名
		String idCard = request.getParameter("idCard");// 身份证
		request.getSession(true).setAttribute("name", name);
		request.getSession(true).setAttribute("idCard", idCard);
		logger.info("doSetTradePsd ==== >  姓名：name=" + name + ",身份证=" + idCard);
		ModelAndView mva = new ModelAndView("/bindcard/setTradePsd_jsp");
		return mva;
	}

	/**
	 * @方法名: doChooseBank
	 * @描述: 第一次银行卡绑定--选择银行卡
	 * @参数 @param request
	 * @参数 @param response
	 * @参数 @return
	 * @返回类型: ModelAndView
	 * @throws
	 * @作者: zhonghang
	 */
	@RequestMapping(params = "method=doChooseBank")
	public ModelAndView doChooseBank(HttpServletRequest request,HttpServletResponse response,Model model) {
		// 设置交易密码
		String trade_paw = request.getParameter("trade_paw") == null ? "": request.getParameter("trade_paw").toString();// 交易密码
		String confirm_trade_paw = request.getParameter("confirm_trade_paw") == null ? "": request.getParameter("confirm_trade_paw").toString();// 确认交易密码
		request.getSession().setAttribute("trade_paw", trade_paw);
		request.getSession().setAttribute("confirm_trade_paw",confirm_trade_paw);
		model.addAttribute("method", "doBankCardCertification");
		logger.info("doSetTradePsd ==== >  交易密码：trade_paw=" + trade_paw+ ",确认交易密码：confirm_trade_paw=" + confirm_trade_paw);
		ModelAndView mva = new ModelAndView("/bindcard/chooseBank_jsp");
		return mva;
	}

	/**
	 * @方法名: doBankCardCertification
	 * @描述: 第一次银行卡绑定--银行卡认证 ,同时进行开基金账户
	 * @参数 @param request
	 * @参数 @param response
	 * @参数 @return
	 * @返回类型: ModelAndView
	 * @throws
	 * @作者: zhonghang
	 */
	@RequestMapping(params = "method=doBankCardCertification")
	public ModelAndView doBankCardCertification(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mva = new ModelAndView("/bindcard/bankCardCertification_jsp");
		// 银行编码
		String bankNo = request.getParameter("bankNo") == null ? "" : request.getParameter("bankNo").toString();
		logger.info("doBankCardCertification ==== > 银行编码:bankNo=" + bankNo);
		return mva;
	}

	/**
	 * @方法名: doOpenFundAccountSuccess
	 * @描述: 第一次银行卡绑定---开户成功页面
	 * @参数 @param request
	 * @参数 @param response
	 * @参数 @return
	 * @返回类型: ModelAndView
	 * @throws
	 * @时间 2016-3-18 上午10:51:47
	 * @作者: zhonghang
	 */
	@RequestMapping(params = "method=doOpenFundAccount")
	public ModelAndView doOpenFundAccountSuccess(HttpServletRequest request,HttpServletResponse response, Model model) {
		String name = request.getSession(true).getAttribute("name") == null ? "": request.getSession().getAttribute("name").toString();// 姓名
		String idCard = request.getSession(true).getAttribute("idCard") == null ? "": request.getSession().getAttribute("idCard").toString();// 身份证号码
		// String trade_paw = request.getParameter("trade_paw") == null ? "" :
		// request.getSession().getAttribute("trade_paw").toString();//交易密码
		String bankCardID = request.getParameter("bankCardID") == null ? "": request.getParameter("bankCardID").toString();// 银行卡号
		String openAccountAddress = request.getParameter("openAccountAddress") == null ? "": request.getParameter("openAccountAddress").toString();// 开户地址
		String telphone = request.getParameter("telphone") == null ? "": request.getParameter("telphone").toString();// 电话号码
		String prov = request.getParameter("prov");// 省份
		String city = request.getParameter("city");// 城市
		logger.info("++++++++++++++++获取用户登录信息开始 start++++++++++++++++++++++++++");
		UserInfo userInfo = (UserInfo)request.getSession().getAttribute(WebConstants.LOGIN_USER);
		logger.info("++++++++++++++++获取用户登录信息开始 end+++++++++++++++++++++++++userInfo="+userInfo);
		// 银行卡信息校验
		Map<String, String> param = new HashMap<String, String>();
		param.put("name", name);
		param.put("idCard", idCard);
		param.put("bankCardID", bankCardID);
		param.put("openAccountAddress", openAccountAddress);
		param.put("telphone", telphone);
		bindBankCardService.checkBankCardInfo(param);// 银行卡信息校验
		// ==============开户 2016年3月18日18:59:24 start===================
		Map<String, String> openAccountParam = new HashMap<String, String>();
		// openAccountParam.put("TRADE_ACCO", "2016031");
		openAccountParam.put("SZACCO", "0123");// 深交所证券账号
		openAccountParam.put("SHACCO", "0123");// 上交所证券账号
		openAccountParam.put("PHONE_NO", telphone);// 电话号码
		openAccountParam.put("PROVINCE", "02");// 省份
		openAccountParam.put("CITY", "01");// 城市
		openAccountParam.put("CAPITAL_MODE", "1");// 资金方式
		openAccountParam.put("BANK_ACCT_NAME", "95421");// 银行名称
		openAccountParam.put("BANK_NO", "123");// 银行编号
		openAccountParam.put("BANK_ACCT", bankCardID);// 银行账号
		openAccountParam.put("COME_FROM", "01");// 来源
		openAccountParam.put("BRANCH_CODE", "002001");// 网点号
		openAccountParam.put("ADDRESS", openAccountAddress);// 开户地址
		openAccountParam.put("CUST_NAME", name);// 客户名称
		openAccountParam.put("ID_TYPE", "1");// 证件类型
		openAccountParam.put("ID_CODE", idCard);// 证件号码
		openAccountParam.put("CUST_TYPE", "1");// 客户类型 ,1机构 2个人
		ModelAndView mva = new ModelAndView("/bindcard/openFuncAccountSuccess_jsp");
		Map result = null;
		try{
			  logger.info("++++++++++++++++银行卡绑卡开户开始 start++++++++++++++++++参数为："+openAccountParam);
			  result = bindBankCardService.openFundAccount(openAccountParam);
			  logger.info("++++++++++++++++银行卡绑卡开户结束 end++++++++++++++++++++返回结果为："+result);
		}catch(InterfaceCallException ex){
			throw new InterfaceCallException("100000","银行卡绑定接口错误:"+ex.toString());
		}
		List<Object> message =(List<Object>)result.get("MESSAGE");
		Map msgResult = null;
		//返回信息
		String msg = "";
		//返回码
		String code = "";
		//资金账号
		String cash_acct = "";
		//客户号
		String cust_no = "";
		if (message != null && message.size()>0) {
			msgResult = (Map)message.get(0);
			msg = msgResult.get("MSG")+"";
			code = msgResult.get("CODE")+"";
			cash_acct = msgResult.get("CASH_ACCT")+"";
			cust_no = msgResult.get("CUST_NO")+"";
			
		}
		logger.info("message======"+message);
		mva.addObject("msg", msg+":"+code);
		//如果返回码为0，说明调用成功
		DataRow saveBankCardInfoParam = new DataRow();
		if ("0".equals(code)) {
			saveBankCardInfoParam.set("cashaccountid", cash_acct);
			saveBankCardInfoParam.set("cust_no", cust_no);
			saveBankCardInfoParam.set("transactionaccountid", "4654");
			saveBankCardInfoParam.set("branch_code", "001");
			saveBankCardInfoParam.set("cust_no", "23");
			saveBankCardInfoParam.set("bank_acct", "6226220618651656");
			saveBankCardInfoParam.set("bank_acct_name", "钟航");
			saveBankCardInfoParam.set("bank_no", "021");
			saveBankCardInfoParam.set("capital_mode", "M");
			saveBankCardInfoParam.set("main_flag", "0");
			saveBankCardInfoParam.set("card_type", "0");
			saveBankCardInfoParam.set("auth_flag", "0");
			saveBankCardInfoParam.set("state", "0");
			saveBankCardInfoParam.set("province_code", "360");
			saveBankCardInfoParam.set("city_no", "吉安");
			saveBankCardInfoParam.set("county", "吉安县");
			saveBankCardInfoParam.set("area_code", "009");
			/*saveBankCardInfoParam.set("last_date", "2016-03-21 16:54:23");
			saveBankCardInfoParam.set("fcu", "system");
			saveBankCardInfoParam.set("fcd", "2016-03-21 16:54:23");
			saveBankCardInfoParam.set("lmu", "system");
			saveBankCardInfoParam.set("lmd", "2016-03-21 16:54:23");*/
			logger.info("+++++++++++++++保存用户绑卡信息 开始++++++++++++++++++++参数为："+saveBankCardInfoParam);
			try {
				bindBankCardService.saveBankCardInfo(saveBankCardInfoParam);
			} catch (Exception e) {
				logger.info("+++++++++++++++保存用户绑卡信息 失败++++++++++++++++++++失败信息是:"+e.getMessage());
				bindBankCardService.insertLogger("加挂银行卡","保存用户绑卡信息失败",e.getMessage(),"");
				e.printStackTrace();
			}
			logger.info("+++++++++++++++保存用户绑卡信息 结束++++++++++++++++++++");
			bindBankCardService.insertLogger("加挂银行卡","保存用户绑卡信息成功","成功","");
		}
		// ==============开户 2016年3月18日18:59:24 end=====================
		return mva;

	}

	/**
	 * @方法名: doAddBankCard
	 * @描述: 加挂卡--添加银行卡
	 * @参数 @param request
	 * @参数 @param response
	 * @参数 @return
	 * @返回类型: ModelAndView
	 * @throws
	 * @时间:2016-3-25上午10:38:40
	 * @作者: zhonghang
	 *//*
	@RequestMapping(params = "method=doAddBankCard")
	public ModelAndView doAddBankCard(HttpServletRequest request,HttpServletResponse response, Model model) {
		ModelAndView mav = new ModelAndView("/bindcard/addBankCard/tradePassWordVerify_jsp");
		return mav;
	}*/

	/**
	 * @方法名: doVerifyTradePassWord
	 * @描述: 加挂卡--- 交易密码校验
	 * @参数 @param request
	 * @参数 @param response
	 * @参数 @return
	 * @返回类型: ModelAndView
	 * @throws
	 * @时间:2016-3-25上午10:35:21
	 * @作者: zhonghang
	 */
	@RequestMapping(params = "method=doVerifyTradePassWord")
	public ModelAndView doVerifyTradePassWord(HttpServletRequest request,HttpServletResponse response, Model model) {
		model.addAttribute("method", "doVerifyBankCard");
		//ModelAndView mav = new ModelAndView("/bindcard/chooseBank_jsp");
		ModelAndView mav = new ModelAndView("/bindcard/addBankCard/tradePassWordVerify_jsp");
		return mav;
	}

	

	/**
	 * @方法名: doChangeBankCard
	 * @描述: 加挂卡---选择银行卡
	 * @参数 @param request
	 * @参数 @param response
	 * @参数 @return
	 * @返回类型: ModelAndView
	 * @throws
	 * @时间:2016-3-24上午10:44:47
	 * @作者: zhonghang
	 */
	@RequestMapping(params = "method=doAddCardChooseBankCard")
	public ModelAndView doAddCardChooseBankCard(HttpServletRequest request,HttpServletResponse response,Model model) {
		model.addAttribute("method", "doVerifyBankCard");
		ModelAndView mav = new ModelAndView("/bindcard/chooseBank_jsp");
		return mav;
	}
	
	/**
	 * @方法名: doVerifyBankCard
	 * @描述: 加挂卡---银行卡校验
	 * @参数 @param request
	 * @参数 @param response
	 * @参数 @param model
	 * @参数 @return
	 * @返回类型: ModelAndView
	 * @throws
	 * @时间:2016-3-25上午11:06:10
	 * @作者: zhonghang
	 */
	@RequestMapping(params = "method=doVerifyBankCard")
	public ModelAndView doVerifyBankCard(HttpServletRequest request,HttpServletResponse response, Model model) {
		//交易密码
		String trade_psd = request.getParameter("trade_psd");
		/*******************验证交易密码 2016年3月30日10:53:31 start********************************************/
				
		/*******************验证交易密码 2016年3月30日10:53:31 end********************************************/
		ModelAndView mav = new ModelAndView("/bindcard/addBankCard/verifyBankCard_jsp");
		return mav;
	}
	
	/**
	  * @方法名: doAddCardSuccess
	  * @描述: 加挂卡--加挂卡成功
	  * @参数 @param request
	  * @参数 @param response
	  * @参数 @param model
	  * @参数 @return   
	  * @返回类型: ModelAndView 
	  * @throws
	  * @时间:2016-3-30下午1:50:11
	  * @作者: zhonghang
	 */
	@RequestMapping(params = "method=doAddCardSuccess")
	public ModelAndView doAddCardSuccess(HttpServletRequest request,HttpServletResponse response, Model model){
		
		/****************************银行卡校验 start******************************************************/
		logger.info("++++++++++++++++++++++++++start 开始调用银行卡校验接口+++++++++++++++++++++++++++++++++++");
		
		
		logger.info("++++++++++++++++++++++++++end  结束调用银行卡校验接口+++++++++++++++++++++++++++++++++++");	
		/****************************银行卡校验 end******************************************************/
		model.addAttribute("msg", "添加银行卡成功！");
		ModelAndView mav = new ModelAndView("/bindcard/addBankCard/addBankCardSuccess_jsp");
		return mav;
	}
	
	
	
	/**http://chinesethink.iteye.com/blog/1552070
	  * @方法名: sendCode
	  * @描述: 发送验证码
	  * @参数 @param request
	  * @参数 @param response
	  * @参数 @return   
	  * @返回类型: Map<String,Object> 
	  * @throws
	  * @时间:2016-3-26上午11:52:43
	  * @作者: zhonghang
	 */
	@ResponseBody
	@RequestMapping(params = "method=sendCode")
	public Map<String, Object> sendCode(HttpServletRequest request,HttpServletResponse response) {
		// 登录用户
		String login_user = request.getSession().getAttribute(WebConstants.LOGIN_USER) == null ? "" : request.getSession().getAttribute(WebConstants.LOGIN_USER).toString();
		Map<String,String> userMap =  (Map<String,String>)request.getSession().getAttribute(WebConstants.LOGIN_USER);
		Map<String, String> userInfoDataRow = null;
		/*try {
			logger.info("++++++++++++++++查询用户信息开始+++++++++++++++++信息为："+userInfoDataRow);
			userInfoDataRow = bindBankCardService.getUserInfo(login_user);
			logger.info("++++++++++++++++查询用户信息结束+++++++++++++++++信息为："+userInfoDataRow);
		} catch (Exception e) {
			logger.info("++++++++++++++++查询用户信息失败+++++++++++++++++信息为："+e.getMessage());
			e.printStackTrace();
		}
		String telphone = userInfoDataRow.get("telphone");
		*/
		//产生6位随机数
		String code = RandomUtil.randNum(6);
		//保存验证码
		request.getSession().setAttribute("@session_tel_code", code);
		logger.info("++++++++++++++++验证码短信发送开始+++++++++++++++++");
		
		String result = "";;
		try {
			result = SendPhoneMessage.sendCode("13266811623", code, "", "", "");
		} catch (Exception e) {
			logger.info("++++++++++++++++验证码短信发送失败+++++++++++++++++错误信息为："+e.getMessage());
			e.printStackTrace();
		}
		logger.info("++++++++++++++++验证码短信发送结束+++++++++++++++++信息为："+result);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if ("0".equals(result)) {
			modelMap.put("data", result);
			modelMap.put("success", "true");
		}else{
			modelMap.put("data", result);
			modelMap.put("success", "false");
		}
		return modelMap;
	}
	
	/**
	  * @方法名: checkCode
	  * @描述: 校验手机验证码
	  * @参数 @param request
	  * @参数 @param response
	  * @参数 @return   
	  * @返回类型: boolean 
	  * @throws
	  * @时间:2016-3-26上午11:54:53
	  * @作者: zhonghang
	 */
	@ResponseBody
	@RequestMapping(params = "method=checkCode")
	public ModelAndView checkCode(HttpServletRequest request,HttpServletResponse response){
		// 手机验证码
		String telcode = request.getParameter("telcode");
		//session中的验证码
		String session_tel_code = request.getSession().getAttribute("@session_tel_code") == null ? "" : request.getSession().getAttribute("@session_tel_code").toString();
		Map<String, Object> map = new HashMap<String, Object>();
		if (telcode.equals(session_tel_code)) {
			map.put("data", 0);
		}else{
			map.put("data", -1);
			map.put("data", 0);
		}
		return new ModelAndView(new MappingJacksonJsonView(),map) ;
	}
	
	/**
	 * 
	  * @方法名: doChooseNewBankCard
	  * @描述: 银行卡换卡--选择新银行卡
	  * @参数 @param request
	  * @参数 @param response
	  * @参数 @param model
	  * @参数 @return   
	  * @返回类型: ModelAndView 
	  * @throws
	  * @时间:2016-3-30下午2:03:34
	  * @作者: zhonghang
	 */
	@RequestMapping(params = "method=doChooseNewBankCard")
	public ModelAndView doChooseNewBankCard(HttpServletRequest request,HttpServletResponse response, Model model){
		
		ModelAndView mav = new ModelAndView("/bindcard/changeBankCard/chooseNewBankCard_jsp");
		return mav;
	}
	
	/**
	 * @方法名: doBankCardVerify
	 * @描述: 银行卡换卡---银行卡验证
	 * @参数 @param request
	 * @参数 @param response
	 * @参数 @return
	 * @返回类型: ModelAndView
	 * @throws
	 * @时间:2016-3-24上午11:16:20
	 * @作者: zhonghang
	 */
	@RequestMapping(params = "method=doBankCardVerify")
	public ModelAndView doBankCardVerify(HttpServletRequest request,HttpServletResponse response, Model model) {
		// 登录用户
		//String login_user = request.getSession().getAttribute(WebConstants.LOGIN_USER) == null ? "" : request.getSession().getAttribute(WebConstants.LOGIN_USER).toString();
		UserInfo userInfo = (UserInfo)request.getSession().getAttribute(WebConstants.LOGIN_USER);
		// 原卡号
		String oldBankCard = request.getParameter("oldBankCard");
		// 新卡号
		String newBankCard = request.getParameter("newBankCard");
		// 交易密码
		String trade_psd = request.getParameter("trade_psd");
		// 交易密码校验
		// 短信校验
		ModelAndView mav = new ModelAndView("/bindcard/changeBankCard/uploadData_jsp");// 跳转到资料上传页面
		return mav;
	}
	
	/**
	  * @方法名: doUplodData
	  * @描述: 银行卡换卡---资料上传
	  * @参数 @param request
	  * @参数 @param response
	  * @参数 @param model
	  * @参数 @return   
	  * @返回类型: ModelAndView 
	  * @throws
	  * @时间:2016-3-26下午1:52:32
	  * @作者: zhonghang
	 */
	@RequestMapping(params="method=doUplodData")
	public ModelAndView doUplodData(HttpServletRequest request,HttpServletResponse response, Model model){
		ModelAndView mav = new ModelAndView("/bindcard/changeBankCard/audit_jsp");
		return mav ; 
	}
	
	
	/**
	  * @方法名: updateImg
	  * @描述: 更新数据库图片地址
	  * @参数 @param request
	  * @参数 @param response
	  * @参数 @param model
	  * @参数 @return   
	  * @返回类型: ModelAndView 
	  * @throws
	  * @时间:2016-3-29下午6:57:12
	  * @作者: zhonghang
	 */
	 @RequestMapping(params="method=updateImg")
	 @ResponseBody
	public ModelAndView updateImg(HttpServletRequest request,HttpServletResponse response, Model model){
		 Map<String, String> map = new HashMap<String, String>();
		 String column = request.getParameter("column");
		 String img_path = request.getParameter("img_path");
		 return new ModelAndView(new MappingJacksonJsonView(),map) ; 
	}
	
	/**
	  * @方法名: deleteBankCard
	  * @描述: 删除银行卡
	  * @参数 @param request
	  * @参数 @param response
	  * @参数 @param model
	  * @参数 @return   
	  * @返回类型: ModelAndView 
	  * @throws
	  * @时间:2016-3-30下午4:44:01
	  * @作者: zhonghang
	 */
	 @RequestMapping(params="method=doDeleteBankCard")
	public ModelAndView doDeleteBankCard(HttpServletRequest request,HttpServletResponse response, Model model){
		 Map<String,String> map = new HashMap<String, String>();
		 map.put("data", "0");
		 /****************************删卡接口调用 start********************************************/
		 /****************************删卡接口调用 end**********************************************/
		ModelAndView mav = new ModelAndView(new MappingJacksonJsonView(),map);
		return mav;
	}
	 
	/**
	  * @方法名: queryBankCardDetail
	  * @描述: 银行卡明细查询
	  * @参数 @param request
	  * @参数 @param response
	  * @参数 @param model
	  * @参数 @return   
	  * @返回类型: ModelAndView 
	  * @throws
	  * @时间:2016-3-30下午6:53:26
	  * @作者: zhonghang
	 */
	@ResponseBody
	@RequestMapping(params="method=queryBankCardDetail")
	public  ModelAndView queryBankCardDetail(HttpServletRequest request,HttpServletResponse response, Model model){
		String trade_date = request.getParameter("trade_date");
		String trade_type = request.getParameter("trade_type");
		String trade_result = request.getParameter("trade_result");
		System.out.println("trade_date="+trade_date+",trade_type="+trade_type+",trade_result="+trade_result);
		Map<String,String> map = new HashMap<String, String>();
		map.put("data", "0");
		ModelAndView mav = new ModelAndView(new MappingJacksonJsonView(),map);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(params="method=test")
	public ModelAndView test(HttpServletRequest request,HttpServletResponse response, Model model){
		Map<String,String> map = new HashMap<String, String>();
		map.put("data", "0");
		ModelAndView mav = new ModelAndView(new MappingJacksonJsonView(),map);
		return mav;
	}


}
