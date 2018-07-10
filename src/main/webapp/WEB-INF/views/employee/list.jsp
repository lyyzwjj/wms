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
    <title>PSS-员工管理</title>
    <style>
        .alt td{ background:black !important;}
    </style>
</head>
<body>
<form id="searchForm" action="/employee/list" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        姓名/邮箱
                        <input type="text" class="ui_input_txt02 " name="keywords" value="${qo.keywords}"/>
                        所属部门
                        <select class="ui_select01" name="deptId">
                            <option value="-1">全部</option>
                            <c:forEach var="department" items="${departments}">
                            <option value="${department.id}" ${department.id == qo.deptId ? "selected":""}>${department.name}</option>
                            </c:forEach>
                        </select>
                      
                    </div>
                    <div id="box_bottom">
                        <input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
                        <input type="button" value="新增" class="ui_input_btn01 btn_input" data-url="/employee/input"/>
                        <input type="button" value="批量删除" class="ui_input_btn01 btn_batchdelete" data-url="/employee/batchDelete"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all" /></th>
                        <th>用户名</th>
                        <th>EMAIL</th>
                        <th>年龄</th>
                        <th>超级管理员</th>
                        <th>所属部门</th>
                        <th>角色</th>
                        <th></th>
                    </tr>
                    <tbody>
					<c:forEach var="employee" items="${result.listData}">
                    <tr>
                        <td><input type="checkbox" name="IDCheck" class="acb" data-id="${employee.id}"/></td>
                        <td>${employee.name}</td>
                        <td>${employee.email}</td>
                        <td>${employee.age}</td>
                        <td>${employee.admin}</td>
                        <td>${employee.department.name}</td>
                       <!-- <td>角色1，角色2</td> -->
                        <td>
                            <a href="/employee/input?id=${employee.id}">编辑</a>
							<a  class="btn_delete" href="javascript:;" data-url="/employee/delete?id=${employee.id}">删除</a>
                        </td>
                    </tr>
					</c:forEach>
                    </tbody>
                </table>
            </div>
          <jsp:include page="/WEB-INF/views/commons/common-page.jsp"></jsp:include>
    </div>
</form>
</body>
</html>

