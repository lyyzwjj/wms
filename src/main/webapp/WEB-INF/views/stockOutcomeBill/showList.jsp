<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>信息管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#btn_back").click(function () {
                window.history.back()
            })
            $("#editForm").find(":input").prop("readonly",true);
            $("#editForm").find("span").prop("readonly",true);
        })
    </script>
</head>
<body>
<form name="editForm" action="/stockOutcomeBill/saveOrUpdate" method="post" id="editForm">
    <input name="id" type="hidden" value="${stockOutcomeBill.id}">
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">出库单编辑</span>
            <div id="page_close">
                <a>
                    <img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <tr>
                    <td class="ui_text_rt" width="140">出库单编码</td>
                    <td class="ui_text_lt">
                        <input name="sn" value="${stockOutcomeBill.sn}" class="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">仓库</td>
                    <td class="ui_text_lt">
                        <select name="depot.id" class="ui_select01">
                            <c:forEach var="depot" items="${depots}">
                                <option value="${depot.id}" ${depot.id == stockOutcomeBill.depot.id ? "selected":""}>${depot.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
				<tr>
                    <td class="ui_text_rt" width="140">客户</td>
                    <td class="ui_text_lt">
                        <select name="depot.id" class="ui_select01">
                            <c:forEach var="client" items="${clients}">
                                <option value="${client.id}" ${client.id == stockOutcomeBill.client.id ? "selected":""}>${client.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td class="ui_text_rt" width="140">业务时间</td>
                    <td class="ui_text_lt">
                        <fmt:formatDate value="${stockOutcomeBill.vdate}" var="vdate" pattern="yyyy-MM-dd"></fmt:formatDate>
                        <input name="vdate" value="${vdate}" onClick="WdatePicker()" class="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">单据明细</td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="button" value="添加明细" class="ui_input_btn01 appendRow"/>
                        <table class="edit_table" cellspacing="0" cellpadding="0" border="0" style="width: auto">
                            <thead>
                            <tr>
                                <th width="10"></th>
                                <th width="200">货品</th>
                                <th width="120">品牌</th>
                                <th width="80">价格</th>
                                <th width="80">数量</th>
                                <th width="80">金额小计</th>
                                <th width="150">备注</th>
                            </tr>
                            </thead>
                            <tbody id="edit_table_body">
                            <%--如果是添加,那么直接写死一行明细--%>
                            <c:if test="${stockOutcomeBill.id==null}">
                                <tr>
                                    <td></td>
                                    <td>
                                        <input disabled="true" readonly="true" class="ui_input_txt02" tag="name"/>
                                        <img src="/images/common/search.png" class="searchproduct"/>
                                        <input type="hidden" tag="pid"/>
                                    </td>
                                    <td><span tag="brand"></span></td>
                                    <td><input tag="salePrice"
                                               class="ui_input_txt02"/></td>
                                    <td><input tag="number"
                                               class="ui_input_txt02"/></td>
                                    <td><span tag="amount"></span></td>
                                    <td><input tag="remark"
                                               class="ui_input_txt02"/></td>
                                </tr>
                            </c:if>
                            <c:if test="${stockOutcomeBill.id!=null}">
                                <c:forEach items="${stockOutcomeBill.items}" var="item">
                                    <tr>
                                        <td></td>
                                        <td>
                                            <input disabled="true" readonly="true" class="ui_input_txt02" tag="name"
                                                   value="${item.product.name}"/>
                                            <img src="/images/common/search.png" class="searchproduct"/>
                                            <input type="hidden" tag="pid" value="${item.product.id}"/>
                                        </td>
                                        <td><span tag="brand">${item.product.brandName}</span></td>
                                        <td><input tag="salePrice" value="${item.salePrice}"
                                                   class="ui_input_txt02"/></td>
                                        <td><input tag="number"
                                                   class="ui_input_txt02" value="${item.number}"/></td>
                                        <td><span tag="amount">${item.amount}</span></td>
                                        <td><input tag="remark" value="${item.remark}"
                                                   class="ui_input_txt02"/></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td class="ui_text_lt">
                        &nbsp;<input id="btn_back" type="button"  value="返回列表" class="ui_input_btn01"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</form>
</body>
</html>