package com.jhcz.base.jdbc.connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.mchange.v2.c3p0.DataSources;
import com.jhcz.base.config.Configuration;
import com.jhcz.base.util.FileHelper;
import com.jhcz.base.util.PropHelper;
import com.jhcz.base.util.StringHelper;
import com.jhcz.base.util.security.AES;
import com.jhcz.base.util.security.SecurityHelper;

/**
 * 描述:	 获取datasource.xml数据库配置信息
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-4
 * 创建时间: 21:33:09
 */
public final class Configure
{
    
    private static HashMap<String, Object> dataSourceMap = new HashMap<String, Object>();
    
    private static HashMap<String, Object> dbConnXmlMap = new HashMap<String, Object>();
    
    private static String _default = "";
    
    private static Logger logger = Logger.getLogger(Configure.class);
    
    private static Configure instance = new Configure();
    
    /**
    * 数据库配置文件
    */
    private static final String CONFIG_FILE_NAME = "datasource.xml";
    
    /**
    * AES加密所需data文件
    */
    private static String KEY_FILE_NAME = "database.dat";
    
    private static String ENCRYPT_KEY = Configuration.getString("encryption.key");
    
    /**
    * 是否加密关键字
    */
    private static final String IS_ENCRYPT_KEYWORD = "encrypt:";
    
    static
    {
        loadConfig();
    }
    
    /**
    * 创建C3P0数据源
    * @param propMap
    * @return
    */
    private static DataSource buildDataSource(HashMap<String, Object> propMap)
    {
        String driverName = (String) propMap.get("driver-name");
        String url = (String) propMap.get("url");
        String user = (String) propMap.get("user");
        String password = (String) propMap.get("password");
        
        propMap.remove("driver-name");
        propMap.remove("url");
        propMap.remove("user");
        propMap.remove("passowrd");
        
        try
        {
            Class.forName(driverName);
            DataSource unpooled = DataSources.unpooledDataSource(url, user, password);
            DataSource pooled = DataSources.pooledDataSource(unpooled, propMap);
            //测试数据库是否能连接上
            connectToDB(pooled);
            return pooled;
        }
        catch (Exception ex)
        {
            logger.error("", ex);
        }
        return null;
    }
    
    private static void connectToDB(DataSource dataSource)
    {
        Connection conn = null;
        try
        {
            conn = dataSource.getConnection();
        }
        catch (Exception ex)
        {
            logger.error("", ex);
        }
        finally
        {
            try
            {
                if (conn != null)
                {
                    conn.close();
                    conn = null;
                }
            }
            catch (Exception ex)
            {
                logger.error("", ex);
            }
        }
    }
    
    /**
    * 描述：销毁数据源
    */
    public static void destroyDataSource()
    {
        try
        {
            for (Iterator<String> iter = dataSourceMap.keySet().iterator(); iter.hasNext();)
            {
                String key = iter.next();
                DataSource dataSource = (DataSource) dataSourceMap.get(key);
                DataSources.destroy(dataSource);
            }
        }
        catch (Exception ex)
        {
            logger.error("销毁数据源", ex);
        }
    }
    
    /**
    *描述：
    *作者：袁永君
    *时间：2015-9-23 下午01:16:37
    * @return
    */
    public static String getAesKey() throws Exception
    {
        String key = "";
        File keyFile = PropHelper.guessPropFile(Configure.class, KEY_FILE_NAME);
        if (keyFile != null && keyFile.exists())
        {
            String fileKey = "B49A86FA425D439dB510A234A3E25A3E";
            byte[] data = org.apache.commons.io.FileUtils.readFileToByteArray(keyFile);
            key = new String(AES.decrypt(data, fileKey.getBytes()));
        }
        return key;
    }
    
    /**
    * 描述：获取实例
    * 作者：袁永君
    * @return
    */
    public static Configure getInstance()
    {
        return instance;
    }
    
    /**
    * 描述：判断datasourceid是否存在
    * 作者：袁永君
    * 时间：Dec 8, 2011 11:20:47 AM
    * @param id
    * @return
    */
    public static boolean isExistDataSource(String id)
    {
        return dataSourceMap.containsKey(id);
    }
    
