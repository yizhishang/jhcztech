<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		$("#close").click(function(){
			window.close();
		});
		
	});
    function saveFunction(formObj)
    {
        formObj.submit();
    }
    
    function checkFund(obj)
    {
    	var id = obj.value;
    	//alert(id);
    	var array = id.split("_");
    	id = array[0];
    	var obj = document.getElementsByName(id);
    	if( obj[0].checked == false)
    	{
    		obj[0].checked = true;
    	}
    }
    
   	function checkService(obj)
    {
			var objs = document.all;
			for(i = 0;i < objs.length;i++)
			{
				if(objs[i].type == 'checkbox')
				{
					var id = obj.id + "_";
					var objsId = objs[i].id;
					var sub = objsId.substr(0,id.length);
					if(id == sub)
					{
						objs[i].checked = obj.checked;
					}
				}
			}	
	}
	
	function allFund(obj)
	{
		var bool = obj.checked;
		var objs = document.all;
		for(i = 0;i < objs.length;i++)
		{
			if(objs[i].type == 'checkbox')
			{
			   objs[i].checked = bool;
			}
		}
	}
	
	function allService(obj)
	{
		checkFund(obj);
		var bool = obj.checked;
		var objs = document.all;
		for(i = 0;i < objs.length;i++)
		{
			if(objs[i].type == 'checkbox')
			{
				var id = obj.id;
				var objsId = objs[i].id;
				var sub = objsId.substr(0,id.length);
				var bname= objsId.substr(id.length);
				
				if(id == sub)
				{
					if(bname!='' && bname != 'b' && bname!='f')
					{
						objs[i].checked = bool;
					}
				}
			}
		}
	}
	
	function checkAll(thisform)
	{
		var fundBool = false;
		var serviceBool = true;
		var objs = document.all;
		for(i = 0;i < objs.length;i++)
		{
			if(objs[i].type == 'checkbox')
			{
				if(objs[i].id.indexOf('_') == -1 && objs[i].checked == true)
				{
					if(objs[i].checked == true)
					{
						fundBool = true;
					}
				}
			}
		}
		if(fundBool == false)
		{
			alert("请至少选择一种基金");
			return false;
		}
		if(fundBool == true)
		{
			saveFunction(thisform);
		}
	}
</script>
<form action="seatService.action" method="post" target="hiddenFrame" id="addForm">
<input type="hidden" name="function" value="add"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%" >
  <tr>
    <td>
        <div class="title">
            <p><img src="${ctxPath }/admin/images/ico04.gif" />增加代销机构业务</p>
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
                <td width="120" class="label"><span class="DetailTagText">机构名称</span>：</td>
                <td><select name="form.seatno">
                      <c:choose><c:when test="${empty seatList}"><option value="-1" selected="selected">无机构信息</option></c:when>
                      <c:otherwise>
                       <c:forEach var="vo" items="${seatList}">
                             <option value="<c:out value='${vo.seatno}'/>"><c:out value='${vo.seatnm}'/></option>
                      </c:forEach></c:otherwise></c:choose>
                    </select>
					<font color="#FF0000">*</font></td>
              </tr>
			  <tr>
                <td width="120" class="label"><span class="DetailTagText">代销基金</span>：</td>
                <td>
                      <table width="97%" border="0" cellspacing="0" cellpadding="0" class="editortable">
                        <tr class="title">
                          <td><input type="checkbox" onclick = "allFund(this)" id = "allfund"/>&nbsp;代销基金</td>
                          <td style="display:none">&nbsp;收费方式</td>
                          <td>&nbsp;开通业务</td>
                          </tr>
	                       <c:forEach var="item" items="${data.cmfFund}">
	                        <tr>
                             <td>  
                              <input onclick="checkService(this)" type="checkbox" id = "<c:out value='${item.fundid}'/>" name="form.fund" value="<c:out value='${item.fundid}'/>"/>                                               						
                              <c:out value='${item.fundnm}'/>
                             </td>
                              <td style="display:none">
                              <input type="checkbox" name="form.chargetype" value='<c:out value='${item.fundid}'/>_1' id="<c:out value='${item.fundid}'/>_f" > 前端 <input type="checkbox" name="form.chargetype" value='<c:out value='${item.fundid}'/>_2' id="<c:out value='${item.fundid}'/>_b" > 后端
                              </td>
                             <td> 
                             <c:forEach var="itemservice" items="${data.service}">
                             <input type="checkbox" onclick="checkFund(this)" id = "<c:out value='${item.fundid}'/>_<c:out value='${itemservice.dict_value}'/>" name="form.serviceid" value="<c:out value='${item.fundid}'/>_<c:out value='${itemservice.dict_value}'/>" />
                             <c:out value='${itemservice.value_desc}'/>
                            </c:forEach>
                            </td>
                           </tr>
	                     </c:forEach>
                    </table>                            
               <div class="space"></div></td>
              </tr>
              <tr>
                <td class="label">&nbsp;</td>
              <td><div class="space"></div>
                	<input type="button" name="enterForm" id="enterForm" value="提交" class="bt04" onclick="javascript:checkAll(this.form);"/>&nbsp;
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
