$(document).ready(function() {
/*// 全局变量
window.config = window.config || new function() {
    this.path = '/static';
    //this.js = this.path + '/js/pc/';
    $.ajax({
		url : '',// 跳转到 action
		data : {},
		type : 'post',
		cache : false,
		dataType : 'json',
		success : function(data) {
			window.config.cdn1 = data.cdn1;
			window.config.cdn2 = data.cdn2;
		},
		error : function() {
			window.config.cdn1 = 'http://cdn1.bluestonehk.com/';
			window.config.cdn2 = 'http://cdn2.bluestonehk.com/';
		}
	});
    
}();*/
});
 var params = {}
// ajax方式上传文件
function ajaxFileUpload(selector, index,callback) {
            var _this = this;
            if (_this.params[index] < 100) {
                alert("已有图片正在上传，请等待!");
                return;
            }

            var hiddenForm = selector;
            //hiddenForm.find('img').attr('src', '');
           // hiddenForm.find('input[name="key"]').val('user/' + Math.uuid());

            var option = {
                dataType: "json",
                beforeSubmit: function() {
                    // 显示上传中
                     hiddenForm.find('span').html('上传中');
                },
                uploadProgress: function(event, position, total, percentComplete) {
                    hiddenForm.find('span').show().html('上传中' + percentComplete + '%');
                    params[index] = percentComplete;
                    params.percentComplete = percentComplete;
                },
                success: function(result) {
                    var key = result.key;
                    var img_path = result.img_path;
                    // 删除上传正面的文字
                    hiddenForm.find('span').hide();
                    // 填充已上传图片,如果返回值的img为空说明，图片没有上传成功
                    if (result.data == '0') {
                    	if (img_path.length ==  1) {//单文件上传
                    		hiddenForm.find('img').attr('src', img_path[0]);//单文件
                    	}else if (img_path.size >1 ) {//多文件上传
                    		//上传之后 界面展示多个上传的文件
                    		//hiddenForm.find('img').attr('src', img_path[0]);//单文件
                    	} else{
                    		hiddenForm.find('img').attr('src', "");//单文件
                    	}
                        
                        
                        callback&&callback(result);
                    }

                },
                error: function(error) {
                    //上传失败设置key为空
                    hiddenForm.find('input[name="key"]').val('');
                    alert(error.responseText);
                }
            };

            hiddenForm.ajaxSubmit(option);

            return false;
        }
 
//拼接图片,加host
 /*function getImgPath(path) {
 	if (window.config.cdn1) {
 		return window.config.cdn1 + path;
 	} 
 }*/