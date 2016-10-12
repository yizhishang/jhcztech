$(document).ready(function(){  
	revalidateDataHeight();
	//处理分页问题
	if($("#qryparm")[0]){
		var totalPages=parseInt($('#totalPages').val());
		var page=parseInt($("#page").val());
		$("#qryparm").submit(function(){
			page=parseInt($("#page").val());
			if(page>totalPages){
				$("#page").val(totalPages);
			}
		});
		
		if(totalPages!=0&&page>totalPages){
			var result=window.confirm("当前数据列表的页数超过了最大页数，按确定可切换到第一页。");
			if(result){
				toPage(1);
			}
		}
	}
});

$(window).resize(function(){
	revalidateDataHeight();
});
/*
 * 重新计算数据列表所在div的高度
 * */
function revalidateDataHeight(){
	$(".databox2").css({"width":$(window).width() - 22,"height":($(window).height()-$(".databox2").attr("offsetTop")-$(".pagelink").height()-15)});
}

function toPage(page,offset){
	var pageNum=1;
	var offsetNum=0;
 
	pageNum=parseInt(page);
	offsetNum=parseInt(offset);
	if(!pageNum){
		pageNum=1;
	}
	if(!offsetNum){
		offsetNum=0;
	}
	var toPageNum=pageNum+offsetNum;
	if(toPageNum<1){
		alert("这已经是第一页！");
	}else if(toPageNum>parseInt($('#totalPages').val())){
		alert("这已经是最后一页！");
	}else{
		$("#page").val(toPageNum);
		$("#qryparm").submit();
	}
}

function disableButtons() {
	var thBtns = document.getElementsByTagName('input');
	for (var i=0; i<thBtns.length-1; i++) {
		var btobj = thBtns[i];
		if (btobj.type == "submit" || btobj.type == "reset" || btobj.type == "button") {
			btobj.disabled = "true";
		}
	}
}

/**
  判断一组同名的checkbox是否有选中
**/
function isChecked(name)
{
    return $(":checkbox[name='"+name+"']:checked").length>0;
}

/**
  根据一个checkbox对象的状态改变其它(多个同名的)checkbox的状态
  checkObj: checkbox对象
  name:需要改变状态的checkbox组的名称
**/
function checkAll(checkObj, name)
{
	var chkBtns=$(":checkbox[name='"+name+"']");
	if(chkBtns==null||chkBtns.length==0){
		return;
	}
	var checked = false;
	if($(checkObj).attr("checked") == "checked")
	{
		checked = true;
	}
	chkBtns.attr("checked",checked);
	
	if($(checkObj).attr("checked")){
		refreshCheckedDisp(chkBtns.length,name);
	}else{
		refreshCheckedDisp(0,name);
	}
}

/**
  根据checkbox对象的状态改变其所在row对象的状态
  checkObj:checkbox对象
**/
function setCheck(checkObj)
{
	if (!checkObj)
    {
        return;
    }
    refreshCheckedDisp(-1,checkObj.name);
}

//刷新共选择数据显示,icnt为设定的数目值，当icnt小于0时，需要计算。name为checkbox的name。
function refreshCheckedDisp(icnt,name) {
	var tk_selcnt=$("#tk_selcnt");
	if (tk_selcnt.length>0) {
		if (icnt < 0) {
			icnt = 0;
			icnt=$(":checkbox[name='"+name+"']:checked").length;
		}
		tk_selcnt.html(""+icnt);
	}
	
	$(":checkbox[name='"+name+"']").each(function(){
		if($(this).parent().parent()){
	    	if($(this).attr("checked")){
		    	$(this).parent().parent().addClass("trlight");
	    	}else{
	    		$(this).parent().parent().removeClass("trlight");
	    	}
	    }
	});
}

/**
   执行正则表达式
**/
function executeExp(re, s)
{
    return re.test(s);
}

/**
  判断是否是字母、数字或者为空
**/
function isAlphaNumeric(strValue)
{
    // 只能是 A-Z a-z 0-9 之间的字母数字 或者为空
    return executeExp(/^\w*$/gi, strValue);
}

