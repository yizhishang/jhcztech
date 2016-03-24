<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
     var fundType = false;	
	$(document).ready(function(){
		$.formValidator.initConfig({formid:"addForm",alertmessage:false,onerror:function(msg){alert(msg)}});
		$("#settledate").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"净值日期不能为空,请确认"});
		$("#nav").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"单位净值不能为空,请确认"});
		$("#sumofnav").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"累计净值不能为空,请确认"});
		$("#daily_growth_rate").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"日增长率不能为空,请确认"});
		$("#close").click(function(){
			window.close();
		});
		
	});
	function change(obj)
    {
    	var value = obj.value;
    	asynCall("NVMaintain.action?function=fundType&value=" + value,setFundType);
    }
     function setFundType(obj)
    {
    	var incomerate = document.getElementById("wfsyl");
    	var weekincomerate = document.getElementById("qrnhsyl");
    	if(obj == "true")
    	{
    		incomerate.style.display = "block";
    		weekincomerate.style.display = "block";
    		$("#incomerate").val('${form.incomerate}');
    		$("#weekincomerate").val('${form.weekincomerate}');
    		fundType = true;
    	}
    	else
    	{
    		incomerate.style.display = "none";
    		weekincomerate.style.display = "none";
    		$("#incomerate").val("");
    		$("#weekincomerate").val("");
    		fundType = false;
    	}
    }
    
</script>
<form action="NVMaintain.action" method="post" target="hiddenFrame" id="addForm">
<input type="hidden" name="function" value="add"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%" >
  <tr>
    <td>
        <div class="title">
            <p><img src="${ctxPath }/admin/images/ico04.gif" />添加基金净值</p>
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
                <td width="120" class="label"><span class="DetailTagText">净值日期</span>：</td>
                <td><input type="text" name="form.settledate" readonly="readonly" id="settledate" class="input02" style="width:116px;" value="${form.settledate}"/>
                <img src="${ctxPath }/admin/images/pic13.gif" width="17" height="15" style="cursor:pointer" onClick="WdatePicker({el:'form.settledate'})"/>
					<font color="#FF0000">*</font></td>
              </tr>
			  <tr>
                <td width="120" class="label"><span class="DetailTagText">基金名称</span>：</td>
                <td><select name="form.fundid" onchange="change(this)" id="fundid">
                      <c:forEach var="vo" items="${cmfFund}">
                         <option value="<c:out value='${vo.fundid}'/>"><c:out value='${vo.fundnm}'/></option>
                      </c:forEach>
                    </select>  
                    <script type="text/javascript">change(document.getElementById("fundid"));</script>                                    
                  <font color="#FF0000">*</font></td>
              </tr>
			  <tr>
                <td width="120" class="label"><span class="DetailTagText">单位净值</span>：</td>
                <td><input type="text" name="form.nav" id="nav" class="input02" style="width:116px;" value="<c:out value='${form.nav}'/>"/>
                  <font color="#FF0000">*</font></td>
              </tr>
              <tr>
                <td width="120" class="label"><span class="DetailTagText">累计净值</span>：</td>
                <td><input type="text" name="form.sumofnav" id="sumofnav" class="input02" style="width:116px;" value="<c:out value='${form.sumofnav}'/>"/>
                  <font color="#FF0000">*</font></td>
              </tr>
              <tr id="wfsyl" style="display: none;">
                <td width="120" class="label"><span class="DetailTagText">万份收益率</span>：</td>
                <td><input type="text" name="form.incomerate" id="incomerate" class="input02" style="width:116px;" value="<c:out value='${form.incomerate}'/>"/>
                  <font color="#FF0000">*</font></td>
              </tr>
              <tr id="qrnhsyl" style="display: none;">
                <td width="120" class="label"><span class="DetailTagText">七日年化收益率</span>：</td>
                <td><input type="text" name="form.weekincomerate" id="weekincomerate" class="input02" style="width:116px;" value="<c:out value='${form.weekincomerate}'/>"/>
                  <font color="#FF0000">*</font></td>
              </tr>
               <tr>
                <td width="120" class="label"><span class="DetailTagText">日增长率</span>：</td>
                <td><input type="text" name="form.daily_growth_rate" id="daily_growth_rate" class="input02" style="width:116px;" value="<c:out value='${form.daily_growth_rate}'/>"/>
                  <font color="#FF0000">*</font></td>
              </tr>
               <tr>
                <td width="120" class="label"><span class="DetailTagText">状态</span>：</td>
                <td> <select name="form.status">
                    <c:forEach var="vo" items="${state}">
                         <option value="<c:out value='${vo.dict_value}'/>"><c:out value='${vo.value_desc}'/></option>
                    </c:forEach>
                     </select>
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
