package com.shop.dao;

import com.shop.pojo.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer adminId);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    int checkAdminAccount(String adminAccount);

    Admin selectLogin(@Param("adminAccount") String adminAccount, @Param("adminPassword") String adminPassword);
}