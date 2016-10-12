<%@ page import="com.jhcz.plat.system.SysLibrary" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
function modiPassword()
{
	
   var returnValue = openDialogWithScroll('/admin/ModifyPwdAdmin/doDefault.action?isModelDialog', 500, 190);
   if (returnValue != null && returnValue.length > 0)
   {
	   if(returnValue[0] == "success")
	   {
	   	   alert("修改密码成功！");
	   }
   }
}

$(document).ready(function(){
	
	$("#loginOut").click(function(){
		if(window!= top){
			top.location.href = "/loginAdmin/loginOut.action"
		}else{
			window.location.href = "/loginAdmin/loginOut.action";
		}
	});
	/*$("#leftCatalogBtn").click(function(){
		var bottomObj = $(window.parent.document).find("[name='bottomFrame']");
		if(bottomObj.attr("cols") == '0,*')
		{
			bottomObj.attr("cols","150,*");
			$(this).attr("src","${ctxPath }/admin/images/pic05.gif");
		}
		else
		{
			bottomObj.attr("cols","0,*");
			$(this).attr("src","${ctxPath }/admin/images/pic06.gif");
		}
	});*/
	
	$("#sites").change(function(){
		parent.location.href="login.action?function=changeAccount&siteNo=" + $(this).val();
	});
	
	<c:if test="${not empty sessionScope[Constants.POPUP_MODIFYPWD_MESSAGE]}">
	alert('<c:out value="${sessionScope[Constants.POPUP_MODIFYPWD_MESSAGE]}"/>');
	modiPassword();
	</c:if>
});
</script>
<!--头部start-->
<div class="ie6_out">
  <div class="ie6_in">
    <div class="header">
      <div class="top1">
        <div class="website"> <span>当前站点：</span> <span>
          <select name="sites" id="sites">
            <c:forEach var="item" items="${data.sites}">
              <option value="${item.siteNo}" ${(item.siteNo eq data.siteNo)?'selected="selected"':''}>${item.name}</option>
            </c:forEach>
          </select>
          </span> </div>
      </div>
      <div class="top2">
        <div class="top2box">
          <div class="oc">
            <!--<img src="${ctxPath }/admin/images/pic05.gif" id="leftCatalogBtn"/>-->
            <!--打开的图片<img src="${ctxPath }/admin/images/pic06.gif" />-->
          </div>
          <ul class="topnav">
            <c:set var="count" value="0"/>
            <c:forEach items="${data.rootMenus}" var="rootMenu">
              <c:if test="${rootMenu.parentId == 1}">
                <li id="${rootMenu.id }" class="tn${count }" style="left:${count*90 }px;"><a href="${rootMenu.linkUrl }?form.manageCatalogId=${rootMenu.id }" target="leftFrame">${rootMenu.name }</a></li>
                <c:set var="count" value="${count+1 }"/>
              </c:if>
            </c:forEach>
          </ul>
          <div class="logininfo"> <span>当前登录用户：${data.userName}</span> <span>&nbsp;|&nbsp;
            <!--<a href="#">帮助</a>&nbsp;|&nbsp;-->
            <a href="javascript:void(0);" onClick="modiPassword();">修改密码</a>&nbsp;|&nbsp;<a href="javascript:" id="loginOut">退出</a>&nbsp;&nbsp;</span> </div>
        </div>
      </div>
      <div class="top3">
        <div class="tl"></div>
        <div class="tr">
          <!--<div class="logininfo">
        	<span>当前登录用户：admin</span>
            <span>&nbsp;|&nbsp;<a href="#">修改密码</a>&nbsp;|&nbsp;<a href="#">退出</a>&nbsp;&nbsp;</span>
            </div>-->
        </div>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
<!--
	setTopClass(window.top.frames['leftFrame'].location.href);
	/*
		设定头部选中样式
	*/
	function setTopClass(url){
		var topmenu=$(".topnav li");
		var k=1;
		for(var i=0;i<topmenu.length;i++){
			if(url.indexOf("form.manageCatalogId="+topmenu[i].id)==-1){
				$(topmenu[i]).addClass("tn"+k);
				k++;
			}else{
				$(topmenu[i]).removeClass().addClass("tn0");
			}
			//默认是最后一个
			if(k==topmenu.length+1){
				$(topmenu[i]).removeClass().addClass("tn0");
				window.top.frames['leftFrame'].location.href=url+"?form.manageCatalogId="+topmenu[i].id;
			}
		}
	}
	
	$(".topnav li").click(function(){
		var cur_src=$(this).children("a").attr("href");
		setTopClass(cur_src);
		window.top.frames['mainFrame'].location.href="/admin/rightAdmin/default.action?form.manageCatalogId="+$(this).attr("id");
	});
//-->
</script>
<!--头部end-->
</body>
</html>