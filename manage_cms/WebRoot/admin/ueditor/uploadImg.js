var _editor;

(function() {

	console.log(window.location.href);
	var paramName = $("[name=uploadImage_js]").attr("src");
	var inputId = paramName.match(new RegExp("[\?\&]" + "input_id"
					+ "=([^\&]+)", "i"));

	var data = {
		input_id : inputId[1]
	};

	$.get("/admin/ueditor/image.html", function(html) {
		$("[name=uploadImage_js]").append(html);
		var uploadHtml = template('uploadHtml', data);
		$("#" + inputId[1] + "Div").html(uploadHtml);

		_editor = UE.getEditor(textareaId[1]);
		_editor.ready(function() {
			// _editor.setDisabled();

			_editor.hide();

			// 侦听图片上传
			_editor.addListener('beforeInsertImage', function(t, arg) {

						$("#" + inputId[1]).attr("value", arg[0].src); // 将地址赋值给相应的input,只去第一张图片的路径

					});

			_editor.addListener('afterUpfile', function(t, arg) {
				$("#file").attr("value", _editor.options.filePath + arg[0].url);
			});
		});

	});
})();

// 弹出图片上传的对话框
function upImage() {
	var myImage = _editor.getDialog("insertimage");
	myImage.open();
}
