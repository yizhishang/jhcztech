package com.yizhishang.plat.domain;

import com.yizhishang.base.domain.DynaModel;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-24
 * 创建时间: 10:40:28
 */
public class Article extends DynaModel
{

    public int getId()
    {
        return getInt("article_id");
    }

    public void setId(int id)
    {
        set("article_id", id);
    }

    public String getTitle()
    {
        return getString("title");
    }

    public void setTitle(String title)
    {
        set("title", title);
    }

    public String getShowTitle()
    {
        return getString("show_title");
    }

    public void setShowTitle(String showTitle)
    {
        set("show_title", showTitle);
    }

    public String getBrief()
    {
        return getString("brief");
    }

    public void setBrief(String brief)
    {
        set("brief", brief);
    }

    public String getUrl()
    {
        return getString("url");
    }

    public void setUrl(String url)
    {
        set("url", url);
    }

    public String getKeyword()
    {
        return getString("keyword");
    }

    public void setKeyword(String keyword)
    {
        set("keyword", keyword);
    }

    public String getAuthor()
    {
        return getString("author");
    }

    public void setAuthor(String author)
    {
        set("author", author);
    }

    public String getSource()
    {
        return getString("source");
    }

    public void setSource(String source)
    {
        set("source", source);
    }

    public int getIsPicture()
    {
        return getInt("is_picture");
    }

    public void setIsPicture(int isPicture)
    {
        set("is_picture", isPicture);
    }

    public String getPictureUrl()
    {
        return getString("picture_url");
    }

    public void setPictureUrl(String pictureUrl)
    {
        set("picture_url", pictureUrl);
    }

    public int getIsHot()
    {
        return getInt("is_hot");
    }

    public void setIsHot(int isHot)
    {
        set("is_hot", isHot);
    }

    public int getIsNew()
    {
        return getInt("is_new");
    }

    public void setIsNew(int isNew)
    {
        set("is_new", isNew);
    }

    public int getIsHead()
    {
        return getInt("is_head");
    }

    public void setIsHead(int isHead)
    {
        set("is_head", isHead);
    }

    public String getPublishBy()
    {
        return getString("publish_by");
    }

    public void setPublishBy(String publishBy)
    {
        set("publish_by", publishBy);
    }

    public String getPublishDate()
    {
        return getString("publish_date");
    }

    public void setPublishDate(String publishDate)
    {
        set("publish_date", publishDate);
    }

    public int getCatalogId()
    {
        return getInt("catalog_id");
    }

    public void setCatalogId(int catalogId)
    {
        set("catalog_id", catalogId);
    }

    public String getSiteNo()
    {
        return getString("siteno");
    }

    public void setSiteNo(String siteNo)
    {
        set("siteno", siteNo);
    }

    public int getType()
    {
        return getInt("type");
    }

    public void setType(int type)
    {
        set("type", type);
    }

    public String getLinkUrl()
    {
        return getString("link_url");
    }

    public void setLinkUrl(String linkUrl)
    {
        set("link_url", linkUrl);
    }

    public int getState()
    {
        return getInt("state");
    }

    public void setState(int state)
    {
        set("state", state);
    }

    public int getOrderline()
    {
        return getInt("orderline");
    }

    public void setOrderline(int orderline)
    {
        set("orderline", orderline);
    }

    public int getReview()
    {
        return getInt("review");
    }

    public void setReview(int review)
    {
        set("review", review);
    }

    public int getHits()
    {
        return getInt("hits");
    }

    public void setHits(int hits)
    {
        set("hits", hits);
    }

    public int getCommentTimes()
    {
        return getInt("comment_times");
    }

    public void setCommentTimes(int commentTimes)
    {
        set("comment_times", commentTimes);
    }

    public int getInFlag()
    {
        return getInt("in_flag");
    }

    public void setInFlag(int inFlag)
    {
        set("in_flag", inFlag);
    }

    public String getUserRight()
    {
        return getString("user_right");
    }

    public void setUserRight(String userRight)
    {
        set("user_right", userRight);
    }

    public String getRoleRight()
    {
        return getString("role_right");
    }

    public void setRoleRight(String roleRight)
    {
        set("role_right", roleRight);
    }

    public String getCreateBy()
    {
        return getString("create_by");
    }

    public void setCreateBy(String createBy)
    {
        set("create_by", createBy);
    }

    public String getCreateDate()
    {
        return getString("create_date");
    }

    public void setCreateDate(String createDate)
    {
        set("create_date", createDate);
    }

    public String getModifiedBy()
    {
        return getString("modified_by");
    }

    public void setModifiedBy(String modifiedBy)
    {
        set("modified_by", modifiedBy);
    }

    public String getModifiedDate()
    {
        return getString("modified_date");
    }

    public void setModifiedDate(String modifiedDate)
    {
        set("modified_date", modifiedDate);
    }

    public String getContent()
    {
        return getString("content");
    }

    public void setContent(String content)
    {
        set("content", content);
    }

    public String getRelKeyword()
    {
        return getString("rel_keyword");
    }

    public void setRelKeyword(String relKeyword)
    {
        set("rel_keyword", relKeyword);
    }

    public String getRelSecu()
    {
        return getString("rel_secu");
    }

    public void setRelSecu(String relSecu)
    {
        set("rel_secu", relSecu);
    }

    public String getBeginTime()
    {
        return getString("begin_time");
    }

    public void setBeginTime(String beginTime)
    {
        set("begin_time", beginTime);
    }

    public String getEndTime()
    {
        return getString("end_time");
    }

    public void setEndTime(String endTime)
    {
        set("end_time", endTime);
    }

    public String getNetWorkName()
    {
        return getString("network_name");
    }

    public void setNetWorkName(String netWorkName)
    {
        set("network_name", netWorkName);
    }

    public String getGpdm()
    {
        return getString("gpdm");
    }

    public void setGpdm(String gpdm)
    {
        set("gpdm", gpdm);
    }

    public String getDataType()
    {
        return getString("data_type");
    }

    public void setDataType(String dataType)
    {
        set("data_type", dataType);
    }

    public String getCatalogNo()
    {
        return getString("catalog_no");
    }

    public void setCatalogNo(String catalogNo)
    {
        set("catalog_no", catalogNo);
    }

    public String getRelSecuName()
    {
        return getString("rel_secu_name");
    }

    public void setRelSecuName(String relSecuName)
    {
        set("rel_secu_name", relSecuName);
    }

    public String getIndustryType()
    {
        return getString("industry_type");
    }

    public void setIndustryType(String industry_type)
    {
        set("industry_type", industry_type);
    }

    public String getColumnLevel()
    {
        return getString("column_level");
    }

    public void setColumnLevel(String columnLevel)
    {
        set("column_level", columnLevel);
    }

    public String getPublishTiming()
    {
        return getString("publish_timing");
    }

    public void setPublishTiming(String publish_timing)
    {
        set("publish_timing", publish_timing);
    }

    public String getLastModifyTime()
    {
        return getString("lastmodifytime");
    }

    public void setLastModifyTime(String lastmodifytime)
    {
        set("lastmodifytime", lastmodifytime);
    }
}
