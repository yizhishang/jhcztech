<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.util.regex.*" %>

<HTML>
<HEAD>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<TITLE>eWebEditor 锛�鍒嗛〉鏄剧ず澶勭悊绀轰緥</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<link rel='stylesheet' type='text/css' href='example.css'>
</HEAD>
<BODY>

<p><b>瀵艰埅 锛�<a href="default.jsp">绀轰緥棣栭〉</a> &gt; 鍒嗛〉鏄剧ず澶勭悊绀轰緥</b></p>
<p>姝や緥婕旂ず浜唀WebEditor鐨勬爣鍑嗗垎椤垫ā寮忎笅锛岀▼搴忓鏍囧噯鍒嗛〉绗︾殑澶勭悊鏂规硶銆傛偍鍙互鏌ョ湅姝ら〉绋嬪簭婧愪唬鐮侊紝浠ヤ簡瑙ｆ爣鍑嗗垎椤电缁撴瀯鍙婁娇鐢ㄦ柟娉曘�</p>


<%
// eWebEditor 鏍囧噯鍒嗛〉绗︽牸寮忓畾涔夛細
// -------------------------------------------------------------------
// <!--ewebeditor:page title="绗琋椤靛皬鏍囬"-->
// 绗琋椤垫鏂嘓TML浠ｇ爜
// <!--/ewebeditor:page-->
// -------------------------------------------------------------------





// sContent鍙橀噺锛氭墍缂栬緫鐨勫唴瀹癸紝涓�埇鏄粠鏁版嵁搴撲腑鍙栧嚭锛屼互涓嬩负妯℃嫙鏁版嵁
// sContent = rs("field")
String sContent = "<!--ewebeditor:page title=\"绗竴椤靛皬鏍囬\"-->\r\n" +
           "<style>\r\n" +
		   ".p1{font-size:14px;color:#000000;}\r\n" +
		   ".p2{font-size:16px;color:#ff0000;}\r\n" +
		   ".p3{font-size:18px;color:#0000ff;}\r\n" +
		   "</style>\r\n" +
           "<p class=p1>绗竴椤垫鏂�/p>\r\n" +
           "<!--/ewebeditor:page-->\r\n" +
		   "<!--ewebeditor:page title=\"绗簩椤靛皬鏍囬\"-->\r\n" +
           "<p class=p2>绗簩椤垫鏂�/p>\r\n" +
           "<!--/ewebeditor:page-->\r\n" +
		   "<!--ewebeditor:page title=\"绗笁椤靛皬鏍囬\"-->\r\n" +
           "<p class=p3>绗笁椤垫鏂�/p>\r\n" +
           "<!--/ewebeditor:page-->";
// sContent = "<p>鍙湁涓�〉锛�/p>"




String sPage = dealNull(request.getParameter("page"));
String[] arr = eWebEditorPagination(sContent, sPage);
String sOutputContent = arr[1];
String sOutputTitles = arr[2];

// 鏄剧ず鏍囬鍒楄〃鍙婂垎椤甸摼鎺�
if (!sOutputTitles.equals("")){
	out.print("<hr size=1>");
	out.print(sOutputTitles);
}

// 鏄剧ず姝ｆ枃
out.print("<hr size=1>");
out.print(sOutputContent);

%>

</BODY>
</HTML>

<%!

