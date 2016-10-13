<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
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
	openWindow("voteMark.action?function=add&sub_id=${param.sub_id }", 650, 350);
}

function editFunction(seri_num)
{
	openWindow("voteMark.action?function=edit&sub_id=${param.sub_id }&seri_num="+seri_num, 650, 380);
}
</script>
<form id="qryparm" name="qryparm" action="voteMark.action" >
  <input type="hidden" name="function" value="${param.function }" />
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
                <div class="title">
				  <p><img src="${ctxPath }/admin/images/ico07.gif" />当前位置：投票调查管理<img src="${ctxPath }/admin/images/pic14.gif" />评级设置［<c:out value='${data.sub_name}' />］
				  </p>  
				</div>
                <div class="label"> <a href="#" onClick="addFunction();"><img src="${ctxPath }/admin/images/ico08.gif" border="0"/>新建</a> <a href="#" onClick="deleteFunction('seri_num','delete')"><img src="${ctxPath }/admin/images/ico15.gif" border="0"/>删除</a> </div>
                <div class="search">
                  <div class="space"></div>
                  <div>
                    <div style="float:left;"> <span>关键字：
                      <input type="text" name="keyword" value="${param.keyword}" class="input01"/>
                      </span> <span>
                      <input type="submit" name="button" id="button" value="查询"  class="bt01" />
                      </span> </div>
                  </div>
                </div>
                <div class="space"></div>
                <div class="databox2" style="height: 350px">
                  <table width="99%" border="0" cellpadding="0" cellspacing="0" class="tab1">
                    <tr>
                      <td class="tdhead" style="width:45px;"><input name="tk_selcnt" type="checkbox" id="checkbox2" onClick="checkAll(this,'seri_num')"/></td>
                      <td class="tdhead">评级名称 </td>
                      <td class="tdhead">分数下限 </td>
                      <td class="tdhead">分数上限</td>
                      <td class="tdhead">描述 </td>
                      <td class="tdhead" style="width:100px;">状态</td>
                      <td class="tdhead" style="width:150px;">&nbsp;&nbsp;操作&nbsp;&nbsp;</td>
                    </tr>
                    <c:forEach var="item" items="${data.list}">
                      <tr style="cursor:pointer" onDblClick="javascript:editFunction(<c:out value='${item.seri_num}'/>)" title="双击查看详细内容">
                        <td>
                            <input type="checkbox" name="seri_num" id="check_<c:out value='${item.seri_num}'/>" value="<c:out value='${item.seri_num}'/>" onClick="setCheck(this)">
                        </td>
                        <td style="text-align:left" class="td3">&nbsp;
                          <c:out value="${item.define}" /></td>
                        <td style="text-align:left" class="td3">&nbsp;
                          <c:out value="${item.downlimit}" /></td>
                        <td style="text-align:left" class="td3">&nbsp;
                          <c:out value="${item.uplimit}" /></td>
                        <td style="text-align:left">&nbsp;
                          <c:out value="${item.remark}" /></td>
                        <td><c:if test="${item.state eq '0'}"><font color="#FF0000">无效</font></c:if>
                          <c:if test="${item.state eq '1'}">有效</c:if></td>
                        <td >&nbsp;<a href="#" onClick="editFunction(<c:out value='${item.seri_num}'/>)">编辑</a></td>
                      </tr>
                    </c:forEach>
                  </table>
                </div>
                <!-- 包含分页代码  -->
                <jsp:include flush="true" page="/WEB-INF/views/include/page.jsp"></jsp:include>
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