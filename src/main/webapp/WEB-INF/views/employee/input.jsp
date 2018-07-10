<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>信息管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <script type="text/javascript" src="/js/system/employee.js"></script>
    <script type="text/javascript" src="/js/jquery/plugins/jquery-validate/jquery.validate.js"></script>
    <script type="text/javascript">
    $(function(){
    	$("[name=editForm]").validate({
			rules:{
				name:{
					required : true,
					minlength : 6
				},
				password:{
					required:true,
					minlength:6
				},
				repassword:{
					required:true,
					equalTo:"#password"
				},
				email:{
					required:true,
					email:true
				},
				age:{
					range:[18,120]
				}
			},
			messages:{
				name:{
					required:"用户名必填",
					minlength:"长度至少为6位"
				},
				password:{
					required:"密码必填",
					minlength:"长度至少为6位"
				},
				repassword:{
					required:"确认密码必填",
					equalTo:"两次密码必须一致"
				},
				email:{
					required:"密邮箱必填",
					email:"邮箱格式不正确例:example@163.com"
				},
				age:{
					range:"年龄不在范围内"
				}
			}
    });
});
    </script>
</head>
<body>
<form name="editForm" action="/employee/saveOrUpdate" method="post" id="editForm">
<input name="id" type="hidden" value="${employee.id}">
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">用户编辑</span>
            <div id="page_close">
                <a>
                    <img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <tr>
                    <td class="ui_text_rt" width="140">用户名</td>
                    <td class="ui_text_lt">
                        <input name="name" value="${employee.name}" class="ui_input_txt02"/>
                    </td>
                </tr>
                <c:if test="${employee.id == null}">
                <tr>
                    <td class="ui_text_rt" width="140">密码</td>
                    <td class="ui_text_lt">
                        <input type="password" name="password" id="password" class="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">验证密码</td>
                    <td class="ui_text_lt">
                        <input name="repassword" type="password" class="ui_input_txt02" />
                    </td>
                </tr>
                </c:if>
                <tr>
                    <td class="ui_text_rt" width="140">Email</td>
                    <td class="ui_text_lt">
                        <input name="email" value="${employee.email}" class="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">年龄</td>
                    <td class="ui_text_lt">
                        <input name="age" value="${employee.age}" class="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">所属部门</td>
                    <td class="ui_text_lt">
                        <select name="department.id" class="ui_select01">
                              <c:forEach var="department" items="${departments}">
                            <option value="${department.id}" >${department.name}</option>
                            </c:forEach>
                            <%-- ${department.id == employee.department.id ? "selected":""} --%>
                        </select>
                        <script type="text/javascript">
                        $("[name='department.id'] option").each(function(index,option){
                        	if(option.value == '${employee.department.id}'){
                        		option.selected=true;
                        	}
                        })
                        </script>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">超级管理员</td>
                    <td class="ui_text_lt">
                        <input type="checkbox" name="admin" ${employee.admin ? 'checked':'' } class="ui_checkbox01"></input>
                    </td>
                </tr>
                 <tr>
                    <td class="ui_text_rt" width="140">角色</td>
                    <td class="ui_text_lt">
                        <table>
                            <tr>
                                <td>
                                    <select multiple="true" class="ui_multiselect01 all_roles">
                                    <c:forEach var="role" items="${roles}">
                                    <option value="${role.id}">${role.name}</option>
                                    </c:forEach>
                                    </select>
                                </td>
                                <td align="center">
                                    <input type="button" id="select" value="-->" class="left2right"/><br/>
                                    <input type="button" id="selectAll" value="==>" class="left2right"/><br/>
                                    <input type="button" id="deselect" value="<--" class="left2right"/><br/>
                                    <input type="button" id="deselectAll" value="<==" class="left2right"/>
                                </td>
                                <td>
                                    <select multiple="true" name="roleIds" class="ui_multiselect01 selected_roles">
                                    <c:forEach var="role" items="${employee.roles}">
                                    <option value="${role.id}">${role.name}</option>
                                    </c:forEach>
                                    </select>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr> 
                <tr>
                    <td>&nbsp;</td>
                    <td class="ui_text_lt">
                        &nbsp;<input type="submit" value="确定保存" class="ui_input_btn01"/>
                        &nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    </form>
</body>
</html>