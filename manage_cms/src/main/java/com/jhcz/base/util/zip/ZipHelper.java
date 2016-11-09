package com.jhcz.base.util.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.jhcz.base.util.DateHelper;
import com.jhcz.base.util.StringHelper;

/**
 * 描述:
 * 版权:   Copyright (c) 2009
 * 公司:   
 * 作者:   袁永君
 * 版本:   1.0
 * 创建日期: 2015-11-22
 * 创建时间: 10:10:19
 */
public class ZipHelper
{
    
    private static Logger logger = LoggerFactory.getLogger(ZipHelper.class);
    
    /**
     * 递归压缩目录和文件
     * @param source  源路径,可以是文件,也可以目录
     * @param target  目标路径,压缩文件名
     */
    public static void compress(String source, String target)
    {
        try
        {
            File srcFile = new File(source);
            if (srcFile.isFile() || srcFile.isDirectory()) //若是文件和目录则处理
            {
                ZipOutputStream zipOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)));
                String rootPath = (srcFile.isFile()) ? srcFile.getParent() : srcFile.getPath();
                rootPath = (rootPath == null) ? "" : rootPath;
                compressFile(rootPath, srcFile, zipOut);
                zipOut.close();
            }
        }
        catch (IOException ex)
        {
            logger.error("", ex);
        }
    }
    
    private static void compressFile(String rootPath, File srcFile, ZipOutputStream zipOut) throws IOException
    {
        String tempPath = rootPath.replace("\\", "/");
        ;
        String storePath = srcFile.toString().replace("\\", "/");
        if (storePath.startsWith(tempPath))
        {
            storePath = storePath.substring(tempPath.length());
            if (storePath.startsWith("/")) //去掉最前面的目录符号
            {
                storePath = storePath.substring(1);
            }
        }
        
        if (srcFile.isFile()) //若是文件
        {
            int readedBytes = 0;
            byte[] buffer = new byte[4096];
            
            FileInputStream inStream = new FileInputStream(srcFile);
            zipOut.putNextEntry(new ZipEntry(storePath));
            while ((readedBytes = inStream.read(buffer)) > 0)
            {
                zipOut.write(buffer, 0, readedBytes);
            }
            inStream.close();
            zipOut.closeEntry();
        }
        else if (srcFile.isDirectory()) //若是目录
        {
            File[] files = srcFile.listFiles();
            
            if (files.length == 0) //若目录中没有文件
            {
                zipOut.putNextEntry(new ZipEntry(storePath + "/"));
                zipOut.closeEntry();
            }
            else
            //若目录中有文件
            {
                for (int i = 0; i < files.length; i++)
                {
                    File file = files[i];
                    compressFile(rootPath, file, zipOut);
                }
            }
        }
    }
    
    /**
     * 解压特定的文件
     * 
     * @param source
     * @param target
     */
    public static void decompress(String source, String target)
    {
        int readedBytes = 0;
        byte[] buffer = new byte[4096];
        
        try
        {
            File srcFile = new File(source);
            if (srcFile.isFile())
            {
                ZipFile zipFile = new ZipFile(source);
                
                for (@SuppressWarnings("rawtypes")
                Enumeration entries = zipFile.entries(); entries.hasMoreElements();)
                {
                    ZipEntry entry = (ZipEntry) entries.nextElement();
                    File file = new File(target + "/" + entry.getName());
                    
                    if (entry.isDirectory())
                    {
                        file.mkdirs();
                    }
                    else
                    {
                        //如果指定文件的目录不存在,则创建之.
                        File parent = file.getParentFile();
                        if (!parent.exists())
                        {
                            parent.mkdirs();
                        }
                        
                        InputStream inputStream = zipFile.getInputStream(entry);
                        FileOutputStream outStream = new FileOutputStream(file);
                        while ((readedBytes = inputStream.read(buffer)) > 0)
                        {
                            outStream.write(buffer, 0, readedBytes);
                        }
                        outStream.close();
                        inputStream.close();
                    }
                }
                zipFile.close();
            }
        }
        catch (IOException ex)
        {
            logger.error("", ex);
        }
    }
    
    public static void zip(String source, String dest) throws IOException
    {
        OutputStream os = new FileOutputStream(dest);
        BufferedOutputStream bos = new BufferedOutputStream(os);
        ZipOutputStream zos = new ZipOutputStream(bos);
        
        //支持中文，但有缺陷！这是硬编码！
        //        zos.setEncoding("GBK");
        
        File file = new File(source);
        
        String basePath = null;
        if (file.isDirectory())
        {
            basePath = file.getPath();
        }
        else
        {
            basePath = file.getParent();
        }
        
        zipFile(file, basePath, zos);
        
        zos.closeEntry();
        zos.close();
    }
    
    @SuppressWarnings("rawtypes")
    public static StringBuffer unzip(String zipFile, String dest, String extPlace) throws IOException
    {
        StringBuffer stringBuf = new StringBuffer();
        ZipFile zip = new ZipFile(zipFile);
        Enumeration en = zip.entries();
        ZipEntry entry = null;
        byte[] buffer = new byte[1024];
        int length = -1;
        InputStream input = null;
        BufferedOutputStream bos = null;
        File file = null;
        
        while (en.hasMoreElements())
        {
            entry = (ZipEntry) en.nextElement();
            if (entry.isDirectory())
            {
                file = new File(dest, entry.getName());
                if (!file.exists())
                {
                    file.mkdir();
                }
                continue;
            }
            
            input = zip.getInputStream(entry);
            file = new File(dest, entry.getName());
            stringBuf.append(extPlace + "/" + entry.getName() + "<br>");
            if (!file.getParentFile().exists())
            {
                file.getParentFile().mkdirs();
            }
            bos = new BufferedOutputStream(new FileOutputStream(file));
            
            while (true)
            {
                length = input.read(buffer);
                if (length == -1)
                    break;
                bos.write(buffer, 0, length);
            }
            bos.close();
            input.close();
        }
        zip.close();
        return stringBuf;
    }
    
    private static void zipFile(File source, String basePath, ZipOutputStream zos) throws IOException
    {
        File[] files = new File[0];
        
        if (source.isDirectory())
        {
            files = source.listFiles();
        }
        else
        {
            files = new File[1];
            files[0] = source;
        }
        
        String pathName;
        byte[] buf = new byte[1024];
        int length = 0;
        for (int i = 0; i < files.length; i++)
        {
            File file = files[i];
            if (file.isDirectory())
            {
                pathName = file.getPath().substring(basePath.length() + 1) + "/";
                zos.putNextEntry(new ZipEntry(pathName));
                zipFile(file, basePath, zos);
            }
            else
            {
                pathName = file.getPath().substring(basePath.length() + 1);
                InputStream is = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(is);
                zos.putNextEntry(new ZipEntry(pathName));
                while ((length = bis.read(buf)) > 0)
                {
                    zos.write(buf, 0, length);
                }
                is.close();
            }
        }
    }
    
    /**
     * 描述：查看自定义上传中的 上传zip包中是否包含 *.jsp *.sh
     * 作者：李丽
     * 时间：2012-1-4 下午06:51:55
     * @param zipFile
     * @return
     * @throws IOException
     */
    @SuppressWarnings("rawtypes")
    public static boolean unzipLb(String zipFile) throws IOException
    {
        ZipFile zip = new ZipFile(zipFile);
        Enumeration en = zip.entries();
        ZipEntry entry = null;
        //      byte[] buffer = new byte[1024];
        //      int length = -1;
        //      InputStream input = null;
        //      BufferedOutputStream bos = null;
        //      File file = null;
        
        boolean bool = true;
        while (en.hasMoreElements())
        {
            entry = (ZipEntry) en.nextElement();
            //          if (entry.isDirectory())
            //          {
            //              file = new File(dest, entry.getName());
            //              if (!file.exists())
            //              {
            //                  file.mkdir();
            //              }
            //              continue;
            //          }
            
            if (!entry.isDirectory())
            {
                String entrynamelower = entry.getName().toLowerCase();
                if (entrynamelower.indexOf(".jsp") != -1 || entrynamelower.indexOf(".sh") != -1)
                {
                    bool = false;
                    zip.close();
                    return bool;
                }
            }
            //          input = zip.getInputStream(entry);
            //          file = new File(dest, entry.getName());
            //          if (!file.getParentFile().exists())
            //          {
            //              file.getParentFile().mkdirs();
            //          }
            //          bos = new BufferedOutputStream(new FileOutputStream(file));
            //          
            //          while (true)
            //          {
            //              length = input.read(buffer);
            //              if (length == -1)
            //                  break;
            //              bos.write(buffer, 0, length);
            //          }
            //          bos.close();
            //          input.close();
        }
        zip.close();
        return bool;
    }
    
    /**
     * 描述：查看资管上传中 上传zip包中是否只包含 *.txt
     * 作者：李丽
     * 时间：2012-3-30 下午05:04:03
     * @param zipFile
     * @return
     * @throws IOException
     */
    @SuppressWarnings("rawtypes")
    public static boolean unAssetLb(String zipFile) throws IOException
    {
        ZipFile zip = new ZipFile(zipFile);
        Enumeration en = zip.entries();
        ZipEntry entry = null;
        
        boolean bool = true;
        while (en.hasMoreElements())
        {
            entry = (ZipEntry) en.nextElement();
            if (!entry.isDirectory())
            {
                String entrynamelower = entry.getName().toLowerCase();
                if (entrynamelower.indexOf(".txt") == -1)
                {
                    bool = false;
                    zip.close();
                    return bool;
                }
            }
        }
        zip.close();
        return bool;
    }
    
    /**
     * 描述：资管上传  不会录入详细(2012-5-4 改为需要录入详情)
     * 作者：李丽
     * 时间：2012-3-30 下午05:07:58
     * @param zipFile
     * @param dest
     * @param extPlace
     * @return
     * @throws IOException
     */
    @SuppressWarnings("rawtypes")
    public static StringBuffer unAssetZip(String zipFile, String dest, String extPlace) throws IOException
    {
        StringBuffer stringBuf = new StringBuffer();
        ZipFile zip = new ZipFile(zipFile);
        Enumeration en = zip.entries();
        ZipEntry entry = null;
        byte[] buffer = new byte[1024];
        int length = -1;
        InputStream input = null;
        BufferedOutputStream bos = null;
        File file = null;
        
        while (en.hasMoreElements())
        {
            entry = (ZipEntry) en.nextElement();
            if (entry.isDirectory())
            {
                file = new File(dest, entry.getName());
                if (!file.exists())
                {
                    file.mkdir();
                }
                continue;
            }
            input = zip.getInputStream(entry);
            file = new File(dest, entry.getName());
            stringBuf.append(extPlace + "/" + entry.getName() + "<br>");
            if (!file.getParentFile().exists())
            {
                file.getParentFile().mkdirs();
            }
            bos = new BufferedOutputStream(new FileOutputStream(file));
            
            while (true)
            {
                length = input.read(buffer);
                if (length == -1)
                    break;
                bos.write(buffer, 0, length);
            }
            bos.close();
            input.close();
        }
        zip.close();
        return stringBuf;
    }
    
    /**
     * 描述：查看上传的ta文件 是否按照正确的上传格式上传
     * 作者：李丽
     * 时间：2012-5-14 下午03:19:17
     * @param zipFile
     * @return
     * @throws IOException
     */
    @SuppressWarnings("rawtypes")
    public static boolean unAssetDirectory(String zipFile) throws IOException
    {
        ZipFile zip = new ZipFile(zipFile);
        Enumeration en = zip.entries();
        ZipEntry entry = null;
        
        boolean bool = true;
        while (en.hasMoreElements())
        {
            entry = (ZipEntry) en.nextElement();
            if (entry.isDirectory())
            {
                String entrynamelower = entry.getName().toLowerCase();
                String[] directoyrAry = entrynamelower.split("/");
                if (directoyrAry.length == 1)
                {
                    if (directoyrAry[0].length() != 8)
                    {
                        bool = false;
                        zip.close();
                        return bool;
                    }
                    else if (!isValidDate(directoyrAry[0]))
                    {
                        bool = false;
                        zip.close();
                        return bool;
                    }
                }
                else if (directoyrAry.length == 2)
                {
                    if (!"kf".equals(directoyrAry[1]))
                    {
                        bool = false;
                        zip.close();
                        return bool;
                    }
                }
            }
        }
        zip.close();
        return bool;
    }
    
    /**
     * 描述：判断穿如的字符串是否为日期 true 为日期 false 不是日期
     * 作者：李丽
     * 时间：2012-5-14 下午03:16:39
     * @param s
     * @return
     */
    public static boolean isValidDate(String s)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        dateFormat.setLenient(false);
        try
        {
            dateFormat.parse(s);
            return true;
        }
        catch (Exception e)
        {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return false;
        }
    }
    
    /**
     * 描述：查看第一层文件的名称 只使用于资管上传
     * 作者：李丽
     * 时间：2012-5-17 上午10:52:09
     * @param zipFile
     * @return
     * @throws IOException
     */
    @SuppressWarnings("rawtypes")
    public static String unFirstDirectory(String zipFile) throws IOException
    {
        ZipFile zip = new ZipFile(zipFile);
        Enumeration en = zip.entries();
        ZipEntry entry = null;
        
        String firstdirest = "";
        while (en.hasMoreElements())
        {
            entry = (ZipEntry) en.nextElement();
            if (entry.isDirectory())
            {
                String entrynamelower = entry.getName().toLowerCase();
                String[] directoyrAry = entrynamelower.split("/");
                if (directoyrAry.length == 1)
                {
                    if (directoyrAry[0].length() != 8)
                    {
                        zip.close();
                        return firstdirest;
                    }
                    else if (!isValidDate(directoyrAry[0]))
                    {
                        zip.close();
                        return firstdirest;
                    }
                    else
                    {
                        firstdirest = directoyrAry[0];
                        zip.close();
                        return firstdirest;
                    }
                }
            }
        }
        zip.close();
        return firstdirest;
    }
    
    /**
     * 描述：7.    导入TA数据，如已经导入20120608的数据，则系统应不允许再导入20120607及之前的数据；
     * 导入TA数据（包括净值数据），当天可重复导入，但需要系统提示如“20120608数据已存在，是否重新导入”，
     * 由操作员确定后方能再次导入。
     * 作者：李丽
     * 时间：2012-5-14 下午03:16:39
     * @param s
     * @return
     */
    public static String isBackDate(String s)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        //AssetUploadService service=new AssetUploadService();
        dateFormat.setLenient(false);
        try
        {
            Date upDate = dateFormat.parse(s);
            String maxDateStr = ""; //service.queryMaxDate();
            //为空的，则随意添加
            if (StringHelper.isNotBlank(maxDateStr))
            {
                Date maxDate = dateFormat.parse(maxDateStr);
                
                String maxDateStr2 = DateHelper.formatDate(maxDate, "yyyy-MM-dd") + " 00:00:00";
                String upDateStr2 = DateHelper.formatDate(upDate, "yyyy-MM-dd") + " 00:00:00";
                
                int a = DateHelper.getDateDiff(DateHelper.parseString(maxDateStr2), DateHelper.parseString(upDateStr2));
                
                if (a > 0)
                { //最大的日期 大于上传日期
                    return "big";
                }
                else if (a == 0)
                { //最大的日期 = 上传日期
                    return "equal";
                }
                else
                {
                    return "goahead";
                }
            }
            else
            {
                return "goahead";
            }
        }
        catch (Exception e)
        {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return "error";
        }
    }
    
}
