<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>eWebEditor 锛�鐢↗avascript鎶婃枃鏈鎴朌IV鏇挎崲涓虹紪杈戝櫒</title>
<meta http-equiv=Content-Type content="text/html; charset=UTF-8">
<script type="text/javascript" src="../ewebeditor.js"></script>
<link rel='stylesheet' type='text/css' href='example.css'>

<script type="text/javascript">
// 鍑芥暟锛欵WEBEDITOR.Replace(s_Id, o_Config)
// 鍙傛暟锛歴_Id     : 瀛楃鍨嬶紝瑕佹浛鎹㈢殑Textarea鐨刬d鎴杗ame, Div鐨刬d
//       o_Config : 瀵硅薄鍨嬶紝鎸囧畾缂栬緫鍣ㄩ厤缃紝鍙�鍙傛暟銆傚鏈缃垯鎸夐粯璁�style=coolblue,width=100%,height=350


//鍒犻櫎缂栬緫鍣�
function RemoveEditor(){
	if (!EWEBEDITOR.Instances["content1"]){return;}

	EWEBEDITOR.Instances["content1"].Remove();
	EWEBEDITOR.Instances["div1"].Remove();
}

//鏇挎崲缂栬緫鍣�
function ReplaceEditor(){
	if (EWEBEDITOR.Instances["content1"]){return;}

	EWEBEDITOR.Replace("content1", {style:"coolblue",width:"550",height:"300"} );
	EWEBEDITOR.Replace("div1");
}
</script>

</head>
<body>

<p><b>瀵艰埅 锛�<a href="default.jsp">绀轰緥棣栭〉</a> &gt; 鐢↗avascript鎶婃枃鏈鎴朌IV鏇挎崲涓虹紪杈戝櫒</b></p>
<p>姝や緥婕旂ず浜嗗浣曠敤Javascript浠ｇ爜鎶�&lt;TEXTAREA&gt; 鎴�&lt;INPUT&gt; 鎴�&lt;DIV&gt; 鏇挎崲涓�eWebEditor 缂栬緫鍣ㄥ疄渚嬨�浠呬粎闇�濡備笅涓�绠�崟浠ｇ爜锛屾偍灏卞彲瀹炵幇鏂囨湰妗嗗埌缂栬緫鍣ㄧ殑杞崲銆傝缁嗕娇鐢ㄦ柟娉曪紝璇峰弬鐪嬫湰椤垫簮鏂囦欢浠ｇ爜涓鏄庢垨鐢ㄦ埛鎵嬪唽銆�/p>
<div class=code>EWEBEDITOR.Replace(&quot;TextareaOrInputOrDiv_id&quot;);</div>
<p>绀轰緥鎿嶄綔璇存槑锛氬綋椤甸潰杞藉叆鏃讹紝鍙互鐪嬪埌鏈変袱涓紪杈戝櫒锛屽叾瀹炲湪浠ｇ爜涓紝涓�釜鏄�ltTEXTAREA&gt;锛屼竴涓槸&lt;DIV&gt;锛屾槸杞藉叆鏃剁敤Javascript浠ｇ爜鏇挎崲鐨勩�鎮ㄥ彲浠ョ偣鍑烩�鍒犻櫎鈥濇寜閽紝鐪嬪師鏍枫�鐐瑰嚮鈥滄浛鎹⑩�鎸夐挳鍥炵紪杈戝櫒鐣岄潰銆傚悓鏃讹紝褰撳垹闄ゆ椂锛屾偍鍦ㄧ紪杈戝櫒涓紪杈戠殑鍐呭灏嗚繑鍥炲埌鍘熷璞′腑銆�/p>

<p>
	<input type="button" value="鍒犻櫎缂栬緫鍣� onclick="RemoveEditor()">
	<input type="button" value="鏇挎崲缂栬緫鍣� onclick="ReplaceEditor()">
</p>

<p><b>绀轰緥1锛�/b>鏇挎崲&lt;TEXTAREA&gt;锛屾寚瀹氱紪杈戝櫒閰嶇疆(style=coolblue,width=550,height=300)</p>
<textarea cols="80" name="content1" rows="10">&lt;p&gt;杩欐槸&lt;strong&gt;鏇挎崲TEXTAREA&lt;/strong&gt;銆�鎮ㄦ鍦ㄤ娇鐢�&lt;a href=&quot;http://www.ewebeditor.net/&quot;&gt;eWebEditor&lt;/a&gt;.&lt;/p&gt;</textarea>
<script type="text/javascript">
	// 鎮ㄤ篃鍙互鍦�window.onload 浜嬩欢涓鐞�
	// 鏇挎崲 <textarea id="content1"> 鎴�<textarea name="content1"> 涓虹紪杈戝櫒瀹炰緥
	EWEBEDITOR.Replace("content1", {style:"coolblue",width:"550",height:"300"} );
</script>

<p><b>绀轰緥2锛�/b>鏇挎崲&lt;DIV&gt;锛屼娇鐢ㄩ粯璁ら厤缃�style=coolblue,width=100%,height=350)</p>
<div id="div1" rows="10"><p>杩欐槸<strong>鏇挎崲DIV</strong>銆�鎮ㄦ鍦ㄤ娇鐢�<a href="http://www.ewebeditor.net/">eWebEditor</a>.</p></div>
<script type="text/javascript">
	// 鎮ㄤ篃鍙互鍦�window.onload 浜嬩欢涓鐞�
	// 鏇挎崲 <div id="div1"> 涓虹紪杈戝櫒瀹炰緥
	EWEBEDITOR.Replace("div1");
</script>

</body>
</html>