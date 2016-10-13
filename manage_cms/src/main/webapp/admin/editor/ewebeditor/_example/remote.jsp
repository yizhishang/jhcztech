<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<HTML>
<HEAD>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<TITLE>eWebEditor 锛�杩滅▼鏂囦欢鑷姩涓婁紶绀轰緥</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<link rel='stylesheet' type='text/css' href='example.css'>
</HEAD>
<BODY>

<p><b>瀵艰埅 锛�<a href="default.jsp">绀轰緥棣栭〉</a> &gt; 杩滅▼鏂囦欢鑷姩涓婁紶绀轰緥</b></p>
<p>婕旂ず鎿嶄綔璇存槑锛�/p>
<ul>
<li>缂栬緫鍖轰腑鐨勫浘鐗囧湴鍧�负锛歨ttp://www.ewebeditor.net/images/ewebeditor.gif
<li>鐐瑰嚮鎸夐挳<img src="images/remoteupload.gif">锛岀劧鍚庤浆鍒扳�浠ｇ爜鈥濇ā寮忕湅涓�笅锛岀紪杈戝尯鐨勫浘鐗囩殑鍦板潃宸茬粡鍒版湰鍦版湇鍔″櫒浜嗐�
<li>鎴栫偣姝よ〃鍗曠殑鈥滄彁浜も�锛屾彁浜ゅ悗锛岀敤IE鐨勨�鏌ョ湅婧愭枃浠垛�鐪嬩竴涓嬶紝鍥剧墖鐨勫湴鍧�篃鍒版湰鍦版湇鍔″櫒浜嗐�
<li>鍒癳WebEditor鎵�湁鐨勭洰褰曚笅鐨剈ploadfile鐩綍涓紝鏌ョ湅涓�笅锛屾槸涓嶆槸澶氫簡涓�釜鍥剧墖鏂囦欢锛岃繖涓枃浠跺氨鏄繙绋嬭嚜鍔ㄨ幏鍙栫殑銆�
</ul>


<script language=javascript>
// 琛ㄥ崟鎻愪氦妫�祴
function doCheck(){
	// 鍙栫紪杈戝櫒瀵硅薄锛岀劧鍚庡彲浠ヨ皟鐢ㄥ璞℃帴鍙�
	var editor1 = document.getElementById("eWebEditor1").contentWindow;


	// 妫�祴琛ㄥ崟鐨勬湁鏁堟�
	// 濡傦細鏍囬涓嶈兘涓虹┖锛屽唴瀹逛笉鑳戒负绌猴紝绛夌瓑...
	if (editor1.getHTML()=="") {
		alert("鍐呭涓嶈兘涓虹┖锛�);
		return false;
	}

	// 琛ㄥ崟鏈夋晥鎬ф娴嬪畬鍚庯紝鑷姩涓婁紶杩滅▼鏂囦欢
	// 鍑芥暟锛�remoteUpload(strEventUploadAfter)
	// 鍙傛暟锛歴trEventUploadAfter ; 涓婁紶瀹屽悗锛岃Е鍙戠殑鍑芥暟鍚嶏紝濡傛灉涓婁紶瀹屽悗涓嶉渶鍔ㄤ綔鍙笉濉弬鏁�
	editor1.remoteUpload("doSubmit()");
	return false;

}

// 琛ㄥ崟鎻愪氦锛堝綋杩滅▼涓婁紶瀹屾垚鍚庯紝瑙﹀彂姝ゅ嚱鏁帮級
function doSubmit(){
	document.myform.submit();
}
</script>


<FORM method="post" name="myform" action="retrieve.jsp" onsubmit="return doCheck();">
<TABLE border="0" cellpadding="2" cellspacing="1">
<TR>
	<TD>缂栬緫鍐呭锛�/TD>
	<TD>
		<INPUT type="hidden" name="content1" value="&lt;IMG src=&quot;http://www.ewebeditor.net/images/ewebeditor.gif&quot;&gt;">
		<IFRAME ID="eWebEditor1" src="../ewebeditor.htm?id=content1&style=coolblue" frameborder="0" scrolling="no" width="550" height="350"></IFRAME>
	</TD>
</TR>
<TR>
	<TD colspan=2 align=right>
	<INPUT type=submit value="鎻愪氦"> 
	<INPUT type=reset value="閲嶅～"> 
	<INPUT type=button value="鏌ョ湅婧愭枃浠� onclick="location.replace('view-source:'+location)"> 
	</TD>
</TR>
</TABLE>
</FORM>



</BODY>
</HTML>
