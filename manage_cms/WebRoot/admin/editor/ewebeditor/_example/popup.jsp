<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<HTML>
<HEAD>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<TITLE>eWebEditor 锛�寮圭獥璋冪敤绀轰緥</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<link rel='stylesheet' type='text/css' href='example.css'>
</HEAD>
<BODY>

<p><b>瀵艰埅 锛�<a href="default.jsp">绀轰緥棣栭〉</a> &gt; 寮圭獥璋冪敤绀轰緥</b></p>
<p>褰撻〉闈腑琛ㄥ崟鐨勫厓绱犺緝澶氭椂锛屾鏂规硶鍙互浣块〉闈㈡洿鍔犳暣娲侊紝骞跺姞蹇〃鍗曢〉鐨勫姞杞介�搴︺�</p>
<p>鐐瑰嚮鈥淗TML缂栬緫鈥濇寜閽紝鍦ㄥ脊鍑虹獥鍙ｇ紪杈戜竴浜涘唴瀹癸紝鐒跺悗鐐光�淇濆瓨杩斿洖鈥濇寜閽紝鐪嬩竴涓嬫晥鏋溿�</p>


<script type="text/javascript">
function eWebEditorPopUp(style, field, width, height) {
	window.open("../popup.htm?style="+style+"&link="+field, "", "width="+width+",height="+height+",toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no");
}
</script>


<FORM ACTION="retrieve.jsp" METHOD="post" NAME="myform">
<TABLE border="0" cellpadding="2" cellspacing="1">
<TR>
	<TD>缂栬緫鍐呭锛�/TD>
	<TD>
		<TEXTAREA NAME="content1" COLS="50" ROWS="10" style="width:550px">&lt;i&gt;寮圭獥璋冪敤绀轰緥&lt;/i&gt;</TEXTAREA><br>
		<INPUT TYPE="BUTTON" NAME="btn" VALUE="HTML缂栬緫" ONCLICK="eWebEditorPopUp('popup', 'content1', 580, 380)">
	</TD>
</TR>
<TR>
	<TD align=right colspan=2>
	<INPUT type=submit value="鎻愪氦">
	<INPUT type=reset value="閲嶅～">
	<INPUT type=button value="鏌ョ湅婧愭枃浠� onclick="location.replace('view-source:'+location)">
	</TD>
</TR>
</TABLE>
</FORM>


</BODY>
</HTML>
