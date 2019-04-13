//定义服务层studentService
app.service("studentService",function($http){
    //查询所有班级
    this.findClasses=function(){
        return  $http.get("/classes/list.do");
    }
    //分页查询学生【情况一：不带条件查询】
    this.findByPage=function(page,pagesize){
        return  $http.get("/student/findByPage.do?page="+page+"&pagesize="+pagesize);
    }
    //查询所有的学生【不带分页的方法】
    this.loadStudentList=function(){
        return  $http.get("/student/list.do");
    }
    //删除学生
    this.delStudent=function(sid){
        return $http.get("/student/delele.do?sid="+sid);
    }
    //保存学生（修改/添加）
    this.save=function(url,entity){
        return  $http.post(url,entity);
    }
    //条件查询带分页
    this.search =function(page,pagesize,entity){
        return  $http.post("/student/search.do?page="+page+"&pagesize="+pagesize,entity);
    }
})