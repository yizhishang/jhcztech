<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/taglibs.jsp" %>
<div id="valuearea" style="width:450px;float:left;border:1px solid black;margin:10px;padding:5px;">
<div style="float:left;margin-bottom:15px;"><h1>VALUE字段</h1></div>
<div class="clearfix"></div>
<c:forEach var="item" items="${data.list}" varStatus="status">
	<div style="float:left;width:140px;"><input type="radio" name="zdyradio" id="zdyradio_${fn:toLowerCase(item.column_name) }" value="${fn:toLowerCase(item.column_name) }"/><span style="cursor:pointer;" onclick="$('#zdyradio_${fn:toLowerCase(item.column_name) }').click()">${fn:toLowerCase(item.column_name) }</span>&nbsp;&nbsp;</div>
</c:forEach>
</div>
<div id="txtarea" style="width:450px;float:left;border:1px solid black;margin:10px;padding:5px;">
<div style="float:left;margin-bottom:15px;"><h1>展示文本字段</h1></div>
<div class="clearfix"></div>
<c:forEach var="item" items="${data.list}" varStatus="status">
	<div style="float:left;width:140px;"><input type="radio" name="zdywbradio" id="zdywbradio_${fn:toLowerCase(item.column_name) }" value="${fn:toLowerCase(item.column_name) }"/><span style="cursor:pointer;" onclick="$('#zdywbradio_${fn:toLowerCase(item.column_name) }').click()">${fn:toLowerCase(item.column_name) }</span>&nbsp;&nbsp;</div>
</c:forEach>
</div>
<div class="clearfix"></div>
<br>
<input type="button" value="确定" onclick="saveZdy()" />
<script type="text/javascript">
$(document).ready(function(){
	var select_value = "${data.select_value}";
	var select_text = "${data.select_text}";
	if(select_value && select_value!="" && select_value!="null"){
		$("#zdyradio_"+select_value).click();
	}
	if(select_text && select_text!="" && select_text!="null"){
		$("#zdywbradio_"+select_text).click();
	}
});
</script>
