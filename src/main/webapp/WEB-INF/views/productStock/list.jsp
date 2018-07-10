<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
<script type="text/javascript"
	src="/js/jquery/plugins/artDialog/jquery.artDialog.js?skin=green"></script>
<title>PSS-即时库存报表</title>
<style>
.alt td {
	background: black !important;
}
</style>
</head>
<body>
	<form id="searchForm" action="/productStock/list" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							货品名称或编号
							<input type="text" class="ui_input_txt02 " name="keywords" value="${qo.keywords}"/>
							所在仓库
							<select class="ui_select01" name="depotId">
								<option value="-1">全部</option>
								<c:forEach var="depot" items="${depots}">
									<option value="${depot.id}" ${depot.id == qo.depotId ? "selected":""}>${depot.name}</option>
								</c:forEach>
							</select>
							货品品牌
							<select class="ui_select01" name=brandName>
								<option value="">全部</option>
								<c:forEach var="brand" items="${brands}">
									<option value="${brand.name}" ${brand.name == qo.brandName ? "selected":""}>${brand.name}</option>
								</c:forEach>
							</select>
							库存阈值
							<input type="text" class="ui_input_txt02 " name="number" value="${qo.number}"/>
						</div>
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
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
								<input type="checkbox" id="all" />
							</th>
							<th>仓库</th>
							<th>商品编码</th>
							<th>商品名称</th>
							<th>品牌</th>
							<th>库存数量</th>
							<th>成本</th>
							<th>库存汇总</th>
							<th></th>
						</tr>
						<tbody>
							<c:forEach var="productStock" items="${productStocks}">
								<tr>
									<td>
										<input type="checkbox" name="IDCheck" class="acb" />
									</td>
									<td>${productStock.depot.name}</td>
									<td>${productStock.product.sn}</td>
									<td>${productStock.product.name}</td>
									<td>${productStock.product.brandName}</td>
									<td>${productStock.storeNumber}</td>
									<td>${productStock.price}</td>
									<td>${productStock.amount}</td>
									<td>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</form>
</body>
</html>
