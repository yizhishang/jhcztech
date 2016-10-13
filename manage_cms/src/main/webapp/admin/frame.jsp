<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td background="images/menu_bg.gif" width="100%">
            <table border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="20"><img src="images/memu_header.gif" width="9" height="23"></td>
                    <td height="23" style="cursor:hand" onMouseOver="this.className='selectedbtn'" onMouseOut="this.className='un'">
                        <table border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td width="10"><img src="images/space.gif"></td>
                                <td align="left" valign="bottom">
                                    <a href="javascript:iframe.closeWindow()"><img src="images/16_saveClose.gif" width="16" height="16" border="0" alt="鍏抽棴"></a>
                                </td>
                                <td noWrap valign="bottom"><font color="#FFFFFF">
                                    <a href="javascript:window.close();"><font color="#FFFFFF">鍏抽棴</font></a></font>
                                </td>
                                <td width="10"><img src="images/space.gif"></td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<table width="100%" height="98%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td width="150" valign="top" background="images/left_bg.gif" align="right">
            <script src="scripts/leftbutton.js"></script>
            <script language="javascript">
                var tabs = new Array();
                tabs[0] = 'leftBtn_1';
                tabs[1] = 'leftBtn_2';
                tabs[2] = 'leftBtn_3';
            </script>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td height="16"></td>
                </tr>
            </table>
            <table id="leftBtn_1" width="140" border="0" cellspacing="0" cellpadding="0" background="images/left_btn_bg_light.gif" height="27">
                <tr>
                    <td width="22">&nbsp;</td>
                    <td width="26">
                    </td>
                    <td width="92">
                        <a href="catalogInfo.jsp?id=1643" onMouseOver="swapButton('leftBtn_1')" onMouseOut="swapButton('leftBtn_1')" onClick="clickButton('leftBtn_1')" target="iframe">鐩綍淇℃伅</a>
                    </td>
                </tr>
            </table>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td height="8"></td>
                </tr>
            </table>
            <table id="leftBtn_2" width="140" border="0" cellspacing="0" cellpadding="0" background="images/left_btn_bg_dark.gif" height="27">
                <tr>
                    <td width="22">&nbsp;</td>
                    <td width="26">
                    </td>
                    <td width="92">
                        <a href="roleRight.jsp?id=1643" onMouseOver="swapButton('leftBtn_2')" onMouseOut="swapButton('leftBtn_2')" onClick="clickButton('leftBtn_2')" target="iframe">瑙掕壊鏉冮檺</a>
                    </td>
                </tr>
            </table>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td height="8"></td>
                </tr>
            </table>
            <table id="leftBtn_3" width="140" border="0" cellspacing="0" cellpadding="0" background="images/left_btn_bg_dark.gif" height="27">
                <tr>
                    <td width="22">&nbsp;</td>
                    <td width="26">
                    </td>
                    <td width="92">
                        <a href="userRight.jsp?id=1643" onMouseOver="swapButton('leftBtn_3')" onMouseOut="swapButton('leftBtn_3')" onClick="clickButton('leftBtn_3')" target="iframe">鐢ㄦ埛鏉冮檺</a>
                    </td>
                </tr>
            </table>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td height="8"></td>
                </tr>
            </table>
        </td>
        <td>
            <iframe frameborder="0" width="100%" height="100%" src="user.action" />
        </td>
    </tr>
    <tr>
        <td height="20" colspan="2" bgcolor="365484"><img src="images/space.gif" width="1" height="1">
        </td>
    </tr>
</table>
</body>