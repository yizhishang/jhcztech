package com.yizhishang.plat.domain;

import com.yizhishang.base.domain.DynaModel;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 江伦
 * 版本:	 1.0
 * 创建日期: 2007-5-11
 * 创建时间: 11:38:34
 */
public class ExpertOnline extends DynaModel
{

    public String getT_question_id()
    {
        return getString("t_question_id");
    }

    public void setT_question_id(String t_question_id)
    {
        set("t_question_id", t_question_id);
    }

    public String getQuestion_title()
    {
        return getString("question_title");
    }

    public void setQuestion_title(String question_title)
    {
        set("question_title", question_title);
    }

    public String getQuestion_content()
    {
        return getString("question_content");
    }

    public void setQuestion_content(String question_content)
    {
        set("question_content", question_content);
    }

    public String getQuestion_author()
    {
        return getString("question_author");
    }

    public void setQuestion_author(String question_author)
    {
        set("question_author", question_author);
    }

    public String getQuestion_datetime()
    {
        return getString("question_datetime");
    }

    public void setQuestion_datetime(String question_datetime)
    {
        set("question_datetime", question_datetime);
    }

    public String getAnswer_expert_id()
    {
        return getString("answer_expert_id");
    }

    public void setAnswer_expert_id(String answer_expert_id)
    {
        set("answer_expert_id", answer_expert_id);
    }

    public String getAnswer_datetime()
    {
        return getString("answer_datetime");
    }

    public void setAnswer_datetime(String answer_datetime)
    {
        set("answer_datetime", answer_datetime);
    }

    public String getAnswer_content()
    {
        return getString("answer_content");
    }

    public void setAnswer_content(String answer_content)
    {
        set("answer_content", answer_content);
    }

    public String getAnswer_state()
    {
        return getString("answer_state");
    }

    public void setAnswer_state(String answer_state)
    {
        set("answer_state", answer_state);
    }

}