/**
  判断是否是正确的日期，格式为2003-12-12
**/
function isDate(strValue)
{
    if (isEmpty(strValue)) return true;

    if (!executeExp(/^\d{4}-[01]?\d-[0-3]?\d$/g, strValue)) return false;

    var arr = strValue.split("-");
    var year = arr[0];
    var month = arr[1];
    var day = arr[2];

    // 1 <= 月份 <= 12，1 <= 日期 <= 31
    if (!( ( 1 <= month ) && ( 12 >= month ) && ( 31 >= day ) && ( 1 <= day ) ))
        return false;

    // 润年检查
    if (!( ( year % 4 ) == 0 ) && ( month == 2) && ( day == 29 ))
        return false;

    // 7月以前的双月每月不超过30天
    if (( month <= 7 ) && ( ( month % 2 ) == 0 ) && ( day >= 31 ))
        return false;

    // 8月以后的单月每月不超过30天
    if (( month >= 8) && ( ( month % 2 ) == 1) && ( day >= 31 ))
        return false;

    // 2月最多29天
    if (( month == 2) && ( day >= 30 ))
        return false;

    return true;
}

/**
  判断是否是正确的Email
**/
function isEmail(strValue)
{
    if (isEmpty(strValue)) return true;

    var pattern = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
    return executeExp(pattern, strValue);
}

/**
  判断字符串是否为空
**/
function isEmpty(strValue)
{
    if (strValue == null || strValue == "")
        return true;
    else
        return false;
}


/**
  判断是否为数字
**/
function isNumeric(strValue)
{
    return executeExp(/^\d*$/g, strValue);
}


/**
  判断是否为浮点数（不带正负号）
**/
function isNumberFloat(strValue)
{
    if (isEmpty(strValue)) return true;
    return executeExp(/^\d+(\.\d+)?$/, strValue);
    //return (!isNaN(parseFloat(strValue))) ? true : false;
}


/**
  判断是否是货币
**/
function isMoney(strValue)
{
    if (isEmpty(strValue)) return true;
    return executeExp(/^[+-]?\d+(,\d{3})*(\.\d+)?$/g, strValue);
}


/**
  判断是否为手机号码
**/
function isMobile(strValue)
{
    if (isEmpty(strValue)) return true;
    return executeExp(/^(13|15|18)[0-9]{9}$/, strValue);
}

/**
  判断是否为电话
**/
function isPhone(strValue)
{
    if (isEmpty(strValue)) return true;
    return executeExp(/(^\(\d{3,5}\)\d{6,8}(-\d{2,8})?$)|(^\d+-\d+$)|(^(130|131|132|133|134|135|136|137|138|139|158|159)\d{8}$)/g, strValue);
}

/**
  判断是否为邮政编码
**/
function isPostalCode(strValue)
{    if (isEmpty(strValue)) return true;
    return executeExp(/(^$)|(^\d{6}$)/gi, strValue);
}

/**
  判断是否为合法的URL
**/
function isURL(strValue)
{
    if (isEmpty(strValue)) return true;
    var pattern = /^(http|https|ftp):\/\/(\w+\.)+[a-z]{2,3}(\/\w+)*(\/\w+\.\w+)*(\?\w+=\w*(&\w+=\w*)*)*/gi;
    return executeExp(pattern, strValue);
}


//比较，compare(1,'<=10')将返回true，第一个参数为要比较的数字，第二个参数支持>、=、<、<=、>=
function compare(l, strParam)
{
    var ml;
    // 要判断的长度
    var co;
    // 比较符

    // 判断是否为<=、>=
    if (strParam.indexOf('<=') != -1 || strParam.indexOf('>=') != -1)
    {
        ml = parseInt(strParam.substr(2));
        cp = strParam.substr(0, 1);
    }
    else
    {
        ml = parseInt(strParam.substr(1));
        cp = strParam.charAt(0);
    }

    switch (cp)
            {
        case '<' :
            if (l >= ml) return false;
            break;
        case '=' :
            if (l != ml) return false;
            break;
        case '>' :
            if (l <= ml) return false;
            break;
        case '<=' :
            if (l > ml) return false;
            break;
        case '>=' :
            if (l < ml) return false;
            break;
        default :
            return false;
    }

    return true;
}

