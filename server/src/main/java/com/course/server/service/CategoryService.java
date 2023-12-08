package com.course.server.service;

import com.course.server.dto.CategoryDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.entity.Category;
import com.course.server.entity.CategoryExample;
import com.course.server.mapper.CategoryMapper;
import com.course.server.utils.BeanCopyUtils;
import com.course.server.utils.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    //查询分类列表
    public void list(PageDto pageDto) {
        //开启分页
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        //查询分类列表
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        //获取分页数据
        PageInfo<Category> pageInfo = new PageInfo<>(categoryList);
        //添加总条数到pageDto
        pageDto.setTotal(pageInfo.getTotal());
        //添加数据到pageDto
        List <CategoryDto> categoryDtoList = BeanCopyUtils.copyBeanList(categoryList, CategoryDto.class);
        pageDto.setList(categoryDtoList);
    }

    /**
     * 查询所有分类
     * @param responseDto
     */
    public void all(ResponseDto responseDto) {
        CategoryExample categoryExample = new CategoryExample();
        List<CategoryDto> categoryDtos = BeanCopyUtils.copyBeanList(categoryMapper.selectByExample(categoryExample), CategoryDto.class);
        responseDto.setContent(categoryDtos);
    }

    /**
    * @param categoryDto
    */
    public void save(CategoryDto categoryDto) {
        //dto转换实体
        Category category = BeanCopyUtils.copyBean(categoryDto, Category.class);
        //判断id字段是否为空
        if (ObjectUtils.isEmpty(categoryDto.getId())) {
        //为空 则新增
        insert(category);
        } else if (!ObjectUtils.isEmpty(categoryDto.getId())) {
        //不为空 修改
        update(category);
        }
    }

    /**
    * 新增分类
    *
    * @param category
    */
    private void insert(Category category) {
        //生成分类id
        category.setId(UuidUtil.getShortUuid());
        //新增分类
        categoryMapper.insert(category);
    }

    /**
    * 修改分类
    *
    * @param category
    */
    private void update(Category category) {
        categoryMapper.updateByPrimaryKey(category);
    }

    /**
    * 删除分类
    * @param id
    */
    public ResponseDto delete(String id) {
        int result = categoryMapper.deleteByPrimaryKey(id);
        if (result == 1) {
        return ResponseDto.successResult();
        }
        return ResponseDto.errorResult();
    }

}
