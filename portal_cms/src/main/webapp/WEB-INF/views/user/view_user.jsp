<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
    <td class="edinner"><table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td>
        	<div class="space"></div>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="editortable">
               <tr>
                <td colspan="2"><h5>用户信息</h5></td>
              </tr>
              <tr>
                <td colspan="2" style="height:8px;"></td>
              </tr>
              <tr>
                <td class="label">用户标识：</td>
                <td><c:out value='${form.uid}' /></td>
              </tr>
               <tr>
                <td class="label">用户名称：</td>
                <td><c:out value='${form.name}' /></td>
              </tr>
               <tr>
                <td class="label">用户状态：</td>
                <td> <c:if test="${form.state == 0}"><font color="#ff0000">关闭</font></c:if>
                     <c:if test="${form.state == 1}"><font color="#ff0000">开放</font></c:if></td>
              </tr>
               <tr>
                <td class="label">登录次数：</td>
                <td><c:out value='${form.loginTimes}' /></td>
              </tr>
               <tr>
                <td class="label">最后登录时间：</td>
                <td><c:out value='${form.lastTime}' /></td>
              </tr>
               <tr>
                <td class="label">电子邮箱：</td>
                <td><c:out value='${form.email}'/></td>
              </tr>
              <tr>
                <td class="label">&nbsp;</td>
              	<td><input type="submit" name="button" id="button" onclick="javascript:window.close();" value="关闭" class="bt04"/>&nbsp;
                </td>
              </tr>
            </table>
          </td>
      </tr>
    </table></td>
  </tr>
</table>


</body>
</html>
