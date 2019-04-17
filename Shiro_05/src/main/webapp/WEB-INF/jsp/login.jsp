<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/base.jsp"%>
<html>
<head>
    <title>用户登录</title>
    <style>
        .container{
            width: 500px;
            margin-top: 50px;
        }
        .form-signin{
            padding:5px;
        }
        .btn{
            margin-top: 20px;
        }
        .error{
            color:red;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">
                用户登录
            </h3>
        </div>
        <div class="panel-body">
        <form class="form-signin" action="${pageContext.request.contextPath}/login" method="post">
            <label>用户名</label>
            <input type="text"  name="username" class="form-control" placeholder="输入用户名" required autofocus>
            <label >密码</label>
            <input type="password" name="password" class="form-control" placeholder="输入密码" required>
            <label >输入验证码</label>
            <input type="text" name="validcode" style="width:360px" class="form-control" placeholder="输入验证码">
            <img src="${pageContext.request.contextPath}/validatecode.jsp" style="position: relative;top:-34px;left: 365px"/>
            <div style="padding:0;margin-top: -20px;">
                <input type="checkbox" name="rememberMe">&nbsp;记住我
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
            <span class="error">${message}</span>
        </form>
    </div>
    </div>
</div> <!-- /container -->
</body>
</html>
