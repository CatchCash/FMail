<%@ page import="mail.receive.MailReceives,bean.*"
contentType="text/html;charset=GB2312" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width">
    <title>邮件系统 - 收件箱</title>
    <link rel="stylesheet" href="../css/mdui.min.css">
    <link rel="stylesheet" href="../css/mail.css">
</head>

<body class="mdui-appbar-with-toolbar mdui-theme-primary-blue mdui-theme-accent-pink">

    <header class="mdui-appbar mdui-appbar-fixed">
        <div class="mdui-toolbar mdui-color-theme">
            <span class="mdui-btn mdui-btn-icon mdui-ripple mdui-ripple-white" mdui-drawer="{target: '#drawer'}">
                <i class="mdui-icon material-icons">&#xe5d2;</i>
            </span>
            <span class="mdui-typo-title">收件箱</span>
        </div>
    </header>

    <div class="mdui-drawer mdui-drawer-close" id="drawer">
        <ul class="mdui-list">
            <li class="mdui-subheader">邮件管理</li>
            <a href="index.jsp" class="mdui-list-item mdui-ripple">
                <i class="mdui-list-item-icon mdui-icon material-icons">&#xe156;</i>
                <div class="mdui-list-item-content">收件箱</div>
            </a>
            <a href="newmail.html" class="mdui-list-item mdui-ripple">
                <i class="mdui-list-item-icon mdui-icon material-icons">&#xe0e1;</i>
                <div class="mdui-list-item-content">写邮件</div>
            </a>
            <a href="sent.html" class="mdui-list-item mdui-ripple">
                <i class="mdui-list-item-icon mdui-icon material-icons">&#xe163;</i>
                <div class="mdui-list-item-content">已发邮件</div>
            </a>
            <li class="mdui-subheader">用户管理</li>
            <a href="passwd.html" class="mdui-list-item mdui-ripple">
                <i class="mdui-list-i tem-icon mdui-icon material-icons">&#xe32a;</i>
                <div class="mdui-list-item-content">修改密码</div>
            </a>
            <a href="login.html" class="mdui-list-item mdui-ripple">
                <i class="mdui-list-item-icon mdui-icon material-icons">&#xe879;</i>
                <div class="mdui-list-item-content">退出用户</div>
            </a>
        </ul>
    </div>

    <div class="mdui-container mail-container mdui-col-md-10 mdui-col-offset-md-1 mdui-col-lg-8 mdui-col-offset-lg-2">
        <div class="mdui-panel mdui-panel-popout" mdui-panel="{accordion: true}">
            <%
                Mail[] mails=MailReceives.Receives(new User(
                        0,/*
                "wangsixiong@hnu.edu.cn",
                "f1sd23a4",*/
                        "386208935@qq.com",
                        "rojunwbrhmhybhgi",
                        null
                ));

                for(Mail mail:mails){
            %>
            <div class="mdui-panel-item">
                <div class="mdui-panel-item-header">
                    <div class="mdui-panel-item-title mdui-col-offset-sm-2"><%=mail.getFrom()%>></div>
                    <div class="mdui-panel-item-summary mdui-col-offset-sm-1"><%=mail.getTitle()%></div>
                    <i class="mdui-panel-item-arrow mdui-icon material-icons">&#xe313;</i>
                </div>
                <div class="mdui-panel-item-body mdui-typo">
                    <h2><%=mail.getTitle()%></h2>
                    <div class="mdui-divider mail-divider"></div>
                    <%=mail.getBodyContent()%>
                </div>
            </div>
            <%
                }
            %>
        </div>
    </div>

    <button class="mdui-fab mdui-color-theme-accent mdui-ripple mdui-fab-fixed newmail-btn" mdui-tooltip="{content: '写邮件', position: 'left'}">
        <i class="mdui-icon material-icons">&#xe145;</i>
    </button>

    <script src="../js/mdui.min.js"></script>
    <script>
        var $$ = mdui.JQ;

        $$('.newmail-btn').on('click', function(e) {
            document.location.assign('newmail.html');
        });
    </script>
</body>

</html>
