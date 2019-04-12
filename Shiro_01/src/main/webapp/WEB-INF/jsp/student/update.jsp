<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="/base.jsp"/>
<style>
    .container{
        text-align: left;
        margin-top: 30px;
        width: 600px;
    }
</style>
<body>
<div class="container">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3>修改学生</h3>
        </div>
        <div class="panel-body">
            <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/student/update.do">
                <input type="hidden" name="sid" value="${student.sid}">
                <div class="form-group">
                    <label class="col-sm-2 control-label">学生姓名</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="sname" placeholder="学生姓名" value="${student.sname}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">性别</label>
                    <div class="col-sm-10">
                        <div class="radio">
                            <label>
                                <input type="radio" name="sex" value="男" ${student.sex=="男"?"checked":""}>男
                            </label>
                            <label>
                                <input type="radio" name="sex"  value="女" ${student.sex=="女"?"checked":""}>女
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">学生年龄</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="age"  placeholder="学生年龄" value="${student.age}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">出生日期</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="birth"  placeholder="出生日期"
                               value="<fmt:formatDate value="${student.birth}" pattern="yyyy-MM-dd"/>">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">家庭住址</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="addr"  placeholder="家庭住址"  value="${student.addr}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">所在班级</label>
                    <div class="col-sm-10">
                        <select name="cid" class="form-control">
                            <c:forEach items="${classes}" var="c">
                                <option value="${c.cid}" ${c.cid==student.cid ? "selected" : ""}>${c.cname}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary btn-block">修改学生</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
