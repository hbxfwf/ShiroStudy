<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="/base.jsp"/>
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
            <input type="button" value="添加学生" class="btn btn-success btn-sm" onclick="addStudent()">
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
            <c:forEach items="${pr.rows}" var="stud">
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
            <tr>
                <td colspan="7">
                    <%--1.分页导航--%>
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <li>
                                <a href="#" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <c:forEach begin="1" end="${pr.totalpages}" var="p">
                                <%--如何动态显示当前页的样式--%>
                                <li class="${p == page ? 'active' : ''}">
                                    <a href="${pageContext.request.contextPath}/student/search.do?page=${p}">${p}</a>
                                </li>
                            </c:forEach>
                            <li>
                                <a href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </td>

            </tr>
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
