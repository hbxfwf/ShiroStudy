<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<jsp:include page="/base.jsp"/>
<head>
    <meta charset="UTF-8">
    <style>
        .table {
            text-align: center;
        }
        .container {
            margin-top: 20px;
        }
        #form1 {
            margin-top: 10px;
            margin-left: 30px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">学生查询</h3>
        </div>

        <form class="form-inline" id="form1" method="post"
              action="${pageContext.request.contextPath}/student/search.do">
            <input type="hidden" name="page" id="page">
            学生姓名：<input type="text" name="sname" value="${param.sname}" placeholder="学生姓名关键字" class="form-control">
            学生住址：<input type="text" name="addr" value="${param.addr}" placeholder="学生住址关键字" class="form-control">
            所在班级：
            <select name="cid" class="form-control">
                <option value="0">所有班级</option>
                <c:forEach items="${classes}" var="c">
                    <option value="${c.cid}" ${c.cid==param.cid?"selected":""}>${c.cname}</option>
                </c:forEach>
            </select>
            <input type="submit" value="查询" class="btn btn-default btn-sm">
            <%--如果有student:create这个权限，则下面的超链接就会显示出来--%>
            <shiro:hasPermission name="student:createxxx">
                <input type="button" value="添加学生" class="btn btn-success btn-sm" >
            </shiro:hasPermission>
            <a href="javascript:parent.location.href = '/logout.do'" class="btn btn-success btn-sm">注销</a>
        </form>
        <table class="table table-bordered table-striped">
            <tr>
                <td>姓名</td>
                <td>性别</td>
                <td>年龄</td>
                <td>住址</td>
                <td>生日</td>
                <td>所在班级</td>
                <td>操作</td>
            </tr>
            <c:forEach items="${students}" var="stud">
                <tr>
                    <td>${stud.sname}</td>
                    <td>${stud.sex}</td>
                    <td>${stud.age}</td>
                    <td>${stud.addr}</td>
                    <td>
                        <fmt:formatDate value="${stud.birth}" pattern="yyyy-MM-dd E"/>
                    </td>
                    <td>
                            ${stud.classes.cname}
                    </td>
                    <td>
                        <a class="btn btn-primary btn-sm"
                           href="${pageContext.request.contextPath}/student/toupdate.do?sid=${stud.sid}">修改</a>
                        <a class="btn btn-danger btn-sm"
                           href="${pageContext.request.contextPath}/student/deleteBySid.do?sid=${stud.sid}"
                           onclick="return confirm('你真的要删除吗?')">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<script>
    //执行提交 表单
    function skip(i) {
        //1.对表单中的隐藏域赋值
        $("#page").val(i);
        //2.提交表单
        $("#form1").submit();
    }

    //添加学生
    function addStudent() {
        location.href = "${pageContext.request.contextPath}/student/toadd.do";
    }

</script>
</body>
</html>
