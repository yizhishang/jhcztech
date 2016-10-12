<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title>eWebEditor 锛�杈撳叆妗嗚皟鐢ㄤ笂浼犳枃浠跺璇濇绀轰緥</title>
<meta http-equiv=Content-Type content="text/html; charset=UTF-8">
<link rel='stylesheet' type='text/css' href='example.css'>
</head>
<body>

<p><b>瀵艰埅 锛�<a href="default.asp">绀轰緥棣栭〉</a> &gt; 杈撳叆妗嗚皟鐢ㄤ笂浼犳枃浠跺璇濇绀轰緥</b></p>
<p>閫氳繃浣跨敤杩欎釜鍔熻兘锛屾偍鍙互鍦ㄤ换鎰忕殑杈撳叆妗嗕腑璋冪敤缂栬緫鍣ㄨ嚜甯︾殑涓婁紶鏂囦欢鍔熻兘锛屼笂浼犳枃浠跺拰娴忚鏈嶅姟鍣ㄤ笂鍘熷凡涓婁紶鐨勬枃浠讹紝骞惰繑鍥炶緭鍏ユ涓婁紶鏂囦欢鐨勮矾寰勬枃浠跺悕銆傛偍鍙互鍦ㄤ竴涓綉椤电殑浠绘剰澶氫釜鍦版柟璋冪敤锛屾敮鎸佸垎绫诲瀷涓婁紶鍙婄缉鐣ュ浘璋冪敤锛屽苟鍙湪缂栬緫鍣ㄥ悗鍙拌缃�涓�璋冪敤浠ｇ爜锛屽嵆鍙负鎮ㄧ殑缃戠珯鍔犲叆涓婁紶鏂囦欢鍔熻兘銆�/p>
<p>鐐瑰嚮涓嬮潰鐨勨�涓婁紶鈥濇寜閽紝鐪嬩竴涓嬫晥鏋滐紝涓嶅悓鐨勬寜閽厑璁镐笂浼犵殑鏂囦欢绫诲瀷鍙婃枃浠跺ぇ灏忎笉鍚屻�</p>


<!-- 绗竴姝ワ細寮曠敤鎺ュ彛js鏂囦欢 -->
<script type="text/javascript" src="../ewebeditor.js"></script>


<script type="text/javascript">

//绗簩姝ワ細鍒涘缓涓�釜缂栬緫鍣ㄥ疄渚嬶紝鐢变簬鏈緥鍙敤浜庝笂浼犳帴鍙ｏ紝鎵�互姝ゅ鏄殣钘忕殑銆傚姝ら〉宸茬粡鏈変簡鍙互涓嶇敤鍒涘缓锛屼娇鐢ㄧ幇鎴愮殑鍗冲彲銆�
//浠ヤ笅"coolblue",鍊煎彲浠ヤ緷鎹疄闄呴渶瑕佷慨鏀逛负鎮ㄧ殑鏍峰紡鍚�閫氳繃姝ゆ牱寮忕殑鍚庡彴璁剧疆鏉ヨ揪鍒版帶鍒跺厑璁镐笂浼犳枃浠剁被鍨嬪強鏂囦欢澶у皬
EWEBEDITOR.Create("eWebEditor1", {style:"coolblue",display:"none",width:"0",height:"0"});


//绗笁姝ワ細璋冪敤鎺ュ彛鏂规硶鎵撳紑涓婁紶瀵硅瘽妗�
/*
openUploadDialog 鏂规硶
鍙傛暟璇存槑锛�
type: 涓婁紶鏂囦欢绫诲瀷锛屽彲鐢ㄥ�涓�image","flash","media","file"
	image: 鍥剧墖
	media: 濯掍綋
	flash: Flash
	file: 闄勪欢

mode: 涓婁紶鎺ュ彛瀵硅瘽妗嗘ā寮�
	0:甯歌妯″紡
	1:甯歌璁剧疆+鍗曟枃浠朵笂浼�
	2:甯歌璁剧疆+澶氭枃浠朵笂浼�榛樿)

savepathfilename : 鏂囦欢涓婁紶鍚庯紝鐢ㄤ簬鎺ユ敹涓婁紶鏂囦欢璺緞鏂囦欢鍚嶇殑琛ㄥ崟鍚嶏紝杩斿洖鍖呭惈璺緞鐨勬枃浠跺悕
savefilename     : 杩斿洖涓婁紶鏂囦欢鐨勬枃浠跺悕
originalfilename : 杩斿洖鍘熸枃浠跺悕

returnflag : 杩斿洖鍊兼柟寮忔爣蹇�
	1: 杈撳叆妗嗗缁堝彧鏈夋渶鍚庝竴娆′笂浼犵殑鏂囦欢鍚�
	2: (榛樿)鏀寔澶氫釜鏂囦欢锛屽涓笂浼犳垨澶氭鎿嶄綔鍚庯紝杈撳叆妗嗕腑淇濈暀澶氫釜鏂囦欢锛屽涓枃浠跺鈥渱鈥濆垎闅斻�
*/


