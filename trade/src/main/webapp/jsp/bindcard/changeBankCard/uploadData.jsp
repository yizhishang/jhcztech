<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>
<script src="<%=basePath %>resources/jquery-easyui-1.4.1/jquery-1.10.2.min.js"></script>
<script src="<%=basePath %>js/jquery.validate.min.js"></script>
<script src="<%=basePath %>js/validateMethod.js"></script>
<script src="<%=basePath %>/js//jquery.form.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传资料</title>
</head>
<body>
    <!-- 上传主键的参考 -->
    <!--  采用jquery.form.js 来上传文件      http://blog.csdn.net/funi16/article/details/8238634-->
    <table>
    	<tr><td colspan="2">手持有效证件正面照：</td></tr>
    	<tr>
    		<td>
    			<img src="" width="100" height="50" id="effective_certificate_positive_img"></img>
    			<form action="<%=basePath %>upload.do?method=uploadData" enctype="multipart/form-data" method="post" id="effective_certificate_positive">
                     <input name="key" type="hidden" value="effective_certificate_positive">
                     <input  type="hidden" id="effective_certificate_positive_url"/>
                     <input type="file" name="uploadfile" onchange="onfireUpload('effective_certificate_positive','effective_certificate_positive')" style="display: none" accept=".jpe,.jpeg,.jpg,.png" id="effective_certificate_positive_upload">
                     <button type="button"  onclick="document.getElementById('effective_certificate_positive_upload').click();">正面上传</button>
                </form>
    		</td>
    		<td></td>
    	</tr>
    	<tr><td colspan="2">开户证件正反面照</td></tr>
    	<tr>
    		<td>
    			<img src="" width="100" height="50" id="openaccount_certificate_positive_img"></img>
    			<form action="<%=basePath %>upload.do?method=uploadData" enctype="multipart/form-data" method="post" id="openaccount_certificate_positive">
                      <input name="key" type="hidden" value="openaccount_certificate_positive">
                      <input  type="hidden" id="openaccount_certificate_positive_url"/>
                      <input type="file" name="uploadfile" onchange="onfireUpload('openaccount_certificate_positive','openaccount_certificate_positive')" style="display: none" accept=".jpe,.jpeg,.jpg,.png" id="openaccount_certificate_positive_upload">
					  <button type="button"  onclick="document.getElementById('openaccount_certificate_positive_upload').click();">正面上传</button>
				</form>
			</td>
    		<td>
    			<img src="" width="100" height="50" id="openaccount_certificate_opposite_img"></img>
    			<form action="<%=basePath %>upload.do?method=uploadData" enctype="multipart/form-data" method="post" id="openaccount_certificate_opposite">
                      <input name="key" type="hidden" value="openaccount_certificate_opposite">
                      <input  type="hidden" id="openaccount_certificate_opposite_url"/>
                      <input type="file" name="uploadfile" onchange="onfireUpload('openaccount_certificate_opposite','openaccount_certificate_opposite')" style="display: none" accept=".jpe,.jpeg,.jpg,.png" id="openaccount_certificate_opposite_upload">
					  <button type="button"  onclick="document.getElementById('openaccount_certificate_opposite_upload').click();">反面上传</button>
				</form>
			</td>
    	</tr>
    	<tr><td colspan="2">新银行卡正反面照</td></tr>
    	<tr>
    		<td>
    			<img src="" width="100" height="50" id="newbankcard_positive_img"></img>
    			<form action="<%=basePath %>upload.do?method=uploadData" enctype="multipart/form-data" method="post" id="newbankcard_positive">
                      <input name="key" type="hidden" value="newbankcard_positive">
                      <input  type="hidden" id="newbankcard_positive_url"/>
                      <input type="file" name="uploadfile" onchange="onfireUpload('newbankcard_positive','newbankcard_positive')" style="display: none" accept=".jpe,.jpeg,.jpg,.png" id="newbankcard_positive_upload">
					  <button type="button"  onclick="document.getElementById('newbankcard_positive_upload').click();">正面上传</button>
				</form>
    		</td>
    		<td>
    			<img src="" width="100" height="50" id="newbankcard_opposite_img"></img>
    			<form action="<%=basePath %>upload.do?method=uploadData" enctype="multipart/form-data" method="post" id="newbankcard_opposite">
                      <input name="key" type="hidden" value="newbankcard_opposite">
                      <input  type="hidden" id="newbankcard_opposite_url"/>
                      <input type="file" name="uploadfile" onchange="onfireUpload('newbankcard_opposite','newbankcard_opposite')" style="display: none" accept=".jpe,.jpeg,.jpg,.png" id="newbankcard_opposite_upload">
					  <button type="button"  onclick="document.getElementById('newbankcard_opposite_upload').click();">正面上传</button>
				</form>
    		</td>
    	</tr>
    	<tr><td colspan="2">银行柜台出具的新开卡凭证(加盖公章)</td></tr>
    	<tr>
    		<td>
    			<img src="" width="100" height="50" id="new_opencard_positive_img"></img>
    			<form action="<%=basePath %>upload.do?method=uploadData" enctype="multipart/form-data" method="post" id="new_opencard_positive">
                      <input name="key" type="hidden" value="new_opencard_positive">
                      <input  type="hidden" id="new_opencard_positive_url"/>
                      <input type="file" name="uploadfile" onchange="onfireUpload('new_opencard_positive','new_opencard_positive')" style="display: none" accept=".jpe,.jpeg,.jpg,.png" id="new_opencard_positive_upload">
                      <button type="button"  onclick="document.getElementById('new_opencard_positive_upload').click();">正面上传</button> 
				</form>
			</td>
    		<td></td>
    	</tr>
    </table>
    <form action="<%=basePath %>bindBankCard.do?method=doUplodData" method="post">
    	 <input type="submit" value="下一步" id="next_step"></input>
    </form>
</body>
</html>
<script src='<%=basePath %>js/uploadImg.js'></script>
<script src='<%=basePath %>js/math.uuid.js'></script>
<script>
	$(document).ready(function(){
		
	});
	
	// 上传方法 index键值是为了判断当前图片是否正在上传，如有多张图片在同一页面需要上传时不要定义重复了
	function onfireUpload(formItem, column) {
			 ajaxFileUpload($('#' + formItem), column, function(resultVo) {
				 $('#'+formItem+"_img").attr("src",resultVo.img_path[0]);
				 alert("column=="+column);
				 alert("img_path="+resultVo.img_path[0]);
				// 修改数据库
				//var detailId = document.getElementById("detailId").value;
				$.ajax({
					type : "POST",
					url : '<%=basePath %>bindBankCard.do?method=updateImg',
					dataType : "json",
					data : {
						"column":column,
						"img_path":resultVo.img_path[0]
					},
					success : function(msg) {
						if (msg.data == 0) {
			                alert("修改成功~");
						}
					}
				}); 
				
			}); 
		} 
</script>