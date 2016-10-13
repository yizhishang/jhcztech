<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ page import="com.jhcz.plat.system.*" %>
<%@ page import="com.jhcz.plat.system.SysLibrary" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.jhcz.base.util.*" %>
<%@ page import="com.jhcz.base.jdbc.connection.ConnManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="com.jhcz.base.config.*" %>
<%
	System.out.println("8888888888");
    if (RequestHelper.isPostBack(request))
    {
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
        String fileUrlPath = "";
        String fileName="";
        String extName="";
        try
        {
            MultipartRequest multi = new MultipartRequest(request, uploadPath, maxUploadSize, "UTF-8");
            //	System.out.println("##########3344request"+request+"uploadPath"+uploadPath+"maxUploadSize"+maxUploadSize);
            //得到所有的文件,在此只处理一个文件
            File file = multi.getFile("uploadfile");
            //  System.out.println("##########!"+file);
            if (file == null)
            {
                
                ScriptHelper.alert(response, "不能得到上传的文件，上传失败", "close");
                return;
            }
			fileName = file.getName();
            extName = FileHelper.getExtension(fileName);
            final String fileNamePartten = ".*\\.(jsp|jspx|asp|aspx|sh|pl|bat|php).*";
            /**YQF 2008-10-21 修改上传文件扩展名限制 **/
            //if ("jsp|jspx".indexOf(extName.toLowerCase()) != -1)
            if (fileName.toLowerCase().matches(fileNamePartten))
            {
                /**xl 2011-12-20 删除上传的非法扩展名文件 **/
                String tempPath = uploadPath + fileName;
                if(StringHelper.isNotEmpty(tempPath))
                {
                	FileHelper.deleteFile(tempPath);
                }
                
                ScriptHelper.alert(response, "对不起，不允许上传此文件", "close");
                return;
            }
            String newFileName = String.valueOf(DateHelper.formatDate(new Date(), "yyyyMMdd") + System.currentTimeMillis());
            if (!StringHelper.isEmpty(extName))
            {
                newFileName = newFileName + "." + extName;
            }

            String destFile = uploadPath + newFileName;

            // System.out.println("###################"+file.getPath()+"################"+destFile);
            FileHelper.renameTo(file.getPath(), destFile);
            fileUrlPath = SysLibrary.getUploadUrlPath() + newFileName;

            /**YZZ 2008-9-4 13:07:27 修改保存到本地**/
            String urls = Configuration.getString("webconfig.uploadPath");
            if (!StringHelper.isEmpty(urls))
            {
                String datePath = DateHelper.formatDate(new Date(), "yyyyMMdd");
                String[] otherPaths = urls.split("\\|");
                for (int i = 0; i < otherPaths.length; i++)
                {
                    String otherPath = FileHelper.normalize(otherPaths[i]).trim();
                    if (!otherPath.endsWith(File.separator))
                    {
                        otherPath += File.separator;
                    }
                    otherPath = otherPath + datePath + File.separator;
                    if (!FileHelper.isDirectory(otherPath))
                    {
                        FileHelper.createDirectory(otherPath);
                    }
                    //System.out.println(otherPath+newFileName);
                    FileHelper.copyFile(new File(destFile), new File(otherPath + newFileName));
                }
            }
            //保存入数据库
            /*          Connection conn = null;
                        try
                        {
                            File srcFile = new File(destFile);
                            FileInputStream inputStream = new FileInputStream(srcFile);
                            long length = srcFile.length();

                            String sql = "insert into T_ATTACHFILE(URL,CONTENT) values(?,?) ";
                            conn = ConnManager.getConnection();
                            PreparedStatement pstmt = conn.prepareStatement(sql);
                            pstmt.setString(1, fileUrlPath);
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
            */


            String context = request.getContextPath();
            if (!StringHelper.isEmpty(context))
            {
                fileUrlPath = context + fileUrlPath;
            }
            pageContext.setAttribute("fileName",fileName.replace("."+extName,""));
            pageContext.setAttribute("fileUrlPath",fileUrlPath);

        }
        catch (Exception ex)
        {
            ScriptHelper.alert(response, "上传时发生错误，上传失败", "close");
            return;
        }

%>
<script language="javascript">
	alert("888");
	s = window.dialogArguments;
	if(s.length==1)
	{
		s.attr('value','${fileUrlPath}');
	}
	if(s.length==2)
	{
		var fileName = s[0];
    	var fieldObj = s[1];
    	if(fileName)
    	{
    		fileName.attr('value','${fileName}');
    	}
    	if (fieldObj)
    	{
        	fieldObj.attr('value','${fileUrlPath}');
    	}
	}
    window.returnValue = "${fileUrlPath}";
    window.close();
</script>
<%
    }
%>
