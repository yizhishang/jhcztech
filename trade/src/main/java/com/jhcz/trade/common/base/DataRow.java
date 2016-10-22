package com.jhcz.trade.common.base;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @类名: DataRow
 * @包名 com.jhcz.trade.common
 * @描述: TODO
 * @版权: Copyright (c) 2016 
 * @公司:深圳市金恒创智信息技术有限公司
 * @作者 zhonghang
 * @时间 2016-3-16 下午5:46:21
 * @版本 V1.0
 */
public class DataRow extends HashMap<Object, Object>{
		
	/**
	* <p>DataRow：HashMap的扩展与封装 </p> 
	* <p>构造方法: </p>
	*/
	public DataRow(){
			
	}
	
	public void set(String key, DataRow value){
		if (key == null || "".equals(key)) {
			return;
		}else{
			if (value == null )  {
				put(key,"");
			}else{
				put(key,value);
			}
		}
	}
	
	public void set(DataRow data){
		int numKeysToBeAdded = data.size();
		if(numKeysToBeAdded == 0){
			return ;
		}
		
		Set<Object> keySet = data.keySet();
		for (Object key : keySet) {
			if(key instanceof String){
				set((String)key,data.get(key).toString());
			}
		}
		
	}
	
	/**
	  * @方法名: set
	  * @描述: DataRow以键值对的方式存String值 
	  * @参数 @param key
	  * @参数 @param value   
	  * @返回类型: void 
	  * @throws
	  * @作者: zhonghang
	 */
	public void set(String key,String value){
		if (key == null || "".equals(key)) {
			return;
		}else{
			if (value == null )  {
				put(key,"");
			}else{
				put(key,value);
			}
		}
	}
	
    /**
      * @方法名: set
      * @描述: DataRow以键值对的方式存int值 
      * @参数 @param key
      * @参数 @param value   
      * @返回类型: void 
      * @throws
      * @作者: zhonghang
     */
	public void set(String key,int value){
		put(key,new Integer(value));
	}
	
	/**
	  * @方法名: set
	  * @描述: DataRow以键值对的方式存long值 
	  * @参数 @param key
	  * @参数 @param value   
	  * @返回类型: void 
	  * @throws
	  * @作者: zhonghang
	 */
	public void set(String key,long value){
		put(key,new Long(value));
	}
	
	/**
	  * @方法名: set
	  * @描述: DataRow以键值对的方式存double值
	  * @参数 @param key
	  * @参数 @param value   
	  * @返回类型: void 
	  * @throws
	  * @作者: zhonghang
	 */
	public void set(String key,double value){
		put(key,new Double(value));
	}
	
	/**
	  * @方法名: set
	  * @描述: DataRow以键值对的方式存float值
	  * @参数 @param key
	  * @参数 @param value   
	  * @返回类型: void 
	  * @throws
	  * @作者: zhonghang
	 */
	public void set(String key,float value){
		put(key,new Float(value));
	}
	
	/**
	  * @方法名: set
	  * @描述: DataRow以键值对的方式存float值
	  * @参数 @param key
	  * @参数 @param decmal   
	  * @返回类型: void 
	  * @throws
	  * @作者: zhonghang
	 */
	public void set(String key,BigDecimal decmal){
		put(key,decmal);
	}
	
	/**
	  * @方法名: set
	  * @描述: DataRow以键值对的方式存boolean值 
	  * @参数 @param key
	  * @参数 @param value   
	  * @返回类型: void 
	  * @throws
	  * @作者: zhonghang
	 */
	public void set(String key,boolean value){
		put(key,value);
	}
	
	
	public List getList(String key){
		Object value = get(key);
		if (value == null) {
			return null;
		}else{
			return (List)value;
		}
	}
	
	public DataRow getDataRow(String key){
		Object value = get(key);
		if (value == null) {
			return null;
		}else{
			return (DataRow)value;
		}
	}
	
	
	/**
	  * @方法名: getString
	  * @描述: 取字符窜值
	  * @参数 @param key
	  * @参数 @return   
	  * @返回类型: String 
	  * @throws
	  * @作者: zhonghang
	 */
	public String getString(String key){
		Object value = get(key);
		if (value == null) {
			return "";
		}else{
			return value.toString();
		}
	}
	
	/**
	  * @方法名: getInt
	  * @描述:取整型值
	  * @参数 @param key
	  * @参数 @return   
	  * @返回类型: int 
	  * @throws
	  * @作者: zhonghang
	 */
	public int getInt(String key){
		Object value = get(key);
		if (value == null ) {
			return 0;
		}else{
			return Integer.parseInt(value.toString());
		}
	}
	
	/**
	  * @方法名: getDouble
	  * @描述: 取double型值
	  * @参数 @param key
	  * @参数 @return   
	  * @返回类型: double 
	  * @throws
	  * @作者: zhonghang
	 */
	public double getDouble(String key){
		Object value = get(key);
		if (value == null) {
			return 0.00;
		}else{
			return Double.parseDouble(value.toString());
		}
	} 
	
	/**
	  * @方法名: getFloat
	  * @描述: 取浮点类型的值
	  * @参数 @param key
	  * @参数 @return   
	  * @返回类型: float 
	  * @throws
	  * @作者: zhonghang
	 */
	public float getFloat(String key){
		Object value = get(key);
		if (value == null) {
			return 0f;
		}else{
			return Float.parseFloat(value.toString());
		}
	}
	
	/**
	  * @方法名: getDecimal
	  * @描述: 取浮点类型的值
	  * @参数 @param key
	  * @参数 @return   
	  * @返回类型: BigDecimal 
	  * @throws
	  * @作者: zhonghang
	 */
	public BigDecimal getDecimal(String key){
		Object value = get(key);
		if (value == null) {
			return new BigDecimal(0);
		}else{
			return new BigDecimal(value.toString());
		}
	}
	
}
