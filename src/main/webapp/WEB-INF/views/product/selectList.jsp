<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <script type="text/javascript"
            src="/js/jquery/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript" src="/js/jquery/plugins/artDialog/plugins/iframeTools.source.js"></script>
    <script type="text/javascript">
        $(function () {
            $(".selectProduct").click(function () {
                var productInfo = $(this).data("productinfo");
                $.dialog.data("productInfo", productInfo);
                $.dialog.close();
            });
        })
    </script>
    <title>PSS-商品管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<form id="searchForm" action="/product/selectList" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        名称/品牌
                        <input type="text" class="ui_input_txt02 " name="keywords" value="${qo.keywords}"/>
                        所属品牌
                        <select class="ui_select01" name="brandId">
                            <option value="-1">全部</option>
                            <c:forEach var="brand" items="${brands}">
                                <option value="${brand.id}" ${brand.id == qo.brandId ? "selected":""}>${brand.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div id="box_bottom">
                        <input type="button" value="新增" class="ui_input_btn01 btn_input" data-url="/product/input"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%"
                       align="center" border="0">
                    <tr>
                        <th width="30">
                            <input type="checkbox" id="all"/>
                        </th>
                        <th>商品图片</th>
                        <th>商品名称</th>
                        <th>商品编码</th>
                        <th>商品商品</th>
                        <th>商品成本价</th>
                        <th>商品销售价</th>
                        <th></th>
                    </tr>
                    <tbody>
                    <c:forEach var="product" items="${result.listData}">
                        <tr>
                            <td>
                                <input type="checkbox" name="IDCheck" class="acb"/>
                            </td>
                            <td><img src="${product.imagePath}" height="80px"></td>
                            <td>${product.name}</td>
                            <td>${product.sn}</td>
                            <td>${product.brandName}</td>
                            <td>${product.costPrice}</td>
                            <td>${product.salePrice}</td>
                            <td>
                                <input type="button" value="选择该商品" class="left2right selectProduct"
                                       data-productinfo='${product.productInfo}'/>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <jsp:include page="/WEB-INF/views/commons/common-page.jsp"></jsp:include>
        </div>
    </div>
</form>
</body>
</html>
