<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<c:if test="${data.page.totalPages > 1}">
    <table style="border-collapse: collapse; border-top-width: 0" border="1" width="100%" cellspacing="0" cellpadding="0" bgcolor="#000000" bordercolor="#BFBFBF">
        <tr>
            <td align="right" style="border-top-style: none; border-top-width: medium">
                <table border="0" width="100%" cellspacing="0" cellpadding="0">
                    <tr class="ListTableSum">
                        <td style="PADDING-TOP: 3px;padding-left:4px;" valign="middle" height="22" align="left">&nbsp;
                        </td>
                        <td style="PADDING-TOP: 3px" valign="middle" height="22" align="right">
                            &nbsp;第<c:out value="${data.page.currentPage}" />页
                            每页<c:out value="${data.page.numPerPage}" />条记录
                            共<c:out value="${data.page.totalPages}" />页
                            <c:out value="${data.page.totalRows}" />条记录&nbsp;
                        </td>
                        <td width="165" valign="middle" align="center" style="PADDING-TOP: 3px">
                            <c:if test="${data.page.currentPage == 1}">
                                <span class="OperateIcon1" title="翻到第一页">
                                  <img border="0" src="images/IconFirstPage1.gif" width="16" height="16">
                                </span>
                            </c:if>
                            <c:if test="${data.page.currentPage > 1}">
                                <span class="OperateIcon" title="翻到第一页" onclick="javascript:toPage(1)">
                                  <img border="0" src="images/IconFirstPage.gif" width="16" height="16">
                                </span>
                            </c:if>
                            <c:if test="${data.page.currentPage <= 5}">
                                <span class="OperateIcon1" title="前翻五页">
                                  <img border="0" src="images/IconLast5Page1.gif" width="16" height="16">
                                </span>
                            </c:if>
                            <c:if test="${data.page.currentPage > 5}">
                                <span class="OperateIcon" title="前翻五页" onclick="javascript:toPage(<c:out value='${data.page.currentPage -5}'/>)">
                                  <img border="0" src="images/IconLast5Page.gif" width="16" height="16">
                                </span>
                            </c:if>
                            <c:if test="${data.page.currentPage == 1}">
                                <span class="OperateIcon1" title="前翻一页">
                                  <img border="0" src="images/IconLastPage1.gif" width="16" height="16">
                                </span>
                            </c:if>
                            <c:if test="${data.page.currentPage > 1}">
                                <span class="OperateIcon" title="前翻一页" onclick="javascript:toPage(<c:out value='${data.page.currentPage -1}'/>)">
                                  <img border="0" src="images/IconLastPage.gif" width="16" height="16">
                                </span>
                            </c:if>
                            <c:if test="${data.page.currentPage == data.page.totalPages}">
                              <span class="OperateIcon" title="后翻一页">
                                <img border="0" src="images/IconNextPage1.gif" width="16" height="16">
                              </span>
                            </c:if>
                            <c:if test="${data.page.currentPage < data.page.totalPages}">
                              <span class="OperateIcon" title="后翻一页" onclick="javascript:toPage(<c:out value='${data.page.currentPage +1}'/>)">
                                <img border="0" src="images/IconNextPage.gif" width="16" height="16">
                              </span>
                            </c:if>
                            <c:if test="${data.page.currentPage >= data.page.totalPages-5}">
                              <span class="OperateIcon1" title="后翻五页">
                                <img border="0" src="images/IconNext5Page1.gif" width="16" height="16">
                              </span>
                            </c:if>
                            <c:if test="${data.page.currentPage < data.page.totalPages-5}">
                              <span class="OperateIcon" title="后翻五页" onclick="javascript:toPage(<c:out value='${data.page.currentPage +5}'/>)">
                                <img border="0" src="images/IconNext5Page.gif" width="16" height="16">
                              </span>
                            </c:if>
                            <c:if test="${data.page.currentPage == data.page.totalPages}">
                                <span class="OperateIcon1" title="翻到末尾页">
                                  <img border="0" src="images/IconLatestPage1.gif" width="16" height="16">
                                </span>
                            </c:if>
                            <c:if test="${data.page.currentPage < data.page.totalPages}">
                                <span class="OperateIcon" title="翻到末尾页" onclick="javascript:toPage(<c:out value='${data.page.totalPages}'/>)">
                                  <img border="0" src="images/IconLatestPage.gif" width="16" height="16">
                                </span>
                            </c:if>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</c:if>