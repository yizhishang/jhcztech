config.ButtonDir = "gray";
config.StyleUploadDir = "uploadfile/";
config.InitMode = "EDIT";
config.AutoDetectPasteFromWord = "1";
config.BaseUrl = "1";
config.BaseHref = "";
config.AutoRemote = "1";
config.ShowBorder = "0";
config.StateFlag = "1";
config.CssDir = "blue";
config.AutoDetectLanguage = "1";
config.DefaultLanguage = "en";

function showToolbar(){

	document.write ("<table border=0 cellpadding=0 cellspacing=0 width='100%' class='Toolbar' id='eWebEditor_Toolbar'><tr><td><div class=yToolbar><DIV CLASS=TBHandle></DIV><SELECT CLASS=TBGen onchange=\"format('FormatBlock',this[this.selectedIndex].value);this.selectedIndex=0\">"+lang["FormatBlock"]+"</SELECT><SELECT CLASS=TBGen onchange=\"format('fontname',this[this.selectedIndex].value);this.selectedIndex=0\">"+lang["FontName"]+"</SELECT><SELECT CLASS=TBGen onchange=\"format('fontsize',this[this.selectedIndex].value);this.selectedIndex=0\">"+lang["FontSize"]+"</SELECT><SELECT CLASS=TBGen onchange=\"doZoom(this[this.selectedIndex].value)\"><option value='10'>10%</option><option value='25'>25%</option><option value='50'>50%</option><option value='75'>75%</option><option value='100' selected>100%</option><option value='150'>150%</option><option value='200'>200%</option><option value='500'>500%</option></SELECT><DIV CLASS=Btn TITLE='"+lang["Bold"]+"' onclick=\"format('bold')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/bold.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["Italic"]+"' onclick=\"format('italic')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/italic.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["UnderLine"]+"' onclick=\"format('underline')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/underline.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["StrikeThrough"]+"' onclick=\"format('StrikeThrough')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/strikethrough.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["SuperScript"]+"' onclick=\"format('superscript')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/superscript.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["SubScript"]+"' onclick=\"format('subscript')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/subscript.gif'></DIV><DIV CLASS=TBSep></DIV><DIV CLASS=Btn TITLE='"+lang["JustifyLeft"]+"' onclick=\"format('justifyleft')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/justifyleft.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["JustifyCenter"]+"' onclick=\"format('justifycenter')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/justifycenter.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["JustifyRight"]+"' onclick=\"format('justifyright')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/justifyright.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["JustifyFull"]+"' onclick=\"format('JustifyFull')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/justifyfull.gif'></DIV></div></td></tr><tr><td><div class=yToolbar><DIV CLASS=TBHandle></DIV><DIV CLASS=Btn TITLE='"+lang["Cut"]+"' onclick=\"format('cut')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/cut.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["Copy"]+"' onclick=\"format('copy')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/copy.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["Paste"]+"' onclick=\"format('paste')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/paste.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["PasteText"]+"' onclick=\"PasteText()\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/pastetext.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["PasteWord"]+"' onclick=\"PasteWord()\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/pasteword.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["FindReplace"]+"' onclick=\"findReplace()\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/findreplace.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["Delete"]+"' onclick=\"format('delete')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/delete.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["RemoveFormat"]+"' onclick=\"format('RemoveFormat')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/removeformat.gif'></DIV><DIV CLASS=TBSep></DIV><DIV CLASS=Btn TITLE='"+lang["UnDo"]+"' onclick=\"goHistory(-1)\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/undo.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["ReDo"]+"' onclick=\"goHistory(1)\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/redo.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["SelectAll"]+"' onclick=\"format('SelectAll')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/selectall.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["UnSelect"]+"' onclick=\"format('Unselect')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/unselect.gif'></DIV><DIV CLASS=TBSep></DIV><DIV CLASS=Btn TITLE='"+lang["OrderedList"]+"' onclick=\"format('insertorderedlist')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/insertorderedlist.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["UnOrderedList"]+"' onclick=\"format('insertunorderedlist')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/insertunorderedlist.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["Indent"]+"' onclick=\"format('indent')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/indent.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["Outdent"]+"' onclick=\"format('outdent')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/outdent.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["ParagraphAttr"]+"' onclick=\"paragraphAttr()\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/paragraph.gif'></DIV><DIV CLASS=TBSep></DIV><DIV CLASS=Btn TITLE='"+lang["ForeColor"]+"' onclick=\"showDialog('selcolor.htm?action=forecolor', true)\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/forecolor.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["BgColor"]+"' onclick=\"showDialog('selcolor.htm?action=bgcolor', true)\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/bgcolor.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["BackColor"]+"' onclick=\"showDialog('selcolor.htm?action=backcolor', true)\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/backcolor.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["BackImage"]+"' onclick=\"showDialog('backimage.htm', true)\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/bgpic.gif'></DIV><DIV CLASS=TBSep></DIV><DIV CLASS=Btn TITLE='"+lang["absolutePosition"]+"' onclick=\"absolutePosition()\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/abspos.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["zIndexForward"]+"' onclick=\"zIndex('forward')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/forward.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["zIndexBackward"]+"' onclick=\"zIndex('backward')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/backward.gif'></DIV></div></td></tr><tr><td><div class=yToolbar><DIV CLASS=TBHandle></DIV><DIV CLASS=Btn TITLE='"+lang["Image"]+"' onclick=\"showDialog('img.htm', true)\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/img.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["Flash"]+"' onclick=\"showDialog('flash.htm', true)\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/flash.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["Media"]+"' onclick=\"showDialog('media.htm', true)\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/media.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["File"]+"' onclick=\"showDialog('file.htm', true)\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/file.gif'></DIV><DIV CLASS=TBSep></DIV><DIV CLASS=Btn TITLE='"+lang["TableMenu"]+"' onclick=\"showToolMenu('table')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/tablemenu.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["FormMenu"]+"' onclick=\"showToolMenu('form')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/formmenu.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["ShowBorders"]+"' onclick=\"showBorders()\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/showborders.gif'></DIV><DIV CLASS=TBSep></DIV><DIV CLASS=Btn TITLE='"+lang["Fieldset"]+"' onclick=\"showDialog('fieldset.htm', true)\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/fieldset.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["Iframe"]+"' onclick=\"showDialog('iframe.htm', true)\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/iframe.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["HorizontalRule"]+"' onclick=\"format('InsertHorizontalRule')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/inserthorizontalrule.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["Marquee"]+"' onclick=\"showDialog('marquee.htm', true)\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/marquee.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["CreateLink"]+"' onclick=\"createLink()\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/createlink.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["Anchor"]+"' onclick=\"showDialog('anchor.htm', true);\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/anchor.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["Map"]+"' onclick=\"mapEdit()\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/map.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["Unlink"]+"' onclick=\"format('UnLink')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/unlink.gif'></DIV><DIV CLASS=TBSep></DIV><DIV CLASS=Btn TITLE='"+lang["Symbol"]+"' onclick=\"showDialog('symbol.htm', true)\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/symbol.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["Emot"]+"' onclick=\"showDialog('emot.htm', true)\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/emot.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["Excel"]+"' onclick=\"insert('excel')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/excel.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["Art"]+"' onclick=\"showDialog('art.htm')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/art.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["NowDate"]+"' onclick=\"insert('nowdate')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/date.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["NowTime"]+"' onclick=\"insert('nowtime')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/time.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["Quote"]+"' onclick=\"insert('quote')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/quote.gif'></DIV><DIV CLASS=TBSep></DIV>");

	if (sFullScreen=="1"){
		document.write ("<DIV CLASS=Btn TITLE='"+lang["Minimize"]+"' onclick=\"parent.Minimize()\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/minimize.gif'></DIV>");
	}else{
		document.write ("<DIV CLASS=Btn TITLE='"+lang["Maximize"]+"' onclick=\"Maximize()\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/maximize.gif'></DIV>");
	}

	document.write ("<DIV CLASS=Btn TITLE='"+lang["About"]+"' onclick=\"showDialog('about.htm')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/about.gif'></DIV></div></td></tr></table>");

}