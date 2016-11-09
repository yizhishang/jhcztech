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
		$("#divContainer").css("height",$(window).height() - 90);
	}
	
	$(document).ready(function(){
		
		winResize();
		/*
		$.formValidator.initConfig({formid:"enterForm",alertmessage:false,onerror:function(msg){alert(msg)}});
		$("#name").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"广告名称不能为空,请确认"});
		$("#orderline").formValidator({empty:true}).regexValidator({regexp:regexEnum.num,onerror:"排序值格式不正确，请输入数字"});
		*/
		
		
		
	});
	function validateImgType()
	{
        var type = $('#picture').val();
        type = type.substring((type.length)-3,(type.length));
        if(type=="jpg"||type=="gif"||type=="png")
        {
           $('#img').show();
           $('#img').attr('src',$('#picture').val())
        }
        else
        {
            alert('请上传jpg或gif或png格式的图片文件');
            $('#picture').val(''); 
        }
    }
</script>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
    <td><jsp:include flush="true" page="/WEB-INF/views/adManage/include/adCatalogLink.jsp">
      <jsp:param name="catalogName" value="修改广告信息"/>
      </jsp:include>
    </td>
  </tr>
  <tr>
    <td class="edinner" style="width:100%">
    <form action="edit.action" method="post" id="enterForm" target="hiddenFrame">
        <input type="hidden" name="function" id="function" value="edit"/>
        <input type="hidden" name="is_submit" id="is_submit" value="1"/>
        <input type="hidden" name="form.ad_id" value="<c:out value='${form.ad_id}'/>"/>
        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td></td>
          </tr>
          <tr>
            <td><div class="space"></div>
              <div class="label">
              	<a href="#" onClick="saveData();"><img src="${ctxPath }/admin/images/icon018a16.gif" border="0"/>保存</a>
              	<a href="#" onClick="closeFunction();"><img src="${ctxPath }/admin/images/closeimg.gif" border="0"/>关闭</a>
              </div></td>
          </tr>
          <tr>
            <td><div class="space"></div>
              <div id="divContainer" style="overflow-y: auto;overflow-x:hidden;width:100%;">
                <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="editortable">
                  <tr>
                    <td colspan="4" style="height:8px;"></td>
                  </tr>
                  <tr>
                    <td width="120" class="label"><span class="DetailTagText">广告名称</span>：</td>
                    <td><input type="text" name="form.name" id="name" class="input02" value="${form.name}" style="width:300px;"/>
                      <font color="#FF0000">*</font></td>
                  </tr>
                  <tr>
                    <td class="label"><span class="DetailTagText">所属组</span>：</td>
                    <td ><select id="type" name="form.type">
                        <c:forEach var="item" items="${list}">
                          <option value="<c:out value='${item.id}'/>">
                          <c:out value='${item.name}'/>
                          </option>
                        </c:forEach>
                      </select>
                      <script langauge="javascript">setSelectSelected("form.type","<c:out value='${form.type}'/>")</script></td>
                  </tr>
                  <tr>
                    <td class="label">链接地址：</td>
                    <td><input type="text" name="form.url" id="url" class="input02" value="${form.url}" style="width:300px;"/></td>
                  </tr>
                  <tr>
                    <td class="label">起始时间：</td>
                    <td><input type="text" name="form.start_time" id="start_time" class="input02" value="${form.start_time}" readonly="true" onFocus="WdatePicker({el:'start_time'})" style="width:150px;"/></td>
                  </tr>
                  <tr>
                    <td class="label"><span class="DetailTagText">结束时间</span>：</td>
                    <td ><input type="text" name="form.end_time" id="end_time" class="input02" value="${form.end_time}" readonly="true" onFocus="WdatePicker({el:'end_time'})" style="width:150px;"/></td>
                  </tr>
                  <tr>
                    <td class="label">图片地址：</td>
                    <td>
						<script type="text/javascript" src="${ctxPath }/admin/ueditor/ueditor.config.js"></script>
						<script type="text/javascript" src="${ctxPath }/admin/ueditor/ueditor.all.js"></script>
						<script type="text/javascript" src="${ctxPath }/admin/ueditor/uploadImage.js"></script>
                    	
                    	<input type="text" name="form.picture" value="${form.picture }" id="picture" style="width:300px;" />
                    	<textarea id="upload_ue" style="display:None;"></textarea> 
                    	<input type="button" class="bt02" onclick="upImage();" value="上传图片" />
                    	
                    </td>
                  </tr>
                  <!-- 
                  <tr>
                    <td class="label"><span class="DetailTagText">上传文件</span>：</td>
                    <td><input type="text" name="form.picture" id="picture" class="input02" style="width:300px;" value="${form.picture}" readonly/>
                      <input type="button" onClick="openUploadFileDialog($('#picture'));validateImgType()" value="上传文件" class="bt02"/>
                      <input type="button"onClick="openDeleteFileDialog($('#picture'));" value="删除" class="bt01"/></td>
                  </tr>
                  <tr> 
                   <td></td>
                   <td>图片文件类型:jpg,png,gif</td>
                  </tr>
                   -->
                  <tr>
                    <td class="label"><span class="DetailTagText">广告图片</span>：</td>
                    <td>
                      <c:choose>
                         <c:when test="${not empty form.picture }">
                            <img width="623px" height="226px" id="preview" src="${form.picture}">
                         </c:when>
                         <c:otherwise>
                            <img width="623px" height="226px" id="preview" style="display: none;">
                         </c:otherwise>
                      </c:choose>
                    </td>
                  </tr>
                  <tr>
                    <td class="label"><span class="DetailTagText">广告宽度</span>：</td>
                    <td><input type="text" name="form.width" id="width" class="input02" value="${form.width}" style="width:150px;" />
                      <font color="#ff0000">像素</font></td>
                  </tr>
                  <tr>
                    <td class="label">广告高度：</td>
                    <td><input type="text" name="form.height" id="height" class="input02" value="${form.height}" style="width:150px;"/>
                      <font color="#ff0000">像素</font></td>
                  </tr>
                  <tr>
                    <td class="label">排序值：</td>
                    <td><input type="text" name="form.orderline" id="orderline" class="input02" value="${form.orderline}" style="width:150px;"/></td>
                  </tr>
                  <tr>
                    <td class="label">链接状态：</td>
                    <td><input type="radio" name="form.state" value="0">
                      无效
                      <input type="radio" name="form.state" value="1" checked="checked">
                      有效
                      <script language="javascript">setRadioChecked("form.state", "<c:out value='${form.state}'/>")</script>
                    </td>
                  </tr>
                  <tr>
                    <td class="label"><span class="DetailTagText">展示状态</span>：</td>
                    <td>
                   
                      <input type="radio" name="form.file_state" value="0" checked="checked">
                          无效
                      <input type="radio" name="form.file_state" value="1">
                          有效
                      <script language="javascript">setRadioChecked("form.file_state", "<c:out value='${form.file_state}'/>")</script>
                    </td>
                  </tr>
                  <tr>
                    <td class="label"><span class="DetailTagText">弹出广告编辑</span>：</td>
                    <td><input type="hidden" id="description" name="form.description" value="<c:out value='${form.description}'/>">
                        <iframe id="eWebEditor_description" src="${ctxPath }/admin/editor/ewebeditor/ewebeditor.htm?id=description&style=mini" frameborder="0" scrolling="No" width="600" height="230"></iframe>
                    </td>
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
</div>
<iframe id="hiddenFrame" name="hiddenFrame" width="0"  height="0" frameborder="0" marginwidth="0" marginheight="0" src="${ctxPath }/null.html"></iframe>
</body>
</html>