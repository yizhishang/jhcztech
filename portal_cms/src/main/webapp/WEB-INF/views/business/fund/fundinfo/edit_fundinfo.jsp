<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">	
	if(window.frameElement!=null && document.all != null)
	{
		window.frameElement.attachEvent("onresize",winResize);
	}
	else if(document.all != null)
	{
		window.attachEvent("onresize",winResize);
	} 
	else
	{
		window.onresize=winResize;
	}
	
	function winResize()
	{
		$("#divContainer").css("height",$(window).height() - 87);
	}
	
	$(document).ready(function(){
		winResize();
		
		$.formValidator.initConfig({formid:"addForm",alertmessage:false,onerror:function(msg){alert(msg)}});
		$("#fundid").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"基金编号不能为空,请确认"});
		$("#establish_date").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"成立日期不能为空,请确认"});
		$("#close").click(function(){
			window.close();
		});
	});
	 function setMgrName(sltObj)
    {
    	document.getElementById("mgrname").value=sltObj.options[sltObj.selectedIndex].innerText;;
    }
    function setCustodianName(sltObj)
    {
    	document.getElementById("custodianname").value=sltObj.options[sltObj.selectedIndex].innerText;;
    }
    function saveFunction()
	{	
		$("#addForm").submit();
	}
	
	function closeFunction()
	{
		window.close();
	}
