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
              <form action="deptManage.action" method="post" id="enterForm">
                <input type="hidden" name="function" value="edit"/>
                <input type="hidden" name="form.dept_id" value="<c:out value='${param.deptId}'/>">
                <input type="hidden" name="successPage" value="deptManage.action?function=edit&deptId=<c:out value='${param.deptId}'/>">
                <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td><div class="space"></div></td>
                  </tr>
                  <tr>
                    <td><div class="space"></div>
                      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="editortable">
                        <tr>
                          <td colspan="2"><h5>修改部门信息</h5></td>
                        </tr>
                        <tr>
                          <td colspan="2" style="height:8px;"></td>
                        </tr>
                        <tr>
                          <td width="140" class="label">部门简称：</td>
                          <td><input type="text" name="form.name" id="name" class="input02" value="<c:out value='${form.name}'/>"/>
                            (同级栏目不要重复) </td>
                        </tr>
                        <tr>
                          <td class="label">部门全称：</td>
                          <td><input type="text" name="form.full_name"  id="full_name" class="input02" value="<c:out value='${form.full_name}'/>"/></td>
                        </tr>
						<tr>
                          <td class="label">排序值：</td>
                          <td><input type="text" name="form.orderline"  id="orderline" class="input02" value="<c:out value='${form.orderline}'/>"/></td>
                        </tr>
                        <tr>
                          <td class="label">&nbsp;</td>
                          <td><input type="submit" name="button" id="button" value="保存" class="bt04"/>
                            &nbsp;
                            <input type="reset" name="button" id="button" value="重置" class="bt04"/>                          </td>
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