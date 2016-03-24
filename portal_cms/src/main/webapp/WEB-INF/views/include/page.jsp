<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<div class="pagelink">
	<div class="tdbox1">已选择:<span id="tk_selcnt">0</span>|共<span>&nbsp;${data.page.totalRows}&nbsp;</span>条记录，每页<span>&nbsp;${data.page.numPerPage}&nbsp;</span>条，当前第<span>&nbsp;${data.page.currentPage}&nbsp;/&nbsp;${data.page.totalPages}&nbsp;</span>页 </div>
	<div class="tdbox2"> 
		<span><a href="#" onclick="javascript:toPage(1);" class="plink">第一页</a></span>
		<span><a href="#" onclick="javascript:toPage($('#page').val(),-1);" class="plink">上一页</a></span>
		<span><a href="#" onclick="javascript:toPage($('#page').val(),1);" class="plink">下一页</a></span>
		<span><a href="#" onclick="javascript:toPage(${data.page.totalPages});" class="plink">最末页</a></span>
		<span>转到第&nbsp;</span>
		<span><input type="text" name="page" id="page" class="input01" value="${data.page.currentPage}" style="width:30px;"/></span>
		<span>页</span> 
		<input id="totalPages" disabled="disabled" type="hidden" name="totalPages" value="${data.page.totalPages}"></input>
		<input type="submit" name="button2" id="button2" value="跳转" class="bt01" />
	</div>
</div>