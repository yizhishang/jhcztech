package com.jhcz.base.mybatis.service;



public interface SequenceService
{
	/**
	* 获得缺省数据源上的表的SEQUENCE
	* @param name 对应SEQUENCE的名称
	* @return
	*/
	public String getNextSequence(String name);
	
	/**
	 * 描述: createSequence
	 * 作者: 袁永君
	 * 创建日期: 2016-4-15
	 * 创建时间: 上午10:12:49
	 * @param name
	 */
	public void createSequence(String name);
	
}
