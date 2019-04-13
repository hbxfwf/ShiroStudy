//2.2)定义控制器(其中$scope与$http都是系统自带的服务，其中$http服务代表访问后台的服务)
app.controller("myController",function($scope,$http,studentService){
    // 关于分页的选项定义
    $scope.paginationConf= {
        currentPage: 1,	//代表当前页
        itemsPerPage: 4,	//代表每页的记录数
        totalItems: 100,	//代表总记录数
        perPageOptions: [5, 10, 15, 20, 25, 30], //代表分页的选项列表
        onChange: function () {	//每次页码改变时此事件会自动调用,窗体一加载完毕会自带调用
            //分页不带条件查询
            //$scope.findByPage( $scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
            //分页带条件查询
            $scope.search();
            //查询所有的班级
            $scope.findClasses();
        }
    }
    //查询所有班级
    $scope.findClasses=function(){
        return studentService.findClasses().success(
            function(response){
                $scope.classesList = response;
            }
        )
    }
    //分页查询学生【情况一：不带条件查询】
    $scope.findByPage=function(page,pagesize){
        return studentService.findByPage(page, paegsize).success(
            function (response) {
                $scope.studentList = response.list;   //将每页的记录集合赋值给全局变量
                //修改总记录数
                $scope.paginationConf.totalItems = response.total;
            }
        )
    }
    //查询所有的学生【不带分页的方法】
    $scope.loadStudentList=function(){
        return studentService.loadStudentList().success(
            function(response){
                $scope.studentList = response;
            }
        )
    }
    //打开添加学生对话框
    $scope.addStduent=function () {
        //1.清空表单数据
        $scope.entity = {};
        //2.显示对话框
        $("#myModal").modal("show");
    }
    //修改学生的界面
    $scope.updateStudent=function (stud) {
        //1.对实体类进行赋值
        $scope.entity = stud;
        //2.弹出对话框
        $("#myModal").modal("show");
    }
    //删除学生
    $scope.delStudent=function(sid){
        return studentService.delStudent(sid).success(
            function(response){
                if (response.success){
                    $scope.search();   //刷新页面
                }else{
                    alert(response.message);
                }
            }
        )
    }
    //保存学生（修改/添加）
    $scope.save =function () {
        //思路：判断$scope.entity.sid是否有值，有则是修改操作，否则为添加操作
        var url = "/student/add.do";
        if ($scope.entity.sid){
            url = "/student/update.do"
        }
        //向后台发出请求
        return studentService.save(url,$scope.entity).success(
            function(response){
                if (response.success){
                    $scope.search();   //刷新页面
                }else{
                    alert(response.message);
                }
                //关闭对话框
                $("#myModal").modal("hide");
            }
        )
    }
    //条件查询带分页
    $scope.search =function () {
        //分页参数：page与pagesize
        //条件查询参数：分页查询的相关条件（sname,addr与cid，并将它们封装到了searchEntity对象中）
        return studentService.search($scope.paginationConf.currentPage,
            $scope.paginationConf.itemsPerPage,
            $scope.searchEntity).success(
            function(response){
                $scope.studentList = response.list;   //将每页的记录集合赋值给全局变量
                //修改总记录数
                $scope.paginationConf.totalItems = response.total;
            }
        )
    }
})