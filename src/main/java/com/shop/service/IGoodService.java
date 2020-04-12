package com.shop.service;

import com.github.pagehelper.PageInfo;
import com.shop.common.ServerResponse;
import com.shop.pojo.Good;
import com.shop.vo.GoodDetailVo;


public interface IGoodService {

    ServerResponse saveOrUpdateGood(Good good);

    ServerResponse<String> setSaleStatus(Integer goodId, Integer status);

    ServerResponse<GoodDetailVo>  manageGoodDetail(Integer goodId);

    ServerResponse<PageInfo> getGoodList(int pageNum, int pageSize);

    ServerResponse<PageInfo> searchGood(String goodName, Integer goodId, int pageNum, int pageSize);

    ServerResponse<GoodDetailVo> getGoodDetail(Integer goodId);

    ServerResponse<PageInfo> getGoodByKeywordCategory(String keyword, Integer categoryId, int pageNum, int pageSize, String orderBy);
}
