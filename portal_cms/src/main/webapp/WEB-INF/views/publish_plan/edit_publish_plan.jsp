<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		$("#addForm").submit(function(){
			var type = '${form.type}';
			var time;
			if(type == 0)
			{
				time = $("#fixedHour").val() + ":" + $("#fixedMinute").val();
			}
			else
			{
				time = $("#minute").val();
			}
			$("#time").val(time);
			return true;
		});
		
		$("#type").change(function(){
			if($(this).val() == 4)
			{
				$("#tr_filepath").show();
			}
			else
			{
				$("#filePath").val("");
				$("#tr_filepath").hide();
			}
		});
		
		$("#close").click(function(){
			window.close();
		});
		
	});
</script>
<form action="edit.action" method="post" target="hiddenFrame" id="addForm">
  <input type="hidden" name="function" value="edit"/>
  <input type="hidden" name="form.id" value="${form.id}"/>
  <input type="hidden" name="form.catalog_id" value="${form.catalog_id}"/>
  <input type="hidden" name="form.type" value="${form.type}"/>
  <input type="hidden" name="form.time" id="time"/>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
    <tr>
      <td><div class="title">
          <p><img src="${ctxPath }/admin/images/ico04.gif" />修改发布计划</p>
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
                  <td width="120" class="label">栏目名称：</td>
                  <td>${form.name}</td>
                </tr>
                <tr>
                  <td width="120" class="label">发布类型：</td>
                  <td><c:if test="${form.type eq '0'}"> 固定时间发布 </c:if>
                    <c:if test="${form.type eq '1'}"> 间隔时间发布 </c:if>
                  </td>
                </tr>
                <tr>
                  <td width="120" class="label">时间：</td>
                  <td><c:choose>
				  <c:when test="${form.type eq '0'}">
                      <select name="fixedHour" id="fixedHour">
                        <script language="javascript">
				  	for(var i=0;i<24;i++)
					{
						var j = i;
						if(i<10)
							j = "0"+i;
						document.write("<option value='"+j+"'>"+j+"</option>");
					}
				  </script>
                      </select>
                      <script language="javascript">setSelectSelected("fixedHour", "${form.fixedHour}")</script>
                      时
                      <select name="fixedMinute" id="fixedMinute">
                        <script language="javascript">
				  	for(var i=0;i<60;i++)
					{
						var j = i;
						if(i<10)
							j = "0"+i;
						document.write("<option value='"+j+"'>"+j+"</option>");
					}
				  </script>
                      </select>
					  <script language="javascript">setSelectSelected("fixedMinute", "${form.fixedMinute}")</script>
                      分
					 </c:when>
					 <c:otherwise>
                      <select name="minute" id="minute">
                        <script language="javascript">
				  	for(var i=1;i<=60;i++)
					{
						var j = i;
						if(i<10)
							j = "0"+i;
						document.write("<option value='"+j+"'>"+j+"</option>");
					}
				  </script>
                      </select>
                      <script language="javascript">setSelectSelected("minute", "${form.time}")</script>
                      分
					  </c:otherwise>
					  </c:choose>
					  </td>
                </tr>
                <tr>
                  <td width="120" class="label">是否发布子栏目：</td>
                  <td><input type="radio" name="form.recursion" value="0" checked="checked">
                    否
                    <input type="radio" name="form.recursion" value="1" >
                    是
                    <script language="javascript">setRadioChecked("form.recursion", "<c:out value='${form.recursion}'/>")</script>
                  </td>
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