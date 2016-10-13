<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<script language="javascript">
    alert("成功删除文件[<%=filePath%>]");
    var fieldObj = window.dialogArguments;
    if (fieldObj)
    {
       fieldObj.val("");
    }
    window.close();
</script>