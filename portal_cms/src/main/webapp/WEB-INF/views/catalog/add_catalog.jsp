<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<script type="text/javascript">
	function getCatalogTreeFunction()
	{
		var sCatalogs = openDialog("catalog.action?function=showCatalogTree", 500, 800);
		if(sCatalogs)
		{
			if(sCatalogs.indexOf(":") != -1)
			{
				var values = sCatalogs.split(":");
				$("#sourceId").val(values[0] + "(" + values[1] + ")");
				$("#sourceId2").val(values[1]);
			}
		}
	}
	$(document).ready(function(){
		$.formValidator.initConfig({formid:"enterForm",alertmessage:false,onerror:function(msg){alert(msg)}});
		$("#catalogNo").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"栏目英文名不能为空,请确认"});
		$("#name").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"栏目名称不能为空,请确认"});
		$("#orderLine").formValidator({empty:true}).regexValidator({regexp:regexEnum.num,onerror:"栏目排序值只能为数字,请确认"});
		
		$("select[name='form.type']").change(function(){
			$("#publishPath").val("");
			if($(this).val() == '2')
			{
				$("#publishMsg0").html("栏目发布方法");
				$("#publishMsg1").html("<font color='red'>当栏目类型为特殊栏目时，这里需填写处理该栏目的实现类名</font>");
				
				$("#publishPath").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"您选择了该栏目为特殊栏目，请填写发布该栏目所需要的实现类。"});
			}
			else
			{
				$("#publishMsg0").html("发布路径");
				$("#publishMsg1").html("相对于根的路径，例:/main/catalog/");
				$("#publishPath").formValidator({empty:true});
			}
		});
		
		$("#attribute").change(function(){
			if($(this).val() == 0)
			{
				$("#sourceTr").hide();
				$("#sourceId").formValidator({empty:true});
			}
			else
			{
				$("#sourceTr").show();
				$("#sourceId").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"源栏目ID不能为空,请确认"});
			}
		});
	});
