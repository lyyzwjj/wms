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
<script type="text/javascript" src="/js/commonAll.js"></script>
<script type="text/javascript" src="/js/artDialog/plugins/My97DatePicker/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="/js/jquery/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
<script type="text/javascript" src="/js/jquery/plugins/artDialog/plugins/iframeTools.source.js"></script>
<script type="text/javascript">
    $(function(){
        $(".btn_show").click(function(){
        var showurl =  $(this).data("url")
            art.dialog.open(showurl, {
                id: 'ajxxList',
                title: '查看商品',
                width: 900,
                height: 450,
                left: '20%',
                top: '20%',
                background: '#000000',
                opacity: 0.1,
                lock: true,
                resize: false
            });
        });
    });
</script>
<title>PSS-入库单管理</title>
<style>
.alt td {
	background: black !important;
}
</style>
</head>
<body>
	<form id="searchForm" action="/stockIncomeBill/list" method="post">
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
                        仓库
                        <select name="depotId" class="ui_select01">
                            <option value="-1">所有仓库</option>
                            <c:forEach var="depot" items="${depots}">
                            <option value="${depot.id}"${depot.id == qo.depotId ? "selected":""}>${depot.name}</option>
                            </c:forEach>
                        </select>
                        状态
                        <select id="status" name="status" class="ui_select01">
                            <option value="-1" >所有状态</option>
                            <option value="0" ${qo.status == 0 ? "selected":""}>待审核</option>
                            <option value="1" ${qo.status == 1 ? "selected":""}>已审核</option>
                        </select>
                        <input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
                    </div>
					
						<div id="box_bottom">
							<input type="button" value="新增" class="ui_input_btn01 btn_input" data-url="/stockIncomeBill/input"/>
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
							<th>入库单编码</th>
							<th>业务时间</th>
							<th>仓库</th>
							<th>总金额</th>
							<th>总数量</th>
							<th>录入人</th>
							<th>审核人</th>
							<th>状态</th>
							<th></th>
						</tr>
						<tbody>
							<c:forEach var="stockIncomeBill" items="${result.listData}">
								<tr>
									<td>
									<input type="checkbox" name="IDCheck" class="acb" />
									</td>
									<td>${stockIncomeBill.sn}</td>
									<td><fmt:formatDate value="${stockIncomeBill.vdate}"  pattern="yyyy-MM-dd"/></td>
									<td>${stockIncomeBill.depot.name}</td>
									<td>${stockIncomeBill.totalAmount}</td>
									<td>${stockIncomeBill.totalNumber}</td>
									<td>${stockIncomeBill.inputUser.name}</td>
									<td>${stockIncomeBill.auditor.name}</td>
									<td>${stockIncomeBill.status}</td>
									<td>
										<c:if test="${stockIncomeBill.status==0}">
										<a href="/stockIncomeBill/input?id=${stockIncomeBill.id}">编辑</a>
										<a class="btn_audit" href="javascript:;" data-url="/stockIncomeBill/audit?id=${stockIncomeBill.id}">审核</a>
										<a class="btn_delete" href="javascript:;" data-url="/stockIncomeBill/delete?id=${stockIncomeBill.id}">删除</a>
										</c:if>
										<c:if test="${stockIncomeBill.status==1}">
										<a class="btn_show" href="javascript:;" data-url="/stockIncomeBill/show?id=${stockIncomeBill.id}">查看</a>
										</c:if>
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
