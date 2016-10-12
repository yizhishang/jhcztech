<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		$.formValidator.initConfig({formid:"addForm",alertmessage:false,onerror:function(msg){alert(msg)}});
		$("#name").formValidator().inputValidator({min:1,onerror:"字段名称不能为空,请确认"});
		$("#id").formValidator().inputValidator({min:1,onerror:"字段代码不能为空,请确认"});
		$("#alias").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"字段别名不能为空,请确认"});
		$("#max_num").formValidator().inputValidator({min:1,onerror:"最大字符数不能为空,请确认！"}).regexValidator({regexp:regexEnum.num,onerror:"最大字符数只能为数字,请确认"});
		
		$("#enterForm").click(function(){
			submitForm("addForm",null,false);
		})
		
		$("#input_type").change(function(){
			var value = $(this).val();
			switch(parseInt(value))
			{
				case 1:
					$("tr[@id='trDefaultValue']").show();//默认值
					$("tr[@id='trMaxNum']").show();//最大字符数
					$("tr[@id='textWH']").hide();//宽高
					$("tr[@id='extendContent']").hide();//列表选项
					
					$("#max_num").formValidator().inputValidator({min:1,onerror:"最大字符数不能为空,请确认！"}).regexValidator({regexp:regexEnum.num,onerror:"最大字符数只能为数字,请确认"});
					$("#width").val("265").formValidator({empty:true});
					$("#height").val("90").formValidator({empty:true});
					$("#extend_content").val("").formValidator({empty:true});
					
				break;
				case 2:
					$("tr[@id='trDefaultValue']").show();//默认值
					$("tr[@id='trMaxNum']").show();//最大字符数
					$("tr[@id='textWH']").show();//宽高
					$("tr[@id='extendContent']").hide();//列表选项
					
					$("#max_num").formValidator().inputValidator({min:1,onerror:"最大字符数不能为空,请确认！"}).regexValidator({regexp:regexEnum.num,onerror:"最大字符数只能为数字,请确认"});
					$("#width").formValidator().inputValidator({min:1,onerror:"宽度不能为空,请确认！"}).regexValidator({regexp:regexEnum.num,onerror:"宽度只能为数字,请确认"});
					$("#height").formValidator().inputValidator({min:1,onerror:"高度不能为空,请确认！"}).regexValidator({regexp:regexEnum.num,onerror:"高度只能为数字,请确认"});
					$("#extend_content").val("").formValidator({empty:true});
				break;
				case 3:
					$("tr[@id='trDefaultValue']").show();//默认值
					$("tr[@id='trMaxNum']").hide();//最大字符数
					$("tr[@id='textWH']").hide();//宽高
					$("tr[@id='extendContent']").show();//列表选项
					
					$("#max_num").val("100").formValidator({empty:true});
					$("#width").val("265").formValidator({empty:true});
					$("#height").val("90").formValidator({empty:true});
					$("#extend_content").formValidator().inputValidator({min:1,onerror:"列表先项不能为空,请确认！"})
				break;
				case 4:
					$("tr[@id='trDefaultValue']").show();//默认值
					$("tr[@id='trMaxNum']").hide();//最大字符数
					$("tr[@id='textWH']").hide();//宽高
					$("tr[@id='extendContent']").show();//列表选项
					
					$("#max_num").val("100").formValidator({empty:true});
					$("#width").val("265").formValidator({empty:true});
					$("#height").val("90").formValidator({empty:true});
					$("#extend_content").formValidator().inputValidator({min:1,onerror:"列表先项不能为空,请确认！"})
				break;
				case 5:
					$("tr[@id='trDefaultValue']").hide();//默认值
					$("tr[@id='trMaxNum']").hide();//最大字符数
					$("tr[@id='textWH']").hide();//宽高
					$("tr[@id='extendContent']").hide();//列表选项
					
					$("#max_num").val("100").formValidator({empty:true});
					$("#width").val("265").formValidator({empty:true});
					$("#height").val("90").formValidator({empty:true});
					$("#extend_content").val("").formValidator({empty:true});
				break;
				default:
				break;
			}
		});
		
		
		$("#close").click(function(){
			window.close();
		});
		
	});
