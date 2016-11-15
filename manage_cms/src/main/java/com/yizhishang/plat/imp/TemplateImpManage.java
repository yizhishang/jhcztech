package com.yizhishang.plat.imp;

import com.yizhishang.base.config.Configuration;
import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.util.DateHelper;
import com.yizhishang.base.util.FileHelper;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.base.util.XMLHelper;
import com.yizhishang.plat.export.TemplateXmlManage;
import com.yizhishang.plat.service.TemplateService;
import com.yizhishang.plat.system.Application;
import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 描述:  模板导入
 * 版权:	 Copyright (c) 2009
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2011-3-3
 * 创建时间: 下午03:22:37
 */
public class TemplateImpManage
{

    private static final String BACKUP_PATH = "/temp/expxml/backup/template/";//模板备份地址

    private static Logger logger = LoggerFactory.getLogger(TemplateImpManage.class);

    String directory = "";

    public TemplateImpManage(String directory)
    {
        this.directory = directory;
    }

    /**
     * 把元素对象转换为Map对象
     *
     * @param element
     * @return
     */
    private DynaModel elementToMap(Element element)
    {
        DynaModel dataRow = new DynaModel();

        //处理该元素的属性
        @SuppressWarnings("unchecked") Iterator<Element> iter = element.elementIterator();
        while (iter.hasNext()) {
            Element el = iter.next();
            String name = el.getName();
            String value = el.getText();
            try {
                value = java.net.URLDecoder.decode(value, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                logger.error("", e);
            }
            dataRow.set(name, value);
        }

        return dataRow;
    }

    /**
     * @throws Exception
     * @描述：
     * @作者：袁永君
     * @时间：2011-3-3 下午05:18:39
     */
    public void impTemplate() throws Exception
    {
        try {
            //读取临时目录文件
            File file = new File(directory);
            if (file.exists()) {
                String datePath = DateHelper.formatDate(new Date(), "yyyyMMdd");
                String hourPath = DateHelper.formatDate(new Date(), "HHmmss");

                String backupPath = "";
                /**
                 * 上传文件保存的地址，从配置文件中取，可以将附件生成在工程外
                 *  add by 20131015
                 */
                String fileSaveAddr = Configuration.getString("system.fileSaveAddr");
                if (StringHelper.isNotEmpty(fileSaveAddr)) {
                    backupPath = fileSaveAddr + BACKUP_PATH + datePath + "/" + hourPath + "/";
                } else {
                    //模板备份的地址为：/admin/expxml/backup/template/ + 当前日期 +"/" + 当前时分秒
                    backupPath = Application.getRootPath() + BACKUP_PATH + datePath + "/" + hourPath + "/";
                }
                File list[] = file.listFiles();
                //读取目录下的所有文件信息
                if (list != null) {
                    int[] templateIds = new int[list.length];
                    HashMap<String, String> xmlPathMap = new HashMap<String, String>();
                    for (int i = 0; i < list.length; i++) {
                        String fileName = list[i].getName();
                        String templateId = FileHelper.getBaseName(fileName);//模板ID
                        templateIds[i] = new Integer(templateId).intValue();
                        xmlPathMap.put(templateId, directory + fileName);
                    }

                    //上传模板之前，先备份模板
                    TemplateXmlManage templateXmlManage = new TemplateXmlManage(backupPath);
                    templateXmlManage.handleXml(templateIds);

                    //将xml文件读到list对象
                    List<DynaModel> dataList = loadXmlToList(xmlPathMap);

                    //更新模板
                    TemplateService templateService = new TemplateService();
                    templateService.editTemplateByList(dataList);

                }
            }
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    /**
     * @param xmlPathMap
     * @描述：读取文件中xml的信息
     * @作者：袁永君
     * @时间：2011-3-3 下午04:08:14
     */
    private List<DynaModel> loadXmlToList(HashMap<String, String> xmlPathMap) throws Exception
    {
        ArrayList<DynaModel> dataList = new ArrayList<DynaModel>();
        for (Iterator<String> iter = xmlPathMap.keySet().iterator(); iter.hasNext(); ) {
            String id = iter.next();
            String path = xmlPathMap.get(id);

            Document document = XMLHelper.getDocument(path);
            if (document == null)
                continue;

            //得到根元素
            Element rootElement = document.getRootElement();

            Element tmpElement = rootElement.element("template");
            DynaModel dataRow = elementToMap(tmpElement);
            dataList.add(dataRow);
        }
        return dataList;
    }
}
