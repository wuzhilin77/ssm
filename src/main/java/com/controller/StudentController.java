package com.controller;

import com.pojo.Student;
import com.service.StudentService;
import com.utils.JSONResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/3/21.
 */
@Controller
@RequestMapping("/student")
public class StudentController {
    @Resource
    private StudentService studentService;
    @RequestMapping(value = "/studentAdd")
    public ModelAndView toAddStudent() {
        ModelAndView modelAndView = new ModelAndView("studentAdd");
        return modelAndView;
    }
    @RequestMapping(value = "/studentEditor")
    public ModelAndView toEditorStudent(String id){
        ModelAndView modelAndView = new ModelAndView("studentEditor");
        return modelAndView;
    }
    @RequestMapping(value="/student")
    public ModelAndView toStudent(){
        ModelAndView modelAndView = new ModelAndView("student");
        return modelAndView;
    }


    /**
     * 添加学生
     *
     * @param request 请求添加的学生信息
     * @return 添加成功与否信息
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public JSONResult addStudent(HttpServletRequest request) {
        JSONResult result;
//        Integer StudentId = Integer.parseInt(request.getParameter("StudentId"));����ɾ������Ϊǰ��û�����id������
        String StudentName = request.getParameter("StudentName");
        String StudentAddress = request.getParameter("StudentAddress");
        String StudentSex = request.getParameter("StudentSex");
        Student student = new Student();
//        student.setId(StudentId);����ɾ������Ϊǰ��û�����id������
        student.setName(StudentName);
        student.setAddress(StudentAddress);
        student.setSex(StudentSex);
        if (studentService.addStudent(student)) {
            result = new JSONResult(student);
            result.setMessage("success");
        } else {
            result = new JSONResult();
        }
        return result;
    }

    /**
     * 根据学生ID删除学生
     *
     * @param request 目标学生ID
     * @return 成功与否信息
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public JSONResult delStudentById(HttpServletRequest request) {
        JSONResult result = new JSONResult();
        Integer StudentId = Integer.parseInt(request.getParameter("StudentId"));
        if (!studentService.delStudentById(StudentId))
            result.setMessage("error");
        return result;
    }


    /**
     * 根据学生ID更新学生
     *
     * @param request 目标学生ID
     * @return 成功与否信息
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public JSONResult updateStudent(HttpServletRequest request) {
        JSONResult result;
        Integer StudentId = Integer.parseInt(request.getParameter("StudentId"));
        String StudentName = request.getParameter("StudentName");
        String StudentAddress = request.getParameter("StudentAddress");
        String StudentSex = request.getParameter("StudentSex");
        Student student = new Student();
        student.setId(StudentId);
        student.setName(StudentName);
        student.setAddress(StudentAddress);
        student.setSex(StudentSex);
        if (studentService.updateStudent(student)) {
            result = new JSONResult(student);
            result.setMessage("success");
        } else {
            result = new JSONResult();
            result.setMessage("error");
        }
        return result;
    }


    /**
     * 根据新闻ID更新学生
     *
     * @param request 目标学生ID
     * @return 成功与否信息
     */
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @ResponseBody
    public JSONResult findStudentById(HttpServletRequest request) {
        JSONResult result;
        Integer StudentId = Integer.parseInt(request.getParameter("StudentId"));
        Student student = studentService.findStudentById(StudentId);
        result = new JSONResult(student);
        return result;
    }
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public JSONResult findAllStudent(){
        List<Student> list = studentService.findAllStudent();
        return new JSONResult(list);
    }
}