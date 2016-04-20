package com.jhcz.trade.common.wtc;

import jas.dto.JasClientConnectDto;
import jas.service.WtcControllerService;
import jas.service.impl.WtcControllerServiceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jhcz.trade.common.base.DataRow;


/**
 * 调用WTC服务
 * @author aibo
 *
 */
public class CallWTC {



	/**
	 * 函数功能：执行调用命令，返回结果
	 * 
	 * @param serviceName
	 *            服务名，可能取值如下：在WTC中配置的服务，即Import中的配置
	 * 
	 * @param command
	 *            发送的命令 * @return 返回的结果值（即tuxedo返回的值）
	 * @throws Exception 
	 */
	public static DataRow execute(String serviceName,Map params)
			throws Exception {
		DataRow result = new DataRow();
		System.out.println("**************************************************");
		WtcControllerService wtcController = new  WtcControllerServiceImpl();
		JasClientConnectDto connectDto  = new JasClientConnectDto();
		connectDto.setServiceName(serviceName);
//		connectDto.setServiceName("100001");
//		connectDto.setServiceName("200001");
		wtcController.jas_cli_init(connectDto, false);
		wtcController.jas_cli_conn_srv();	
		wtcController.jas_cli_begin_write();
		
		if(params != null){
			Set<String> keySet = params.keySet();
			for(String key : keySet){
				wtcController.jas_cli_set_value(key,params.get(key));
			}
		}
		wtcController.jas_cli_set_value("g_sysid","0");
		wtcController.jas_cli_set_value("g_menuid","1");
		wtcController.jas_cli_set_value("g_funcid","200008");
		wtcController.jas_cli_set_value("g_userid","8888");
		wtcController.jas_cli_set_value("g_userpwd","1");
		wtcController.jas_cli_set_value("g_userway","0");
		wtcController.jas_cli_set_value("g_stationaddr","127.0.0.1");
		wtcController.jas_cli_set_value("g_chkuserid","8888");
		wtcController.jas_cli_set_value("g_checksno","0");
		wtcController.jas_cli_set_value("g_confirmaction","0");
		wtcController.jas_cli_set_value("g_confirmlevel","0");
		wtcController.jas_cli_set_value("g_sessionid","1000010001");
		
		
		
		/*wtcController.jas_cli_set_value("TRADE_ACCO", "2016031");
		wtcController.jas_cli_set_value("SZACCO", "0123");
		wtcController.jas_cli_set_value("CUST_TYPE", "1");*/
		
		com.alibaba.fastjson.JSONObject putJson= wtcController.getPutJson();

		System.out.println("打包的json:"+putJson);
		wtcController.jas_cli_sync_call(null);
		wtcController.jas_cli_begin_read();
		Set<String> name_set = wtcController.jas_cli_get_field_name();
		System.out.println("返回key:"+name_set);
		result = jsonToMap(wtcController.getGetJson());
		
		return result;
		
	}
	
	/**
	 * json对象转换为map对象
	 * @param json
	 * @return
	 */
	
	 private static DataRow jsonToMap(JSONObject json)  
     {  
		if(json == null){
			return null;
		}
		DataRow data = new DataRow();
        //最外层解析
        for(Object k : json.keySet()){
          Object v = json.get(k); 
          //如果内层还是数组的话，继续解析
          if(v instanceof JSONArray){
            List<DataRow> list = new ArrayList<DataRow>();
            Iterator<Object> it = ((JSONArray)v).iterator();
            while(it.hasNext()){
              Object obj = it.next();
              if(obj instanceof JSONObject){
	              JSONObject json2 = (JSONObject)obj;
	              list.add(jsonToMap(json2));
              }
            }
            data.put(k.toString(), list);
          } else {
            data.put(k.toString(), v);
            System.out.println(k.toString()+":"+v);
          }
        }
        return data;
     }  

}
