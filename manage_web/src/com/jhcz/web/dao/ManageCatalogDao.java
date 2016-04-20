package com.jhcz.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.jhcz.base.pojo.ManageCatalog;

@Repository
public interface ManageCatalogDao
{
	/**
	 * 描述: 根据父栏目id查询子栏目
	 * 作者: 袁永君
	 * 创建日期: 2016-3-1
	 * 创建时间: 下午3:17:13
	 * @param parentId
	 * @return
	 */
	@Select("select * from t_manage_catalog where parent_id = #{parentId}")
	public List<ManageCatalog> queryFunctionCatalogByParentId(String parentId);
}
