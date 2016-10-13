<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/taglibs.jsp" %>
<%@ taglib uri="/WEB-INF/tlds/jhcz.tld" prefix="jhcz" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>jhczCMS后台管理系统</title>
    <%@ include file="/admin/common/meta.jsp" %>
    <link href="<%=request.getContextPath()%>/admin/styles/cms.css" rel="stylesheet" type="text/css" />
    <script language="javascript" src="<%=request.getContextPath()%>/admin/scripts/ajax.js"></script>
    <script language="javascript" src="<%=request.getContextPath()%>/admin/scripts/form_commons.js"></script>
    <script language="javascript" src="<%=request.getContextPath()%>/admin/scripts/jquery.min.js"></script>
	<script language="javascript" src="<%=request.getContextPath()%>/admin/scripts/datepicker/WdatePicker.js"></script>
	<script language="javascript" src="<%=request.getContextPath()%>/admin/scripts/jqueryUI/jquery-ui-1.9.2.custom.min.js"></script>
</head>
<%
	//这是主要包装了pageUrl
	String queryStr= request.getQueryString() ;
	StringBuffer pageUrl=new StringBuffer();
 	if(queryStr!=null&&queryStr.length()>0){
 		String[] queryArr=queryStr.split("&");
 		for(int i=0;i<queryArr.length;i++){
 			if(queryArr[i].startsWith("function=")||queryArr[i].startsWith("pageUrl=")||queryArr[i].startsWith("form.pageUrl=")){
 				continue;
 			}
 			pageUrl.append(queryArr[i]);
 			if(i!=queryArr.length-1){
 				pageUrl.append("&");
 			}
 		}
 		request.setAttribute("pageUrl",pageUrl);
 	}
%>

