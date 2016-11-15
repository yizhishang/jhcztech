package com.yizhishang.plat.template;

import java.util.*;

/**
 * 描述:
 * 版权:	 Copyright (c) 2009
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2009-12-28
 * 创建时间: 16:15:55
 */
public class Context
{

    /**
     * 属性数据，可以附加
     */
    private final Map<String, Object> attributes = new HashMap<String, Object>();

    /**
     * 接收错误信息
     */
    private final List<Object> errList = new ArrayList<Object>();

    /**
     * 变量数据
     */
    private final Map<String, Object> variables = new HashMap<String, Object>();

    /**
     * 当前目录的ID
     */
    private int catalogId = 0;

    /**
     * 当前发布的文章ID，只有在发布文章时才会传入此参数
     */
    private int articleId = 0;

    /**
     * 添加错误码消息
     *
     * @param msg
     */
    public void addErrMsg(String msg)
    {
        errList.add(msg);
    }

    /**
     * 添加一个变量
     *
     * @param name
     * @param value
     */
    public void addVariable(String name, String value)
    {
        variables.put(name, value);
    }

    /**
     * 获取所有变量
     *
     * @return
     */
    public Map<String, Object> getAllVariable()
    {
        return variables;
    }

    /**
     * 返回当前文章ID
     *
     * @return
     */
    public int getArticleId()
    {
        return articleId;
    }

    /**
     * 设置当前文章ID
     *
     * @param articleId
     */
    public void setArticleId(int articleId)
    {
        this.articleId = articleId;
    }

    /**
     * 获得属性对象
     *
     * @param name
     * @return
     */
    public Object getAttribute(String name)
    {
        return attributes.get(name);
    }

    /**
     * 返回当前栏目ID
     *
     * @return
     */
    public int getCatalogId()
    {
        return catalogId;
    }

    /**
     * 设置当前栏目ID
     *
     * @param catalogId
     */
    public void setCatalogId(int catalogId)
    {
        this.catalogId = catalogId;
    }

    /**
     * 获得错误消息
     *
     * @return
     */
    public String getErrMsg()
    {
        StringBuffer buffer = new StringBuffer();
        for (Iterator<Object> iter = errList.iterator(); iter.hasNext(); ) {
            String msg = (String) iter.next();
            buffer.append(msg + "\r\n");
        }
        return buffer.toString();
    }

    /**
     * 获得一个变量
     *
     * @param name
     * @return
     */
    public String getVariable(String name)
    {
        String value = (String) variables.get(name);
        return (value == null) ? "" : value;
    }

    /**
     * 设置属性对象
     *
     * @param name
     * @param object
     */
    public void setAttribute(String name, Object object)
    {
        attributes.put(name, object);
    }
}
