package com.yizhishang.plat.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yizhishang.base.config.Configuration;
import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.service.BaseService;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.plat.system.Application;

/**
 * 描述: 附件管理
 * 版权: Copyright (c) 2012 
 * 公司:  
 * 作者: 袁永君
 * 版本: 1.0 
 * 创建日期: Nov 9, 2013 
 * 创建时间: 9:01:22 PM
 */
@Service
public class AttachService extends BaseService
{
	        
    /**
    * 描述：添加附件
    * 作者：袁永君
    * 时间：Nov 9, 2013 9:06:11 PM
    * @param dataRow
    */
	public void addAttach(DynaModel dataRow)
	{
		if (dataRow != null)
		{
			String[] save_filename_list = StringHelper.split(dataRow.getString("save_filename"), "|");
			String[] real_filaname_list = StringHelper.split(dataRow.getString("real_filaname"), "|");
			String[] url_list = StringHelper.split(dataRow.getString("url"), "|");
			if (save_filename_list.length > 0 && save_filename_list.length == real_filaname_list.length && save_filename_list.length == url_list.length)
			{
				DynaModel tempData = null;
				for (int i = 0; i < save_filename_list.length; i++)
				{
					tempData = new DynaModel();
					String id = getSeqValue("T_ATTACH_INFO");
					tempData.set("ATTACH_NO", id);
					
					tempData.set("SOURCE", dataRow.getString("source"));
					tempData.set("SOURCE_ID", dataRow.getString("source_id"));
					tempData.set("SAVE_FILENAME", save_filename_list[i]);
					tempData.set("REAL_FILANAME", real_filaname_list[i]);
					tempData.set("URL", url_list[i]);
					tempData.set("CREATE_BY", dataRow.getString("create_by"));
					tempData.set("CREATE_DATE", dataRow.getString("create_date"));
					tempData.set("DOWN_COUNT", dataRow.getString("down_count"));
					
					getJdbcTemplateUtil().insert("T_ATTACH_INFO", tempData);
				}
			}
		}
		
	}
	
	                /**
    * 
    * 描述：删除附件
    * 作者：袁永君
    * 时间：Nov 14, 2013 1:17:24 PM
    * @param source
    * @param sourceId
    * @param save_filename
    */
	public void deleteAttach(int source, int sourceId, String save_filename)
	{
		String sql = "SELECT ATTACH_NO,URL FROM T_ATTACH_INFO WHERE SOURCE = ? AND SOURCE_ID = ? AND SAVE_FILENAME = ?";
		DynaModel attachData = getJdbcTemplateUtil().queryMap(sql, new Object[] { source, sourceId, save_filename });
		if (attachData != null)
		{
			int id = attachData.getInt("attach_no");
			String url = attachData.getString("url");
			
			File file = null;
			int length = url.indexOf("/upload/");
			if (length >= 0)
			{
				                                                                /**
                * 上传文件保存的地址，从配置文件中取，可以将附件生成在工程外
                *  add by 20131015
                */
				String fileSaveAddr = Configuration.getString("system.fileSaveAddr");
				
				if (StringHelper.isNotEmpty(fileSaveAddr))
				{
					file = new File(fileSaveAddr + url.substring(length));
				}
				else
				{
					file = new File(Application.getRootPath() + url);
				}
				
				if (file != null && file.exists())
				{
					file.delete();
				}
				getJdbcTemplateUtil().delete("T_ATTACH_INFO", "attach_no", id);
			}
		}
	}
	
