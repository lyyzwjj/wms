<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>叩丁狼教育PSS（演示版）</title>
    <link href="/style/error_css.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="login_center">
    <div id="login_area">
        <div id="login_box">
            <div id="login_form">
                <H2>出错啦！</H2>
                <span>执行过程中发生了异常：</span>
                <span class="error">${exception.message}</span>
                <span>请联系管理员解决！</span>
                <span>联系电话：020-29007520</span>
                <span>联系邮件：service@wolfcode.cn</span>
                <span>&copy;广州叩丁狼教育科技有限公司</span>
            </div>
        </div>
    </div>
</div>
</body>
</html>
