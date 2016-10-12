<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<div class="sidebar" id="sidebar">
    <div class="leftnav">
    	<c:forEach var="menu" items="${data.menuCatalogs }">
	        <div id="pre_${menu.id }">
	        	<c:set var="sep" value="?"></c:set>
	        	<c:if test="${fn:contains(menu.linkUrl, '?') }">
	        		<c:set var="sep" value="&"></c:set>
	        	</c:if>
	        	<c:choose>
	        		<c:when test="${empty menu.linkUrl or menu.linkUrl eq '#'}">
	        		<p><a class="lnd" id="${menu.id }" href="javascript:void(0);" target="mainFrame"  title="${menu.description }"><img src="${ctxPath }/admin/images/${menu.smallImage }" border="0">${menu.name }</a></p>
	        		</c:when>
	        		<c:otherwise>
		            <p><a class="lnd" id="${menu.id }" href="${menu.linkUrl }${sep }manageCatalogId=${menu.id}" target="mainFrame"  title="${menu.description }"><img src="${ctxPath }/admin/images/${menu.smallImage }" border="0">${menu.name }</a></p>
	        		</c:otherwise>
	        	</c:choose>
				<%--如果有子节点 --%>
        		<c:set var="sep" value="&"></c:set>
	    		<c:if test="${not empty menu.children and menu.linkUrl eq '#'}">
		           <ul id="sub_${menu.id }" class="subnav" style="display:none">
		           <c:forEach var="subMenu" items="${menu.children }">
		                <li class="snd"><a href="${subMenu.linkUrl }${sep }manageCatalogId=${subMenu.id}" target="mainFrame" title="${subMenu.description}"><img src="${ctxPath }/admin/images/${subMenu.smallImage }" border="0">${subMenu.name}</a></li>
	    			</c:forEach>
		            </ul>
	    		</c:if>
	        </div>
    	</c:forEach>
    </div>
</div>
<script type="text/javascript">
<!-- 
 $(".lnd,.lns").click(function () { 
 	  var id=$(".lns").attr("id");

  	 var child=$("#sub_"+$(this).attr("id"));
  	 if($(this).attr("id")!=id){
  	 	$("#sub_"+$(".lns").attr("id")).hide();
  	 	$(".lns").removeClass("lns").addClass("lnd");
 	    $(".over").removeClass("over");
  	 
	 	$(this).removeClass("lnd").addClass("lns");
	 	$("#pre_"+$(this).attr("id")).addClass("over");
	 	if(child){
		 	$("#sub_"+$(this).attr("id")).show();
	 	}
	 }else{
	 	if(child){
		 	if(child.css("display")=="none"){
		 		$("#sub_"+$(this).attr("id")).show();
		 	}else{
		 		$("#sub_"+$(this).attr("id")).hide();
		 	}
	 	}
	 }
	 $(".sns,.snd").removeClass("sns").addClass("snd");
 });
 
$(".sns,.snd").click(function () { 
  	  $(".sns,.snd").removeClass("sns").addClass("snd");
  	  if($(this).attr("class")=="snd"){
  	  		$(this).removeClass("snd").addClass("sns");
  	  }
});
//-->
</script>
</body>
</html>
