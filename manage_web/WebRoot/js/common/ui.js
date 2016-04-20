/**
 * 描述: 公用函数js类
 * 作者: JHCZ-蒋斌
 * 时间：2016/3/2
 */
var ui = {

};
ui.request = function (obj_code, obj_param, successCallback,isAsync, errorCallback ) {//kjse公共调用入口
  var temp_isAsync=true;
  if (false==isAsync||isAsync) {
    temp_isAsync=isAsync;
  }
  $.ajax({
    url: obj_code,// 跳转到 action
    data: obj_param,
    async: temp_isAsync, //默认为true 异步
    type: 'post',
    cache: false,
    dataType: 'json',
    success: function (responseData) {
      var recordArray = responseData.results;
      if (successCallback)successCallback(recordArray, responseData.resultCode, responseData.resultMessage);
    },
    error: function (responseData) {
      if (errorCallback) errorCallback(responseData.resultMessage, responseData.resultCode);
    }
  })
},
    /* 获取链接参数  参数个数一个*/
ui.getUrlParam=function(param){
  var result = location.search.match(new RegExp("[\?\&]" + param + "=([^\&]+)", "i"));
  if (result == null || result.length < 1) {
    return "";
  }
  return result[1];
}
