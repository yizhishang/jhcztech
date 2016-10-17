package com.jhcz.base.pojo;

import java.io.Serializable;

/**
 * 描述: Site.java
 * 版权: Copyright (c) 2016
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2016-2-29
 * 创建时间: 下午2:11:58
 */
public class Site implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	
	public static String STATE_CLOSE = "0";
    public static String STATE_OPEN = "1";
	
	private String id;
	
	private String siteno;
	
	private String name;
	
	private String state;
	
	private String is_main;
	
	private String publish_path;
	
	private String description;
	
	private String create_by;
	
	private String create_date;
	
	private String modified_by;
	
	private String modified_date;
	
	public Site()
	{
		this.id = "";
		this.siteno = "";
		this.name = "";
		this.state = "";
		this.is_main = "";
		this.publish_path = "";
		this.description = "";
		this.create_by = "";
		this.create_date = "";
		this.modified_by = "";
		this.modified_date = "";
	}
	
	/**
	 * 返回id
	 * @return
	 */
	public String getId()
	{
		return this.id;
	}
	
	/**
	 * 设置id
	 * @param id
	 */
	public void setId(String id)
	{
		this.id = id;
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
	 * 返回state
	 * @return
	 */
	public String getState()
	{
		return this.state;
	}
	
	/**
	 * 设置state
	 * @param state
	 */
	public void setState(String state)
	{
		this.state = state;
	}
	
	/**
	 * 返回is_main
	 * @return
	 */
	public String getIs_main()
	{
		return this.is_main;
	}
	
	/**
	 * 设置is_main
	 * @param is_main
	 */
	public void setIs_main(String is_main)
	{
		this.is_main = is_main;
	}
	
	/**
	 * 返回publish_path
	 * @return
	 */
	public String getPublish_path()
	{
		return this.publish_path;
	}
	
	/**
	 * 设置publish_path
	 * @param publish_path
	 */
	public void setPublish_path(String publish_path)
	{
		this.publish_path = publish_path;
	}
	
	/**
	 * 返回description
	 * @return
	 */
	public String getDescription()
	{
		return this.description;
	}
	
	/**
	 * 设置description
	 * @param description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	/**
	 * 返回create_by
	 * @return
	 */
	public String getCreate_by()
	{
		return this.create_by;
	}
	
	/**
	 * 设置create_by
	 * @param create_by
	 */
	public void setCreate_by(String create_by)
	{
		this.create_by = create_by;
	}
	
	/**
	 * 返回create_date
	 * @return
	 */
	public String getCreate_date()
	{
		return this.create_date;
	}
	
	/**
	 * 设置create_date
	 * @param create_date
	 */
	public void setCreate_date(String create_date)
	{
		this.create_date = create_date;
	}
	
	/**
	 * 返回modified_by
	 * @return
	 */
	public String getModified_by()
	{
		return this.modified_by;
	}
	
	/**
	 * 设置modified_by
	 * @param modified_by
	 */
	public void setModified_by(String modified_by)
	{
		this.modified_by = modified_by;
	}
	
	/**
	 * 返回modified_date
	 * @return
	 */
	public String getModified_date()
	{
		return this.modified_date;
	}
	
	/**
	 * 设置modified_date
	 * @param modified_date
	 */
	public void setModified_date(String modified_date)
	{
		this.modified_date = modified_date;
	}

	@Override
	public String toString()
	{
		return "Site [id=" + id + ", siteno=" + siteno + ", name=" + name + ", state=" + state + ", is_main=" + is_main + ", publish_path=" + publish_path + ", description=" + description
				+ ", create_by=" + create_by + ", create_date=" + create_date + ", modified_by=" + modified_by + ", modified_date=" + modified_date + "]";
	}
	
}
