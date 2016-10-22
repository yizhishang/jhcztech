package com.jhcz.trade.framework.plugin.dialet;

/**
 * oracle分页方言
 * @author Administrator
 *
 */
public class OracleDialect extends Dialect{

	public boolean supportsLimitOffset(){
		return true;
	}
	
    public boolean supportsLimit() {   
        return true;   
    }  
    
	/** 
	 * 查询数量
	 */
	public String getLimitString(String sql, int start, int end) {
         return "SELECT * FROM (SELECT A.*, ROWNUM RN FROM ("+sql+") A WHERE ROWNUM <= "+end+") WHERE RN > "+start;
	}   
  
}
