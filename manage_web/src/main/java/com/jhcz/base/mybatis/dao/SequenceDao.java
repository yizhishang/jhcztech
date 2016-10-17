package com.jhcz.base.mybatis.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.jhcz.base.pojo.Sequence;

/**
 * 描述: 序列管理
 * 版权: Copyright (c) 2016
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2016-2-29
 * 创建时间: 下午2:47:07
 */
@Repository
public interface SequenceDao
{
	/**
	 * 描述: 创建序列
	 * 作者: 袁永君
	 * 创建日期: 2016-2-29
	 * 创建时间: 下午3:19:40
	 * @param name
	 */
	@Insert("insert into T_SEQUENCE (name, current_value, step, roll_back, start_value, max_value) values(#{name},'2','1','0','1','999999999999')")
	public void createSequence(String name);
	
	/**
	 * 描述: 得到序列
	 * 作者: 袁永君
	 * 创建日期: 2016-2-29
	 * 创建时间: 下午3:19:48
	 * @param name
	 * @return
	 */
	@Select("select * from t_sequence where name = #{name}")
	public Sequence getNextSequence(String name);
	
	/**
	 * 描述: 更改序列
	 * 作者: 袁永君
	 * 创建日期: 2016-2-29
	 * 创建时间: 下午3:19:59
	 * @param name
	 * @param current_value
	 */
	@Update("UPDATE T_SEQUENCE SET current_value=#{current_value} WHERE name = #{name}")
	public void updateSequence(Sequence sequence);
}
