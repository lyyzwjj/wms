<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript" src="/js/artDialog/plugins/My97DatePicker/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
<title>PSS-商品管理</title>
<style>
.alt td {
	background: black !important;
}
</style>
</head>
<body>
	<form id="searchForm" action="/chart/list" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						 <div id="box_center">
						<fmt:formatDate value="${qo.beginDate}" var="beginDate" pattern="yyyy-MM-dd"/>
						<fmt:formatDate value="${qo.endDate}" var="endDate"  pattern="yyyy-MM-dd"/>
			                        开始时间<input name="beginDate" onclick="WdatePicker();" value="${beginDate}" class="ui_input_txt02 Wdate beginDate"/>~
			                        结束时间<input name="endDate" onclick="WdatePicker({
			                            	maxDate:new Date()
			                            });" value="${endDate}" class="ui_input_txt02 Wdate endDate"/>
			                        	供应商
			                        <select name="supplierId" class="ui_select01">
			                            <option value="-1">所有供应商</option>
			                            <c:forEach var="supplier" items="${suppliers}">
			                            <option value="${supplier.id}"${supplier.id == qo.supplierId ? "selected":""}>${supplier.name}</option>
			                            </c:forEach>
			                        </select>
			              				货品品牌
			                        <select name="brandId" class="ui_select01">
			                            <option value="-1">所有品牌</option>
			                            <c:forEach var="brand" items="${brands}">
			                            <option value="${brand.id}"${brand.id == qo.brandId ? "selected":""}>${brand.name}</option>
			                            </c:forEach>
			                        </select>
			              				 分组
				                        <select id="groupByType" name="groupByType" class="ui_select01">
				                                <c:forEach var="groupByType" items="${groupByTypes}">
					                            <option value="${groupByType.key}"${groupByType.key == qo.groupByType ? "selected":""}>${groupByType.value}</option>
				                                </c:forEach>
				                        </select>
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
							<th>分组类型</th>
							<th>采购总数量</th>
							<th>采购总金额</th>
						</tr>
						<tbody>
							<c:forEach var="chart" items="${charts}">
								<tr>
									<td>
										<input type="checkbox" name="IDCheck" class="acb" />
									</td>
									<td>${chart.groupByType}</td>
									<td>${chart.totalNumber}</td>
									<td>${chart.totalAmount}</td>
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
