<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<script type="text/javascript">
	$(document).ready(function(){
		$.formValidator.initConfig({formid:"enterForm",alertmessage:false,onerror:function(msg){alert(msg)}});
		$("#catalogNo").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"目录英文名不能为空,请确认"});
		$("#name").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"目录名称不能为空,请确认"});
		$("#orderLine").formValidator({empty:true}).regexValidator({regexp:regexEnum.num,onerror:"目录排序值只能为数字,请确认"});
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
				<input type="hidden" name="successPage" value="functionCatalog.action?function=edit&catalogId=<c:out value='${form.id}'/>">
                <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td><div class="space"></div></td>
                  </tr>
                  <tr>
                    <td><div class="space"></div>
                      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="editortable">
                        <tr>
                          <td colspan="2"><h5>添加目录</h5></td>
                        </tr>
                        <tr>
                          <td colspan="2" style="height:8px;"></td>
                        </tr>
                        <tr>
                          <td width="120" class="label">目录英文名：</td>
                          <td><input type="text" name="form.catalogNo" id="catalogNo" class="input02" value="<c:out value='${form.catalogNo}'/>"/>
                            (同级目录不要重复) </td>
                        </tr>
                        <tr>
                          <td width="120" class="label">目录名称：</td>
                          <td><input type="text" name="form.name"  id="name" class="input02" value="<c:out value='${form.name}'/>"/></td>
                        </tr>
                        <tr>
                          <td width="120" class="label">目录描述：</td>
                          <td>
                          <textarea class="input02" name="form.description" id="description" style="width:300px;height:50px;"><c:out value="${form.description}" escapeXml="true"/></textarea>
                          </td>
                        </tr>
                        <tr>
                          <td width="120" class="label"><span class="DetailTagText">目录状态</span>：</td>
                          <td><input type="radio" size="30" name="form.state" class="DetailInputBox" value="0">
                            关闭
                            <input type="radio" size="30" name="form.state" class="DetailInputBox" checked value="1">
                            开放
							<script language="javascript">setRadioChecked("form.state",'<c:out value='${form.state}'/>')</script>
							</td>
                        </tr>
                        <tr>
                          <td width="120" class="label"><span class="DetailTagText">目录排序值</span>：</td>
                          <td><input type="text" name="form.orderLine" id="orderLine" class="input02" value="<c:out value='${form.orderLine}'/>"/></td>
                        </tr>
                        <tr>
                          <td width="120" class="label"><span class="DetailTagText">目录链接</span>：</td>
                          <td><input type="text" name="form.linkUrl"  id="linkUrl" class="input02" style="width:300px;"  value="<c:out value='${form.linkUrl}'/>"/></td>
                        </tr>
                        <tr>
                          <td width="120" class="label"><span class="DetailTagText">目录图片</span>：</td>
                          <td><input type="text" name="form.smallImage" id="smallImage" class="input02" style="width:300px;"  value="<c:out value='${form.smallImage}'/>"/>
                          	 </td>
                        </tr>
                        <tr>
                          <td class="label">&nbsp;</td>
                          <td><input type="submit" name="button" id="button" value="保存" class="bt04"/>
                            &nbsp;
                            <input type="reset" name="button" id="button" value="重置" class="bt04"/>
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