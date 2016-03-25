package com.jhcz.cms.dao;

import org.springframework.stereotype.Repository;

import com.jhcz.cms.model.CatalogEntity;

@Repository
public interface CatalogEntityMapper
{
	
	int deleteByPrimaryKey(Long catalogId);
	
	int insert(CatalogEntity record);
	
	int insertSelective(CatalogEntity record);
	
	CatalogEntity selectByPrimaryKey(Long catalogId);
	
	int updateByPrimaryKeySelective(CatalogEntity record);
	
	int updateByPrimaryKeyWithBLOBs(CatalogEntity record);
	
	int updateByPrimaryKey(CatalogEntity record);
}