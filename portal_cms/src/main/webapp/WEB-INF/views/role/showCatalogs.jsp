<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<link href="${ctxPath }/admin/styles/checktree.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="${ctxPath }/admin/scripts/jquery.new.checktree.js"></script>
<script type="text/javascript">
    $(document).ready(function()
    {
        $.ajax({
            url: "role.action?function=ajaxShowCatalogRoleRight&id=${param.id}",
            cache: false,
            success: function(html)
            {
				$(window.parent.document).find(".okbox").show();
                $(".tree").html(html);
                $(".okbox").show();
                $("ul.tree").checkTree();
            }
        });
    });
</script>
<body>
<form action="role.action" id="addForm" method="post">
  <input type="hidden" name="function" value="editCatalogRoleRight" />
  <input type="hidden" name="role_id" value="${param.id}" />
  <div class="treebox">
    <ul class="tree" style="margin-left: 15px;">
      <img src="${ctxPath }/admin/images/loading.gif" />正在加载栏目树，请稍候
    </ul>
  </div>
</form>
</body>
</html>