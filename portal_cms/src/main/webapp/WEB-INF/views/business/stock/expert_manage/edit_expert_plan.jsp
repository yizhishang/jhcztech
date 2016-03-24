<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
	$(document).ready(function(){
	
		$("#close").click(function(){
			window.close();
		});
		
	});
</script>
<form action="expertManage.action" method="post" target="hiddenFrame" id="addForm">
  <input type="hidden" name="function" value="editPlan"/>
  <input type="hidden" name="form.expid" value="<c:out value='${form.expid}'/>">
  <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
    <tr>
      <td><div class="title">
          <p><img src="${ctxPath }/admin/images/ico04.gif" />修改值班表</p>
        </div></td>
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
                  <td width="120" rowspan="2" class="label"><span class="DetailTagText">星期一</span>：</td>
                  <td><c:choose>
                      <c:when test="${form.w10=='1'}">
                        <input type="checkbox" name="form.w10" value="1" checked>
                      </c:when>
                      <c:otherwise>
                        <input type="checkbox" name="form.w10" value="1">
                      </c:otherwise>
                    </c:choose>
                    上午</td>
                </tr>
                <tr>
                  <td><c:choose>
                      <c:when test="${form.w11=='1'}">
                        <input type="checkbox" name="form.w11" value="1" checked>
                      </c:when>
                      <c:otherwise>
                        <input type="checkbox" name="form.w11" value="1">
                      </c:otherwise>
                    </c:choose>
                    下午</td>
                </tr>
                <tr>
                  <td rowspan="2" class="label"><span class="DetailTagText">星期二</span>：</td>
                  <td><c:choose>
                      <c:when test="${form.w20=='1'}">
                        <input type="checkbox" name="form.w20" value="1" checked>
                      </c:when>
                      <c:otherwise>
                        <input type="checkbox" name="form.w20" value="1">
                      </c:otherwise>
                    </c:choose>
                    上午</td>
                </tr>
                <tr>
                  <td><c:choose>
                      <c:when test="${form.w21=='1'}">
                        <input type="checkbox" name="form.w21" value="1" checked>
                      </c:when>
                      <c:otherwise>
                        <input type="checkbox" name="form.w21" value="1">
                      </c:otherwise>
                    </c:choose>
                    下午</td>
                </tr>
                <tr>
                  <td rowspan="2" class="label"><span class="DetailTagText">星期三</span>：</td>
                  <td><c:choose>
                      <c:when test="${form.w30=='1'}">
                        <input type="checkbox" name="form.w30" value="1" checked>
                      </c:when>
                      <c:otherwise>
                        <input type="checkbox" name="form.w30" value="1">
                      </c:otherwise>
                    </c:choose>
                    上午</td>
                </tr>
                <tr>
                  <td><c:choose>
                      <c:when test="${form.w31=='1'}">
                        <input type="checkbox" name="form.w31" value="1" checked>
                      </c:when>
                      <c:otherwise>
                        <input type="checkbox" name="form.w31" value="1">
                      </c:otherwise>
                    </c:choose>
                    下午</td>
                </tr>
                <tr>
                  <td rowspan="2" class="label"><span class="DetailTagText">星期四</span>：</td>
                  <td><c:choose>
                      <c:when test="${form.w40=='1'}">
                        <input type="checkbox" name="form.w40" value="1" checked>
                      </c:when>
                      <c:otherwise>
                        <input type="checkbox" name="form.w40" value="1">
                      </c:otherwise>
                    </c:choose>
                    上午</td>
                </tr>
                <tr>
                  <td><c:choose>
                      <c:when test="${form.w41=='1'}">
                        <input type="checkbox" name="form.w41" value="1" checked>
                      </c:when>
                      <c:otherwise>
                        <input type="checkbox" name="form.w41" value="1">
                      </c:otherwise>
                    </c:choose>
                    下午</td>
                </tr>
                <tr>
                  <td rowspan="2" class="label"><span class="DetailTagText">星期五</span>：</td>
                  <td><c:choose>
                      <c:when test="${form.w50=='1'}">
                        <input type="checkbox" name="form.w50" value="1" checked>
                      </c:when>
                      <c:otherwise>
                        <input type="checkbox" name="form.w50" value="1">
                      </c:otherwise>
                    </c:choose>
                    上午</td>
                </tr>
                <tr>
                  <td><c:choose>
                      <c:when test="${form.w51=='1'}">
                        <input type="checkbox" name="form.w51" value="1" checked>
                      </c:when>
                      <c:otherwise>
                        <input type="checkbox" name="form.w51" value="1">
                      </c:otherwise>
                    </c:choose>
                    下午</td>
                </tr>
                <tr>
                  <td class="label">&nbsp;</td>
                  <td><input type="submit" name="enterForm" id="enterForm" value="提交" class="bt04"/>
                    &nbsp;
                    <input type="button" id="close" name="close"value="关闭" class="bt04"/>
                  </td>
                </tr>
                <tr>
                  <td colspan="2" style="height:8px;"></td>
                </tr>
              </table></td>
          </tr>
        </table></td>
    </tr>
  </table>
</form>
<iframe id="hiddenFrame" name="hiddenFrame" width="0"  height="0" frameborder="0" marginwidth="0" marginheight="0" src="${ctxPath }/null.html"></iframe>
</body>
</html>