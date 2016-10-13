<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<HTML>
<HEAD>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<TITLE>eWebEditor 锛�鏍囧噯璋冪敤绀轰緥</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<link rel='stylesheet' type='text/css' href='example.css'>
</HEAD>
<BODY>

<p><b>瀵艰埅 锛�<a href="default.jsp">绀轰緥棣栭〉</a> &gt; 鏍囧噯璋冪敤绀轰緥</b></p>
<p>姝や緥婕旂ず浜唀WebEditor鐨勬爣鍑嗚皟鐢ㄦ柟娉曪紝涔熸槸鏈�父鐢ㄧ殑璋冪敤鏂规硶銆�/p>
<p>鏈牱寮忎娇鐢ㄧ郴缁熺殑榛樿鏍峰紡(coolblue)锛屾渶浣宠皟鐢ㄥ搴�50px锛屾渶浣宠皟鐢ㄩ珮搴�50px銆�/p>


<FORM method="post" name="myform" action="retrieve.jsp">
<TABLE border="0" cellpadding="2" cellspacing="1">
<TR>
	<TD>缂栬緫鍐呭锛�/TD>
	<TD>
		<%
		// 瀹氫箟鍙橀噺
		String html;
		// 璧嬪�锛屽浠庢暟鎹簱鍙栧�
		// html = rs("field")
		html = "<P align=center><FONT color=#ff0000><FONT face=\"Arial Black\" size=7><STRONG>eWeb<FONT color=#0000ff>Editor</FONT><FONT color=#000000><SUP>TM</SUP></FONT></STRONG></FONT></FONT></P><P align=right><FONT style='BACKGROUND-COLOR: #ffff00' color=#ff0000><STRONG>eWebEditor V9.0 for JSP 绠�綋涓枃鍟嗕笟鐗�/STRONG></FONT></P><P>鏈牱寮忎负绯荤粺榛樿鏍峰紡锛坈oolblue锛夛紝鏈�匠璋冪敤瀹藉害550px锛岄珮搴�50px锛�/P><P>杩樻湁涓�簺楂樼骇璋冪敤鍔熻兘鐨勪緥瀛愶紝浣犲彲浠ラ�杩囧鑸繘鍏ョず渚嬮椤垫煡鐪嬨�</P><P><B><TABLE borderColor=#ff9900 cellSpacing=2 cellPadding=3 align=center bgColor=#ffffff border=1><TBODY><TR><TD bgColor=#00ff00><STRONG>鐪嬪埌杩欎簺鍐呭锛屼笖娌℃湁閿欒鎻愮ず锛岃鏄庡畨瑁呭凡缁忔纭畬鎴愶紒</STRONG></TD></TR></TBODY></TABLE></B></P>";
		// 瀛楃杞崲锛屼富瑕侀拡瀵瑰崟鍙屽紩鍙风瓑鐗规畩瀛楃
		// 鍙湁鍦ㄧ粰缂栬緫鍣ㄨ祴鍊兼椂鎵嶆湁蹇呰浣跨敤姝ゅ瓧绗﹁浆鎹㈠嚱鏁帮紝鍏ュ簱鍙婂嚭搴撴樉绀洪兘涓嶉渶瑕佷娇鐢ㄦ鍑芥暟
		html = htmlEncode(html);
		%>

		<INPUT type="hidden" name="content1" value="<%=html%>">
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

<%!

static String htmlEncode(int i){
	if (i=='&') return "&amp;";
	else if (i=='<') return "&lt;";
	else if (i=='>') return "&gt;";
	else if (i=='"') return "&quot;";
	else return ""+(char)i;
}
	
static String htmlEncode(String st){
	StringBuffer buf = new StringBuffer();
	for (int i = 0;i<st.length();i++){
		buf.append(htmlEncode(st.charAt(i)));
	}
	return buf.toString();
}

%>