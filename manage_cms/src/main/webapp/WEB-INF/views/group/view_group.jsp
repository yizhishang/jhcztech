<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
    <td><div class="title"><p><img src="${ctxPath }/admin/images/ico04.gif" />查看组</p></div></td>
  </tr>
  <tr>
    <td class="edinner"><table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td><div class="space"></div>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="editortable">
              <tr>
                <td colspan="2" style="height:8px;"></td>
              </tr>
              <tr>
                <td width="120" class="label">组名称：</td>
                <td><c:out value='${form.name}' /></td>
              </tr>
              <tr>
                <td width="120" class="label">组名称：</td>
                <td><c:out value='${form.description}' /></td>
              </tr>
              <tr>
                <td width="120" class="label">创建时间：</td>
                <td><c:out value='${form.create_date}' /></td>
              </tr>
              <tr>
                <td colspan="2" style="height:8px;"></td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td colspan="2" style="height:8px;"></td>
        </tr>
        </tr>
           <td colspan="2" style="text-align:center;">
            	<input type="button" id="close" name="close" value="关闭" class="bt04"/>
            </td>
          </tr>
      </table></td>
  </tr>
</table>
</body>
</html>