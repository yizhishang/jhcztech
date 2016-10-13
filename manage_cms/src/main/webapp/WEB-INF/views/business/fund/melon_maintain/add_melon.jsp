<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		$.formValidator.initConfig({formid:"addForm",alertmessage:false,onerror:function(msg){alert(msg)}});
		$("#fundid").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"基金代码不能为空,请确认"});
		$("#dividedate").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"分红日期不能为空,请确认"});
		$("#divideProj").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"分红方案不能为空,请确认"});
		
		$("#close").click(function(){
			window.close();
		});
		
	});
</script>
<form action="melonMd.action" method="post" target="hiddenFrame" id="addForm">
<input type="hidden" name="function" value="add"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%" >
  <tr>
    <td>
        <div class="title">
            <p><img src="${ctxPath }/admin/images/ico04.gif" />添加基金分红信息</p>
        </div>
    </td>
  </tr>
  <tr>
    <td class="edinner" valign="top"><table width="90%" border="0" align="center" cellpadding="0" cellspacing="0" >
      
      <tr>
        <td>
			<div class="space"></div>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="editortable">
			  <tr>
                <td colspan="2" style="height:8px;"></td>
              </tr>
              <tr>
                <td width="120" class="label"><span class="DetailTagText">基金代码</span>：</td>
                <td><select name="form.fundid">
                      <c:forEach var="vo" items="${cmfFund}">
                             <option value="<c:out value='${vo.fundid}'/>"><c:out value='${vo.fundnm}'/></option>
                      </c:forEach>
                    </select>
					<font color="#FF0000">*</font></td>
              </tr>
			  <tr>
                <td width="120" class="label"><span class="DetailTagText">分红日期</span>：</td>
                <td><input type="text" name="form.dividedate" readonly="readonly" id="dividedate" class="input02" style="width:116px;" value="${form.dividedate}"/>
                <img src="${ctxPath }/admin/images/pic13.gif" width="17" height="15" style="cursor:pointer" onClick="WdatePicker({el:'form.dividedate'})"/>                                   
                  <font color="#FF0000">*</font></td>
              </tr>
			  <tr>
                <td width="120" class="label"><span class="DetailTagText">状态</span>：</td>
                <td><select name="form.status">
                     <option value='N'>正常</option>
                     <option value='C'>撤销</option>
                    </select>
                  <font color="#FF0000">*</font></td>
              </tr>
              <tr>
                <td width="120" class="label"><span class="DetailTagText">分红方案</span>：</td>
                <td><textarea rows="5" cols="50" name="form.divideproj" class="input02" style="height:80px;"><c:out value="${form.divideproj }"/></textarea>
                  <font color="#FF0000">*</font><div class="space"></div></td>
              </tr>
              <tr id="wfsyl">
                <td width="120" class="label"><span class="DetailTagText">备注</span>：</td>
                <td><textarea rows="5" cols="50" name="form.remark" class="input02" style="height:80px;"><c:out value="${form.remark }"/></textarea></td>
              </tr>
              <tr>
                <td class="label">&nbsp;</td>
              <td><div class="space"></div>
                	<input type="submit" name="enterForm" id="enterForm" value="提交" class="bt04"/>&nbsp;
                <input type="button" id="close" name="close"value="关闭" class="bt04"/>
                </td>
              </tr>
			  <tr>
                <td colspan="2" style="height:8px;"></td>
              </tr>
            </table>
          </td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
<iframe id="hiddenFrame" name="hiddenFrame" width="0"  height="0" frameborder="0" marginwidth="0" marginheight="0" src="${ctxPath }/null.html"></iframe>
</body>
</html>
