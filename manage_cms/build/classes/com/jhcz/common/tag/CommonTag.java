package com.jhcz.common.tag;

import javax.servlet.jsp.tagext.TagSupport;

public class CommonTag extends TagSupport
{
    
    private static final long serialVersionUID = 1241927666350106L;
    
    protected String id;
    
    protected String name;
    
    protected String value;
    
    protected String optionName;
    
    protected String optionValue;
    
    protected String css;
    
    protected String style;
    
    protected boolean disabled;
    
    protected boolean readonly;
    
    protected String onClick;
    
    protected String onKeyUp;
    
    protected String onChange;
    
    public String getCss()
    {
        return css;
    }
    
    public void setCss(String css)
    {
        this.css = css;
    }
    
    public boolean isDisabled()
    {
        return disabled;
    }
    
    public void setDisabled(boolean disabled)
    {
        this.disabled = disabled;
    }
    
    @Override
    public String getId()
    {
        return id;
    }
    
    @Override
    public void setId(String id)
    {
        this.id = id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getOnChange()
    {
        return onChange;
    }
    
    public void setOnChange(String onChange)
    {
        this.onChange = onChange;
    }
    
    public String getOnClick()
    {
        return onClick;
    }
    
    public void setOnClick(String onClick)
    {
        this.onClick = onClick;
    }
    
    public String getOnKeyUp()
    {
        return onKeyUp;
    }
    
    public void setOnKeyUp(String onKeyUp)
    {
        this.onKeyUp = onKeyUp;
    }
    
    public boolean isReadonly()
    {
        return readonly;
    }
    
    public void setReadonly(boolean readonly)
    {
        this.readonly = readonly;
    }
    
    public String getStyle()
    {
        return style;
    }
    
    public void setStyle(String style)
    {
        this.style = style;
    }
    
    public String getValue()
    {
        return value;
    }
    
    public void setValue(String value)
    {
        this.value = value;
    }
    
    public String getOptionName()
    {
        return optionName;
    }
    
    public void setOptionName(String optionName)
    {
        this.optionName = optionName;
    }
    
    public String getOptionValue()
    {
        return optionValue;
    }
    
    public void setOptionValue(String optionValue)
    {
        this.optionValue = optionValue;
    }
}
