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
	
	
	$scope.$watch("phone",function(){
		
	});
	
	
	$scope.register = function(){
		toastr.info('这是一个提示信息');
		if($scope.form.$valid){
			
			$.post("/insertMessage.action",{
				"mobileNo" : $scope.phone,
				"name" : $scope.realName,
				"message" : $scope.message,
			},function(data){
				$log.info(data);
			});
			
		}else{
			$log.info(888);
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