/**
  打开新窗口
**/
function openWin(url, width, height)
{
    if (url == '')
        return;
    var winOption = "toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=yes,left=50,top=50,width=" + width + ",height=" + height;
    window.open(url, '', winOption);
    return;
}

/**
  打开新窗口,带滚动条
**/
function openWinWithScroll(url, width, height)
{
    if (url == '')
        return;
    var winOption = "toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,left=50,top=50,width=" + width + ",height=" + height;
    window.open(url, '', winOption);
    return;
}

/**
 以最大化方式打开新窗口
**/
function openMaxWindow(url)
{
    if (url == '')
        return;
    var winOption = "toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=yes,fullscreen=0";
    var win = window.open(url, '', winOption);
    win.moveTo(0, 0);
    win.resizeTo(screen.availWidth, screen.availHeight);
    return win;
}

/**
  以最大化方式打开新窗口，并且带滚动条
**/
function openMaxWindowWithScroll(url)
{
    if (url == '')
        return;
    var winOption = "toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,fullscreen=0";
    var win = window.open(url, '', winOption);
    win.moveTo(0, 0);
    win.resizeTo(screen.availWidth, screen.availHeight);
    return win;
}

/**
  以最大化方式打开新窗口，并且带滚动条，还可以命名
**/
function openMaxNamedWindowWithScroll(url, windowName)
{
    if (url == '')
        return;
    var winOption = "toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,fullscreen=0";
    var win = window.open(url, windowName, winOption);
    win.moveTo(0, 0);
    win.resizeTo(screen.availWidth, screen.availHeight);
    return win;
}

/**
  打开对话框
**/
function openDialog(url, width, height)
{
	if(url.lastIndexOf("#") != -1)
	{
		url = url.substr(0,url.lastIndexOf("#"));
	}
	if(url.indexOf("?") != -1)
	{
		url += "&isModelDialog";
	}
	else
	{
		url += "?isModelDialog";
	}
    return window.showModalDialog(url, window, 'dialogHeight: ' + height + 'px; dialogWidth: ' + width + 'px;edge: Raised; center: Yes; help: Yes;scroll:no; resizable: no; status: no;resizable:yes');
}

/**
  打开对话框，并且带滚动条
**/
function openDialogWithScroll(url, width, height)
{
    return window.showModalDialog(url, window, 'dialogHeight: ' + height + 'px; dialogWidth: ' + width + 'px;edge: Raised; center: Yes; help: Yes;scroll:auto; resizable: no; status: no;');
}

function openUploadFileDialog(fieldObj)
{
    if(fieldObj && fieldObj[0].tagName == "INPUT" && fieldObj[0].type=="text")
    {
    	return window.showModalDialog("/admin/upload/upload.jsp",fieldObj,'dialogHeight: 100px; dialogWidth: 350px;edge: Raised; center: Yes; help: no;scroll:no; resizable: no; status: no;');
    }
    alert("接收上传文件路径的对象不是一个输入域");
}

/**
 * 描述: 删除文件
 * 作者: 袁永君
 * 创建日期: 2015-12-25
 * 创建时间: 下午8:46:25
 * @param fieldObj	文件对象
 * @returns
 */
function openDeleteFileDialog(fieldObj)
{
    if(fieldObj)
    {
        if($.trim(fieldObj.val()).length == 0)
        {
            alert("您还没有上传文件");
            return;
        }
        var url = "/admin/UploadFileAdmin/deleteFile.action";
        $.post(url, {"filePath": fieldObj.val()},function(data){
			if(data.errorNo == 0)
			{
				if(window.confirm(data.errorInfo)){
		            fieldObj.val("");
		            $('#img').hide();
		            $('#img').attr('src','');
				}
			}else{
				alert(data.errorInfo);
			}
		},"json");
        //var returnValue =  window.showModalDialog(url,fieldObj,'dialogHeight: 80px; dialogWidth: 350px;edge: Raised; center: Yes; help: no;scroll:no; resizable: no; status: no;');
    }
}

