<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
	var btnHTML = "<img name='addimg' style='cursor:pointer' src='${ctxPath }/admin/images/icon_add.gif' onClick='addItem();'/>&nbsp;<img name='delimg' style='cursor:pointer' src='${ctxPath }/admin/images/icon_remove.gif'  onClick='delItem();'/>";
	function addItem()
	{
		$("img[name='addimg']").parent().parent().after("<tr id='tr_answer'>"+$("#tr_answer").html()+"</tr>");
		$("tr[@id='tr_answer']").each(function(index){
				$(this).find("td:first-child").html("问题答案选项" + index + "：");
				$(this).find("img").remove();
				if(index == $("tr[@id='tr_answer']").length -1)
				{
					$(this).find(":text").val("");
					$(this).find("td:last-child").append(btnHTML);
				}
				
			});
	}
	
	function delItem()
	{
		$("tr[@id='tr_answer']").each(function(index){
			if($("tr[@id='tr_answer']").length == 1)
			{
				alert("还剩最后一行，不能删除。");
				return;
			}
			if(index == $("tr[@id='tr_answer']").length -2)
			{
				$(this).find("td:last-child").append(btnHTML);
			}
			if(index == $("tr[@id='tr_answer']").length -1)
			{
				$(this).remove();
			}
		});
	}
	
	$(document).ready(function(){
		$("#addForm").submit(function(){
			if($.trim($("#name").val()) == 0)
			{
				alert("投票问题名称不能为空！");
				return false;
			}
			
			var checkAnswer = false;
			$("input[@id='answer']").each(function(){
				if($.trim($(this).val()).length == 0)
				{
					checkAnswer = true;
					return;
				}
			});
			
			if(checkAnswer)
			{
				alert("还有未填写的问题答案，请检查！");
				return false;
			}
			
			return true;
		});
		$("#close").click(function(){
			window.close();
		});
	});
	
</script>
<form action="vote.action" method="post" target="hiddenFrame" id="addForm">
  <input type="hidden" name="function" value="addQuestion"/>
  <input type="hidden" name="form.sub_id" value="${param.id}"/>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
    <tr>
      <td><div class="title">
          <p><img src="${ctxPath }/admin/images/ico04.gif" />添加主题问题信息</p>
        </div></td>
    </tr>
    <tr>
      <td class="edinner"><table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td><div class="space"></div>
              <div id="divContainer" style="overflow-y: auto;overflow-x:hidden;width:100%;height:310px;">
                <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="editortable">
                  <tr>
                    <td colspan="2" style="height:8px;"></td>
                  </tr>
                  <tr>
                    <td width="120" class="label">投票问题名称：</td>
                    <td><input type="text" name="form.name" id="name" class="input02" style="width:310px;" value="<c:out value='${form.name}'/>"/>
                      <font color="#FF0000">*</font> &nbsp;&nbsp;
                      <input type="checkbox" name="form.type" value="1"/>
                      多选 </td>
                  </tr>
                  <tr>
                    <td width="120" class="label">有效天数：</td>
                    <td><input type="text" name="form.time_expries" id="time_expries" class="input02" style="width:100px;" value="<c:out value='${form.time_expries}'/>"/>
                      <font color="#FF0000">*</font> &nbsp;&nbsp;
                    </td>
                  </tr>
                  <tr>
                    <td width="120" class="label">投票问题的描述：</td>
                    <td><textarea name="form.description"  class="input02" style="height:100px;width:400px;border-top:1px solid;"><c:out value='${form.description}'/>
</textarea></td>
                  </tr>
                  <tr id="tr_answer">
                    <td width="120" class="label">问题答案选项0：</td>
                    <td><input type="text" name="answer" id="answer" class="input02" style="width:270px;" value="<c:out value='${form.answer}'/>"/>&nbsp;
                    	分值：<input type="text" name="mark" id="mark" class="input02" style="width:30px;" value="<c:out value='${form.mark}'/>"/>
                      <img name='addimg' style='cursor:pointer' src='${ctxPath }/admin/images/icon_add.gif' onClick="addItem();"/>&nbsp;<img name='delimg' style='cursor:pointer' src='${ctxPath }/admin/images/icon_remove.gif' onClick="delItem();"/></td>
                  </tr>
                  <tr>
                    <td class="label">&nbsp;</td>
                    <td><input type="submit" name="enterForm" id="enterForm" value="提交" class="bt04"/>
                      &nbsp;
                      <input type="button" id="close" name="close"value="关闭" class="bt04"/>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="2" style="height:8px;"></td>
                  </tr>
                </table>
              </div></td>
          </tr>
        </table></td>
    </tr>
  </table>
</form>
<iframe id="hiddenFrame" name="hiddenFrame" width="0"  height="0" frameborder="0" marginwidth="0" marginheight="0" src="${ctxPath }/null.html"></iframe>
</body>
</html>