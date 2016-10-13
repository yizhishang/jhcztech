<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>

<table width="100%" border="0" cellspacing="0" cellpadding="0" height="40">
  <tr>
    <td width="70%" >共<span>&nbsp;${data.page.totalRows}&nbsp;</span>条记录，每页<span>&nbsp;${data.page.numPerPage}&nbsp;</span>条，当前第<span>&nbsp;${data.page.currentPage}&nbsp;/&nbsp;${data.page.totalPages}&nbsp;</span>页 </td>
    <td width="30%"><div class="pagelink_pic"> <span><a href="#" onClick="javascript:toPage(1);" class="first">第一页</a></span> <span><a href="#" onClick="javascript:toPage($('#page').val(),-1);" class="next">上一页</a></span> <span><a href="#" onClick="javascript:toPage($('#page').val(),1);" class="pre">下一页</a></span> <span><a href="#" onClick="javascript:toPage(${data.page.totalPages});" class="end">最后页</a></span> </div></td>
  </tr>
</table>
<input type="hidden" name="page" id="page"value="${data.page.currentPage}" />
<input type="hidden" name="totalPages" id="totalPages"value="${data.page.totalPages}" />
