<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
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
</style>
<script type="text/javascript">
$(document).ready(function(){
	var selected = "${selected}";
	if(selected!=""){
		var sids = selected.split(",");
		for(var i=0; i<sids.length; i++){
			$("#select_content_"+sids[i]).attr("checked",true);
		}
	}
});
function saveSelect(){
	var value = $("#select_content_div input:checked").val();
	if(!value || value.length<1){
		alert("请选择一个先!");
		return false;
	}
	var ids = "";
	var txts = "";
	$("#select_content_div input:checked").each(function(){
		var temp_showtext = $(this).attr("showtext");
		var temp_value = $(this).val();
		ids += ","+temp_value;
		txts += ","+temp_showtext;
	});
	if(ids.length>0){
		ids = ids.substr(1);
	}
	if(txts.length>0){
		txts = txts.substr(1);
	}
	$("#${id}",window.opener.document).val(ids);
	$("#show_temp_${id}",window.opener.document).val(txts);
	if("${txtid}"!=""){
		$("#${txtid}",window.opener.document).val(txts);
	}
	window.close();
}
function doClose(){
	window.close();
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
              <ul class="menu"><li class="cur"><h2>选择内容</h2></li></ul>
              <div class="space"></div>
              <div>
              	  <c:if test="${input_type != ''}">
	              <div class="tabcontent" id="select_content_div">
	              	<c:forEach var="item" items="${list}" varStatus="status">
	              		<div style="float:left;">
	              			<input type="${input_type }" name="select_content" id="select_content_${item.optionvalue }" value="${item.optionvalue }" showtext="${item.optiontext }" /><span style="cursor:pointer;" onclick="$('#select_content_${item.optionvalue }').click()">${item.optiontext }</span>&nbsp;&nbsp;
	              		</div>
	              	</c:forEach>
	              	<div class="clearfix"></div>
	              	<br>
	              	<input type="button" value="确定" onclick="saveSelect()" />&nbsp;&nbsp;<input type="button" value="关闭" onclick="doClose()" />
	              </div>
	              </c:if>
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

