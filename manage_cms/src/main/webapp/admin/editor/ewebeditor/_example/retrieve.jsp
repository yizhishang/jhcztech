<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.*" %>

<HTML>
<HEAD>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<TITLE>eWebEditor 锛�琛ㄥ崟鎺ユ敹绀轰緥</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<link rel='stylesheet' type='text/css' href='example.css'>
</HEAD>
<BODY>

<p><b>瀵艰埅 锛�<a href="default.jsp">绀轰緥棣栭〉</a> &gt; 琛ㄥ崟鎺ユ敹绀轰緥</b></p>
<p>姝や緥婕旂ず浜嗗浣曟帴鏀跺埌琛ㄥ崟鎻愪氦杩囨潵鐨凥TML浠ｇ爜锛屽苟鏄剧ず瀹冦�</p>

<%
String sContent1 = request.getParameter("content1");

out.println("缂栬緫鍐呭濡備笅锛�br><br>" + sContent1);
out.println("<br><br><p><input type=button value=' 鍚庨� ' onclick='history.back()'></p>");
%>

</BODY>
</HTML>