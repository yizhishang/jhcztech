package com.jhcz.plat.domain;

import com.jhcz.base.domain.DynaModel;

/**
 * 评论
 * @author liuming
 *
 */
public class Review extends DynaModel
{
	public int getId()
	{
		return getInt("id");
	}
	
	public void setId(int id)
	{
		set("id",id);
	}
	
	public int getElemType()
	{
		return getInt("elem_type");
	}
	
	public void setElemType(int elem_type)
	{
		set("elem_type",elem_type);
	}
	
	public int getElemId()
	{
		return getInt("elem_id");
	}
	
	public void setElemId(int elem_id)
	{
		set("elem_id",elem_id);
	}
	
	public int getClientId()
	{
		return getInt("client_id");
	}
	
	public void setClientId(int client_id)
	{
		set("client_id",client_id);
	}
	
	public String getClientName()
	{
		return getString("client_name");
	}
	
	public void setClientName(String client_name)
	{
		set("client_name",client_name);
	}
	
	public String getQuoteId()
	{
		return getString("quote_id");
	}
	
	public void setQuoteId(String quote_id)
	{
		set("quote_id",quote_id);
	}
	
	public float getMark()
	{
		return getFloat("mark");
	}
	
	public void setMark(float mark)
	{
		set("mark",mark);
	}
	
	public String getContent()
	{
		return getString("content");
	}
	
	public void setContent(String content)
	{
		set("content",content);
	}
	
	public String getCreateTime()
	{
		return getString("create_time");
	}
	
	public void setCreateTime(String create_time)
	{
		set("create_time",create_time);
	}
	
	public int getState()
	{
		return getInt("state");
	}
	
	public void setState(int state)
	{
		set("state",state);
	}
	
	public String getSource()
	{
		return getString("source");
	}
	
	public void setSource(String source)
	{
		set("source",source);
	}
	
}