// eWebEditor 鏍囧噯鍒嗛〉绗︽牸寮忓畾涔夛細
// -------------------------------------------------------------------
// <!--ewebeditor:page title="绗琋椤靛皬鏍囬"-->
// 绗琋椤垫鏂嘓TML浠ｇ爜
// <!--/ewebeditor:page-->
// -------------------------------------------------------------------
// eWebEditor鏍囧噯鍒嗛〉绗﹀垎鏋愬鐞嗗嚱鏁扮ず鍒� 鍙緷瀹為檯闇�淇敼, 杩斿洖澶氬�鏁扮粍
// -------------------------------------------------------------------
static String[] eWebEditorPagination(String s_Content, String s_CurrPage){
	// 灏忔爣棰樺垪琛紝褰撳墠椤垫爣棰橈紝褰撳墠椤靛唴瀹�
	String s_Titles = "";
	String s_CurrTitle = "";
	String s_CurrContent = s_Content;

	// 椤垫暟锛�琛ㄧず娌℃湁鍒嗛〉
	int n_PageCount = 0;

	// 褰撳墠椤�
	int n_CurrPage = 1;

	// 褰撴湁鍒嗛〉鏃讹紝瀛樺垎椤垫鏂囧拰鏍囬鐨勬暟缁勶紝涓嬫爣浠�寮�
	ArrayList a_PageContent = new ArrayList();
	ArrayList a_PageTitle = new ArrayList();
	a_PageContent.add("");
	a_PageTitle.add("");


	// 姝ｅ垯琛ㄨ揪寮忓璞�
	Pattern p;
	Matcher m;
	int flags = Pattern.CASE_INSENSITIVE | Pattern.DOTALL;

	// 鍒嗙鍑哄唴瀹逛腑鐨凜SS鏍峰紡閮ㄥ垎锛岀劧鍚庡湪鍚勯〉涓悎骞讹紝浣垮悇鍒嗛〉鐨勬樉绀烘晥鏋滀笉鍙�
	// <style>...</style>
	String s_Style = "";
	p = Pattern.compile("<style[^>]*>([\\s\\S]+?)</style>", flags);
	m = p.matcher(s_CurrContent);
	while (m.find()){
		s_Style = "\r\n" + s_Style + m.group() + "\r\n";
	}
	if (!s_Style.equals("")){
		s_CurrContent = m.replaceAll("");
	}

	// 浣跨敤姝ｅ垯琛ㄨ揪寮忓鍒嗛〉杩涜澶勭悊
	p = Pattern.compile("<!--ewebeditor:page title=\"([^\"]*)\"-->([\\s\\S]+?)<!--/ewebeditor:page-->", flags);
	m = p.matcher(s_CurrContent);
	while (m.find()){
		n_PageCount = n_PageCount + 1;
		a_PageTitle.add(m.group(1));
		a_PageContent.add(m.group(2));
	}

	if (n_PageCount == 0){
		// 娌℃湁鍒嗛〉
		s_Titles = "";
		s_CurrContent = s_Content;
	}else{
		// 鏈夊垎椤�
		// 浠庡弬鏁板垽鏂綋鍓嶉〉鐨勬湁鏁堟�
		if (!IsInt(s_CurrPage)){
			n_CurrPage = 1;
		}else{
			n_CurrPage = Integer.parseInt(s_CurrPage);
			if (n_CurrPage < 1 || n_CurrPage > n_PageCount){
				n_CurrPage = 1;
			}
		}

		// 褰撴湁澶氫釜椤垫椂锛屾樉绀哄垎椤靛皬鏍囬鍙婂垎椤甸摼鎺�
		s_Titles = "";
		for (int i=1; i<=n_PageCount; i++) {
			if (i == n_CurrPage){
				s_Titles = s_Titles + "<li>" + i + ") " + a_PageTitle.get(i).toString() + "";
			}else{
				s_Titles = s_Titles + "<li><a href='?page=" + i + "'>" + i + ") " + a_PageTitle.get(i).toString() + "</a>";
			}
		}

		// 褰撳墠椤垫爣棰樺拰鍐呭
		s_CurrTitle = a_PageTitle.get(n_CurrPage).toString();
		s_CurrContent = s_Style + a_PageContent.get(n_CurrPage).toString();
	}

	// 杩斿洖鍊兼暟缁勶紝渚濆疄闄呴渶瑕佷慨鏀�
	String[] ret = new String[4];
	ret[1] = s_CurrContent;		//褰撳墠椤靛唴瀹�
	ret[2] = s_Titles;			//鏍囬鍒楄〃
	ret[3] = s_CurrTitle;		//褰撳墠椤垫爣棰�

	return ret;
}


static String dealNull(String str) {
	String returnstr = null;
	if (str == null) {
		returnstr = "";
	} else {
		returnstr = str;
	}
	return returnstr;
}

static Object dealNull(Object obj) {
	Object returnstr = null;
	if (obj == null){
		returnstr = (Object) ("");
	}else{
		returnstr = obj;
	}
	return returnstr;
}

static boolean IsInt(String str){
	if (str.equals("")){
		return false;
	}

	Pattern p = Pattern.compile("[^0-9]+");
	Matcher m = p.matcher(str);
	if (m.matches()){
		return false;
	}

	return true;
}

%>
