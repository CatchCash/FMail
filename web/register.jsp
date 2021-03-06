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
  <form name="login_register" action="UserLoginAction" method="post">
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
                <button class="mdui-btn mdui-btn-block mdui-btn-raised mdui-ripple mdui-color-blue" name="login" id="log-login-btn">登录</button>
            </div>
            <div class="mdui-col">
                <button class="mdui-btn mdui-btn-block mdui-btn-raised mdui-ripple" name="register" id="log-enroll-btn">注册</button>
            </div>
        </div>

        <div class="mdui-row-xs-2 login-button text-center enroll-btn-list">
            <div class="mdui-col">
                <button class="mdui-btn mdui-btn-block mdui-btn-raised mdui-ripple mdui-color-blue" name="register" id="en-enroll-btn">注册</button>
            </div>
            <div class="mdui-col">
                <button class="mdui-btn mdui-btn-block mdui-btn-raised mdui-ripple enroll-btn" name="cancel" id="en-cancel-btn">取消</button>
            </div>
        </div>
    </div>

    <script src="js/mdui.min.js"></script>

    <script src="js/login.js"></script>
  </form>
</body>

</html>
