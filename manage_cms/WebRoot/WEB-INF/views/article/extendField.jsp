<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/taglibs.jsp" %>

<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="editortable">
  <c:choose>
    <c:when test="${not empty data.extendFieldList}">
      <c:forEach var="item" items="${data.extendFieldList}">
        <tr>
          <td class="label" width="120">${item.name}：</td>
          <td><c:if test="${item.input_type == 1}">
              <input type="text" name="extFieldform.${item.code}" id="${item.code}" value="${item.default_value}" class="input02" style="width:150px;"/>
            </c:if>
            <c:if test="${item.input_type == 2}">
              <textarea class="input02" name="extFieldform.${item.code}" id="${item.code}" style="width:${item.width}px;height:${item.height}px;">${item.default_value}</textarea>
            </c:if>
            <c:if test="${item.input_type == 3}">
              <select name="extFieldform.${item.code}" id="${item.code}" style="width:153px;">
                <option value="">--请选择--</option>
                <c:forTokens var="it" items="${item.extend_content}" delims="$">
                  <option value="${it}">${it}</option>
                </c:forTokens>
              </select>
              <c:if test="${not empty item.default_value}">
                <script language="javascript">setSelectSelected('extFieldform.${item.code}','${item.default_value}')</script>
              </c:if>
            </c:if>
            <c:if test="${item.input_type == 4}">
              <c:forTokens var="it" items="${item.extend_content}" delims="$">
                <input type="radio" name="extFieldform.${item.code}" value="${it}"/>
                ${it} </c:forTokens>
              <c:if test="${not empty item.default_value}">
                <script language="javascript">setRadioChecked('extFieldform.${item.code}','${item.default_value}')</script>
              </c:if>
            </c:if>
            <c:if test="${item.input_type == 5}">
              <input type="text" name="extFieldform.${item.code}" id="${item.code}" class="input02" style="width:300px;"  value="${item.default_value}"/>
              <input type="button" onClick="openUploadFileDialog($('#${item.code}'))" value="上传文件" class="bt02"/>
              <input type="button"onClick="openDeleteFileDialog($('#${item.code}'))" value="删除" class="bt01"/>
            </c:if>
            <c:if test="${item.ismandatory == 1}"> <font color="#FF0000">*</font> </c:if>
          </td>
        </tr>
      </c:forEach>
    </c:when>
    <c:otherwise>
      <tr>
        <td colspan="2" align="center"><font color="#FF0000">该栏目无自定义字段</font></td>
      </tr>
    </c:otherwise>
  </c:choose>
</table>
