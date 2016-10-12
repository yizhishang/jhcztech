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
		$("#name").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"真实姓名不能为空,请确认"});
		$("#jobtime").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"从业时间不能为空,请确认"}).regexValidator({regexp:regexEnum.num,onerror:"从业时间格式不正确，请输入数字"});
	});
</script>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
    <td>
        <div class="title">
            <p><img src="${ctxPath }/admin/images/ico04.gif" />编辑专家信息</p>
        </div>
    </td>
  </tr>
  <tr>
    <td class="edinner" style="width:100%"><form action="expertManage.action" method="post" id="enterForm" target="hiddenFrame">
        <input type="hidden" name="function" id="function" value="edit"/>
		<input type="hidden" name="form.expid" value="<c:out value='${form.expid}'/>">
        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td></td>
          </tr>
          <tr>
            <td><div class="space"></div>
              <div class="label"> <a href="#" onClick="addFunction();"><img src="${ctxPath }/admin/images/icon018a16.gif" border="0"/>保存</a> <a href="#" onClick="closeFunction();"><img src="${ctxPath }/admin/images/closeimg.gif" border="0"/>关闭</a> </div></td>
          </tr>
          <tr>
            <td><div class="space"></div>
              <div id="divContainer" style="overflow-y: auto;overflow-x:hidden;width:100%;">
                <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="editortable">
                  <tr>
                    <td colspan="4" style="height:8px;"></td>
                  </tr>
                  <tr>
                    <td class="label"><span class="DetailTagText">真实姓名</span>：</td>
                    <td >
                      <input type="text" name="form.name" id="name" value="${form.name}" class="input02" style="width:150px;"/> <font color="#ff0000">*</font></td>
                  </tr>
                  <tr>
                    <td class="label"><span class="DetailTagText">学历</span>：</td>
                    <td><input type="text" name="form.school" id="school" value="${form.school}" class="input02" style="width:150px;"/></td>
                  </tr>
                  <tr>
                    <td  class="label"><span class="DetailTagText">所属部门名称</span>：</td>
                    <td><input type="text" name="form.deptname" value="${form.deptname}" id="deptname" class="input02" style="width:150px;"/></td>
                  </tr>
                  <tr>
                    <td class="label"><span class="DetailTagText">专业职称</span>：</td>
                    <td ><input type="text" name="form.title" value="${form.title}" id="title" class="input02" style="width:150px;"/></td>
                  </tr>
                  <tr>
                    <td class="label"><span class="DetailTagText">从业资格</span>：</td>
                    <td ><input type="text" name="form.certificate" value="${form.certificate}" id="certificate" class="input02" style="width:150px;"/></td>
                  </tr>
                  <tr>
                    <td class="label"><span class="DetailTagText">从业时间</span>：</td>
                    <td ><input type="text" name="form.jobtime" value="${form.jobtime}" id="jobtime" class="input02" style="width:150px;"/>
                      年 <font color="#ff0000">*</font></td>
                  </tr>
                  <tr>
                    <td class="label"><span class="DetailTagText">电子邮件</span>：</td>
                    <td ><input type="text" name="form.email" value="${form.email}" id="email" class="input02" style="width:150px;"/></td>
                  </tr>
                  <tr>
                    <td class="label"><span class="DetailTagText">联系电话</span>：</td>
                    <td ><input type="text" name="form.telephone" value="${form.telephone}" id="telephone" class="input02" style="width:150px;"/></td>
                  </tr>
				  <tr>
                    <td class="label"><span class="DetailTagText">专家照片</span>：</td>
                    <td><input type="text" name="form.photo" id="photo" class="input02" style="width:300px;" readonly  value="<c:out value='${form.photo}'/>"/>
                      <input type="button" onClick="openUploadFileDialog($('#photo'))" value="上传文件" class="bt02"/>
                      <input type="button"onClick="openDeleteFileDialog($('#photo'))" value="删除" class="bt01"/></td>
                  </tr>
                  <tr>
                    <td class="label"><span class="DetailTagText">服务内容</span>：</td>
                    <td ><textarea name="form.service" id="service" class="input02" style="width:450px;height:70px;"><c:out value='${form.service}'/></textarea></td>
                  </tr>
                  
                  <tr>
                    <td class="label"><span class="DetailTagText">专家介绍</span>：</td>
                    <td ><textarea name="form.intro" id="intro" class="input02" style="width:450px;height:70px;"><c:out value='${form.intro}'/></textarea></td>
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