/*
 * 从<pre style="word-wrap: break-word; white-space: pre-wrap;">{"errorNo":0,"errorInfo":"上传成功!","obj":null}</pre>
 * 获取Result返回对象
 */
function getJsonValue(param){
	param = param.split(">")[1].split("<")[0];
	return $.parseJSON(param);
}

/**
 * 上传文件
 */
function uploadFile()
{
	$.ajaxFileUpload({
        url: "/admin/UploadFileAdmin/fileUpload.action", 
        secureuri: false, //一般设置为false
        fileElementId: 'uploadfile', // 上传文件的id、name属性名
        dataType: 'application/json', //返回值类型，一般设置为json、application/json
        success: function(data, status, e){
        	if(data == ""){
        		return false;
        	}
        	data = getJsonValue(data);
        	if(data.errorNo == 0)
    		{
        		if(window.confirm(data.errorInfo)){
        			
        			var s = window.dialogArguments;
        			if (s.length == 1) {
        			    s.attr('value', data.obj.path);
        			}
        			if (s.length == 2) {
        			    var fileName = s[0];
        			    var fieldObj = s[1];
        			    if (fileName) {
        			        fileName.attr('value', data.obj.name);
        			    }
        			    if (fieldObj) {
        			        fieldObj.attr('value', data.obj.path);
        			    }
        			}
        			window.returnValue = data.obj;
            		window.close();
    			}
    		}
        },
        error: function(data, status, e){
        	if(data){
	            console.log(data);  
        	}else{
        		alert("error:  "+e);
        	}
        }
    });
}

function openWindow(url, width, height)
{
//   var returnValue = openDialogWithScroll(url, width, height);
   var returnValue = openDialog(url, width, height);
   if (returnValue != null && returnValue.length > 0)
   {
	   if(returnValue[0] == "success")
	   {
	   		window.location.reload();
	   }
    }
}


//根据select的名称和值，设置某一项选中
function setSelectSelected(selectName, value)
{
	$("select[name='"+selectName+"']").val(value);
}

//根据radio的名称和值，设置某一项选中
function setRadioChecked(radioName, value)
{
	if($.trim(value).length == 0)
		return;
  	$("input[name='"+radioName+"']").each(function(){
		if($(this).val() == value)
		{
			$(this).attr("checked", true);	
		}
		else
		{
			$(this).attr("checked", false);	
		}
	});
}

/**
 * 描述: 获取地址栏参数值
 * 作者: 袁永君
 * 创建日期: 2015-12-25
 * 创建时间: 下午8:46:25
 * @param name	参数名
 * @param args	地址栏字符串
 * @returns
 */
function getKeyword(name, args){
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
	var key = "";
	if(args == null)
	{
		args = window.location.search;
		key = args.substr(1).match(reg);
	}else{
		key = args.match(reg);
	}
	if (key!=null){
		var value = decodeURIComponent(key[2]);
		return decodeURIComponent(value);
	}
	return "";
};

/**
 * 描述: 提交表单,刷新父页面
 * 作者: 袁永君
 * 创建日期: 2015-12-25
 * 创建时间: 下午8:46:25
 * @param formId	formId
 * @param funcitonId	方法名
 * @param isSelf	false--刷新父页面
 * @param type	ajax返回类型，默认不传为json
 * @returns
 */
 function submitForm(formId, funcitonId, isSelf, type)
{
	if(!formId){
		formId = $("form").attr("id");
	}
	
	var URL = "";
	if(funcitonId)
	{
		URL = funcitonId + ".action";
	}else{
		URL = $("#" + formId).attr("action");
	}
	
	if(type == null)
	{
		type = "json";
	}
	$.ajax({
		type:"post",
		url:URL,
		dataType : type,
		data : encodeURI($("#" + formId).serialize()),
		success: function(data){
			if(data.errorNo == 0)
			{
				if(window.confirm(data.errorInfo)){
					if(isSelf)
					{
						location.reload();
					}else{
						if(!isIE())
						{
							if(window.opener == null)
							{
								location.reload();
								return;
							}
							window.opener.location=window.opener.location;
						}
						window.returnValue = data;
						window.close();
					}
				}
			}else{
				alert(data.errorInfo);
			}
		},
		error: function(data)
		{
			alert(data);
		}
	});
}

