package com.jhcz.trade.framework.freemark;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.apache.commons.io.IOUtils;

import freemarker.cache.TemplateLoader;

/**
 * @类名: HtmlTemplateLoader
 * @包名 com.jhcz.trade.freemark
 * @描述: 操作html，使Freemark可以解析html模板文件
 * @版权: Copyright (c) 2016 
 * @公司:深圳市金恒创智信息技术有限公司
 * @作者 zhonghang
 * @时间 2016-4-16 下午4:08:04
 * @版本 V1.0
 */
public class HtmlTemplateLoader implements TemplateLoader{

	 private static final String HTML_ESCAPE_PREFIX= "<#escape x as x?html>";
	    private static final String HTML_ESCAPE_SUFFIX = "</#escape>";
	    
	    private final TemplateLoader delegate;
	    
	    public HtmlTemplateLoader(TemplateLoader delegate) {
	        this.delegate = delegate;
	    }
	    
	    /* (non-Javadoc)
	     * @see freemarker.cache.TemplateLoader#closeTemplateSource(java.lang.Object)
	     */
	    @Override
	    public void closeTemplateSource(Object templateSource) throws IOException {
	        delegate.closeTemplateSource(templateSource);
	    }

	    /* (non-Javadoc)
	     * @see freemarker.cache.TemplateLoader#findTemplateSource(java.lang.String)
	     */
	    @Override
	    public Object findTemplateSource(String name) throws IOException {
	        return delegate.findTemplateSource(name);
	    }

	    /* (non-Javadoc)
	     * @see freemarker.cache.TemplateLoader#getLastModified(java.lang.Object)
	     */
	    @Override
	    public long getLastModified(Object templateSource) {
	        return delegate.getLastModified(templateSource);
	    }

	    /* (non-Javadoc)
	     * @see freemarker.cache.TemplateLoader#getReader(java.lang.Object, java.lang.String)
	     * 在读取template文件之后, 在前后套上<#escape>标签
	     */
	    @Override
	    public Reader getReader(Object templateSource, String encoding) throws IOException {
	        Reader reader = delegate.getReader(templateSource, encoding);
	        String templateText = IOUtils.toString(reader);
	        return new StringReader(HTML_ESCAPE_PREFIX+templateText + HTML_ESCAPE_SUFFIX);
	    }

}