	                    /**
    * 
    * 描述：
    * 作者：袁永君
    * 时间：Nov 10, 2013 10:08:40 AM
    * @param source
    * @param sourceId
    * @param dataRow
    */
	public void editAttach(int source, int sourceId, DynaModel dataRow)
	{
		if (dataRow != null)
		{
			String[] save_filename_list = StringHelper.split(dataRow.getString("save_filename"), "|");
			String[] real_filaname_list = StringHelper.split(dataRow.getString("real_filaname"), "|");
			String[] url_list = StringHelper.split(dataRow.getString("url"), "|");
			
			String save_filename = "";
			String real_filaname = "";
			String url = "";
			
            //查询已经存在的附件，存在的附件不作更新
			String sql = "SELECT SAVE_FILENAME FROM T_ATTACH_INFO WHERE SOURCE = ? AND SOURCE_ID = ? ";
			String[] existsFile = getJdbcTemplateUtil().queryStringArray(sql, new Object[] { new Integer(source), new Integer(sourceId) });
			
            List<String> addAttachList = getListDiff(save_filename_list, existsFile);//需要新增的附件
            List<String> delAttachList = getListDiff(existsFile, save_filename_list);//需要删除的附件
			
            //保存新增加的附件
			if (addAttachList != null && addAttachList.size() > 0)
			{
				for (int i = 0; save_filename_list != null && i < save_filename_list.length; i++)
				{
					if (addAttachList.contains(save_filename_list[i]))
					{
						save_filename += save_filename_list[i] + "|";
						real_filaname += real_filaname_list[i] + "|";
						url += url_list[i] + "|";
					}
				}
				
				if (StringHelper.isNotEmpty(save_filename) && StringHelper.isNotEmpty(real_filaname) && StringHelper.isNotEmpty(url))
				{
					save_filename = save_filename.substring(0, save_filename.length() - 1);
					real_filaname = real_filaname.substring(0, real_filaname.length() - 1);
					url = url.substring(0, url.length() - 1);
					
					dataRow.set("save_filename", save_filename);
					dataRow.set("real_filaname", real_filaname);
					dataRow.set("url", url);
					
					addAttach(dataRow);
				}
			}
			
            //删除附件
			if (delAttachList != null && delAttachList.size() > 0)
			{
				for (String saveFileName : delAttachList)
				{
					deleteAttach(source, sourceId, saveFileName);
				}
			}
			
		}
		
	}
	
	                /**
    * 
    * 描述：查询附件，将多个附件用|分隔
    * 作者：袁永君
    * 时间：Nov 9, 2013 10:22:30 PM
    * @param type
    * @param sourceId
    * @return
    */
	public DynaModel getAttachBySourceId(int type, int sourceId)
	{
		DynaModel resultData = null;
		String sql = "SELECT * FROM T_ATTACH_INFO WHERE SOURCE = ? AND SOURCE_ID = ? ORDER BY ATTACH_NO";
        List<DynaModel> dataList = getJdbcTemplateUtil().queryForList(sql, new Object[] { type, sourceId });
		
		String save_filename_list = "";
		String real_filaname_list = "";
		String url_list = "";
		
		for (int i = 0; i < dataList.size(); i++)
		{
			DynaModel dataRow = (DynaModel) dataList.get(i);
			String save_filename = dataRow.getString("save_filename");
			String real_filaname = dataRow.getString("real_filaname");
			String url = dataRow.getString("url");
			
			if (StringHelper.isNotEmpty(save_filename) && StringHelper.isNotEmpty(real_filaname) && StringHelper.isNotEmpty(url))
			{
				save_filename_list += save_filename + "|";
				real_filaname_list += real_filaname + "|";
				url_list += url + "|";
			}
		}
		
		if (StringHelper.isNotEmpty(save_filename_list))
		{
			save_filename_list = save_filename_list.substring(0, save_filename_list.length() - 1);
			real_filaname_list = real_filaname_list.substring(0, real_filaname_list.length() - 1);
			url_list = url_list.substring(0, url_list.length() - 1);
			resultData = new DynaModel();
			resultData.set("save_filename", save_filename_list);
			resultData.set("real_filaname", real_filaname_list);
			resultData.set("url", url_list);
		}
		
		return resultData;
	}
	
	/**
    * 
    * 描述：比较两个数据的差异，找出在数据2中不存在的数据
    * 作者：袁永君
    * 时间：Nov 14, 2013 11:05:14 AM
    * @param strArray1
    * @param strArray2
    * @return
    */
    public List<String> getListDiff(String[] strArray1, String[] strArray2)
	{
		List<String> resultList = new ArrayList<String>();
		
		List<String> arrlist1 = new ArrayList<String>();
		if (strArray1 != null)
		{
			arrlist1 = Arrays.asList(strArray1);
		}
		
		List<String> arrlist2 = new ArrayList<String>();
		if (strArray2 != null)
		{
			arrlist2 = Arrays.asList(strArray2);
		}
		for (String str : arrlist1)
		{
			if (!arrlist2.contains(str))
			{
				resultList.add(str);
			}
		}
		
		return resultList;
	}
}
