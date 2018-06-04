<%@ page contentType="text/html; charset=UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>登录</title>
    <script type="text/javascript" src="../static/js/jquery.js"></script>
    <script type="text/javascript" src="../static/easyui/jquery-ui.js"></script>
    <link href="../static/easyui/jquery-ui.css" rel="stylesheet" type="text/css" />
    <link href="../static/easyui/jquery-ui.structure.css" rel="stylesheet" type="text/css" />
    <link href="../static/easyui/jquery-ui.theme.css" rel="stylesheet" type="text/css" />
    <link href="../static/css/login.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div class="login">
    <div class="box png">
        <div class="logo png"></div>
        <div class="input">
            <form action="/login" method="post">
                <div class="log">
                    <div class="name">
                        <label>用户名</label><input type="text" class="text" id="value_1" placeholder="用户名" name="value_1" tabindex="1">
                    </div>
                    <div class="pwd">
                        <label>密　码</label><input type="password" class="text" id="value_2" placeholder="密码" name="value_2" tabindex="2">
                        <input type="submit" class="submit" tabindex="3" value="登录">
                        <div class="check"></div>
                    </div>
                    <div class="tip"></div>
                </div>
            </form>
        </div>
    </div>
    <div class="air-balloon ab-1 png"></div>
    <div class="air-balloon ab-2 png"></div>
    <div class="footer"></div>
</div>
<script type="text/javascript" src="../static/js/login-fun.base.js"></script>
<script type="text/javascript" src="../static/js/login-script.js"></script>

</body>
</html>