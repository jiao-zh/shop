package com.shop.dao;

import com.shop.pojo.Good;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodMapper {
    int deleteByPrimaryKey(Integer goodId);

    int insert(Good record);

    int insertSelective(Good record);

    Good selectByPrimaryKey(Integer goodId);

    int updateByPrimaryKeySelective(Good record);

    int updateByPrimaryKey(Good record);

    List<Good> selectList();

    List<Good> selectByNameAndGoodId(@Param("goodName") String goodName, @Param("goodId") Integer goodId);

    List<Good> selectByNameAndCategoryIds(@Param("goodName") String goodName,@Param("categoryIdList")List<Integer> categoryIdList);
}