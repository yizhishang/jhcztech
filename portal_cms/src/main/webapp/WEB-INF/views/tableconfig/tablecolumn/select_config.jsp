<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
    <%@ include file="/admin/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>jhczCMS后台管理系统</title>
    <%@ include file="/admin/common/meta.jsp" %>
    <link href="<%=request.getContextPath()%>/admin/styles/cms.css" rel="stylesheet" type="text/css" />
    <script language="javascript" src="<%=request.getContextPath()%>/admin/scripts/ajax.js"></script>
    <script language="javascript" src="<%=request.getContextPath()%>/admin/scripts/jqueryUI/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/admin/scripts/form_commons.js"></script>
	<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/admin/scripts/datepicker/WdatePicker.js"></script>
	<script language="javascript" src="<%=request.getContextPath()%>/admin/scripts/jqueryUI/jquery-ui-1.8.19.custom.min.js"></script>
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
	.curtab{float:left;width:120px;border:1px solid #000000;cursor:pointer;text-align:center;}
	.tab{float:left;width:120px;backgroud-color:#d9d9d9;cursor:pointer;border:0px;text-align:center;}
	.tabcontent{width:990px;backgroud-color:#d9d9d9;border:1px solid #000000;padding:15px;}
	.sortnum{padding:10px;}
</style>
<script type="text/javascript">
$(document).ready(function(){
	var selected_type = "${data.select_type}";
	if(selected_type!="" && selected_type!="null"){
		changeTab($("#"+selected_type+"_title_div"),selected_type);
		if(selected_type=="zdy"){
			initSearchTable();
		}
	}
	$("#sortable").sortable();
	$('#sortable').bind('sortupdate', function(event, ui) { resortorder(); }); // 拖拽后回调
});
function resortorder(){
	$("#sortable input[id^='sortnum_']").each(function(i){
		$(this).val(i+1);
		$(this).parent().find("span[id^='sdsr_span_sort_']").html(i+1);
	});
}
function initSearchTable(){
	var select_value = "${data.select_value}";
	var select_text = "${data.select_text}";
	var selected_table = "${data.selected_table}";
	var selected_condition = "${data.select_condition}";
	if(selected_table && selected_table.length>0){
		$("#table_name").val(selected_table);
		$("#table_condition").val(selected_condition);
		searchTable(select_value,select_text);
	}
}
function changeTab(obj,id){
	$(obj).parent().find("div[class='curtab']").removeClass().addClass("tab");
	$(obj).removeClass().addClass("curtab");
	$(obj).parent().find("div[class='tabcontent']").hide();
	$("#"+id).show();
}
function searchTable(select_value,select_text){
	var table_name = $("#table_name").val();
	var datastr = "&function=AjaxSelectTable&table_name="+table_name;
	if(select_value && select_value.length>0){
		datastr += "&select_value="+select_value;
	}
	if(select_text && select_text.length>0){
		datastr += "&select_text="+select_text;
	}
	if(table_name && table_name.length>0){
		$("#zdyCols").html("<img src='/admin/images/loading.gif' align='absmiddle'>正在获取数据，请耐性等待。。。");
		$.ajax({
			type: "POST",
			  url: "tableCol.action",
			  data: datastr,
			  cache: false,
			  success: function(html){
				$("#zdyCols").html(html);
				$("#table_name_hidden").val(table_name);
			  }
			});
	}else{
		alert("请输入表名称！");
	}
}
function saveSjzd(){
	var value = $("#sjzd input:checked").val();
	if(!value || value.length<1){
		alert("请选择一个先!");
		return false;
	}
	var colselect = $("#colselect").val();
	$("#temp_select_value",window.opener.document).val("item_value");
	$("#temp_select_text",window.opener.document).val("item_name");
	$("#temp_select_table",window.opener.document).val("t_enum_value");
	$("#temp_select_condition",window.opener.document).val(" and enum_type='"+value+"'");
	$("#temp_select_text_column",window.opener.document).val(colselect);
	$("#savetemp",window.opener.document).attr("disabled",false);
	$("#resettemp",window.opener.document).attr("disabled",false);
	window.close();
}
function saveZdy(){
	var value = $("#valuearea input:checked").val();
	var text = $("#txtarea input:checked").val();
	var condition = $("#table_condition").val();
	if(!condition || condition.length<1){
		condition = "";
	}
	if(!value || value.length<1){
		alert("请选择一个value先!");
		return false;
	}
	if(!text || text.length<1){
		alert("请选择一个text先!");
		return false;
	}
	var colselect = $("#colselect").val();
	$("#temp_select_value",window.opener.document).val(value);
	$("#temp_select_text",window.opener.document).val(text);
	$("#temp_select_table",window.opener.document).val($("#table_name_hidden").val());
	$("#temp_select_condition",window.opener.document).val(condition);
	$("#temp_select_text_column",window.opener.document).val(colselect);
	$("#savetemp",window.opener.document).attr("disabled",false);
	$("#resettemp",window.opener.document).attr("disabled",false);
	window.close();
}
function saveSdsr(){
	var temp_id = "${data.temp_id}";
	//var ordernum = "";
	var item_value = "";
	var item_text = "";
	$("#sortable li div[id^='sdsr_div_']").each(function(){
		$(this).find("input").each(function(){
			var name = $(this).attr("name");
			if(name=="item_value"){
				var value = $(this).val();
				item_value += ","+value;
			}else if(name=="item_text"){
				var value = $(this).val();
				item_text += ","+value;
			}
		});
	});
	if(item_value.length>0){
		item_value = item_value.substr(1);
	}
	if(item_text.length>0){
		item_text = item_text.substr(1);
	}
	var datastr = "&temp_id="+temp_id;
	if(item_value.length>0 && item_text.length>0){
		datastr += "&item_value="+item_value+"&item_text="+item_text;
	}
	datastr =encodeURI(encodeURI(datastr));
	$.ajax({
	  type: "POST",
	  url: "tableCol.action?function=AjaxUpdateManualOption",
	  data: datastr,
	  cache: false,
	  success: function(html){
		  if(html && html=="suc"){
			var colselect = $("#colselect").val();
			$("#temp_select_value",window.opener.document).val("item_value");
			$("#temp_select_text",window.opener.document).val("item_text");
			$("#temp_select_table",window.opener.document).val("t_b_dictionary");
			$("#temp_select_condition",window.opener.document).val(" and column_id='"+temp_id+"'");
			$("#temp_select_text_column",window.opener.document).val(colselect);
			$("#savetemp",window.opener.document).attr("disabled",false);
			$("#resettemp",window.opener.document).attr("disabled",false);
			window.close();
		  }else{
			if(!html){
				html = "未知错误！";
			}
			alert(html);
		  }
		}
	});
}
function addInputSpan(index){
	var next_index = $("#sortable li:last").index()+1+1;
	var listart = "<li style=\"cursor:pointer;\"> <div id=\"sdsr_div_"+next_index+"\" style=\"padding:5px;\">";
	var inputs = "<input type=\"hidden\" name=\"sortnum\" id=\"sortnum_"+next_index+"\" value=\""+next_index+"\"/> <span class=\"sortnum\" style=\"width:30px;\" id=\"sdsr_span_sort_"+next_index+"\">"+next_index+"</span><span class=\"sortnum\">VALUE值：</span><span><input type=\"text\" name=\"item_value\" value=\"\"/></span>&nbsp;&nbsp;<span class=\"sortnum\">展示文本：</span><span><input type=\"text\" name=\"item_text\" value=\"\"/></span>";
	var ops = "<span style=\"padding-left:20px;\"><input type=\"button\" value=\"+\" alt=\"增加一行\" onclick=\"addInputSpan("+next_index+")\"></span><span><input type=\"button\" value=\"-\" alt=\"移除本行\" onclick=\"removeInputSpan("+next_index+")\"></span>";
	var liend = "</div></li>";
	$("#sdsr_div_"+index).parent().after(listart+inputs+ops+liend);
	resortorder();
}
function removeInputSpan(index){
	if(index){
		$("#sdsr_div_"+index).parent().remove();
	}else{
		alert("序号错误！");
	}
}
</script>
<div class="c_ie6_out">
  <div class="c_ie6_in">
    <div class="Wrapper">
      <div class="inner">
        <div class="innercontent">
          <div class="cl"></div>
          <div class="contentbox">
            <div class="content">
              <!-- 包含导航代码  -->
              <ul class="menu"><li class="cur"><h2>配置选项</h2></li></ul>
              <div class="space"></div>
              <div>
	              <div id="sjzd_title_div" class="curtab" onclick="changeTab(this,'sjzd')">数据字典</div><div id="zdy_title_div" class="tab" onclick="changeTab(this,'zdy')">自定义表字段</div><div id="sdsr_title_div" class="tab" onclick="changeTab(this,'sdsr')">手动输入</div>
	              <div style="padding-left:740px;">
	              	关联展示文本字段：<select name="colselect" id="colselect" style="width:220px;">
	              		<option value="">------------------</option>
	              		<c:forEach var="colitem" items="${data.cols}">
		              		<c:choose>
	              				<c:when test="${not empty data.select_text_column && data.select_text_column==colitem.name_en}">
	              					<option value="${colitem.name_en }" selected="selected">${colitem.name_ch }(${colitem.name_en })</option>
	              				</c:when>
	              				<c:otherwise>
									<option value="${colitem.name_en }">${colitem.name_ch }(${colitem.name_en })</option>
								</c:otherwise>
	              			</c:choose>
	              			
	              		</c:forEach>
	              	</select>
	              </div>
	              <div class="clearfix"></div>
	              <div class="tabcontent" id="sjzd">
	              	<c:forEach var="item" items="${data.list}" varStatus="status">
	              		<c:choose>
              				<c:when test="${not empty data.selected && data.selected==item.enum_value}">
              					<div style="float:left;"><input type="radio" name="sjzdradio" id="sjzdradio_${item.enum_value }" value="${item.enum_value }" checked="checked"/><span style="cursor:pointer;" onclick="$('#sjzdradio_${item.enum_value }').click()">${item.enum_name }</span>&nbsp;&nbsp;</div>
              				</c:when>
              				<c:otherwise>
								<div style="float:left;"><input type="radio" name="sjzdradio" id="sjzdradio_${item.enum_value }" value="${item.enum_value }"/><span style="cursor:pointer;" onclick="$('#sjzdradio_${item.enum_value }').click()">${item.enum_name }</span>&nbsp;&nbsp;</div>
							</c:otherwise>
              			</c:choose>
	              	</c:forEach>
	              	<div class="clearfix"></div>
	              	<br>
	              	<input type="button" value="确定" onclick="saveSjzd()" />
	              </div>
	              <div class="tabcontent" id="zdy" style="display:none;">
	              	<div>
	              		输入表名称(英文):<input type="hidden" id="table_name_hidden" value="" /><input type="text" name="table_name" id="table_name" value="" />&nbsp;&nbsp;<input type="button" value="查询" onclick="searchTable()" />
	              		<br>
	              		&nbsp;&nbsp;&nbsp;&nbsp;输入查询条件:<input type="text" name="table_condition" id="table_condition" value="" />  (形如 “ and columnA='1' and columnB='2'”,此处查询条件表示用于选择框展示或能被选择的内容受此条件限制)
	              	</div>
		            <div id="zdyCols"></div>
	              </div>
	              <div class="tabcontent" id="sdsr" style="display:none;">
	              	<ul id="sortable">
					<c:choose>
						<c:when test="${not empty data.select_type && data.select_type=='sdsr' && not empty data.dic_cols}">
							<c:forEach var="item" items="${data.dic_cols}" varStatus="status">
							<li style="cursor:pointer;">
								<div id="sdsr_div_${status.index+1}" style="padding:5px;">
								<input type="hidden" name="sortnum" id="sortnum_${status.index+1}" value="${status.index+1}"/> <span class="sortnum" style="width:30px;" id="sdsr_span_sort_${status.index+1}">${status.index+1}</span><span class="sortnum">VALUE值：</span><span><input type="text" name="item_value" value="${item.item_value}"/></span>&nbsp;&nbsp;<span class="sortnum">展示文本：</span><span><input type="text" name="item_text" value="${item.item_text}"/></span><span style="padding-left:20px;"><input type="button" value="+" alt="增加一行" onclick="addInputSpan(${status.index+1})"></span><span><input type="button" value="-" alt="移除本行" onclick="removeInputSpan(${status.index+1})"></span>
								</div>
							</li>
							</c:forEach>
						</c:when>
					<c:otherwise>
						<li style="cursor:pointer;">
	              			<div id="sdsr_div_1" style="padding:5px;">
	              			<input type="hidden" name="sortnum" id="sortnum_1" value="1"/> <span class="sortnum" style="width:30px;" id="sdsr_span_sort_1">1</span><span class="sortnum">VALUE值：</span><span><input type="text" name="item_value" value=""/></span>&nbsp;&nbsp;<span class="sortnum">展示文本：</span><span><input type="text" name="item_text" value=""/></span><span style="padding-left:20px;"><input type="button" value="+" alt="增加一行" onclick="addInputSpan(1)"></span><span><input type="button" value="-" alt="移除本行" onclick="removeInputSpan(1)"></span>
			              	</div>
	              		</li>
					</c:otherwise>
					</c:choose>
	              	</ul>
	              	<br>
	              	<input type="button" value="确定" onclick="saveSdsr()" />
	              </div>
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