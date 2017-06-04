<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ page import="bean.User" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width">
    <title>邮件系统 - 登录</title>
    <link rel="stylesheet" href="css/mdui.min.css">
    <link rel="stylesheet" href="css/mail.css">
    <link rel="stylesheet" href="css/login.css">
    <link rel="icon" type="image/png" href="icons/mail_favicon.png">
</head>

<body class="mdui-theme-primary-blue mdui-theme-accent-pink login-container">
<form id="loginForm" name="login_register"  method="post">
    <div class="mdui-container login-input-container mdui-card mdui-col-md-4 mdui-col-offset-md-4 mdui-col-sm-6 mdui-col-offset-sm-3">
        <div class="mdui-card-primary">
            <div class="mdui-card-primary-title text-center">邮件系统</div>
        </div>

        <div class="mdui-textfield mdui-textfield-floating-label">
            <label class="mdui-textfield-label">用户名</label>
            <input class="mdui-textfield-input" name="user.account" id="account"/>
        </div>

        <div class="mdui-textfield mdui-textfield-floating-label">
            <label class="mdui-textfield-label">密码</label>
            <input class="mdui-textfield-input" name="user.password" id="first-passwd" type="password"/>
        </div>

        <div class="mdui-textfield mdui-textfield-floating-label enroll-confirm-passwd">
            <label class="mdui-textfield-label">确认密码</label>
            <input class="mdui-textfield-input" name="repassword" id="confirm-passwd" type="password"/>
        </div>

        <div class="mdui-row-xs-2 login-button text-center login-btn-list">
            <div class="mdui-col">
                <input value="登录" class="mdui-btn mdui-btn-block mdui-btn-raised mdui-ripple mdui-color-blue" id="log-login-btn"></input>
            </div>
            <div class="mdui-col">
                <input value="注册" class="mdui-btn mdui-btn-block mdui-btn-raised mdui-ripple"id="log-enroll-btn"></input>
            </div>
        </div>

        <div class="mdui-row-xs-2 login-button text-center enroll-btn-list">
            <div class="mdui-col">
                <input value="注册" class="mdui-btn mdui-btn-block mdui-btn-raised mdui-ripple mdui-color-blue" id="en-enroll-btn"></input>
            </div>
            <div class="mdui-col">
                <input value="取消" class="mdui-btn mdui-btn-block mdui-btn-raised mdui-ripple enroll-btn" id="en-cancel-btn"></input>
            </div>
        </div>
    </div>
</form>

    <script type="text/javascript" src="js/mdui.min.js"></script>

    <script type="text/javascript" src="js/login.js"></script>
</body>

</html>
