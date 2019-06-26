package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.sellergoods.service.BrandService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName BrandServiceImpl
 * @Auther: Jiang YiLin
 * @Date: 2019/6/12  21:38
 * @Description:
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private TbBrandMapper mapper;
    @Override
    public List<TbBrand> findAll() {
        return mapper.selectByExample(null);
    }



    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbBrand> page=   (Page<TbBrand>) mapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void add(TbBrand brand) {
        mapper.insert(brand);
    }

    @Override
    public void update(TbBrand brand) {
        mapper.updateByPrimaryKey(brand);
    }

    @Override
    public TbBrand findOne(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Long[] ids) {

        for(Long id:ids){
            mapper.deleteByPrimaryKey(id);
        }

    }

    @Override
    public PageResult findPage(TbBrand brand, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        TbBrandExample example=new TbBrandExample();
        TbBrandExample.Criteria criteria = example.createCriteria();
        if(brand!=null){
            if(brand.getName()!=null && brand.getName().length()>0){
                criteria.andNameLike("%"+brand.getName()+"%");
            }
            if(brand.getFirstChar()!=null && brand.getFirstChar().length()>0){
                criteria.andFirstCharEqualTo(brand.getFirstChar());
            }
        }
        Page<TbBrand> page= (Page<TbBrand>)mapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }


}