</script>
<form action="fundInfo.action" method="post" target="hiddenFrame" id="addForm">
<input type="hidden" name="function" value="edit"/>
<input type="hidden" name="form.fundid" value="<c:out value='${form.fundid}'/>" id="fundid">
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%" >
  <tr>
    <td valign="top">
        <div class="title">
            <p><img src="${ctxPath }/admin/images/ico04.gif" />编辑基金信息</p>
        </div>
    </td>
  </tr>
  <tr>
    <td class="edinner" style="width:100%"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
      
      <tr>
         <td><div class="space"></div>
         <div class="label"><a href="#" onClick="javascript:saveFunction();"><img src="${ctxPath }/admin/images/icon018a16.gif" border="0"/>保存</a><a href="#" onClick="javascript:closeFunction();"><img src="${ctxPath }/admin/images/closeimg.gif" border="0"/>关闭</a> </div></td>
     </tr>
      <tr>
        <td>
			<div class="space"></div>
			<div id="divContainer" style="overflow-y: auto;overflow-x:hidden;width:100%;">
            <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="editortable">
			  <tr>
                <td colspan="2" style="height:8px;"></td>
              </tr>
              <tr>
                <td width="120" class="label"><span class="DetailTagText">基金代码</span>：</td>
                <td> <c:out value='${form.fundid}'/></td>
              </tr>
			  <tr>
                <td width="120" class="label"><span class="DetailTagText">后端收费代码</span>：</td>
                <td><input type="text" name="form.backfundid" id="backfundid" class="input02" style="width:116px;" value="<c:out value='${form.backfundid}'/>"/>
                  （LOF基金类型需要）</td>
              </tr>
			  <tr>
                <td width="120" class="label"><span class="DetailTagText">基金名称</span>：</td>
                <td><input type="text" name="form.fundnm" id="fundnm" class="input02" style="width:116px;" value="<c:out value='${form.fundnm}'/>"/>
                  <font color="#FF0000">*</font></td>
              </tr>
              <tr>
                <td width="120" class="label"><span class="DetailTagText">基金简称</span>：</td>
                <td><input type="text" name="form.fundshortnm" id="fundshortnm" class="input02" style="width:116px;" value="<c:out value='${form.fundshortnm}'/>"/>
                  <font color="#FF0000">*</font></td>
              </tr>
              <tr id="wfsyl">
                <td width="120" class="label"><span class="DetailTagText">英文名称</span>：</td>
                <td><input type="text" name="form.fundengnm" id="fundengnm" class="input02" style="width:116px;" value="<c:out value='${form.fundengnm}'/>"/>
                  <font color="#FF0000">*</font></td>
              </tr>
              <tr id="qrnhsyl">
                <td width="120" class="label"><span class="DetailTagText">成立日期</span>：</td>
                <td><input type="text" name="form.establish_date" readonly="readonly" id="establish_date" class="input02" style="width:116px;" value="${form.establish_date}"/>
                <img src="${ctxPath }/admin/images/pic13.gif" width="17" height="15" style="cursor:pointer" onClick="WdatePicker({el:'form.establish_date'})"/>
                  <font color="#FF0000">*</font></td>
              </tr>
               <tr>
                <td width="120" class="label"><span class="DetailTagText">基金类型1</span>：</td>
                <td><select name="form.fundtp1">
                      <option value="">----请选择-----</option>
                        <c:forEach var="vo" items="${fundTPList1}">
                        <option value="<c:out value='${vo.dict_value}'/>"><c:out value='${vo.value_desc}'/></option>
                       </c:forEach>
                    </select>
                     <script language="javascript">setSelectSelected("form.fundtp1",'<c:out value='${form.fundtp1}'/>')</script>                                           	
                  </td>
              </tr>
               <tr>
                <td width="120" class="label"><span class="DetailTagText">基金类型2</span>：</td>
                <td><select name="form.fundtp2">
                     <option value="">----请选择-----</option>
                      <c:forEach var="vo" items="${fundTPList2}">
                         <option value="<c:out value='${vo.dict_value}'/>"><c:out value='${vo.value_desc}'/></option>
                      </c:forEach>
                   </select>
                    <script language="javascript">setSelectSelected("form.fundtp2",'<c:out value='${form.fundtp2}'/>')</script> 
                  </td>
              </tr>
               <tr>
                <td width="120" class="label"><span class="DetailTagText">基金类型3</span>：</td>
                <td><select name="form.fundtp3">
                   <option value="">----请选择-----</option>
                    <c:forEach var="vo" items="${fundTPList3}">
                      <option value="<c:out value='${vo.dict_value}'/>"><c:out value='${vo.value_desc}'/></option>
                       </c:forEach>
                    </select>
                    <script language="javascript">setSelectSelected("form.fundtp3",'<c:out value='${form.fundtp3}'/>')</script> 
                  </td>
              </tr>
              <tr>
                <td width="120" class="label"><span class="DetailTagText">基金状态</span>：</td>
                <td><select name="form.fundst">
                     <option value="">----请选择-----</option>
                      <c:forEach var="vo" items="${fundSTList}">
                        <option value="<c:out value='${vo.dict_value}'/>"><c:out value='${vo.value_desc}'/></option>
                       </c:forEach>
                    </select>
                    <script language="javascript">setSelectSelected("form.fundst",'<c:out value='${form.fundst}'/>')</script>
                  </td>
              </tr>
               <tr>
                <td width="120" class="label"><span class="DetailTagText">基金风险等级</span>：</td>
                <td><select name="form.riskrating">
                     <option value="">----请选择-----</option>
                     <c:forEach var="vo" items="${riskRatingList}">
                        <option value="<c:out value='${vo.dict_value}'/>"><c:out value='${vo.value_desc}'/></option>
                      </c:forEach>
                    </select>
                    <script language="javascript">setSelectSelected("form.riskrating",'<c:out value='${form.riskrating}'/>')</script> 
                  </td>
              </tr>
              <tr>
                <td width="120" class="label"><span class="DetailTagText">深圳交易所代码</span>：</td>
                <td><input type="text" name="form.szexch_code" id="szexch_code" class="input02" style="width:116px;" value="<c:out value='${form.szexch_code}'/>"/>
                  </td>
              </tr>
              <tr>
                <td width="120" class="label"><span class="DetailTagText">管理人名称</span>：</td>
                <td><select name="form.mgrcode" onChange="setMgrName(this)">
                    <option value="">----请选择-----</option>
                     <c:forEach var="vo" items="${mgrList}">
                     <option value="<c:out value='${vo.dict_value}'/>"><c:out value='${vo.value_desc}'/></option>
                     </c:forEach>
                   </select>
                   <script language="javascript">setSelectSelected("form.mgrcode",'<c:out value='${form.mgrcode}'/>')</script>
                   <input type="hidden" size="30"  name="form.mgrname" id="mgrname" class="input02" style="width:116px;" value="<c:out value='${form.mgrname}'/>">
                  </td>
              </tr>
               <tr>
                <td width="120" class="label"><span class="DetailTagText">托管人名称</span>：</td>
                <td><select name="form.custodian" onChange="setCustodianName(this)">
                     <option value="">----请选择-----</option>
                      <c:forEach var="vo" items="${custodianList}">
                      <option value="<c:out value='${vo.dict_value}'/>"><c:out value='${vo.value_desc}'/></option>
                      </c:forEach>
                     </select>
                     <script language="javascript">setSelectSelected("form.custodian",'<c:out value='${form.custodian}'/>')</script>
                   <input type="hidden" size="30"  name="form.custodianname" id="custodianname" class="input02" style="width:116px;" value="<c:out value='${form.custodianname}'/>">
                  </td>
              </tr>
              <tr>
                <td width="120" class="label"><span class="DetailTagText">管理费率</span>：</td>
                <td><input type="text" name="form.manage_rate" id="manage_rate" class="input02" style="width:116px;" value="<c:out value='${form.manage_rate}'/>"/>
                  </td>
              </tr>
              <tr>
                <td width="120" class="label"><span class="DetailTagText">托管年费</span>：</td>
                <td><input type="text" name="form.trustee_rate" id="trustee_rate" class="input02" style="width:116px;" value="<c:out value='${form.trustee_rate}'/>"/>
                  </td>
              </tr>
               <tr>
                <td width="120" class="label"><span class="DetailTagText">销售和服务费率</span>：</td>
                <td><input type="text" name="form.salesrv_rate" id="salesrv_rate" class="input02" style="width:116px;" value="<c:out value='${form.salesrv_rate}'/>"/>
                  </td>
              </tr>
               <tr>
                <td width="120" class="label"><span class="DetailTagText">认购费率</span>：</td>
                <td><input type="hidden" name="form.sub_rate" id="sub_rate" class="input02" style="width:116px;" value="<c:out value='${form.sub_rate}'/>"/>
                 <iframe id="eWebEditor_brief" src="editor/standard/ewebeditor.htm?id=sub_rate&style=mini" frameborder="0" scrolling="No" width="600" height="140"></iframe>
                   <div class="space"></div></td>
              </tr>
               <tr>
                <td width="120" class="label"><span class="DetailTagText">申购费率</span>：</td>
                <td><input type="hidden" name="form.buy_rate" id="buy_rate" class="input02" style="width:116px;" value="<c:out value='${form.buy_rate}'/>"/>
                 <iframe id="eWebEditor_brief" src="editor/standard/ewebeditor.htm?id=buy_rate&style=mini" frameborder="0" scrolling="No" width="600" height="140"></iframe>
                   <div class="space"></div></td>
              </tr>
              <tr>
                <td width="120" class="label"><span class="DetailTagText">赎回费率</span>：</td>
                <td><input type="hidden" name="form.redeem_rate" id="redeem_rate" class="input02" style="width:116px;" value="<c:out value='${form.redeem_rate}'/>"/>
                  <iframe id="eWebEditor_brief" src="editor/standard/ewebeditor.htm?id=redeem_rate&style=mini" frameborder="0" scrolling="No" width="600" height="140"></iframe>
                   <div class="space"></div></td>
              </tr>
              <tr>
                <td width="120" class="label"><span class="DetailTagText">后端认购费率</span>：</td>
                <td><input type="hidden" name="form.back_sub_rate" id="back_sub_rate" class="input02" style="width:116px;" value="<c:out value='${form.back_sub_rate}'/>"/>
                    <iframe id="eWebEditor_brief" src="editor/standard/ewebeditor.htm?id=back_sub_rate&style=mini" frameborder="0" scrolling="No" width="600" height="140"></iframe>                    	
                   <div class="space"></div></td>
              </tr>
              <tr>
                <td width="120" class="label"><span class="DetailTagText">后端申购费率</span>：</td>
                <td><input type="hidden" name="form.back_buy_rate" id="back_buy_rate" class="input02" style="width:116px;" value="<c:out value='${form.back_buy_rate}'/>"/>
                   <iframe id="eWebEditor_brief" src="editor/standard/ewebeditor.htm?id=back_buy_rate&style=mini" frameborder="0" scrolling="No" width="600" height="140"></iframe>            
                  <div class="space"></div> </td>
              </tr>
              <tr>
                <td width="120" class="label"><span class="DetailTagText">认/申购方式描述</span>：</td>
                <td><textarea name="form.subbuy_desc" maxlength="1000"  cols="70" rows="4" style="height: 60px;" class="input02"><c:out value='${form.subbuy_desc}'/></textarea>      	
                  </td>
              </tr>
              <tr>
                <td width="120" class="label"><span class="DetailTagText">成立规模</span>：</td>
                <td><input type="text" name="form.fundsize" id="fundsize" class="input02" style="width:116px;" value="<c:out value='${form.fundsize}'/>"/>                   	
                  </td>
              </tr>
              <tr>
                <td width="120" class="label"><span class="DetailTagText">资产净值</span>：</td>
                <td><input type="text" name="form.equity" id="equity" class="input02" style="width:116px;" value="<c:out value='${form.equity}'/>"/>                   	
                  </td>
              </tr>
              <tr>
                <td width="120" class="label"><span class="DetailTagText">基金展示类型</span>：</td>
                <td><select name="form.fundshowtp">
                   <option value="1" selected="selected">股票型</option>
                   <option value="2">债券型</option>     
                   <option value="3">货币型</option>     
                   <option value="4">LOF型</option>                           
			      </select>
			      <script language="javascript">setSelectSelected("form.fundshowtp",'<c:out value='${form.fundshowtp}'/>')</script>
                  </td>
              </tr>
              <tr>
                <td width="120" class="label"><span class="DetailTagText">净值保留位数</span>：</td>
                <td><select name="form.navscale">
                    <option value="3" selected="selected">3位</option>
                    <option value="4">4位</option>     
                    <option value="5">5位</option>
                    <option value="6">6位</option>                      
			     </select>
			       <script language="javascript">setSelectSelected("form.navscale",'<c:out value='${form.navscale}'/>')</script>
                  </td>
              </tr>
              <tr>
                <td width="140" class="label"><span class="DetailTagText">净值尾数处理方式</span>：</td>
                <td><select name="form.navtailtp">
                     <option value="0" selected="selected">截尾</option>
                     <option value="1">四舍五入</option>                      
			        </select>
			         <script language="javascript">setSelectSelected("form.navtailtp",'<c:out value='${form.navtailtp}'/>')</script>
                  </td>
              </tr>
              <tr>
                <td width="120" class="label"><span class="DetailTagText">状态</span>：</td>
                <td><select name="form.status">
                     <option value="N" selected="selected">正常</option>
                     <option value="C">撤销</option>                         
                    </select>
                     <script language="javascript">setSelectSelected("form.status",'<c:out value='${form.status}'/>')</script>
                  </td>
              </tr>
               <tr>
                <td width="120" class="label"><span class="DetailTagText">排列顺序</span>：</td>
                <td><input type="text" name="form.sn" id="sn" class="input02" style="width:116px;" value="<c:out value='${form.sn}'/>"/>                   	
                  </td>
              </tr>
               <tr>
                <td width="120" class="label"><span class="DetailTagText">备注</span>：</td>
                <td><textarea name="form.remark" maxlength="200"  cols="70" rows="4" style="height: 60px;" class="input02"><c:out value='${form.remark}'/></textarea>                 	
                  </td>
              </tr>
			  <tr>
                <td colspan="2" style="height:8px;"></td>
              </tr>
            </table>
            </div>
          </td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
<iframe id="hiddenFrame" name="hiddenFrame" width="0"  height="0" frameborder="0" marginwidth="0" marginheight="0" src="${ctxPath }/null.html"></iframe>
</body>
</html>
