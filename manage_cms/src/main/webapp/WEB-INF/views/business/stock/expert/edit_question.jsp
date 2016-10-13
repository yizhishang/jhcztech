<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<script type="text/javascript">	
	if(window.frameElement!=null && document.all != null)
	{
		window.frameElement.attachEvent("onresize",winResize);
	}
	else if(document.all != null)
	{
		window.attachEvent("onresize",winResize);
	} 
	else
	{
		window.onresize=winResize;
	}
	function winResize()
	{
		$("#divContainer").css("height",$(window).height() - 100);
	}
	
	function addFunction()
	{
		$("#enterForm").submit();
	}
	
	function closeFunction()
	{
		window.close();
	}
	
	$(document).ready(function(){
		winResize();
		$.formValidator.initConfig({formid:"enterForm",alertmessage:false,onerror:function(msg){alert(msg)}});
		$("#ansexpid").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"回复专家不能为空,请确认"});
		$("#answer").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"回复内容不能为空,请确认"});
		
		$("select[name='expertId']").change(function(){
			$("#ansexpid").val($(this).val());
		});
	});
</script>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
    <td><div class="title">
        <p><img src="${ctxPath }/admin/images/ico04.gif" />回答客户提问</p>
      </div></td>
  </tr>
  <tr>
    <td class="edinner" style="width:100%"><form action="expert.action" method="post" id="enterForm" target="hiddenFrame">
        <input type="hidden" name="function" id="function" value="${param.function}"/>
		<input type="hidden" name="form.queid" value="<c:out value='${form.queid}'/>">
        <input type="hidden" name="form.ansexpid" id="ansexpid" value="${form.eexpid}"/>
        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td></td>
          </tr>
          <tr>
            <td><div class="space"></div>
              <div class="label"> <a href="#" onClick="addFunction();"><img src="${ctxPath }/admin/images/icon018a16.gif" border="0"/>保存</a> <a href="#" onClick="closeFunction();"><img src="${ctxPath }/admin/images/closeimg.gif" border="0"/>关闭</a> </div></td>
          </tr>
          <tr>
            <td><div class="space"></div>
              <div id="divContainer" style="overflow-y: auto;overflow-x:hidden;width:100%;">
                <table width="90%" border="0" align="center" cellpadding="0" cellspacing="0" class="editortable">
                  <tr>
                    <td colspan="4" style="height:8px;"></td>
                  </tr>
                  <tr>
                    <td width="120" class="label"><span class="DetailTagText">客户号</span>：</td>
                    <td><c:out value='${form.clientid}'/></td>
                  </tr>
                  <tr>
                    <td class="label"><span class="DetailTagText">客户名称</span>：</td>
                    <td ><c:out value='${form.clientname}'/></td>
                  </tr>
                  <tr>
                    <td class="label"><span class="DetailTagText">提问时间</span>：</td>
                    <td><c:out value='${form.quetime}'/></td>
                  </tr>
                  <tr>
                    <td  class="label"><span class="DetailTagText">被提问专家</span>：</td>
                    <td><c:out value='${form.expertname}'/></td>
                  </tr>
                  <tr>
                    <td  class="label"><span class="DetailTagText">相关股票</span>：</td>
                    <td><c:out value='${form.stockcode}'/></td>
                  </tr>
                  <tr>
                    <td class="label"><span class="DetailTagText">问题内容</span>：</td>
                    <td ><textarea name="form.question" readonly="readonly" id="question" class="input02" style="width:450px;height:150px;"><c:out value='${form.question}'/>
</textarea></td>
                  </tr>
                  <c:if test="${form.isSystemAdmin}">
                    <tr>
                      <td class="label"><span class="DetailTagText">回复专家</span>：</td>
                      <td ><select name="expertId" style="width:150px;">
                          <option value="">--请选择--</option>
                          <c:forEach var="item" items="${form.expertList}">
                            <option value="${item.expid}">${item.name}</option>
                          </c:forEach>
                        </select>
						<script type="text/javascript">setSelectSelected("expertId","${form.eexpid}")</script>
                      </td>
                    </tr>
                  </c:if>
                  <tr>
                    <td class="label"><span class="DetailTagText">回复内容</span>：</td>
                    <td ><textarea name="form.answer" id="answer" class="input02" style="width:450px;height:150px;"><c:out value='${form.answer}'/></textarea></td>
                  </tr>
				  <tr>
                      <td class="label"><span class="DetailTagText">是否推荐</span>：</td>
                      <td ><select name="form.iscommend">
                                                        <option value="0">否</option>
                                                        <option value="1">是</option>
                                                    </select>
                                                    <script language="javascript">setSelectSelected("form.iscommend","<c:out value='${form.iscommend}'/>")</script>
						<script type="text/javascript">setSelectSelected("expertId","${form.eexpid}")</script>
                      </td>
                    </tr>
                  <tr>
                    <td colspan="4" style="height:8px;"></td>
                  </tr>
                </table>
              </div></td>
          </tr>
        </table>
      </form></td>
  </tr>
</table>
<iframe id="hiddenFrame" name="hiddenFrame" width="0"  height="0" frameborder="0" marginwidth="0" marginheight="0" src="${ctxPath }/null.html"></iframe>
</body>
</html>