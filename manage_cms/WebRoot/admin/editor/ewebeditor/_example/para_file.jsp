<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<HTML>
<HEAD>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<TITLE>eWebEditor 锛�涓婁紶鏂囦欢鎺ュ彛绀轰緥</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<link rel='stylesheet' type='text/css' href='example.css'>
</HEAD>
<BODY>

<p><b>瀵艰埅 锛�<a href="default.jsp">绀轰緥棣栭〉</a> &gt; 涓婁紶鏂囦欢鎺ュ彛绀轰緥</b></p>
<p>閫氳繃浣跨敤杩欎釜鎺ュ彛鍔熻兘锛屾偍鍙互鑾峰彇鍒版墍鏈夐�杩囩紪杈戝櫒涓婁紶鐨勫浘鐗囨垨鏂囦欢鐨勬枃浠跺悕鍙婅矾寰勩�</p>
<p>鍦ㄧ紪杈戝櫒涓笂浼犱竴涓枃浠舵垨鍥剧墖锛岀湅涓�笅鏁堟灉銆�/p>


<Script Language=JavaScript>
function doChange(objText, objDrop){
	if (!objDrop) return;
	var str = objText.value;
	var arr = str.split("|");
	objDrop.length=0;
	for (var i=0; i<arr.length; i++){
		objDrop.options[i] = new Option(arr[i], arr[i]);
	}
}
</Script>


<FORM method="post" name="myform" action="retrieve.jsp">
<TABLE border="0" cellpadding="2" cellspacing="1">
<TR>
	<TD>缂栬緫鍐呭锛�/TD>
	<TD>
		<INPUT type="hidden" name="content1" value="">
		<IFRAME ID="eWebEditor1" src="../ewebeditor.htm?id=content1&style=coolblue&originalfilename=myText1&savefilename=myText2&savepathfilename=myText3" frameborder="0" scrolling="no" width="550" height="350"></IFRAME>
	</TD>
</TR>
<TR>
	<TD>鍙傛暟锛歰riginalfilename</TD>
	<TD><input type=text id=myText1 style="width:200px" onchange="doChange(this,myDrop1)">&nbsp;<select id=myDrop1 size=1 style="width:200px"></select></TD>
</TR>
<TR>
	<TD>鍙傛暟锛歴avefilename</TD>
	<TD><input type=text id=myText2 style="width:200px" onchange="doChange(this,myDrop2)">&nbsp;<select id=myDrop2 size=1 style="width:200px"></select></TD>
</TR>
<TR>
	<TD>鍙傛暟锛歴avepathfilename</TD>
	<TD><input type=text id=myText3 style="width:200px" onchange="doChange(this,myDrop3)">&nbsp;<select id=myDrop3 size=1 style="width:350px"></select></TD>
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