<body>
<style>
input .list_input{border-left:0px;border-right:0px;border-top:0px;};
</style>
<style>
	.sortul{background-color:#ffffff;height:480px;overflow-y:scroll;border:1px solid #000000;}
	.sortlist{background-color:#ffffff;cursor:pointer;}
	.sortlist span{display:inline-block;cursor:normal;background-color:#ffffff;width:100px;text-align:center;height:35px;padding-bottom:1px;}
	.titleul{background-color:#ffffff;}
	.titlelist{background-color:#ffffff;}
	.titlelist span{display:inline-block;cursor:normal;background-color:#ffffff;font-weight:bolder;text-align:center;width:105px;}
	.infodiv{float:left;margin-left:15px;margin-top:24px;border:1px solid #000000;}
    .infodiv .title{margin-left:10px;margin-top:5px;}
    .infodiv .content{float:left;margin:15px;}
    .infodiv .content div{width:110px;}
    .clearfix:before, .clearfix:after {content:"";display:table;}
	.clearfix:after {clear:both;}
	.clearfix {zoom:1;}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		$("#sortable").sortable();
		$('#sortable').bind('sortupdate', function(event, ui) { resortorder(); }); // 拖拽后回调
		//$( "#sortable" ).disableSelection();
		$("#infodiv").hide();
		$("#infodiv > *").change(function(){
			$("#savetemp").attr("disabled",false);
			$("#resettemp").attr("disabled",false);
		});
	});
	
	function doSelectConfig(){
		var tempid = $("#temp_id").val();
		window.open("/admin/TableColumnAdmin/configSelect.action?temp_id="+tempid);
	}
	
	function resortorder(){
		$("#sortable input[id$='_orderno']").each(function(i){
			$(this).val(i);
		});
	}
	
	function saveFunction(){
		var functionname = $("#functionname").val();
		//$("#functionname").val("edit");
		$("#qryparm").submit();
		$("#functionname").val("");
	}
	
	function showEditor(id){
		$("#infodiv *[id^='temp_']").each(function(){
			var tempid = $(this).attr("id");
			var tid = id+"_"+tempid.substr("temp_".length);
			$(this).val($("#"+tid).val());
		});
		$("#infodiv").show();
		$("#savetemp").attr("disabled",true);
		$("#resettemp").attr("disabled",true);
	}
	function resetTemp(){
		var id = $("#temp_id").val();
		$("#infodiv *[id^='temp_']").each(function(){
			var tempid = $(this).attr("id");
			var tid = id+"_"+tempid.substr("temp_".length);
			$(this).val($("#"+tid).val());
		});
		$("#savetemp").attr("disabled",true);
		$("#resettemp").attr("disabled",true);
	}
	function saveTempChange(){
		var id = $("#temp_id").val();
		$("#infodiv *[id^='temp_']").each(function(){
			var tempid = $(this).attr("id");
			if(tempid!="temp_id" && tempid!="temp_name_en"){
				var tid = id+"_"+tempid.substr("temp_".length);
				$("#"+tid).val($(this).val());
			}
		});
		$("#savetemp").attr("disabled",true);
		$("#resettemp").attr("disabled",true);
		//$("#infodiv").hide();
	}
</script>
<form id="qryparm" name="qryparm" method="post" action="edit.action" >
<input type="hidden" name="form.pageUrl" value="${pageUrl}"></input>
<input type="hidden" id="functionname" name="function" value="edit"></input>
<input type="hidden" name="table_id" value="${param.table_id }"></input>
<input type="hidden" name="manageCatalogId" value="${param.manageCatalogId }"></input>
<input type="hidden" name="subManageCatalogId" value="${param.subManageCatalogId }"></input>
<div class="c_ie6_out">
  <div class="c_ie6_in">
    <div class="Wrapper">
      <div class="inner">
        <div class="innercontent">
          <div class="cl"></div>
          <div class="contentbox">
            <div class="content">
              <!-- 包含导航代码  -->
              <ul class="menu"><li class="cur"><h2>${data.tableBean.name_ch }【${data.tableBean.name_en }】</h2></li></ul>

              <div class="label">
                <a href="javascript:void(0)" onClick="window.location.reload();"><img src="${ctxPath }/admin/images/ico08.gif" border="0"/>更新</a> 
                <a href="javascript:void(0)" onClick="submitForm('qryparm',null, false);"><img src="${ctxPath }/admin/images/ico08.gif" border="0"/>保存</a>
                <a href="javascript:void(0)" onClick="window.close();"><img src="${ctxPath }/admin/images/closeimg.gif" border="0"/>关闭</a>
              </div>
              <div class="search">
                <div class="space"></div>
                <div>
                  <div style="float:left;">
                  <span>英文名称：<input type="text" alt="notempty" name="table_name_en" value="${param.table_name_en}" class="input01"/></span> 
                  <span>中文名称：<input type="text" alt="notempty" name="table_name_ch" value="${param.table_name_ch}" class="input01"/></span> 
                  <span><input type="submit" name="button" id="button" value="查询"  class="bt01" /></span> 
                </div>
               </div>
               
              </div>
              <div class="space"></div>
              <div style="width:544px;float:left;">
              	<ul class="titleul">
					<li class="titlelist"><span>英文名称</span><span>中文名称</span><span>默认值</span><span>可为空</span><span>列表展示</span></li>
				</ul>
              	<ul id="sortable" class="sortul">
              		<c:forEach var="item" items="${data.list}" varStatus="status">
		              <li class="sortlist">
		              	<input type='hidden' value='${item.table_name_ch}' id='${item.id }_table_name_ch' name='form.table_name_ch'/>
						<input type='hidden' value='${item.table_id}' id='${item.id }_table_id' name='form.table_id'/>
						<input type='hidden' value='${item.name_en}' id='${item.id }_name_en' name='form.name_en'/>
						<input type='hidden' value='${item.input_type}' id='${item.id }_input_type' name='form.input_type'/>
						<input type='hidden' value='${item.is_single}' id='${item.id }_is_single' name='form.is_single'/>
						<input type='hidden' value='${item.is_special_format}' id='${item.id }_is_special_format' name='form.is_special_format'/>
						<input type='hidden' value='${item.special_format}' id='${item.id }_special_format' name='form.special_format'/>
						<input type='hidden' value='${item.show_in_info}' id='${item.id }_show_in_info' name='form.show_in_info'/>
						<input type='hidden' value='${item.is_readonly}' id='${item.id }_is_readonly' name='form.is_readonly'/>
						<input type='hidden' value='${item.be_export}' id='${item.id }_be_export' name='form.be_export'/>
						<input type='hidden' value='${item.show_in_search}' id='${item.id }_show_in_search' name='form.show_in_search'/>
						<input type='hidden' value='${item.orderno}' id='${item.id }_orderno' name='form.orderno'/>
						<input type='hidden' value='${item.id}' id='${item.id }_id' name='form.id'/>
						<input type='hidden' value='${item.table_name_en}' id='${item.id }_table_name_en' name='form.table_name_en'/>
						<input type='hidden' value='${item.select_value}' id='${item.id }_select_value' name='form.select_value'/>
						<input type='hidden' value='${item.select_text}' id='${item.id }_select_text' name='form.select_text'/>
						<input type='hidden' value='${item.select_table}' id='${item.id }_select_table' name='form.select_table'/>
						<input type='hidden' value="${item.select_condition}" id='${item.id }_select_condition' name='form.select_condition'/>
						<input type='hidden' value='${item.is_sys}' id='${item.id }_is_sys' name='form.is_sys'/>
						<input type='hidden' value='${item.sys_type}' id='${item.id }_sys_type' name='form.sys_type'/>
						<input type='hidden' value='${item.align_in_list}' id='${item.id }_align_in_list' name='form.align_in_list'/>
						<input type='hidden' value='${item.select_text_column}' id='${item.id }_select_text_column' name='form.select_text_column'/>
		              	<span onclick="showEditor('${item.id}');">${item.name_en}</span>
		              	<span><input id="${item.id }_name_ch" name="form.name_ch" value="<c:out value="${item.name_ch}" />" style="width:90px;"/></span>
		              	<span><input id="${item.id }_default_value" name="form.default_value" value="<c:out value="${item.default_value}" />" style="width:90px;"/></span>
		              	<span>
		              		<select id="${item.id}_be_null" name="form.be_null" style="width:90px;">
	                    		<option value="Y">可为空</option>
	                    		<option value="N">不可为空</option>
	                    	</select>
		              	</span>
		              	<span>
		              		<select id="${item.id}_show_in_list" name="form.show_in_list" style="width:90px;">
		              			<option value="Y">是</option>
	                    		<option value="N">否</option>
	                    	</select>
		              	</span>
		              </li>
		              <script>
		              	$("#${item.id}_show_in_list").val("${item.show_in_list}");
		              	$("#${item.id}_be_null").val("${item.be_null}");
		              </script>
       			 	</c:forEach>
				</ul>
              </div>
              <!-- 编辑区开始 -->
              <div id="infodiv" class="infodiv" style="display:hidden;">
              	<div class="title">字段详细信息：
              	<input type="hidden" id="temp_id" value=""/>
              	<input type='hidden' value='' id='temp_select_value'/>
				<input type='hidden' value='' id='temp_select_text'/>
				<input type='hidden' value='' id='temp_select_table'/>
				<input type='hidden' value='' id='temp_select_condition'/>
				<input type='hidden' value='' id='temp_select_text_column'/>
              	</div>
              	<div class="content"><input type="text" id="temp_name_en" value="" style="font-weight:bolder;border:0px;background-color:transparent;"/></div>
              	<div class="clearfix"></div>
              	<div class="content"><div>中文名:</div><div><input id="temp_name_ch" value="" style="width:100px;"/></div></div>
              	<div class="content">
              		<div>是否单行:</div>
              		<div>
              			<select id="temp_is_single" style="width:100px;">
              				<option value="Y">是</option>
		             		<option value="N">否</option>
	             		</select>
              		</div>
              	</div>
              	<div class="content">
              		<div>可否为空:</div>
            		<div>
            			<select id="temp_be_null" style="width:100px;">
              				<option value="Y">可为空</option>
		             		<option value="N">不可为空</option>
	             		</select>
            		</div>
            	</div>
            	<div class="content">
              		<div>默认值:</div>
              		<div>
              			<input id="temp_default_value" value="" style="width:100px;"/>
              		</div>
              	</div>
            	<div class="clearfix"></div>
            	<div class="content">
              		<div>输入框类型:</div>
              		<div>
              			<jhcz:constselect id="temp_input_type" style="width:100px;" prefix="input_type_" value=""/>
              		</div>
              	</div>
              	<div class="content">
              		<div><a href="javascript:void(0)" onclick="doSelectConfig()" style="color:red;">配置输入框选项</a></div>
              		<div></div>
              	</div>
              	<div class="content">
              		<div>是否特殊格式:</div>
              		<div>
              			<select id="temp_is_special_format" style="width:100px;">
              				<option value="Y">是</option>
		             		<option value="N">否</option>
	             		</select>
              		</div>
              	</div>
              	<div class="content">
              		<div>特殊格式类型</div>
              		<div><jhcz:constselect id="temp_special_format" style="width:100px;" prefix="special_format_" value=""/></div>
              	</div>
              	<div class="clearfix"></div>
              	<div class="content">
              		<div>是否列表展示:</div>
  					<div>
	      				<select id="temp_show_in_list" style="width:100px;">
	      					<option value="Y">是</option>
		             		<option value="N">否</option>
	             		</select>
  					</div>
              	</div>
              	<div class="content">
              		<div>是否详情展示:</div>
              		<div>
              			<select id="temp_show_in_info" style="width:100px;">
              				<option value="Y">是</option>
		             		<option value="N">否</option>
	             		</select>
              		</div>
              	</div>
              	<div class="content">
              		<div>是否可查:</div>
              		<div>
              			<select id="temp_show_in_search" style="width:100px;">
              				<option value="Y">是</option>
		             		<option value="N">否</option>
	             		</select>
              		</div>
              	</div>
              	<div class="content">
              		<div>是否只读:</div>
              		<div>
              			<select id="temp_is_readonly" style="width:100px;">
              				<option value="Y">是</option>
		             		<option value="N">否</option>
	             		</select>
              		</div>
              	</div>
              	<div class="clearfix"></div>
              	<div class="content">
              		<div>是否导出:</div>
              		<div>
              			<select id="temp_be_export" style="width:100px;">
              				<option value="Y">是</option>
		             		<option value="N">否</option>
	             		</select>
              		</div>
              	</div>
              	<div class="content">
              		<div>是否系统取值:</div>
              		<div>
              			<select id="temp_is_sys" style="width:100px;">
              				<option value="Y">是</option>
		             		<option value="N">否</option>
	             		</select>
              		</div>
              	</div>
              	<div class="content">
              		<div>系统取值类型:</div>
              		<div>
              			<jhcz:constselect id="temp_sys_type" style="width:100px;" prefix="sys_col_" value=""/>
              		</div>
              	</div>
              	<div class="content">
              		<div>列表展示位置:</div>
              		<div>
              			<select id="temp_align_in_list" style="width:100px;">
              				<option value="center">居中</option>
              				<option value="left">左对齐</option>
		             		<option value="right">右对齐</option>
	             		</select>
              		</div>
              	</div>
              	<div class="clearfix"></div>
              	<input type="button" id="savetemp" value="确认" onclick="saveTempChange()" disabled="disabled">&nbsp;&nbsp;&nbsp;
				<input type="button" id="resettemp" value="重置" onclick="resetTemp()" disabled="disabled">
              </div>
              <!-- 编辑区结束 -->
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</form>
</body>
</html>

