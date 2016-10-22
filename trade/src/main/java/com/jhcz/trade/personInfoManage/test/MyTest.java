package com.jhcz.trade.personInfoManage.test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jhcz.trade.common.base.DataRow;
import com.jhcz.trade.personInfoManage.service.imp.BillSendWayServiceImpl;

public class MyTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
		BillSendWayServiceImpl billSendWaySerivce = new BillSendWayServiceImpl();
		DataRow params = new DataRow();
		params.set("CUST_NO", "");
		params.set("ADDRESS", "钟航测试");
		try {
			billSendWaySerivce.modifyCustInfo(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
