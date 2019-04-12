<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/12
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/base.jsp"%>
<style>

    .col-md-2{
        margin-top: 20px;
    }
    .list-group-item{
        text-align: center;
    }
    .col-md-10{
        padding-left: 0px;
        margin-left: -10px;
    }

</style>
<html>
<head>
    <title>显示菜单</title>
</head>
<body>
    <div class="container">
        <%--分成左右栏--%>
        <%--1.左边显示菜单--%>
        <div class="row">
            <div class="col-md-2">
                <div class="list-group">
                    <a href="#" class="list-group-item active">
                        用户菜单
                    </a>
                    <c:forEach items="${menus}" var="m">
                        <a href="${m.url}" target="right" class="list-group-item">${m.name}</a>
                    </c:forEach>
                </div>
            </div>
            <%--2.右边显示效果--%>
            <div class="col-md-10">
                <iframe src="" width="1100" height="800" frameborder="0" name="right"></iframe>
            </div>
        </div>

    </div>
</body>
</html>
