package com.jhcz.trade.common.vo;

import java.io.Serializable;
import java.sql.Date;


/***
 * 基础VO
 * @author aibo
 *
 */

public class BaseVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fcu;
	private Date fcd;
	private String lmu;
	private Date lmd;
	public String getFcu() {
		return fcu;
	}
	public void setFcu(String fcu) {
		this.fcu = fcu;
	}
	public Date getFcd() {
		return fcd;
	}
	public void setFcd(Date fcd) {
		this.fcd = fcd;
	}
	public String getLmu() {
		return lmu;
	}
	public void setLmu(String lmu) {
		this.lmu = lmu;
	}
	public Date getLmd() {
		return lmd;
	}
	public void setLmd(Date lmd) {
		this.lmd = lmd;
	}
	
	
}
