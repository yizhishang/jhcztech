<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
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
		$("#divContainer").css({"height":$(window).height() - 90});
	}
	
	function addFunction()
	{
		$("#enterForm").submit();
	}
	
	function closeFunction()
	{
		window.close();
	}
	
	$(document).ready(function(){
		winResize();
		$.formValidator.initConfig({formid:"enterForm",alertmessage:false,onerror:function(msg){alert(msg)}});
		$("#branchno").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"营业部编号不能为空,请确认"}).regexValidator({regexp:regexEnum.num,onerror:"营业部编号格式不正确，请输入数字"});
		$("#branchname").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"营业部名称不能为空,请确认"});
		$("#zoom").formValidator({empty:true}).regexValidator({regexp:regexEnum.num,onerror:"放大倍数格式不正确，请输入数字"});
		$("#maxzoom").formValidator({empty:true}).regexValidator({regexp:regexEnum.num,onerror:"细览时的放大倍数格式不正确，请输入数字"});
	});
</script>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
    <td><jsp:include flush="true" page="/WEB-INF/views/business/stock/branch_manage/include/branchCatalogLink.jsp">
      <jsp:param name="catalogName" value="添加营业部信息"/>
      </jsp:include>
    </td>
  </tr>
  <tr>
    <td class="edinner" valign="top" style="width:100%"><form action="branchManage.action" method="post" id="enterForm" target="hiddenFrame">
        <input type="hidden" name="function" id="function" value="add"/>
        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td></td>
          </tr>
          <tr>
            <td><div class="space"></div>
              <div class="label"> <a href="#" onClick="addFunction();"><img src="${ctxPath }/admin/images/icon018a16.gif" border="0"/>保存</a> <a href="#" onClick="closeFunction();"><img src="${ctxPath }/admin/images/closeimg.gif" border="0"/>关闭</a> </div></td>
          </tr>
          <tr>
            <td valign="top"><div class="space"></div>
              <div id="divContainer" style="overflow-y: auto;overflow-x:hidden;width:100%;">
                <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="editortable">
                  <tr>
                    <td colspan="4" style="height:8px;"></td>
                  </tr>
                  <tr>
                    <td width="120" class="label">营业部编号：</td>
                    <td><input type="text" name="form.branchno" id="branchno" class="input02" style="width:150px;"/>
                      <font color="#FF0000">*</font></td>
                  </tr>
                  <tr>
                    <td class="label"><span class="DetailTagText">营业部名称</span>：</td>
                    <td ><input type="text" name="form.branchname" id="branchname" class="input02" style="width:150px;"/>
                      <font color="#FF0000">*</font></td>
                  </tr>
                  <tr>
                    <td class="label"><span class="DetailTagText">营业部区域</span>：</td>
                    <td><select name="form.region" style="width:150px;">
						<option value="">--请选择--</option>
                        <c:forEach var="item" items="${data.regionList}">
                          <option value="<c:out value='${item.id}'/>">
                          <c:out value='${item.regionname}' />
                          </option>
                        </c:forEach>
                      </select></td>
                  </tr>
                  <tr>
                    <td  class="label"><span class="DetailTagText">营业部城市</span>：</td>
                    <td><input type="text" name="form.city" id="city" class="input02" style="width:150px;"/></td>
                  </tr>
                  <tr>
                    <td class="label"><span class="DetailTagText">营业部地址</span>：</td>
                    <td ><input type="text" name="form.address" id="address" class="input02" style="width:150px;"/></td>
                  </tr>
                  <tr>
                    <td class="label"><span class="DetailTagText">营业部博客</span>：</td>
                    <td><input type="text" name="form.blog" id="blog" class="input02" style="width:150px;"/></td>
                  </tr>
                  <tr>
                    <td class="label"><span class="DetailTagText">客户资金存管银行</span>：</td>
                    <td><input type="text" name="form.bank" id="bank" class="input02" style="width:150px;" /></td>
                  </tr>
                  <tr>
                    <td class="label"><span class="DetailTagText">交易品种</span>：</td>
                    <td><input type="text" name="form.breed" id="breed" class="input02" style="width:150px;"/></td>
                  </tr>
                  <tr>
                    <td class="label"><span class="DetailTagText">沪A/沪B席位</span>：</td>
                    <td><input type="text" name="form.sha_shb" id="sha_shb" class="input02" style="width:150px;"/>
                      <font color="red">A股席位号与B席位号股请用“/”分割</font></td>
                  </tr>
                  <tr>
                    <td class="label"><span class="DetailTagText">深A/深B席位</span>：</td>
                    <td><input type="text" name="form.sza_szb" id="sza_szb" class="input02" style="width:150px;"/>
                      <font color="red">A股席位号与B席位号股请用“/”分割</font></td>
                  </tr>
                  <tr>
                    <td class="label"><span class="DetailTagText">邮编</span>：</td>
                    <td><input type="text" name="form.postcode" id="postcode" class="input02" style="width:150px;"/></td>
                  </tr>
                  <tr>
                    <td class="label"><span class="DetailTagText">委托电话</span>：</td>
                    <td><input type="text" name="form.entrustphone" id="entrustphone" class="input02" style="width:150px;"/></td>
                  </tr>
                  <tr>
                    <td class="label"><span class="DetailTagText">服务电话</span>：</td>
                    <td><input type="text" name="form.servicephone" id="servicephone" class="input02" style="width:150px;"/></td>
                  </tr>
                  <tr>
                    <td class="label"><span class="DetailTagText">邮箱地址</span>：</td>
                    <td><input type="text" name="form.email" id="email" class="input02" style="width:150px;"/></td>
                  </tr>
                  <tr>
                    <td class="label"><span class="DetailTagText">营业部照片</span>：</td>
                    <td><input type="text" name="form.photo" id="photo" class="input02" style="width:300px;" readonly  value="<c:out value='${form.photo}'/>"/>
                      <input type="button" onClick="openUploadFileDialog($('#photo'))" value="上传文件" class="bt02"/>
                      <input type="button"onClick="openDeleteFileDialog($('#photo'))" value="删除" class="bt01"/></td>
                  </tr>
                  <tr>
                    <td colspan="4" style="height:3px;"></td>
                  </tr>
                  <tr>
                    <td class="label"><span class="DetailTagText">交通信息</span>：</td>
                    <td ><input name="form.trainfo" type="hidden" id="trainfo" value="<c:out value='${form.trainfo}'/>" />
					  <iframe id="eWebEditor_trainfo" src="editor/standard/ewebeditor.htm?id=trainfo&style=coolblue" frameborder="0" scrolling="No" width="600" height="300"></iframe>
					  </td>
                  </tr>
                  <tr>
                    <td colspan="4" style="height:3px;"></td>
                  </tr>
                  <tr>
                    <td class="label"><span class="DetailTagText">营业部介绍</span>：</td>
                    <td ><input name="form.intro" type="hidden" id="intro" value="<c:out value='${form.intro}'/>" />
					  <iframe id="eWebEditor_trainfo" src="editor/standard/ewebeditor.htm?id=intro&style=coolblue" frameborder="0" scrolling="No" width="600" height="300"></iframe>
					  </td>
                  </tr>
                  <tr>
                    <td colspan="4" style="height:3px;"></td>
                  </tr>
                  <tr>
                    <td class="label"><span class="DetailTagText">营业部特色服务</span>：</td>
                    <td ><input name="form.character" type="hidden" id="character" value="<c:out value='${form.character}'/>" />
					  <iframe id="eWebEditor_trainfo" src="editor/standard/ewebeditor.htm?id=character&style=coolblue" frameborder="0" scrolling="No" width="600" height="300"></iframe>
					  </td>
                  </tr>
				  <tr>
                    <td class="label"><span class="DetailTagText">纬度</span>：</td>
                    <td><input type="text" name="form.lat" id="lat" class="input02" style="width:150px;"/></td>
                  </tr>
				  <tr>
                    <td class="label"><span class="DetailTagText">经度</span>：</td>
                    <td><input type="text" name="form.lnt" id="lnt" class="input02" style="width:150px;"/></td>
                  </tr>
				  <tr>
                    <td class="label"><span class="DetailTagText">放大倍数</span>：</td>
                    <td><input type="text" name="form.zoom" id="zoom" class="input02" style="width:150px;"/></td>
                  </tr>
				  <tr>
                    <td class="label"><span class="DetailTagText">细览时的放大倍数</span>：</td>
                    <td><input type="text" name="form.maxzoom" id="maxzoom" class="input02" style="width:150px;"/></td>
                  </tr>
                  <tr>
                    <td colspan="4" style="height:8px;"></td>
                  </tr>
                </table>
              </div></td>
          </tr>
        </table>
      </form></td>
  </tr>
</table>
<iframe id="hiddenFrame" name="hiddenFrame" width="0"  height="0" frameborder="0" marginwidth="0" marginheight="0" src="${ctxPath }/null.html"></iframe>
</body>
</html>