</script>
<form action="addField.action" method="post" target="hiddenFrame" id="addForm">
  <input type="hidden" name="function" value="addField"/>
  <input type="hidden" name="form.catalog_id" value="${param.catalogId}"/>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
    <tr>
      <td><div class="title">
          <p><img src="${ctxPath }/admin/images/ico04.gif" />添加文档自定义字段</p>
        </div></td>
    </tr>
    <tr>
      <td class="edinner"><table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td><div class="space"></div>
              <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="editortable">
                <tr>
                  <td colspan="2" style="height:8px;"></td>
                </tr>
                <tr>
                  <td width="120" class="label"><span class="DetailTagText">表现形式</span>：</td>
                  <td><select name="form.input_type" id="input_type" style="width:118px;">
                      <option value="1">文本框</option>
                      <option value="2">多行文本框</option>
                      <option value="3">下拉框</option>
                      <option value="4">单选框</option>
					  <option value="5">文件上传</option>
                    </select>
                    <font color="#FF0000">*</font></td>
                </tr>
                <tr>
                  <td width="120" class="label">字段名称：</td>
                  <td><input type="text" name="form.name"  id="name" class="input02" style="width:116px;" value="<c:out value='${form.name}'/>"/> <font color="#FF0000">*</font></td>
                </tr>
                <tr>
                  <td width="120" class="label">字段代码：</td>
                  <td>
				  <select name="form.id" style="width:120px;">
				  	<c:forEach var="it" items="${form.fieldData}">
				  	<option value="${it}">EXT_FIELD${it}</option>
					</c:forEach>
				  </select>
				  <font color="#FF0000">*</font></td>
                </tr>
				<tr>
                  <td width="120" class="label">字段别名：</td>
                  <td><input type="text" name="form.alias"  id="alias" class="input02" style="width:116px;" value="<c:out value='${form.alias}'/>"/> <font color="#FF0000">*同一栏目下，别名不能相同</font></td>
                </tr>
                <tr>
                  <td width="120" class="label">是否必填：</td>
                  <td><input type="radio" name="form.ismandatory" value="1"/>是 <input type="radio" name="form.ismandatory" value="0" checked="checked"/>否</td>
                </tr>
                <tr id="trDefaultValue">
                  <td width="120" class="label">默认值：</td>
                  <td><input type="text" name="form.default_value"  id="default_value" class="input02" style="width:116px;" value="<c:out value='${form.default_value}'/>"/></td>
                </tr>
                <tr id="trMaxNum">
                  <td width="120" class="label">最大字符数：</td>
                  <td><input type="text" name="form.max_num"  id="max_num" class="input02" style="width:116px;" value="100"/> <font color="#FF0000">*</font></td>
                </tr>
				 <tr id="textWH" style="display:none">
                  <td width="120" class="label">宽度：</td>
                  <td><input type="text" name="form.width"  id="width" class="input02" style="width:116px;" value="265"/> <font color="#FF0000">*</font></td>
                </tr>
				 <tr id="textWH" style="display:none">
                  <td width="120" class="label">高度：</td>
                  <td><input type="text" name="form.height"  id="height" class="input02" style="width:116px;" value="90"/> <font color="#FF0000">*</font></td>
                </tr>
				<tr id="extendContent" style="display:none">
                  <td width="120" class="label">列表选项<BR/><font color="#FF0000">(每行为一个列表项)</font>：</td>
                  <td><textarea class="input02" name="form.extend_content" id="extend_content" style="width:240px;height:80px;"></textarea> <font color="#FF0000">*</font></td>
                </tr>
                <tr>
                  <td class="label">&nbsp;</td>
                  <td><input type="button" name="enterForm" id="enterForm" value="提交" class="bt04"/>
                    &nbsp;
                    <input type="button" id="close" name="close"value="关闭" class="bt04"/>
                  </td>
                </tr>
                <tr>
                  <td colspan="2" style="height:8px;"></td>
                </tr>
              </table></td>
          </tr>
        </table></td>
    </tr>
  </table>
</form>
<iframe id="hiddenFrame" name="hiddenFrame" width="0"  height="0" frameborder="0" marginwidth="0" marginheight="0" src="${ctxPath }/null.html"></iframe>
</body>
</html>