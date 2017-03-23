
    /*--------------------------------------------渲染页面--------------*/
    function getQueryString(name) {
      var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
      var r = window.location.search.substr(1).match(reg);
      if (r != null) {
        return unescape(r[2]);
      }
      return null;
    }

    var StudentId = getQueryString("StudentId");

    $.ajax({
      type: "GET",
      url: "rest/student/find",  //根据id查询记录
      data:{
        StudentId:StudentId
      },
      dataType: "json",
      success:function(data){
        //获取到id=xxx的用户对象
        var student = data.data;
        $("#id").val();
        $("#name").val(StudentId);
        $("#address").val(StudentAddress);
        $("#sex").val(StudentSex);
        //给提交按钮绑定事件
        $("#submitBtn").click(function() {
          updateStudent();
        })

      }
    });
    /*--------------------------------------------渲染页面--------------*/
    function updateStudent(){
      $.ajax({
        type: "POST",
        dataType: "json",
        url: "rest/student/update",  //更新路径
        data: {
          StudentId: $("#id").val(StudentId),
          StudentName: $("#name").val(StudentName),
          StudentAddress: $("#address").val(StudentAddress),
          StudentSex: $("#sex").val(StudentSex)
        },
        success: function(data) {

          window.location.href="rest/student/student";  //跳转到列表页面
        }

      });
    }