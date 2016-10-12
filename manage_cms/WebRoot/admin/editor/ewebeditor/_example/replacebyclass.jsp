<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>eWebEditor 锛�鐢ㄦ牱寮忕被鍚嶆妸鏂囨湰妗嗚嚜鍔ㄦ浛鎹负缂栬緫鍣�/title>
<meta http-equiv=Content-Type content="text/html; charset=UTF-8">
<script type="text/javascript" src="../ewebeditor.js"></script>
<link rel='stylesheet' type='text/css' href='example.css'>

<script type="text/javascript">
//鎮ㄥ彲浠ヤ娇鐢ㄤ笅闈㈢殑灞炴�鏉ヨ缃鐞嗘柟寮�
//EWEBEDITOR.ReplaceByClassName = "ewebeditor";		//鎸囧畾浣跨敤鐨勭被鍨嬶紝榛樿鏄�ewebeditor"
//EWEBEDITOR.ReplaceByClassEnabled = false;			//鎸囧畾鏄惁鍚敤姝ゅ姛鑳斤紝榛樿鏄惎鐢�
</script>

</head>
<body>

<p><b>瀵艰埅 锛�<a href="default.jsp">绀轰緥棣栭〉</a> &gt; 鐢ㄦ牱寮忕被鍚嶆妸鏂囨湰妗嗚嚜鍔ㄦ浛鎹负缂栬緫鍣�/b></p>
<p>姝や緥婕旂ず浜嗘偍鍙互閫氳繃鏍峰紡绫诲悕寰堢畝鍗曠殑鎶�lt;textarea&gt;鏇挎崲涓篹WebEditor缂栬緫鍣ㄥ疄渚嬨�濡備笅浠ｇ爜鎵�ず锛屾偍鍙渶瑕佺粰&lt;textarea&gt;鍔犱釜鎸囧畾鐨刢lass鍗冲彲銆傛偍鍙互鎸囧畾鑷繁鎯崇敤鐨勬牱寮忕被鍚嶃�璇︾粏浣跨敤鏂规硶锛岃鍙傜湅鏈〉婧愭枃浠朵唬鐮佷腑璇存槑鎴栫敤鎴锋墜鍐屻�</p>
<div class=code>&lt;textarea class=&quot;ewebeditor&quot; name=&quot;content1&quot;&gt;&lt;/textarea&gt;</div>


<form action="retrieve.asp" method="post">
<p><b>绀轰緥1锛�/b></p>
<textarea class="ewebeditor" name="content1" rows="10" cols="80">&lt;p&gt;杩欐槸&lt;strong&gt;绀轰緥1&lt;/strong&gt;銆�鎮ㄦ鍦ㄤ娇鐢�&lt;a href=&quot;http://www.ewebeditor.net/&quot;&gt;eWebEditor&lt;/a&gt;.&lt;/p&gt;</textarea>

<p><b>绀轰緥2锛�/b></p>
<textarea class="ewebeditor" name="content2" rows="10" cols="80">&lt;p&gt;杩欐槸&lt;strong&gt;绀轰緥2&lt;/strong&gt;銆�鎮ㄦ鍦ㄤ娇鐢�&lt;a href=&quot;http://www.ewebeditor.net/&quot;&gt;eWebEditor&lt;/a&gt;.&lt;/p&gt;</textarea>


<p>
	<input type="submit" value="鎻愪氦" />
</p>
</form>

</body>
</html>