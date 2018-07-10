<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>信息管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>

</head>
<body>
	<form name="editForm"
		action="/product/saveOrUpdate"
		method="post" id="editForm" enctype="multipart/form-data">
		<input  type="hidden" value="${product.id}">
		<input  type="hidden" value="${product.imagePath}">
		<div id="container">
			<div id="nav_links">
				<span style="color: #1A5CC6;">商品查看</span>
				<div id="page_close">
					<a>
						<img src="/images/common/page_close.png" width="20" height="20"
							style="vertical-align: text-top;" />
					</a>
				</div>
			</div>
			<div class="ui_content">
				<table cellspacing="0" cellpadding="0" width="100%" align="left"
					border="0">
					<tr>
						<td class="ui_text_rt" width="140">商品编码</td>
						<td class="ui_text_lt">
							<input value="${product.sn}" class="ui_input_txt02" />
						</td>
					</tr>
					<tr>
						<td class="ui_text_rt" width="140">商品名称</td>
						<td class="ui_text_lt">
							<input value="${product.name}" class="ui_input_txt02" />
						</td>
					</tr>

					<tr>
						<td class="ui_text_rt" width="140">成本价格</td>
						<td class="ui_text_lt">
							<input value="${product.costPrice}"
								class="ui_input_txt02" />
						</td>
					</tr>
					<tr>
						<td class="ui_text_rt" width="140">销售价格</td>
						<td class="ui_text_lt">
							<input value="${product.salePrice}"
								class="ui_input_txt02" />
						</td>
					</tr>
					<tr>
						<td class="ui_text_rt" width="140">商品品牌</td>
						<td class="ui_text_lt">
							<select name="brandId" class="ui_select01">
								<c:forEach var="brand" items="${brands}">
									<option value="${brand.id}">${brand.name}</option>
								</c:forEach>
								<%-- ${department.id == employee.department.id ? "selected":""} --%>
							</select>
							<script type="text/javascript">
                            $("[name='brandId'] option").each(function(index,option){
                                if(option.value == '${product.brandId}'){
                                    option.selected=true;
                                }
                            })
                        </script>
						</td>
					</tr>
					<tr>
						<td class="ui_text_rt" width="140">商品图片</td>
						<td class="ui_text_lt">
							<input type="file" >
							<img src="${product.imagePath}" alt="图片失效" height="80px">
						</td>
					</tr>
					<tr>
						<td class="ui_text_rt" width="140">商品简介</td>
						<td class="ui_text_lt">
							<textarea id="intro" cols="30" rows="10">${product.intro}</textarea>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td class="ui_text_lt">
							&nbsp;
							<input type="submit" value="返回列表" class="ui_input_btn01" />
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
</body>
</html>