package com.controller;

import com.pojo.User;
import com.service.UserService;
import com.utils.JSONResult;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/3/19.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @RequestMapping(value = "/toadd", method = RequestMethod.GET)
    public ModelAndView addUser(){
        ModelAndView modelAndView=new ModelAndView("userAdd");
        return modelAndView;

    }
    /**
     * ����û�
     *
     * @param request ������ӵ��û���Ϣ
     * @return ��ӳɹ������Ϣ
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addUser(HttpServletRequest request) {
        Integer Uid = Integer.parseInt(request.getParameter("Uid"));
        String Username = request.getParameter("Username");
        String Password = request.getParameter("Password");
        String Name = request.getParameter("Name");
        String Emali = request.getParameter("Emali");
        String Phone = request.getParameter("Phone");
        String Addr = request.getParameter("Addr");
        String State = request.getParameter("State");
        String Code = request.getParameter("Code");
        User user = new User();
        user.setUid(Uid);
        user.setUsername(Username);
        user.setUsername(Password);
        user.setName(Name);
        user.setEmali(Emali);
        user.setPhone(Phone);
        user.setAddr(Addr);
        user.setState(State);
        user.setCode(Code);
        userService.addUser(user);
        return new ModelAndView("forward:/rest/user/list");
    }


    /**
     * �����û�Uidɾ���û�
     *
     * @param request Ŀ���û�Uid
     * @return �ɹ������Ϣ
     */
    @RequestMapping(value = "/del",method = RequestMethod.GET)
    public ModelAndView delUserByUid(HttpServletRequest request) {
        ModelAndView modelAndView=new ModelAndView("userList");
        int UserUid = Integer.parseInt(request.getParameter("UserUid"));
        if (!userService.delUserByUid(UserUid))
            modelAndView.setStatus(HttpStatus.valueOf("error"));
        return new ModelAndView("forward:/rest/user/list");
    }


    /**
     * �����û�Uid�����û�
     *
     * @param request �û�Uid
     * @return �ɹ������Ϣ
     */

    @RequestMapping(value = "/editor", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView editorUser(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("userEditor");
        Integer Uid = Integer.parseInt(request.getParameter("Uid"));
        String Username = request.getParameter("Username");
        String Password = request.getParameter("Password");
        String Name = request.getParameter("Name");
        String Emali = request.getParameter("Emali");
        String Phone = request.getParameter("Phone");
        String Addr = request.getParameter("Addr");
        String State = request.getParameter("State");
        String Code = request.getParameter("Code");
        User user = new User();
        user.setUid(Uid);
        user.setUsername(Username);
        user.setName(Name);
        user.setEmali(Emali);
        user.setPhone(Phone);
        user.setAddr(Addr);
        user.setState(State);
        user.setCode(Code);
        if (userService.addUser(user)) {
            modelAndView = new ModelAndView((View) user);
            modelAndView.setStatus(HttpStatus.valueOf("success"));
        } else {
            modelAndView = new ModelAndView();
            modelAndView.setStatus(HttpStatus.valueOf("error"));
        }
        return new ModelAndView("forward:/rest/user/list");

    }

    /**
     * �����û�Uid��ѯ�û�
     *
     * @param model �û�Uid
     * @return Ŀ���û���Ϣ
     */

    @RequestMapping("/list")
    public ModelAndView UserList(Model model) {
        ModelAndView modelAndView = new ModelAndView("userList");

        List<User> list =userService.findAll();
        //����������ǰ��
        modelAndView.addObject("list", list);
        //���ض�Ӧ��ͼ
        return modelAndView;
    }


}
