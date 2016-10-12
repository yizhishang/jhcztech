<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<HTML>
<HEAD>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<TITLE>eWebEditor 锛�澶栭儴鏍峰紡寮曠敤鎺ュ彛绀轰緥</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<link rel='stylesheet' type='text/css' href='example.css'>
</HEAD>
<BODY>

<p><b>瀵艰埅 锛�<a href="default.jsp">绀轰緥棣栭〉</a> &gt; 澶栭儴鏍峰紡寮曠敤鎺ュ彛绀轰緥</b></p>
<p>閫氳繃浣跨敤澶栭儴鏍峰紡鍙傛暟鎺ュ彛锛屼綘鍙互鎸囧畾缂栬緫鍖虹殑鏍峰紡銆�/p>
<p>甯哥敤浜庢妸缂栬緫鍖虹殑鏍峰紡璁剧疆涓轰笌浣犵殑搴旂敤绯荤粺鐩稿悓鐨凜SS鏍峰紡鏂囦欢锛岃繖鏍蜂繚璇佷簡缂栬緫鍣ㄤ腑鏄剧ず鐨勬晥鏋滀笌鎮ㄧ殑搴旂敤绯荤粺涓緭鍑烘樉绀虹殑鏁堟灉瀹屽叏鐩稿悓銆�/p>
<p>涓嬮潰鐨勪緥瀛愶紝鎸囧畾extcss=_example/myeditorarea.css锛屼綘鍙互鍦ㄧ紪杈戝尯涓緭鍏ヤ竴浜涙枃瀛楃湅鐪嬶紝琛ㄧ幇鏁堟灉涓巑yeditorarea.css涓畾涔夌殑鏄浉鍚岀殑銆�/p>
<p>绀轰緥鏍峰紡鏂囦欢涓紝榛樿瀛椾綋涓虹孩鑹诧紝鎶婃钀界殑杈硅窛璁句负0锛屼娇寰楄緭鍏モ�鍥炶溅鈥濓紝涓庤緭鍏モ�Shift+鍥炶溅鈥濈殑鏁堟灉涓�牱銆�/p>


<FORM method="post" name="myform" action="retrieve.jsp">
<TABLE border="0" cellpadding="2" cellspacing="1">
<TR>
	<TD>缂栬緫鍐呭锛�/TD>
	<TD>
		<INPUT type="hidden" name="content1" value="&lt;p&gt;&nbsp;&lt;/p&gt;">
		<IFRAME ID="eWebEditor1" src="../ewebeditor.htm?id=content1&style=coolblue&extcss=_example/myeditorarea.css" frameborder="0" scrolling="no" width="550" height="350"></IFRAME>
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