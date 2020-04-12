package com.shop.pojo;

import java.util.Date;

public class Category {
    private Integer categoryId;

    private Integer parentId;

    private String categoryName;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public Category(Integer categoryId, Integer parentId, String categoryName, Integer status, Date createTime, Date updateTime) {
        this.categoryId = categoryId;
        this.parentId = parentId;
        this.categoryName = categoryName;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Category() {
        super();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}