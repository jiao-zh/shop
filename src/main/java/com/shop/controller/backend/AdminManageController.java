package com.shop.controller.backend;



import com.shop.common.Const;
import com.shop.common.ServerResponse;
import com.shop.pojo.Admin;
import com.shop.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/admin")
public class AdminManageController {

    @Autowired
    private IAdminService iAdminService;

    @RequestMapping(value = "Login.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Admin> login(String adminAcoount, String adminPassword, HttpSession session){
        ServerResponse<Admin> response = iAdminService.Login(adminAcoount,adminPassword);
        if(response.isSuccess()){
            Admin admin = response.getData();
            session.setAttribute(Const.CURRENT_USER,admin);
        }
        return response;
    }
}
