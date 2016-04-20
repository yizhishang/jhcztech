//成功提示绑定
$("#submitButton").click(function(){
    toastr.success("祝贺你成功了");
    toastr.info("这是一个提示信息");
    toastr.warning("警告你别来烦我了");
    toastr.error("出现错误，请更改");
})

/*    //信息提示绑定
    $("#info").click(function(){
        toastr.info("这是一个提示信息");
    })

    //敬告提示绑定
    $("#warning").click(function(){
        toastr.warning("警告你别来烦我了");
    })

    //错语提示绑定
    $("#error").click(function(){
        toastr.error("出现错误，请更改");
    })

    //清除窗口绑定
    $("#clear").click(function(){
        toastr.clear();
    })*/

var app = angular.module("about",[]);

app.controller("aboutCtrl",["$scope","$http","$log",function($scope, $http, $log){
	
	
	toastr.options = {
	    "closeButton": false, //是否显示关闭按钮
	    "debug": false, //是否使用debug模式
	    "positionClass": "toast-bottom-center",//弹出窗的位置
	    "showDuration": "300",//显示的动画时间
	    "hideDuration": "1000",//消失的动画时间
	    "timeOut": "2000", //展现时间
	    "extendedTimeOut": "1000",//加长展示时间
	    "showEasing": "swing",//显示时的动画缓冲方式
	    "hideEasing": "linear",//消失时的动画缓冲方式
	    "showMethod": "fadeIn",//显示时的动画方式
	    "hideMethod": "fadeOut" //消失时的动画方式
	};
	
	$scope.$watch("phone",function(){
		
	});
	
	
	$scope.register = function(){
		
		if($scope.form.$valid){
			
			$.post("/insertMessage.action",{
				"mobileNo" : $scope.phone,
				"name" : $scope.realName,
				"message" : $scope.message,
			},function(data){
				$log.info(data);
			});
			
		}else{
			toastr.info("这是一个提示信息");
		}
		
		
	}
	
}]);

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

