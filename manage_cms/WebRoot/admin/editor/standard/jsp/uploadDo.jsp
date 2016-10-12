<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.jhcz.base.util.ScriptHelper" %>
<%@ page import="com.jhcz.plat.system.SysLibrary" %>
<%@ page import="com.jhcz.base.util.FileHelper" %>
<%@ page import="com.jhcz.base.config.SysConfig" %>
<%@ page import="com.jhcz.base.util.StringHelper" %>
<%@ page import="java.io.File" %>
<%@ page import="com.jhcz.base.util.DateHelper" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.jhcz.base.jdbc.session.SessionFactory" %>
<%@ page import="com.jhcz.base.jdbc.session.Session" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.jhcz.base.jdbc.connection.ConnManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%

    String fileName = "";
    String fileURL = "";
    String filePath = "";

    //先判断是否已经登录
    if (!SysLibrary.isLogin(session))
    {
        ScriptHelper.alert(response, "您还没有登录，上传失败", "close");
        return;
    }

    //先判断是否超出最大上传的长度
    int maxUploadSize = SysConfig.getMaxUploadSize();
    int contentLength = request.getContentLength();
    if (contentLength > maxUploadSize)
    {
        //关闭输入流
        try
        {
            request.getInputStream().close();
        }
        catch (Exception ex)
        {
        }
        ScriptHelper.alert(response, "您上传的文件太大，上传失败", "close");
        return;
    }

    String uploadPath = SysLibrary.getUploadPath();
    uploadPath = FileHelper.normalize(uploadPath);
    if (!FileHelper.isDirectory(uploadPath))
    {
        FileHelper.createDirectory(uploadPath);
    }

    try
    {
        MultipartRequest multi = new MultipartRequest(request, uploadPath, maxUploadSize, "UTF-8");

        //得到所有的文件,在此只处理一个文件
        File file = multi.getFile("uploadfile");
        if (file == null)
        {
            ScriptHelper.alert(response, "不能得到上传的文件，上传失败", "close");
            return;
        }

        fileName = file.getName();
        String extName = FileHelper.getExtension(fileName);
        /**YQF 2008-10-21 修改上传文件扩展名限制 **/
        if("jsp|jspx".indexOf(extName.toLowerCase())!=-1)
        {
        	String tempPath = uploadPath + fileName;
            if(StringHelper.isNotEmpty(tempPath))
            {
            	FileHelper.deleteFile(tempPath);
            }
           ScriptHelper.alert(response,"对不起，不允许上传此文件","close");
           return;
        }        
        String newFileName = String.valueOf(DateHelper.formatDate(new Date(), "yyyyMMdd") + System.currentTimeMillis());
        if (!StringHelper.isEmpty(extName))
            newFileName = newFileName + "." + extName;

        String destFile = uploadPath + newFileName;
        FileHelper.renameTo(file.getPath(), destFile);


        fileURL = SysLibrary.getUploadUrlPath() + newFileName;
        //保存入数据库
        Connection conn = null;
        try
        {
            File srcFile = new File(destFile);
            FileInputStream inputStream = new FileInputStream(srcFile);
            long length = srcFile.length();

            String sql = "insert into T_ATTACHFILE(URL,CONTENT) values(?,?) ";
            conn = ConnManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, fileURL);
            pstmt.setBinaryStream(2, inputStream, (int) length);
            pstmt.execute();
            pstmt.close();
        }
        catch (Exception ex)
        {
           ex.printStackTrace();
        }
        finally
        {
            if (conn != null)
            {
                conn.close();
            }
        }

        

        String context = request.getContextPath();
        if (!StringHelper.isEmpty(context))
        {
            fileURL = context + fileURL;
        }

        filePath = destFile;


    }
    catch (Exception ex)
    {
        ScriptHelper.alert(response, "上传时发生错误，上传失败", "close");
        return;
    }

    StringBuffer buffer = new StringBuffer();
    buffer.append("<script language=\"javascript\">\n");
    buffer.append("parent.UploadSaved('" + fileURL + "');\n");
    // buffer.append("var obj=parent.dialogArguments.dialogArguments;\n");
    //buffer.append("if (!obj) obj=parent.dialogArguments;\n");
    //buffer.append("try{\n");
    //buffer.append("	obj.addUploadFile('" + fileName + "', '" + fileURL + "', '" + filePath + "');\n");
    //buffer.append("}\n");
    //buffer.append("catch(e){};\n");
    //buffer.append("history.back();\n");
    buffer.append("</script>\n");
    out.println(buffer.toString());
%>