</script>
<body>
<div class="c_ie6_out">
  <div class="c_ie6_in">
    <div class="Wrapper">
      <div class="inner">
        <div class="innercontent">
          <div class="cl"></div>
          <div class="contentbox">
            <div class="content">
              <form action="add.action" method="post" id="enterForm">
                <input type="hidden" name="function" value="add"/>
                <input type="hidden" name="form.parentId" value="<c:out value='${form.parentId}'/>">
                <input type="hidden" name="successPage" value="catalog.action?function=edit&catalogId=<c:out value='${form.id}'/>">
                <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td><div class="space"></div></td>
                  </tr>
                  <tr>
                    <td><div class="space"></div>
                      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="editortable">
                        <tr>
                          <td colspan="2"><h5>添加栏目</h5></td>
                        </tr>
                        <tr>
                          <td colspan="2" style="height:8px;"></td>
                        </tr>
                        <tr>
                          <td width="140" class="label">栏目英文名：</td>
                          <td><input type="text" name="form.catalogNo" id="catalogNo" class="input02" value="<c:out value='${form.catalogNo}'/>"/>
                            (同级栏目不要重复) </td>
                        </tr>
                        <tr>
                          <td class="label">栏目名称：</td>
                          <td><input type="text" name="form.name"  id="name" class="input02" value="<c:out value='${form.name}'/>"/></td>
                        </tr>
                        <tr>
                          <td class="label">栏目描述：</td>
                          <td><input type="hidden" id="description" name="form.description" value="<c:out value='${form.description}'/>">
                            <iframe id="eWebEditor_description" src="/admin/editor/ewebeditor/ewebeditor.htm?id=description&style=mini" frameborder="0" scrolling="No" width="600" height="230"></iframe></td>
                        </tr>
                        <tr>
                          <td class="label"><span class="DetailTagText">栏目状态</span>：</td>
                          <td><input type="radio" size="30" name="form.state" class="DetailInputBox" value="0">
                            关闭
                            <input type="radio" size="30" name="form.state" class="DetailInputBox" checked value="1">
                            开放
                            <script language="javascript">setRadioChecked("form.state",'<c:out value='${form.state}'/>')</script>
                          </td>
                        </tr>
                        <tr>
                          <td class="label"><span class="DetailTagText">栏目排序值</span>：</td>
                          <td><input type="text" name="form.orderLine" id="orderLine" class="input02" value="<c:out value='${form.orderLine}'/>"/></td>
                        </tr>
                        <tr>
                          <td class="label"><span class="DetailTagText">栏目类型</span>：</td>
                          <td><select name="form.type" style="width:155px;">
                              <option value="0">普通栏目</option>
                              <option value="1">推荐栏目</option>
                              <option value="2">特殊栏目</option>
                            </select>
                            <script language="javascript">setSelectSelected("form.type",'<c:out value='${form.type}'/>')</script>
                          </td>
                        </tr>
                        <tr>
                          <td class="label"><span class="DetailTagText">页面类型</span>：</td>
                          <td><select name="form.pageType" style="width:155px;">
                              <option value="0">信息列表</option>
                              <option value="1">页面</option>
                            </select>
                            <script language="javascript">setSelectSelected("form.pageType",'<c:out value='${form.pageType}'/>')</script>
                          </td>
                        </tr>
                        <c:if test="${not empty form.column_level}">
                          <tr>
                            <td class="label"><span class="DetailTagText">栏目星级</span>：</td>
                            <td><select name="form.columnLevel" style="width:155px;">
							    <option value="">请选择</option>
                                <c:forEach var="item" items="${form.column_level}">
                                  <option value="<c:out value='${item.item_value}'/>">
                                  <c:out value="${item.item_name}"/>
                                  </option>
                                </c:forEach>
                              </select>
                              <script language="javascript">setSelectSelected("form.columnLevel",'<c:out value='${form.columnLevel}'/>')</script>
                            </td>
                          </tr>
                        </c:if>
                        <tr>
                          <td class="label"><span class="DetailTagText">文章发布方式</span>：</td>
                          <td><select name="form.needPublish" style="width:155px;">
                              <option value="0">手动发布</option>
                              <option value="3">自动发布</option>
                            </select>
                            <script language="javascript">setSelectSelected("form.needPublish",'<c:out value='${form.needPublish}'/>')</script>
                          </td>
                        </tr>
                        <tr>
                          <td class="label"><span class="DetailTagText">继承模板方式</span>：</td>
                          <td><select name="form.inheritMode" style="width:155px;">
                              <option value="0">全部不继承</option>
                              <option value="1">只继承信息细览模板</option>
                              <option value="2">只继承信息列表模板</option>
                              <option value="3" selected>全部继承</option>
                            </select>
                          </td>
                        </tr>
                        <tr>
                          <td class="label"><span class="DetailTagText">发布文件类型</span>：</td>
                          <td><select name="form.fileType" style="width:155px;">
                              <option value="shtml">shtml类型</option>
                              <option value="html">html类型</option>
                              <option value="jsp">jsp类型</option>
                            </select>
                          </td>
                        </tr>
                        <tr>
                          <td class="label"><span class="DetailTagText">继承文档自定义字段</span>：</td>
                          <td><select name="form.inheritField" style="width:155px;">
                              <option value="0">不继承</option>
                              <option value="1">继承</option>
                            </select>
                            <font color="#FF0000">（选择继承时，如果当前栏目无自定义字段，则递归查询父栏目的自定义字段）</font>
                            <script language="javascript">setSelectSelected("form.inheritField",'<c:out value='${form.inheritField}'/>')</script>
                          </td>
                        </tr>
						<tr>
                          <td class="label"><span class="DetailTagText">栏目属性</span>：</td>
                          <td><select name="form.attribute" id="attribute" style="width:155px;">
                              <option value="0" selected="selected">实体类型</option>
                              <option value="1">虚拟栏目只读</option>
							  <option value="2">虚拟栏目读写</option>
                            </select>
                            <script language="javascript">setSelectSelected("form.attribute",'<c:out value='${form.attribute}'/>')</script>
                          </td>
                        </tr>
						<tr style="display:none" id="sourceTr">
                          <td class="label"><span class="DetailTagText">源栏目ID</span>：</td>
                          <td><input type="text" id="sourceId" class="input02" readonly="true"/> <input type="button"onClick="getCatalogTreeFunction();" value="选择" class="bt01"/>
						  <input type="hidden" name="form.sourceId" id="sourceId2" value=""/>
						  </td>
                        </tr>
                        <tr>
                          <td class="label"><span class="DetailTagText" id="publishMsg0">发布路径</span>：</td>
                          <td><input type="text" name="form.publishPath" id="publishPath" class="input02" style="width:300px;" value="<c:out value='${form.publishPath}'/>"/>
                            （<span  id="publishMsg1">相对于根的路径，例:/main/catalog/</span>） </td>
                        </tr>
                        <tr>
                          <td class="label"><span class="DetailTagText">栏目链接</span>：</td>
                          <td><input type="text" name="form.linkUrl" id="linkUrl" class="input02" style="width:300px;" value="<c:out value='${form.linkUrl}'/>"/></td>
                        </tr>
                        <tr>
                          <td class="label"><span class="DetailTagText">栏目图片(小)</span>：</td>
                          <td>
                          
                            <script type="text/javascript" src="/admin/ueditor/ueditor.config.js"></script>
							<script type="text/javascript" src="/admin/ueditor/ueditor.all.js"></script>
	                    	
	                    	<input type="text" name="form.smallImage" id="smallImage" style="width:300px;" />
	                    	<textarea id="smallImage_ue" style="display:None;"></textarea> 
	                    	<input type="button" class="bt02" id="smallImage_btn" value="上传图片" />
	                    	
	                    	<script type="text/javascript">
	                    		$(function(){
		                    		var smallImage_editor;
                    				smallImage_editor = UE.getEditor("smallImage_ue");
                    				smallImage_editor.ready(function() {
                    					smallImage_editor.hide();
                    					smallImage_editor.addListener('beforeInsertImage', function(t, arg) {
                    						$("#smallImage").attr("value", arg[0].src); // 将地址赋值给相应的input,只去第一张图片的路径
                    					});
                    				});
                    				$("#smallImage_btn").click(function(){
                    					var myImage = smallImage_editor.getDialog("insertimage");
                    					myImage.open();                    					
                    				});
	                    		});
	                    	</script>
	                    	
                        </tr>
                        <tr>
                          <td class="label"><span class="DetailTagText">栏目图片(大)</span>：</td>
                          <td>
                                                      
	                    	<input type="text" name="form.largeImage" id="largeImage" style="width:300px;" />
	                    	<textarea id="largeImage_ue" style="display:None;"></textarea> 
	                    	<input type="button" class="bt02" id="largeImage_btn" value="上传图片" />
	                    	
	                    	<script type="text/javascript">
	                    		$(function(){
		                    		var largeImage_editor;
                    				largeImage_editor = UE.getEditor("largeImage_ue");
                    				largeImage_editor.ready(function() {
                    					largeImage_editor.hide();
                    					largeImage_editor.addListener('beforeInsertImage', function(t, arg) {
                    						$("#largeImage").attr("value", arg[0].src); // 将地址赋值给相应的input,只去第一张图片的路径
                    					});
                    				});
                    				$("#largeImage_btn").click(function(){
                    					var myImage = largeImage_editor.getDialog("insertimage");
                    					myImage.open();                    					
                    				});
	                    		});
	                    	</script>
                           </td>
                        </tr>
                        <tr>
                          <td class="label">&nbsp;</td>
                          <td><input type="submit" name="button" id="button" value="保存" class="bt04"/>
                            &nbsp;
                            <input type="reset" name="button" value="重置" class="bt04"/>
                          </td>
                        </tr>
                      </table></td>
                  </tr>
                </table>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</div>
</body>
</html>