config.ButtonDir = "blue";
config.StyleUploadDir = "uploadfile/";
config.InitMode = "EDIT";
config.AutoDetectPasteFromWord = "1";
config.BaseUrl = "0"; // 路径模式：[0=相对路径，1=绝对路径，2=绝对根路径]
config.BaseHref = "/"; // 显示路径
config.AutoRemote = "0";
config.ShowBorder = "0";
config.StateFlag = "1";
config.CssDir = "office";
config.AutoDetectLanguage = "1";
config.DefaultLanguage = "en";

function showToolbar(){

	document.write ("<table border=0 cellpadding=0 cellspacing=0 width='100%' class='Toolbar' id='eWebEditor_Toolbar'><tr><td><div class=yToolbar><DIV CLASS=TBHandle></DIV><SELECT CLASS=TBGen onchange=\"format('fontname',this[this.selectedIndex].value);this.selectedIndex=0\">"+lang["FontName"]+"</SELECT><SELECT CLASS=TBGen onchange=\"format('fontsize',this[this.selectedIndex].value);this.selectedIndex=0\">"+lang["FontSize"]+"</SELECT><DIV CLASS=Btn TITLE='"+lang["Bold"]+"' onclick=\"format('bold')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/bold.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["Italic"]+"' onclick=\"format('italic')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/italic.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["UnderLine"]+"' onclick=\"format('underline')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/underline.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["StrikeThrough"]+"' onclick=\"format('StrikeThrough')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/strikethrough.gif'></DIV><DIV CLASS=TBSep></DIV><DIV CLASS=Btn TITLE='"+lang["ForeColor"]+"' onclick=\"showDialog('selcolor.htm?action=forecolor', true)\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/forecolor.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["BackColor"]+"' onclick=\"showDialog('selcolor.htm?action=backcolor', true)\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/backcolor.gif'></DIV><DIV CLASS=TBSep></DIV><DIV CLASS=Btn TITLE='"+lang["HorizontalRule"]+"' onclick=\"format('InsertHorizontalRule')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/inserthorizontalrule.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["Marquee"]+"' onclick=\"showDialog('marquee.htm', true)\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/marquee.gif'></DIV></div></td></tr><tr><td><div class=yToolbar><DIV CLASS=TBHandle></DIV><DIV CLASS=Btn TITLE='"+lang["JustifyLeft"]+"' onclick=\"format('justifyleft')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/justifyleft.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["JustifyCenter"]+"' onclick=\"format('justifycenter')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/justifycenter.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["JustifyRight"]+"' onclick=\"format('justifyright')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/justifyright.gif'></DIV><DIV CLASS=TBSep></DIV><DIV CLASS=Btn TITLE='"+lang["OrderedList"]+"' onclick=\"format('insertorderedlist')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/insertorderedlist.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["UnOrderedList"]+"' onclick=\"format('insertunorderedlist')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/insertunorderedlist.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["Indent"]+"' onclick=\"format('indent')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/indent.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["Outdent"]+"' onclick=\"format('outdent')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/outdent.gif'></DIV><DIV CLASS=TBSep></DIV><DIV CLASS=Btn TITLE='"+lang["CreateLink"]+"' onclick=\"createLink()\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/createlink.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["Unlink"]+"' onclick=\"format('UnLink')\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/unlink.gif'></DIV><DIV CLASS=TBSep></DIV><DIV CLASS=Btn TITLE='"+lang["Image"]+"' onclick=\"showDialog('img.htm', true)\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/img.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["Flash"]+"' onclick=\"showDialog('flash.htm', true)\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/flash.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["Media"]+"' onclick=\"showDialog('media.htm', true)\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/media.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["Emot"]+"' onclick=\"showDialog('emot.htm', true)\"><IMG CLASS=Ico SRC='buttonimage/" + config.ButtonDir + "/emot.gif'></DIV><DIV CLASS=TBSep></DIV><DIV CLASS=Btn TITLE='"+lang["Quote"]+"' onclick=\"insert('quote')\"><IMG CLASS=Ico SRC='buttonimage/blue/quote.gif'></DIV><DIV CLASS=Btn TITLE='"+lang["Code"]+"' onclick=\"insert('code')\"><IMG CLASS=Ico SRC='buttonimage/blue/code.gif'></DIV></div></td></tr></table>");

}
