<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<link href="/admin/styles/checktree.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="/admin/scripts/jquery.new.checktree.js"></script>
<script language="javascript" src="/admin/scripts/syncReqJs.js"></script>
<script type="text/javascript">
	function deleteFunction(name,funcitonId){
		if(isChecked(name)){
			if($(":input[name='function']").length>0){
				$(":input[name='function']").val(funcitonId);
				if(window.confirm("确定删除选中数据？")){
					$("#qryparm").submit();
				}
			}
		}else{
			alert("请选择需要删除的数据！");
		}
	}

	function addFunction()
	{
		openWindow("doAddField.action?catalogId=${param.catalogId}", 500, 365);
	}
	
	function editFunction(id)
	{
		openWindow("doEditField.action?id=" + id, 500, 365);
	}
</script>
<body>
<div class="c_ie6_out">
  <div class="c_ie6_in">
    <div class="Wrapper">
      <div class="inner">
        <div class="innercontent">
          <div class="cl"></div>
          <div class="contentbox">
            <div class="content" id="content">
              <jsp:include page="${ctxPath }/WEB-INF/views/catalog/include/menu.jsp" flush="true">
              <jsp:param name="type" value="3"/>
              </jsp:include>
              <div class="label">
              	<a href="#" onClick="addFunction();"><img src="${ctxPath }/admin/images/ico08.gif" border="0"/>新建</a>
              	<a href="#" onClick="deleteFunction('id','deleteField')"><img src="${ctxPath }/admin/images/ico15.gif" border="0"/>删除</a>
              	<a href="#" onClick="location.reload();"><img src="${ctxPath }/admin/images/ico10.gif" border="0"/>刷新</a>
              </div>
              <form id="qryparm" name="qryparm" action="deleteField.action" method="post">
                <input type="hidden" name="function" value="${param.function }"/>
                <input type="hidden" name="catalogId" value="${param.catalogId}"/>
                <input type="hidden" name="successPage" value="doListField.action?catalogId=<c:out value='${param.catalogId}'/>">
                <div class="c_ie6_out">
                  <div class="c_ie6_in">
                    <div class="Wrapper">
                      <div class="inner">
                        <div class="innercontent">
                          <div class="cl"></div>
                          <div class="contentbox">
                            <div class="content">
                              <div class="search">
                                <div class="space"></div>
                                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab1">
                                  <tr>
                                    <td class="td1" style="width:52px;"><input name="tk_selcnt" type="checkbox" id="checkbox2" onClick="checkAll(this,'id')"/></td>
                                    <td class="tdhead" width="320">名称</td>
                                    <td class="tdhead" style="width:170px;">代码</td>
                                    <td class="tdhead" style="width:170px;">别名</td>
                                    <td class="tdhead" style="width:170px;">表现形式</td>
                                    <td class="tdhead" style="width:100px;">是否必填</td>
                                    <td class="tdhead" style="width:70px;">操作</td>
                                  </tr>
                                  <c:forEach var="item" items="${fieldList}">
                                    <tr style="cursor:pointer" onDblClick="javascript:editFunction('${param.catalogId}','${item.id}')" title="双击编辑文章">
                                      <td><input type="checkbox" name="id" id="check_<c:out value='${item.id}'/>" value="<c:out value='${item.id}'/>" onClick="setCheck(this)"></td>
                                      <td class="td3" style="text-align:left">&nbsp;
                                        <c:out value="${item.name}"/></td>
                                      <td style="text-align:left"><c:out value="${item.code}" /></td>
                                      <td style="text-align:left"><c:out value="${item.alias}" default="--"/></td>
                                      <td style="text-align:left"><c:if test="${item.input_type == 1}">文本框</c:if>
                                        <c:if test="${item.input_type == 2}">多行文本框</c:if>
                                        <c:if test="${item.input_type == 3}">下拉框</c:if>
                                        <c:if test="${item.input_type == 4}">单选框</c:if>
                                        <c:if test="${item.input_type == 5}">文件上传</c:if></td>
                                      <td><c:if test="${item.ismandatory == 0}"><font color="#ff0000">否</font></c:if>
                                        <c:if test="${item.ismandatory == 1}">是</c:if></td>
                                      <td><a href="#" onClick="editFunction(${item.id});">编辑</a></td>
                                    </tr>
                                  </c:forEach>
                                </table>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
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