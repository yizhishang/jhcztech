<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<HTML>
<HEAD>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<TITLE>eWebEditor 锛�瀹㈡埛绔疉PI绀轰緥</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<link rel='stylesheet' type='text/css' href='example.css'>
</HEAD>
<BODY>

<p><b>瀵艰埅 锛�<a href="default.jsp">绀轰緥棣栭〉</a> &gt; 瀹㈡埛绔疉PI绀轰緥</b></p>
<p>鎮ㄥ彲浠ョ敤eWebEditor鎻愪緵鐨勫鎴风API锛屽缂栬緫鍣ㄦ墽琛岄珮绾ф搷浣溿�鏇村API锛岃鍙傝寮�彂鎵嬪唽銆�/p>



<FORM method="post" name="myform" action="retrieve.jsp">
<TABLE border="0" cellpadding="2" cellspacing="1">
<TR>
	<TD>缂栬緫鍐呭锛�/TD>
	<TD>
		<INPUT type="hidden" name="content1" value="&lt;p&gt;eWebEditor - 鍦ㄧ嚎HTML缂栬緫鍣紝HTML鍦ㄧ嚎缂栬緫濂藉府鎵�lt;/p&gt;">
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
<TR>
	<TD>HTML浠ｇ爜锛�/TD>
	<TD><TEXTAREA cols=50 rows=5 id=myTextArea style="width:550px">鐐瑰嚮鈥滃彇鍊尖�鎸夐挳锛岀湅涓�笅鏁堟灉锛�/TEXTAREA></TD>
</TR>
<TR>
	<TD colspan=2 align=right>

<script type="text/javascript">
function DoAPI(s_Flag){
	var o_Editor = document.getElementById("eWebEditor1").contentWindow;
	var o_Text = document.getElementById("myTextArea");
	switch(s_Flag){
	case "gethtml":
		o_Text.value = o_Editor.getHTML();
		break;
	case "gettext":
		o_Text.value = o_Editor.getText();
		break;
	case "sethtml":
		o_Editor.setHTML("<b>Hello My World!</b>");
		break;
	case "inserthtml":
		o_Editor.insertHTML("This is insertHTML function!");
		break;
	case "appendhtml":
		o_Editor.appendHTML("This is appendHTML function!");
		break;
	case "code":
		o_Editor.setMode("CODE");
		break;
	case "edit":
		o_Editor.setMode("EDIT");
		break;
	case "text":
		o_Editor.setMode("TEXT");
		break;
	case "view":
		o_Editor.setMode("VIEW");
		break;
	case "getcount0":
		alert(o_Editor.getCount(0));
		break;
	case "getcount1":
		alert(o_Editor.getCount(1));
		break;
	case "getcount2":
		alert(o_Editor.getCount(2));
		break;
	case "getcount3":
		alert(o_Editor.getCount(3));
		break;
	case "readonly1":
		o_Editor.setReadOnly("1");
		break;
	case "readonly2":
		o_Editor.setReadOnly("2");
		break;
	case "readonly0":
		o_Editor.setReadOnly("");
		break;

	}

}

</script>

		<INPUT type=button value="鍙栧�(HTML)" onclick="DoAPI('gethtml')" class=btn> 
		<INPUT type=button value="鍙栧�(绾枃鏈�" onclick="DoAPI('gettext')" class=btn> 
		<INPUT type=button value="璧嬪�" onclick="DoAPI('sethtml')" class=btn>
		<INPUT type=button value="褰撳墠浣嶇疆鎻掑叆" onclick="DoAPI('inserthtml')" class=btn>
		<INPUT type=button value="灏鹃儴杩藉姞" onclick="DoAPI('appendhtml')" class=btn>
		<br>
		<INPUT type=button value="浠ｇ爜鐘舵�" onclick="DoAPI('code')" class=btn>
		<INPUT type=button value="璁捐鐘舵�" onclick="DoAPI('edit')" class=btn>
		<INPUT type=button value="鏂囨湰鐘舵�" onclick="DoAPI('text')" class=btn>
		<INPUT type=button value="棰勮鐘舵�" onclick="DoAPI('view')" class=btn>
		<br>
		<INPUT type=button value="鑻辨枃瀛楁暟" onclick="DoAPI('getcount0')" class=btn>
		<INPUT type=button value="涓枃瀛楁暟" onclick="DoAPI('getcount1')" class=btn>
		<INPUT type=button value="涓嫳鏂囧瓧鏁�涓枃鍔�)" onclick="DoAPI('getcount2')" class=btn>
		<INPUT type=button value="涓嫳鏂囧瓧鏁�涓枃鍔�)" onclick="DoAPI('getcount3')" class=btn>	
		<br>
		<INPUT type=button value="鍙[妯″紡1]" onclick="DoAPI('readonly1')" class=btn>
		<INPUT type=button value="鍙[妯″紡2]" onclick="DoAPI('readonly2')" class=btn>
		<INPUT type=button value="鍙栨秷鍙" onclick="DoAPI('readonly0')" class=btn>

	</TD>
</TR>
</TABLE>
</FORM>


</BODY>
</HTML>