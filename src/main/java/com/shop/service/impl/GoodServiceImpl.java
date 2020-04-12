package com.shop.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.shop.common.Const;
import com.shop.common.ResponseCode;
import com.shop.common.ServerResponse;
import com.shop.dao.CategoryMapper;
import com.shop.dao.GoodMapper;
import com.shop.pojo.Category;
import com.shop.pojo.Good;
import com.shop.service.ICategoryService;
import com.shop.service.IGoodService;
import com.shop.util.DateTimeUtil;
import com.shop.vo.GoodDetailVo;
import com.shop.vo.GoodListVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("iProductService")
public class GoodServiceImpl implements IGoodService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private GoodMapper goodMapper;
    @Autowired
    private ICategoryService iCategoryService;


    public ServerResponse saveOrUpdateGood(Good good){
        if(good != null){
            if(StringUtils.isNoneBlank(good.getImage())){
                String[] subImageArray = good.getImage().split(",");
                if(subImageArray.length > 0){
                    good.setImage(subImageArray[0]);
                }
            }
            if(good.getGoodId() != null){
                int rowCount  = goodMapper.updateByPrimaryKey(good);
                if(rowCount > 0) {
                    return ServerResponse.createBySuccess("更新产品成功");
                }
                return ServerResponse.createBySuccess("更新产品失败");
            }else{
                int rowCount  = goodMapper.insert(good);
                if(rowCount > 0) {
                    return ServerResponse.createBySuccess("新增产品成功");
                }
                return ServerResponse.createBySuccess("新增产品失败");
            }
        }
        return ServerResponse.createByErrorMessage("新增或更新产品参数不正确");
    }

    public ServerResponse<String> setSaleStatus(Integer goodId,Integer status){
        if(goodId == null || status == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Good good = new Good();
        good.setGoodId(goodId);
        good.setStatus(status);
        int rowCount = goodMapper.updateByPrimaryKeySelective(good);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("修改产品销售状态成功");
        }
        return ServerResponse.createByErrorMessage("修改产品销售状态失败");
    }

    public ServerResponse<GoodDetailVo>  manageGoodDetail(Integer goodId){
        if(goodId == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Good product = goodMapper.selectByPrimaryKey(goodId);
        if(product == null){
            return ServerResponse.createByErrorMessage("产品已下架或者删除");
        }
        GoodDetailVo productDetailVo = assembleGoodDetailVo(product);
        return ServerResponse.createBySuccess(productDetailVo);
    }
//
    private GoodDetailVo assembleGoodDetailVo(Good good){
        GoodDetailVo goodDetailVo = new GoodDetailVo();
        goodDetailVo.setGoodId(good.getGoodId());
        goodDetailVo.setTitle(good.getTitle());
        goodDetailVo.setPrice(good.getPrice());
        goodDetailVo.setImage(good.getImage());
        goodDetailVo.setCategoryId(good.getCategoryId());
        goodDetailVo.setDetail(good.getDetail());
        goodDetailVo.setGoodName(good.getGoodName());
        goodDetailVo.setStatus(good.getStatus());

        //imageHost
        //parentCategoryId
        Category category = categoryMapper.selectByPrimaryKey(good.getCategoryId());
        if(category == null){
            goodDetailVo.setParentCategoryId(0); //默认根节点
        }else {
            goodDetailVo.setParentCategoryId(category.getParentId());
        }

        goodDetailVo.setCreateTime(DateTimeUtil.dateToStr(good.getCreateTime()));
        goodDetailVo.setUpdateTime(DateTimeUtil.dateToStr(good.getUpdateTime()));

        return goodDetailVo;
    }

    public ServerResponse<PageInfo> getGoodList(int pageNum,int pageSize){
        //startPage--start
        //填充自己的sql查询逻辑
        //pageHelper--收尾
        PageHelper.startPage(pageNum,pageSize);
        List<Good> goodList = goodMapper.selectList();
        List<GoodListVo> goodListVoList = Lists.newArrayList();
        for(Good goodItem : goodList){
            GoodListVo productListVo = assembleGoodListVo(goodItem);
            goodListVoList.add(productListVo);
        }

        PageInfo pageResult = new PageInfo(goodList);
        pageResult.setList(goodListVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    private GoodListVo assembleGoodListVo(Good good){
        GoodListVo productListVo = new GoodListVo();
        productListVo.setGoodId(good.getGoodId());
        productListVo.setGoodName(good.getGoodName());
        productListVo.setCategoryId(good.getCategoryId());
        productListVo.setImage(good.getImage());
        productListVo.setPrice(good.getPrice());
        productListVo.setTitle(good.getTitle());
        productListVo.setStatus(good.getStatus());

        return productListVo;
    }


    public ServerResponse<PageInfo> searchGood(String goodName,Integer goodId,int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        if(StringUtils.isNotBlank(goodName)){
            goodName = new StringBuilder().append("%").append(goodName).append("%").toString();
        }
        List<Good> goodList = goodMapper.selectByNameAndGoodId(goodName,goodId);

        List<GoodListVo> goodListVoList = Lists.newArrayList();
        for(Good goodItem : goodList){
            GoodListVo goodListVo = assembleGoodListVo(goodItem);
            goodListVoList.add(goodListVo);
        }
        PageInfo pageResult = new PageInfo(goodList);
        pageResult.setList(goodListVoList);
        return ServerResponse.createBySuccess(pageResult);
    }


    public ServerResponse<GoodDetailVo> getGoodDetail(Integer goodId){
        if(goodId == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Good good = goodMapper.selectByPrimaryKey(goodId);
        if(good == null){
            return ServerResponse.createByErrorMessage("产品已下架或者删除");
        }
        if(good.getStatus() != Const.GoodStatusEnum.ON_SALE.getCode()){
            return ServerResponse.createByErrorMessage("产品已下架或者删除");
        }
        GoodDetailVo productDetailVo = assembleGoodDetailVo(good);
        return ServerResponse.createBySuccess(productDetailVo);
    }

    public ServerResponse<PageInfo> getGoodByKeywordCategory(String keyword,Integer categoryId,int pageNum,int pageSize,String orderBy){
        if(StringUtils.isBlank(keyword) && categoryId ==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        List<Integer> categoryIdList = new ArrayList<Integer>();

        if(categoryId != null){
            Category category = categoryMapper.selectByPrimaryKey(categoryId);
            if(category == null && StringUtils.isBlank(keyword)){
                //没有该分类，并且没有关键字，这个时候返回一个空的结果集，不报错
                PageHelper.startPage(pageNum,pageSize);
                List<GoodListVo> goodListVoList = Lists.newArrayList();
                PageInfo pageInfo = new PageInfo(goodListVoList);
                return ServerResponse.createBySuccess(pageInfo);
            }
            if(category !=null) {
                categoryIdList = iCategoryService.selectCategoryAndChildrenById(category.getCategoryId()).getData();
            }
        }
        if(StringUtils.isNotBlank(keyword)){
            keyword = new StringBuilder().append("%").append(keyword).append("%").toString();
        }
        PageHelper.startPage(pageNum,pageSize);
        //排序处理
        if(StringUtils.isNotBlank(orderBy)){
            if(Const.GoodListOrderBy.PRICE_ASC_DESC.contains(orderBy)){
                String[] orderByArray = orderBy.split("_");
                PageHelper.orderBy(orderByArray[0]+" "+orderByArray[1]);
            }
        }
        List<Good> goodList = goodMapper.selectByNameAndCategoryIds(StringUtils.isBlank(keyword)?null:keyword,categoryIdList.size()==0?null:categoryIdList);
        List<GoodListVo> goodListVoList =Lists.newArrayList();
        for(Good good : goodList){
            GoodListVo goodListVo = assembleGoodListVo(good);
            goodListVoList.add(goodListVo);
        }

        PageInfo pageInfo = new PageInfo(goodList);
        pageInfo.setList(goodListVoList);
        return ServerResponse.createBySuccess(pageInfo);


    }

}
