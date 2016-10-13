<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/tags/jstl-core" prefix="c" %>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="editortable" style="border:0px">
  <c:forEach var="item" items="${webpartList}">
    <tr>
      <td width="120" class="label">${item.text}：</td>
      <td><c:choose>
          <c:when test="${item.type eq 'select'}">
            <select name="${item.name}">
              <c:forEach var="optionItem" items="${item.option}">
                <option value="${optionItem.value}" <c:if test="${not empty optionItem.checked and optionItem.checked eq 'true'}">selected="selected"</c:if>>${optionItem.text}</option>
              </c:forEach>
            </select>
          </c:when>
          <c:when test="${item.type eq 'radio'}">
            <c:forEach var="optionItem" items="${item.option}">
              <input type="radio" value="${optionItem.value}" name="${item.name}" <c:if test="${not empty optionItem.checked and optionItem.checked eq 'true'}">checked="checked"</c:if>/>
              ${optionItem.text} </c:forEach>
          </c:when>
         <c:when test="${item.type eq 'checkbox'}">
            <c:forEach var="optionItem" items="${item.option}">
              <input type="checkbox" value="${optionItem.value}" name="${item.name}" <c:if test="${not empty optionItem.checked and optionItem.checked eq 'true'}">checked="checked"</c:if>/>
              ${optionItem.text} </c:forEach>
          </c:when>
		  <c:when test="${item.type eq 'textarea'}">
		  <textarea name="${item.name}"><c:out value="${item.value}" escapeXml="true"/></textarea>
		  </c:when>
          <c:otherwise>
            <input type="text" name="${item.name}" id="${item.name}" class="input02" value="<c:out value="${item.value}" escapeXml="true"/>"/>
          </c:otherwise>
        </c:choose>
        <c:if test="${not empty item.notEmpty and item.notEmpty eq 'true'}"><font color="#FF0000">*</font></c:if>
        <c:if test="${not empty item.description}"><font color="#FF0000">${item.description}</font></c:if>
      </td>
    </tr>
  </c:forEach>
  <c:if test="${not empty templateContent}">
  <tr>
      <td width="120" class="label">模板正文：</td>
	  <td> <textarea name="templateContent" style="width:400px;height:150px;"><c:out value="${templateContent}" escapeXml="true"/></textarea><br/>
	  <font color="#FF0000">没有可为空</font>
	  </td>
   </tr>
   </c:if>
</table>
<script type="text/javascript">
function checkForm()
{
	var obj;
	var name;
	var length;
	<c:forEach var="item" items="${webpartList}">
		<c:if test="${not empty item.notEmpty and item.notEmpty eq 'true'}">
			name = '${item.name}';
			<c:choose>
			<c:when test="${item.type eq 'radio' or item.type eq 'checkbox'}">
			obj = $("[name='"+name+"']:checked");
			</c:when>
			<c:otherwise>
			obj = $("[name='"+name+"']");
			</c:otherwise>
			</c:choose>
			length = $.trim(obj.val()).length;
			
			if(length == 0)
			{
				return "${item.text}不能为空";
			}
		</c:if>
	</c:forEach>
	return "";
}

function getData()
{
	var webpartInfo = "";
	var obj;
	var name;
	var value;
		
	<c:if test="${not empty provider}">
	webpartInfo += " provider=\"${provider}\"";
	</c:if>
	<c:forEach var="item" items="${webpartList}">
	name = '${item.name}';
	<c:choose>
		<c:when test="${item.type eq 'radio' or item.type eq 'checkbox'}">
			obj = $("[name='"+name+"']:checked");
		</c:when>
		<c:otherwise>
			obj = $("[name='"+name+"']");
		</c:otherwise>
	</c:choose>
	value = obj.val();
	if($.trim(value).length == 0)
	{
		value = "";
	}
	webpartInfo += " " + name + "=\"" + value + "\"";
	</c:forEach>	
	return webpartInfo;
}
</script>