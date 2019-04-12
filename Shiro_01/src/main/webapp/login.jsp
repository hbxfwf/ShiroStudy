<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/12
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
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
        <form class="form-signin" action="${pageContext.request.contextPath}/login.do" method="post">
            <label>用户名</label>
            <input type="text"  name="username" class="form-control" placeholder="输入用户名" required autofocus>
            <label >密码</label>
            <input type="password" name="password" class="form-control" placeholder="输入密码" required>
            <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
            <span class="error">${message}</span>
        </form>
    </div>
    </div>


</div> <!-- /container -->
</body>
</html>
