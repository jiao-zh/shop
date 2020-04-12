package com.shop.service.impl;

import com.shop.common.Const;
import com.shop.common.ServerResponse;
import com.shop.dao.AdminMapper;
import com.shop.pojo.Admin;
import com.shop.service.IAdminService;
import com.shop.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("iUserService")
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;


    @Override
    public ServerResponse<Admin> Login(String adminAccount, String adminPassword) {
        int resultCount = adminMapper.checkAdminAccount(adminAccount);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("用户账户不存在");
        }
        String md5Password = MD5Util.MD5EncodeUtf8(adminPassword);

        Admin admin = adminMapper.selectLogin(adminAccount,md5Password);
        if(admin == null){
            return ServerResponse.createByErrorMessage("密码错误");
        }

        admin.setAdminPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登陆成功",admin);

    }

}
