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
<title>PSS-部门管理</title>
<style>
.alt td {
	background: black !important;
}
</style>
</head>
<body>
	<form id="searchForm" action="/brand/list" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_bottom">
							<input type="button" value="新增" class="ui_input_btn01 btn_input" data-url="/brand/input"/>
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
							<th>品牌编号</th>
							<th>品牌名称</th>
							<th>品牌编码</th>
							<th></th>
						</tr>
						<tbody>
							<c:forEach var="brand" items="${result.listData}">
								<tr>
									<td>
										<input type="checkbox" name="IDCheck" class="acb" />
									</td>
									<td>${brand.id}</td>
									<td>${brand.name}</td>
									<td>${brand.sn}</td>
									<td>
										<a href="/brand/input?id=${brand.id}">编辑</a>
										<a class="btn_delete" href="javascript:;" data-url="/brand/delete?id=${brand.id}">删除</a>
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
