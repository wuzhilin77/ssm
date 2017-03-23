/**查询全部用户**/
function queryStudent() {
    $.ajax({
        type: "GET",
        url: "rest/student/list",
        dataType: "json",
        success: function (data) {
            var studentList = data.data;
            if (studentList.lenght == 0) {
                $("#show_list").html("暂时查不到您要的数据！");
            } else {
                var studentListHtml = "<tr><th><input id='checked-all' type='checkbox'><label>全选</label></th><th>姓&nbsp;&nbsp;名</th><th>地&nbsp;&nbsp;址</th><th>性&nbsp;&nbsp;别</th></tr>";
                $.each(studentList, function (i, n) {
                    i += 1;
                    studentListHtml = studentListHtml + "<tr><td class='list-id'><input type='checkbox'><label>"
                        + i + "</label></td><td>"
                        + n.name + "</td><td>"
                        + n.address + "</td><td>"
                        + n.sex + "</td><td class='user-edit'><a  class='editBtn' data-id='"
                        + n.id + "'>编辑</a><a class='deleteBtn'  data-id='"
                        + n.id + "'>删除</a></td></tr>";
                });
                $("#show_list").html(studentListHtml);
                $(".deleteBtn").click(function () {
                    delStudent($(this).attr("data-id"));
                });
                $(".editBtn").click(function () {
                    updateStudent($(this).attr("data-id"));
                })

            }
        }
    });
}

/**
 * @decription:添加案例
 *  @param：#submit 提交
 */

function addStudent(StudentId) {
    window.location.href = "rest/student/studentAdd" + "?id=" + StudentId;//跳转到添加页面

}

/**
 * @decription:删除案例
 */
function delStudent(StudentId) {
    var param = "StudentId=" + StudentId;
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "rest/student/del",
        data: param,
        success: function () {
            queryStudent();
        }
    });
}

/**
 * @decription:更新案例
 */
function updateStudent(StudentId) {
    window.location.href = "rest/student/studentEditor" + "?id=" + StudentId;

}
queryStudent();