function DoClickUpload(s_Flag){
	var editor = EWEBEDITOR.Instances["eWebEditor1"];
	if (!editor){
		return;
	}

	switch(s_Flag){
	case 'image':
		editor.openUploadDialog({
			type : 'image',
			mode : '2',
			savepathfilename : 'd_image_savepath',
			savefilename : 'd_image_savefile',
			originalfilename : 'd_image_original',
			returnflag : '2'
		});
		break;
	case 'flash':
		editor.openUploadDialog({
			type : 'flash',
			mode : '2',
			savepathfilename : 'd_flash_savepath',
			savefilename : 'd_flash_savefile',
			originalfilename : 'd_flash_original',
			returnflag : '2'
		});
		break;
	case 'media':
		editor.openUploadDialog({
			type : 'media',
			mode : '2',
			savepathfilename : 'd_media_savepath',
			savefilename : 'd_media_savefile',
			originalfilename : 'd_media_original',
			returnflag : '2'
		});
		break;
	case 'file':
		editor.openUploadDialog({
			type : 'file',
			mode : '2',
			savepathfilename : 'd_file_savepath',
			savefilename : 'd_file_savefile',
			originalfilename : 'd_file_original',
			returnflag : '2'
		});
		break;
	}

}

</script>


<form method="post" name="myform" action="">

1. 姝ょず渚嬪厑璁镐笂浼犲浘鐗囩被鍨嬫枃浠讹細<br>
涓婁紶鏂囦欢锛�input type="text" id="d_image_savepath" size="50"> 
淇濆瓨鏂囦欢鍚嶏細<input type="text" id="d_image_savefile" size="20"> 
婧愭枃浠跺悕锛�input type="text" id="d_image_original" size="20"> 
<input type="button" value="涓婁紶鍥剧墖..." onclick="DoClickUpload('image')">
<br><br>

2. 姝ょず渚嬪厑璁镐笂浼燜lash绫诲瀷鏂囦欢锛�br>
涓婁紶鏂囦欢锛�input type="text" id="d_flash_savepath" size="50"> 
淇濆瓨鏂囦欢鍚嶏細<input type="text" id="d_flash_savefile" size="20"> 
婧愭枃浠跺悕锛�input type="text" id="d_flash_original" size="20"> 
<input type="button" value="涓婁紶Flash..." onclick="DoClickUpload('flash')">
<br><br>

3. 姝ょず渚嬪厑璁镐笂浼犲獟浣撶被鍨嬫枃浠讹細<br>
涓婁紶鏂囦欢锛�input type="text" id="d_media_savepath" size="50"> 
淇濆瓨鏂囦欢鍚嶏細<input type="text" id="d_media_savefile" size="20"> 
婧愭枃浠跺悕锛�input type="text" id="d_media_original" size="20"> 
<input type="button" value="涓婁紶濯掍綋..." onclick="DoClickUpload('media')">
<br><br>

4. 姝ょず渚嬪厑璁镐笂浼犻檮浠剁被鍨嬫枃浠讹細<br>
涓婁紶鏂囦欢锛�input type="text" id="d_file_savepath" size="50"> 
淇濆瓨鏂囦欢鍚嶏細<input type="text" id="d_file_savefile" size="20"> 
婧愭枃浠跺悕锛�input type="text" id="d_file_original" size="20"> 
<input type="button" value="涓婁紶鏂囦欢..." onclick="DoClickUpload('file')">
<br><br>

<input type=button value="鏌ョ湅婧愭枃浠� onclick="location.replace('view-source:'+location)"> 

</form>

</body>
</html>
