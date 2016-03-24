<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		$.formValidator.initConfig({formid:"editForm",alertmessage:false,onerror:function(msg){alert(msg)}});
		$("#publish_date").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"发布日期不能为空,请确认"});
		$("#weekly_return").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"最近一周不能为空,请确认"});
		$("#monthly_return").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"最近一月不能为空,请确认"});
		$("#quarter_return").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"三个月收益不能为空,请确认"});
		$("#halfyear_return").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"半年收益不能为空,请确认"});
		$("#year_return").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"一年收益不能为空,请确认"});
		$("#twoyear_yearreturn").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"二年收益不能为空,请确认"});
		$("#fromyear_return").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"今年以来不能为空,请确认"});
		$("#frombuild_return").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"成立以来不能为空,请确认"});
		$("#close").click(function(){
			window.close();
		});
		
	});
</script>
<form action="fundReturn.action" method="post" target="hiddenFrame" id="editForm">
<input type="hidden" name="function" value="edit"/>
<input type="hidden" name="form.id" value="<c:out value='${form.id}'/>">
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%" >
  <tr>
    <td>
        <div class="title">
            <p><img src="${ctxPath }/admin/images/ico04.gif" />编辑基金收益</p>
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
                     <script language="javascript">setSelectSelected("form.fundid",'<c:out value='${form.fundid}'/>')</script>
					<font color="#FF0000">*</font></td>
              </tr>
			  <tr>
                <td width="120" class="label"><span class="DetailTagText">发布日期</span>：</td>
                <td><input type="text" name="form.publish_date" readonly="readonly" id="publish_date" class="input02" style="width:116px;" value="${form.publish_date}"/>
                <img src="${ctxPath }/admin/images/pic13.gif" width="17" height="15" style="cursor:pointer" onClick="WdatePicker({el:'form.publish_date'})"/>                                   
                  <font color="#FF0000">*</font></td>
              </tr>
			  <tr>
                <td width="120" class="label"><span class="DetailTagText">最近一周</span>：</td>
                <td><input type="text" name="form.weekly_return" id="weekly_return" class="input02" style="width:116px;" value="<c:out value='${form.weekly_return}'/>"/>
                  <font color="#FF0000">*</font></td>
              </tr>
              <tr>
                <td width="120" class="label"><span class="DetailTagText">最近一月</span>：</td>
                <td><input type="text" name="form.monthly_return" id="monthly_return" class="input02" style="width:116px;" value="<c:out value='${form.monthly_return}'/>"/>
                  <font color="#FF0000">*</font></td>
              </tr>
              <tr id="wfsyl">
                <td width="120" class="label"><span class="DetailTagText">三个月收益</span>：</td>
                <td><input type="text" name="form.quarter_return" id="quarter_return" class="input02" style="width:116px;" value="<c:out value='${form.quarter_return}'/>"/>
                  <font color="#FF0000">*</font></td>
              </tr>
              <tr id="qrnhsyl">
                <td width="120" class="label"><span class="DetailTagText">半年收益</span>：</td>
                <td><input type="text" name="form.halfyear_return" id="halfyear_return" class="input02" style="width:116px;" value="<c:out value='${form.halfyear_return}'/>"/>
                  <font color="#FF0000">*</font></td>
              </tr>
               <tr>
                <td width="120" class="label"><span class="DetailTagText">一年收益</span>：</td>
                <td><input type="text" name="form.year_return" id="year_return" class="input02" style="width:116px;" value="<c:out value='${form.year_return}'/>"/>
                  <font color="#FF0000">*</font></td>
              </tr>
               <tr>
                <td width="120" class="label"><span class="DetailTagText">二年收益</span>：</td>
                <td><input type="text" name="form.twoyear_yearreturn" id="twoyear_yearreturn" class="input02" style="width:116px;" value="<c:out value='${form.twoyear_yearreturn}'/>"/>
                  <font color="#FF0000">*</font></td>
              </tr>
               <tr>
                <td width="120" class="label"><span class="DetailTagText">今年以来</span>：</td>
                <td><input type="text" name="form.fromyear_return" id="fromyear_return" class="input02" style="width:116px;" value="<c:out value='${form.fromyear_return}'/>"/>
                  <font color="#FF0000">*</font></td>
              </tr>
              <tr>
                <td width="120" class="label"><span class="DetailTagText">成立以来</span>：</td>
                <td><input type="text" name="form.frombuild_return" id="frombuild_return" class="input02" style="width:116px;" value="<c:out value='${form.frombuild_return}'/>"/>
                  <font color="#FF0000">*</font></td>
              </tr>
               <tr>
                <td width="120" class="label"><span class="DetailTagText">状态</span>：</td>
                <td> <select name="form.status">
                      <option value='N'>正常</option>
                      <option value='C'>撤销</option>
                   </select>
                    <script language="javascript">setSelectSelected("form.status",'<c:out value='${form.status}'/>')</script> 
                  <font color="#FF0000">*</font></td>
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
