package com.jhcz.base.pojo;

/**
 * 描述: 数据库表T_SEQUENCE所对应的实体类
 * 版权: Copyright (c) 2016
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2016-2-29
 * 创建时间: 下午3:14:14
 */
public class Sequence
{
	
	private String name;
	
	private String current_value;
	
	private String step;
	
	private String roll_back;
	
	private String start_value;
	
	private String max_value;
	
	private String siteno;
	
	public Sequence()
	{
		this.name = "";
		this.current_value = "";
		this.step = "";
		this.roll_back = "";
		this.start_value = "";
		this.max_value = "";
		this.siteno = "";
	}
	
	/**
	 * 返回name
	 * @return
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * 设置name
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * 返回current_value
	 * @return
	 */
	public String getCurrent_value()
	{
		return this.current_value;
	}
	
	/**
	 * 设置current_value
	 * @param current_value
	 */
	public void setCurrent_value(String current_value)
	{
		this.current_value = current_value;
	}
	
	/**
	 * 返回step
	 * @return
	 */
	public String getStep()
	{
		return this.step;
	}
	
	/**
	 * 设置step
	 * @param step
	 */
	public void setStep(String step)
	{
		this.step = step;
	}
	
	/**
	 * 返回roll_back
	 * @return
	 */
	public String getRoll_back()
	{
		return this.roll_back;
	}
	
	/**
	 * 设置roll_back
	 * @param roll_back
	 */
	public void setRoll_back(String roll_back)
	{
		this.roll_back = roll_back;
	}
	
	/**
	 * 返回start_value
	 * @return
	 */
	public String getStart_value()
	{
		return this.start_value;
	}
	
	/**
	 * 设置start_value
	 * @param start_value
	 */
	public void setStart_value(String start_value)
	{
		this.start_value = start_value;
	}
	
	/**
	 * 返回max_value
	 * @return
	 */
	public String getMax_value()
	{
		return this.max_value;
	}
	
	/**
	 * 设置max_value
	 * @param max_value
	 */
	public void setMax_value(String max_value)
	{
		this.max_value = max_value;
	}
	
	/**
	 * 返回siteno
	 * @return
	 */
	public String getSiteno()
	{
		return this.siteno;
	}
	
	/**
	 * 设置siteno
	 * @param siteno
	 */
	public void setSiteno(String siteno)
	{
		this.siteno = siteno;
	}

	@Override
	public String toString()
	{
		return "Sequence [name=" + name + ", current_value=" + current_value + ", step=" + step + ", roll_back=" + roll_back + ", start_value=" + start_value + ", max_value=" + max_value
				+ ", siteno=" + siteno + "]";
	}
	
}
