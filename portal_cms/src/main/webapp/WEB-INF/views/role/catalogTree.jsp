<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%><%@page import="com.jhcz.base.util.RequestHelper"%>
<html>
<head>
<link rel="stylesheet" type="text/css" media="screen" href="/admin/styles/zTreeStyle/zTreeStyle.css"></link>
<script type="text/javascript" language="javascript" src="/admin/scripts/jquery.min.js"></script>
<script type="text/javascript" language="javascript" src="/admin/scripts/jqueryUI/jquery.ztree.all-3.5.js"></script>
<SCRIPT type="text/javascript">
		/**
		 * query tree type，0:find yes data 1: find no daata
		 */
		var type = '<%=RequestHelper.getString(request,"type") %>';
		var roleId = '<%=RequestHelper.getString(request,"roleId") %>';
		var chkboxType = {};
		if(type == 0)
		{
			/**
			 * 
			 */
			chkboxType = { "Y" : "s", "N" : "ps" };
		}
		else
		{
			/**
			 * 
			 */
			chkboxType = { "Y" : "ps", "N" : "ps" };
		}
		/**
		 * async find tree
		 */
		var setting = {
				check: {
					enable: true,
					chkboxType: chkboxType
				},
				data: {
					simpleData: {
						enable: true
					}
				}
			};

			/**
			 * result：{1001:[queryData,add,modify],1002:[delete,export]}
			 * checked: true yes   false no 
			 */
			function getCheckedData(checked)
			{
				var checkedData = null;
				var zTree = $.fn.zTree.getZTreeObj("treeObj");
				var nodes = zTree.getCheckedNodes(checked);
				if(nodes.length > 0)
				{
					checkedData = {};
					for (var i=0, l=nodes.length; i<l; i++) {
						var id = nodes[i].id;
						var funName = nodes[i].funName;
						
						if(id.indexOf("_") > 0)
						{
							id = id.substr(0,id.indexOf("_"));
						}
						var obj = checkedData[id];
						if(!obj)
						{
							obj = [];
							checkedData[id] = obj;
						}
						
						if(funName !== null && funName !== undefined && funName !== "")
						{
							obj.push(funName);
						}
					}
				}
				return checkedData;
			}
		
			var zNodes = ${result};
			$(document).ready(function(){
				//$.fn.zTree.init($("#treeObj"), setting);
				$.fn.zTree.init($("#treeObj"), setting, zNodes);
			});
</SCRIPT>
</head>
<body>
<div class="content_wrap">
	<div class="ztreeObjBackground left">
		<ul id="treeObj" class="ztree"></ul>
	</div>
</div>
</body>
</html>