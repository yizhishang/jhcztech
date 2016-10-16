<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/admin/common/header.jsp"%>
<body>
	<script type="text/javascript">
	
	$(document).ready(function(){
		$.formValidator.initConfig({formid:"addForm",alertmessage:false,onerror:function(msg){alert(msg)}});
		$("#wantdept").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"部门不能为空,请确认"});
		$("#position").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"职位不能为空,请确认"});
		$("#close").click(function(){
			window.close();
		});
	});
	
	function closeFunction()
	{
		window.close();
	}
</script>
	<form action="edit.action" method="post" target="hiddenFrame" id="addForm">
		<input type="hidden" name="function" value="edit" id="function" />
		<input type="hidden" name="form.jobid" value="${form.jobid}">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%" >
  <tr>
    <td>
        <div class="title">
            <p><img src="${ctxPath }/admin/images/ico04.gif" />修改招聘信息</p>
        </div>
    </td>
  </tr>
  <tr>
    <td class="edinner" style="width:100%"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
      <tr>
         <td><div class="space"></div>
     </tr>
      <tr>
        <td>
			<div class="space"></div>
			<div id="divContainer" style="overflow-y: auto;overflow-x:hidden;width:100%;">
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="editortable">
			  <tr>
                <td colspan="2" style="height:8px;"></td>
              </tr>
              <tr>
                <td width="120" class="label"><span class="DetailTagText">招聘部门</span>：</td>
                <td><input type="text" name="form.wantdept" id="wantdept" class="input02" style="width:200px;" value="<c:out value='${form.wantdept}'/>"/>
					<font color="#FF0000">*</font></td>
              </tr>
			  <tr>
                <td width="120" class="label"><span class="DetailTagText">职位名称</span>：</td>
                <td><input type="text" name="form.position" id="position" class="input02" style="width:200px;" value="${form.position}"/>
                <font color="#FF0000">*</font>
                </td>
              </tr>
			  <tr style="display:None;">
                <td width="120" class="label"><span class="DetailTagText">工作性质</span>：</td>
                <td>
	               <select name="form.property">
	                 <c:choose>
					   <c:when test="${form.property==0}">
					          <option value="0" selected="selected">全职</option>
			                  <option value="1">兼职</option>
					   </c:when>
					   <c:otherwise>
					          <option value="0" >全职</option>
			                  <option value="1" selected="selected">兼职</option>
					   </c:otherwise>
					</c:choose>
	               </select>
                </td> 
              </tr>
			  <tr>
                <td width="120" class="label"><span class="DetailTagText">薪水</span>：</td>
                <td><input type="text" name="form.salaryrange" id="salaryrange" class="input02" style="width:200px;" value="<c:out value='${form.salaryrange}'/>"/>
                </td> 
              </tr>
			  <tr>
                <td width="120" class="label"><span class="DetailTagText">阅历</span>：</td>
                <td><input type="text" name="form.experience" id="experience" class="input02" style="width:200px;" value="<c:out value='${form.experience}'/>"/>
                </td> 
              </tr>
			  <tr>
                <td width="120" class="label"><span class="DetailTagText">学历要求</span>：</td>
                <td><input type="text" name="form.degree" id="degree" class="input02" style="width:200px;" value="<c:out value='${form.degree}'/>"/>
                </td> 
              </tr>
			  <tr>
                <td width="120" class="label"><span class="DetailTagText">招聘数量</span>：</td>
                <td><input type="text" name="form.amount" id="amount" class="input02" style="width:200px;" value="<c:out value='${form.amount}'/>"/>
                </td> 
              </tr>
			  <tr>
                <td width="120" class="label"><span class="DetailTagText">发布时间</span>：</td>
                <td><input type="text" name="form.issuedate" readonly="readonly" id="issuedate" class="input02" style="width:116px;" value="${form.issuedate}"/>
                <img src="${ctxPath }/admin/images/pic13.gif" width="17" height="15" style="cursor:pointer" onClick="WdatePicker({el:'issuedate'})"/>
                 </td> 
              </tr>
			  <tr>
                <td width="120" class="label"><span class="DetailTagText">到期时间</span>：</td>
                <td><input type="text" name="form.expiredate" readonly="readonly" id="expiredate" class="input02" style="width:116px;" value="${form.expiredate}"/>
                <img src="${ctxPath }/admin/images/pic13.gif" width="17" height="15" style="cursor:pointer" onClick="WdatePicker({el:'expiredate'})"/>
                </td>
              </tr>
			  <tr>
                <td width="120" class="label"><span class="DetailTagText">工作地点</span>：</td>
                <td><input type="text" name="form.workarea" id="workarea" class="input02" style="width:200px;" value="<c:out value='${form.workarea}'/>"/>
                </td> 
              </tr>
			  <tr>
                <td width="120" class="label"><span class="DetailTagText">职位要求</span>：</td>
                <td>
                	<script type="text/javascript" src="${ctxPath }/admin/ueditor/ueditor.config.js"></script>
					<script type="text/javascript" src="${ctxPath }/admin/ueditor/ueditor.all.js"></script>
					<script type="text/javascript">
						$(function(){
							UE.getEditor('demand',{
						        initialFrameWidth : 900,
						        initialFrameHeight: 200,
						        onready:function(){
						        	this.setContent('${form.demand}');
						        }
						    });
						});
					</script>
                   	<textarea name="form.demand" id="demand"></textarea>
                </td>
              </tr>
			  <tr>
                <td width="120" class="label"><span class="DetailTagText">职位描述</span>：</td>
                <td>
					<script type="text/javascript">
						$(function(){
							UE.getEditor('description',{
						        initialFrameWidth : 900,
						        initialFrameHeight: 200,
						        onready:function(){
						        	this.setContent('${form.description}');
						        }
						    });
						});
					</script>
                   	<textarea name="form.description" id="description"></textarea>                
                
                
                </td>
              </tr>
              <td colspan="2" style="height:25px;" align="center">
                   <input type="type" name="enterForm" onClick="saveData();" id="enterForm" value="提交" class="bt04"/>&nbsp;
                   <input type="button" id="close" name="close"value="关闭" class="bt04"/>
                </td>
            </table>
            </div>
          </td>
      </tr>
    </table></td>
  </tr>
</table>
	</form>
	<iframe id="hiddenFrame" name="hiddenFrame" width="0" height="0" frameborder="0" marginwidth="0" marginheight="0" src="${ctxPath }/null.html"></iframe>
</body>
</html>
