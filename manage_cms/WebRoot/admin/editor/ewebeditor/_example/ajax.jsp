<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>eWebEditor 锛�Ajax搴旂敤涔嬪垱寤哄拰鍒犻櫎缂栬緫鍣ㄥ疄渚�/title>
<meta http-equiv=Content-Type content="text/html; charset=UTF-8">
<script type="text/javascript" src="../ewebeditor.js"></script>
<link rel='stylesheet' type='text/css' href='example.css'>


<script type="text/javascript">
var editor, html;
function CreateEditor(){
	if (editor){return;}

	//鍦�<div id="div_editor1"> 涓垱寤轰竴涓紪杈戝櫒瀹炰緥锛屽苟璁剧疆濂圭殑鍒濆鍊间负 html銆�
	editor = EWEBEDITOR.Append("div_editor1", {style:"coolblue",width:"550",height:"350"}, html);
}

function RemoveEditor(){
	if (!editor){return;}

	//鎺ユ敹缂栬緫鍣ㄧ殑鍐呭銆傚湪涓�釜Ajax搴旂敤涓紝鍙互鎶婃暟鎹彂鍒版湇鍔″櫒涓婏紝鎴栫敤浜庡叾瀹冩偍闇�鐨勫湴鏂广�
	//鎮ㄤ篃鍙互鐢�var editor=EWEBEDITOR.Instances["div_editor1"]; 鏉ヨ闂凡鍒涘缓缂栬緫鍣ㄧ殑鎺ュ彛瀵硅薄銆�
	document.getElementById("div_content1").innerHTML = html = editor.getHTML();

	//鍒犻櫎缂栬緫鍣�
	editor.Remove();
	editor = null;
}
</script>

</head>
<body>

<p><b>瀵艰埅 锛�<a href="default.jsp">绀轰緥棣栭〉</a> &gt; Ajax搴旂敤涔嬪垱寤哄拰鍒犻櫎缂栬緫鍣ㄥ疄渚�/b></p>
<p>姝や緥婕旂ず浜嗗浣曞垱寤哄拰鍒犻櫎 eWebEditor 缂栬緫鍣ㄥ疄渚嬨�缂栬緫鍣ㄥ垹闄ゅ悗锛屾偍鎵�紪杈戠殑鍐呭灏嗘樉绀哄湪涓�釜 &lt;div&gt; 涓�鍏充簬璇︾粏鐨勪娇鐢ㄦ柟娉曪紝璇峰弬鐪嬭繖椤电殑婧愭枃浠躲�</p>
<p>鎿嶄綔璇存槑锛氱偣鍑烩�鍒涘缓鈥濇寜閽垱寤轰竴涓�eWebEditor 瀹炰緥锛屽湪缂栬緫鍣ㄤ腑杈撳叆涓�簺鍐呭锛岀劧鍚庣偣鍑烩�鍒犻櫎鈥濇寜閽紝鎵�紪杈戠殑鍐呭灏嗘樉绀哄湪涓�釜 &lt;div&gt; 涓�</p>

<p>
	<input type="button" value="鍒涘缓缂栬緫鍣� onclick="CreateEditor()">
	<input type="button" value="鍒犻櫎缂栬緫鍣� onclick="RemoveEditor()">
</p>

<!-- 缂栬緫鍣ㄥ皢鎻掑叆杩欎釜div涓� -->
<div id="div_editor1"></div>

<!-- 鏄剧ず缂栬緫鐨勫唴瀹� -->
<hr>
<p><b>缂栬緫鐨勫唴瀹癸細</b></p>
<div id="div_content1"></div>

</body>
</html>