package com.jhcz.cms.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jhcz.cms.model.Site;


@Repository
public interface SiteDao
{
	public List<Site> getAllSite();
}
