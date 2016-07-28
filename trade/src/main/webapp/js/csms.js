

var CSMS = {};

CSMS.common = {};

/**
 * 获取偏移日期
 * @param day Date
 * @param o 偏移值
 */
CSMS.common.getOffsetDay = function(day, o) {
	day.setDate(day.getDate() + parseInt(o, 10));
	/*var t = day.getTime();
	t -= parseInt(o, 10) * 3600000 * 24;
	day = new Date(t);*/
	var y = day.getFullYear();
	var m = day.getMonth() + 1;
	if (m < 10) {
		m = "0" + m;
	}
	var d = day.getDate();
	if (d < 10) {
		d = "0" + d;
	}
	return y + "-" + m + "-" + d;
};

/**
 * 验证手机号
 * @param mobile 手机号
 * @param tipshow 是否显示验证失败提示消息
 */
CSMS.checkMobile = function(mobile, tipshow) {
	var reg = /^8613[0-9]{9}$|^8615[0-9]{9}$|^86178[0-9]{8}$|^8618[0-9]{9}$|^8614[57][0-9]{8}$|^868[0-9]{10}$|^8617[0][0-9]{8}$/;
	if (reg.test(mobile) || reg.test("86" + mobile)) {
		return true;
	} else {
		if (tipshow) {
			$.messager.show({
				title:'验证手机号码失败',
				msg:'请输入正确的手机号',
				showType:'fade',
				timeout:2000,
				style:{
					right:'',
					top:document.body.scrollTop+document.documentElement.scrollTop + 30,
					bottom:''
				}
			});
		}
		return false;
	}
};

CSMS.easyui = {};
CSMS.easyui.datebox = {};
/**
 * EasyUI databox 日期 formatter 函数
 */
CSMS.easyui.datebox.dayFormatter = function(date) {
	var y = date.getFullYear();
    var m = date.getMonth()+1;
    var d = date.getDate();
    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
};
/**
 * EasyUI databox 日期 parser 函数
 */
CSMS.easyui.datebox.dayParser = function(s){
    if (!s) return new Date();
    var ss = (s.split('-'));
    var y = parseInt(ss[0],10);
    var m = parseInt(ss[1],10);
    var d = parseInt(ss[2],10);
    if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
        return new Date(y,m-1,d);
    } else {
        return new Date();
    }
};

/**
 * EasyUI datatimebox 日期 formatter 函数
 */
CSMS.easyui.datebox.dateTimeFormatter = function(date) {
	var y = date.getFullYear();
    var m = date.getMonth()+1;
    var d = date.getDate();
    var h = date.getHours();
    var mi = date.getMinutes();
    var se = date.getSeconds();
    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d) + ' ' 
    	+ (h<10?('0'+h):h) + ':' + (mi<10?('0'+mi):mi) + ':' + (se<10?('0'+se):se);
};
/**
 * EasyUI datatimebox 日期 parser 函数
 */
CSMS.easyui.datebox.dateTimeParser = function(s){
    if (!s) return new Date();
    var ds = s.split(' ');
    var ss = (ds[0].split('-'));
    var y = parseInt(ss[0], 10);
    var m = parseInt(ss[1], 10);
    var d = parseInt(ss[2], 10);
    var hms = ds[1].split(':');
    var h = parseInt(hms[0], 10);
    var mi = parseInt(hms[1], 10);
    var se = parseInt(hms[2], 10);
    if (!isNaN(y) && !isNaN(m) && !isNaN(d) && !isNaN(h) && !isNaN(mi) && !isNaN(se)){
        return new Date(y,m-1,d, h, mi, se);
    } else {
        return new Date();
    }
};

/**
 * 格式化 datagrid 的内容，添加 title
 */
