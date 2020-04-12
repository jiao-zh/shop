package com.shop.service;


import com.shop.common.ServerResponse;
import com.shop.pojo.Admin;

public interface IAdminService {
    ServerResponse<Admin> Login(String adminAccount, String password);
}
