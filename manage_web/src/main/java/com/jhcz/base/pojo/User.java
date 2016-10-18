package com.jhcz.base.pojo;


public class User
{
	private long user_id;
	private String siteno;
	private String name;
	private String password;
	private String state;
	private String is_system;
	private long login_times;
	private String last_time;
	private String email;
	private String phone;
	private String mobile;
	private String create_by;
	private String create_date;
	private String modify_by;
	private String modify_date;
	private String uid2;
	private String branchno;
	private String sitename;
	
	public static String STATE_CLOSE = "0";
    public static String STATE_OPEN = "1";
	
	public long getUser_id()
	{
		return user_id;
	}
	
	public void setUser_id(long user_id)
	{
		this.user_id = user_id;
	}
	
	public String getSiteno()
	{
		return siteno;
	}
	
	public void setSiteno(String siteno)
	{
		this.siteno = siteno;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getState()
	{
		return state;
	}
	
	public void setState(String state)
	{
		this.state = state;
	}
	
	public String getIs_system()
	{
		return is_system;
	}
	
	public void setIs_system(String is_system)
	{
		this.is_system = is_system;
	}
	
	public long getLogin_times()
	{
		return login_times;
	}
	
	public void setLogin_times(long login_times)
	{
		this.login_times = login_times;
	}
	
	public String getLast_time()
	{
		return last_time;
	}
	
	public void setLast_time(String last_time)
	{
		this.last_time = last_time;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getPhone()
	{
		return phone;
	}
	
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	
	public String getMobile()
	{
		return mobile;
	}
	
	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}
	
	public String getCreate_by()
	{
		return create_by;
	}
	
	public void setCreate_by(String create_by)
	{
		this.create_by = create_by;
	}
	
	public String getCreate_date()
	{
		return create_date;
	}
	
	public void setCreate_date(String create_date)
	{
		this.create_date = create_date;
	}
	
	public String getModify_by()
	{
		return modify_by;
	}
	
	public void setModify_by(String modify_by)
	{
		this.modify_by = modify_by;
	}
	
	public String getModify_date()
	{
		return modify_date;
	}
	
	public void setModify_date(String modify_date)
	{
		this.modify_date = modify_date;
	}
	
	public String getUid2()
	{
		return uid2;
	}
	
	public void setUid2(String uid2)
	{
		this.uid2 = uid2;
	}
	
	public String getBranchno()
	{
		return branchno;
	}
	
	public void setBranchno(String branchno)
	{
		this.branchno = branchno;
	}
	
	public String getSitename()
	{
		return sitename;
	}
	
	public void setSitename(String sitename)
	{
		this.sitename = sitename;
	}

	@Override
	public String toString()
	{
		return "User [user_id=" + user_id + ", siteno=" + siteno + ", name=" + name + ", password=" + password + ", state=" + state + ", is_system=" + is_system + ", login_times=" + login_times
				+ ", last_time=" + last_time + ", email=" + email + ", phone=" + phone + ", mobile=" + mobile + ", create_by=" + create_by + ", create_date=" + create_date + ", modify_by="
				+ modify_by + ", modify_date=" + modify_date + ", uid2=" + uid2 + ", branchno=" + branchno + ", sitename=" + sitename + "]";
	}
	
}