CSMS.easyui.titleFormatter = function(value, row, index) {
	if (null != value && value != "") {
		title = value.replace(/'/g, "&acute;").replace(/"/g, '&quot;');
       	return "<span title='" + title + "' >" + value + "</span>";
	} else {
		return value;
	}
};
CSMS.easyui.styleFormatter = function(value, style) {
	return "<span style='" + style + "'>" + value + "</span>";
};

/**
 * 缓存数据
 * coFuncTypes: 客户信息受理维护 二级功能分类
 */
CSMS.cache = {};
/**
 *  功能分类 选择对话框
 * @param dc 对话框容器，可以是一个空的 div
 * @param callback 点击确定分类时的回调函数，会传递一个参数 包含 typeid，typename 成员
 * @param destroy 是否在关闭时 destroy dialog
 */
CSMS.customerOrderFuncType = function(dc, callback, destroy) {
	var init = dc.attr("initFlag");
	if (undefined == init) {
		dc.attr("initFlag", true);
		dc.empty();
		var wrap = $("<div class='co-code-defined-func-wrap'></div>");
		dc.append(wrap);
		var loading = $("<div>正在加载功能分类</div>");
		wrap.append(loading);
		var func1 = $("<div class='co-code-defined-func-1'></div>");
		var func2 = $("<div class='co-code-defined-func-2'></div>");
		wrap.append(func1).append(func2);
		dc.dialog({
		    title: '选择功能分类',
		    width: 580,
		    height: 500,
		    closed: false,
		    cache: false,
		    modal: true,
		    buttons:[{
				text:'关闭',
				handler:function(){
					if (destroy) {
						dc.dialog('destroy');
					} else {
						dc.dialog('close');
					}
				}
			}],
			onClose: function(){
				if (destroy) {
					dc.dialog('destroy');
				}
			}
		});
		function fill(data) {
			loading.hide();
			for(var i = 0; i <data.length ; i++){
				var fa1 = $("<a href='javascript:' typeid='" + data[i].id +"'>" + data[i].fundesc + "</a>");
				func1.append(fa1);
				fa1.click(function(){
					var $this = $(this);
					$.ajax({
						type:"post",
						url: "driver/basic/getGoodsCodeName",
						dataType: "json",
						data: ({
							parentId: $this.attr("typeid")
						}),
						success: function (data) {
							$this.siblings().removeClass("choosen");
							$this.addClass("choosen");
							func2.empty();
							for(var i = 0; i <data.length ; i++){
								var fa2 = $('<a href="javascript:" class="easyui-linkbutton c8 l-btn l-btn-small" style="width: 160px;" group="" id=""' +  
										' typeid="' + data[i].id + '" typename="' + data[i].fundesc + 
										'" ><span class="l-btn-left" style="margin-top: 0px;"><span class="l-btn-text">' +
										 data[i].fundesc+ '</span></span></a>');
								func2.append(fa2);
								fa2.click(function(){
									var $2 = $(this);
									//回调并关闭对话框
									callback({typeid: $2.attr("typeid"), typename: $2.attr("typename")});
									dc.dialog("close");
									return false;
								});
							}
						},
			    		error: function() {
			    			wrap.append("查询数据后台异常，请稍后再试！");
						}
					});
					return false;
				});
			}
			func1.find("a:first-child").click();
		}
		if (CSMS.cache.coFuncTypes) {
			fill(CSMS.cache.coFuncTypes);
		} else {
			$.ajax({
				type:"post",
				url: "driver/basic/getGoodsCodeName",
				dataType: "json",
				data: ({
					parentId: 0  // 功能分类
				}),
				success: function (data) {
					fill(data);
					CSMS.cache.coFuncTypes = data;
				},
				error: function() {
					wrap.append("查询数据后台异常，请稍后再试！");
				}
			});
		}
	} else {
		dc.dialog('open');
	}
};

$.extend($.fn.validatebox.defaults.rules, {
    selectRequired: {
        validator: function(value, param){
        	if (value === param[0]) {
        		return false;
        	} else {
        		return true;
        	}
        },
        message: '请选择'
    },
    numberStr : {
    	validator: function(value, param){
    		var reg = /^[0-9]+$/;
        	return reg.test(value);
        },
        message: '请输入数字'
    },
    selectValueRequired: {   
	   validator: function(value,param){             
	         if (value == "" || value.indexOf('请选择') >= 0) {   
	            return false;  
	         }else {  
	            return true;  
	         }    
	    },   
	    message: '该下拉框为必选项'   
	},
	username: {// 验证用户名  
       validator : function(value) {  
               return /^[a-zA-Z][a-zA-Z0-9_]{5,15}$/i.test(value);  
          },  
        message : '不合法（字母开头，允许6-16字节，允许字母数字下划线）'  
      },  
      account: {//param的值为[]中值  
    	         validator: function (value, param) {  
    	             if (value.length < param[0] || value.length > param[1]) {  
    	                  $.fn.validatebox.defaults.rules.account.message = '长度必须在' + param[0] + '至' + param[30] + '范围';  
    	                  return false;  
    	             } else {  
    	                 if (!/^[\w]+$/.test(value)) {  
    	                      $.fn.validatebox.defaults.rules.account.message = '只能数字、字母、下划线组成.';  
    	                      return false;  
    	                  } else {  
    	                      return true;  
    	                  }  
    	             }  
    	          }, message: ''  
    	      },
      english : {// 验证英语  
             validator : function(value) {  
                 return /^[A-Za-z]+$/i.test(value);  
              },  
              message : '请输入英文'  
          },
          vehiclee: { //判断车牌号
          	   validator:function(value){
          		     return /(^[\u4E00-\u9FA5]{1}[A-Z0-9]{6}$)|(^[A-Z]{2}[A-Z0-9]{2}[A-Z0-9\u4E00-\u9FA5]{1}[A-Z0-9]{4}$)|(^[\u4E00-\u9FA5]{1}[A-Z0-9]{5}[挂学警军港澳]{1}$)|(^[A-Z]{2}[0-9]{5}$)|(^(08|38){1}[A-Z0-9]{4}[A-Z0-9挂学警军港澳]{1}$)/.test(str);
          		   /*var re=/^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/;
       			   if(value.lenth>0 && value.lenth!=6){
       				   $.fn.validatebox.defaults.rules.vehiclee.message = '您输入的车牌号码超过7位';  
       				   return false;
       			   }else{
       				   if(value.search(re)==-1){
       					   $.fn.validatebox.defaults.rules.vehiclee.message = '请输入正确定的车牌号[A-Z0-9].';  
       	                   return false;  
       				   }else{
       					   return true;
       				   }
       			   }*/
          	   },
          	      message:'仅允许以汉字开头，后面可录入六个字符，由大写英文字母和阿拉伯数字组成。如：粤B12345'
             },
             phoneAndMobile : {// 电话号码或手机号码  
                 validator : function(value) {  
                     return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value) || /^(13|15|18)\d{9}$/i.test(value);  
                 },  
                 message : '电话号码或手机号码格式不正确'  
             },
             CHS: {  
                 validator: function (value) {  
                     return /^[\u0391-\uFFE5]+$/.test(value);  
                 },  
                 message: '只能输入汉字'  
             },  
             idcard : {// 验证身份证  
                 validator : function(value) {  
                     return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value) || /^\d{18}(\d{2}[A-Za-z0-9])?$/i.test(value);  
                 },  
                 message : '身份证号码格式不正确'  
             }
});

