<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<link href="${ctxPath }/admin/styles/checktree.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="${ctxPath }/admin/scripts/jquery.new.checktree.js"></script>
<script language="javascript" src="/admin/scripts/syncReqJs.js"></script>
<script language="javascript" src="/admin/scripts/articleAuthor.js"></script>

<script type="text/javascript" src="/admin/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="/admin/ueditor/ueditor.all.js"></script>

<script type="text/javascript">

$(document).ready(function(){
	$.formValidator.initConfig({formid:"enterForm",alertmessage:false,onerror:function(msg){alert(msg)}});
	$("#title").formValidator().inputValidator({min:1,onerror:"文章标题不能为空,请确认"});

	
	UE.getEditor('brief',{
        initialFrameWidth : 900,
        initialFrameHeight: 200
    });
	
	
	UE.getEditor('content',{
        initialFrameWidth : 900,
        initialFrameHeight: 400
    });
	
		
	$("#type").change(function(){
		if($(this).val() == 0)
		{	
			$("#trLinkUrl").hide();
		}
		else if($(this).val() == 1)
		{
			$("#trLinkUrl").show();
			$("#trLinkUrl :button").hide();
		}
		else
		{
			$("#trLinkUrl").show();
			$("#trLinkUrl :button").show();
		}
	});
	
	//图片新闻选中
	$("#pictureNews").click(function(){
		if($(this).attr("checked"))
		{
			$("#isPicture").val("1");
			$("#trIsPicture").show();
		}
		else
		{
			$("#isPicture").val("0");
			$("#trIsPicture").hide();
		}
	});
	
	//推荐文章
	$("#hotNews").click(function(){
		if($(this).attr("checked"))
		{
			$("#isHot").val("1");
		}
		else
		{
			$("#isHot").val("0");
		}
	});
	
	//置顶文章
	$("#headNews").click(function(){
		if($(this).attr("checked"))
		{
			$("#isHead").val("1");
		}
		else
		{
			$("#isHead").val("0");
		}
	});

});
</script>
<body>
<form action="add.action" method="post" id="enterForm" target="hiddenFrame">
  <input type="hidden" name="function" id="function" value="add"/>
  <input type="hidden" name="form.catalogId" value="<c:out value='${form.catalogId}'/>">
  <input type="hidden" name="form.state" id="state" value="0">
  <input type="hidden" name="form.catalogNo" value="<c:out value='${form.catalogNo}'/>">
  <input type="hidden" name="form.isPicture" id="isPicture" value="0"/>
  <input type="hidden" name="form.isHot" id="isHot" value="0"/>
  <input type="hidden" name="form.isHead" id="isHead" value="0"/>
  <input type="hidden" name="isPreview" id="isPreview" value="0"/>
  <input type="hidden" name="is_submit" id="is_submit" value="1"/>
  <input type="hidden" name="successPage" value="article.action?function=edit&catalogId=<c:out value='${form.catalogId}'/>">
  <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
    <tr>
      <td><div class="title">
          <p><img src="/admin/images/ico04.gif" />添加文章</p>
        </div></td>
    </tr>
    <tr>
      <td class="edinner" style="width:100%"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td><jsp:include flush="true" page="/WEB-INF/views/article/include/menu.jsp"/></td>
          </tr>
          <tr>
            <td><jsp:include flush="true" page="/WEB-INF/views/article/include/navigation.jsp"/></td>
          </tr>
          <tr>
            <td><div class="space"></div>
              <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="editortable" id="tab0">
                <tr>
                  <td colspan="4" style="height:8px;"></td>
                </tr>
                <tr>
                  <td width="120" class="label">文章标题：</td>
                  <td><input type="text" name="form.title" id="title" class="input02" style="width:300px;"/>
                    <font color="#FF0000">*</font></td>
                </tr>
                <tr>
                  <td class="label">文章摘要：</td>
                  <td>
                  	<textarea name="form.brief" id="brief1" rows="10" style="width:900px;"></textarea>
                  </td>
                </tr>
                <tr>
                  <td class="label">文章类型：</td>
                  <td ><select name="form.type" id="type" style="width:153px;">
                      <option value="0">普通</option>
                      <option value="1">链接</option>
                      <option value="2">下载</option>
                    </select>
                  </td>
                </tr>
                <tr style="display:none" id="trLinkUrl">
                  <td class="label">文章链接：</td>
                  <td><input type="text" name="form.linkUrl" id="linkUrl" class="input02" style="width:300px;"  value=""/>
                    <input type="button" onClick="openUploadFileDialog($('#linkUrl'))" value="上传文件" class="bt02"/>
                    <input type="button"onClick="openDeleteFileDialog($('#linkUrl'))" value="删除" class="bt01"/></td>
                </tr>
                <tr>
                  <td class="label">文章内容：</td>
                  <td>
                  	<textarea name="form.content" id="content"></textarea>
                  	<!-- 
                  	<input name="form.content" type="hidden" id="content" value="" />
                    <IFRAME ID="eWebEditor1" SRC="/admin/editor/ewebeditor/ewebeditor.htm?id=content&style=standard650" FRAMEBORDER="0" SCROLLING="no" WIDTH="99%" HEIGHT="500"></IFRAME>
                  	 -->
                  </td>
                </tr>
                <tr>
                  <td colspan="4" style="height:8px;"></td>
                </tr>
              </table>
              <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="editortable" id="tab1" style="display:none">
                <tr>
                  <td colspan="4" style="height:8px;"></td>
                </tr>
                <tr>
                  <td width="120" class="label">文章作者：</td>
                  <td ><input type="text" name="form.author" id="author" class="input02" style="width:150px;"/></td>
                </tr>
                <tr>
                  <td class="label">文章关键字：</td>
                  <td><input type="text" name="form.relKeyword" id="relKeyword" class="input02" style="width:150px;"/>
                    <font color="#FF0000">(以空格隔开)</font></td>
                </tr>
                <tr>
                  <td  class="label">文章来源：</td>
                  <td><select name="form.source" style="width:153px;">
                      <option value="">无</option>
                      <c:forEach items="${form.sourceList }" var="item">
                        <option value="${item.name }">${item.name }</option>
                      </c:forEach>
                    </select>
                  </td>
                </tr>
                <c:if test="${not empty form.industryList}">
                  <tr>
                    <td class="label">行业类别：</td>
                    <td ><select name="form.industryType" style="width:153px;">
                        <option value="">无</option>
                        <c:forEach var="item" items="${form.industryList}">
                          <option value="${item.item_value}">${item.item_name}</option>
                        </c:forEach>
                      </select></td>
                  </tr>
                </c:if>
                <c:if test="${not empty form.column_level}">
                  <tr>
                    <td class="label">文章级别：</td>
                    <td ><select name="form.columnLevel" style="width:153px;">
                        <option value="">无</option>
                        <c:forEach var="item" items="${form.column_level}">
                          <option value="${item.item_value}">${item.item_name}</option>
                        </c:forEach>
                      </select></td>
                  </tr>
                </c:if>
                <!-- 
                <tr>
                  <td class="label">股票代码：</td>
                  <td><input type="text" name="form.gpdm" id="gpdm" class="input02" style="width:150px;"/></td>
                </tr>
                 -->
                <tr>
                  <td class="label">录入时间：</td>
                  <td><input type="text" name="form.createDate" id="create_date" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="input02" style="width:150px;" readonly="true"/></td>
                </tr>
                <tr>
                  <td class="label">分类属性：</td>
                  <td><input type="checkbox" value="1" id="pictureNews"/>图片新闻
                    <input type="checkbox" value="1" id="hotNews"/>推荐
                    <input type="checkbox" value="1" id="headNews"/>置顶
                  </td>
                </tr>
                <tr style="display:none" id="trIsPicture">
                  <td class="label">图片地址：</td>
                  <td>
                  
                  	<!-- 
                  	<input type="text" name="form.pictureUrl" id="pictureUrl" class="input02" style="width:300px;" readonly="true" value="<c:out value='${form.pictureUrl}'/>"/>
                    <input type="button" onClick="openUploadFileDialog($('#pictureUrl'))" value="上传图片" class="bt02"/>
                    <input type="button" onClick="openDeleteFileDialog($('#pictureUrl'))" value="删除" class="bt01"/>
                  	 -->
                    
                    <input type="text" name="form.pictureUrl" id="pictureUrl" style="width:300px;"  readonly="true"/>
                   	<textarea id="pictureUrl_ue" style="display:None;"></textarea> 
                   	<input type="button" class="bt02" id="pictureUrl_btn" value="上传图片" />
                   	
                   	<script type="text/javascript">
                   		$(function(){
                    		var pictureUrl_editor;
                  				pictureUrl_editor = UE.getEditor("pictureUrl_ue");
                  				pictureUrl_editor.ready(function() {
                  					pictureUrl_editor.hide();
                  					pictureUrl_editor.addListener('beforeInsertImage', function(t, arg) {
                  						$("#pictureUrl").attr("value", arg[0].src); // 将地址赋值给相应的input,只去第一张图片的路径
                  					});
                  				});
                  				$("#pictureUrl_btn").click(function(){
                  					var myImage = pictureUrl_editor.getDialog("insertimage");
                  					myImage.open();                    					
                  				});
                   		});
                   	</script>
                    
                    <input type="button" value="预览" class="bt01" onClick="window.open($('#pictureUrl').val());"/></td>
                </tr>
                <tr>
                  <td colspan="4" style="height:8px;"></td>
                </tr>
              </table>
              <div id="tab2" style="display:none"> </div>
              <div id="tab3" style="display:none"> </div></td>
          </tr>
        </table></td>
    </tr>
  </table>
</form>
<iframe id="hiddenFrame" name="hiddenFrame" width="0"  height="0" frameborder="0" marginwidth="0" marginheight="0" src="${ctxPath }/null.html"></iframe>
</body>
</html>