    @SuppressWarnings("deprecation")
    private static void loadConfig()
    {
        File file = PropHelper.guessPropFile(Configure.class, CONFIG_FILE_NAME);
        if (file != null && file.exists() && file.isFile())
        {
            InputStream is = null;
            try
            {
                is = new FileInputStream(file);
                SAXReader reader = new SAXReader();
                
                Document document = reader.read(is);
                if (document == null)
                    return;
                
                //得到根元素
                Element rootElement = document.getRootElement();
                _default = rootElement.attributeValue("default", "");
                
                @SuppressWarnings("unchecked")
                List<Element> funcElementList = rootElement.elements("datasource");
                boolean isEncrypt = false;
                for (Iterator<Element> iter = funcElementList.iterator(); iter.hasNext();)
                {
                    Element dsElement = iter.next();
                    String id = dsElement.attributeValue("id", "");
                    if (StringHelper.isEmpty(id))
                        continue;
                    
                    HashMap<String, Object> propMap = new HashMap<String, Object>();
                    @SuppressWarnings("unchecked")
                    List<Element> propElementList = dsElement.elements("property");
                    
                    String encrypt = "";
                    Element pwdElm = null;
                    Element encryptElm = null;
                    for (Iterator<Element> iter2 = propElementList.iterator(); iter2.hasNext();)
                    {
                        Element propElement = iter2.next();
                        String name = propElement.attributeValue("name");
                        String value = propElement.getTextTrim();
                        if (name != null && name.equals("password"))
                        {
                            pwdElm = propElement;
                        }
                        else if (name != null && name.equals("encrypt"))
                        {
                            encryptElm = propElement;
                        }
                        propMap.put(name, value);
                    }
                    
                    if (encryptElm != null)
                    {
                        String pwd = pwdElm.getText();
                        //密码未加密
                        if (!pwd.startsWith(IS_ENCRYPT_KEYWORD))
                        {
                            //加密方式，AES或DES加密
                            encrypt = encryptElm.getTextTrim();
                            
                            if (StringHelper.isNotEmpty(encrypt))
                            {
                                if (encrypt.toUpperCase().equalsIgnoreCase("AES"))
                                {
                                    //AES加密
                                    String key = getAesKey();
                                    
                                    AES aes = new AES(key);
                                    pwd = IS_ENCRYPT_KEYWORD + aes.encrypt(pwd);
                                    pwdElm.setText(pwd);
                                    isEncrypt = true;
                                }
                                else if (encrypt.toUpperCase().equalsIgnoreCase("DES"))
                                {
                                    //DES加密
                                    pwd = IS_ENCRYPT_KEYWORD + SecurityHelper.encode(pwd, ENCRYPT_KEY);
                                    pwdElm.setText(pwd);
                                    isEncrypt = true;
                                }
                            }
                            
                        }
                        else
                        {
                            pwd = pwd.substring(IS_ENCRYPT_KEYWORD.length());
                            
                            //加密方式，AES或DES加密
                            encrypt = encryptElm.getTextTrim();
                            
                            if (encrypt.equalsIgnoreCase("AES"))
                            {
                                //AES加密
                                String key = getAesKey();
                                
                                AES aes = new AES(key);
                                pwd = aes.decrypt(pwd);
                                propMap.put("password", pwd);
                            }
                            else
                            {
                                //DES加密
                                pwd = SecurityHelper.decode(pwd, ENCRYPT_KEY);
                                propMap.put("password", pwd);
                            }
                        }
                    }
                    if (!dbConnXmlMap.containsKey(id))
                    {
                        HashMap<String, Object> xmlMap = new HashMap<String, Object>();
                        xmlMap.putAll(propMap);
                        dbConnXmlMap.put(id, xmlMap);
                    }
                    DataSource dataSource = buildDataSource(propMap);
                    if (dataSource != null)
                    {
                        dataSourceMap.put(id, dataSource);
                    }
                }
                //            从明文加密到密文时重写配置文件
                if (isEncrypt)
                {
                    FileHelper.WriteToXMLFile(file.getAbsolutePath(), document, "GBK");
                }
            }
            catch (Exception ex)
            {
                logger.error("", ex);
            }
            finally
            {
                if (is != null)
                {
                    try
                    {
                        is.close();
                        is = null;
                    }
                    catch (IOException ex)
                    {
                        logger.error("", ex);
                    }
                }
            }
        }
        
    }
    
    private Configure()
    {
    }
    
    /**
    * 获得缺省数据源
    *
    * @return
    */
    public DataSource getDataSource()
    {
        //若只有一个数据源，则直接返回相应的数据源
        if (dataSourceMap.size() == 1)
        {
            Object[] dataSourceArray = dataSourceMap.values().toArray();
            return (DataSource) dataSourceArray[0];
        }
        
        //若有多个数据源，则查找缺省数据源
        if (StringHelper.isEmpty(_default))
            return null;
        
        return getDataSource(_default);
    }
    
    /**
    * 根据datasource.xml文件中配置的数据源ID，得到对应的数据源对象
    * @param id
    * @return
    */
    public DataSource getDataSource(String id)
    {
        return (DataSource) dataSourceMap.get(id);
    }
    
    /**
    * 根据datasource.xml文件中配置的数据源ID，得到对应的数据源的xml配置
    * @param id
    * @return
    */
    @SuppressWarnings("unchecked")
    public HashMap<String, Object> getDbConnXmlMap(String id)
    {
        if (StringHelper.isBlank(id))
        {
            id = _default;
        }
        if (StringHelper.isBlank(id))
        {
            return null;
        }
        else
        {
            return (HashMap<String, Object>) dbConnXmlMap.get(id);
        }
    }
}
