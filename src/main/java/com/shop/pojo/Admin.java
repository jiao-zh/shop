package com.shop.pojo;

import java.math.BigDecimal;

public class Admin {
    private Integer adminId;

    private Integer adminAccount;

    private String adminName;

    private BigDecimal adminSalary;

    private String adminPassword;

    private String phone;

    private String address;

    private String remarks;

    public Admin(Integer adminId, Integer adminAccount, String adminName, BigDecimal adminSalary, String adminPassword, String phone, String address, String remarks) {
        this.adminId = adminId;
        this.adminAccount = adminAccount;
        this.adminName = adminName;
        this.adminSalary = adminSalary;
        this.adminPassword = adminPassword;
        this.phone = phone;
        this.address = address;
        this.remarks = remarks;
    }

    public Admin() {
        super();
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount(Integer adminAccount) {
        this.adminAccount = adminAccount;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public BigDecimal getAdminSalary() {
        return adminSalary;
    }

    public void setAdminSalary(BigDecimal adminSalary) {
        this.adminSalary = adminSalary;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword == null ? null : adminPassword.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}