function addFunction(param)
{
	if(param == null){
		saveDataFunction("doAdd.action");
	}else{
		saveDataFunction("doAdd.action?" + param);
	}
}

function editFunction(param)
{
	if(param == null){
		saveDataFunction("doEdit.action");
	}else{
		saveDataFunction("doEdit.action?" + param);
	}
}

function saveDataFunction(url)
{
	var returnValue = openDialogWithScroll(url, 1300, 800);
    if (returnValue != null && returnValue.length > 0)
    {
    	var is_submit = getKeyword("is_submit", returnValue);
    	if(is_submit == "1")
    	{
    		location.reload();
    	}else{
    		url = getKeyword("function", returnValue) + ".action";
    		$.post(url, returnValue,function(data){
    			if(data.errorNo == 0)
    			{
    				if(window.confirm(data.errorInfo)){
    					location.reload();
    				}
    			}else{
    				alert(data.errorInfo);
    			}
    		},"json");
    	}
    }
}

function saveData()
{
	if($("#is_submit").val() && $("#is_submit").val() == "1")
	{
		$("form").submit();
	}
	window.returnValue = encodeURI($("form").serialize());
	window.close();
}

function closeFunction()
{
	window.close();
}

function deleteFunction(name,funcitonId){
	if (isChecked(name) && window.confirm("确定删除选中数据？")){
		submitForm("qryparm", funcitonId, true);
	}else{
		alert("请选择需要删除的数据！");
	}
}

function deleteAllFunction(funcitonId){
	$(":input[name='function']").val(funcitonId);
	if (window.confirm("确定删除所有数据？")){
		submitForm("qryparm", funcitonId, true);
	}else{
		alert("请选择需要删除的数据！");
	}
}
/**
 * 描述: 更新数据状态
 * 作者: 袁永君
 * 创建日期: 2015-12-25
 * 创建时间: 下午9:18:50
 * @param name
 * @param funcitonId
 * @param state
 */
function editStateFunction(name, funcitonId, state)
{
    if (isChecked(name) && window.confirm("确定更新选中数据？"))
    {
        $(":input[name='function']").val(funcitonId);
        $(":input[name='state']").val(state);
        submitForm("qryparm", funcitonId, true);
    }
    else
    {
        alert("请选择需要更新的数据！");
    }
}

function manageFunction(name,funcitonId,msg){
	if(isChecked(name) && window.confirm("确定"+msg+"选中数据？")){
		$(":input[name='function']").val(funcitonId);
		submitForm("qryparm", funcitonId, true);
	}else{
		alert("请选择需要"+msg+"的数据！");
	}
}

function menuOperation()
{
	if($("#hiddenForm").attr("target") == "_blank"){
		$("#hiddenForm").submit();
	}else{
		$.post($("#hiddenForm").attr("action"),{"catalogId": $("#hiddenForm").find("input[name=catalogId]").val()},function(result){
			if(result && result.errorNo == 0)
			{
				alert(result.errorInfo);
			}else{
				alert("操作失败")
			}
		});
	}
}


function autoOncontextmenu()
{
   	var menu = new BootstrapMenu('.webfx-tree-row', {
		actions: [{
			name: '重新读入子目录',
			onClick: function() {
				reloadChildrenCatalog();
			}
		}, {
			name: '刷新目录树',
			onClick: function() {
				window.location.reload();
			}
		}, {
     		name: '添加子目录',
       		onClick: function() {
       			addFunction();
       		}
       	}, {
			name: '删除本目录',
			onClick: function() {
				deleteFunction();
			}
		}]
   	});	
}